package com.xr.multiprotocal.server.handler;

import com.xr.multiprotocal.domain.Msg3;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Msg03Handler extends SimpleChannelInboundHandler<Msg3> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Msg3 msg) throws Exception {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(new Date()) + " 接收消息的处理器：" + this.getClass().getName());
        System.out.println("channelId：" + msg.getChannelId());
        System.out.println("消息内容：" + msg.getMsg());
        ctx.writeAndFlush(msg);
    }
}
