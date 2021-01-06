package HumanBenchmark;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.paint.Color;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * This is the class for the Reaction Test.
 * The objective of this game is to click on the screen 
 * once it turns green as fast as possible. The lower the score
 * the better the reaction time. 
 * 
 * @author Jacob Varela
 */
public class ReactionTest {

    // Global variables.
    String BACKGROUNDBLUE = "-fx-background-color: #2b86d1";
    String BACKGROUNDRED = "-fx-background-color: #ce2636";
    String BACKGROUNDGREEN = "-fx-background-color: #4bdb6a";
    private long highScore, startTime, scoreTime, endTime, elapsedTime;
    private int sleepTime;
    private boolean gameInProgress;
    
    /**
     * This method sets up the VBox that the user will see when they
     * choose to play this game from the main screen.
     * 
     * @return VBox that describes how to play this game.
     */
    public VBox playGame() {

        // initialize gameState.
        gameInProgress = false;

        // Various icons used during the animation.
        ImageView iconView = new ImageView(new Image("file:resources/humanBenchmarkicon.png"));
        ImageView redDots = new ImageView(new Image("file:resources/dotsRed.png"));
        ImageView greenDots = new ImageView(new Image("file:resources/dotsGreen.png"));
        ImageView clockIcon = new ImageView(new Image("file:resources/clockIcon.png"));
        ImageView exclamationIcon = new ImageView(new Image("file:resources/exclamationIcon.png"));

        // Main label
        Label mainLabel = new Label("Reaction Time Test");
        mainLabel.setFont(Font.font("Arial", FontWeight.BOLD, 70));
        mainLabel.setTextFill(Color.web("#FFFFFF"));

        // Subtitle label #1
        Label subLabel1 = new Label("When the red box turns green, click as quickly as you can.");
        subLabel1.setFont(Font.font("Arial", 20));
        subLabel1.setTextFill(Color.web("#FFFFFF"));

        // Subtitle label #2
        Label subLabel2 = new Label("Click anywhere to start.");
        subLabel2.setFont(Font.font("Arial", 20));
        subLabel2.setTextFill(Color.web("#FFFFFF"));

        // Showcases this game's current high score.
        Label scoreLabel = new Label("HighScore: " + String.valueOf(highScore) + " ms");
        scoreLabel.setFont(Font.font("Arial", FontPosture.ITALIC, 14));
        scoreLabel.setTextFill(Color.web("#FFFFFF"));

        // Tells the user to wait.
        Label redLabel = new Label("Wait for green");
        redLabel.setFont(Font.font("Arial", FontWeight.BOLD, 50));
        redLabel.setTextFill(Color.web("#FFFFFF"));

        // Tells the user to click the screen.
        Label greenLabel = new Label("Click!");
        redLabel.setFont(Font.font("Arial", FontWeight.BOLD, 50));
        redLabel.setTextFill(Color.web("#FFFFFF"));

        // Tells the user their reaction time in ms.
        Label timeLabel = new Label(String.valueOf(elapsedTime) + " ms");
        timeLabel.setFont(Font.font("Arial", FontWeight.BOLD, 50));
        timeLabel.setTextFill(Color.web("#FFFFFF"));

        // Label appears after a successful reaction.
        Label timeSubLabel = new Label("Click to keep going");
        timeSubLabel.setFont(Font.font("Arial", 20));
        timeSubLabel.setTextFill(Color.web("#FFFFFF"));

        // Label appears after an unsuccessful reaction.
        Label fastLabel = new Label("Too soon!");
        fastLabel.setFont(Font.font("Arial", FontWeight.BOLD, 50));
        fastLabel.setTextFill(Color.web("#FFFFFF"));

        // Label appears after an unsuccessful reaction.
        Label fastSubLabel = new Label("Click to try again.");
        fastSubLabel.setFont(Font.font("Arial", 20));
        fastSubLabel.setTextFill(Color.web("#FFFFFF"));

        /* This Vbox is where the game is played.
         * It will change colors depending on the current game state.
         */
        VBox vboxDefault = new VBox();
        vboxDefault.setPadding(new Insets(10, 10, 10, 10));
        vboxDefault.setStyle(BACKGROUNDBLUE);
        vboxDefault.setMinHeight(550);
        vboxDefault.setMaxHeight(550);
        vboxDefault.setSpacing(30);
        vboxDefault.getChildren().addAll(scoreLabel, iconView, mainLabel, subLabel1, subLabel2);
        vboxDefault.setAlignment(Pos.CENTER);

        /* If the screen is clicked and a game is not in progress, then begin
         * a timer and show a red screen for a random amount of time. Once the
         * screen turns green identify how long the user took to react.
         *
         */
        vboxDefault.setOnMouseClicked(e -> {

            /* If the game is in progress then the user has already
             * initiated the guessing process. This will process if the
             * users guess was before or after the visual change on screen.
             */
            if (gameInProgress) {

                // Determine the users reaction time. 
                endTime = System.nanoTime();
                elapsedTime = endTime - startTime;
                scoreTime = elapsedTime/1000000 - sleepTime;

                // If the user reacted after the visual change.
                if (scoreTime > 0) {
                    
                    updateScore();
                    vboxDefault.getChildren().clear();
                    vboxDefault.setStyle(BACKGROUNDBLUE);
                    timeLabel.setText(String.valueOf(scoreTime) + " ms");
                    vboxDefault.getChildren().addAll(clockIcon, timeLabel, timeSubLabel);
                    gameInProgress = false;
                } else { // Else the user reacted preemptively.
                    vboxDefault.getChildren().clear();
                    vboxDefault.setStyle(BACKGROUNDBLUE);
                    vboxDefault.getChildren().addAll(exclamationIcon, fastLabel, fastSubLabel);
                    gameInProgress = false;
                }
            } else { // The game hasn't started yet. Begin the guessing process.
                Random rand = new Random();
                sleepTime = rand.nextInt(5000)*2;
                vboxDefault.getChildren().clear();
                vboxDefault.setStyle(BACKGROUNDRED);
                vboxDefault.getChildren().addAll(redDots, redLabel);
                gameInProgress = true;
                
                /* Begin the timer used to determine the user's reaction time.
                 * Also start the timer to change the VBox after a 
                 * random amount of time (less than 10 seconds).
                 */
                startTime = System.nanoTime();
                Timer gameTimer = new Timer();
                gameTimer.schedule(new TimerTask() {

                    @Override
                    public void run() {
                        Platform.runLater(() ->{
                        if(gameInProgress){
                            vboxDefault.getChildren().clear();
                            vboxDefault.setStyle(BACKGROUNDGREEN);
                            vboxDefault.getChildren().addAll(greenDots, greenLabel);
                            
                        }
                        else{
                            gameTimer.cancel();
                        }});
                    }
                },sleepTime);
            }
        });

        return vboxDefault;
    }

