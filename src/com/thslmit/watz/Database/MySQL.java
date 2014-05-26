package com.thslmit.watz.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTransientConnectionException;
import java.sql.Statement;

public class MySQL {
	
	private Connection mysql_connection;
	
	private boolean auto_reconnect = true;
    private int auto_reconnect_time = 5000;
    private int auto_reconnect_retry = 15;
    
    private String username_local_cache = null;
    private String password_local_cache = null;
    private String hostname_local_cache = null;
    private String database_local_cache = null;
    
    /**
     * Enable automatic auto_reconnect if the MySQL Database Connection is lost
     * By default this is enabled.
     * 
     * @see #DisableReconnect() 
     */
    public void EnableReconnect(){
        auto_reconnect = true;
    }
    /**
     * Disable automatic auto_reconnect if the MySQL Database Connection is lost
     * By default this is enabled
     * 
     * @see #EnableReconnect() 
     */
    public void DisableReconnect(){
        auto_reconnect = false;
    }
    /**
     * Test whether or not automatic reconnect is currently enabled.
     * By default automatic auto_reconnect is enabled
     * 
     * @return true if automatic auto_reconnect is enabled, false if it is disabled
     * @see #EnableReconnect() 
     * @see #DisableReconnect() 
     */
    public boolean isReconnectEnabled(){
        return auto_reconnect;
    }
    /**
     * Sets the waiting time before attempting to auto_reconnect to the MySQL
     * Database server.
     * 
     * Default waiting time is 5 seconds
     * 
     * @param time in milliseconds
     */
    public void setReconnectTime(int time){
        auto_reconnect_time = time;
    }    
    /**
     * Returns the waiting time before attempting to auto_reconnect to the MySQL 
     * Database server.
     * 
     * If this value was not changed with {@link #setReconnectTime(int)} the default
     * waiting time is 5 seconds
     * 
     * @return time in milliseconds
     * @see #setReconnectTime(int) 
     */
    public int getReconnectTime(){
        return auto_reconnect_time;
    }
    /**
     * Sets the maximum number of automatic reconnection attempts before giving
     * up and throwing an exception.
     * 
     * Default number of attempts is 15
     * 
     * @param retry_times
     */
    public void setReconnectNumRetry(int retry_times){
        auto_reconnect_retry = retry_times;
    }
    /**
     * Returns the maximum number of automatic reconnection attempts before giving
     * up and throwing an exception.
     * 
     * If this value was not changed with {@link #setReconnectNumRetry(int)} the default 
     * number of attempts is 15
     * 
     * @return Number of retries
     * @see #setReconnectNumRetry(int) 
     */
    public int getReconnectNumRetry(){
        return auto_reconnect_retry;
    }
    
    /**
     * Connect to the MySQL Server using default connection parameters.
     * 
     * <p>
     * <strong>HOST</strong>: mysql<br />
     * <strong>USER</strong>: root<br />
     * No Password
     * </p>
     * 
     * @return
     */
    public boolean connect(){
        return connect("mysql", "root", "");
    }
    
    /**
     * Connects to the MySQL Server using the given server, username, and password.
     * 
     * @param server
     * @param username
     * @param password
     * @return True on a successful connection
     */
    public boolean connect(String server, String username, String password) {
        String mysql_connectionURL;
        String mysql_driver;  
        
        //Cache the server, user, and password localy for auto-auto_reconnect
        username_local_cache = username;
        password_local_cache = password;
        hostname_local_cache = server;        
        
        try {
            //Load MySQL JDBC Driver
            mysql_driver = "com.mysql.jdbc.Driver";
            Class.forName(mysql_driver);

            //Open Connection
            mysql_connectionURL = "jdbc:mysql://" + server;
            mysql_connection = DriverManager.getConnection(mysql_connectionURL, username, password);
            return true;
        }        
        catch( Exception x ) {
            System.err.println("Can  not connect to the MySQL Database Server. "
                    + "Please check your configuration.\n\n"
                    + "Hostname: " + hostname_local_cache + "\n"
                    + "Username: " + username_local_cache + "\n\n"
                    + "Error: " + x.getLocalizedMessage());
            
            return false;
        }      
    }
    
    /**
     * Connects to the MySQL Server using the given server, username, and password. 
     * Auto selects the given database.
     * 
     * @param server
     * @param username
     * @param password
     * @param database
     * @return True on a successful connection.
     */
    public boolean connect(String server, String username, String password, String database) {
        //cache the database for auto-auto_reconnect
        database_local_cache = database;
        
        if (connect(server, username, password)){
            return SelectDB(database);
        }
        else {
            return false;
        }
    }
    
    /**
     * Manually select the database to Query.
     * 
     * @param database
     * @return True on successful operation.
     */
    public boolean SelectDB(String database){
        boolean result = true;
        try{
            mysql_connection.setCatalog(database);
        }
        catch(Exception e){
            System.err.println(e.getLocalizedMessage());
            result = false;
        }
        return result;
    }
    
