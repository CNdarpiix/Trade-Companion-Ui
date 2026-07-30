module com.charly.tradecompanion.trade_companion_ui {

    requires javafx.controls;
    requires javafx.fxml;

    requires static lombok;

    requires java.net.http;

    requires com.fasterxml.jackson.databind;

    exports app;

    opens app to javafx.graphics, javafx.fxml;


    opens components.fundation.badge to javafx.fxml;
    opens components.fundation.button to javafx.fxml;
    opens components.fundation.card to javafx.fxml;
    opens components.fundation.chip to javafx.fxml;
    opens components.fundation.header to javafx.fxml;
    opens components.fundation.navitem to javafx.fxml;
    opens components.fundation.slidebar to javafx.fxml;
    opens components.trading.progresscard to javafx.fxml;

    opens layouts.main to javafx.fxml;

    opens pages.dashboard to javafx.fxml;
    opens pages.trade to javafx.fxml;

    opens config.models.evaluation to com.fasterxml.jackson.databind;
    opens config.models.criterion to com.fasterxml.jackson.databind;
    opens config.models.table to com.fasterxml.jackson.databind;

    opens pages.dashboard.models to com.fasterxml.jackson.databind;
    opens pages.trade.models to com.fasterxml.jackson.databind;

    opens pages.analysis to com.fasterxml.jackson.databind;


}