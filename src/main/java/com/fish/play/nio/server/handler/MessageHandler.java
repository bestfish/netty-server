package com.fish.play.nio.server.handler;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import com.fish.play.nio.server.entity.Incoming;
import com.fish.play.nio.server.entity.Outcoming;

public class MessageHandler extends ChannelHandlerAdapter {
	
	private String[] contents = { "Do not, for one repulse, forgo the purpose that you resolved to effort.", "When all else is lost the future still remains.",
			"The best hearts are always the bravest." };
	
	public MessageHandler() {
		System.out.println("============server MessageHandler===============");
	}
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		Incoming incoming = (Incoming) msg;
		long serialNum = incoming.getSerialNum();
		Outcoming response = new Outcoming();
		response.setSerialNum(serialNum);
		
		int number = Integer.parseInt(incoming.getRequest());
		if (number > contents.length - 1) {
			response.setResponse(contents[0]);
		}
		
		response.setResponse(contents[number]);
		ctx.writeAndFlush(response);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		ctx.close();
		cause.printStackTrace();
	}

}
