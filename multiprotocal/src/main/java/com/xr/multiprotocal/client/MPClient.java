package com.xr.multiprotocal.client;

import com.xr.multiprotocal.util.MsgUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class MPClient {

    public static void main(String[] args) {
        new MPClient().connect("127.0.0.1", 7397);
    }

    private void connect(String inetHost, int inetPort) {
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup).channel(NioSocketChannel.class)
                    .option(ChannelOption.AUTO_READ,true)
                    .handler(new MPChildInitializer());
            ChannelFuture channelFuture = b.connect(inetHost,inetPort).sync();

            System.out.println("itstack-demo-netty client start done");

            channelFuture.channel().writeAndFlush(MsgUtil.buildMsg1(
                    channelFuture.channel().id().toString(),"hi，消息体Msg1。"));
            channelFuture.channel().writeAndFlush(MsgUtil.buildMsg2(
                    channelFuture.channel().id().toString(),"hi，消息体Msg2。"));
            channelFuture.channel().writeAndFlush(MsgUtil.buildMsg3(
                    channelFuture.channel().id().toString(),"hi，消息体Msg3。"));



            channelFuture.channel().closeFuture().sync();

        }catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}
