package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TradeCompanionApplication extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader MainLoader = new FXMLLoader(
                TradeCompanionApplication.class.getResource("/layouts/main/MainLayout.fxml")
        );

        FXMLLoader DashboardLoader = new FXMLLoader(
                TradeCompanionApplication.class.getResource("/pages/dashboard/DashboardView.fxml")
        );

        Scene MainScene = new Scene(MainLoader.load());

        MainScene.getStylesheets().add(
                TradeCompanionApplication.class
                        .getResource("/layouts/main/theme-dark.css")
                        .toExternalForm());

        MainScene.getStylesheets().add(
                TradeCompanionApplication.class
                        .getResource("/layouts/main/global.css")
                        .toExternalForm());
        MainScene.getStylesheets().add(
                TradeCompanionApplication.class
                        .getResource("/components/fundation/card/card.css")
                        .toExternalForm());
        MainScene.getStylesheets().add(
                TradeCompanionApplication.class
                        .getResource("/components/fundation/sidebar/sideBar.css")
                        .toExternalForm());
        //contentPane.getChildren().add(DashboardScene);

        primaryStage.setTitle("Trade Companion");

        primaryStage.setScene(MainScene);

        primaryStage.show();
    }
}
