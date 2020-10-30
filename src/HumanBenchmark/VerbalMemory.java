package HumanBenchmark;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.paint.Color;

/**
 * 
 */
public class VerbalMemory {
    
    String BACKGROUNDBLUE = "-fx-background-color: #2b86d1";
    private int highScore;
    private boolean gameInProgress;

    public VBox playGame() {

        ImageView iconView = new ImageView(new Image("file:resources/verbalIcon2.png"));


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

        Button startTestBtn = new Button("Start");
        startTestBtn.setStyle("-fx-background-color: #ffd154");
        startTestBtn.setPrefSize(145, 45);
        startTestBtn.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        startTestBtn.setOnMouseClicked( ( e ) -> {
            /* play*/
        });

        VBox vboxDefault = new VBox();
        vboxDefault.setPadding(new Insets(10, 10, 10, 10));
        vboxDefault.setStyle(BACKGROUNDBLUE);
        vboxDefault.setMinHeight(550);
        vboxDefault.setMaxHeight(550);
        vboxDefault.setSpacing(20);
        vboxDefault.getChildren().addAll(scoreLabel, iconView,mainLabel, subLabel1, subLabel2, startTestBtn);
        vboxDefault.setAlignment(Pos.CENTER);

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

    public int getHighScore(){
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