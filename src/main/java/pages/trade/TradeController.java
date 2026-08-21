package pages.trade;

import components.fundation.button.ButtonType;
import components.fundation.button.TCButton;
import components.fundation.input.InputType;
import components.fundation.input.TCInput;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import org.w3c.dom.events.MouseEvent;

public abstract class TradeController {


    protected final TradeApiService tradeApiService = new TradeApiService();

    protected TCInput symbol;

    protected HBox direction;

    protected boolean isLong = true;

    protected HBox price;

    protected TCInput entryPrice;

    protected TCInput stopLoss;

    protected TCInput takeProfit;

    protected HBox profit;

    protected TCInput exitPrice;

    protected TCInput profitLoose;

    protected HBox note;

    protected TCInput openingNote;

    protected TCInput closingNote;

    protected TCButton createTradeButton;

    protected HBox buttons ;

    protected TCButton closeTradeButton ;

    protected TCButton updateTradeButton;

    protected VBox fillForm(VBox content) {
        if (profit != null)
            content.getChildren().addAll(symbol, direction, price, profit, note, buttons );
        else
            content.getChildren().addAll(symbol, direction, price, note, createTradeButton);
        return content;
    }


    protected void createForm() {
        symbol = new TCInput("Symbol", InputType.TEXT, "SYMBOL ERROR");
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
        Region region = new Region();
        HBox.setHgrow(region , Priority.ALWAYS);
        direction = new HBox(longButton, shortButton);
        direction.setSpacing(10);
        direction.getStyleClass().add("center");

        entryPrice = new TCInput("Entry price ", InputType.NUMBER, "Price error");
        stopLoss = new TCInput("stop loss  ", InputType.NUMBER, "SL ERROR");
        takeProfit = new TCInput("Take profit ", InputType.NUMBER, "TP ERROR");

        price = new HBox(entryPrice, stopLoss, takeProfit);

        openingNote = new TCInput("Opening note", InputType.TEXT, "");

        note = new HBox(openingNote);

        createTradeButton = new TCButton("New trade", ButtonType.OUTLINE);

    }


}
