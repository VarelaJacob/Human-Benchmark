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
 *************** To Do ***************
 * Add home button-> Close all apps
 * Add get started button event handler
 */

public class MainGameLoop extends Application {

    // Launch the program
    public static void main(String[] args) {
        launch(args);
    }

    Button homeButton;
    ScrollPane scroll;
    VBox numMemStart, verbMemStart, customStart;
    VBox reactionStart, aimStart, chimpStart, visualStart, typingStart;

    String BACKGROUNDBLUE  = "-fx-background-color: #2b86d1";
    String BACKGROUNDGRAY  = "-fx-background-color: #e6e8f4";
    String BACKGROUNDWHITE = "-fx-background-color: #FF0000";

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

    private BorderPane createBorderPane() {

        Score highScores = new Score();
        highScores.initializeScores();

        BorderPane border = new BorderPane();
       
        ImageView iconView = new ImageView( new Image("file:resources/humanBenchmarkicon.png"));

        ReactionTest reactionGame = new ReactionTest();
        AimTrainer   aimTrainer   = new AimTrainer();
        ChimpTest chimpTest = new ChimpTest();
        VisualMemory visualMemory = new VisualMemory();
        CustomGame customGame = new CustomGame();
        TypingTest typingTest = new TypingTest();
        NumberMemory numberMemory = new NumberMemory();
        VerbalMemory verbalMemory = new VerbalMemory();

        Label mainLabel = new Label("Human Benchmark");
        mainLabel.setFont(Font.font("Arial", FontWeight.BOLD,70));
        mainLabel.setTextFill(Color.web("#FFFFFF"));

        Label subLabel = new Label("Measure your abilities with brain games and cognitive tests.");
        subLabel.setFont(Font.font("Arial", 20));
        subLabel.setTextFill(Color.web("#FFFFFF"));

        Button getStartedBtn = new Button("Get Started");
        getStartedBtn.setStyle("-fx-background-color: #ffd154");
        getStartedBtn.setPrefSize(145, 45);
        getStartedBtn.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        getStartedBtn.setOnMouseClicked( ( e ) -> {
            border.setCenter(reactionGame.playGame());
        });
        
        VBox vboxDefault = new VBox();
        vboxDefault.setPadding(new Insets(10, 10, 10, 10));
        vboxDefault.setStyle(BACKGROUNDBLUE);
        vboxDefault.setMaxHeight(550);
        vboxDefault.setMinHeight(550);
        vboxDefault.setSpacing(30);
        vboxDefault.getChildren().addAll(iconView,mainLabel,subLabel,getStartedBtn);
        vboxDefault.setAlignment(Pos.CENTER);

        homeButton = new Button("Human Benchmark (home)");
        homeButton.setMinHeight(35);
        homeButton.setStyle("-fx-background-color: #f2f2f2");
        homeButton.setStyle("-fx-border-color: #000000");
        homeButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                border.setCenter(vboxDefault);
                highScores.setReactionScore(reactionGame.getHighScore());
            }
        });

        HBox hboxTop = new HBox();
        hboxTop.setStyle(BACKGROUNDGRAY);
        hboxTop.setPadding(new Insets(0, 0, 0, 20));
        hboxTop.setSpacing(20);
        hboxTop.getChildren().addAll(homeButton);

        VBox vboxBottom = new VBox();
        vboxBottom.setStyle(BACKGROUNDGRAY);
        vboxBottom.setPrefHeight(500);
        vboxBottom.setSpacing(20);

        HBox gameRow1 = new HBox();
        gameRow1.setSpacing(25);
        gameRow1.setAlignment(Pos.CENTER);
        HBox gameRow2 = new HBox();
        gameRow2.setAlignment(Pos.CENTER);
        gameRow2.setSpacing(25);
        HBox gameRow3 = new HBox();
        gameRow3.setSpacing(25);
        gameRow3.setAlignment(Pos.CENTER);

        reactionStart = reactionGame.createVBox();
        reactionStart.setOnMouseClicked( ( e ) -> {
            border.setCenter(reactionGame.playGame());
            scroll.setVvalue(0);
        });

        aimStart = aimTrainer.createVBox();
        aimStart.setOnMouseClicked( ( e ) -> {
            border.setCenter(aimTrainer.playGame());
            scroll.setVvalue(0);
        });

        chimpStart = chimpTest.createVBox();
        chimpStart.setOnMouseClicked( ( e ) -> {
            border.setCenter(chimpTest.playGame());
            scroll.setVvalue(0);
        });

        visualStart = visualMemory.createVBox();
        visualStart.setOnMouseClicked( e -> {
            border.setCenter(visualMemory.playGame());
            scroll.setVvalue(0);
        });

        customStart = customGame.createVBox();
        customStart.setOnMouseClicked( e -> {
            border.setCenter(customGame.playGame());
            scroll.setVvalue(0);
        });

        typingStart = typingTest.createVBox();
        typingStart.setOnMouseClicked( e -> {
            border.setCenter(typingTest.playGame());
            scroll.setVvalue(0);
        });
        
        numMemStart = numberMemory.createVBox();
        numMemStart.setOnMouseClicked( e -> {
            border.setCenter(numberMemory.playGame());
            scroll.setVvalue(0);
        });

        verbMemStart = verbalMemory.createVBox();
        verbMemStart.setOnMouseClicked( e -> {
            border.setCenter(verbalMemory.playGame());
            scroll.setVvalue(0);
        });


        gameRow1.getChildren().addAll(reactionStart, aimStart, chimpStart);
        gameRow2.getChildren().addAll(visualStart, customStart, typingStart);
        gameRow3.getChildren().addAll(numMemStart, verbMemStart);

        HBox hBoxSpacer1 = new HBox();
        hBoxSpacer1.setMinHeight(20);

        HBox hBoxSpacer2 = new HBox();
        hBoxSpacer2.setMinHeight(20);

        vboxBottom.getChildren().addAll(hBoxSpacer1, gameRow1, gameRow2, gameRow3, hBoxSpacer2);

        border.setTop(hboxTop);
        border.setCenter(vboxDefault);
        border.setBottom(vboxBottom);

        return border;
    }

    private void createbutton() {
    }
}