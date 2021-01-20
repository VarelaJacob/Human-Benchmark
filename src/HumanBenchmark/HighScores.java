package HumanBenchmark;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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

            /* if Nothing is found initialize initial high score table
             * and initialize all values to zero.
             */
            if ( !scores.next() ) {
               
                // Build the table
                Statement state2 = connection.createStatement();
                state2.execute("CREATE TABLE game(" 
                + "name varchar(15), highScore long, primary key(name));");

                // Initialize AimTrainer High Score to zero.
                PreparedStatement prep = connection.prepareStatement(
                    "INSERT INTO game values(?, ?);");
                prep.setString(1, "AimTrainer");
                prep.setLong  (2, 0);
                prep.execute();

                // Initialize ChimpTest High Score to zero.
                PreparedStatement prep2 = connection.prepareStatement(
                    "INSERT INTO game values(?, ?);");
                prep2.setString(1, "ChimpTest");
                prep2.setLong  (2, 0);
                prep2.execute();

                // Initialize NumberMemory High Score to zero.
                PreparedStatement prep3 = connection.prepareStatement(
                    "INSERT INTO game values(?, ?);");
                prep3.setString(1, "NumberMemory");
                prep3.setLong  (2, 0);
                prep3.execute();

                // Initialize ReactionTest High Score to zero.
                PreparedStatement prep4 = connection.prepareStatement(
                    "INSERT INTO game values(?, ?);");
                prep4.setString(1, "ReactionTest");
                prep4.setLong  (2, 0);
                prep4.execute();

                // Initialize VerbalMemory High Score to zero.
                PreparedStatement prep5 = connection.prepareStatement(
                    "INSERT INTO game values(?, ?);");
                prep5.setString(1, "VerbalMemory");
                prep5.setLong  (2, 0);
                prep5.execute();

                // Initialize VisualMemory High Score to zero.
                PreparedStatement prep6 = connection.prepareStatement(
                    "INSERT INTO game values(?, ?);");
                prep6.setString(1, "VisualMemory");
                prep6.setLong  (2, 0);
                prep6.execute();               
            }
        }
    }
}
