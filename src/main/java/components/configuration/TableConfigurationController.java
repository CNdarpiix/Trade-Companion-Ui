package components.configuration;

import components.fundation.button.TCButton;
import components.fundation.button.TCButtonType;
import components.fundation.card.Card;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.table.TableResponse;
import pages.configuration.ConfigurationApiService;
import pages.configuration.ConfigurationPage;
import util.ComponentsConfig;

import java.util.List;

public class TableConfigurationController {

    private final ConfigurationApiService tableService = new ConfigurationApiService();

    @FXML
    private HBox contentBox;

    @FXML
    private VBox scrollBox;

    @FXML
    public void initialize() {
        loadView();
    }



    public void loadView() {
        TCButton creaeteTable = new TCButton("New Table" , TCButtonType.OUTLINE);
        creaeteTable.setOnMouseClicked(event -> {
            contentBox.getChildren().setAll(new TableEdit());
        });
        contentBox.getChildren().add(creaeteTable);
        refreshScrollList();
    }



    private void refreshScrollList() {
        List<TableResponse> tablelist = tableService.getAllTables();
        scrollBox.getChildren().clear();


        if (!tablelist.isEmpty()) {
            tablelist.forEach(table -> {
                scrollBox.getChildren().add(ComponentsConfig.createTableCard(table));
            });
        }
    }

}