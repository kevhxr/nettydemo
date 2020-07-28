package com.xr.multiprotocal.domain;

import com.xr.multiprotocal.domain.protocal.Command;
import com.xr.multiprotocal.domain.protocal.Packet;

public class Msg2 extends Packet {
    private String channelId;
    private String msg;

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Msg2(String channelId, String msg01) {
        this.channelId = channelId;
        this.msg = msg01;
    }


    @Override
    public Byte getCommand() {
        return Command.Type2;
    }
}
