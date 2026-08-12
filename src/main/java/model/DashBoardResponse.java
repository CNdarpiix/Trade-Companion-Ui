package model;

import lombok.Data;
import model.table.TableDashboardResponse;

import java.util.ArrayList;
import java.util.List;

@Data
public class DashBoardResponse {

    private Double totalScore ;

    private List<TableDashboardResponse> tables = new ArrayList<>() ;





}
