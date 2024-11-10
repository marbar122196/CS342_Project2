package components;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import gamelogic.*;

public class OptionsMenu {

    private Font customFont;
    private int titleSize;
    private int bodySize;
    private Player playerOne;
    private Player playerTwo;
    private Dealer theDealer;
    private Stage primaryStage;

    public OptionsMenu(Font customFont, int titleSize, int bodySize, Player playerOne, Player playerTwo, Dealer theDealer, Stage primaryStage) {
        this.customFont = customFont;
        this.titleSize = titleSize;
        this.bodySize = bodySize;
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.theDealer = theDealer;
        this.primaryStage = primaryStage;
    }

    public void show(Stage primaryStage) {
        Stage optionsStage = new Stage();
        optionsStage.initModality(Modality.APPLICATION_MODAL);
        optionsStage.initOwner(primaryStage);

        BorderPane optionsRoot = new BorderPane();
        Label optionsLabel = new Label("Options Menu");

        Button freshStartButton = new Button("fresh start");
        Button newLookButton = new Button("new look");
        Button winningHandsButton = new Button("winning hands");
        Button exitButton = new Button("exit >:(");

        freshStartButton.setMinWidth(200);
        newLookButton.setMinWidth(200);
        winningHandsButton.setMinWidth(200);
        exitButton.setMinWidth(200);

        newLookButton.setOnAction(e -> NewLook.apply(primaryStage.getScene()));
        winningHandsButton.setOnAction(e -> new WinningHandsScreen(customFont, titleSize, bodySize).show(optionsStage));
        exitButton.setOnAction(e -> new ExitScreen(customFont, titleSize).show(optionsStage));
        freshStartButton.setOnAction(e -> new StartNewGame(customFont,titleSize,bodySize,primaryStage,playerOne,playerTwo,theDealer).show(primaryStage));

        VBox optionsContent = new VBox(10, freshStartButton, newLookButton, winningHandsButton, exitButton);
        optionsContent.setAlignment(Pos.CENTER);
        optionsContent.setPadding(new Insets(20));
        optionsRoot.setCenter(optionsContent);

        Scene optionsScene = new Scene(optionsRoot, 250, 200);
        optionsScene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        optionsScene.getRoot().getStyleClass().add("options-scene");
        optionsStage.setScene(optionsScene);
        optionsStage.showAndWait();
    }
}
