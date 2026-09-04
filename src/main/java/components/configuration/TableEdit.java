package components.configuration;

import com.sun.tools.javac.Main;
import components.fundation.TCDialog.TCDialog;
import components.fundation.button.TCButton;
import components.fundation.button.TCButtonType;
import components.fundation.input.InputType;
import components.fundation.input.TCInput;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import layouts.main.MainLayoutController;
import model.TimeFrame;
import model.table.CreateTable;
import model.table.TableResponse;
import pages.configuration.ConfigurationApiService;
import util.ComponentsConfig;

import java.util.ArrayList;
import java.util.List;


public class TableEdit extends VBox {

    private TCInput name;

    private FlowPane timeFrames;

    private List<TimeFrame> timeFrameList;

    private VBox timeFramesEdit;

    private TCInput tfCoef;

    private TCInput tfName;

    private TCButton createTimeFrames;

    private HBox timeFramesBox;

    private TCButton createTableButton;

    private final ConfigurationApiService service  = new ConfigurationApiService();

    public TableEdit() {
        name = new TCInput("Table Name", InputType.TEXT, "Need a name");

        createTableButton = new TCButton("CREATE TABLE", TCButtonType.OUTLINE);
        createTableButton.setOnMouseClicked(event -> {
            CreateTable table = new CreateTable();

            if (name.getText().isEmpty())
                name.isError(true);
            else {
                name.isError(false);
                table.setName(name.getText());
            }

            if (timeFrameList.isEmpty()){
                tfCoef.isError(true);
                tfName.isError(true);
            }else {
                tfCoef.isError(false);
                tfName.isError(false);
                table.setTimeFrames(new ArrayList<>(timeFrameList));
                if (!name.getText().isEmpty()) {
                    TableResponse response = service.createTable(table);
                    MainLayoutController.createPush("Table :"+ response.getName() + " is created" , "ID :" + response.getId());
                }
            }



        });

        loadTfEdit();

        getChildren().addAll(name, timeFramesBox, createTableButton);
    }


    public void loadTfEdit() {

        timeFrames = new FlowPane();
        timeFrameList = new ArrayList<TimeFrame>();

         tfName = new TCInput("TimeFrame (ex 5min)", InputType.TEXT, "Enter a TimeFrame");
         tfCoef = new TCInput("TimeFrame impact (ex 3)", InputType.NUMBER, "Enter a impact Coefficiant");

        createTimeFrames = new TCButton("+", TCButtonType.OUTLINE);
        createTimeFrames.setOnMouseClicked(event -> {

            TimeFrame tf = new TimeFrame();

            if (tfName.getText().isEmpty())
                tfName.isError(true);
            else {
                tfName.isError(false);
                tf.setName(tfName.getText());
            }

            if (tfCoef.getText().isEmpty())
                tfCoef.isError(true);
            else {
                tf.setCoefficient(Double.parseDouble(tfCoef.getText()));
            }

            timeFrameList.add(tf);
            refreshTimeframes();
        });


        timeFramesEdit = new VBox(tfName, tfCoef, createTimeFrames);


        timeFramesBox = new HBox(timeFrames, timeFramesEdit);


    }

    private void refreshTimeframes() {
        timeFrames.getChildren().clear();
        timeFrameList.forEach(timeFrame -> {
            TCButton timeFrameButton = new TCButton(timeFrame.getName(), TCButtonType.OUTLINE);

            timeFrameButton.setOnMouseClicked(event -> {
                TCDialog alerte = new TCDialog("Delete TimeFrame ?", "Are you sure you want to permanently delete this TimeFrame?");

                TCButton yesButton = new TCButton("YES", TCButtonType.BUY);
                yesButton.setOnMouseClicked(event1 -> {
                    timeFrameList.remove(timeFrame);
                    refreshTimeframes();
                    MainLayoutController.createPush(
                            "TIMEFRAME REMOVED",
                            "");
                    MainLayoutController.getDialogVBox().getChildren().remove(alerte);
                });

                TCButton noButton = new TCButton("NO", TCButtonType.SELL);
                noButton.setOnMouseClicked(event1 -> {
                    MainLayoutController.getDialogVBox().getChildren().remove(alerte);
                });

                alerte.getDialogContent().getChildren().addAll(yesButton, noButton);
                MainLayoutController.showPush(alerte);
            });

            timeFrames.getChildren().add(timeFrameButton);
        });
    }
}
