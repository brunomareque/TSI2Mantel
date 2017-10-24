package com.Mantel.mantelArch.util;

import javax.json.Json;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import com.Mantel.mantelArch.model.ChatMessage;
public class ChatMessageEncoder implements Encoder.Text<ChatMessage> {
	@Override
	public void init(final EndpointConfig config) {
	}

	@Override
	public void destroy() {
	}

	@Override
	public String encode(final ChatMessage chatMessage) throws EncodeException {
		return Json.createObjectBuilder()
				.add("message", chatMessage.getMensaje())
				.add("sender", chatMessage.getUsuario())
				.add("received", chatMessage.getRecibido().toString()).build()
				.toString();
	}
}
