package components.configuration;

import components.fundation.button.TCButton;
import components.fundation.button.TCButtonType;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.table.TableResponse;
import pages.configuration.ConfigurationApiService;
import pages.configuration.ConfigurationController;
import util.ComponentsConfig;

import java.util.List;

public class TableConfigurationController {

    private final ConfigurationApiService tableService = new ConfigurationApiService();

    @FXML
    private HBox contentBox;


    @FXML
    public void initialize() {
        loadView();
    }



    public void loadView() {

        TCButton creaeteTable = new TCButton("New Table" , TCButtonType.OUTLINE);

        creaeteTable.setOnMouseClicked(event -> {
            contentBox.getChildren().setAll(new TableEdit());

            ConfigurationController.getBackButton().setOnMouseClicked(event1 -> {
                contentBox.getChildren().setAll(refreshScrollList(), creaeteTable);
            });

            ConfigurationController.getBackButton().setVisible(true);

        });
        contentBox.getChildren().addAll(creaeteTable , refreshScrollList());
    }



    private ScrollPane refreshScrollList() {
        List<TableResponse> tablelist = tableService.getAllTables();

        VBox scrollBox = new VBox();

        if (!tablelist.isEmpty()) {
            tablelist.forEach(table -> {
                scrollBox.getChildren().add(ComponentsConfig.createTableCard(table));
            });
        }

        ScrollPane scrollPane = new ScrollPane(scrollBox);

        scrollPane.setFitToHeight(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        return scrollPane;
    }

}