package components.fundation.button;

import javafx.scene.control.Button;

public class TCButton extends Button {

    private String text;
    private ButtonType type;

    public TCButton(String text, ButtonType type) {
        super();
        this.text = text;
        this.type = type;
        setText(text);

        if (type.equals(ButtonType.CARD)) {
            getStyleClass().addAll(
                    "card",
                    type.name().toLowerCase()
            );
        } else {
            getStyleClass().addAll(
                    "button",
                    type.
                            name().
                            toLowerCase()
            );
        }

    }

    public void setType(ButtonType type) {
        if (type != null) {
            getStyleClass().remove(this.type.name().toLowerCase());
            this.type = type ;
            getStyleClass().add(this.type.name().toLowerCase());
        } else {
            getStyleClass().add(this.type.name().toLowerCase());
        }

    }


}
