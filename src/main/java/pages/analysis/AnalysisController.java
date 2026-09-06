package pages.analysis;

import components.fundation.card.Card;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import model.DashBoardResponse;
import model.TimeFrame;
import model.criterion.CriterionDashboardResponse;
import model.table.TableDashboardResponse;
import model.evaluation.EvaluationResponse;
import pages.dashboard.DashboardApiService;
import util.ComponentsConfig;

import java.util.ArrayList;
import java.util.List;


public class AnalysisController {

    @FXML
    private VBox analysisContent;

    private Card dashboardContent;

    private Card analysisTable;

    private DashBoardResponse dashboard;


    @FXML
    private void initialize() {


        DashboardApiService dashboardApi = new DashboardApiService();
        dashboard = dashboardApi.getDashboard();
        List<TableDashboardResponse> tables = new ArrayList<>(dashboard.getTables().stream().filter(table -> !table.getCriteria().isEmpty()).toList());

        loadPage(tables);
    }

    private void loadPage(List<TableDashboardResponse> tables) {

        dashboardContent = new Card();
        dashboardContent.getChildren().add(ComponentsConfig.createLabel("Analysis dashboard", List.of("title-h3", "text-primary")));
        dashboardContent.getChildren().add(ComponentsConfig.createLabel("53 %", List.of("value", "text-primary")));

        analysisTable = new Card();
        analysisTable.getChildren().add(ComponentsConfig.createLabel("Analysis ", List.of("title-h1", "text-primary")));

        if (!tables.isEmpty())
            tables.forEach(table -> analysisTable.getChildren().add(createTable(table)));

        analysisContent.getChildren().addAll(dashboardContent, analysisTable);


    }

    private GridPane createTable(TableDashboardResponse table) {
        GridPane tableGrid = new GridPane((table.getCriteria().getFirst().getEvaluations().size()) + 1, (table.getCriteria().size()) + 1);

        int v = 1;
        for (CriterionDashboardResponse criterion : table.getCriteria()) {
            tableGrid.add(ComponentsConfig.createLabel(criterion.getName(), List.of("title-h3", "text-primary")), 0, v);
            v++;
        }


        int h = 1;
        for (TimeFrame tf : table.getTimeFrames()) {
            tableGrid.add(ComponentsConfig.createLabel(tf.getName(), List.of("title-h3", "text-primary")), h, 0);
            h++;
        }
        return tableGrid;
    }


}

