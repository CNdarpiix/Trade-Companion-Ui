package config.models.table;

import config.models.TimeFrame;
import lombok.Data;


import java.util.ArrayList;
import java.util.List;

@Data
public class CreateTable {

    private String name;

    private List<TimeFrame> timeFrames = new ArrayList<>() ;


}
