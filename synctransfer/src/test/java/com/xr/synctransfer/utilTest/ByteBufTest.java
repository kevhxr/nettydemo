package com.xr.synctransfer.utilTest;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.junit.Test;

public class ByteBufTest {

    @Test
    public void testBuf(){
        ByteBuf byteBuf = Unpooled.buffer(10);
        for (int i = 0; i <=9 ; i++) {
            byteBuf.writeByte(i);
        }

        System.out.println(byteBuf.capacity());
        System.out.println(byteBuf.readerIndex());
        System.out.println(byteBuf.writerIndex());
        System.out.println(byteBuf.readableBytes());
/*        for (int i = 0; i <byteBuf.capacity() ; i++) {
            System.out.println(byteBuf.getByte(i));
        }*/
    }
}
