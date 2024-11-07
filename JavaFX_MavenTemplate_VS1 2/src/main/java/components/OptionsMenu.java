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

public class OptionsMenu {

    private Font customFont;
    private int titleSize;
    private int bodySize;

    public OptionsMenu(Font customFont, int titleSize, int bodySize) {
        this.customFont = customFont;
        this.titleSize = titleSize;
        this.bodySize = bodySize;
    }

    public void show(Stage primaryStage) {
        Stage optionsStage = new Stage();
        optionsStage.initModality(Modality.APPLICATION_MODAL);
        optionsStage.initOwner(primaryStage);

        BorderPane optionsRoot = new BorderPane();
        Label optionsLabel = new Label("Options Menu");

        Button freshStartButton = new Button("fresh start");
        Button newLookButton = new Button("new look");
        Button rulesButton = new Button("rules");
        Button winningHandsButton = new Button("winning hands");
        Button exitButton = new Button("exit >:(");

        freshStartButton.setMinWidth(200);
        newLookButton.setMinWidth(200);
        rulesButton.setMinWidth(200);
        winningHandsButton.setMinWidth(200);
        exitButton.setMinWidth(200);

        rulesButton.setOnAction(e -> new RulesScreen(customFont, titleSize, bodySize).show(optionsStage));
        winningHandsButton.setOnAction(e -> new WinningHandsScreen(customFont, titleSize, bodySize).show(optionsStage));
        exitButton.setOnAction(e -> new ExitScreen(customFont, titleSize).show(optionsStage));

        VBox optionsContent = new VBox(10, freshStartButton, newLookButton, rulesButton, winningHandsButton, exitButton);
        optionsContent.setAlignment(Pos.CENTER);
        optionsContent.setPadding(new Insets(20));
        optionsRoot.setCenter(optionsContent);

        Scene optionsScene = new Scene(optionsRoot, 250, 300);
        optionsStage.setScene(optionsScene);
        optionsStage.showAndWait();
    }
}
