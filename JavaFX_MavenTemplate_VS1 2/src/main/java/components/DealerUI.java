package components;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class DealerUI {
    private ImageView dc1Image1;
    private ImageView dc2Image2;
    private ImageView dc3Image3;
    private HBox dealerPane;

    public DealerUI() {
        Image faceDownImage = new Image(getClass().getResourceAsStream("/facedown.png"));

        dc1Image1 = new ImageView(faceDownImage);
        dc1Image1.setFitWidth(75);
        dc1Image1.setFitHeight(75);

        dc2Image2 = new ImageView(faceDownImage);
        dc2Image2.setFitWidth(75);
        dc2Image2.setFitHeight(75);

        dc3Image3 = new ImageView(faceDownImage);
        dc3Image3.setFitWidth(75);
        dc3Image3.setFitHeight(75);

        dealerPane = new HBox(10, dc1Image1, dc2Image2, dc3Image3);
    }

    public HBox getDealerPane() {
        return dealerPane;
    }

    public void resetDealerCards() {
        Image faceDownImage = new Image(getClass().getResourceAsStream("/facedown.png"));

        dc1Image1.setImage(faceDownImage);
        dc2Image2.setImage(faceDownImage);
        dc3Image3.setImage(faceDownImage);
    }

    public ImageView getDc1Image1() {
        return dc1Image1;
    }

    public ImageView getDc2Image2() {
        return dc2Image2;
    }

    public ImageView getDc3Image3() {
        return dc3Image3;
    }
}
