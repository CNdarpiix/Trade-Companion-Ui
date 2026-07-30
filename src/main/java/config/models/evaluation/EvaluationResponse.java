package config.models.evaluation;


import lombok.Data;
import pages.analysis.AnalysisBias;


@Data
public class EvaluationResponse {

    private Long id ;

    private Long timeFrameId ;

    private Long criterionId;

    private AnalysisBias bias;
}
