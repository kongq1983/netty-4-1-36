package io.netty.example.helloworld;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author kq
 * @date 2020-08-06 17:44
 * @since 2020-0630
 */
public class HelloWorldClientHandler extends ChannelInboundHandlerAdapter {

    public static final String BEGIN = "#";
    public static final String END = "=";


//    private final ByteBuf firstMessage;

    private AtomicInteger atomicInteger = new AtomicInteger();

    /**
     * Creates a client-side handler.
     */
    public HelloWorldClientHandler() {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println("HelloWorldClientHandler channelActive.");

        String msg = BEGIN+"i'm fine! i'm hellowolrd client!"+END;

        ByteBuf byteBuf = Unpooled.wrappedBuffer(msg.getBytes());
        ctx.writeAndFlush(byteBuf);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {

        try {
            TimeUnit.SECONDS.sleep(30);
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("receive message : "+msg);

        int index = atomicInteger.incrementAndGet();
        String sendMes = BEGIN+"i'm "+index+END;
        ByteBuf byteBuf = Unpooled.wrappedBuffer(sendMes.getBytes());
        ctx.write(byteBuf);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }



}
