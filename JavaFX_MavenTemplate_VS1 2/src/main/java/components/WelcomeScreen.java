package components;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
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
        outerPane.setStyle("-fx-background-color: #A30262;");

        // Buttons
        Button start = new Button("Play <3");
        start.setFont(customFont);
        start.setMinWidth(200);
        start.setPrefHeight(20);

        Button exit = new Button("Exit >:(");
        exit.setFont(customFont);
        exit.setMinWidth(200);
        exit.setPrefHeight(20);

        // Labels for the title and authors
        Label intro = new Label("Welcome to 3 Card Poker");
        intro.setFonstat(customFont);
        intro.setMinWidth(300);

        Label authors = new Label("By Coda and Martha");
        authors.setFont(customFont);
        authors.setMinWidth(400);

        // Spacers
        Region r1 = new Region();
        r1.setMinWidth(30);

        Region r2 = new Region();
        r2.setMinHeight(30);

        Region r3 = new Region();
        r3.setMinWidth(30);

        Region r4 = new Region();
        r4.setMinWidth(30);

        Region r5 = new Region();
        r5.setMinWidth(100);

        // Layout structure for the header, buttons, and text
        HBox hBox = new HBox(r1, intro, r3);
        hBox.setAlignment(Pos.CENTER);

        HBox buttonBox = new HBox(start, r4, exit);
        buttonBox.setAlignment(Pos.CENTER);

        VBox welcomeText = new VBox(hBox, r2, authors, r5, buttonBox);
        welcomeText.setAlignment(Pos.CENTER);

        // Set actions for buttons
        start.setOnAction(e -> onStartGame.run());
        exit.setOnAction(e -> {
            PauseTransition pause = new PauseTransition(Duration.seconds(0.8));
            pause.setOnFinished(event -> Platform.exit());
            pause.play();
        });

        // Center the content
        VBox innerPane = new VBox(welcomeText);
        innerPane.setStyle("-fx-background-color: #FFFFFF; -fx-padding: 20;");
        innerPane.setMaxWidth(450);
        innerPane.setMaxHeight(350);

        outerPane.setCenter(innerPane);
        outerPane.setPrefSize(500, 400);

        this.scene = new Scene(outerPane, 500, 400);
    }

    public Scene getScene() {
        return scene;
    }
}
