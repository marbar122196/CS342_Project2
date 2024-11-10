package components;

import javafx.scene.Scene;

public class NewLook {
    private static final String DEFAULT_CSS = "/style.css";
    private static final String NEWLOOK_CSS = "/invert.css";
    private static boolean isDefault = true;

    public static void apply(Scene scene) {
        String defaultCssPath = NewLook.class.getResource(DEFAULT_CSS).toExternalForm();
        String newLookCssPath = NewLook.class.getResource(NEWLOOK_CSS).toExternalForm();

        if (isDefault) {
            scene.getStylesheets().remove(defaultCssPath);
            scene.getStylesheets().add(newLookCssPath);
        } else {
            scene.getStylesheets().remove(newLookCssPath);
            scene.getStylesheets().add(defaultCssPath);
        }
        isDefault = !isDefault;
    }
}
