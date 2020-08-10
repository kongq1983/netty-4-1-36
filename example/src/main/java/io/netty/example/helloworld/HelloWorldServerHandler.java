package io.netty.example.helloworld;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.example.util.ByteBufUtil;

/**
 * @author kq
 * @date 2020-08-06 17:39
 * @since 2020-0630
 */
public class HelloWorldServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {

        ByteBuf byteBuf = (ByteBuf) msg;

        String message = ByteBufUtil.byteBufToString(byteBuf);
        System.out.println("server read msg="+ message);
        String responseMes = "ok,"+message;

        ByteBuf resByteBuf = Unpooled.wrappedBuffer(responseMes.getBytes());
        ctx.write(resByteBuf);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        System.out.println("channelReadComplete");
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }

}
