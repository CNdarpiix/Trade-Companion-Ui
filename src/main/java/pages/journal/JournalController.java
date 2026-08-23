package pages.journal;

import components.fundation.tradeDetail.TradeDetail;
import javafx.fxml.FXML;
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

    @FXML
    private VBox overlayContent;

    public static Long actualTradeId;

    private static VBox overlayVBox;

    private static VBox openedTrade;

    private static VBox closedTrade;

    private Label titleLabel;


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


    private void loadPage() {


        titleLabel = ComponentsConfig.createLabel("JOURNAL", List.of("title-h1", "text-primary"));

        openedTrade = new VBox();
        closedTrade = new VBox();
        loadJournal();

        journalContent.getStyleClass().add("journalContent");
        journalContent.getChildren().addAll(titleLabel, openedTrade, closedTrade);

        loadOverlay();
    }

    private void loadOverlay() {

        overlayContent.getStyleClass().add("card");


        overlayVBox = new VBox();
        overlayContent.getChildren().addAll(overlayVBox);
    }

    public static void showOverlay(TradeDetail tradeDetail, Long tradeID) {
        if (tradeDetail == null)
            overlayVBox.getChildren().setAll();/// Utilisé pour fermer l'overlay / use to close the overlay
        else {
            actualTradeId = tradeID;
            overlayVBox.getChildren().setAll(tradeDetail);
        }
    }


    public static void refreshTrade() {
        openedTrade.getChildren().clear();
        closedTrade.getChildren().clear();

        loadJournal();

        journalService.getAllTrades().forEach(JournalController::addTradeCard);
    }

    @FXML
    private void initialize() {
        loadPage();

        refreshTrade();
    }

    private static void loadJournal() {


        openedTrade.getChildren().addAll(ComponentsConfig.createSeparator(), ComponentsConfig.createLabel("TRADE OPENED    ", List.of("title-h2", "text-primary")));
        openedTrade.setSpacing(10);

        closedTrade.getChildren().addAll(ComponentsConfig.createSeparator(), ComponentsConfig.createLabel("TRADE CLOSED", List.of("title-h2", "text-primary")));
        closedTrade.setSpacing(10);
    }

}
