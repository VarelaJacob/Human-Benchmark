package HumanBenchmark;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 */
public class HighScores {

    // Handles connection to the db.
    public static Connection connection;
    public static boolean hasData = false;

    /**
     * This method will display the currently recorded high scores for each of the
     * games.
     * 
     * @return a ResultSet containing the high scores from each game.
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public ResultSet displayScores() throws ClassNotFoundException, SQLException {

        if (connection == null) {
            getConnection();
        }

        Statement state = connection.createStatement();

        ResultSet scores = state.executeQuery("SELECT score FROM game");

        return scores;
    }

    /**
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    private void getConnection() throws ClassNotFoundException, SQLException {

        Class.forName("org.sqlite.JDBC");

        // set Connection to the appropriate directory.
        connection = DriverManager.getConnection("jdbc:sqlite:HighScores.db");

        // Internal method to initialize the db.
        initialize();
    }

    /**
     * @throws SQLException
     */
    private void initialize() throws SQLException {

        if( !hasData ) {

            hasData = true;

            Statement state = connection.createStatement();
            
            ResultSet scores = state.executeQuery(
                "SELECT scores FROM sqlite master WHERE type = 'table' " + 
                "AND name ='game'");
        }
    }
}
