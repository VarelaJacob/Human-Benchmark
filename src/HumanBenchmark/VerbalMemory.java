package HumanBenchmark;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.paint.Color;

/**
 * This is the class for the Verbal Memory Test.
 * The objective of this game is to identify if the word being shown on
 * the screen has been displayed before. The user will then click a button to
 * indicate their decision. This game gives the user 3 lives, meaning they 
 * can incorrectly guess 2 times before the game ends.
 * 
 * @author Jacob Varela
 */
public class VerbalMemory {
    
    // Global variables.
    String BACKGROUNDBLUE   = "-fx-background-color: #2b86d1";
    String BACKGROUNDYELLOW = "-fx-background-color: #ffd154";
    private int highScore, lives, randIndex, currentScore;
    private ArrayList<String> wordBank = new ArrayList<>();
    private HBox statBox, gameBtnsBox;
    private VBox vboxDefault;
    private Label wordLabel, livesLabel, currScoreLabel, mainLabel;
    private Random rand = new Random();
    private ImageView iconView = new ImageView(new Image("file:resources/verbalIcon2.png"));
    private Button startTestBtn;
    private boolean[] seenArray;

    /**
     * This method sets up the VBox that the user will see when they
     * choose to play this game from the main screen.
     * It will read in the words in play from a package file.
     * 
     * @return VBox that describes how to play this game.
     */
    public VBox playGame() {

        populateWordBank();

        startGame();

        return vboxDefault;
    }

    /**
     * This method sets up the initial layout of the game VBox.
     * Once the start button is clicked the user will be shown a 
     * word randomly chosen from the wordbank and they must guess if this
     * word has been shown before or if this is the first time seeing it.
     */
    private void startGame() {

        // Initialize seenArray to false, indicating we haven't seen any words yet.
        seenArray = new boolean[100];
        randIndex = rand.nextInt(100);
        Arrays.fill(seenArray, false);
        
        // Initialize game constraint variables.
        this.lives = 3;
        this.currentScore = 0;        
        
        // Main label
        mainLabel = new Label("Verbal Memory Test");
        mainLabel.setFont(Font.font("Arial", FontWeight.BOLD, 50));
        mainLabel.setTextFill(Color.web("#FFFFFF"));

        // Subtitle label #1
        Label subLabel1 = new Label("You will be shown words, one at a time.");
        subLabel1.setFont(Font.font("Arial", 20));
        subLabel1.setTextFill(Color.web("#FFFFFF"));

        // Subtitle label #2
        Label subLabel2 = new Label("If you've seen a word during the test, click SEEN");
        subLabel2.setFont(Font.font("Arial", 20));
        subLabel2.setTextFill(Color.web("#FFFFFF"));

        // Subtitle label #3
        Label subLabel3 = new Label("If it's a new word, click NEW");
        subLabel3.setFont(Font.font("Arial", 20));
        subLabel3.setTextFill(Color.web("#FFFFFF"));

        // Showcases this game's current high score.
        Label scoreLabel = new Label("HighScore: " + String.valueOf(highScore) + " word(s)");
        scoreLabel.setFont(Font.font("Arial", FontPosture.ITALIC, 14));
        scoreLabel.setTextFill(Color.web("#FFFFFF")); 
        // Showcases this game's current lives remaining.
        livesLabel = new Label("Lives | " + String.valueOf(lives));
        livesLabel.setFont(Font.font("Arial", 20));
        livesLabel.setTextFill(Color.web("#FFFFFF")); 

        // Showcases this game's current score.
        currScoreLabel = new Label("Score | " + String.valueOf(currentScore));
        currScoreLabel.setFont(Font.font("Arial", 20));
        currScoreLabel.setTextFill(Color.web("#FFFFFF")); 

        // Showcases a random word chosen from the wordBank.
        wordLabel = new Label("Current word : " + wordBank.get(randIndex));
        wordLabel.setFont(Font.font("Arial", 40));
        wordLabel.setTextFill(Color.web("#FFFFFF")); 

        // Button to be clicked on if the displayed word has been seen before.
        Button seenBtn = new Button("SEEN");
        seenBtn.setStyle(BACKGROUNDYELLOW);
        seenBtn.setPrefSize(145, 45);
        seenBtn.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        
        // Button to be clicked on if the displayed word is new. 
        Button newBtn = new Button("NEW");
        newBtn.setStyle(BACKGROUNDYELLOW);
        newBtn.setPrefSize(145, 45);
        newBtn.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        // Button to start the game. 
        startTestBtn = new Button("Start");
        startTestBtn.setStyle(BACKGROUNDYELLOW);
        startTestBtn.setPrefSize(145, 45);
        startTestBtn.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        /* This vbox will showcase the elements on the start screen, the
         * post-game screen, as well as the in-game screen.
         */
        vboxDefault = new VBox();
        vboxDefault.setPadding(new Insets(10, 10, 10, 10));
        vboxDefault.setStyle(BACKGROUNDBLUE);
        vboxDefault.setMinHeight(550);
        vboxDefault.setMaxHeight(550);
        vboxDefault.setSpacing(20);
        vboxDefault.getChildren().addAll(scoreLabel, iconView,mainLabel, subLabel1, subLabel2, startTestBtn);
        vboxDefault.setAlignment(Pos.CENTER);

        // Begins the game if the start button is clicked. 
        startTestBtn.setOnMouseClicked( ( e ) -> {

            // Initialize game variables.
            this.lives = 3;
            this.currentScore = 0;
            seenArray = new boolean[100];
            randIndex = rand.nextInt(100);
            Arrays.fill(seenArray, false);

            // Initialize game labels and on-screen text.
            statBox = new HBox();
            statBox.setAlignment(Pos.CENTER);
            livesLabel.setText("Lives | " + String.valueOf(lives));
            currScoreLabel.setText("Score | " + String.valueOf(currentScore));
            statBox.getChildren().addAll(livesLabel, currScoreLabel);
            statBox.setSpacing(30);

            // Create an HBox to store the game buttons.
            gameBtnsBox = new HBox();
            gameBtnsBox.setAlignment(Pos.CENTER);
            gameBtnsBox.getChildren().addAll(seenBtn, newBtn);
            gameBtnsBox.setSpacing(30);

            // Update vbox to show in-game elements. 
            vboxDefault.getChildren().clear();
            vboxDefault.getChildren().addAll(statBox, wordLabel, gameBtnsBox);
        });

        /* Validates if the word on screen has been shown before or not. 
         * Update this game's current score and number of lives accordingly.
         */
        newBtn.setOnMouseClicked( ( e ) -> {
            if( !isRepeatWord(seenArray, randIndex) ) {
                seenArray[randIndex] = true;                
                this.currentScore = (currentScore +1);
                update(currentScore, lives);
            }
            else{
                this.lives = (lives - 1);
                update(currentScore, lives);
            }
        });

        /* Validates if the word on screen has been shown before or not. 
         * Update this game's current score and number of lives accordingly.
         */
        seenBtn.setOnMouseClicked( ( e ) -> {
            if( isRepeatWord(seenArray, randIndex) ) {     
                this.currentScore = (currentScore +1);
                update(currentScore, lives);
            }
            else{
                this.lives = (lives - 1);
                update(currentScore, lives);
            }
        });
    }

    
    /**
     * If the user has run out of their remaining lives then display their
     * final score and give them the option to try again.
     * Otherwise update their score and continue playing the game. 
     * 
     * @param score Updates the current score value.
     * @param lives Updates the number of lives remaining.
     */
    private void update(int score, int lives) {

        if(highScore == 0 ){
            this.highScore = score; 
        }
        else if(score > highScore){
            this.highScore = score;
        }

        if( lives == 0){
            vboxDefault.getChildren().clear();
            mainLabel.setText("Verbal Memory score:");
            currScoreLabel.setText(String.valueOf(currentScore) + " word(s)");
            startTestBtn.setText("Try Again");
            
            vboxDefault.getChildren().addAll(iconView, mainLabel, currScoreLabel, startTestBtn);
        }
        else {
            this.currentScore = score;
            
            this.lives = lives;
            randIndex = rand.nextInt(100);
            wordLabel.setText("Current word : " + wordBank.get(randIndex));
            livesLabel.setText("Lives | " + String.valueOf(lives));
            currScoreLabel.setText("Score | " + String.valueOf(currentScore));
            statBox.getChildren().clear();
            statBox.getChildren().addAll(livesLabel, currScoreLabel);
            vboxDefault.getChildren().clear();
            vboxDefault.getChildren().addAll(statBox, wordLabel, gameBtnsBox);
        }
    }


