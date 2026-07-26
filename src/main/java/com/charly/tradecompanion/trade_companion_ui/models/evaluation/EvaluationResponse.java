package com.charly.tradecompanion.trade_companion_ui.models.evaluation;


import  com.charly.tradecompanion.trade_companion_ui.enums.*;
import lombok.Data;


@Data
public class EvaluationResponse {

    private Long id ;

    private Long timeFrameId ;

    private Long criterionId;

    private AnalysisBias bias;
}
