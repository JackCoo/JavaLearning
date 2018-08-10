package nowcoder.choice.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * java TCP
 * 
 * @author Yanjie
 *
 */
public class SocketDemo {

	
	public static void main(String[] args) {

		new Thread(() -> {
			server();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}).start();
		client();
		
	}
	
	public static void server() {
		
		ServerSocket server = null;
		try {
			// 指点端口及等待队列长度
			server = new ServerSocket(8080, 10);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		
		while(true) {
			final Socket client;
			try {
				// 接受客户端连接
				client = server.accept();
			} catch (IOException e) {
				e.printStackTrace();
				continue;
			}
			
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					System.out.println("Server:recv connect from " + client.getPort());
					try {
						
						// 读取客户端发送信息
						BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
						while (true) {
							System.out.println("Server Recv:" + br.readLine());
						}
						
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				}
			}).start();
		}
		
	}
	
	public static void client() {
		new Thread(() -> {
			
			Socket client;
			
			try {
				
				// 连接服务器
				client = new Socket("127.0.0.1", 8080);
			} catch (IOException e) {
				e.printStackTrace();
				return;
			}
			
			System.out.println("Client:is connect " + client.isConnected());
			
			try {
				
				// 获取输入流
				PrintStream out = new PrintStream (client.getOutputStream());
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				
				out.println("Hello");
				while (true) {
					out.println(br.readLine());
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}).start(); 
	}
}
