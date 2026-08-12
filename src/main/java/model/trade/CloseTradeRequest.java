package model.trade;


import lombok.Data;

@Data
public class CloseTradeRequest {

    private Double profit ;

    private Double exitPrice;

    private String closingNote;

}
