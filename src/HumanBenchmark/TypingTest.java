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
import javafx.scene.paint.Color;

/**
 * This is the class for the Typing Test game. 
 * The objective of this game is accurately type the prompt given on screen
 * and to match the prompt in the shortest amount of time to determine how 
 * many words per minute the user can type.
 * 
 * @author Jacob Varela
 */
public class TypingTest {
    
    // Global variables.
    String BACKGROUNDBLUE = "-fx-background-color: #2b86d1";
    private int highScore;

    /**
     * This method sets up the VBox that the user will see when they
     * choose to play this game from the main screen.
     * 
     * @return VBox that describes how to play this game.
     */
    public VBox playGame() {

        // Title label.
        Label mainLabel = new Label("Typing Test");
        mainLabel.setFont(Font.font("Arial", FontWeight.BOLD, 50));
        mainLabel.setTextFill(Color.web("#FFFFFF"));

        // Subtitle label.
        Label subLabel1 = new Label("How many words per minute");
        subLabel1.setFont(Font.font("Arial", 20));
        subLabel1.setTextFill(Color.web("#FFFFFF"));

        // High Score label.
        Label scoreLabel = new Label("HighScore: " + String.valueOf(highScore) + " wpm");
        scoreLabel.setFont(Font.font("Arial", FontPosture.ITALIC, 14));
        scoreLabel.setTextFill(Color.web("#FFFFFF"));      

        // Main vbox to store the labels.
        VBox vboxDefault = new VBox();
        vboxDefault.setPadding(new Insets(10, 10, 10, 10));
        vboxDefault.setStyle(BACKGROUNDBLUE);
        vboxDefault.setMinHeight(550);
        vboxDefault.setMaxHeight(550);
        vboxDefault.setSpacing(20);
        vboxDefault.getChildren().addAll(scoreLabel, mainLabel, subLabel1);
        vboxDefault.setAlignment(Pos.CENTER);

        return vboxDefault;
    }

    /**
     * @return return this game's highest score values so far.
     */    
    public int getHighScore(){
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

        ImageView iconView = new ImageView( new Image("file:resources/typingIcon.png"));

        Label mainLabel = new Label("Typing");
        mainLabel.setFont(Font.font("Arial", FontWeight.BOLD, 30));

        Label subLabel = new Label("How many words per minute");
        subLabel.setFont(Font.font("Arial", 12));

        Label subLabel2 = new Label("can you type?");
        subLabel.setFont(Font.font("Arial", 12));

        VBox vbox = new VBox();
        vbox.setPrefSize(250, 250);
        vbox.setStyle("-fx-background-color: #FFFFFF");
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(20);

        vbox.getChildren().addAll(iconView, mainLabel, subLabel, subLabel2);

        return vbox;
    }
}