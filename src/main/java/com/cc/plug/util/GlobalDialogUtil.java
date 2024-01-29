package com.cc.plug.util;

import com.alibaba.fastjson2.JSON;
import com.cc.plug.entity.GlobalDialogEntity;


public class GlobalDialogUtil {
    public static String toStr(GlobalDialogEntity globalDialogEntity){
        return JSON.toJSONString(globalDialogEntity);
    }
    public static GlobalDialogEntity toObj(String str){
        return JSON.parseObject(str, GlobalDialogEntity.class);
    }
}
