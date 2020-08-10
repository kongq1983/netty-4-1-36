package io.netty.example.util;

import io.netty.buffer.ByteBuf;

/**
 * @author kq
 * @date 2020-08-06 18:05
 * @since 2020-0630
 */
public class ByteBufUtil {

    public static String byteBufToString(ByteBuf buf) {
        String str;
        if(buf.hasArray()) { // 处理堆缓冲区
            str = new String(buf.array(), buf.arrayOffset() + buf.readerIndex(), buf.readableBytes());
        } else { // 处理直接缓冲区以及复合缓冲区
            byte[] bytes = new byte[buf.readableBytes()];
            buf.getBytes(buf.readerIndex(), bytes);
            str = new String(bytes, 0, buf.readableBytes());
        }
        return str;
    }

}
