package io.netty.example.kq.buffer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

/**
 * 不确定ByteBuf
 * @author kq
 * @date 2021-10-21 15:53
 * @since 2020-0630
 */
public class CompositeByteBufDemo3 {

    public static void main(String[] args) {
        String a = "abc";
        String b = "1234";
        ByteBuf buf1 = Unpooled.wrappedBuffer(a.getBytes(CharsetUtil.UTF_8));
        ByteBuf buf2 = Unpooled.wrappedBuffer(b.getBytes(CharsetUtil.UTF_8));
        CompositeByteBuf compositeByteBuf = Unpooled.compositeBuffer();
//compositeByteBuf.addComponent(buf1);//不要使用这个方法，它不会增加writeIndex
        compositeByteBuf.addComponent(true,buf1);//一定要使用这个方法
        if (buf2 != null) {
            compositeByteBuf.addComponent(true, buf2);
        }
        int size = compositeByteBuf.readableBytes();
        byte[] bytes = new byte[size];
        compositeByteBuf.readBytes(bytes);

        String value = new String(bytes,CharsetUtil.UTF_8);
        System.out.println("composite buff result : " + value);
    }

}
