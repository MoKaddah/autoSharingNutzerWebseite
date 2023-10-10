package de.unidue.inf.is.stores;

import de.unidue.inf.is.ViewDriveServlet;
import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import de.unidue.inf.is.domain.benutzer;
import de.unidue.inf.is.domain.bewertung;
import de.unidue.inf.is.domain.fahrt;
import de.unidue.inf.is.utils.DBUtil;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class FahrtStore  {
	
	private Connection connection;
    private boolean complete;


    public FahrtStore() throws StoreException, IOException {
        try {
            connection = DBUtil.getConnection();
            connection.setAutoCommit(false);
        }
        catch (SQLException e) {
            System.out.println("Connection null");
        }
    }
  
    public ArrayList<fahrt> getOffneFahrt() throws StoreException, SQLException {
    	
    	ArrayList<fahrt> Result = new ArrayList<fahrt>();
    	
    	try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from fahrt where STATUS = 'offen'");   
            ResultSet rs = preparedStatement.executeQuery();      
            while(rs.next()) {
            	fahrt temp = new fahrt();
            	temp.setFid(rs.getInt(1));
            	temp.setMaxplaetze(rs.getInt(2));
                temp.setBeschreibung(rs.getString(3));
                temp.setFahrtkosten(rs.getInt(4));
                temp.setStartort(rs.getString(5));
                temp.setZielort(rs.getString(6));
                temp.setFahrtdatum(rs.getString(7));
                temp.setFahrtuhrzeit(rs.getString(8));
                temp.setStatus(rs.getString(9));
                temp.setTransportmittel(rs.getString(10));
            	Result.add(temp);
            }
            
        }
    	catch (SQLException e) {
    		throw new StoreException(e);
    	}
    	
    	return Result;
    }
    
    public ArrayList<fahrt> getReserviertFahrt(int bid) throws StoreException, SQLException{
    	
    	ArrayList<fahrt> Result = new ArrayList<fahrt>();
    	
    	try {
            connection=DBUtil.getConnection();
            PreparedStatement preparedStatement = connection 
                    .prepareStatement("select * from fahrt f join reservieren r on f.fid=r.fid where r.bid=?");
            preparedStatement.setInt(1, bid);        
            ResultSet rs = preparedStatement.executeQuery(); 
            while(rs.next()) {
            	fahrt temp = new fahrt();
            	temp.setFid(rs.getInt(1));
            	temp.setMaxplaetze(rs.getInt(2));
                temp.setBeschreibung(rs.getString(3));
                temp.setFahrtkosten(rs.getInt(4));
                temp.setStartort(rs.getString(5));
                temp.setZielort(rs.getString(6));
                temp.setFahrtdatum(rs.getString(7));
                temp.setFahrtuhrzeit(rs.getString(8));
                temp.setStatus(rs.getString(9));
                temp.setTransportmittel(rs.getString(10));
            	Result.add(temp);
            }
            
        }
    	catch (SQLException e) {
    		throw new StoreException(e);
    	}
    	
    	return Result;
    }

	public void fahrtErstellen(String startort, String zielort, String fahrtdatum, String maxP, String fahrtkost, benutzer user, String transportmittle, String beschreibung,String fahrtuhrzeit) {
				
		int maxPlaetze = Integer.parseInt(maxP);
		double fahrtkosten = Double.parseDouble(fahrtkost);
		try {
                        connection = DBUtil.getConnection();
                        PreparedStatement stmt = null;
                        //insert into fahrt
			stmt = connection.prepareStatement("INSERT INTO fahrt (startort, zielort, fahrtdatum, maxPlaetze, fahrtkosten, beschreibung,transportmittel,fahrtuhrzeit,status,freePlaces) VALUES (?,?,?,?,?,?,?,?,?,?)");
			stmt.setString(1, startort);
			stmt.setString(2, zielort);
			stmt.setString(3,fahrtdatum);
			stmt.setInt(4, maxPlaetze);
			stmt.setDouble(5, fahrtkosten);
			stmt.setString(6, beschreibung);
                        stmt.setString(7, transportmittle);
                        stmt.setString(8, fahrtuhrzeit);
                        stmt.setString(9, "offen");
                        stmt.setInt(10, maxPlaetze);
			stmt.executeUpdate();
  
		} catch (SQLException e) {
			throw new StoreException(e);
		}		
	}
	
	
	
	public fahrt getFahrtTeil1(int fid) {
		fahrt temp = new fahrt();
		
		try {
			PreparedStatement stmt = null;
			
			ResultSet rs = null;
			
			stmt = connection.prepareStatement("SELECT * FROM fahrt where fid = ?");
			
			stmt.setInt(1,fid);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
                            temp.setFid(rs.getInt(1));
                            temp.setMaxplaetze(rs.getInt(2));
                            temp.setBeschreibung(rs.getString(3));
                            temp.setFahrtkosten(rs.getInt(4));
                            temp.setStartort(rs.getString(5));
                            temp.setZielort(rs.getString(6));
                            temp.setFahrtdatum(rs.getString(7));
                            temp.setFahrtuhrzeit(rs.getString(8));
                            temp.setStatus(rs.getString(9));
                            temp.setTransportmittel(rs.getString(10));
                            temp.setFreePlaces(rs.getInt(11));
			}
		} catch (SQLException e) {
			throw new StoreException(e);
		}
		return temp;
	}	
        
        public boolean reservation(int bid,int fid,int n){
            try {
                connection = DBUtil.getConnection();
                PreparedStatement stmt = null;
                stmt=connection.prepareStatement("select * from reservieren where bid=? and fid=?");
                stmt.setInt(1, bid);
                stmt.setInt(2, fid);
                ResultSet rs = stmt.executeQuery();
                if(rs.next()){
                    int sets=rs.getInt(3);
                    sets+=n;
                    connection.close();
                    connection = DBUtil.getConnection();
                    stmt = connection.prepareStatement("update reservieren set AnzPlaetze=? where bid=? and fid=?");
                    stmt.setInt(1, sets);
                    stmt.setInt(2, bid);
                    stmt.setInt(3, fid);
                    stmt.executeUpdate();
                }
                connection.close();
                connection = DBUtil.getConnection();
                stmt = connection.prepareStatement("INSERT INTO reservieren (bid, fid, AnzPlaetze) VALUES (?,?,?)");
                stmt.setInt(1, bid);
                stmt.setInt(2, fid);
                stmt.setInt(3, n);
                stmt.executeUpdate();
                //
                connection.close();
                connection = DBUtil.getConnection();
                stmt = connection.prepareStatement("select freePlaces from fahrt where fid=?");
                stmt.setInt(1, fid);
                ResultSet rs2 = stmt.executeQuery();
                if(rs2.next()){
                    int freePlaces=rs2.getInt(1);
                    freePlaces-=n;
                    connection.close();
                    connection = DBUtil.getConnection();
                    stmt = connection.prepareStatement("update fahrt set freePlaces=? where fid=?");
                    stmt.setInt(1, freePlaces);
                    stmt.setInt(2, fid);
                    stmt.executeUpdate();
                    //change the status
                    if(freePlaces==0){
                        stmt = connection.prepareStatement("update fahrt set status=? where fid=?");
                        stmt.setString(1, "Geschloseen");
                        stmt.setInt(2, fid);
                        stmt.executeUpdate();
                    }
                }
			
		} catch (SQLException e) {
                    Logger.getLogger(FahrtStore.class.getName()).log(Level.SEVERE, null, e);
                    return false;
		}
            return true;
        }	
        
        public ArrayList<fahrt> search(String start,String end,String date) throws SQLException{
            connection = DBUtil.getConnection();
            PreparedStatement stmt = null;
            stmt=connection.prepareStatement("select * from fahrt where startort like ? and zielort like ? and fahrtdatum=?");
            stmt.setString(1, start);
            stmt.setString(2, end);
            stmt.setString(3, date);
            ResultSet rs = stmt.executeQuery();    
            ArrayList<fahrt> fahrts=new ArrayList();
            while(rs.next()){
                fahrt f=new fahrt(rs.getString("fahrtdatum"), rs.getString("startort"), rs.getString("zielort"));
                f.setFahrtkosten(Double.parseDouble(rs.getString("fahrtkosten")));
                f.setTransportmittel(rs.getString(10));
                fahrts.add(f);
            }
            return fahrts;
        }
        
        public void cancel(int bid,int fid,int sets,int canceled){
            try {
                connection = DBUtil.getConnection();
                PreparedStatement stmt = null;
                stmt = connection.prepareStatement("update reservieren set AnzPlaetze=? where bid=? and fid=?");
                stmt.setInt(2, bid);
                stmt.setInt(3, fid);
                stmt.setInt(1, sets);
                stmt.executeUpdate();	
                //
                connection.close();
                connection = DBUtil.getConnection();
                stmt = connection.prepareStatement("select freePlaces from fahrt where fid=?");
                stmt.setInt(1, fid);
                ResultSet rs2 = stmt.executeQuery();
                if(rs2.next()){
                    int freePlaces=rs2.getInt(1);
                    //change the status
                    if(freePlaces==0){
                        stmt = connection.prepareStatement("update fahrt set status=? where fid=?");
                        stmt.setString(1, "Offene");
                        stmt.setInt(2, fid);
                        stmt.executeUpdate();
                    }
                    freePlaces+=canceled;
                    connection.close();
                    connection = DBUtil.getConnection();
                    stmt = connection.prepareStatement("update fahrt set freePlaces=? where fid=?");
                    stmt.setInt(1, freePlaces);
                    stmt.setInt(2, fid);
                    stmt.executeUpdate();
                }
		} catch (SQLException e) {
                     System.out.println(e);
		}
        }
        
        public void delete(int bid,int fid){
            try {
                connection = DBUtil.getConnection();
                PreparedStatement stmt = null;
                stmt = connection.prepareStatement("delete from reservieren where bid=? and fid=?");
                stmt.setInt(1, bid);
                stmt.setInt(2, fid);
                stmt.executeUpdate();			
		} catch (SQLException e) {
                     System.out.println(e);
		}
        }
        
        public int getAnzPlaetze(int bid,int fid){
            try{
                connection=DBUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("select * from reservieren where bid=? and fid=?");
                preparedStatement.setInt(1, bid); 
                preparedStatement.setInt(2, fid); 
                ResultSet rs = preparedStatement.executeQuery(); 
                if(rs.next())
                    return rs.getInt(3);
            }catch(SQLException e){
                 return -1;
            }
            return -1;
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


