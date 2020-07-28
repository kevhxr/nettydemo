package com.xr.multiprotocal.domain;

import com.xr.multiprotocal.domain.protocal.Command;
import com.xr.multiprotocal.domain.protocal.Packet;

public class Msg3 extends Packet {
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

    public Msg3(String channelId, String msg) {
        this.channelId = channelId;
        this.msg = msg;
    }


    @Override
    public Byte getCommand() {
        return Command.Type3;
    }
}
