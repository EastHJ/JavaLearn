package com.imooc.socket.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * 服务器端， 基于udp的用户登录
 * 
 * @author h
 *
 */
public class UDPServer {
	public static void main(String[] args) throws IOException {
		/**
		 * 接受客户端信息
		 */
		// 1.创建服务器端的DatagramSocket，指定端口
		DatagramSocket socket = new DatagramSocket(19892);
		System.out.println("--------服务器端已启动,等待客户端连接··········");
		while (true) {
			// 给socket加锁，保证在多个客户端请求连接时的安全
			synchronized (socket) {
				// 2. 创建DategramPacket，用于向接受客户端发送的数据
				byte[] data = new byte[1024];
				DatagramPacket packet = new DatagramPacket(data, data.length);
				// 3.接受客户端发送的数据
				socket.receive(packet);
				// 4.读取信息，使用String 的构造方法，将字节数组中的数据转换成字符串
				String info = new String(data, 0, packet.getLength());
				System.out.println("我是服务器：客户端说：" + info);
				/**
				 * 相应客户端连接
				 */
				// 1.获取客户端的地址，端口号
				InetAddress address = packet.getAddress();
				int port = packet.getPort();
				byte[] data1 =("服务端："+address.getHostName() +"你好").getBytes();
				// 2.创建数据报
				DatagramPacket packet2 = new DatagramPacket(data1, data1.length, address, port);
				// 3.向客户端发送数据
				socket.send(packet2);
			}
		}
     }

}
