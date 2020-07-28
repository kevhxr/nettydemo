package com.xr.multiprotocal.util;

import com.xr.multiprotocal.domain.Msg1;
import com.xr.multiprotocal.domain.Msg2;
import com.xr.multiprotocal.domain.Msg3;

public class MsgUtil {
    public static Msg1 buildMsg1(String channelId, String msgContent) {
        return new Msg1(channelId, msgContent);
    }
    public static Msg2 buildMsg2(String channelId, String msgContent) {
        return new Msg2(channelId, msgContent);
    }

    public static Msg3 buildMsg3(String channelId, String msgContent) {
        return new Msg3(channelId, msgContent);
    }
}
