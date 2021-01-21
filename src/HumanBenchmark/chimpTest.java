package HumanBenchmark;

import java.util.Random;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.paint.Color;

/**
 * This is the class for the Chimp Test game. The objective of this game is to
 * click on squares in order from lowest to highest based on their number value.
 * As you clear a level more numbers will be added to the screen.
 * 
 * @author Jacob Varela
 */
public class chimpTest {

    // Global variables.
    private final String BORDERHIGHLIGHT = "-fx-border-color: yellow;-fx-border-width: 10; -fx-background-color: #FFFFFF";
    private final String BACKGROUNDBLUE = "-fx-background-color: #2b86d1";
    private int numCount, tempCount, strikes;
    private long highScore;
    private String playerGuess;
    private Labeled mainLabel, subLabel1, subLabel2, scoreLabel;
    private Button newLevelBtn;
    private VBox vboxDefault;
    private GridPane gameBoard;
    private int[] rowArray,colArray;
    private boolean buttonsHidden;

    /**
     * This method sets up the VBox that the user will see when they
     * choose to play this game from the main screen.
     * 
     * @return VBox that describes how to play this game.
     */
    public VBox playGame() {
        
        // Used to start the game with 4 tiles.
        numCount = 4;

        // Initialize number of strikes and playerGuess.
        strikes = 0;
        playerGuess = "";

        // Icon for this game.
        ImageView iconView = new ImageView(new Image("file:resources/chimpIcon2.png"));

        // Main title label.
        mainLabel = new Label("Are You Smarter Than a Chimpanzee?");
        mainLabel.setFont(Font.font("Arial", FontWeight.BOLD, 50));
        mainLabel.setTextFill(Color.web("#FFFFFF"));

        // Subtitle label #1
        subLabel1 = new Label("Click the Squares in order according to their numbers.");
        subLabel1.setFont(Font.font("Arial", 20));
        subLabel1.setTextFill(Color.web("#FFFFFF"));

        // Subtitle label #2
        subLabel2 = new Label("This test will get progressively harder.");
        subLabel2.setFont(Font.font("Arial", 20));
        subLabel2.setTextFill(Color.web("#FFFFFF"));

        // Showcases this game's high score.
        scoreLabel = new Label("HighScore: " + String.valueOf(highScore));
        scoreLabel.setFont(Font.font("Arial", FontPosture.ITALIC, 14));
        scoreLabel.setTextFill(Color.web("#FFFFFF"));       

        // Button that will start the game when clicked on.
        newLevelBtn = new Button("Start Test");
        newLevelBtn.setStyle("-fx-background-color: #ffd154");
        newLevelBtn.setPrefSize(145, 45);
        newLevelBtn.setFont(Font.font("Arial", FontWeight.BOLD, 20));
     
        // Vbox to store title labels, score label, and the start button.
        vboxDefault = new VBox();
        vboxDefault.setPadding(new Insets(10, 10, 10, 10));
        vboxDefault.setStyle(BACKGROUNDBLUE);
        vboxDefault.setMinHeight(550);
        vboxDefault.setMaxHeight(550);
        vboxDefault.setSpacing(20);
        vboxDefault.getChildren().addAll(scoreLabel, iconView,mainLabel, subLabel1, subLabel2,newLevelBtn);
        vboxDefault.setAlignment(Pos.CENTER);

        /* When this button is clicked it will randomly place the appropriate
         * amount of game numbers on the gridPane depending on what level the
         * user is on. 
         */
        newLevelBtn.setOnMouseClicked(e -> {

            /* The tempCount variable is used to add numbers to the 
             * gameBoard.
             */
            tempCount = numCount;

            // Boolean indicating if the numbers are visible on screen or hidden.
            buttonsHidden = false;

            // Boolean array to verify if a gamebaord space is occupied or not.
            boolean[] indexArray = new boolean[40];

            // Interger arrays to store the locations of the numbers.
            rowArray = new int[numCount];
            colArray = new int[numCount];

            // Initialize all indices to false
            for(int i=0; i<40; i++){
                indexArray[i] = false;
            }

            // Initialize all indices to 0
            for(int i=0; i< numCount; i++){
                rowArray[i] = 0;
                colArray[i] = 0;
            }
            
            // Create the gridpane to hold the numbers on-screen.
            gameBoard = new GridPane();

            // Set Grid Pane size.
            gameBoard.setMinSize(800, 400);
            gameBoard.setMaxSize(800, 400);

            // Set Grid Pane padding.
            gameBoard.setPadding(new Insets(10, 10, 10, 10));

            // Set Grid Pane vertical and horizontal gaps.
            gameBoard.setVgap(5);
            gameBoard.setHgap(5);

            // Set Grid Pane alighment.
            gameBoard.setAlignment(Pos.CENTER);

            // While there are numbers to add on-screen, try and add to the gameBoard.
            while( tempCount > 0){
                
                // Use the Random class to randomly choose a row and column.
                Random rand = new Random();
                int randRow = rand.nextInt(5);
                int randCol = rand.nextInt(8);

                // This index is used to determine the location in the indexArray[]
                int boardIndex = randRow*8 + randCol;
                
                // If there is no number there, place a number.
                if(indexArray[boardIndex] == false){    

                    // Add the location to both arrays.
                    rowArray[tempCount-1] = randRow;
                    colArray[tempCount-1] = randCol;
                    
                    /* This button represents the numbers that you have to 
                     * choose from on-screen.
                     */
                    Button numBtn = new Button(Integer.toString(tempCount));
                    numBtn.setMinSize(75,75);
                    numBtn.setFont(Font.font("Arial", 40));
                    numBtn.setTextFill(Color.web("#FFFFFF"));
                    numBtn.setStyle("-fx-border-color: #FFFFFF;-fx-border-width: 2px;"+ BACKGROUNDBLUE);

                    // Highlight the number when the mouse is on the button.
                    numBtn.setOnMouseEntered(event ->{
                        numBtn.setTextFill(Color.RED);
                        numBtn.setFont(Font.font("Arial",FontWeight.BOLD, 40));
                    });

                    // Remove the number highlight when the mouse leaves the button.
                    numBtn.setOnMouseExited(event ->{
                        numBtn.setTextFill(Color.WHITE);
                        numBtn.setFont(Font.font("Arial",40));
                    });

                    // Add the number button to the gameBoard.
                    gameBoard.add(numBtn, randCol, randRow);

                    // Decrement the temp count.
                    tempCount--;

                    /* Mark the index as true, indicating that there is a button
                     * located on that space in the gameBoard.
                     */
                    indexArray[boardIndex] = true;
                    
                    /* Set up methods to move the game forward depending on
                     * whether or not the correct number was selected by the 
                     * user.
                     */
                    numBtn.setOnMouseClicked(ev -> {

                        // If the button is "in-play"
                        if(Integer.parseInt(numBtn.getText()) > 0){
                            playerGuess = numBtn.getText();
                            tempCount++;

                            // White out the buttons if past level 1.
                            if(numCount > 4 && !buttonsHidden){
                                buttonsHidden = true; 
                                gameBoard.getChildren().clear();
                                for(int k=numCount; k>0; k--){
                                    
                                    /* Create a new button. This button is 
                                     * whited out, but holds the same number
                                     * value as the button it is covering.
                                     */
                                    Button whtBtn = new Button(Integer.toString(k));
                                    whtBtn.setStyle("-fx-background-color: #FFFFFF");
                                    whtBtn.setTextFill(Color.web("#FFFFFF"));
                                    whtBtn.setMinSize(75,75);

                                    if(k==1){
                                        whtBtn.setOpacity(0);
                                    }
                                    
                                    /* If the player correctly selects the 
                                     * hidden number, remove the button from
                                     * the screen and allow the user to make 
                                     * another selection. If the player guesses 
                                     * incorrectly give them a strike and 
                                     * proceed appropriately.
                                     */
                                    whtBtn.setOnMouseClicked(event-> {
                                    if(Integer.parseInt(whtBtn.getText()) > 0){
                                        // Identify the player's guess.
                                        playerGuess = whtBtn.getText();
                                        
                                        tempCount++;           
                                        
                                        // If the player successfully guesses the last number.
                                        if(playerGuess.equals(Integer.toString(tempCount)) 
                                           && tempCount == numCount) {
                                               numCount++;
                                               showStatusScreen(numCount);
                                        }
                                        else if(playerGuess.equals(Integer.toString(tempCount))) {
                                            // Hide the button on-screen.
                                            whtBtn.setText("0");
                                            whtBtn.setOpacity(0);
                                        }
                                        else {
                                            // The player guessed incorrectly.
                                            strikes++;
            
                                            // End the game if the player has 3 strikes.
                                            if(strikes >= 3){
                                                endGame();
                                            }
                                            else{
                                                showStatusScreen(numCount);
                                            }
                                        }
                                    }});
                                    
                                    gameBoard.add(whtBtn, colArray[k-1], rowArray[k-1]);
                                }
                            }

                            /* The following if/else statements are only used
                             * for the first level, where the numbers are not
                             * hidden after the player guesses a number.
                             */
                            if(playerGuess.equals(Integer.toString(tempCount)) 
                               && tempCount == numCount) {
                                   numCount++;
                                   showStatusScreen(numCount);
                            }
                            else if(playerGuess.equals(Integer.toString(tempCount))) {
                                
                                numBtn.setText("0");
                                numBtn.setOpacity(0);
                            }
                            else {
                                strikes++;

                                if(strikes >= 3){
                                    endGame();
                                }
                                else{
                                    showStatusScreen(numCount);
                                }
                            }
                        }
                    });
                }
            }
            

            // Clear the vbox and show the in-game screen/elements.
            vboxDefault.getChildren().clear();
            vboxDefault.getChildren().addAll(gameBoard);


        });

        return vboxDefault;
    }

