package com.charly.tradecompanion.trade_companion_ui.models.dashBoard;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DashBoardResponse {

    private Double totalScore ;

    private List<TableDashboardResponse> tables = new ArrayList<>() ;





}
