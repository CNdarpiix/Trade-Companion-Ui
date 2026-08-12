package model.table;



import lombok.Data;
import model.TimeFrame;
import model.criterion.CriterionDashboardResponse;

import java.util.ArrayList;
import java.util.List;

@Data
public class TableDashboardResponse {
    private Long id;

    private Double score;

    private String name ;

    private List<CriterionDashboardResponse> criteria = new ArrayList<>() ;

    private List<TimeFrame> timeFrames ;


}
