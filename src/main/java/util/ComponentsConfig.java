package util;

import components.fundation.card.Card;
import components.fundation.card.TradeCard;
import components.fundation.input.TCInput;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.table.TableResponse;
import pages.trade.TradeDirection;
import pages.trade.TradeStatus;
import model.trade.TradeResponse;


import java.util.Collection;
import java.util.List;

public final class ComponentsConfig {

    public static Label createLabel(String texte, Collection<String> styleClass) {

        Label textLabel = new Label(texte);
        textLabel.setPadding(new Insets(10, 10, 10, 10));

        if (!styleClass.isEmpty())
            textLabel.getStyleClass().addAll(styleClass);

        return textLabel;
    }

    public static VBox createLabel(String texte , String optionalTexte , Collection<String> styleClass , Collection<String> optionalStyleClass){
        if (optionalTexte.isEmpty())
            return new VBox(createLabel(texte , styleClass));
        return new VBox(createLabel(texte , styleClass) , createLabel(optionalTexte , optionalStyleClass) );
    }

    public static Separator createSeparator() {
        Separator separator = new Separator();
        separator.getStyleClass().add("separator");
        return separator;
    }

    public static TradeCard createTradeCard(TradeResponse trade) {

        Label directionLabel = createLabel(trade.getDirection().name(), List.of("title-h4"));


        Label statutLabel = createLabel(trade.getStatus().name(), List.of("text-secondary", "title-h4"));


        TradeCard card = new TradeCard(trade);

        if (trade.getDirection().equals(TradeDirection.SHORT))
            directionLabel.getStyleClass().add("sell");
        else
            directionLabel.getStyleClass().add("buy");

        HBox title = new HBox(createLabel(trade.getSymbol(), List.of("text-primary", "title-h3")), directionLabel, statutLabel);
        card.getChildren().addAll(title, createSeparator());

        if (trade.getStatus().equals(TradeStatus.OPEN)) {

            card.getChildren().add(createLabel("Entry : " + trade.getEntryPrice(), List.of("text-secondary", "title-h4")));

        } else {
            if (trade.getProfit() >= 0)
                card.getChildren().add(createLabel("Profit : " + trade.getProfit(), List.of("buy", "title-h4")));
            else
                card.getChildren().add(createLabel("Profit : " + trade.getProfit(), List.of("sell", "title-h4")));

        }


        return card;
    }

    public static VBox createProgressCard(String title, Double value , Collection<String> style) {

        VBox card = createCard();



        card.getChildren().addAll(
                createLabel(title, style),
                createSeparator(),
                new ProgressBar(value));
        return card;
    }

    public static VBox createTableCard(TableResponse table) {
        VBox tableCard = ComponentsConfig.createCard();

        tableCard.getChildren().add(ComponentsConfig.createLabel(table.getName(), List.of("title-h1", "text-primary")));

        if (!table.getTimeFrames().isEmpty()) {

            ScrollPane scrollPane = new ScrollPane();
            HBox cardContainer = new HBox();

            cardContainer.setSpacing(10);

            scrollPane.setContent(cardContainer);
            scrollPane.setFitToHeight(true);
            scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
            scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);


            table.getTimeFrames().forEach(timeFrame -> {
                cardContainer.getChildren().add(ComponentsConfig.createLabel(timeFrame.getName(), List.of("outline", "button")));
            });
            tableCard.getChildren().add(scrollPane);
        }
        return tableCard;
    }


    public static VBox createCard() {
        VBox card = new VBox();
        card.getStyleClass().add("card");
        return card;
    }




}
