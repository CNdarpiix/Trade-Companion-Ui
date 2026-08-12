package pages.journal;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import pages.trade.TradeDirection;
import pages.trade.TradeStatus;
import model.trade.TradeResponse;
import util.ComponentsConfig;

import java.util.List;

public class JournalController {

    private final JournalApiService journalService = new JournalApiService();

    @FXML
    private VBox journalContent;


    private VBox openedTrade;


    private VBox closedTrade;


    private Label titleLabel;

    private void loadPage(List<TradeResponse> tradeList) {
        titleLabel = ComponentsConfig.createLabel("JOURNAL", List.of("title-h1", "text-primary"));

        openedTrade = new VBox();
        openedTrade.getChildren().addAll(ComponentsConfig.createSeparator() , ComponentsConfig.createLabel("TRADE OPEN" , List.of("title-h2" , "text-primary")));

        closedTrade = new VBox();
        closedTrade.getChildren().addAll(  ComponentsConfig.createSeparator() ,ComponentsConfig.createLabel("TRADE OPEN" , List.of("title-h2" , "text-primary")));


        tradeList
                .forEach(trade
                        -> {
                    if (trade.getStatus().equals(TradeStatus.OPEN)) {
                        openedTrade.getChildren().add(ComponentsConfig
                                .createTradeCard(trade));
                    } else {
                        closedTrade.getChildren().add(ComponentsConfig
                                .createTradeCard(trade));
                    }

                });


        journalContent.getStyleClass().add("journalContent");
        journalContent.getChildren().addAll(titleLabel, openedTrade, closedTrade);
    }


    @FXML
    private void initialize() {
        List<TradeResponse> tradeList = journalService.getAllTrades();

       /// Trade de test
        TradeResponse tradeTest = new TradeResponse();
        tradeTest.setStatus(TradeStatus.OPEN);
        tradeTest.setSymbol("XAUUSD");
        tradeTest.setDirection(TradeDirection.LONG);
        tradeTest.setEntryPrice(3333.00);
        tradeTest.setProfit(1212.12);


        tradeList.add(tradeTest);
       ///
        loadPage(tradeList);
    }
}
