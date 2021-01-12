package HumanBenchmark;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.paint.Color;

/**
 * This is the class for the Visual Memory Test. 
 * The objective of this game is to remember highlighted
 * squares on a large board of squares and click on the highlighted 
 * squares once they are flipped over.
 * 
 * @author Jacob Varela
 */
public class VisualMemory {
    
    // Global variables.
    private final String BORDERHIGHLIGHT = "-fx-border-color: yellow;-fx-border-width: 10; -fx-background-color: #FFFFFF";
    private final String BACKGROUNDBLUE = "-fx-background-color: #2b86d1";
    private final String TILEWHITE = "-fx-background-color: #FFFFFF;"+
                                     "-fx-background-radius: 15px";
    private final String TILEBLACK = "-fx-background-color: #000000;"+
                                     "-fx-background-radius: 15px";
    private final String TILEBLUE  = "-fx-background-color: #000795;"+
                                     "-fx-background-radius: 15px";
    private final int SLEEPTIME = 1000; // milliseconds
    private int highScore, currentScore, currLvl, currLives, boardSize, tileSize;
    private Random rand = new Random();
    private GridPane gameBoard;
    private VBox vboxDefault;
    private Label scoreLabel, subLabel1, mainLabel;
    private Button newGameBtn;

    /**
     * This method sets up the VBox that the user will see when they
     * choose to play this game from the main screen.
     * 
     * @return VBox that describes how to play this game.
     */
    public VBox playGame() {

        // Start the game at level 1.
        currLvl = 1;

        // Boardsize will start as 3x3
        boardSize = 3;

        // Tiles start out as 125x125
        tileSize = 125;

        // Start the game with 3 lives.
        currLives = 3;

        // This game shares an icon with the chimp game.
        ImageView iconView = new ImageView(new Image("file:resources/chimpIcon2.png"));

        // Main title label.
        mainLabel = new Label("Visual Memory Test");
        mainLabel.setFont(Font.font("Arial", FontWeight.BOLD, 50));
        mainLabel.setTextFill(Color.web("#FFFFFF"));

        // Subtitle label.
        subLabel1 = new Label("Memorize the squares.");
        subLabel1.setFont(Font.font("Arial", 20));
        subLabel1.setTextFill(Color.web("#FFFFFF"));

        // High score label.
        scoreLabel = new Label("HighScore: Level " + String.valueOf(highScore));
        scoreLabel.setFont(Font.font("Arial", FontPosture.ITALIC, 14));
        scoreLabel.setTextFill(Color.web("#FFFFFF"));

        // Button to start the game when clicked on.
        newGameBtn = new Button("Start");
        newGameBtn.setStyle("-fx-background-color: #ffd154");
        newGameBtn.setPrefSize(145, 45);
        newGameBtn.setFont(Font.font("Arial", FontWeight.BOLD, 20));
       
        // Vbox to store title labels, score label, and the start button.
        vboxDefault = new VBox();
        vboxDefault.setPadding(new Insets(10, 10, 10, 10));
        vboxDefault.setStyle(BACKGROUNDBLUE);
        vboxDefault.setMinHeight(550);
        vboxDefault.setMaxHeight(550);
        vboxDefault.setSpacing(20);
        vboxDefault.getChildren().addAll(scoreLabel, iconView,mainLabel, subLabel1, newGameBtn);
        vboxDefault.setAlignment(Pos.CENTER);

        // Begin playing the game when the start button is clicked.
        newGameBtn.setOnMouseClicked(e ->{
            takeTurn();
        });

        return vboxDefault;
    }
    
