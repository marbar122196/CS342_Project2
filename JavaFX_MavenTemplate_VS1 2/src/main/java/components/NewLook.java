package components;

import javafx.scene.Scene;

public class NewLook {
    private static final String INVERT_CSS = "/invert.css";

    public static void apply(Scene scene) {
        scene.getStylesheets().add(NewLook.class.getResource(INVERT_CSS).toExternalForm());  // Add invert CSS
    }
}

//APPLIES TO PRIMARYSTAGE WICH IS THE STARTEWGAME FIGURE OUT CSS