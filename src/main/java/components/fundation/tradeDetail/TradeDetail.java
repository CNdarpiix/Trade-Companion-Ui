package components.fundation.tradeDetail;

import components.fundation.button.ButtonType;
import components.fundation.button.TCButton;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
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

    private VBox infoDetail;

    private HBox dateDetail;

    private VBox openedAt;

    private VBox closedAt;

    private HBox buttons ;

    public TradeDetail(TradeResponse trade) {
        showDetails(trade);
    }

    private void showDetails(TradeResponse trade) {

               /// Edit button

        TCButton edit = new TCButton("EDIT", ButtonType.OUTLINE);
        edit.setOnMouseClicked(event -> showUpdateForm(trade));


        TCButton closed = new TCButton("X", ButtonType.OUTLINE);
        closed.setOnMouseClicked(event -> {
            showOverlay(null , null);
        });
        closed.setPrefSize(30, 30);

        Region region = new Region();
        HBox.setHgrow(region , Priority.ALWAYS);

        openedAt = ComponentsConfig.createLabel(trade.getOpenedAt().format(DateTimeFormatter.ISO_DATE), "OPENED AT", h3, h4);

        if (trade.getClosedAt() != null) {
            closedAt = ComponentsConfig.createLabel(trade.getOpenedAt().format(DateTimeFormatter.ISO_DATE), "CLOSED AT", h3, h4);
            dateDetail = new HBox(openedAt,region,closedAt);
        }else{
            dateDetail = new HBox(openedAt);
        }



         buttons = new HBox(edit ,region, closed);


        infoDetail = new VBox(buttons, dateDetail);
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

    private void showUpdateForm(TradeResponse trade) {
        TCButton backButton = new TCButton("<", ButtonType.OUTLINE);
        backButton.setOnMouseClicked(event -> {
            showDetails(trade);
        });

        buttons.getChildren().setAll(backButton);
/// PRobelme avec la fléche bakc et tt

        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/pages/trade/UpdateTradeView.fxml")
            );

            Parent updateForm = loader.load();
            getChildren().setAll(infoDetail, updateForm);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
