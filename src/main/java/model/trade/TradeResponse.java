package model.trade;

import lombok.Data;
import model.trade.snapshot.AnalysisSnapshotResponse;
import pages.trade.TradeDirection;
import pages.trade.TradeStatus;

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

    private AnalysisSnapshotResponse analysis;

}
