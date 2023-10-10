package de.unidue.inf.is.stores;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import de.unidue.inf.is.utils.DBUtil;

public class ReservierenStore {
	
	private Connection connection;
    private boolean complete;


    public ReservierenStore() throws StoreException, IOException {
        try {
            connection = DBUtil.getConnection();
            connection.setAutoCommit(false);
        }
        catch (SQLException e) {
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
