package com.cdfg.thdfhcl.pojo.until;

import java.util.HashMap;
import java.util.Map;

public class Thddparam {
    private static Map<String,String> msg = new HashMap<>();

    static {
        msg.put("01","Sanya International");
        msg.put("02","三亚国内出发厅②");
        msg.put("03","海口国内出发厅");
        msg.put("04","博鳌国内出发厅");
        msg.put("05","海口火车站");
        msg.put("06","岛内其他火车站");
        msg.put("07","海口秀英港");
        msg.put("08","海口新海港");
        msg.put("13","三亚国内出发①");
    }

    public static String getAddress(String key) {
        if (msg.containsKey(key)){
            return msg.get(key);
        }else {
            return "未知提货点";
        }
    }
}
