package CosmicWipeout;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.Scanner;

public class m_SqlConnector {//rewrite this all chiki briki on campus
        private Connection dbConnection;

        public void saveToDB(String humanName, String computerName, LinkedList<m_ScoreRecord> records) throws Exception{ //muh not a collection (just call it a queue?)
            connectToDatabase();
            if (dbConnection != null) {
                insertIntoGame(humanName, computerName);
                insertIntoScore(records);
            }
        }
		  
        private void closeConnection() {
            try {
                dbConnection.close();
                System.out.println("Database connection has been closed.");
            }
            catch (SQLException ex) {
                reportSQLError(ex, "closeConnection");
            }
        }
        //pre: A ResultSet object needs to be closed.
//post: ResultSet object now closed.
        private void closeResultSet(ResultSet rs) {
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (SQLException sqlEx) {
                }
                rs = null;
            }
        }
        //pre: A Statement object needs to be closed.
//post: Statement object now closed.
        private void closeStatement(Statement stmt) {
            if (stmt != null) {
                try {
                    stmt.close();
                }
                catch (SQLException sqlEx) {
                }
                stmt = null;
            }
        }
        //pre: Need connection to database.
//pre: The CLASSPATH includes the location of the MySQL JDBC driver, which is        in a jar file.
        //     The jar file name is something like mysql-connector-java-8.0.21.jar
//     The instructor puts this jar file in the same folder as the source        code files.
        //post: Connected to AddressBook database.
        protected void connectToDatabase() throws Exception {
            try {
//Register driver name with the DriverManager.
                Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance(); //Connect to a database
                dbConnection = DriverManager.getConnection(
                        "jdbc:mysql://cscmysql.lemoyne.edu/game276germakovski?" +
                                "user=germakovski&password=pol");
                System.out.println("Connected to game database.");
            }
            catch (SQLException ex) {
                dbConnection = null;
                reportSQLError(ex, "connectToDatabase");
            }
            catch (Exception ex) {
                dbConnection = null;
                System.out.println(ex);
            }
        }
               //pre: Have connection to AddressBook database.
//post: Added one new contact.
        private void insertIntoGame(String humanName, String computerName) {
		  		String insertNames = "insert into Game (computerName, humanName) VALUES ('" + computerName + "', '" + humanName +"');"; //should ref comp name constant?
						PreparedStatement stmt = null;
            try {
					 stmt = dbConnection.prepareStatement(insertNames);
					 stmt.executeUpdate();
                }
			  catch (SQLException ex) {
                reportSQLError(ex, "insertNames");
            }
            closeStatement(stmt);
        }

        private void insertIntoScore(LinkedList<m_ScoreRecord> records){ //generalize
            String insertScoreRecord = ""; //should ref comp name constant?
            PreparedStatement stmt = null;
            try {
                for (m_ScoreRecord record: records){
                    insertScoreRecord += "insert into Score SET player = '" + record.getPlayer() + "',";
                    insertScoreRecord += "turnScore = '" + record.getTurnScore() + "',";
                    insertScoreRecord += "totalScore = '" + record.getTotalScore() + "',";
                    insertScoreRecord += "gameId = (SELECT MAX(id) FROM Game);"; //just fetch this once?
                }
                stmt = dbConnection.prepareStatement(insertScoreRecord);
                stmt.executeUpdate();
            }
            catch (SQLException ex) {
                reportSQLError(ex, "insertScoreRecord");
            }
            closeStatement(stmt);
        }
		  
        //pre: A SQLException has occured.
//post: The SQLException has been displayed via console output.
        private void reportSQLError(SQLException ex, String methodName) {
            System.out.println("SQLException in " + methodName + ":");
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
}//don't need to print database status