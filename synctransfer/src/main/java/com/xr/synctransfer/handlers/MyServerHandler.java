package com.xr.synctransfer.handlers;

import com.xr.synctransfer.entities.MyRequest;
import com.xr.synctransfer.entities.MyResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

public class MyServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object obj){
        MyRequest msg = (MyRequest) obj;
        //反馈
        MyResponse response = new MyResponse();
        response.setRequestId(msg.getRequestId());
        response.setParam(msg.getResult() + " 请求成功，反馈结果请接受处理");
        ctx.writeAndFlush(response);
        //释放
        ReferenceCountUtil.release(msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }
}
