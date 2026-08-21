package pages.journal;

import components.fundation.button.ButtonType;
import components.fundation.button.TCButton;
import components.fundation.tradeDetail.TradeDetail;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import pages.trade.TradeDirection;
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

    private VBox openedTrade;

    private VBox closedTrade;

    private Label titleLabel;

    private static final ObservableList<TradeResponse> tradeList =
            FXCollections.observableArrayList();

    private static final ListProperty<TradeResponse> tradeListProperty =
            new SimpleListProperty<>(tradeList);

    private final ListChangeListener<TradeResponse> tradesListener = change -> {

        while (change.next()) {

            if (change.wasAdded()) {
                for (TradeResponse trade : change.getAddedSubList()) {

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
            }

            if (change.wasRemoved()) {

                for (TradeResponse trade : change.getRemoved()) {

                    VBox container;

                    if (trade.getStatus() == TradeStatus.OPEN) {
                        container = openedTrade;
                    } else {
                        container = closedTrade;
                    }

                    container.getChildren().removeIf(node ->
                            trade.getId().equals(node.getUserData())
                    );
                }
            }
        }
    };


    private void loadPage() {


        titleLabel = ComponentsConfig.createLabel("JOURNAL", List.of("title-h1", "text-primary"));


        openedTrade = new VBox();
        openedTrade.getChildren().addAll(ComponentsConfig.createSeparator(), ComponentsConfig.createLabel("TRADE OPENED    ", List.of("title-h2", "text-primary")));
        openedTrade.setSpacing(10);

        closedTrade = new VBox();
        closedTrade.getChildren().addAll(ComponentsConfig.createSeparator(), ComponentsConfig.createLabel("TRADE CLOSED", List.of("title-h2", "text-primary")));
        closedTrade.setSpacing(10);

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
        tradeListProperty.setAll(
                journalService.getAllTrades()
        );
    }

    @FXML
    private void initialize() {
        loadPage();

        tradeListProperty.addListener(tradesListener);

        tradeListProperty.setAll(
                journalService.getAllTrades()
        );
    }
}
