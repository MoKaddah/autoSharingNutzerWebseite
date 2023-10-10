package de.unidue.inf.is.stores;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import de.unidue.inf.is.utils.DBUtil;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

public class BenutzerStore {
	
	private Connection connection;
    private boolean complete;


    public BenutzerStore() throws StoreException, IOException {
        try {
            connection = DBUtil.getConnection();
            connection.setAutoCommit(false);
        }
        catch (SQLException e) {
            throw new StoreException(e);
        }
    }
    public int getID(String email) throws SQLException{
        connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select bid from benutzer where email = '"+email+"'");
        ResultSet rs = preparedStatement.executeQuery();
        int res=0;
        if(rs.next()){
            res=rs.getInt(1);
        }
        return res;
    }
    public String getPassword(String email) throws SQLException{
        connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from benutzer where email = '"+email+"'");
        ResultSet rs = preparedStatement.executeQuery();
        String res="";
        if(rs.next()){
            res=rs.getString(3);
        }
        return res;
    }
    
    public String getName(int bid) throws SQLException{
        connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from benutzer where bid = '"+bid+"'");
        ResultSet rs = preparedStatement.executeQuery();
        String res="";
        if(rs.next()){
            res=rs.getString(2);
        }
        return res;
    }
    
    public boolean isAdmin(int id) throws SQLException{
        connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select isAdmin from benutzer where bid = '"+id+"'");
        ResultSet rs = preparedStatement.executeQuery();
        boolean is=false;
        int res=0;
        if(rs.next()){
            res=rs.getInt(1);
        }
        if(res==1){
            is=true;
        }
        return is;
    }
    
    public void addBenutzer(String name,String email,String password){
        try {
            connection = DBUtil.getConnection();
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO benutzer (name,email,passwort,isAdmin) VALUES (?,?,?,?)");
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, password);
            stmt.setInt(4, 0);
            stmt.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            throw new StoreException(e);
	}    
    }
    public void complete() {
        complete = true;
    }
    
    public void close() throws IOException {
        if (connection != null) {
            try {
                if (complete) {
                    connection.commit();
                }
                else {
                    connection.rollback();
                }
            }
            catch (SQLException e) {
                throw new StoreException(e);
            }
            finally {
                try {
                    connection.close();
                }
                catch (SQLException e) {
                    throw new StoreException(e);
                }
            }
        }
    }


}
