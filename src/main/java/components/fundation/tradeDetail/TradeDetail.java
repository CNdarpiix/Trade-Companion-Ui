package components.fundation.tradeDetail;

import components.fundation.button.TCButtonType;
import components.fundation.button.TCButton;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Separator;
import javafx.scene.layout.*;
import layouts.main.MainLayoutController;
import model.trade.TradeResponse;
import pages.journal.JournalController;
import pages.trade.TradeApiService;
import util.ComponentsConfig;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import static pages.journal.JournalController.showOverlay;

public class TradeDetail extends VBox {
    private List<String> h3 = List.of("title-h3", "text-primary");
    private List<String> h4 = List.of("title-h4", "text-secondary");

    private VBox infoDetail;

    private HBox dateDetail;

    private VBox openedAt;

    private VBox closedAt;

    private HBox buttons;

    private final TradeApiService service = new TradeApiService();


    public TradeDetail(TradeResponse trade) {
        showDetails(trade);
    }

    private void showDetails(TradeResponse trade) {

        /// Edit button

        TCButton edit = new TCButton("EDIT", TCButtonType.OUTLINE);
        edit.setOnMouseClicked(event -> showUpdateForm(trade));


        TCButton closed = new TCButton("X", TCButtonType.OUTLINE);
        closed.setOnMouseClicked(event -> {
            showOverlay(null, null);
        });
        closed.setPrefSize(30, 30);

        Region region = new Region();
        HBox.setHgrow(region, Priority.ALWAYS);

        openedAt = ComponentsConfig.createLabel(trade.getOpenedAt().format(DateTimeFormatter.ISO_DATE), "OPENED AT", h3, h4);

        if (trade.getClosedAt() != null) {
            closedAt = ComponentsConfig.createLabel(trade.getOpenedAt().format(DateTimeFormatter.ISO_DATE), "CLOSED AT", h3, h4);
            dateDetail = new HBox(openedAt, region, closedAt);
        } else {
            dateDetail = new HBox(openedAt);
        }


        buttons = new HBox(edit, region, closed);


        infoDetail = new VBox(buttons, dateDetail);
        infoDetail.setSpacing(10);

        VBox symbol = ComponentsConfig.createLabel(trade.getSymbol(), "SYMBOL", h3, h4);
        VBox direction = ComponentsConfig.createLabel(trade.getDirection().name(), "DIRECTION", h3, h4);
        VBox status = ComponentsConfig.createLabel(trade.getStatus().name(), "STATUS", h3, h4);

        HBox primaryDetail = new HBox(symbol, direction, status);
        primaryDetail.setSpacing(10);


        HBox priceDetails = new HBox();
        priceDetails.setSpacing(10);

        priceDetails.getChildren().add(ComponentsConfig.createLabel(trade.getEntryPrice().toString(), "ENTRY", h3, h4));
        if (trade.getStopLoss() != null)
            priceDetails.getChildren().add(ComponentsConfig.createLabel(trade.getStopLoss().toString(), "STOPLOSS", h3, h4));
        if (trade.getTakeProfit() != null)
            priceDetails.getChildren().add(ComponentsConfig.createLabel(trade.getTakeProfit().toString(), "TAKEPROFIT", h3, h4));

        HBox profitDetails = new HBox();
        profitDetails.setSpacing(10);

        if (trade.getExitPrice() != null)
            profitDetails.getChildren().add(ComponentsConfig.createLabel(trade.getExitPrice().toString(), "EXIT", h3, h4));
        if (trade.getProfit() != null)
            profitDetails.getChildren().add(ComponentsConfig.createLabel(trade.getProfit().toString(), "PROFIT", h3, h4));

        getChildren().setAll(infoDetail, new Separator(), primaryDetail, new Separator(), priceDetails, new Separator(), profitDetails);

    }

    private void showUpdateForm(TradeResponse trade) {
        TCButton backButton = new TCButton("<", TCButtonType.OUTLINE);
        backButton.setOnMouseClicked(event -> {
            showDetails(trade);
        });

        TCButton deleteButton = new TCButton("DELETE TRADE", TCButtonType.OUTLINE);
        deleteButton.setOnMouseClicked(event -> {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

            alert.setTitle("Delete trade");
            alert.setHeaderText("Delete trade " + trade.getId() + "?");
            alert.setContentText("Are you sure you want to permanently delete this trade?");

            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                service.removeTrade(trade.getId());
                JournalController.refreshTrade();
                MainLayoutController.showPush(
                        "TRADE REMOVED",
                        "" + trade.getId()
                );
            }
        });

        Region region = new Region();
        HBox.setHgrow(region, Priority.ALWAYS);
        buttons.getChildren().setAll(backButton, region, deleteButton);
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