    /**
     * Closes the current MySQL Connection.
     * 
     * @return True on close
     */
    public boolean close(){
        try{
            mysql_connection.close();
            return true;
        }
        catch (Exception x) {
             System.err.println("Could not close connection: " + x.getLocalizedMessage());
             return false;
        }
    }
    
    private boolean reconnect(String server, String username, String password, String database) throws SQLTransientConnectionException {        
        boolean connected = false;
        try{
            connected = connect(server, username, password, database);
        }
        catch(Exception e){   
            connected = false;
        }
        
        if (!connected){            
            throw new SQLTransientConnectionException("Unable to re-establish database connection, please try agian later.");
        }
        System.out.println("Database connection re-established");
        return connected;
    }
    
    private synchronized void auto_reconnect(){
        System.out.println("Attempting Auto-Reconnect...");
        
        //Clean and desrtoy anything that may be left
        try{mysql_connection.close(); mysql_connection = null;}catch(SQLException e){}
       
        //On a sucesufull connection stop retrying
        boolean connected = false;        
        
        //Attempt to reconnect up to the number of auto_reconnect_retry
        int retries_left = auto_reconnect_retry;
        while(retries_left > 0 && !connected){
            retries_left--;
            System.out.println("Auto-Reconnect Attempt #" + (auto_reconnect_retry - retries_left) + " of " + auto_reconnect_retry);
            try{
                wait(auto_reconnect_time);
                connected = reconnect(hostname_local_cache, username_local_cache, password_local_cache, database_local_cache);                
            }
            catch(InterruptedException i){
                System.err.println("Reconnect Canceled!");
            }      
            catch(SQLTransientConnectionException e){
                System.err.println("AUTO RECONNECT: " + e.getMessage());
            }
            catch(Exception e){
                System.err.println("Unkown faliure: " + e.getLocalizedMessage());
            }
        }
    }
    
    private void check_connection(){
        Statement stmt;
        ResultSet mysql_result;  
        try{
            //Execute Query
            stmt = mysql_connection.createStatement();
            mysql_result = stmt.executeQuery("SELECT 1 from DUAL WHERE 1=0");
            mysql_result.close();
        }
        catch(NullPointerException e){
            System.err.println("MySQL Database not connected!");
        }        
        catch(SQLTransientConnectionException e){
            System.err.println("Database connection problem");
            if(auto_reconnect){auto_reconnect();}
        }
        catch(SQLException e){
            System.err.println("Database Communications Error");
            if(auto_reconnect){auto_reconnect();}
        }  
        finally{
            mysql_result = null;
        }
    }
    
    /**
     * Old style SimpleMySQL query.
     * @deprecated This method is replaced by the new {@link #Query(java.lang.String)}
     * method.  
     * @param Query
     * @return For SELECT type queries a Java SQL ResultSet object. all other 
     * type of queries will return null
     * @see #Query(java.lang.String) 
     */
    @Deprecated
    public ResultSet query(String query){
        return Query(query).getResultSet();
    }
    
    /**
     * Executes a simple Query on the MySQL database.  You must first
     * connect to a MySQL database.  If a MySQL database is not connected
     * this will return null.
     * 
     * @param Query
     * @return For SELECT type queries a SimpleMySQLResult. All other type of 
     * queries will return null
     * @see #connect() 
     * @see #connect(java.lang.String, java.lang.String, java.lang.String) 
     * @see #connect(java.lang.String, java.lang.String, java.lang.String, java.lang.String) 
     */
    public MySQLResult Query(String query){
        //Make sure connection is alive
        check_connection();
        
        //Create Statement and result objects
        Statement stmt;
        ResultSet mysql_result;  
        MySQLResult result = null;
        
        /*
         * We want to keep things simple, so...
         *
         * Detect whether this is an INSERT, DELETE, or UPDATE statement      
         * And use the executeUpdate() function
         *
         * Or...
         * 
         * Detect whether this is a SELECT statment and use the executeQuery()
         * Function. 
         * 
        */                      
        try{
            if (query.startsWith("SELECT")) {
                //Use the "executeQuery" function becuase we have to retrive data
                //Return the data as a resultset

                //Execute Query
                stmt = mysql_connection.createStatement();
                mysql_result = stmt.executeQuery(query);
                result = new MySQLResult(mysql_result);                
            }
            else {
                //It's an UPDATE, INSERT, or DELETE statement
                //Use the "executeUpdate" function and return a null result

                //Execute Query
                stmt = mysql_connection.createStatement();
                stmt.executeUpdate(query);
            }
        }
        catch(NullPointerException y){
            System.err.println("You are not connected to a MySQL server");
        }
        catch(Exception x) {                
            System.err.println("ERROR: " + x.getLocalizedMessage());
        }            
        
        //Return the SimpleMySQLResult Object or null
        return result;                      
    }    	
}
