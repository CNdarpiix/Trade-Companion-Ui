package com.charly.tradecompanion.trade_companion_ui.controller.component;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class ProgressCardController {

    @FXML
    private Label titreLabel;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Label valueTexte;

    @FXML
    private Label otherTexte;

    public void setStat(String titre , Double ratio , String otherTexte){
        if (titre !=null && !titre.isBlank())
            titreLabel.setText(titre);
        if (ratio != null) {
            progressBar.setProgress(ratio / 100);
            valueTexte.setText(String.format("%.1f %%", ratio));
        }
        if (otherTexte !=null && !otherTexte.isBlank())
            this.otherTexte.setText(otherTexte);

    }


}
