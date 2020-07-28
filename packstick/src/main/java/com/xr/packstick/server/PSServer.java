package com.xr.packstick.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class PSServer {

    public static void main(String[] args) {
        new PSServer().start(7397);
    }

    private void start(int port) {
        EventLoopGroup parentGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try{
            ServerBootstrap b = new ServerBootstrap();
            b.group(parentGroup,workerGroup).channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG,125)
                    .childHandler(new PSChannelInitializer());
            ChannelFuture future = b.bind(port).sync();
            System.out.println("PS server start done!!");
            future.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            parentGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
