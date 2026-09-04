package pages.configuration;

import components.fundation.button.TCButton;
import components.fundation.button.TCButtonType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class ConfigurationController {

    @FXML
    private HBox headerBox;

    @FXML
    private StackPane contentBox;

    private Parent tableSettings;

    private Parent criterionSettings;

    @FXML
    public void initialize() {
        loadContent();
        loadHeader();
    }

    private void loadHeader() {
        TCButton tableEdit = new TCButton("TABLES", TCButtonType.OUTLINE);
        tableEdit.setOnMouseClicked(event -> {
            contentBox.getChildren().setAll(tableSettings);
        });

        TCButton criterionEdit = new TCButton("CRITERIONS", TCButtonType.OUTLINE);
        criterionEdit.setOnMouseClicked(event -> {
            contentBox.getChildren().setAll(criterionSettings);
        });

        TCButton themeEdit = new TCButton("THEME", TCButtonType.OUTLINE);

        headerBox.getChildren().addAll(tableEdit, criterionEdit, themeEdit);
        headerBox.setAlignment(Pos.CENTER);
        headerBox.setSpacing(15);
        headerBox.setPadding(new Insets(20));
    }

    private void loadContent() {
        try {

            FXMLLoader tableConfigurationView = new FXMLLoader(getClass().getResource("/components/configuration/tableConfigurationView.fxml"));
            tableSettings = tableConfigurationView.load();

            FXMLLoader criterionConfigurationView = new FXMLLoader(getClass().getResource("/components/configuration/criterionConfigurationView.fxml"));
            criterionSettings = criterionConfigurationView.load();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
