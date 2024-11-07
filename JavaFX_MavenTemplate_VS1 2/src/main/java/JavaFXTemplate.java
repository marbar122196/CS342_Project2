import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.scene.control.Label;
import java.util.HashMap;
import javafx.animation.PauseTransition;
import javafx.util.Duration;


import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Region;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.scene.layout.ColumnConstraints;


public class JavaFXTemplate extends Application {

//	private Player playerOne;
//	private Player playerTwo;
//	private Dealer theDealer;

	Button start, exit, Options;
	Label intro, authors;
	VBox root;
	HashMap<String, Scene> sceneMap= new HashMap<String, Scene>();

	EventHandler<ActionEvent> myHandler;
	BorderPane borderPane;
	HBox hBox;
	PauseTransition pause = new PauseTransition(Duration.seconds(3));
	Button sceneChange;



//	public void pauseGame(){}
//	public void playGame(){}
//	public void newLook(){}
//	public void quitGame(){}
//	public void showRules(){}

	public Scene optionsScreen(Stage primryStage){
		BorderPane rootPane = new BorderPane();
		rootPane.setStyle("-fx-background-color: #FFFFFF;"); // Optional styling

		// Add some content to the options screen (e.g., a label)
		Label optionsLabel = new Label("Options Screen");
		optionsLabel.setStyle("-fx-font-size: 20; -fx-text-fill: black;");
		rootPane.setCenter(optionsLabel); // Center the label in the options screen


		Scene scene = new Scene(rootPane, 1000, 1000);
		return scene;
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

		playPlayerOne.setEditable(false);
		playPlayerTwo.setEditable(false);

<<<<<<< Updated upstream
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
=======

		VBox betsPlayerOne = new VBox(playPlayerOne, antePlayerOne, pairPlusPlayerOne);
		VBox betsPlayerTwo = new VBox(playPlayerTwo, antePlayerTwo, pairPlusPlayerTwo);
>>>>>>> Stashed changes

		// Use a BorderPane as the root layout and add the HBox and Pane
		BorderPane rootPane = new BorderPane();
		rootPane.setTop(optionsBox); // Place Options button in the top right
		rootPane.setCenter(pane); // Place the game layout pane in the center

		Options.setOnAction(e -> primaryStage.setScene(sceneMap.get("OptionsScreen")));

		// Create and return the scene
		Scene scene = new Scene(rootPane, 1000, 1000);
		return scene;
	}




	public Scene createWelcomeScreen(Stage primaryStage){
		BorderPane outerPane = new BorderPane();
		outerPane.setStyle("-fx-background-color: #A30262;");

		start = new Button("Play <3");
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

		hBox = new HBox(r1, intro, r3);
		HBox buttonBox = new HBox(start, r4, exit);
		VBox welcomeText = new VBox(hBox, r2, authors, r5, buttonBox);

		StackPane innerPane = new StackPane(welcomeText);
		innerPane.setStyle("-fx-background-color: #FFFFFF; -fx-padding: 20;");
		innerPane.setMaxWidth(450);
		innerPane.setMaxHeight(350);


		//changes scene when play button is pressed
		start.setOnAction(e -> primaryStage.setScene(sceneMap.get("game")));
		exit.setOnAction(e -> {//delays exit for user viewability
			PauseTransition pause = new PauseTransition(Duration.seconds(.8));
			pause.setOnFinished(event -> Platform.exit());
			pause.play();
		});



//		borderPane = new BorderPane();
		outerPane.setPrefSize(500, 400);

		outerPane.setCenter(innerPane);
		Scene scene = new Scene(outerPane, 500, 400);
		return scene;

	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage primaryStage){
		primaryStage.setTitle("Three Card Poker");

//		myHandler = new EventHandler<ActionEvent>{ //change to change scene
//			public void handle(ActionEvent e){
//
//			}
//		}

		sceneMap.put("welcomeScreen", createWelcomeScreen(primaryStage));
		sceneMap.put("game", startNewGame(primaryStage));
		sceneMap.put("OptionsScreen",optionsScreen(primaryStage));

		primaryStage.setScene(sceneMap.get("welcomeScreen")); //change this
		primaryStage.show();

	}
//	public void updateGameInfo(String info){}

}