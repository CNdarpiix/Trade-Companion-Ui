package pages.trade;

import components.fundation.button.ButtonType;
import components.fundation.button.TCButton;
import components.fundation.input.InputType;
import components.fundation.input.TCInput;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CreateTradeController {
    @FXML
    private VBox contentVBox;

    @FXML
    private void initialize() {
        loadPage();
    }

    private void loadPage() {

        TCInput symbolInput = new TCInput("Symbol", InputType.TEXT, "SYMBOL ERROR");

        TCButton longButton = new TCButton("long" , ButtonType.BUY );
        TCButton shortButton = new TCButton("short" , ButtonType.SELL );

        HBox direction = new HBox(longButton , shortButton);

        TCButton openButton = new TCButton("open" , ButtonType.BUY );
        TCButton closetButton = new TCButton("close" , ButtonType.SELL );

        HBox status = new HBox(openButton , closetButton);

        TCInput entryInput = new TCInput("Entry price ", InputType.NUMBER, "PRICE ERROR");
        TCInput SLInput = new TCInput("stop loss  ", InputType.NUMBER, "SL ERROR");
        TCInput TPInput = new TCInput("Take profit ", InputType.NUMBER, "TP ERROR");

        HBox price = new HBox(entryInput ,SLInput , TPInput);


        TCInput exitInput = new TCInput("Exit price ", InputType.NUMBER , "EXIT PRICE ERROR");
        TCInput profitInput = new TCInput("Profit / Loose ", InputType.NUMBER , "PL ERROR");

        HBox profit = new HBox(exitInput , profitInput);

        TCInput openingNote = new TCInput("Opening note" , InputType.TEXT , "");
        TCInput closingNote = new TCInput("Closing note" , InputType.TEXT , "");


        contentVBox.getStyleClass().addAll("card" , "tradeContent");
        contentVBox.getChildren().addAll(symbolInput , direction , status , price , profit , openingNote , closingNote);
    }

}
