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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


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

        // Text fields for player 1
        TextField playPlayerOne = new TextField();
        TextField antePlayerOne = new TextField();
        TextField pairPlusPlayerOne = new TextField();
        TextField namePlayerOne = new TextField();
        namePlayerOne.setPromptText("Enter name here: ");

        //Buttons for Player 1
        Button playerOneDeal = new Button("deal");
        Button playerOneFold = new Button("fold");

        // Text fields for player 2
        TextField playPlayerTwo = new TextField();
        TextField antePlayerTwo = new TextField();
        TextField pairPlusPlayerTwo = new TextField();
        TextField namePlayerTwo = new TextField();
        namePlayerTwo.setPromptText("Enter name here: ");


        //Buttons for Player 2
        Button playerTwoDeal = new Button("deal");
        Button playerTwoFold = new Button("fold");

        playPlayerOne.setEditable(false);
        playPlayerTwo.setEditable(false);

        // Player 1 bets VBox
        VBox betsPlayerOne = new VBox(10, playPlayerOne, antePlayerOne, pairPlusPlayerOne);
        betsPlayerOne.setLayoutX(100);  // Set the x-coordinate for player one's VBox
        betsPlayerOne.setLayoutY(300);  // Set the y-coordinate for player one's VBox


        //Player 1 buttons Hbox
        HBox buttonPlayerOne = new HBox(10, playerOneDeal, playerOneFold);
//        buttonPlayerOne.setLayoutX(260);
//        buttonPlayerOne.setLayoutY(350);

        //Player 1 contents VBox
        VBox contentsPlayerOne = new VBox(30, namePlayerOne, buttonPlayerOne);
        contentsPlayerOne.setLayoutX(260);
        contentsPlayerOne.setLayoutY(310);


        // Player 2 bets VBox
        VBox betsPlayerTwo = new VBox(10, playPlayerTwo, antePlayerTwo, pairPlusPlayerTwo);
        betsPlayerTwo.setLayoutX(700);  // Set the x-coordinate for player two's VBox
        betsPlayerTwo.setLayoutY(300);  // Set the y-coordinate for player two's VBox


        //Player 2 buttons HBox
        HBox buttonPlayerTwo = new HBox(10, playerTwoDeal, playerTwoFold);
//        buttonPlayerTwo.setLayoutX(600);
//        buttonPlayerTwo.setLayoutY(350);


        //Player 2 contents Vboc
        VBox contentsPlayerTwo = new VBox(30, namePlayerTwo, buttonPlayerTwo);
        contentsPlayerTwo.setLayoutX(530);
        contentsPlayerTwo.setLayoutY(310);

        //should we do thing single cards so that it can flip one at a time as a transition?
        Image p1c1 = new Image(getClass().getResourceAsStream("/faceDown.png"));
        ImageView p1c1Image1 = new ImageView(p1c1);
        p1c1Image1.setFitWidth(100);
        p1c1Image1.setFitHeight(100);

        Image p1c2 = new Image(getClass().getResourceAsStream("/faceDown.png"));
        ImageView p1c1Image2 = new ImageView(p1c2);
        p1c1Image2.setFitWidth(100);
        p1c1Image2.setFitHeight(100);

        Image p1c3 = new Image(getClass().getResourceAsStream("/faceDown.png"));
        ImageView p1c1Image3 = new ImageView(p1c3);
        p1c1Image3.setFitWidth(100);
        p1c1Image3.setFitHeight(100);

        HBox deckOfCardsP1 = new HBox(5, p1c1Image1,p1c1Image2,p1c1Image3);

        VBox p1CardsHolder= new VBox(10, deckOfCardsP1);
        p1CardsHolder.setLayoutX(300);
        p1CardsHolder.setLayoutY(200);

        pane.getChildren().addAll(betsPlayerOne, betsPlayerTwo, contentsPlayerOne, contentsPlayerTwo, p1CardsHolder);

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
