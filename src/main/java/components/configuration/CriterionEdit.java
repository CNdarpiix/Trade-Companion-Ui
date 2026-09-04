package components.configuration;

import components.fundation.button.TCButton;
import components.fundation.button.TCButtonType;
import components.fundation.input.InputType;
import components.fundation.input.TCInput;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import layouts.main.MainLayoutController;
import model.criterion.CreateCriterion;
import model.criterion.CriterionResponse;
import model.table.TableResponse;
import pages.configuration.ConfigurationApiService;

import java.util.List;

public class CriterionEdit extends VBox {
    private final ConfigurationApiService service = new ConfigurationApiService();

    public CriterionEdit(List<String> tablesNames) {
        TCInput name = new TCInput("Criterion name", InputType.TEXT, "Enter a name");

        TCInput coefficiant = new TCInput("Impact coefficiant", InputType.NUMBER, "Enter a coefficiant");

        ComboBox<String> chooseTable = new ComboBox<>();
        tablesNames.forEach(names -> {
            chooseTable.getItems().add(names);
        });

        TCButton createCriterion = new TCButton("Create Criterion", TCButtonType.OUTLINE);
        createCriterion.setOnMouseClicked(event -> {

            if (name.getText().isEmpty())
                name.isError(true);
            else {
                name.isError(false);
            }

            if (coefficiant.getText().isEmpty())
                coefficiant.isError(true);
            else {
                coefficiant.isError(false);
            }

            if (chooseTable.getValue().isEmpty())
                MainLayoutController.createPush("Please choose a table", "");
            else {
                if (!coefficiant.getText().isEmpty() && !name.getText().isEmpty()) {
                    CreateCriterion createCriterion1 = new CreateCriterion();
                    createCriterion1.setCoefficient(Double.parseDouble(coefficiant.getText()));
                    createCriterion1.setName(name.getText());

                    for (TableResponse allTable : service.getAllTables()) {
                        if (allTable.getName().equals(chooseTable.getValue()))
                            createCriterion1.setTableId(allTable.getId());
                    }


                    CriterionResponse response = service.createCriterion(createCriterion1);
                    MainLayoutController.createPush("Criterion :"+ response.getName() + " is created" , "ID :" + response.getId());
                }
            }

        });

        getChildren().addAll(name, coefficiant, chooseTable , createCriterion);

    }
}