    /**
     * This method will look at the current global variables
     * in the game being played and will update this game's 
     * high score value if a lower reaction time has been achieved.
     */
    private void updateScore() {
        if(highScore == 0 ){
            this.highScore = scoreTime;
        }
        else if(scoreTime < highScore){
            this.highScore = scoreTime;
        }
    }

    /**
     * @return return this game's highest score values so far.
     */
    public long getHighScore(){
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

        // Initialize highScore.
        this.highScore = 0;

        // Lightning bolt icon.
        ImageView iconView = new ImageView( new Image("file:resources/reactionIcon.png"));

        // mainLabel describes the type of game.
        Label mainLabel = new Label("Reaction Time");
        mainLabel.setFont(Font.font("Arial", FontWeight.BOLD, 30));

        // Description of the game.
        Label subLabel = new Label("Test your visual reflexes");
        subLabel.setFont(Font.font("Arial", 12));

        // This vbox will appear on the GUI at all times. 
        VBox vbox = new VBox();
        vbox.setMinSize(250, 250);
        vbox.setMaxSize(250, 250);
        vbox.setStyle("-fx-background-color: #FFFFFF");
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(20);

        // Add appropriate icons and labels to the vbox.
        vbox.getChildren().addAll(iconView, mainLabel, subLabel);

        return vbox;
    }
}
