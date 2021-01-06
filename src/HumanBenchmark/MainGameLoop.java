package HumanBenchmark;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * This is the main class for this project. This class will create the
 * main front end GUI and will change what game is displayed based on
 * the game clicked on by the user.
 * 
 * @author Jacob Varela
 */
public class MainGameLoop extends Application {

    // Launch the program.
    public static void main(String[] args) {
        launch(args);
    }

    // Global variables for the GUI.
    VBox reactionStart, aimStart, chimpStart, visualStart;
    VBox numMemStart, verbMemStart;
    ScrollPane scroll;
    Button homeButton;

    // Used for setting the background colors of some gui elements.
    String BACKGROUNDBLUE  = "-fx-background-color: #2b86d1";
    String BACKGROUNDGRAY  = "-fx-background-color: #e6e8f4";
    String BACKGROUNDWHITE = "-fx-background-color: #FF0000";

    /**
     * Creates the primaryStage to hold the GUI elements.
     * Creates a BorderPane to separate and organize the display.
     * Places the BorderPane within a ScrollPane to allow for smooth
     * scrolling functionality.
     * Adds the ScrollPane to the Scene, and display the GUI
     * (automativally maximized to the screen).
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Human Benchmark - Jacob Varela");

        BorderPane humanPane = createBorderPane();

        scroll = new ScrollPane(humanPane);
        scroll.setFitToWidth(true);

        Scene scene = new Scene(new BorderPane(scroll), 1600, 900);
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();        

    }

    /**
     * This method creates it's own instances of each game. It also sets
     * up the main organization and formatting for the BorderPane to 
     * create a simple and intuative user-friendly interface.
     * 
     * @return borderpane mimics look of humanbenchmark.com
     */
    private BorderPane createBorderPane() {

        // Initialze highScore count for each game.
        Score highScores = new Score();
        highScores.initializeScores();

        // Create new BorderPane.
        BorderPane border = new BorderPane();
       
        // Create a variable to use the lightning bolt icon used on the homepage.
        ImageView iconView = new ImageView( new Image("file:resources/humanBenchmarkicon.png"));

        // Create instances of each game.
        ReactionTest reactionGame = new ReactionTest();
        AimTrainer   aimTrainer   = new AimTrainer();
        chimpTest    chimpTest    = new chimpTest();
        VisualMemory visualMemory = new VisualMemory();
        NumberMemory numberMemory = new NumberMemory();
        VerbalMemory verbalMemory = new VerbalMemory();

        // Homepage title label.
        Label mainLabel = new Label("Human Benchmark");
        mainLabel.setFont(Font.font("Arial", FontWeight.BOLD,70));
        mainLabel.setTextFill(Color.web("#FFFFFF"));

        // Homepage subtitle label.
        Label subLabel = new Label("Measure your abilities with brain games and cognitive tests.");
        subLabel.setFont(Font.font("Arial", 20));
        subLabel.setTextFill(Color.web("#FFFFFF"));

        // Button to begin with the first test. (Reaction Time)
        Button getStartedBtn = new Button("Get Started");
        getStartedBtn.setStyle("-fx-background-color: #ffd154");
        getStartedBtn.setPrefSize(145, 45);
        getStartedBtn.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        getStartedBtn.setOnMouseClicked( ( e ) -> {
            border.setCenter(reactionGame.playGame());
        });
        
        // Creates Main VBox to be used in the center of the BorderPane.
        VBox vboxDefault = new VBox();
        vboxDefault.setPadding(new Insets(10, 10, 10, 10));
        vboxDefault.setStyle(BACKGROUNDBLUE);
        vboxDefault.setMaxHeight(550);
        vboxDefault.setMinHeight(550);
        vboxDefault.setSpacing(30);
        vboxDefault.getChildren().addAll(iconView,mainLabel,subLabel,getStartedBtn);
        vboxDefault.setAlignment(Pos.CENTER);

        /* Creates a home button that will always be visible on the top of 
         * the page. This button will also update the highscores of each
         * game when clicked. 
         */
        homeButton = new Button("Human Benchmark (home)");
        homeButton.setMinHeight(35);
        homeButton.setStyle("-fx-background-color: #f2f2f2");
        homeButton.setStyle("-fx-border-color: #000000");
        homeButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                border.setCenter(vboxDefault);

                highScores.setReactionScore(reactionGame.getHighScore());
                highScores.setAimScore(aimTrainer.getHighScore());
                highScores.setChimpScore(chimpTest.getHighScore());
                highScores.setVisualScore(visualMemory.getHighScore());
                highScores.setNumMemScore(numberMemory.getHighScore());
                highScores.setVerbScore(verbalMemory.getHighScore());
            }
        });

        // Create HBox to store the home button.
        HBox hboxTop = new HBox();
        hboxTop.setStyle(BACKGROUNDGRAY);
        hboxTop.setPadding(new Insets(0, 0, 0, 20));
        hboxTop.setSpacing(20);
        hboxTop.getChildren().addAll(homeButton);

        // Create Vbox to store the game elements on the homepage.
        VBox vboxBottom = new VBox();
        vboxBottom.setStyle(BACKGROUNDGRAY);
        vboxBottom.setPrefHeight(500);
        vboxBottom.setSpacing(20);

        /* Create Hboxes to store each of the game elements. This is done
         * to ensure that the homepage is clean and organized. 
         */
        HBox gameRow1 = new HBox();
        gameRow1.setSpacing(25);
        gameRow1.setAlignment(Pos.CENTER);
        HBox gameRow2 = new HBox();
        gameRow2.setAlignment(Pos.CENTER);
        gameRow2.setSpacing(25);
        HBox gameRow3 = new HBox();
        gameRow3.setSpacing(25);
        gameRow3.setAlignment(Pos.CENTER);

        /* Scroll to the top of the screen and begin taking the Reaction Test
         * when this element is clicked.
         */
        reactionStart = reactionGame.createVBox();
        reactionStart.setOnMouseClicked( ( e ) -> {
            border.setCenter(reactionGame.playGame());
            scroll.setVvalue(0);
        });

        /* Scroll to the top of the screen and begin playing the Aim Trainer
         * when this element is clicked.
         */
        aimStart = aimTrainer.createVBox();
        aimStart.setOnMouseClicked( ( e ) -> {
            border.setCenter(aimTrainer.playGame());
            scroll.setVvalue(0);
        });

        /* Scroll to the top of the screen and begin taking the Chimp Test
         * when this element is clicked.
         */
        chimpStart = chimpTest.createVBox();
        chimpStart.setOnMouseClicked( ( e ) -> {
            border.setCenter(chimpTest.playGame());
            scroll.setVvalue(0);
        });

        /* Scroll to the top of the screen and begin taking the Visual Test
         * when this element is clicked.
         */
        visualStart = visualMemory.createVBox();
        visualStart.setOnMouseClicked( e -> {
            border.setCenter(visualMemory.playGame());
            scroll.setVvalue(0);
        });
        
        /* Scroll to the top of the screen and begin taking Number Memory Test
         * when this element is clicked.
         */
        numMemStart = numberMemory.createVBox();
        numMemStart.setOnMouseClicked( e -> {
            border.setCenter(numberMemory.playGame());
            scroll.setVvalue(0);
        });

        /* Scroll to the top of the screen and begin taking Verbal Memory Test
        * when this element is clicked.
        */
        verbMemStart = verbalMemory.createVBox();
        verbMemStart.setOnMouseClicked( e -> {
            border.setCenter(verbalMemory.playGame());
            scroll.setVvalue(0);
        });

        // Add the game elements to HBoxes.
        gameRow1.getChildren().addAll(reactionStart, aimStart, chimpStart);
        gameRow2.getChildren().addAll(visualStart, numMemStart, verbMemStart);

        /* Create Hboxes to be used only to separate the GridPane center from the
         * GridPane bottom.
         */
        HBox hBoxSpacer1 = new HBox();
        hBoxSpacer1.setMinHeight(20);
        HBox hBoxSpacer2 = new HBox();
        hBoxSpacer2.setMinHeight(20);

        // Add the game and spacer HBoxes to the bottom VBox.
        vboxBottom.getChildren().addAll(hBoxSpacer1, gameRow1, gameRow2, gameRow3, hBoxSpacer2);

        // Add the final elements to the BorderPane.
        border.setTop(hboxTop);
        border.setCenter(vboxDefault);
        border.setBottom(vboxBottom);

        return border;
    }
}