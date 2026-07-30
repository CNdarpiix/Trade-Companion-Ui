package com.charly.tradecompanion.trade_companion.dto.table;

import config.models.TimeFrame;
import lombok.Data;


import java.util.List;
@Data
public class TableResponse {

    private Long id ;

    private String name ;

    private List<TimeFrame> timeFrames ;


}
