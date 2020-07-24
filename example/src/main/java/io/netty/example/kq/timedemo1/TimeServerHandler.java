package io.netty.example.kq.timedemo1;

import io.netty.buffer.ByteBuf;
import io.netty.channel.*;

/**
 * @author kq
 * @date 2020-07-16 15:26
 * @since 2020-0630
 */

@ChannelHandler.Sharable
public class TimeServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(final ChannelHandlerContext ctx) { // (1)
        final ByteBuf time = ctx.alloc().buffer(4); // (2)
//        2208988800为1900年1月1日00:00:00~1970年1月1日00:00:00的总秒数
        time.writeInt((int) (System.currentTimeMillis() / 1000L + 2208988800L));
//        time.writeInt((int) (System.currentTimeMillis() / 1000L ));

        final ChannelFuture f = ctx.writeAndFlush(time); // (3)
        f.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) {
                assert f == future;
                ctx.close();
            }
        }); // (4)
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        System.out.println("================exceptionCaught"+cause);
        cause.printStackTrace();
        ctx.close();
    }
}
