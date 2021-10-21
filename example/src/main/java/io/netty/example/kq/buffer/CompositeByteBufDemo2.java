package io.netty.example.kq.buffer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 相当于不确定
 * @author kq
 * @date 2021-10-21 15:39
 * @since 2020-0630
 */
public class CompositeByteBufDemo2 {

    public static void main(String[] args) {


        List<ByteBuf> list = new ArrayList<ByteBuf>();
        for(int i=1;i<20;i++) {
            String num = String.valueOf(i);
            list.add(Unpooled.wrappedBuffer(num.getBytes(CharsetUtil.UTF_8)));
        }

        ByteBuf compositeByteBuf = Unpooled.wrappedBuffer(list.toArray(new ByteBuf[0]));  //传进去几个就是几个

        int size = compositeByteBuf.readableBytes();
        byte[] bytes = new byte[size];
        compositeByteBuf.readBytes(bytes); // CompositeByteBuf
        String value = new String(bytes,CharsetUtil.UTF_8);
        System.out.println("composite buff result : " + value);

    }


}
