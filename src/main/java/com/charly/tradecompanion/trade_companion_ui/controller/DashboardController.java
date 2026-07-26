package com.charly.tradecompanion.trade_companion_ui.controller;

import com.charly.tradecompanion.trade_companion_ui.models.dashBoard.DashBoardResponse;
import com.charly.tradecompanion.trade_companion_ui.service.DashboardApiService;
import javafx.fxml.FXML;

public class DashboardController {
    private final DashboardApiService dashboardApiService = new DashboardApiService();

    @FXML
    public void initialize(){
        DashBoardResponse dashboard = dashboardApiService.getDashboard();
        System.out.println(dashboard.getTotalScore());
    }
}