    /**
     * Checks the seenArray to determine if a word has been shown previously.
     * 
     * @param seenArray boolean Array of previously shown words.
     * @param randIndex Index of the current word being validated.
     * @return True if the word has been shown previously, false otherwise.
     */
    private boolean isRepeatWord(boolean[] seenArray, int randIndex) {
        
        if( seenArray[randIndex] == true ){
            return true;
        }        
        return false;
    }

    /**
     * This method reads in words from a text file in the same package 
     * and stores each word in an Arraylist for easy accessing.
     * 
     * @return populated wordBanke of 100 unique words (Strings).
     */
    public ArrayList<String> populateWordBank() {
        
        String fileName = "resources/workBank.txt";

        BufferedReader reader = null;
        try{ 
            reader = new BufferedReader(
                new FileReader(fileName, StandardCharsets.UTF_8));

            String nextWord;
            while ((nextWord = reader.readLine()) != null) {
                wordBank.add(nextWord);
            }
        
        } catch (IOException e){
            e.printStackTrace();
        }

        return wordBank;
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

        ImageView iconView = new ImageView( new Image("file:resources/verbalIcon.png"));

        Label mainLabel = new Label("Verbal Memory");
        mainLabel.setFont(Font.font("Arial", FontWeight.BOLD, 25));

        Label subLabel = new Label("Keep as many words in short term memory as possible.");
        subLabel.setFont(Font.font("Arial", 12));
        subLabel.wrapTextProperty().set(true);

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