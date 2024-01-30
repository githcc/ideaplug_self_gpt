package com.cc.plug.util.convert;

import com.alibaba.fastjson2.JSON;
import com.cc.plug.entity.gpt.GptStreamEntity;

public class GptStreamUtil {
    private GptStreamUtil(){
    }
    public static GptStreamEntity toObj(String str){
        return JSON.parseObject(str, GptStreamEntity.class);
    }
}
