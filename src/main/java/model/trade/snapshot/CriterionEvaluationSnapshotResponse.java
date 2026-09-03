package model.trade.snapshot;

import lombok.Data;
import model.evaluation.EvaluationResponse;

import java.util.List;
@Data
public class CriterionEvaluationSnapshotResponse {
    private List<EvaluationResponse> criterionEvalutaionList ;
}
