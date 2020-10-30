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
 * 
 */
public class ReactionTest {

    String BACKGROUNDBLUE = "-fx-background-color: #2b86d1";
    String BACKGROUNDRED = "-fx-background-color: #ce2636";
    String BACKGROUNDGREEN = "-fx-background-color: #4bdb6a";
    private int sleepTime;
    private boolean gameInProgress;
    private long highScore, startTime, scoreTime, endTime, elapsedTime;

    public VBox playGame() {

        gameInProgress = false;

        ImageView iconView = new ImageView(new Image("file:resources/humanBenchmarkicon.png"));
        ImageView redDots = new ImageView(new Image("file:resources/dotsRed.png"));
        ImageView greenDots = new ImageView(new Image("file:resources/dotsGreen.png"));
        ImageView clockIcon = new ImageView(new Image("file:resources/clockIcon.png"));
        ImageView exclamationIcon = new ImageView(new Image("file:resources/exclamationIcon.png"));

        Label mainLabel = new Label("Reaction Time Test");
        mainLabel.setFont(Font.font("Arial", FontWeight.BOLD, 70));
        mainLabel.setTextFill(Color.web("#FFFFFF"));

        Label subLabel1 = new Label("When the red box turns green, click as quickly as you can.");
        subLabel1.setFont(Font.font("Arial", 20));
        subLabel1.setTextFill(Color.web("#FFFFFF"));

        Label subLabel2 = new Label("Click anywhere to start.");
        subLabel2.setFont(Font.font("Arial", 20));
        subLabel2.setTextFill(Color.web("#FFFFFF"));

        Label scoreLabel = new Label("HighScore: " + String.valueOf(highScore));
        scoreLabel.setFont(Font.font("Arial", FontPosture.ITALIC, 14));
        scoreLabel.setTextFill(Color.web("#FFFFFF"));

        Label redLabel = new Label("Wait for green");
        redLabel.setFont(Font.font("Arial", FontWeight.BOLD, 50));
        redLabel.setTextFill(Color.web("#FFFFFF"));

        Label greenLabel = new Label("Click!");
        redLabel.setFont(Font.font("Arial", FontWeight.BOLD, 50));
        redLabel.setTextFill(Color.web("#FFFFFF"));

        Label timeLabel = new Label(String.valueOf(elapsedTime) + " ms");
        timeLabel.setFont(Font.font("Arial", FontWeight.BOLD, 50));
        timeLabel.setTextFill(Color.web("#FFFFFF"));

        Label timeSubLabel = new Label("Click to keep going");
        timeSubLabel.setFont(Font.font("Arial", 20));
        timeSubLabel.setTextFill(Color.web("#FFFFFF"));

        Label fastLabel = new Label("Too soon!");
        fastLabel.setFont(Font.font("Arial", FontWeight.BOLD, 50));
        fastLabel.setTextFill(Color.web("#FFFFFF"));

        Label fastSubLabel = new Label("Click to try again.");
        fastSubLabel.setFont(Font.font("Arial", 20));
        fastSubLabel.setTextFill(Color.web("#FFFFFF"));

        VBox vboxDefault = new VBox();
        vboxDefault.setPadding(new Insets(10, 10, 10, 10));
        vboxDefault.setStyle(BACKGROUNDBLUE);
        vboxDefault.setMinHeight(550);
        vboxDefault.setMaxHeight(550);
        vboxDefault.setSpacing(30);
        vboxDefault.getChildren().addAll(scoreLabel, iconView, mainLabel, subLabel1, subLabel2);
        vboxDefault.setAlignment(Pos.CENTER);

        vboxDefault.setOnMouseClicked(e -> {

            if (gameInProgress) {

                endTime = System.nanoTime();
                elapsedTime = endTime - startTime;
                scoreTime = elapsedTime/1000000 - sleepTime;

                if (scoreTime > 0) {
                    
                    updateScore();
                    vboxDefault.getChildren().clear();
                    vboxDefault.setStyle(BACKGROUNDBLUE);
                    timeLabel.setText(String.valueOf(scoreTime) + " ms");
                    vboxDefault.getChildren().addAll(clockIcon, timeLabel, timeSubLabel);
                    gameInProgress = false;
                } else {
                    vboxDefault.getChildren().clear();
                    vboxDefault.setStyle(BACKGROUNDBLUE);
                    vboxDefault.getChildren().addAll(exclamationIcon, fastLabel, fastSubLabel);
                    gameInProgress = false;
                }
            } else {
                Random rand = new Random();
                sleepTime = rand.nextInt(5000)*2;
                vboxDefault.getChildren().clear();
                vboxDefault.setStyle(BACKGROUNDRED);
                vboxDefault.getChildren().addAll(redDots, redLabel);
                gameInProgress = true;
                
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
    }

    public long getHighScore(){
        return highScore;
    }

    public VBox createVBox() {

        this.highScore = 0;

        ImageView iconView = new ImageView( new Image("file:resources/reactionIcon.png"));

        Label mainLabel = new Label("Reaction Time");
        mainLabel.setFont(Font.font("Arial", FontWeight.BOLD, 30));

        Label subLabel = new Label("Test your visual reflexes");
        subLabel.setFont(Font.font("Arial", 15));

        VBox vbox = new VBox();
        vbox.setPrefSize(250, 250);
        vbox.setStyle("-fx-background-color: #FFFFFF");
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(20);

        vbox.getChildren().addAll(iconView, mainLabel, subLabel);

        return vbox;
    }
}
