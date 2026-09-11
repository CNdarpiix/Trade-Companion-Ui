package components.configuration;

import components.fundation.button.TCButton;
import components.fundation.button.TCButtonType;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
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
            TableEdit tableEdit = new TableEdit();
            contentBox.getChildren().setAll(tableEdit);


            ConfigurationController.getBackButton().setOnMouseClicked(event1 -> {
                contentBox.getChildren().setAll(refreshScrollList(), creaeteTable);
            });

            ConfigurationController.getBackButton().setVisible(true);

        });
        contentBox.getChildren().setAll(refreshScrollList(), creaeteTable);
    }



    private ScrollPane refreshScrollList() {

        List<TableResponse> tablelist = tableService.getAllTables();

        VBox scrollBox = new VBox();

        tablelist.forEach(table -> {

            VBox tableCard = ComponentsConfig.createTableCard(table);
            StackPane stackPane = new StackPane(tableCard);

            HBox overlay = createReadOrEditOverlay(table);
            overlay.setVisible(false);

            stackPane.getChildren().add(overlay);

            stackPane.setOnMouseEntered(event -> {
                overlay.setVisible(true);
            });

            stackPane.setOnMouseExited(event -> {
                overlay.setVisible(false);
            });

            scrollBox.getChildren().add(stackPane);
        });

        ScrollPane scrollPane = new ScrollPane(scrollBox);

        scrollPane.setFitToHeight(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        return scrollPane;
    }


    private HBox createReadOrEditOverlay(TableResponse tableResponse) {

        TCButton readButton = new TCButton("READ", TCButtonType.READOVERLAY);
        TCButton updateButton = new TCButton("UPDATE", TCButtonType.READOVERLAY);

        readButton.setOnMouseClicked(event -> {
            contentBox.getChildren().setAll(new TableEdit(tableResponse));
        });

        updateButton.setOnMouseClicked(event -> {
            contentBox.getChildren().setAll(new TableEdit(tableResponse));
        });

        HBox overlay = new HBox(10, readButton, updateButton);
        overlay.getStyleClass().add("read-overlay");

        return overlay;
    }



}