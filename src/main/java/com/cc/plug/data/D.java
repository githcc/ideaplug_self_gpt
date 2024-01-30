package com.cc.plug.data;

import com.cc.plug.action.SelectAction;
import com.cc.plug.entity.GlobalDataEntity;

public class D {
    public static GlobalDataEntity globalDataEntity = new GlobalDataEntity();

    static {
        // Fix the bug and modify the prompt word immediately
        new SelectAction();
    }
}