    /**
     * This screen is shown if the player has successfully or
     * unsuccessfully cleared a level. It displays how many numbers the user 
     * must complete in order to beat the next level they play.
     * @param newCount integer identifying the number of numbers on the next level. 
     */
    private void showStatusScreen(int newCount) {
        // Set the "level"
        numCount = newCount;

        // Update labels to show the user the most up-to-date information.
        mainLabel.setText("Numbers: " + Integer.toString(numCount));
        subLabel2.setText("Strikes: " +
            Integer.toString(strikes) +
            " of 3");
        newLevelBtn.setText("Continue");

        // Clear the current vbox and add the updated labels. 
        vboxDefault.getChildren().clear();
        vboxDefault.getChildren().addAll(mainLabel, subLabel2, newLevelBtn);
    }

    /**
     * This method will reset the global variables to the first level
     * of the game, tell the user what their score was, update the high
     * score if a new high score was achieved, and give the user an option
     * to try again. 
     */
    private void endGame() {
        // Update the final game score.
        mainLabel.setText("Score: " + Integer.toString(numCount));

        // Update the high score.
        updateScore(numCount);

        // Reset game variables to "level 1"
        numCount = 4;
        strikes = 0;

        // Update text from "Start Game" to "Try again"
        newLevelBtn.setText("Try again");

        // Clear current vbox and add the new elements.
        vboxDefault.getChildren().clear();
        vboxDefault.getChildren().addAll(scoreLabel, mainLabel, newLevelBtn);
    }

