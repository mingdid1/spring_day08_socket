<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
	<script type="text/javascript">
		let sock = null;
		window.onload = ()=> {
			let wsUri = "ws://localhost:8088/root/test/websocket";
			sock = new WebSocket(wsUri);
			
			sock.onmessage = onMessage;
			
			$("#sendBtn").click( () =>{
				sendMessage();
			})
		}
		
		function onMessage(msg) {
			$("#rec_data").append(msg.data + "<br>")
		}
		function sendMessage() {
			sock.send($("#send_msg").val());
			$("#send_msg").val("");
		}
		
	</script>
</head>
<body>
	socket.jsp<br>
	
	<h3>socket 연결</h3>
	
	<div id="rec_data"></div>
	<input type="text" id="send_msg"><br>
	<button type="button" id="sendBtn">전송</button>
</body>
</html>