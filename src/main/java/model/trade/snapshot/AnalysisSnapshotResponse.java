package model.trade.snapshot;

import lombok.Data;
import model.criterion.CriterionResponse;

import java.util.List;
@Data
public class AnalysisSnapshotResponse {
    private List<CriterionResponse> criterion;

    private List<CriterionEvaluationSnapshotResponse> criterionData;
}
