package layouts.main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import navigation.Page;
import navigation.SidebarController;
import navigation.SidebardService;
import pages.dashboard.DashboardController;

public class MainLayoutController {

    @FXML
    private StackPane contentPane;

    @FXML
    private StackPane sidebar;

    private Parent dashboard;
    private Parent analysis;
    private Parent trade;
    private Parent journal;
    private Parent configuration;



    private void loadView() {

        try {
            FXMLLoader sidebarLoader = new FXMLLoader(
                    getClass().getResource("/navigation/sidebar.fxml")
            );
            Parent sidebarView = sidebarLoader.load();

            FXMLLoader dashboardLoader = new FXMLLoader(
                    getClass().getResource("/pages/dashboard/DashboardView.fxml")
            );
            dashboard = dashboardLoader.load();


            FXMLLoader analysisLoader = new FXMLLoader(
                    getClass().getResource("/pages/analysis/AnalysisView.fxml")
            );
            analysis = analysisLoader.load();


            FXMLLoader tradeLoader = new FXMLLoader(
                    getClass().getResource("/pages/trade/CreateTradeView.fxml")
            );
            trade = tradeLoader.load();


            FXMLLoader journalLoader = new FXMLLoader(
                    getClass().getResource("/pages/journal/JournalView.fxml")
            );
            journal = journalLoader.load();


            FXMLLoader configurationLoader = new FXMLLoader(
                    getClass().getResource("/pages/configuration/ConfigurationView.fxml")
            );
            configuration = configurationLoader.load();


            contentPane.getChildren().setAll(dashboard);
            sidebar.getChildren()
                    .setAll(sidebarView);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void loadPage(Page page) {

        switch (page) {

            case DASHBOARD ->
                    contentPane.getChildren().setAll(dashboard);

            case TRADE ->
                    contentPane.getChildren().setAll(trade);

            case ANALYSIS ->
                    contentPane.getChildren().setAll(analysis);

            case JOURNAL ->
                    contentPane.getChildren().setAll(journal);

            case CONFIGURATION ->
                    contentPane.getChildren().setAll(configuration);
        }
        SidebarController.setActivePage(page);
    }

    @FXML
    public void initialize() {
        loadView();
        SidebardService.setMainController(this);
    }


}
