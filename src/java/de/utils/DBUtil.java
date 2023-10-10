package de.unidue.inf.is.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public final class DBUtil {
    private static Connection con;
    Statement stmt;
    

    private DBUtil() {
    }
    
    public static Connection getConnection(){
        try{           
             Class.forName("com.mysql.jdbc.Driver");
             con=DriverManager.getConnection("jdbc:mysql://localhost:3306/database1?user=root&password="+"password");
             
             return con;
        }
        catch(Exception e){ System.out.println(e);}
        return con;
    }
    
     protected void closeConn() throws SQLException{
        con.close();
    }





}