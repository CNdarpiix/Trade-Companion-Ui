package components.fundation.card;

import components.fundation.tradeDetail.TradeDetail;
import model.trade.TradeResponse;
import pages.journal.JournalController;

public class TradeCard extends Card{

    private Long tradeId ;

    private TradeDetail tradeDetail;

    public TradeCard(TradeResponse trade){
        super();
        tradeDetail=new TradeDetail(trade);
        this.tradeId = trade.getId() ;
        setOnMouseClicked(event ->{
            JournalController.showOverlay(tradeDetail);
        });
    }


}
