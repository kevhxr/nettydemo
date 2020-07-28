package com.xr.hbreconn.client;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.EventLoop;

import java.util.concurrent.TimeUnit;

public class HRChannelFutureListener implements ChannelFutureListener {

    @Override
    public void operationComplete(ChannelFuture future) throws Exception {
        if (future.isSuccess()) {
            System.out.println("client start done!!!");
            return;
        }
        final EventLoop loop = future.channel().eventLoop();
        loop.schedule(()->{
            try{
                new HRClient().connect("127.0.0.1", 7397);
                System.out.println("client start done!!!!!!");
                Thread.sleep(500);
            }catch (Exception e){
                System.out.println("client start error go reconnect!!!");

            }
        },1L, TimeUnit.SECONDS);
    }
}
