package com.Mantel.mantelArch.util;

import java.io.StringReader;
import java.util.Date;

import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import com.Mantel.mantelArch.model.ChatMessage;

public class ChatMessageDecoder implements Decoder.Text<ChatMessage> {
	@Override
	public void init(final EndpointConfig config) {
	}

	@Override
	public void destroy() {
	}

	@Override
	public ChatMessage decode(final String textMessage) throws DecodeException {
		ChatMessage chatMessage = new ChatMessage();
		JsonObject obj = Json.createReader(new StringReader(textMessage))
				.readObject();
		chatMessage.setMensaje(obj.getString("mensaje"));
		chatMessage.setUsuario(obj.getString("usuario"));
		chatMessage.setRecibido(new Date());
		return chatMessage;
	}

	@Override
	public boolean willDecode(final String s) {
		return true;
	}
}
