package components.fundation.TCDialog;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;


public class TCDialog extends VBox {
    private Label mainText;
    private Label optionalText;

    public TCDialog(String mainText, String optionalText) {

        if (mainText.isEmpty())
            this.mainText = new Label("No main Text");
        else
            this.mainText = new Label(mainText);

        this.mainText.getStyleClass().addAll("title-h4", "text-primary", "center");
        getChildren().add(this.mainText);

        if (!optionalText.isEmpty()) {
            this.optionalText = new Label(optionalText);
            this.optionalText.getStyleClass().addAll("title-h4", "text-secondary");
            getChildren().add(this.optionalText);
        }

        getStyleClass().addAll("card");


    }
}
