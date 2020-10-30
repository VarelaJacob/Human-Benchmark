package HumanBenchmark;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import sun.jvm.hotspot.debugger.cdbg.BaseClass;
import javafx.scene.paint.Color;


/**
 * 
 */
public class ReactionTest {
    
    String BACKGROUNDBLUE   = "-fx-background-color: #2b86d1";
    String BACKGROUNDRED    = "-fx-background-color: #ce2636";
    String BACKGROUNDGREEN  = "-fx-background-color: #4bdb6a";
    private int highScore;

    public VBox playGame(){

        int status = 0;
        
        ImageView iconView = new ImageView( new Image("file:resources/humanBenchmarkicon.png"));
        ImageView redDots = new ImageView( new Image("file:resources/dotsRed.png"));
        ImageView greenDots = new ImageView( new Image("file:resources/dotsGreen.png"));

        Label mainLabel = new Label("Reaction Time Test");
        mainLabel.setFont(Font.font("Arial", FontWeight.BOLD,70));
        mainLabel.setTextFill(Color.web("#FFFFFF"));

        Label subLabel1 = new Label("When the red box turns green, click as quickly as you can.");
        subLabel1.setFont(Font.font("Arial", 20));
        subLabel1.setTextFill(Color.web("#FFFFFF"));

        Label subLabel2 = new Label("Click anywhere to start.");
        subLabel2.setFont(Font.font("Arial", 20));
        subLabel2.setTextFill(Color.web("#FFFFFF"));

        Label scoreLabel = new Label("HighScore: " + highScore);
        scoreLabel.setFont(Font.font("Arial", FontPosture.ITALIC, 12));

        Label redLabel = new Label("Wait for green");
        redLabel.setFont(Font.font("Arial", FontWeight.BOLD, 50));
        redLabel.setTextFill(Color.web("#FFFFFF"));

        VBox vboxDefault = new VBox();
        vboxDefault.setPadding(new Insets(10, 10, 10, 10));
        vboxDefault.setStyle(BACKGROUNDBLUE);
        vboxDefault.setMinHeight(550);
        vboxDefault.setMaxHeight(550);
        vboxDefault.setSpacing(30);
        vboxDefault.getChildren().addAll(iconView,mainLabel,subLabel1,subLabel2, scoreLabel);
        vboxDefault.setAlignment(Pos.CENTER);

        vboxDefault.setOnMouseClicked( e -> {

            vboxDefault.getChildren().clear();
            vboxDefault.setStyle(BACKGROUNDRED);
            vboxDefault.getChildren().addAll(redDots, redLabel);
        });

        return vboxDefault;
    }

    public VBox createVBox(int newScore){

        this.highScore = newScore;

        ImageView iconView = new ImageView( new Image("file:resources/reactionIcon.png"));

        Label mainLabel = new Label("Reaction Time");
        mainLabel.setFont(Font.font("Arial", FontWeight.BOLD, 30));

        Label subLabel = new Label("Test your visual reflexes");
        subLabel.setFont(Font.font("Arial", 15));

        Label scoreLabel = new Label("HighScore: " + highScore);
        scoreLabel.setFont(Font.font("Arial", FontPosture.ITALIC, 12));

        VBox vbox = new VBox();
        vbox.setPrefSize(250, 250);
        vbox.setStyle("-fx-background-color: #FFFFFF");
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(20);

        vbox.getChildren().addAll(iconView, mainLabel, subLabel, scoreLabel);

        return vbox;
    }
}
