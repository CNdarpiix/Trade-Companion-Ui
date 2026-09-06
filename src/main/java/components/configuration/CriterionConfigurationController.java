package components.configuration;

import components.fundation.button.TCButton;
import components.fundation.button.TCButtonType;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import layouts.main.MainLayoutController;
import model.criterion.CriterionResponse;
import model.table.TableResponse;
import pages.configuration.ConfigurationApiService;
import pages.configuration.ConfigurationController;
import util.ComponentsConfig;

import java.util.ArrayList;
import java.util.List;

public class CriterionConfigurationController {

    private final ConfigurationApiService service = new ConfigurationApiService();

    @FXML
    private HBox contentBox;

    @FXML
    public void initialize() {
        loadview();
    }

    public void loadview() {

        TCButton createCriterion = new TCButton("New Criterion", TCButtonType.OUTLINE);


        createCriterion.setOnMouseClicked(event -> {

            if (service.getAllTables().isEmpty())
                MainLayoutController.createPush("Please make sure to have a table to create a Criterion", "");

            else {
                contentBox.getChildren().setAll(new CriterionEdit(service.getAllTables().stream().map(TableResponse::getName).toList()));

                ConfigurationController.getBackButton().setOnMouseClicked(event1 -> {
                    contentBox.getChildren().setAll(refreshCriterionList(), createCriterion);
                });

                ConfigurationController.getBackButton().setVisible(true);

            }
        });

        contentBox.getChildren().addAll(refreshCriterionList() , createCriterion);


    }

    public ScrollPane refreshCriterionList() {
        List<CriterionResponse> criterionList = service.getAllCriterion();

        VBox scrollBox = new VBox();

        if (!criterionList.isEmpty()) {
            criterionList.forEach(criterion -> {
                TableResponse table = service.getTableById(criterion.getTable());
                scrollBox.getChildren().add(ComponentsConfig.createCriterionCard(criterion, table.getName()));
            });
        }
        ScrollPane scrollPane = new ScrollPane(scrollBox);

        scrollPane.setFitToHeight(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        return scrollPane;
    }
}
