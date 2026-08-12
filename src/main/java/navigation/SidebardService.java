package navigation;

import layouts.main.MainLayoutController;

public class SidebardService {

    private static MainLayoutController mainController ;

    public static void setMainController(MainLayoutController c) {
        mainController = c;
    }

    public static void navigate(Page page){
        mainController.loadPage(page);
    }
}
