package HumanBenchmark;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
 * This is the main class for this project. This class will create the main
 * front end GUI and will change what game is displayed based on the game
 * clicked on by the user.
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
    Button homeBtn;

    // Used for setting the background colors of some gui elements.
    String BACKGROUNDBLUE = "-fx-background-color: #2b86d1";
    String BACKGROUNDGRAY = "-fx-background-color: #e6e8f4";
    String BACKGROUNDWHITE = "-fx-background-color: #FF0000";

    // Create instances of each game.
    ReactionTest reactionGame = new ReactionTest();
    AimTrainer   aimTrainer   = new AimTrainer();
    chimpTest    chimpTest    = new chimpTest();
    VisualMemory visualMemory = new VisualMemory();
    NumberMemory numberMemory = new NumberMemory();
    VerbalMemory verbalMemory = new VerbalMemory();

    // Used to set the speed of Scrolling on the ScrollPane
    final double SPEED = 0.01;

    /**
     * Creates the primaryStage to hold the GUI elements. Creates a BorderPane to
     * separate and organize the display. Places the BorderPane within a ScrollPane
     * to allow for smooth scrolling functionality. Adds the ScrollPane to the
     * Scene, and display the GUI (automativally maximized to the screen).
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Human Benchmark - Jacob Varela");

        BorderPane humanPane = createBorderPane();

        scroll = new ScrollPane(humanPane);
        scroll.setFitToWidth(true);

        // Increase the speed of the scrolling.
        scroll.getContent().setOnScroll(scrollEvent -> {
            double deltaY = scrollEvent.getDeltaY() * SPEED;
            scroll.setVvalue(scroll.getVvalue() - deltaY);
        });

        Scene scene = new Scene(new BorderPane(scroll), 1000, 900);
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();

    }

    /** */
    public void setHighScores(){
        
    }

    /**
     * 
     * @throws SQLException
     */
    private void updateHighScores() throws SQLException {
        // Update AimTrainer High Score value in the db.
            PreparedStatement prep = HighScores.connection.prepareStatement(
                "UPDATE game SET highScore = ? WHERE name = ?");
            prep.setLong  (1, aimTrainer.getHighScore());
            prep.setString(2, "AimTrainer");
            prep.executeUpdate();

            // Update ChimpTest High Score in the db.
            PreparedStatement prep2 = HighScores.connection.prepareStatement(
                "UPDATE game SET highScore = ? WHERE name = ?");
            prep2.setLong  (1, chimpTest.getHighScore());
            prep2.setString(2, "ChimpTest");
            prep2.executeUpdate();

            // Update NumberMemory High Score in the db.
            PreparedStatement prep3 = HighScores.connection.prepareStatement(
                "UPDATE game SET highScore = ? WHERE name = ?");
            prep3.setLong  (1, numberMemory.getHighScore());
            prep3.setString(2, "NumberMemory");
            prep3.executeUpdate();

            // Update ReactionTest High Score in the db.
            PreparedStatement prep4 = HighScores.connection.prepareStatement(
                "UPDATE game SET highScore = ? WHERE name = ?");
            prep4.setLong  (1, reactionGame.getHighScore());
            prep4.setString(2, "ReactionTest");
            prep4.executeUpdate();

            // Update VerbalMemory High Score in the db.
            PreparedStatement prep5 = HighScores.connection.prepareStatement(
                "UPDATE game SET highScore = ? WHERE name = ?");
            prep5.setLong  (1, verbalMemory.getHighScore());
            prep5.setString(2, "VerbalMemory");
            prep5.executeUpdate();

            // Update VisualMemory High Score in the db.
            PreparedStatement prep6 = HighScores.connection.prepareStatement(
                "UPDATE game SET highScore = ? WHERE name = ?");
            prep6.setLong  (1, visualMemory.getHighScore());
            prep6.setString(2, "VisualMemory");
            prep6.executeUpdate();
    }

    /**
     * This method creates it's own instances of each game. It also sets up the main
     * organization and formatting for the BorderPane to create a simple and
     * intuative user-friendly interface.
     * 
     * @return borderpane mimics look of humanbenchmark.com
     */
    private BorderPane createBorderPane() {

        HighScores scoreDB = new HighScores();
        ResultSet scores;
        try {
            scores = scoreDB.displayScores();
            while( scores.next()){
                System.out.println(scores.getString("name") +
                " "+scores.getLong("highScore"));
            }
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

        // Create new BorderPane.
        BorderPane border = new BorderPane();
       
        // Create a variable to use the lightning bolt icon used on the homepage.
        ImageView iconView = new ImageView( new Image("file:resources/humanBenchmarkicon.png"));

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
        homeBtn = new Button("Home");
        homeBtn.setMinHeight(40);
        homeBtn.setMinWidth(50);
        homeBtn.setStyle("-fx-background-color: #f2f2f2;"+
        "-fx-border-color: #000000;"+
        "-fx-border-radius: 15px;" +
        "-fx-background-radius: 15px;" +
        "-fx-border-width: 2");
        homeBtn.setFont(Font.font("Arial", FontWeight.THIN, 13));
        
        homeBtn.setOnMouseEntered(e ->{
            homeBtn.setStyle("-fx-background-color: yellow;"+
        "-fx-border-color: black; -fx-border-width: 2");          
        });

        homeBtn.setOnMouseExited(ev ->{
            homeBtn.setStyle("-fx-background-color: #f2f2f2;"+
            "-fx-border-color: #000000;"+
            "-fx-border-radius: 15px;" +
            "-fx-background-radius: 15px;" +
            "-fx-border-width: 2");
        });

        homeBtn.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                border.setCenter(vboxDefault);

                
                try {                    
                    
                    updateHighScores();

                    ResultSet scoresTemp;
                    scoresTemp = scoreDB.displayScores();
                    while( scoresTemp.next()){
                        System.out.println(scoresTemp.getString("name") +
                        " "+ scoresTemp.getLong("highScore"));
                    }
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }

            
        });

        // Create HBox to store the home button.
        HBox hboxTop = new HBox();
        hboxTop.setStyle(BACKGROUNDGRAY);
        hboxTop.setPadding(new Insets(0, 0, 0, 20));
        hboxTop.setSpacing(20);
        hboxTop.getChildren().addAll(homeBtn);
        hboxTop.setAlignment(Pos.CENTER);

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