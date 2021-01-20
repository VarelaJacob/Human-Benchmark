package HumanBenchmark;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 
 */
public class HighScores {

    // Handles connection to the db.
    public static Connection connection;
    public static boolean hasData = false;
    
    /**
     * This method will display the currently recorded high scores for
     * each of the games.
     * @return a ResultSet containing the high scores from each game.
     */
    public ResultSet displayScores() {

        if(connection == null){
            getConnection();
        }

        Statement state = connection.createStatement();

        ResultSet scores = state.executeQuery("SELECT score FROM game");

        return scores;
    }

    /** */
    private void getConnection() {

        Class.forName("org.sqlite.JDBC");

        // set Connection to the appropriate directory.
        connection = DriverManager.getConnection("jdbc:sqlite:HighScores.db");

        // Internal method to initialize the db.
        initialize();
    }
}
