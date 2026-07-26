package com.charly.tradecompanion.trade_companion_ui.models.dashBoard;

import com.charly.tradecompanion.trade_companion_ui.models.evaluation.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CriterionDashboardResponse {
    private Long id;

    private String name;

    private Double coefficient;

    private Double score;

    private List<EvaluationResponse> evaluations = new ArrayList<>();

}
