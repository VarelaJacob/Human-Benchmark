package HumanBenchmark;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainGameLoop extends Application {

    // Launch the program
    public static void main(String[] args) {
        launch(args);
    }

    Button homeButton;

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        primaryStage.setTitle("Human Benchmark - Jacob Varela");
        homeButton = new Button("Go home");

        StackPane layout = new StackPane();
        layout.getChildren().add(homeButton);

        Scene scene = new Scene(layout, 1600, 900);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}