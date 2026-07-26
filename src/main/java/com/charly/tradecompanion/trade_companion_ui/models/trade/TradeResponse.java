package com.charly.tradecompanion.trade_companion.dto.trade;

import com.charly.tradecompanion.trade_companion_ui.enums.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TradeResponse {

    private Long id;

    private String symbol;

    private TradeDirection direction;

    private TradeStatus status;

    private Double entryPrice;

    private Double stopLoss;

    private Double takeProfit;

    private Double exitPrice;

    private Double profit;

    private String openingNote;

    private String closingNote;

    private LocalDateTime openedAt;

    private LocalDateTime closedAt;

}
