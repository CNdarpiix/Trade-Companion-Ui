module com.charly.tradecompanion.trade_companion_ui {

    requires javafx.controls;
    requires javafx.fxml;

    requires static lombok;

    requires java.net.http;

    requires com.fasterxml.jackson.databind;
    requires java.smartcardio;
    requires java.sql;

    exports app;

    opens app to javafx.graphics, javafx.fxml;


    opens components.fundation.badge to javafx.fxml;
    opens components.fundation.button to javafx.fxml;
    opens components.fundation.card to javafx.fxml;
    opens components.fundation.chip to javafx.fxml;
    opens components.fundation.header to javafx.fxml;
    opens components.fundation.navitem to javafx.fxml;
    opens components.trading.progresscard to javafx.fxml;

    opens navigation to javafx.fxml;

    opens layouts.main to javafx.fxml;

    opens pages.dashboard to javafx.fxml;
    opens pages.trade to javafx.fxml , com.fasterxml.jackson.databind;
    opens pages.journal to javafx.fxml;
    opens pages.analysis to javafx.fxml , com.fasterxml.jackson.databind;
    opens pages.configuration to javafx.fxml;



    opens pages.configuration.models.evaluation to com.fasterxml.jackson.databind;
    opens pages.configuration.models.criterion to com.fasterxml.jackson.databind;
    opens pages.configuration.models.table to com.fasterxml.jackson.databind;


    opens model.criterion to com.fasterxml.jackson.databind;
    opens model.table to com.fasterxml.jackson.databind;
    opens model to com.fasterxml.jackson.databind;
    opens model.trade to com.fasterxml.jackson.databind, javafx.fxml;


}