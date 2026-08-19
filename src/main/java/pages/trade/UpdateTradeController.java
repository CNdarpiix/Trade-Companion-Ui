package pages.trade;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class UpdateTradeController extends TradeController {
    @FXML
    private VBox contentVBox;

    @FXML
    private void initialize(){
        updateForm();
        fillForm(contentVBox);
    }


}
