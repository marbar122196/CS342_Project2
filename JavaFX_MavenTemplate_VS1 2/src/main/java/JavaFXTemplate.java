import components.*;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Region;
import javafx.application.Platform;
import java.util.HashMap;
import javafx.scene.control.TextField;

public class JavaFXTemplate extends Application {

    Button start, exit, Options;
    Label intro, authors;
    HashMap<String, Scene> sceneMap = new HashMap<>();
    Font customFont = Font.loadFont(getClass().getResourceAsStream("/DotGothic16-Regular.ttf"), 20);
    int titleSize = 30;
    int bodySize = 20;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Three Card Poker");

        sceneMap.put("welcomeScreen", createWelcomeScreen(primaryStage));
        sceneMap.put("game", startNewGame(primaryStage));

        primaryStage.setScene(sceneMap.get("welcomeScreen"));
        primaryStage.show();
    }

    public Scene createWelcomeScreen(Stage primaryStage) {
        BorderPane outerPane = new BorderPane();
        outerPane.setStyle("-fx-background-color: #A30262;");

        start = new Button("Play <3");
        start.setFont(customFont);
        exit = new Button("Exit >:(");
        start.setMinWidth(200);
        exit.setMinWidth(200);
        start.setPrefHeight(20);
        exit.setPrefHeight(20);

        intro = new Label("Welcome to 3 Card Poker");
        authors = new Label("By Coda and Martha");
        intro.setMinWidth(300);
        authors.setMinWidth(400);

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

        HBox hBox = new HBox(r1, intro, r3);
        HBox buttonBox = new HBox(start, r4, exit);
        VBox welcomeText = new VBox(hBox, r2, authors, r5, buttonBox);

        VBox innerPane = new VBox(welcomeText);
        innerPane.setStyle("-fx-background-color: #FFFFFF; -fx-padding: 20;");
        innerPane.setMaxWidth(450);
        innerPane.setMaxHeight(350);

        start.setOnAction(e -> primaryStage.setScene(sceneMap.get("game")));
        exit.setOnAction(e -> {
            PauseTransition pause = new PauseTransition(Duration.seconds(0.8));
            pause.setOnFinished(event -> Platform.exit());
            pause.play();
        });

        outerPane.setPrefSize(500, 400);
        outerPane.setCenter(innerPane);
        return new Scene(outerPane, 500, 400);
    }

    public Scene startNewGame(Stage primaryStage) {

        // Create the Options button
        Options = new Button("Options");
        Options.setMinWidth(100);
        Options.setPrefHeight(20);

        // Create an HBox to hold the Options button and align it to the right
        HBox optionsBox = new HBox(Options);
        optionsBox.setAlignment(Pos.TOP_RIGHT);
        optionsBox.setPadding(new Insets(10)); // Optional padding for spacing

        // Create a Pane for the player fields and layout
        Pane pane = new Pane();

        // Text fields for player 1
        TextField playPlayerOne = new TextField();
        TextField antePlayerOne = new TextField();
        TextField pairPlusPlayerOne = new TextField();

        // Text fields for player 2
        TextField playPlayerTwo = new TextField();
        TextField antePlayerTwo = new TextField();
        TextField pairPlusPlayerTwo = new TextField();

        // Set play fields to be non-editable
        playPlayerOne.setEditable(false);
        playPlayerTwo.setEditable(false);

        // Player 1 bets VBox
        VBox betsPlayerOne = new VBox(10, playPlayerOne, antePlayerOne, pairPlusPlayerOne);
        betsPlayerOne.setLayoutX(100);  // Set the x-coordinate for player one's VBox
        betsPlayerOne.setLayoutY(200);  // Set the y-coordinate for player one's VBox

        // Player 2 bets VBox
        VBox betsPlayerTwo = new VBox(10, playPlayerTwo, antePlayerTwo, pairPlusPlayerTwo);
        betsPlayerTwo.setLayoutX(700);  // Set the x-coordinate for player two's VBox
        betsPlayerTwo.setLayoutY(200);  // Set the y-coordinate for player two's VBox

        // Add the player VBoxes to the Pane
        pane.getChildren().addAll(betsPlayerOne, betsPlayerTwo);

        // Use a BorderPane as the root layout and add the HBox and Pane
        BorderPane rootPane = new BorderPane();
        rootPane.setTop(optionsBox); // Place Options button in the top right
        rootPane.setCenter(pane); // Place the game layout pane in the center

        // Define Options button action to open OptionsMenu
        Options.setOnAction(e -> new OptionsMenu(customFont, titleSize, bodySize).show(primaryStage));

        // Create and return the scene
        return new Scene(rootPane, 1000, 1000);
    }
}