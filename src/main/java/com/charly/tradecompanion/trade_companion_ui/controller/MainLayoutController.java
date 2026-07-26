package com.charly.tradecompanion.trade_companion_ui.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

public class MainLayoutController {

    @FXML
    private StackPane contentPane ;



    private void loadView(String fxml) {

        try {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(fxml)
            );

            Parent view = loader.load();

            contentPane.getChildren().setAll(view);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void initialize() {
        loadView("/fxml/DashboardView.fxml");

    }

    @FXML
    private void DashboardChossed() {
        loadView("/fxml/DashboardView.fxml");
    }

    @FXML
    private void TradesChossed() {
        loadView("/fxml/TradeView.fxml");
    }

    @FXML
    private void JournalChossed() {
        loadView("/fxml/JournalView.fxml");
    }

    @FXML
    private void ConfigurationChossed() {
        loadView("/fxml/ConfigurationView.fxml");
    }
}