    /**
     * This method will update the high score of the game if there
     * is no current high score or if a new high score is achieved.
     * @param newScore The user's final score in the most recent playthrough.
     */
    private void updateScore(int newScore) {
        if(highScore == 0 || newScore > highScore){
            this.highScore = newScore;
            scoreLabel.setText("HighScore: " + String.valueOf(highScore));
        }
    }

    /**
     * @return return this game's highest score values so far.
     */
    public long getHighScore(){
        return highScore;
    }

    /**
     * Set a new high score value for this object.
     */
    public void setHighScore( long newVal) {
        highScore = newVal;
    }

     /**
     * This method creates a Vbox to be used on the bottom of the main screen.
     * This VBox will contain an icon representing the game, a label identifying
     * which game it is, as well as a quick description. A user can click on this
     * Vbox on the main screen to launch this game.
     * 
     * @return This game's vbox to be used on the homescreen.
     */
    public VBox createVBox() {

        ImageView iconView = new ImageView( new Image("file:resources/chimpIcon.png"));

        Label mainLabel = new Label("Chimp Test");
        mainLabel.setFont(Font.font("Arial", FontWeight.BOLD, 30));

        Label subLabel = new Label("Are you smarter than a chimpanzee?");
        subLabel.setFont(Font.font("Arial", 12));

        VBox vbox = new VBox();
        vbox.setMinSize(250, 250);
        vbox.setMaxSize(250, 250);
        vbox.setStyle("-fx-background-color: #FFFFFF");
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(20);

        // Highlight the vbox on Mouse hover.
        vbox.setOnMouseEntered(e -> {
            vbox.setStyle(BORDERHIGHLIGHT);
        });

        // Remove highlight when Mouse isn't hovering over.
        vbox.setOnMouseExited(ev -> {
            vbox.setStyle("-fx-background-color: #FFFFFF");
        });
        
        vbox.getChildren().addAll(iconView, mainLabel, subLabel);

        return vbox;
    }
}