import components.*;
import gamelogic.*;
import components.WelcomeScreen;
import components.StartNewGame;
import components.OptionsMenu;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import java.util.HashMap;

public class JavaFXTemplate extends Application {

    private HashMap<String, Scene> sceneMap = new HashMap<>();
    private Font customFont = Font.loadFont(getClass().getResourceAsStream("/DotGothic16-Regular.ttf"), 20);
    private int titleSize = 30;
    private int bodySize = 20;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Three Card Poker");

        WelcomeScreen welcomeScreen = new WelcomeScreen(customFont, primaryStage, () -> primaryStage.setScene(sceneMap.get("game")));
        sceneMap.put("welcomeScreen", welcomeScreen.getScene());

        StartNewGame startNewGame = new StartNewGame(customFont, titleSize, bodySize, primaryStage);
        sceneMap.put("game", startNewGame.getScene());

        primaryStage.setScene(sceneMap.get("welcomeScreen"));
        primaryStage.show();
    }
}
