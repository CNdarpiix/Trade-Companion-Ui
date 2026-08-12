package components.fundation.navitem;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import navigation.Page;
import navigation.SidebardService;

public class NavItem extends Pane {
    private Pane content;

    private final Page page;

    public NavItem(Page page, boolean isHBox
                   //  , Node icon
    ) {
        getStyleClass().addAll("nav-item");

        if (isHBox) {
            content = new HBox();
            content.prefWidthProperty().bind(widthProperty());
            content.setMaxWidth(Double.MAX_VALUE);
            content.getStyleClass().addAll("center");
        } else
            content = new VBox();


        this.page = page;

        Label title = new Label(page.name());
        title.getStyleClass().addAll("text-primary");


        content.getChildren().addAll(
                //icon,
                title);
        getChildren().add(content);
        setOnMouseClicked(event -> {
            SidebardService.navigate(page);
        });
    }

    public void setActive(Boolean b) {
        getStyleClass().remove("nav-item-active");

        if (b) {
            getStyleClass().add("nav-item-active");

        }
    }

    public Page getPage() {
        return page;
    }

    public Pane getContent() {
        return content;
    }
}
