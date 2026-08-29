package pages.journal;

import components.fundation.button.TCButton;
import components.fundation.button.TCButtonType;
import components.fundation.tradeDetail.TradeDetail;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import pages.trade.TradeStatus;
import model.trade.TradeResponse;
import util.ComponentsConfig;

import java.util.List;

public class JournalController {

    private static final JournalApiService journalService = new JournalApiService();

    @FXML
    private VBox journalContent;

    private HBox headerPage;

    private Label titleLabel;

    private TCButton createTradeButton;

    private static VBox openedTrade;

    private static VBox closedTrade;

    @FXML
    private VBox overlayContent;

    private Parent createTradeView;

    public static Long actualTradeId;

    private static VBox overlayVBox;


    /// Add a trade to the journal
    private static void addTradeCard(TradeResponse trade) {
        if (trade.getStatus() == TradeStatus.OPEN) {
            openedTrade.getChildren().add(
                    ComponentsConfig.createTradeCard(trade)
            );
        } else {
            closedTrade.getChildren().add(
                    ComponentsConfig.createTradeCard(trade)
            );
        }
    }

    public static void showOverlay(Parent tradeDetail, Long tradeID) {
        if (tradeDetail == null)
            overlayVBox.getChildren().setAll();/// Utilisé pour fermer l'overlay / use to close the overlay
        else {
            actualTradeId = tradeID;
            overlayVBox.getChildren().setAll(tradeDetail);
        }
    }

    /// Refresh the list of trades
    public static void refreshTrade() {
        openedTrade.getChildren().clear();
        closedTrade.getChildren().clear();

        loadJournal();

        journalService.getAllTrades().forEach(JournalController::addTradeCard);
    }

    private static void loadJournal() {


        openedTrade.getChildren().addAll(ComponentsConfig.createSeparator(), ComponentsConfig.createLabel("TRADE OPENED    ", List.of("title-h2", "text-primary")));
        openedTrade.setSpacing(10);

        closedTrade.getChildren().addAll(ComponentsConfig.createSeparator(), ComponentsConfig.createLabel("TRADE CLOSED", List.of("title-h2", "text-primary")));
        closedTrade.setSpacing(10);
    }

    private void loadPage() {

        Region region = new Region();
        HBox.setHgrow(region, Priority.ALWAYS);

        TCButton createTradeButton = new TCButton("NEW TRADE", TCButtonType.OUTLINE);
        createTradeButton.setOnMouseClicked(event -> {
            showOverlay(createTradeView , 0L);
        });

        headerPage = new HBox(
                ComponentsConfig.createLabel("JOURNAL", List.of("title-h1", "text-primary")),
                region,
                createTradeButton
        );

        openedTrade = new VBox();
        closedTrade = new VBox();

        loadJournal();

        journalContent.getChildren().addAll(headerPage, openedTrade, closedTrade);
        journalContent.setPadding(new Insets(10));
        loadOverlay();
    }

    private void loadOverlay() {
        /// Overlay
        overlayContent.getStyleClass().add("card");

        overlayVBox = new VBox();
        overlayContent.getChildren().addAll(overlayVBox);

        /// Create Trade View initialize

        try {
            FXMLLoader tradeLoader = new FXMLLoader(
                    getClass().getResource("/pages/trade/CreateTradeView.fxml")
            );
            createTradeView = tradeLoader.load();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }


    @FXML
    private void initialize() {
        loadPage();

        refreshTrade();
    }


}
