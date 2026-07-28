package com.charly.tradecompanion.trade_companion_ui.controller;

import com.charly.tradecompanion.trade_companion_ui.controller.component.ProgressCardController;
import com.charly.tradecompanion.trade_companion_ui.models.dashBoard.DashBoardResponse;
import com.charly.tradecompanion.trade_companion_ui.service.DashboardApiService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class DashboardController {
    private final DashboardApiService dashboardApiService = new DashboardApiService();


    @FXML
    private VBox cardsContainer;



    private void initializeDashboard() {
        DashBoardResponse dashboard = dashboardApiService.getDashboard();

        setStats(dashboard);
    }

    private void setStats(DashBoardResponse dashboard) {
        try {
            addProgressCard("Score Global", dashboard.getTotalScore());
            // addProgressCard("Win Rate", dashboard.getWinRate());
            // addProgressCard("Structure BUY", structureScore);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void addProgressCard(String title, Double value) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/fxml/component/ProgressCard.fxml")
        );

        Node card = loader.load();

        ProgressCardController controller = loader.getController();
        controller.setStat(title, value, "");

        cardsContainer.getChildren().add(card);
    }

    @FXML
    public void initialize() {
        initializeDashboard();
    }
}
