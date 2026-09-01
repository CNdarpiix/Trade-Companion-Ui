package model.trade.snapshot;

import pages.configuration.models.criterion.CriterionResponse;

import java.util.List;

public class AnalysisSnapshotResponse {
    private List<CriterionResponse> criterion;

    private List<CriterionEvaluationSnapshotResponse> criterionData;
}
