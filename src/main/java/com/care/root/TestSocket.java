package com.care.root;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class TestSocket extends TextWebSocketHandler{
	
	ArrayList<WebSocketSession> auth = new ArrayList<>();
	
	@Override	// Ŭ���̾�Ʈ�� ���� �� ����Ǵ� �޼ҵ�
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println(session.getId()+"�� ����Ǿ����ϴ�");
		auth.add(session);
	}

	@Override	// �޽����� �޴� �޼ҵ�
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		System.out.println(session.getId()+"�� �޽���: "+ message.getPayload());

		// session.sendMessage(message);
		
		System.out.println("session : "+ session.getAttributes());
		
		Map<String, Object> map = session.getAttributes();
		TextMessage text = new TextMessage(map.get("nick")+ ":" + message.getPayload());
		for(WebSocketSession s : auth) {
			s.sendMessage( text );
		}
	}

	@Override	// ������ ����� �����
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		System.out.println(session.getId()+"�� ��������");
	}
	
}
