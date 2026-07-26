package com.charly.tradecompanion.trade_companion_ui.models.trade;

import com.charly.tradecompanion.trade_companion_ui.enums.TradeDirection;
import lombok.Data;


@Data
public class CreateTradeRequest {

    private String symbol;

    private TradeDirection direction;

    private Double entryPrice;

    private Double stopLoss;

    private Double takeProfit;

    private String openingNote;

}
