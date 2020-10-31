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
 * 
 */
public class NumberMemory {
    
    String BACKGROUNDYELLOW = "-fx-background-color: #ffd154";
    String BACKGROUNDBLUE = "-fx-background-color: #2b86d1";
    private int highScore, currentScore;
    private Random rand = new Random();
    private long magicNum, currentNum;
    private Label scoreLabel, subLabel1, subLabel2, mainLabel;
    private Button startTestBtn;
    private VBox vboxDefault;

    public VBox playGame() {

        ImageView iconView = new ImageView(new Image("file:resources/numberIcon2.png"));
        
        mainLabel = new Label("Number Memory");
        mainLabel.setFont(Font.font("Arial", FontWeight.BOLD, 50));
        mainLabel.setTextFill(Color.web("#FFFFFF"));

        subLabel1 = new Label("The average person can remember 7 numbers at once.");
        subLabel1.setFont(Font.font("Arial", 20));
        subLabel1.setTextFill(Color.web("#FFFFFF"));

        subLabel2 = new Label("Can you do more?");
        subLabel2.setFont(Font.font("Arial", 20));
        subLabel2.setTextFill(Color.web("#FFFFFF"));

        scoreLabel = new Label("HighScore: Level " + String.valueOf(highScore));
        scoreLabel.setFont(Font.font("Arial", FontPosture.ITALIC, 14));
        scoreLabel.setTextFill(Color.web("#FFFFFF"));      

        startTestBtn = new Button("Start");
        startTestBtn.setStyle("-fx-background-color: #ffd154");
        startTestBtn.setPrefSize(145, 45);
        startTestBtn.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        vboxDefault = new VBox();
        vboxDefault.setPadding(new Insets(10, 10, 10, 10));
        vboxDefault.setStyle(BACKGROUNDBLUE);
        vboxDefault.setMinHeight(550);
        vboxDefault.setMaxHeight(550);
        vboxDefault.setSpacing(20);
        vboxDefault.getChildren().addAll(scoreLabel,iconView, mainLabel, subLabel1, subLabel2, startTestBtn);
        vboxDefault.setAlignment(Pos.CENTER);

        startTestBtn.setOnMouseClicked( ( e ) -> {
            this.currentScore = 1;
            magicNum = Math.abs(rand.nextLong());

            flashNumber();           

        });

        return vboxDefault;
    }

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
        nextBtn.setOnMouseClicked(e -> {
            flashNumber();
        });
    }

    public int getHighScore() {
        return highScore;
    }

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