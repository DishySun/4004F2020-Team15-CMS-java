package cms;

import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;

public class Server {
	public static void main(String[] args) throws Exception{
		HttpServer server = HttpServer.create(new InetSocketAddress(4004),0);
		server.createContext("/", new cms.handler.LoginHandler());
		server.start();
	}
}
