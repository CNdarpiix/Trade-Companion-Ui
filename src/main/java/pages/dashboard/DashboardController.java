package pages.dashboard;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import model.DashBoardResponse;
import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import navigation.Page;
import navigation.SidebardService;
import util.ComponentsConfig;


import java.util.List;

public class DashboardController {
    private final DashboardApiService dashboardApiService = new DashboardApiService();
    private final SidebardService sidebardService = new SidebardService();

    @FXML
    private VBox cardsContainer;

    @FXML
    private VBox centerVBox;

    private final List<String> style = List.of("title-h3", "text-primary");


    private void initializeDashboard() {
        DashBoardResponse dashboard = dashboardApiService.getDashboard();
        setStats(dashboard);
        setQuickLinks();
        centerVBox.setPadding(new Insets(10, 30, 10, 30));


    }

    private void setStats(DashBoardResponse dashboard) {
//        try {
//            addProgressCard("Score Global", dashboard.getTotalScore());
//            // addProgressCard("Win Rate", dashboard.getWinRate());
//            // addProgressCard("Structure BUY", structureScore);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        VBox activity = ComponentsConfig.createProgressCard("TOTAL ACTIVITY", 100.0, style);
        VBox WR = ComponentsConfig.createProgressCard("WINRATE", 67.0, style);
        VBox PL = ComponentsConfig.createCard();
        PL.getChildren().addAll(
                ComponentsConfig.createLabel("P&L SCORE", style),
                ComponentsConfig.createSeparator(),
                ComponentsConfig.createLabel("45", List.of("title-h4", "text-secondary"))
        );

        FlowPane infoMenu = new FlowPane();

        infoMenu.setAlignment(Pos.CENTER);
        infoMenu.getChildren().addAll(activity, WR, PL);


        cardsContainer.getChildren().add(infoMenu);

    }

    private void setQuickLinks() {


        VBox analysis = newQuickLinks(Page.ANALYSIS);
        analysis.getChildren().addAll(
                ComponentsConfig.createLabel("ANALYSIS", style),
                ComponentsConfig.createLabel("Need to analyze?", List.of("title-h4", "text-secondary"))
        );


        VBox journal = newQuickLinks(Page.JOURNAL);
        journal.getChildren().addAll(
                ComponentsConfig.createLabel("JOURNAL", style),
                ComponentsConfig.createLabel("a good observation.", List.of("title-h4", "text-secondary"))
        );


        VBox stats = newQuickLinks(Page.TRADE);
        stats.getChildren().addAll(
                ComponentsConfig.createLabel("STATS", style),
                ComponentsConfig.createLabel("You can never have enough.", List.of("title-h4", "text-secondary"))
        );

        VBox configuration = newQuickLinks(Page.CONFIGURATION);
        configuration.getChildren().addAll(
                ComponentsConfig.createLabel("SETTINGS", style),
                ComponentsConfig.createLabel("Got a problem?", List.of("title-h4", "text-secondary"))
        );

        FlowPane quickLinks = new FlowPane(analysis, journal, stats, configuration);
        quickLinks.setAlignment(Pos.CENTER);

        cardsContainer.getChildren().add(quickLinks);
    }

    public VBox newQuickLinks(Page page) {
        VBox item = ComponentsConfig.createCard();
        item.setOnMouseClicked(event -> {
            SidebardService.navigate(page);
        });

        return item;
    }

    @FXML
    public void initialize() {
        initializeDashboard();
    }
}
