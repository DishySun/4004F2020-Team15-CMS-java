package cms.handler;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.Scanner;
import java.io.File;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class LoginHandler implements HttpHandler{
	private final String PATH ="src/main/resources";
	private final String INDEX = this.PATH+"/login.html";
	
	public void handle(HttpExchange exchange) throws IOException {
		String method = exchange.getRequestMethod();
		String uri = exchange.getRequestURI().toString();
		
		if (method.equals("GET")) {
			File file;
			if (uri.equals("/")) {
				file = new File(this.INDEX);
			}else {
				file = new File(this.PATH + uri);
			}
			if (!file.exists()) {
				System.out.println("file not exists");
				exchange.sendResponseHeaders(404 , 0);
				exchange.getResponseBody().close();
				return;
			}
			OutputStream os = exchange.getResponseBody();
			exchange.sendResponseHeaders(200 , file.length());
			Files.copy(file.toPath(), os);
			os.close();
		}else if (method.equals("POST")) {
			Scanner sc = new Scanner(exchange.getRequestBody());
			while (sc.hasNextLine()) {
				System.out.println(sc.nextLine());
			}
			exchange.sendResponseHeaders(200 , 0);
			exchange.getResponseBody().write("nice".getBytes());
			exchange.getResponseBody().close();
		}
	}
}
