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


public class JavaFXTemplate extends Application {

//	private Player playerOne;
//	private Player playerTwo;
//	private Dealer theDealer;

	Button start, exit;
	Label intro, authors;
	VBox root;
	HashMap<String, Scene> sceneMap= new HashMap<String, Scene>();

	EventHandler<ActionEvent> myHandler;
	BorderPane borderPane;
	HBox hBox;
	PauseTransition pause = new PauseTransition(Duration.seconds(3));



//	public void pauseGame(){}
//	public void playGame(){}
//	public void newLook(){}
//	public void quitGame(){}
//	public void showRules(){}
	public Scene creatWelcomeScreen(){
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

		sceneMap.put("welcomeScreen", creatWelcomeScreen());

		primaryStage.setScene(sceneMap.get("welcomeScreen")); //change this
		primaryStage.show();

	}
	public void startNewGame(){

	}
//	public void updateGameInfo(String info){}

}
