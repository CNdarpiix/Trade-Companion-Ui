package com.charly.tradecompanion.trade_companion_ui.models.table;

import com.charly.tradecompanion.trade_companion_ui.models.TimeFrame;
import lombok.Data;


import java.util.ArrayList;
import java.util.List;

@Data
public class CreateTable {

    private String name;

    private List<TimeFrame> timeFrames = new ArrayList<>() ;


}
