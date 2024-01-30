package com.cc.plug.util;

import com.cc.plug.util.convert.GptStreamUtil;
import org.junit.Test;

public class TestGptStreamUtil {
    @Test
    public void t(){
        String str = "{\"id\":\"chatcmpl-8mbpuYgw60cYOEbkbLpa97sXK2k9V\",\"object\":\"chat.completion.chunk\",\"created\":1706595878,\"model\":\"gpt-3.5-turbo-1106\",\"system_fingerprint\":\"fp_b57c83dd65\",\"choices\":[{\"index\":0,\"delta\":{\"content\":\" am\"},\"logprobs\":null,\"finish_reason\":null}]}";
        System.out.println(GptStreamUtil.toObj(str));
    }
}
