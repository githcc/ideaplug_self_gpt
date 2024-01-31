package com.cc.plug.util.convert;

import com.alibaba.fastjson2.JSON;
import com.cc.plug.entity.gpt.nostream.GptNoStreamEntity;

public class GptNoStreamUtil {
    private GptNoStreamUtil(){

    }

    public static GptNoStreamEntity toObj(String str){
        return JSON.parseObject(str, GptNoStreamEntity.class);
    }
}
