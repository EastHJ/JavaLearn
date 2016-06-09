package com.imooc.socket.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * 客户端 实现基于UDP与服务端通信
 * 
 * @author h
 *
 */
public class UDPClient {
	public static void main(String[] args) throws IOException {
		/**
		 * 向服务器发送数据
		 */
		// 1.定义服务器地址，端口，数据
		InetAddress address = InetAddress.getByName("localhost");
		int port = 19892;
		byte[] data = "用户名：qqq  密码：eee".getBytes();
		// 2.创建DatagramSocket,发送数据
		DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
		// 3.创建socket，向服务器端发送数据
		DatagramSocket socket = new DatagramSocket();
		//4.向服务器端发送数据
		socket.send(packet);
		/**
		 * 接受服务器的相应
		 */
		//1.创建数组存储接受到的数据
		byte[] data1 = new byte[1024];
		DatagramPacket packet1 = new DatagramPacket(data1, data1.length);
		//2.接受服务器相应信息
		socket.receive(packet1);
		//3.将接受信息转化成字符串输出
		String msg = new String(data1, 0, packet1.getLength());
		System.out.println(msg);
		
		socket.close();
	}
}
