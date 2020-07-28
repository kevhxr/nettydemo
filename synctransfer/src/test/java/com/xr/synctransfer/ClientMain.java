package com.xr.synctransfer;

import com.alibaba.fastjson.JSON;
import com.xr.synctransfer.client.ClientSocket;
import com.xr.synctransfer.entities.MyRequest;
import com.xr.synctransfer.entities.MyResponse;
import com.xr.synctransfer.sync.SyncWrite;
import io.netty.channel.ChannelFuture;

public class ClientMain {

    public static void main(String[] args) {
        ClientSocket client = new ClientSocket();
        new Thread(client).start();
        ChannelFuture future = null;
        while (true) {
            try {
                //获取future，线程有等待处理时间
                if (null == future) {
                    future = client.getFuture();
                    Thread.sleep(500);
                    continue;
                }
                //构建发送参数
                MyRequest request = new MyRequest();
                request.setResult("查询用户信息");
                SyncWrite s = new SyncWrite();
                MyResponse response = s.writeAndSync(future.channel(), request, 1000);
                System.out.println("调用结果：" + JSON.toJSON(response));
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
