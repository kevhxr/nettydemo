package com.xr.multiprotocal.server.handler;

import com.xr.multiprotocal.domain.Msg1;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Msg01Handler extends SimpleChannelInboundHandler<Msg1> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Msg1 msg) throws Exception {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(new Date()) + " 接收消息的处理器：" + this.getClass().getName());
        System.out.println("channelId：" + msg.getChannelId());
        System.out.println("消息内容：" + msg.getMsg());
        int[] a = new int[10];
    }
}
