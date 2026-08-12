package navigation;

import components.fundation.navitem.NavItem;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;


public class SidebarController {


    @FXML
    private VBox sidebar;

    @FXML
    private VBox logoBox;

    
    private static List<NavItem> navItems;


    private void setNav() {

        navItems = new ArrayList<NavItem>();
        navItems.addAll(List.of(new NavItem(Page.DASHBOARD , true), new NavItem(Page.TRADE , true), new NavItem(Page.JOURNAL , true), new NavItem(Page.ANALYSIS , true), new NavItem(Page.CONFIGURATION , true)));
        navItems.getFirst().setActive(true);

        sidebar.getChildren().addAll(navItems);
    }

    

    public static void setActivePage(Page page) {
        resetActiveItem();
        for (NavItem nav : navItems){
            if (nav.getPage().equals(page))
                nav.setActive(true);
        }
    }

    public static void resetActiveItem() {
        for (NavItem nav : navItems){
            nav.setActive(false);
        }
    }

    @FXML
    public void initialize() {
        setNav();
    }
}
