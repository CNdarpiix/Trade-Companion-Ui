module com.charly.tradecompanion.trade_companion_ui {

    requires javafx.controls;
    requires javafx.fxml;

    exports com.charly.tradecompanion.trade_companion_ui;

    opens com.charly.tradecompanion.trade_companion_ui to javafx.fxml;
    opens com.charly.tradecompanion.trade_companion_ui.controller to javafx.fxml;
}