package io.netty.example.kq.helloworld;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.example.kq.util.DateUtil;

import java.util.concurrent.TimeUnit;

/**
 * @author kq
 * @date 2020-07-15 18:10
 * @since 2020-0630
 */
public class HelloWorldClientHandler extends ChannelInboundHandlerAdapter {

    static final int SIZE = Integer.parseInt(System.getProperty("size", "256"));

    private final ByteBuf firstMessage;

    /**
     * Creates a client-side handler.
     */
    public HelloWorldClientHandler() {
        firstMessage = Unpooled.buffer(SIZE);
        for (int i = 0; i < firstMessage.capacity(); i ++) {
            firstMessage.writeByte((byte) i);
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println("channelActive================");
        ctx.writeAndFlush(firstMessage);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        System.out.println(DateUtil.now()+"  HelloWorldClientHandler channelRead :"+msg);
        try {
            TimeUnit.SECONDS.sleep(3);
        }catch (Exception e){
            e.printStackTrace();
        }
        ctx.write(msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        System.out.println("channelReadComplete================");
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}