    /**
     * 
     */
    private void takeTurn() {

        // tempCount used to add numbers to the gameBoard.
        int tempCount = currLvl + 2;

        // Increase the board size every 3 levels.
        if(currLvl % 3 == 0){
            boardSize++;
            tileSize -= 25;
        }

        // Boolean array to keep track of the game tiles.
        boolean[] indexArray = new boolean[boardSize*boardSize];

        // Integer Arrays to store the locations of the numbers.
        int[] rowArray = new int[tempCount];
        int[] colArray = new int[tempCount];

        // Initialize array to false;
        for(int i=0; i<indexArray.length; i++){
            indexArray[i] = false;
        }

        // Create the gridpane to hold the tiles.
        gameBoard = new GridPane();

        // Set the gameBoard size.
        gameBoard.setMinSize(800, 400);
        gameBoard.setMaxSize(800, 400);

        // Set Grid Pane padding.
        gameBoard.setPadding(new Insets(10, 10, 10, 10));

        // Set Grid Pane vertical and horizontal gaps.
        gameBoard.setVgap(5);
        gameBoard.setHgap(5);

        // Set Grid Pane alighment.
        gameBoard.setAlignment(Pos.CENTER);

        // Initialize the gameBoard with blank tiles.
        clearGameBoard();
    
        /* Begin the timer used to display the white tiles
         * after a small delay. 
         */
        Timer delayTimer = new Timer();
        delayTimer.schedule( new TimerTask(){
            
            @Override
            public void run() {
                Platform.runLater(() ->{

                    int tempCount2 = tempCount;

                    /* While there are not the correct number of tiles, 
                    * add them to the gameBoard.
                    */
                    while(tempCount2 > 0){

                        // Choose a random row and column.
                        int randRow = rand.nextInt(boardSize);
                        int randCol = rand.nextInt(boardSize);

                        // Determine the index in the indexArray[].
                        int tileIndex = randRow*boardSize + randCol;

                        // if there is no tile there, place a tile there.
                        if( indexArray[tileIndex] == false ){

                            // Add the location to both arrays.
                            rowArray[tempCount2-1] = randRow;
                            colArray[tempCount2-1] = randCol;

                            // Create the tile using a button element.
                            Button whiteBtn = new Button();
                            whiteBtn.setMinSize(tileSize, tileSize);
                            whiteBtn.setStyle(TILEWHITE);

                            // Add the tile to the gameBoard
                            gameBoard.add(whiteBtn, randCol, randRow);

                            // decrement the tempCount;
                            tempCount2--;

                            /* Mark the index as true, indicating that there is a tile
                            * located in that space on the gameBoard.
                            */
                            indexArray[tileIndex] = true;
                        }
                    }
                });
            }
        },SLEEPTIME);

        delayTimer.schedule( new TimerTask(){

            @Override
            public void run(){
                Platform.runLater(() ->{
                    clearGameBoard();
                });
            }
        },SLEEPTIME*3);        

        

        // Clear the vbox and show the new screen.
        vboxDefault.getChildren().clear();
        vboxDefault.getChildren().addAll(gameBoard);

    }

    /**
     * Initializes the gameboard with blank tiles. Used 
     * before and after the user is shown the white tiles.
     */
    private void clearGameBoard() {
        for(int i=0; i<boardSize; i++){
            for(int j=0; j<boardSize; j++){
                // Create blank button for the gameBoard.
                Button blankBtn = new Button();
                blankBtn.setMinSize(tileSize, tileSize);
                blankBtn.setStyle(TILEBLUE);    

                gameBoard.add(blankBtn, j, i);
            }
        }
    }

    /**
     * @return return this game's highest score values so far.
     */
    public int getHighScore(){
        return highScore;
    }

    /**
     * This method will look at the current global variables
     * in the game being played and will update this game's 
     * high score value if a higher values has been reached. 
     */
    private void updateHighScore() {
        if(highScore == 0 ){
            this.highScore = currentScore;
        }
        else if(currentScore > highScore){
            this.highScore = currentScore;
        }
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

        Label mainLabel = new Label("Visual Memory");
        mainLabel.setFont(Font.font("Arial", FontWeight.BOLD, 30));

        Label subLabel = new Label("Remember an increasingly large board of squares.");
        subLabel.setFont(Font.font("Arial", 12));
        subLabel.wrapTextProperty().set(true);

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