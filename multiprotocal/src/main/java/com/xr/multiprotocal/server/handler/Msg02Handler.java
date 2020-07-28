package com.xr.multiprotocal.server.handler;

import com.xr.multiprotocal.domain.Msg2;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Msg02Handler extends SimpleChannelInboundHandler<Msg2> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Msg2 msg) throws Exception {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(new Date()) + " 接收消息的处理器：" + this.getClass().getName());
        System.out.println("channelId：" + msg.getChannelId());
        System.out.println("消息内容：" + msg.getMsg());
        msg.setMsg("im 22 yes received");
        ctx.writeAndFlush(msg);
    }
}
