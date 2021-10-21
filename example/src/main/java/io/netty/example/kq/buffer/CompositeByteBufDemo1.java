package io.netty.example.kq.buffer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

/**
 * @author kq
 * @date 2021-10-21 15:01
 * @since 2020-0630
 */
public class CompositeByteBufDemo1 {

    public static void main(String[] args) {
        String a = "abc";
        String b = "1234";
        ByteBuf buf1 = Unpooled.wrappedBuffer(a.getBytes(CharsetUtil.UTF_8)); // UnpooledHeapByteBuf
        ByteBuf buf2 = Unpooled.wrappedBuffer(b.getBytes(CharsetUtil.UTF_8));
        ByteBuf compositeByteBuf = Unpooled.wrappedBuffer(buf1,buf2);  // 最多16个Component

        int size = compositeByteBuf.readableBytes();
        byte[] bytes = new byte[size];
        compositeByteBuf.readBytes(bytes); // CompositeByteBuf
        String value = new String(bytes,CharsetUtil.UTF_8);
        System.out.println("composite buff result : " + value);

    }

}
