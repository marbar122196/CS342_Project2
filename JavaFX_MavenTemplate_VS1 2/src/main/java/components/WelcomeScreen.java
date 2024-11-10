package components;

//import javafx.animation.PauseTransition;
import javafx.application.Platform;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen; // Import Screen
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.text.Font;

public class WelcomeScreen {
    private Font customFont;
    private Scene scene;

    public WelcomeScreen(Font customFont, Stage primaryStage, Runnable onStartGame) {
        this.customFont = customFont;

        // Main layout with background color
        BorderPane outerPane = new BorderPane();
        outerPane.getStyleClass().add("purple-outer-pane-border");

        // Buttons
        Button start = new Button("Play <3");
        start.getStyleClass().add("purple-button");

        Button exit = new Button("Exit >:(");
        exit.getStyleClass().add("purple-button");

        // Labels for the title and authors
        Label intro = new Label("Welcome to 3 Card Poker");
        intro.getStyleClass().add("purple-label-title");

        Label authors = new Label("By Coda and Martha");
        authors.getStyleClass().add("purple-body-text");

        // Layout structure with a single VBox
        VBox welcomeText = new VBox(20); // 20 is the spacing between elements
        welcomeText.getChildren().addAll(intro, authors, start, exit);
        welcomeText.getStyleClass().add("purple-vbox");

        // Set actions for buttons
        start.setOnAction(e -> {
            // Set the desired position for the primary stage when switching to the StartNewGame scene
            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
            primaryStage.setX((screenBounds.getWidth() - 1500) / 2);  // Center horizontally, assuming 1500 width for StartNewGame
            primaryStage.setY((screenBounds.getHeight() - 800) / 2);  // Center vertically, assuming 800 height for StartNewGame
            onStartGame.run();
        });

        // Center the content
        VBox innerPane = new VBox(welcomeText);
        innerPane.getStyleClass().add("purple-vbox");

        outerPane.setCenter(innerPane);
        outerPane.setPrefSize(500, 400);

        this.scene = new Scene(outerPane, 500, 400);
        this.scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
    }

    public Scene getScene() {
        return scene;
    }
}
