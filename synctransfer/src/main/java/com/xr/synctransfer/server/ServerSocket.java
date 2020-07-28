package com.xr.synctransfer.server;

import com.xr.synctransfer.entities.MyRequest;
import com.xr.synctransfer.entities.MyResponse;
import com.xr.synctransfer.handlers.MyServerHandler;
import com.xr.synctransfer.util.RpcDecoder;
import com.xr.synctransfer.util.RpcEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class ServerSocket implements Runnable {

    private ChannelFuture channelFuture;

    @Override
    public void run() {
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(boss, worker).channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) {
                            ch.pipeline().addLast(
                                    new RpcDecoder(MyRequest.class),
                                    new RpcEncoder(MyResponse.class),
                                    new MyServerHandler());
                        }
                    });
            channelFuture = bootstrap.bind(7397).sync();
            channelFuture.channel().closeFuture().sync();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }
}
