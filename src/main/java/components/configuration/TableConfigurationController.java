package components.configuration;

import components.fundation.card.Card;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.table.TableResponse;
import pages.configuration.ConfigurationApiService;
import util.ComponentsConfig;

import java.util.List;

public class TableConfigurationController {

    private final ConfigurationApiService tableService = new ConfigurationApiService();

    @FXML
    private HBox scrollBox;

    @FXML
    public void initialize() {
        loadView();
    }

    ;

    public void loadView() {


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