package components.fundation.input;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.util.Locale;

public class TCInput extends VBox {
    private Label text;
    private TextField textField;
    private Label errorText;

    public TCInput(String placeHolder, InputType type, String error) {
        text = new Label();
        text.getStyleClass().add("text-primary");

        textField = new TextField();

        errorText = new Label();
        errorText.getStyleClass().add("text-error");


        if (placeHolder != null && !placeHolder.isBlank())
            text.setText(placeHolder);

        if (error != null && !error.isBlank()) {
            errorText.setText(error);
            errorText.setVisible(false);
        }

        getStyleClass().addAll("input", type.name().toLowerCase());
        getChildren().addAll(text , textField , errorText);

    }

    public String getText(){
        return textField.getText();
    }

    public void isError(boolean t) {
        errorText.setVisible(t);
    }

}
