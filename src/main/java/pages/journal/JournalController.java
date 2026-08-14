package pages.journal;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import pages.trade.TradeDirection;
import pages.trade.TradeStatus;
import model.trade.TradeResponse;
import util.ComponentsConfig;

import java.util.List;

public class JournalController {

    private static final JournalApiService journalService = new JournalApiService();

    @FXML
    private VBox journalContent;


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

        closedTrade = new VBox();
        closedTrade.getChildren().addAll(ComponentsConfig.createSeparator(), ComponentsConfig.createLabel("TRADE CLOSED", List.of("title-h2", "text-primary")));

        journalContent.getStyleClass().add("journalContent");
        journalContent.getChildren().addAll(titleLabel, openedTrade, closedTrade);
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
