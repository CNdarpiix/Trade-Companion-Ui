package model.table;

import model.TimeFrame;
import lombok.Data;


import java.util.List;
@Data
public class TableResponse {

    private Long id ;

    private String name ;

    private List<TimeFrame> timeFrames ;


}
