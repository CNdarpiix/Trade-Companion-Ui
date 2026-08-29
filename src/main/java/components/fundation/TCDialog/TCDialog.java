package components.fundation.TCDialog;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import util.ComponentsConfig;

import java.util.List;


public class TCDialog extends VBox {
    private HBox dialogContent;

    public TCDialog(String mainText, String optionalText) {
        getChildren().addAll(
                ComponentsConfig.createLabel(mainText, List.of("title-h4", "text-primary", "center")),
                ComponentsConfig.createLabel(optionalText, List.of("title-h4", "text-secondary"))
        );
        getStyleClass().addAll("card");


    }

    public TCDialog(String mainText) {
        getChildren().add(ComponentsConfig.createLabel(mainText, List.of("title-h4", "text-primary", "center")));
    }

    public HBox getDialogContent() {
        if (dialogContent == null) {
            dialogContent = new HBox();
            getChildren().add(dialogContent);
        }

        return dialogContent;
    }
}
