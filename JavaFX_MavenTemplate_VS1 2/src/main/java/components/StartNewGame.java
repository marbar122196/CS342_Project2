package components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.text.Font;

public class StartNewGame {
    private Font customFont;
    private int titleSize;
    private int bodySize;
    private Scene scene;

    public StartNewGame(Font customFont, int titleSize, int bodySize, Stage primaryStage) {
        this.customFont = customFont;
        this.titleSize = titleSize;
        this.bodySize = bodySize;

        Button optionsButton = new Button("Options");
        optionsButton.setMinWidth(100);
        HBox optionsBox = new HBox(optionsButton);
        optionsBox.setAlignment(Pos.TOP_RIGHT);
        optionsBox.setPadding(new Insets(10));

        Pane pane = new Pane();
        TextField playPlayerOne = new TextField();
        TextField antePlayerOne = new TextField();
        TextField pairPlusPlayerOne = new TextField();
        TextField playPlayerTwo = new TextField();
        TextField antePlayerTwo = new TextField();
        TextField pairPlusPlayerTwo = new TextField();

        playPlayerOne.setEditable(false);
        playPlayerTwo.setEditable(false);

        VBox betsPlayerOne = new VBox(10, playPlayerOne, antePlayerOne, pairPlusPlayerOne);
        betsPlayerOne.setLayoutX(100);
        betsPlayerOne.setLayoutY(200);
        VBox betsPlayerTwo = new VBox(10, playPlayerTwo, antePlayerTwo, pairPlusPlayerTwo);
        betsPlayerTwo.setLayoutX(700);
        betsPlayerTwo.setLayoutY(200);

        pane.getChildren().addAll(betsPlayerOne, betsPlayerTwo);

        BorderPane rootPane = new BorderPane();
        rootPane.setTop(optionsBox);
        rootPane.setCenter(pane);

        optionsButton.setOnAction(e -> new OptionsMenu(customFont, titleSize, bodySize).show(primaryStage));

        this.scene = new Scene(rootPane, 1000, 1000);
    }

    public Scene getScene() {
        return scene;
    }
}
