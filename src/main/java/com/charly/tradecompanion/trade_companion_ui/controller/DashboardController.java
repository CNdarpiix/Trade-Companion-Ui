package com.charly.tradecompanion.trade_companion_ui.controller;

import com.charly.tradecompanion.trade_companion_ui.controller.component.trading.ProgressCardController;
import com.charly.tradecompanion.trade_companion_ui.models.dashBoard.DashBoardResponse;
import com.charly.tradecompanion.trade_companion_ui.service.DashboardApiService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class DashboardController {
    private final DashboardApiService dashboardApiService = new DashboardApiService();


    @FXML
    private FlowPane cardsContainer;


    private void initializeDashboard() {
        DashBoardResponse dashboard = dashboardApiService.getDashboard();

        setStats(dashboard);
    }

    private void setStats(DashBoardResponse dashboard) {
//        try {
//            addProgressCard("Score Global", dashboard.getTotalScore());
//            // addProgressCard("Win Rate", dashboard.getWinRate());
//            // addProgressCard("Structure BUY", structureScore);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        for (int i = 0; i < 10; i++)
            addCard("W | R", "100 %");

    }

    private void addProgressCard(String title, Double value) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/fxml/component/trading/ProgressCard.fxml")
        );

        Node card = loader.load();

        ProgressCardController controller = loader.getController();
        controller.setStat(title, value, "");

        cardsContainer.getChildren().add(card);
    }

    private void addCard(String info, String value) {
        VBox card = new VBox();
        card.getStyleClass().add("card");

        Label infoTitle = new Label(info);
        infoTitle.getStyleClass().addAll("title-h3", "text-primary");

        Label valueT = new Label(value);
        valueT.getStyleClass().addAll("value", "text-primary");

        card.getChildren().addAll(infoTitle, valueT);

        cardsContainer.getChildren().add(card);
        card.prefWidthProperty().bind(
                cardsContainer.widthProperty().divide(5)
        );
    }

    @FXML
    public void initialize() {
        initializeDashboard();
    }
}
