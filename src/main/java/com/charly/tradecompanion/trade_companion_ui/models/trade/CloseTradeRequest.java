package com.charly.tradecompanion.trade_companion_ui.models.trade;


import lombok.Data;

@Data
public class CloseTradeRequest {

    private Double profit ;

    private Double exitPrice;

    private String closingNote;

}
