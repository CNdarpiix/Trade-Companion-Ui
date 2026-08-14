package pages.trade;

import components.fundation.button.ButtonType;
import components.fundation.button.TCButton;
import components.fundation.input.InputType;
import components.fundation.input.TCInput;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.trade.CreateTradeRequest;
import model.trade.TradeResponse;
import pages.journal.JournalController;


public class CreateTradeController {
    @FXML
    private VBox contentVBox;

    private boolean isLong = true;

    private final TradeApiService tradeApiService = new TradeApiService();

    @FXML
    private void initialize() {
        loadPage();
    }

    private void loadPage() {

        TCInput symbolInput = new TCInput("Symbol", InputType.TEXT, "");

        TCButton longButton = new TCButton("long", ButtonType.BUY);
        TCButton shortButton = new TCButton("short", ButtonType.OUTLINE);

        longButton.setOnMouseClicked(event -> {
            if (!isLong) {
                isLong = true;
                shortButton.setType(ButtonType.OUTLINE);
                longButton.setType(ButtonType.BUY);
            }
        });
        shortButton.setOnMouseClicked(event -> {
            if (isLong) {
                isLong = false;
                shortButton.setType(ButtonType.SELL);
                longButton.setType(ButtonType.OUTLINE);
            }
        });

        HBox direction = new HBox(longButton, shortButton);
        direction.setSpacing(10);
        direction.getStyleClass().add("center");

//        TCButton openButton = new TCButton("open" , ButtonType.BUY );
//        TCButton closetButton = new TCButton("close" , ButtonType.SELL );

//        HBox status = new HBox(openButton , closetButton);

        TCInput entryInput = new TCInput("Entry price ", InputType.NUMBER, "Price error");
        TCInput SLInput = new TCInput("stop loss  ", InputType.NUMBER, "");
        TCInput TPInput = new TCInput("Take profit ", InputType.NUMBER, "");

        HBox price = new HBox(entryInput, SLInput, TPInput);


//        TCInput exitInput = new TCInput("Exit price ", InputType.NUMBER , "EXIT PRICE ERROR");
//        TCInput profitInput = new TCInput("Profit / Loose ", InputType.NUMBER , "PL ERROR");
//
//        HBox profit = new HBox(exitInput , profitInput);

        TCInput openingNote = new TCInput("Opening note", InputType.TEXT, "");
        //TCInput closingNote = new TCInput("Closing note" , InputType.TEXT , "");

        TCButton createTradeButton = new TCButton("New trade", ButtonType.OUTLINE);
        createTradeButton.setOnMouseClicked(event -> {
            CreateTradeRequest request = new CreateTradeRequest();

            if (symbolInput.getText().isEmpty()) {
                symbolInput.isError(true);
            } else {
                request.setSymbol(symbolInput.getText());
                symbolInput.isError(false);
            }

            if (isLong)
                request.setDirection(TradeDirection.LONG);
            else
                request.setDirection(TradeDirection.SHORT);

            if (entryInput.getText().isEmpty()) {
                entryInput.isError(true);
            } else {
                request.setEntryPrice(Double.parseDouble(entryInput.getText()));
                entryInput.isError(false);
            }

            if (SLInput.getText().isEmpty()) {
                SLInput.isError(true);
            } else {
                SLInput.isError(false);
                request.setStopLoss(Double.parseDouble(SLInput.getText()));
            }

            if (TPInput.getText().isEmpty())
                TPInput.isError(true);
            else {
                TPInput.isError(false);
                request.setTakeProfit(Double.parseDouble(TPInput.getText()));

            }

            request.setOpeningNote(openingNote.getText());

            TradeResponse trade = tradeApiService.createTrade(request);
            JournalController.refreshTrade();
        });

        contentVBox.getStyleClass().addAll("card", "tradeContent", "center");
        contentVBox.getChildren().addAll(symbolInput, direction, price, openingNote, createTradeButton);
        //   contentVBox.getChildren().addAll(symbolInput , direction , status , price , profit , openingNote , closingNote , createTradeButton);
    }


}
