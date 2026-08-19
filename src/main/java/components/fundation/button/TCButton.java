package components.fundation.button;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import org.w3c.dom.events.MouseEvent;

public class TCButton extends Button {

    private String text;
    private ButtonType type;

    public TCButton(String text, ButtonType type) {
        super();
        this.text = text;
        this.type = type;
        setText(text);

        if (type.equals(ButtonType.CARD)) {
            getStyleClass().add(
                    "card"
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


       public TCButton(ImageView imageView) {

            getStyleClass().add("image");

            imageView.setFitWidth(20);
            imageView.setFitHeight(20);
            imageView.setPreserveRatio(true);

            setGraphic(imageView);

            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);

            setBackground(Background.EMPTY);
            setBorder(Border.EMPTY);

            setPadding(Insets.EMPTY);

            setMinSize(30, 30);
            setPrefSize(30, 30);
            setMaxSize(30, 30);
        }


    public void setType(ButtonType type) {
        if (type != null) {
            getStyleClass().remove(this.type.name().toLowerCase());
            this.type = type;
            getStyleClass().add(this.type.name().toLowerCase());
        } else {
            getStyleClass().add(this.type.name().toLowerCase());
        }

    }



}
