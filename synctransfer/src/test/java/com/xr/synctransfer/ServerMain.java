package com.xr.synctransfer;

import com.xr.synctransfer.server.ServerSocket;

public class ServerMain {
    public static void main(String[] args) {
        new Thread(new ServerSocket()).start();
        System.out.println("itstack-demo-netty server start done.");
    }
}
