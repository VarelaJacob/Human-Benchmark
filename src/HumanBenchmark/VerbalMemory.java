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
 * 
 */
public class VerbalMemory {
    
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

    public VBox playGame() {

        populateWordBank();

        startGame();

        return vboxDefault;
    }

    private void startGame() {
        seenArray = new boolean[100];
        randIndex = rand.nextInt(100);
        Arrays.fill(seenArray, false);
        
        this.lives = 3;
        this.currentScore = 0;        
        
        mainLabel = new Label("Verbal Memory Test");
        mainLabel.setFont(Font.font("Arial", FontWeight.BOLD, 50));
        mainLabel.setTextFill(Color.web("#FFFFFF"));

        Label subLabel1 = new Label("You will be shown words, one at a time.");
        subLabel1.setFont(Font.font("Arial", 20));
        subLabel1.setTextFill(Color.web("#FFFFFF"));

        Label subLabel2 = new Label("If you've seen a word during the test, click SEEN");
        subLabel2.setFont(Font.font("Arial", 20));
        subLabel2.setTextFill(Color.web("#FFFFFF"));

        Label subLabel3 = new Label("If it's a new word, click NEW");
        subLabel3.setFont(Font.font("Arial", 20));
        subLabel3.setTextFill(Color.web("#FFFFFF"));

        Label scoreLabel = new Label("HighScore: " + String.valueOf(highScore) + " word(s)");
        scoreLabel.setFont(Font.font("Arial", FontPosture.ITALIC, 14));
        scoreLabel.setTextFill(Color.web("#FFFFFF")); 
        
        livesLabel = new Label("Lives | " + String.valueOf(lives));
        livesLabel.setFont(Font.font("Arial", 20));
        livesLabel.setTextFill(Color.web("#FFFFFF")); 

        currScoreLabel = new Label("Score | " + String.valueOf(currentScore));
        currScoreLabel.setFont(Font.font("Arial", 20));
        currScoreLabel.setTextFill(Color.web("#FFFFFF")); 

        wordLabel = new Label("Current word : " + wordBank.get(randIndex));
        wordLabel.setFont(Font.font("Arial", 40));
        wordLabel.setTextFill(Color.web("#FFFFFF")); 

        Button seenBtn = new Button("SEEN");
        seenBtn.setStyle(BACKGROUNDYELLOW);
        seenBtn.setPrefSize(145, 45);
        seenBtn.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        
        Button newBtn = new Button("NEW");
        newBtn.setStyle(BACKGROUNDYELLOW);
        newBtn.setPrefSize(145, 45);
        newBtn.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        startTestBtn = new Button("Start");
        startTestBtn.setStyle(BACKGROUNDYELLOW);
        startTestBtn.setPrefSize(145, 45);
        startTestBtn.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        vboxDefault = new VBox();
        vboxDefault.setPadding(new Insets(10, 10, 10, 10));
        vboxDefault.setStyle(BACKGROUNDBLUE);
        vboxDefault.setMinHeight(550);
        vboxDefault.setMaxHeight(550);
        vboxDefault.setSpacing(20);
        vboxDefault.getChildren().addAll(scoreLabel, iconView,mainLabel, subLabel1, subLabel2, startTestBtn);
        vboxDefault.setAlignment(Pos.CENTER);

        startTestBtn.setOnMouseClicked( ( e ) -> {

            this.lives = 3;
            this.currentScore = 0;
            seenArray = new boolean[100];
            randIndex = rand.nextInt(100);
            Arrays.fill(seenArray, false);

            statBox = new HBox();
            statBox.setAlignment(Pos.CENTER);
            livesLabel.setText("Lives | " + String.valueOf(lives));
            currScoreLabel.setText("Score | " + String.valueOf(currentScore));
            statBox.getChildren().addAll(livesLabel, currScoreLabel);
            statBox.setSpacing(30);

            gameBtnsBox = new HBox();
            gameBtnsBox.setAlignment(Pos.CENTER);
            gameBtnsBox.getChildren().addAll(seenBtn, newBtn);
            gameBtnsBox.setSpacing(30);

            vboxDefault.getChildren().clear();
            vboxDefault.getChildren().addAll(statBox, wordLabel, gameBtnsBox);
        });

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


    private boolean isRepeatWord(boolean[] seenArray, int randIndex) {
        
        if( seenArray[randIndex] == true ){
            return true;
        }        
        return false;
    }

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

    public int getHighScore() {
        return highScore;
    }

    public VBox createVBox() {

        this.highScore = 0;

        ImageView iconView = new ImageView( new Image("file:resources/verbalIcon.png"));

        Label mainLabel = new Label("Verbal Memory");
        mainLabel.setFont(Font.font("Arial", FontWeight.BOLD, 30));

        Label subLabel = new Label("Keep as many words in short term memory as possible.");
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