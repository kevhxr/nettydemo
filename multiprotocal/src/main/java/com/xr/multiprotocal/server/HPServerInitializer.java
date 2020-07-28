package com.xr.multiprotocal.server;

import com.xr.multiprotocal.codec.ObjDecoder;
import com.xr.multiprotocal.codec.ObjEncoder;
import com.xr.multiprotocal.server.handler.Msg01Handler;
import com.xr.multiprotocal.server.handler.Msg02Handler;
import com.xr.multiprotocal.server.handler.Msg03Handler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.nio.charset.Charset;

public class HPServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(new ObjDecoder());
        ch.pipeline().addLast(new ObjEncoder());
        ch.pipeline().addLast(new Msg01Handler());
        ch.pipeline().addLast(new Msg02Handler());
        ch.pipeline().addLast(new Msg03Handler());

    }
}
