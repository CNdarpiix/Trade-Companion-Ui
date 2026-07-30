package components.fundation.navitem;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class NavItem extends HBox {

    private Label title;

    public NavItem(String text
          //  , Node icon
    ) {
        title = new Label(text);
        setStyle("-fx-alignment: CENTER");
        getStyleClass().add("nav-item");
        getChildren().addAll(
                //icon,
                title);
    }
}
