package HumanBenchmark;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
import java.lang.Math;

/**
 * This is the class for the Number Memory game. 
 * The objective of this game is to recall the most amount
 * of digits from an increasing number as possible.
 * 
 * @author Jacob Varela
 */
public class NumberMemory {
    
    // Global variables.
    String BACKGROUNDYELLOW = "-fx-background-color: #ffd154";
    String BACKGROUNDBLUE = "-fx-background-color: #2b86d1";
    private long magicNum, currentNum;
    private int highScore, currentScore;
    private Random rand = new Random();
    private Label scoreLabel, subLabel1, subLabel2, mainLabel;
    private Button startTestBtn;
    private VBox vboxDefault;

    /**
     * This method sets up the VBox that the user will see when they
     * choose to play this game from the main screen.
     * 
     * @return VBox that describes how to play this game.
     */
    public VBox playGame() {

        ImageView iconView = new ImageView(new Image("file:resources/numberIcon2.png"));
        
        // Main label
        mainLabel = new Label("Number Memory");
        mainLabel.setFont(Font.font("Arial", FontWeight.BOLD, 50));
        mainLabel.setTextFill(Color.web("#FFFFFF"));

        // Subtitle label #1
        subLabel1 = new Label("The average person can remember 7 numbers at once.");
        subLabel1.setFont(Font.font("Arial", 20));
        subLabel1.setTextFill(Color.web("#FFFFFF"));

        // Subtitle label #2
        subLabel2 = new Label("Can you do more?");
        subLabel2.setFont(Font.font("Arial", 20));
        subLabel2.setTextFill(Color.web("#FFFFFF"));

        // Showcases this game's current high score.
        scoreLabel = new Label("HighScore: Level " + String.valueOf(highScore));
        scoreLabel.setFont(Font.font("Arial", FontPosture.ITALIC, 14));
        scoreLabel.setTextFill(Color.web("#FFFFFF"));      

        // This button will start the game when clicked on.
        startTestBtn = new Button("Start");
        startTestBtn.setStyle("-fx-background-color: #ffd154");
        startTestBtn.setPrefSize(145, 45);
        startTestBtn.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        // Create and format vbox
        vboxDefault = new VBox();
        vboxDefault.setPadding(new Insets(10, 10, 10, 10));
        vboxDefault.setStyle(BACKGROUNDBLUE);
        vboxDefault.setMinHeight(550);
        vboxDefault.setMaxHeight(550);
        vboxDefault.setSpacing(20);
        vboxDefault.getChildren().addAll(scoreLabel,iconView, mainLabel, subLabel1, subLabel2, startTestBtn);
        vboxDefault.setAlignment(Pos.CENTER);

        // Begin playing the game when the start button is clicked.
        startTestBtn.setOnMouseClicked( ( e ) -> {
            this.currentScore = 1;
            magicNum = Math.abs(rand.nextLong());

            flashNumber();           

        });

        return vboxDefault;
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
        else {
            //do nothing.
        }
    }

    /**
     * This method will show the next number that must be remembered
     * by the user playing the game. It will show more digits of the
     * predefined magicNumber as the user gets into higher levels. The
     * number will appear on screen for 3,000 ms. (3 seconds).
     */
    private void flashNumber() {
        currentNum = magicNum;
        int sleepTime = 3000;

        for(int i=0; i < 19-currentScore; i++){
            currentNum = currentNum/10;
        }

        Label numLabel = new Label(String.valueOf(currentNum));
        numLabel.setFont(Font.font("Arial", FontWeight.BOLD, 70));
        numLabel.setTextFill(Color.web("#FFFFFF"));

        vboxDefault.getChildren().clear();
        vboxDefault.getChildren().addAll(numLabel);

        Timer gameTimer = new Timer();
                gameTimer.schedule(new TimerTask() {

                    @Override
                    public void run() {
                        Platform.runLater(() ->{
                            guessNumber();
                        });
                    }
                },sleepTime);
    }

    /**
     * This method will change the defaule VBox to 
     * allow for this game to accept the user's guess as to what
     * number that have remembered seeing. 
     */
    private void guessNumber() {
        subLabel1.setText("What was the Number?");
        
        TextField textBox = new TextField();
        textBox.setAlignment(Pos.CENTER);
        textBox.setFont(Font.font("Arial", 30));
        textBox.setMinHeight(30);
        textBox.setMaxWidth(200);

        Button submitBtn = new Button("Submit");
        submitBtn.setStyle(BACKGROUNDYELLOW);
        submitBtn.setPrefSize(145, 45);
        submitBtn.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        vboxDefault.getChildren().clear();
        vboxDefault.getChildren().addAll(subLabel1, textBox, submitBtn);

        /* Validate whether the user remembered the number correctly or not.
         * Continue the game if correct, display their results if otherwise.
         */
        submitBtn.setOnMouseClicked(e -> {
            int input = -1;
            try{
                input = Integer.parseInt(textBox.getText());
            } 
            catch (NumberFormatException nfe) {
                //do Nothing
            }

            if( input == currentNum){
                this.currentScore = currentScore + 1;
                completeLevel();
            }
            else{
                subLabel1.setText("Number : " + String.valueOf(currentNum));
                subLabel2.setText("Your Answer : " + String.valueOf(input));
                startTestBtn.setText("Try Again");

                vboxDefault.getChildren().clear();
                vboxDefault.getChildren().addAll(subLabel1, subLabel2, startTestBtn);
            }
        });
    }

    /**
     * If the user has successfully remembered the number on the screen then 
     * this method will display their guess and the number so far. It will
     * prompt them to click a button to continue to the next level.
     */
    private void completeLevel() {

        updateHighScore();
        subLabel1.setText("Number : " + String.valueOf(currentNum));
        subLabel2.setText("Your Answer : " + String.valueOf(currentNum));
        mainLabel.setText("Level " + String.valueOf(currentScore-1));

        Button nextBtn = new Button("NEXT");
        nextBtn.setStyle(BACKGROUNDYELLOW);
        nextBtn.setPrefSize(145, 45);
        nextBtn.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        vboxDefault.getChildren().clear();
        vboxDefault.getChildren().addAll(subLabel1, subLabel2, mainLabel, nextBtn);

        // Begin the next level when the nextButton is clicked.
        nextBtn.setOnMouseClicked(e -> {
            flashNumber();
        });
    }

    /**
     * @return return this game's highest score values so far.
     */
    public int getHighScore() {
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

        ImageView iconView = new ImageView( new Image("file:resources/numberIcon.png"));

        Label mainLabel = new Label("Number Memory");
        mainLabel.setFont(Font.font("Arial", FontWeight.BOLD, 30));

        Label subLabel = new Label("Remember the longest number you can");
        subLabel.setFont(Font.font("Arial", 12));

        VBox vbox = new VBox();
        vbox.setPrefSize(400, 400);
        vbox.setStyle("-fx-background-color: #FFFFFF");
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(20);

        vbox.getChildren().addAll(iconView, mainLabel, subLabel);

        return vbox;
    }
}