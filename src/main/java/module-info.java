module com.charly.tradecompanion.trade_companion_ui {

    requires javafx.controls;
    requires javafx.fxml;

    requires static lombok;

    requires java.net.http;

    requires com.fasterxml.jackson.databind;

    exports com.charly.tradecompanion.trade_companion_ui;

    opens com.charly.tradecompanion.trade_companion_ui to javafx.fxml;
    opens com.charly.tradecompanion.trade_companion_ui.controller to javafx.fxml;



    opens com.charly.tradecompanion.trade_companion_ui.models.dashBoard
            to com.fasterxml.jackson.databind;

    opens com.charly.tradecompanion.trade_companion_ui.models.trade
            to com.fasterxml.jackson.databind;

    opens com.charly.tradecompanion.trade_companion_ui.models.table
            to com.fasterxml.jackson.databind;

    opens com.charly.tradecompanion.trade_companion_ui.models.criterion
            to com.fasterxml.jackson.databind;

    opens com.charly.tradecompanion.trade_companion_ui.models.evaluation
            to com.fasterxml.jackson.databind;

    opens com.charly.tradecompanion.trade_companion_ui.models
            to com.fasterxml.jackson.databind;

    opens com.charly.tradecompanion.trade_companion_ui.enums
            to com.fasterxml.jackson.databind;
}