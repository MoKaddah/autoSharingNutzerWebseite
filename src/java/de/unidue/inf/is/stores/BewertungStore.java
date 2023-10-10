package de.unidue.inf.is.stores;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import de.unidue.inf.is.utils.DBUtil;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BewertungStore {
	
	private Connection connection;
    private boolean complete;


    public BewertungStore() throws StoreException, IOException  {
        try {
            connection = DBUtil.getConnection();
            connection.setAutoCommit(false);
        }
        catch (SQLException e) {
            throw new StoreException(e);
        }
    }
    
    //insert new fahrt bewertung
    public void insertBewertung(int rating,String text,int fid){
        try {
            connection = DBUtil.getConnection();
            PreparedStatement stmt = null;
            stmt = connection.prepareStatement("INSERT INTO bewertung (Rating, Textnachricht, fid) VALUES (?,?,?)");
            stmt.setInt(1, rating);
            stmt.setString(2, text);
            stmt.setInt(3, fid);
            stmt.executeUpdate();
        }catch (SQLException e) {
            throw new StoreException(e);
        }
    }
    
    //get fahrt rating text (Bewertung text)
    public ArrayList<String> getBewertung(int fid){
        try {
                PreparedStatement stmt = null;

                ResultSet rs = null;

                stmt = connection.prepareStatement("SELECT Textnachricht FROM bewertung where fid=?");

                stmt.setInt(1, fid);
                
                ArrayList<String> strList=new ArrayList();

                rs = stmt.executeQuery();
                
                while(rs.next()){
                    String text=rs.getString(1);
                    strList.add(text);
                }
 
                return strList;
        } catch (SQLException e) {
                throw new StoreException(e);
        }
    }
    
    // get fahrt rating avg
    public double Druchschnittsrating (int fid) {		
		try {
			PreparedStatement stmt = null;
			
			ResultSet rs = null;
			
			stmt = connection.prepareStatement("SELECT AVG(rating) FROM bewertung where fid=?");
			
			stmt.setInt(1, fid);
			
			rs = stmt.executeQuery();
			
			rs.next();
			
			double DR = rs.getDouble(1);
			
			return DR;
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
