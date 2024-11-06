import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.scene.control.Label;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
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
	sceneMap = new HashMap<String, scene>();

	EventHandler<ActionEvent> myHandler;
	BorderPane borderPane;
	HBox hBox;



//	public void pauseGame(){}
//	public void playGame(){}
//	public void newLook(){}
//	public void quitGame(){}
//	public void showRules(){}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception{
		primaryStage.setTitle("Three Card Poker");

		start = new Button("Start <3");
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

//		myHandler = new EventHandler<ActionEvent>{ //change to change scene
//			public void handle(ActionEvent e){
//
//			}
//		}


		hBox = new HBox(r1, intro, r2, authors, r3);
		VBox top = new VBox(hBox, authors);
		HBox buttonBox = new HBox(start, r1, exit);



		borderPane = new BorderPane();
		borderPane.setPrefSize(500, 400);
		borderPane.setTop(top);
		borderPane.setCenter(buttonBox);
		Scene scene = new Scene(borderPane, 360, 360);
		primaryStage.setScene(scene); //change this
		primaryStage.show();

	}
//	public void startNewGame(){}
//	public void updateGameInfo(String info){}

}
