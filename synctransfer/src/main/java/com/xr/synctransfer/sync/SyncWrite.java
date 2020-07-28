package com.xr.synctransfer.sync;

import com.xr.synctransfer.entities.MyRequest;
import com.xr.synctransfer.entities.MyResponse;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;

import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class SyncWrite {
    public MyResponse writeAndSync(final Channel channel, final MyRequest request, final long timeout) throws Exception {

        if (channel == null) {
            throw new NullPointerException("channel");
        }
        if (request == null) {
            throw new NullPointerException("request");
        }
        if (timeout <= 0) {
            throw new IllegalArgumentException("timeout <= 0");
        }

        String requestId = UUID.randomUUID().toString();
        request.setRequestId(requestId);

        WriteFuture<MyResponse> future = new SyncWriteFuture(request.getRequestId());
        SyncWriteMap.syncKey.put(request.getRequestId(), future);

        MyResponse response = doWriteAndSync(channel, request, timeout, future);

        SyncWriteMap.syncKey.remove(request.getRequestId());
        return response;
    }

    private MyResponse doWriteAndSync(Channel channel,
                                      MyRequest request, long timeout,
                                      final WriteFuture<MyResponse> writefuture) throws Exception {

        channel.writeAndFlush(request).addListener((ChannelFutureListener) future -> {
            System.out.println("callback been  called");
            writefuture.setWriteResult(future.isSuccess());
            writefuture.setCause(future.cause());
            //失败移除
            if (!writefuture.isWriteSuccess()) {
                SyncWriteMap.syncKey.remove(writefuture.requestId());
            }
        });
        System.out.println("start to get");
        MyResponse response = writefuture.get(timeout, TimeUnit.MILLISECONDS);
        System.out.println("get done");
        if (response == null) {
            if (writefuture.isTimeout()) {
                throw new TimeoutException();
            } else {
                throw new Exception(writefuture.cause());
            }
        }
        return response;
    }
}
