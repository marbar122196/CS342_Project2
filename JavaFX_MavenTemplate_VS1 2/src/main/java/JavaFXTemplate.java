import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.scene.control.Label;
import java.util.HashMap;
import javafx.animation.PauseTransition;
import javafx.util.Duration;
import javafx.stage.Modality;
import javafx.scene.text.Font;
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

	Button start, exit, Options,optionsButton,freshStartButton,newLookButton,rulesButton,winningHandsButton,exitButton;
	Label intro, authors,rulesText,rulesTitle;
	VBox root;
	HashMap<String, Scene> sceneMap= new HashMap<String, Scene>();

	EventHandler<ActionEvent> myHandler;
	BorderPane borderPane;
	HBox hBox;
	PauseTransition pause = new PauseTransition(Duration.seconds(3));
	Button sceneChange;
	Font customFont = Font.loadFont(getClass().getResourceAsStream("/DotGothic16-Regular.ttf"), 20);



//	public void pauseGame(){}
//	public void playGame(){}
//	public void newLook(){}
//	public void quitGame(){}
//	public void showRules(){}

	public void rules(Stage optionsStage){
		//making stage and setting optionsstage as its parent
		Stage rulesStage = new Stage();
		rulesStage.initModality(Modality.APPLICATION_MODAL);
		rulesStage.initOwner(optionsStage);
		rulesStage.setTitle("Rules");

		// title
		rulesTitle = new Label("rules");
		rulesTitle.setFont(customFont);
		rulesTitle.setStyle("-fx-font-size: 28px; -fx-text-fill: #A30262;");
		rulesTitle.setStyle("-fx-text-alignment: center;");

		// rules
		rulesText = new Label(
				"Ante Wager: Players place an ante wager ($5 - $25).\n\n" +
						"Optional Pair Plus Bet: Players can also make a Pair Plus wager ($5 - $25), paying\n" +
						"based solely on their hand if it has at least a pair of 2’s, regardless of the dealer's hand.\n\n" +
						"Dealing Cards: Each player and the dealer receive three cards; players’ cards are face\n" +
						"up, the dealer’s face down.\n\n" +
						"Play or Fold:\n" +
						"• Fold: Player loses both ante and Pair Plus wagers (if made).\n" +
						"• Play: Player places a play wager equal to the ante.\n\n" +
						"Dealer's Hand:\n" +
						"• Below Queen High: Play wager is returned, and ante is pushed.\n" +
						"• Queen High or Better: Dealer’s hand is compared to the player’s hand:\n" +
						"  - Dealer Wins: Player loses both ante and play wagers.\n" +
						"  - Player Wins: Player receives 1 to 1 payout on both wagers."
		);
		//font and center text
		rulesText.setFont(customFont);
		rulesText.setStyle("-fx-font-size: 18px; -fx-text-fill: #A30262;");
		rulesText.setWrapText(true);
		rulesText.setStyle("-fx-text-alignment: center;");
		rulesText.setAlignment(Pos.CENTER);

		// escape button
		Button escapeButton = new Button("escape");
		escapeButton.setFont(customFont);
		escapeButton.setStyle("-fx-background-color: #A30262; -fx-text-fill: white;");
		escapeButton.setOnAction(e -> rulesStage.close());

		//set alignment
		VBox rulesBox = new VBox(10, rulesTitle, rulesText, escapeButton);
		rulesBox.setAlignment(Pos.CENTER);
		rulesBox.setPadding(new Insets(20));
		rulesBox.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #A30262; -fx-border-width: 15px;");

		Scene rulesScene = new Scene(rulesBox, 850, 850);
		rulesStage.setScene(rulesScene);
		rulesStage.showAndWait(); // show as modal

	}

	public void optionsMenu(Stage primaryStage){
		Stage optionsStage = new Stage();
		optionsStage.initModality(Modality.APPLICATION_MODAL);//sets modality to pause all other windows until its closed
		optionsStage.initOwner(primaryStage);//primarystage (game window) is owner of the options stage so it will always stay on top of primary stage window

		BorderPane optionsRoot = new BorderPane();//holds all other ui elems in scene
		Label optionsLabel = new Label("Options Menu");

		optionsButton = new Button("options");
		freshStartButton = new Button("fresh start");
		newLookButton = new Button("new look");
		rulesButton = new Button("rules");
		winningHandsButton = new Button("winning hands");
		exitButton = new Button("exit >:(");

		// Set button widths for consistency
		optionsButton.setMinWidth(200);
		freshStartButton.setMinWidth(200);
		newLookButton.setMinWidth(200);
		rulesButton.setMinWidth(200);
		winningHandsButton.setMinWidth(200);
		exitButton.setMinWidth(200);

		// Define actions for the buttons
		optionsButton.setOnAction(e -> System.out.println("Options clicked"));
		freshStartButton.setOnAction(e -> System.out.println("Fresh Start clicked"));
		newLookButton.setOnAction(e -> System.out.println("New Look clicked"));
		rulesButton.setOnAction(e -> rules(optionsStage));
		winningHandsButton.setOnAction(e -> System.out.println("Winning Hands clicked"));
		exitButton.setOnAction(e -> optionsStage.close());

		// Add buttons to the VBox and set alignment
		VBox optionsContent = new VBox(10, optionsButton, freshStartButton, newLookButton, rulesButton, winningHandsButton, exitButton);
		optionsContent.setAlignment(Pos.CENTER);
		optionsContent.setPadding(new Insets(20)); // Add padding for aesthetics
		optionsRoot.setCenter(optionsContent);

		Scene optionsScene = new Scene(optionsRoot, 300, 400);
		optionsStage.setScene(optionsScene);
		optionsStage.showAndWait();

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

		Options.setOnAction(e -> optionsMenu(primaryStage));//does return scene only opens modal

		// Create and return the scene
		Scene scene = new Scene(rootPane, 1000, 1000);
		return scene;
	}




	public Scene createWelcomeScreen(Stage primaryStage){
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

		primaryStage.setScene(sceneMap.get("welcomeScreen")); //change this
		primaryStage.show();

	}
//	public void updateGameInfo(String info){}

}