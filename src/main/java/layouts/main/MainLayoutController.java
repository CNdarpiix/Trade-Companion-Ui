package layouts.main;

import components.fundation.navitem.NavItem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class MainLayoutController {

    @FXML
    private StackPane contentPane;

    @FXML
    private VBox sidebar;

    @FXML
    private VBox logoBox;

    @FXML
    private VBox navigation;


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

    private void setNav(){
        navigation.getChildren().add(new NavItem("Dashboard"
                //, dashboardIcon ( mettre les icons
                ));
        navigation.getChildren().add(new NavItem("Trades"
                //, tradesIcon
                ));
        navigation.getChildren().add(new NavItem("Journal"
                //, journalIcon
        ));
        navigation.getChildren().add(new NavItem("Configuration"
                //, configIcon
                ));

    }

    @FXML
    public void initialize() {
        loadView("/pages/dashboard/DashboardView.fxml");
        setNav();
    }


}
