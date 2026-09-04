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
//        if (!dashboard.getTables().isEmpty())
//            loadPage(dashboard.getTables().getFirst());
///Filtrer les tables n'ayant pas de criterion
        loadPage(test());

        /// Faire un GEt de tableDashboardResponse et CriterionDashboardResponse
    }

    private void loadPage(TableDashboardResponse table) {

        dashboardContent = new Card();
        dashboardContent.getChildren().add(ComponentsConfig.createLabel("Analysis dashboard", List.of("title-h3", "text-primary")));
        dashboardContent.getChildren().add(ComponentsConfig.createLabel("53 %", List.of("value", "text-primary")));

        analysisTable = new Card();
        analysisTable.getChildren().add(ComponentsConfig.createLabel("Analysis ", List.of("title-h1", "text-primary")));

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


        analysisTable.getChildren().add(tableGrid);
        analysisContent.getChildren().addAll(dashboardContent, analysisTable);


    }


    private TableDashboardResponse test() {
        TimeFrame tm1 = new TimeFrame();
        tm1.setCoefficient(1.0);
        tm1.setName("1 minute");
        tm1.setId(1L);

        EvaluationResponse evaluation = new EvaluationResponse();
        evaluation.setCriterionId(1L);
        evaluation.setTimeFrameId(1L);

        CriterionDashboardResponse criterion = new CriterionDashboardResponse();
        criterion.setId(1L);
        criterion.setName("Structure");
        criterion.setEvaluations(List.of(evaluation));

        TableDashboardResponse table = new TableDashboardResponse();
        table.setCriteria(List.of(criterion));
        table.setTimeFrames(List.of(tm1));

        return table;
    }
}

