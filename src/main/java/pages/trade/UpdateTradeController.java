package pages.trade;

import components.fundation.button.ButtonType;
import components.fundation.button.TCButton;
import components.fundation.input.InputType;
import components.fundation.input.TCInput;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import layouts.main.MainLayoutController;
import model.trade.TradeResponse;
import model.trade.UpdateTradeRequest;
import pages.journal.JournalController;
import util.ComponentsConfig;

public class UpdateTradeController extends TradeController {
    @FXML
    private VBox contentVBox;

    private long tradeId;

    @FXML
    private void initialize() {
        updateForm();
        fillForm(contentVBox);
    }

    protected void updateForm() {
        createForm();
        exitPrice = new TCInput("Exit price", InputType.NUMBER, "EXIT PRICE ERROR");
        profitLoose = new TCInput("Profit or Loose ", InputType.NUMBER, "PL ERROR");

        profit = new HBox(exitPrice, profitLoose);

        closingNote = new TCInput("Closing note", InputType.TEXT, "");

        note = new HBox(openingNote, closingNote);

        closeTradeButton = new TCButton("CLOSE TRADE", ButtonType.OUTLINE);

        updateTradeButton = new TCButton("UPDATE TRADE", ButtonType.OUTLINE);
        updateTradeButton.setOnMouseClicked(event -> {
            UpdateTradeRequest request = updateTrade();
            TradeResponse trade = tradeApiService.updateTrade(request , JournalController.actualTradeId);
            JournalController.refreshTrade();
            MainLayoutController.showPush("Trade updated" , JournalController.actualTradeId.toString());
        });


        Region region = new Region();
        HBox.setHgrow(region, Priority.ALWAYS);
        buttons = new HBox(updateTradeButton, region, closeTradeButton);
        /// faire le fill des différents champs si possible , adapter le bouton crée trade -> modifier trade

    }


    private UpdateTradeRequest updateTrade() {
        UpdateTradeRequest request = new UpdateTradeRequest();
        ///SYMBOL
        if (!symbol.getText().isEmpty())
            request.setSymbol(symbol.getText());

        ///DIRECTION
        if (isLong)
            request.setDirection(TradeDirection.LONG);
        else
            request.setDirection(TradeDirection.SHORT);

        ///ENTRY PRICE
        if (!entryPrice.getText().isEmpty())
            request.setEntryPrice(Double.parseDouble(entryPrice.getText()));
        ///STOPLOSS
        if (!stopLoss.getText().isEmpty())
            request.setStopLoss(Double.parseDouble(stopLoss.getText()));
        ///TAKEPROFIT
        if (!takeProfit.getText().isEmpty())
            request.setTakeProfit(Double.parseDouble(takeProfit.getText()));

        ///EXITPRICE
        if (!exitPrice.getText().isEmpty())
            request.setExitPrice(Double.parseDouble(exitPrice.getText()));
        ///PROFIT
        if (!profitLoose.getText().isEmpty())
            request.setProfit(Double.parseDouble(profitLoose.getText()));

        ///OPENING NOTE
        request.setOpeningNote(openingNote.getText());
        ///CLOSING NOTE
        request.setClosingNote(closingNote.getText());

        return request;
    }

///   TradeResponse trade = tradeApiService.createTrade(request);
///             JournalController.refreshTrade();
///             MainLayoutController.showPush("New Trade Opened", "");
}
