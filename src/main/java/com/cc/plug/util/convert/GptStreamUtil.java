package com.cc.plug.util.convert;

import com.alibaba.fastjson2.JSON;
import com.cc.plug.entity.gpt.nostream.GptNoStreamEntity;
import com.cc.plug.entity.gpt.stream.GptStreamEntity;

public class GptStreamUtil {
    private GptStreamUtil(){
    }
    public static GptStreamEntity toObj(String str){
        return JSON.parseObject(str, GptStreamEntity.class);
    }

    public static String toStr(GptStreamEntity gptStreamEntity){
        return JSON.toJSONString(gptStreamEntity);
    }
}
