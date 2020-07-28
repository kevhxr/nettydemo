package com.xr.packstick.server;

import com.xr.packstick.codec.MyDecoder;
import com.xr.packstick.codec.MyEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.nio.charset.Charset;

public class PSChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {

        channel.pipeline().addLast(new StringDecoder(Charset.forName("GBK")));
        // 解码转String，注意调整自己的编码格式GBK、UTF-8
        channel.pipeline().addLast(new StringEncoder(Charset.forName("GBK")));

  /*      channel.pipeline().addLast(new MyDecoder());
        channel.pipeline().addLast(new MyEncoder());*/
        channel.pipeline().addLast(new PSServerHandler());
    }
}
