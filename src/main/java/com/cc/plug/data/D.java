package com.cc.plug.data;

import com.cc.plug.action.SelectAction;
import com.cc.plug.entity.GlobalDataEntity;

import java.io.Serializable;

import static com.cc.plug.util.io.PersistenceUtil.globalToObj;

public class D implements Serializable {
    public static GlobalDataEntity globalDataEntity;

    static {
        globalDataEntity = globalToObj();
        // Fix the bug and modify the prompt word immediately
        new SelectAction();
    }
}
