package model.criterion;

import pages.configuration.models.evaluation.*;
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
