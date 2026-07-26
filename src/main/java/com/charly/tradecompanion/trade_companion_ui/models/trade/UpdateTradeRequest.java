package com.charly.tradecompanion.trade_companion_ui.models.trade;

import com.charly.tradecompanion.trade_companion_ui.enums.TradeDirection;
import lombok.Data;

@Data
public class UpdateTradeRequest {

    private String symbol;

    private TradeDirection direction;

    private Double entryPrice;

    private Double stopLoss;

    private Double takeProfit;

    private Double exitPrice;

    private Double profit;

    private String openingNote;

    private String closingNote;

}
