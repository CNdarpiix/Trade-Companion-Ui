package layouts.main;

import components.fundation.TCDialog.TCDialog;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import navigation.Page;
import navigation.SidebarController;
import navigation.SidebardService;
import pages.dashboard.DashboardController;

import java.util.Stack;

public class MainLayoutController {
    @FXML
    private FlowPane dialogContent;

    @FXML
    private StackPane contentPane;

    @FXML
    private StackPane sidebar;

    private static VBox dialogVBox;

    @FXML
    public void initialize() {
        loadViews();
        loadDialogContent();
        SidebardService.setMainController(this);
    }


    private Parent dashboard;
    private Parent analysis;
    private Parent trade;
    private Parent journal;
    private Parent configuration;


    private void loadViews() {

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

    private void loadDialogContent() {
        dialogVBox = new VBox();
        dialogVBox.setMouseTransparent(true);
        dialogContent.getChildren().add(dialogVBox);
        dialogContent.setMouseTransparent(true);
        showPush("teste", "vla le teste");
    }

    public static void showPush(String mainText, String secondText) {
        TCDialog dialog = new TCDialog(mainText, secondText);
        dialogVBox.getChildren().add(dialog);

        PauseTransition pause = new PauseTransition(Duration.seconds(5.0));

        pause.setOnFinished(event -> dialogVBox.getChildren().remove(dialog));

        pause.play();
    }

    public void loadPage(Page page) {

        switch (page) {

            case DASHBOARD -> contentPane.getChildren().setAll(dashboard);

            case TRADE -> contentPane.getChildren().setAll(trade);

            case ANALYSIS -> contentPane.getChildren().setAll(analysis);

            case JOURNAL -> contentPane.getChildren().setAll(journal);

            case CONFIGURATION -> contentPane.getChildren().setAll(configuration);
        }
        SidebarController.setActivePage(page);
    }


}
