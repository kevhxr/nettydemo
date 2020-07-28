package com.xr.multiprotocal.client;

import com.xr.multiprotocal.codec.ObjDecoder;
import com.xr.multiprotocal.codec.ObjEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.serialization.ObjectDecoder;

public class MPChildInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(new ObjDecoder());
        ch.pipeline().addLast(new ObjEncoder());
        ch.pipeline().addLast(new MPChildHandler());
    }
}
