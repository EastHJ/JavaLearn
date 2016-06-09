package com.imooc.socket.tcp;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务器端，用来制定端口号，监听客户端连接,基于TCP协议的通信
 * 
 * @author h
 *
 */
public class Server {
	public static void main(String[] args) {
		ServerSocket server = null;
		try {
			// 1.创建seversocket实例制定端口号
			server = new ServerSocket(19892);
			// 创建客户端连接计数器
			int account = 0;
			while (true) {
				// 输出提示信息
				System.out.println("等待客户端连接·········");
				// 2.监听端口，有客户端请求时接受请求创建socket实例，监听时处于阻塞状态
				Socket socket = server.accept();
				account++;
				InetAddress address = socket.getInetAddress();
				System.out.println("客户端:" + address.getHostName() + "上线！\n当前有" + account + "个客户端在线。");
				ServerThread t = new ServerThread(socket);
				t.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				//关闭流
				server.close();
			} catch (IOException e) {
				System.out.println("流关闭失败！");
				e.printStackTrace();
			}
		}
	}
}
