package pages.configuration;

import components.fundation.button.TCButton;
import components.fundation.button.TCButtonType;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class ConfigurationController {

    @FXML
    private HBox headerBox;

    @FXML
    private StackPane contentBox;

    @FXML
    public void initialize() {
        loadContent();
    }

    private void loadContent(){
        TCButton tableEdit = new TCButton("TABLES" , TCButtonType.OUTLINE);

        TCButton criterionEdit = new TCButton("CRITERIONS" , TCButtonType.OUTLINE);

        TCButton themeEdit = new TCButton("THEME" , TCButtonType.OUTLINE);

        headerBox.getChildren().addAll(tableEdit , criterionEdit , themeEdit);
        headerBox.setAlignment(Pos.CENTER);
        headerBox.setSpacing(15);
        headerBox.setPadding(new Insets(20));
    }

}
