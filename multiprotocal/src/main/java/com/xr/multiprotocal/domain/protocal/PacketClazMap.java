package com.xr.multiprotocal.domain.protocal;

import com.xr.multiprotocal.domain.Msg1;
import com.xr.multiprotocal.domain.Msg2;
import com.xr.multiprotocal.domain.Msg3;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PacketClazMap {
    public final static Map<Byte, Class<? extends Packet>> packetTypeMap = new ConcurrentHashMap<>();
    static {
        packetTypeMap.put(Command.Type1, Msg1.class);
        packetTypeMap.put(Command.Type2, Msg2.class);
        packetTypeMap.put(Command.Type3, Msg3.class);
    }
}
