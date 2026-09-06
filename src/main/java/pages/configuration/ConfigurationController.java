package pages.configuration;

import components.fundation.button.TCButton;
import components.fundation.button.TCButtonType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import lombok.Getter;
import util.ComponentsConfig;

import java.util.List;

public class ConfigurationController {

    @FXML
    private VBox headerBox;

    @FXML
    private StackPane contentBox;

    private Parent tableSettings;

    private Parent criterionSettings;

    private HBox buttonsBox;

    private HBox titleBox;

    private Label title;

    @Getter
    private static TCButton backButton;

    @FXML
    public void initialize() {
        loadContent();
        loadHeader();
    }

    private void loadHeader() {

        TCButton tableEdit = new TCButton("TABLES", TCButtonType.OUTLINE);
        tableEdit.setOnMouseClicked(event -> {
            contentBox.getChildren().setAll(tableSettings);
            title.setText("TABLE");
        });

        TCButton criterionEdit = new TCButton("CRITERIONS", TCButtonType.OUTLINE);
        criterionEdit.setOnMouseClicked(event -> {
            contentBox.getChildren().setAll(criterionSettings);
            title.setText("CRITERION");
        });

        TCButton themeEdit = new TCButton("THEME", TCButtonType.OUTLINE);

        buttonsBox = new HBox(tableEdit, criterionEdit, themeEdit);
        buttonsBox.setAlignment(Pos.CENTER);
        buttonsBox.setSpacing(15);
        buttonsBox.setPadding(new Insets(20));

        title = ComponentsConfig.createLabel("SETTINGS" , List.of("title-h1","text-primary"));

        backButton = new TCButton("<", TCButtonType.OUTLINE);
        backButton.setVisible(false);

        Region region = new Region();
        HBox.setHgrow(region, Priority.ALWAYS);
        titleBox = new HBox(title, region, backButton);

        headerBox.getChildren().addAll(buttonsBox, ComponentsConfig.createSeparator(), titleBox);
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
