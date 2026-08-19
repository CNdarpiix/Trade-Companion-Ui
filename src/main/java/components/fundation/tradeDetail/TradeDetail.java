package components.fundation.tradeDetail;

import components.fundation.button.ButtonType;
import components.fundation.button.TCButton;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Separator;
import javafx.scene.layout.*;
import model.trade.TradeResponse;
import util.ComponentsConfig;

import java.time.format.DateTimeFormatter;
import java.util.List;

import static pages.journal.JournalController.showOverlay;

public class TradeDetail extends VBox {
    private List<String> h3 = List.of("title-h3", "text-primary");
    private List<String> h4 = List.of("title-h4", "text-secondary");

    public TradeDetail(TradeResponse trade) {
        showDetails(trade);
    }

    private void showDetails(TradeResponse trade) {
        VBox openedAt = ComponentsConfig.createLabel(trade.getOpenedAt().format(DateTimeFormatter.ISO_DATE), "OPENED AT", h3, h4);

        /// Edit button

        TCButton edit = new TCButton("EDIT", ButtonType.OUTLINE);
        edit.setOnMouseClicked(event -> showUpdateForm());


        TCButton closed = new TCButton("X", ButtonType.OUTLINE);
        closed.setOnMouseClicked(event -> {
            showOverlay(null);
        });
        closed.setPrefSize(30, 30);


        HBox infoDetail = new HBox(openedAt, edit, closed);
        infoDetail.setSpacing(10);

        VBox symbol = ComponentsConfig.createLabel(trade.getSymbol(), "SYMBOL", h3, h4);
        VBox direction = ComponentsConfig.createLabel(trade.getDirection().name(), "DIRECTION", h3, h4);
        VBox status = ComponentsConfig.createLabel(trade.getStatus().name(), "STATUS", h3, h4);

        HBox primaryDetail = new HBox(symbol, direction, status);
        primaryDetail.setSpacing(10);


        FlowPane priceDetails = new FlowPane();
        priceDetails.setHgap(10);

        priceDetails.getChildren().add(ComponentsConfig.createLabel(trade.getEntryPrice().toString(), "ENTRY", h3, h4));
        if (trade.getStopLoss() != null)
            priceDetails.getChildren().add(ComponentsConfig.createLabel(trade.getStopLoss().toString(), "STOPLOSS", h3, h4));
        if (trade.getTakeProfit() != null)
            priceDetails.getChildren().add(ComponentsConfig.createLabel(trade.getTakeProfit().toString(), "TAKEPROFIT", h3, h4));
        if (trade.getExitPrice() != null)
            priceDetails.getChildren().add(ComponentsConfig.createLabel(trade.getExitPrice().toString(), "EXIT", h3, h4));
        if (trade.getProfit() != null)
            priceDetails.getChildren().add(ComponentsConfig.createLabel(trade.getProfit().toString(), "PROFIT", h3, h4));

        getChildren().setAll(infoDetail, new Separator(), primaryDetail, new Separator(), priceDetails);

    }

    private void showUpdateForm() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/pages/trade/UpdateTradeView.fxml")
            );

            Parent updateForm = loader.load();
            getChildren().setAll(updateForm);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
