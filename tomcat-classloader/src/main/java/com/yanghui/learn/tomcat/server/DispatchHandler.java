package com.yanghui.learn.tomcat.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DispatchHandler extends ChannelInboundHandlerAdapter {

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        log.info("\n 收到数据class：{}, 缓冲区类型：{}",byteBuf.getClass(),byteBuf.hasArray()?"堆内存":"直接内存");
        int length = byteBuf.readableBytes();
        byte[] data = new byte[length];
        byteBuf.getBytes(byteBuf.readerIndex(), data);
        log.info("\n 收到数据：{}", new String(data,"utf-8"));
        log.info("写回前refCnt:{}",byteBuf.refCnt());
        ChannelFuture f = ctx.channel().writeAndFlush(msg);
        f.addListener((ChannelFuture channelFuture)->{
            log.info("写回后refCnt：{}",byteBuf.refCnt());
        });
        ctx.fireChannelRead(msg);
    }
}
