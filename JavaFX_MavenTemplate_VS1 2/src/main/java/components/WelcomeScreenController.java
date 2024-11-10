package components;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class WelcomeScreenController {

    @FXML private Label intro;
    @FXML private Label authors;
    @FXML private Button startButton;
    @FXML private Button exitButton;

    private Runnable onStartGame;

    public void init(Runnable onStartGame) {
        this.onStartGame = onStartGame;

        startButton.setOnAction(e -> onStartGame.run());
        exitButton.setOnAction(e -> ((Stage) exitButton.getScene().getWindow()).close());
    }
}
