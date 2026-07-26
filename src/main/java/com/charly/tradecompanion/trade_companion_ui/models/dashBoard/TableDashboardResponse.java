package com.charly.tradecompanion.trade_companion_ui.models.dashBoard;



import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TableDashboardResponse {
    private Long id;

    private Double score;

    private String name ;

    private List<CriterionDashboardResponse> criteria = new ArrayList<>() ;

}
