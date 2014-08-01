package com.fish.play.nio.server.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.nio.charset.Charset;

import com.fish.play.nio.server.entity.Outcoming;

public class MessageEncoder extends MessageToByteEncoder<Outcoming> {
	private final Charset charset;

	public MessageEncoder() {
		this(Charset.defaultCharset());
	}

	public MessageEncoder(Charset charset) {
		if (charset == null) {
            throw new NullPointerException("charset");
        }
        this.charset = charset;
	}

	@Override
	protected void encode(ChannelHandlerContext ctx, Outcoming msg, ByteBuf out) throws Exception {
		String content = msg.getResponse();
		byte[] bytes = content.getBytes(charset);
		int totalLen = bytes.length + 8;
		out.writeInt(totalLen);
		out.writeLong(msg.getSerialNum());
		out.writeBytes(bytes);
	}

}
