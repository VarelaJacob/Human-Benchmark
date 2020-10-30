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
    private int highScore;
    private boolean gameInProgress;
    private ArrayList<String> wordBank = new ArrayList<>();

    public VBox playGame() {

        //File wordFile = new File("file:resources/wordBank.txt");
        ImageView iconView = new ImageView(new Image("file:resources/verbalIcon2.png"));

        populateWordBank();

        String currWord;
        int lives, currentScore, randIndex;
        boolean[] seenArray = new boolean[100];

        Random rand = new Random();
        randIndex = rand.nextInt(100);

        currWord = wordBank.get(randIndex);
        seenArray[randIndex] = true;

        lives = 3;
        currentScore = 0;        
        gameInProgress = false;
        Arrays.fill(seenArray, false);



        Label mainLabel = new Label("Verbal Memory Test");
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
        
        Label livesLabel = new Label("Lives | " + String.valueOf(lives));
        livesLabel.setFont(Font.font("Arial", 20));
        livesLabel.setTextFill(Color.web("#FFFFFF")); 

        Label currScoreLabel = new Label("Score | " + String.valueOf(currentScore));
        currScoreLabel.setFont(Font.font("Arial", 20));
        currScoreLabel.setTextFill(Color.web("#FFFFFF")); 

        Label wordLabel = new Label(currWord);
        wordLabel.setFont(Font.font("Arial", 40));
        wordLabel.setTextFill(Color.web("#FFFFFF")); 

        Button seenBtn = new Button("SEEN");
        seenBtn.setStyle(BACKGROUNDYELLOW);
        seenBtn.setPrefSize(145, 45);
        seenBtn.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        seenBtn.setOnMouseClicked( ( e ) -> {
            /* play*/
        });

        Button newBtn = new Button("NEW");
        newBtn.setStyle(BACKGROUNDYELLOW);
        newBtn.setPrefSize(145, 45);
        newBtn.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        newBtn.setOnMouseClicked( ( e ) -> {
            /* play*/
        });

        Button startTestBtn = new Button("Start");
        startTestBtn.setStyle(BACKGROUNDYELLOW);
        startTestBtn.setPrefSize(145, 45);
        startTestBtn.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        VBox vboxDefault = new VBox();
        vboxDefault.setPadding(new Insets(10, 10, 10, 10));
        vboxDefault.setStyle(BACKGROUNDBLUE);
        vboxDefault.setMinHeight(550);
        vboxDefault.setMaxHeight(550);
        vboxDefault.setSpacing(20);
        vboxDefault.getChildren().addAll(scoreLabel, iconView,mainLabel, subLabel1, subLabel2, startTestBtn);
        vboxDefault.setAlignment(Pos.CENTER);

        startTestBtn.setOnMouseClicked( ( e ) -> {

            gameInProgress = true;

            HBox statBox = new HBox();
            statBox.setAlignment(Pos.CENTER);
            statBox.getChildren().addAll(livesLabel, currScoreLabel);
            statBox.setSpacing(30);

            HBox gameBtnsBox = new HBox();
            gameBtnsBox.setAlignment(Pos.CENTER);
            gameBtnsBox.getChildren().addAll(seenBtn, newBtn);
            gameBtnsBox.setSpacing(30);

            vboxDefault.getChildren().clear();
            vboxDefault.getChildren().addAll(statBox, gameBtnsBox);
        });

        return vboxDefault;
    }

    /*
    private void updateScore() {
        if(highScore == 0 ){
            this.highScore = scoreTime;
        }
        else if(elapsedTime < highScore){
            this.highScore = scoreTime;
        }
        else {
            //do nothing.
        }
    }*/

    public ArrayList<String> populateWordBank() {
        
        String fileName = "resources/workBank.txt";

        BufferedReader reader = null;
        try{ 
            reader = new BufferedReader(
                new FileReader(fileName, StandardCharsets.UTF_8));

            var sb = new StringBuilder();

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