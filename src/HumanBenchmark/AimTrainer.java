package HumanBenchmark;

import java.util.Random;

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

/**
 * This is the class for the aim trainer game. 
 * The objective of this game is to click on the 30 target icons that appear
 * on the screen in the smallest amount of time possible.
 * 
 * @author Jacob Varela
 */
public class AimTrainer {
    
    // Global variables.
    String BACKGROUNDBLUE = "-fx-background-color: #2b86d1";
    private long highScore, startTime, scoreTime, endTime, elapsedTime;
    private int targetsRemaining;
    private boolean gameInProgress;

    /**
     * This method sets up the VBox that the user will see when they
     * choose to play this game from the main screen.
     * 
     * @return VBox that describes how to play this game.
     */
    public VBox playGame() {

        // Initialize gameState
        gameInProgress = false;
        targetsRemaining = 10;

        // Target Icon to be used while playing this game.
        ImageView targetIcon = new ImageView(new Image("file:resources/targetIcon.png"));

        Label mainLabel = new Label("Aim Trainer");
        mainLabel.setFont(Font.font("Arial", FontWeight.BOLD, 70));
        mainLabel.setTextFill(Color.web("#FFFFFF"));

        Label subLabel1 = new Label("Hit 30 targets as quickly as you can.");
        subLabel1.setFont(Font.font("Arial", 20));
        subLabel1.setTextFill(Color.web("#FFFFFF"));

        Label subLabel2 = new Label("Click the target above to begin.");
        subLabel2.setFont(Font.font("Arial", 20));
        subLabel2.setTextFill(Color.web("#FFFFFF"));

        Label scoreLabel = new Label("HighScore: " + String.valueOf(highScore)+ " ms");
        scoreLabel.setFont(Font.font("Arial", FontPosture.ITALIC, 14));
        scoreLabel.setTextFill(Color.web("#FFFFFF"));

        Label countLabel = new Label("10");
        countLabel.setFont(Font.font("Arial", 30));
        countLabel.setTextFill(Color.web("#FFFFFF"));

        // Create and format vbox
        VBox vboxDefault = new VBox();
        vboxDefault.setPadding(new Insets(10, 10, 10, 10));
        vboxDefault.setStyle(BACKGROUNDBLUE);
        vboxDefault.setMinHeight(550);
        vboxDefault.setMaxHeight(550);
        vboxDefault.setSpacing(30);
        vboxDefault.getChildren().addAll(scoreLabel, mainLabel, targetIcon, subLabel1, subLabel2);
        vboxDefault.setAlignment(Pos.CENTER);

        /* TBD
         * 
         */
        targetIcon.setOnMouseClicked(e -> {

            if(gameInProgress){

            }
            else{
                Random rand = new Random();
                vboxDefault.getChildren().clear();
                mainLabel.setText("Remaining");
                mainLabel.setFont(Font.font("Arial", 20));


                vboxDefault.getChildren().addAll(mainLabel, countLabel);

            }
        });

        return vboxDefault;
    }

    /**
     * @return return this game's highest score values so far.
     */
    public long getHighScore(){
        return highScore;
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
     * This method creates a Vbox to be used on the bottom of the main screen.
     * This VBox will contain an icon representing the game, a label identifying
     * which game it is, as well as a quick description. A user can click on this
     * Vbox on the main screen to launch this game.
     * 
     * @return This game's vbox to be used on the homescreen.
     */
    public VBox createVBox() {

        this.highScore = 0;

        ImageView iconView = new ImageView( new Image("file:resources/aimIcon.png"));

        Label mainLabel = new Label("Aim Trainer");
        mainLabel.setFont(Font.font("Arial", FontWeight.BOLD, 30));

        Label subLabel = new Label("How quickly can you hit all the targets?");
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