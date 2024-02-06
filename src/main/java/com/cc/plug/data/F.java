package com.cc.plug.data;

import java.io.File;

public class F {
    public final static String GlobalDataEntity_PROXY = "https://api.openai-proxy.com";
    public final static String GlobalDataEntity_KEY = "Bearer sk-xxx";
    public final static String GlobalDataEntity_CHAT = "Chat";

    public final static String GlobalDialogEntity_MODEL = "gpt-3.5-turbo-1106";
    public final static boolean GlobalDialogEntity_STREAM = true;
    public final static boolean GlobalDialogEntity_SHARE_PROMPTS = false;
    public final static boolean GlobalDialogEntity_SHARE_CONVERSATIONS = false;

    public final static int COLOR_USER = 0xf7f8fa;
    public final static int COLOR_USER_DARK = 0x1e1f22;
    public final static int COLOR_BOT = 0xffffff;
    public final static int COLOR_BOT_DARK = 0x2b2d30;

    public final static String ROLE_USER = "user";
    public final static String ROLE_BOT = "assistant";

    public final static String ASK_GPT = "Ask Gpt";
    public final static String ASK_GPT_FOR = "Ask Gpt For ";
    public final static String PERSISTENCE_FILE_NAME = File.separator+"gpt_data.bin";
    public final static String[] PROMPTS_HEAD = {"Prompts Name", "Prompts Content"};
}
