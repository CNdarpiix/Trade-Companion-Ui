package pages.trade;


import components.fundation.input.TCInput;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import layouts.main.MainLayoutController;
import model.trade.CreateTradeRequest;
import model.trade.TradeResponse;
import model.trade.UpdateTradeRequest;
import pages.journal.JournalController;


public class CreateTradeController extends TradeController {
    @FXML
    private VBox contentVBox;


    @FXML
    private void initialize() {
        loadPage();
    }



    private void loadPage() {


//        TCButton openButton = new TCButton("open" , ButtonType.BUY );
//        TCButton closetButton = new TCButton("close" , ButtonType.SELL );

//        HBox status = new HBox(openButton , closetButton);


        contentVBox.getStyleClass().addAll("card", "tradeContent", "center");
        createForm();
        contentVBox = fillForm(contentVBox);


        createTradeButton.setOnMouseClicked(event -> {
            CreateTradeRequest request = new CreateTradeRequest();

            if (symbol.getText().isEmpty()) {
                symbol.isError(true);
            } else {
                request.setSymbol(symbol.getText());
                symbol.isError(false);
            }



            if (isLong)
                request.setDirection(TradeDirection.LONG);
            else
                request.setDirection(TradeDirection.SHORT);

            if (entryPrice.getText().isEmpty()) {
                entryPrice.isError(true);
            } else {
                request.setEntryPrice(Double.parseDouble(entryPrice.getText()));
                entryPrice.isError(false);
            }

            if (stopLoss.getText().isEmpty()) {
                stopLoss.isError(true);
            } else {
                stopLoss.isError(false);
                request.setStopLoss(Double.parseDouble(stopLoss.getText()));
            }

            if (takeProfit.getText().isEmpty())
                takeProfit.isError(true);
            else {
                takeProfit.isError(false);
                request.setTakeProfit(Double.parseDouble(takeProfit.getText()));
            }

            request.setOpeningNote(openingNote.getText());


            TradeResponse trade = tradeApiService.createTrade(request);
            JournalController.refreshTrade();
            MainLayoutController.showPush("New Trade Opened", "");
        });

    }


}
