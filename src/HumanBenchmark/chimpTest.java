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
    String BACKGROUNDBLUE = "-fx-background-color: #2b86d1";
    private int highScore, numCount, tempCount, strikes;
    private String playerGuess;
    private Labeled mainLabel, subLabel1, subLabel2, scoreLabel;
    private Button startBtn;
    private VBox vboxDefault;

    /**
     * This method sets up the VBox that the user will see when they
     * choose to play this game from the main screen.
     * 
     * @return VBox that describes how to play this game.
     */
    public VBox playGame() {

        // Used to start the game with 4 tiles.
        numCount = 4;

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
        startBtn = new Button("Start Test");
        startBtn.setStyle("-fx-background-color: #ffd154");
        startBtn.setPrefSize(145, 45);
        startBtn.setFont(Font.font("Arial", FontWeight.BOLD, 20));
     
        // Vbox to store title labels, score label, and the start button.
        vboxDefault = new VBox();
        vboxDefault.setPadding(new Insets(10, 10, 10, 10));
        vboxDefault.setStyle(BACKGROUNDBLUE);
        vboxDefault.setMinHeight(550);
        vboxDefault.setMaxHeight(550);
        vboxDefault.setSpacing(20);
        vboxDefault.getChildren().addAll(scoreLabel, iconView,mainLabel, subLabel1, subLabel2,startBtn);
        vboxDefault.setAlignment(Pos.CENTER);

        startBtn.setOnMouseClicked(e -> {

            tempCount = numCount;

            // Boolean array to verify if a gamebaord space is occupied or not.
            boolean[] indexArray = new boolean[40];

            // Initialize all indices to false
            for(int i=0; i<40; i++){
                indexArray[i] = false;
            }
            
            // Create the gridpane to hold the numbers on-screen.
            GridPane gameBoard = new GridPane();

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
                Random rand = new Random();
                int randRow = rand.nextInt(5);
                int randCol = rand.nextInt(8);

                int boardIndex = randRow*8 + randCol;
                
                // If there is no number there, place a number.
                if(indexArray[boardIndex] == false){                
                    Button numBtn = new Button(Integer.toString(tempCount));
                    numBtn.setMinSize(75,75);
                    numBtn.setFont(Font.font("Arial", 40));
                    numBtn.setTextFill(Color.web("#FFFFFF"));
                    numBtn.setStyle("-fx-border-color: #FFFFFF;-fx-border-width: 2px;"+ BACKGROUNDBLUE);

                    numBtn.setOnMouseEntered(event ->{
                        numBtn.setTextFill(Color.RED);
                        numBtn.setFont(Font.font("Arial",FontWeight.BOLD, 40));
                    });

                    numBtn.setOnMouseExited(event ->{
                        numBtn.setTextFill(Color.WHITE);
                        numBtn.setFont(Font.font("Arial",40));
                    });

                    gameBoard.add(numBtn, randCol, randRow);

                    tempCount--;
                    indexArray[boardIndex] = true;
                    
                    numBtn.setOnMouseClicked(ev -> {

                        // If the button is "in-play"
                        if(Integer.parseInt(numBtn.getText()) > 0){
                            playerGuess = numBtn.getText();
                            tempCount++;

                            // White out the buttons if past level 1.
                            if(numCount > 4){
                                gameBoard.getChildren().
                            }

                            
                            if(playerGuess.equals(Integer.toString(tempCount)) 
                               && tempCount == numCount) {
                                   numCount++;
                                   showStatusScreen(numCount);
                            }
                            else if(playerGuess.equals(Integer.toString(tempCount))) {
                                System.out.println("Right");
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
     * 
     */
    private void showStatusScreen(int newCount) {
        numCount = newCount;

        mainLabel.setText("Numbers: " + Integer.toString(numCount));
        subLabel2.setText("Strikes: " +
            Integer.toString(strikes) +
            " of 3");
        startBtn.setText("Continue");
        vboxDefault.getChildren().clear();
        vboxDefault.getChildren().addAll(mainLabel, subLabel2, startBtn);
    }

    /**
     * 
     */
    private void endGame() {
        mainLabel.setText("Score: " + Integer.toString(numCount));

        updateScore(numCount);

        numCount = 4;
        strikes = 0;
        startBtn.setText("try again");

        vboxDefault.getChildren().clear();
        vboxDefault.getChildren().addAll(scoreLabel, mainLabel, startBtn);
    }

    private void updateScore(int newScore) {
        if(highScore == 0 || newScore > highScore){
            this.highScore = newScore;
            scoreLabel.setText("HighScore: " + String.valueOf(highScore));
        }
    }

    /**
     * @return return this game's highest score values so far.
     */
    public int getHighScore(){
        return highScore;
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

        this.highScore = 0;

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

        vbox.getChildren().addAll(iconView, mainLabel, subLabel);

        return vbox;
    }
}