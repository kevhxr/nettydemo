package com.xr.packstick.codec;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.nio.charset.Charset;
import java.util.List;

public class MyDecoder extends ByteToMessageDecoder {

    private final int BASE_LENGTH = 4;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        int size = in.readableBytes();

        byte[] data = new byte[size];
        in.readBytes(data);

        byte begin = data[0];     //开始符02
        byte end = data[size - 1];//结束符03

        //无开始符，只有结束符，数据丢包
        if (begin != 0x02 && end == 0x03){
            System.out.println("提示；byteBuf数据，无开始符，只有结束符，数据丢包。");
            ctx.writeAndFlush("error");
            return; //直接返回，不置位指针
        }
        //有开始符，无结束符号，数据半包。置位指针，接收余下数据
        if (begin != 0x02 || end != 0x03) {
            in.resetReaderIndex();
            System.out.println("提示；byteBuf数据，有开始符，无结束符号，数据半包。置位指针，接收余下数据。");
            return;
        }
        //数据完整，解析处理
        System.out.println(JSON.toJSONString(data));
        //越过02 03位
        ByteBuf copy = in.copy(1, size - 1);
        //转换
        String msg = copy.toString(Charset.forName("GBK"));
        //填充
        out.add(msg);
    }
}
