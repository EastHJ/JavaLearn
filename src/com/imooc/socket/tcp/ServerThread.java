package com.imooc.socket.tcp;
/**
 * 此类是服务器端线程处理类，此类简单实现服务器多线程操作，未使用线程池
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class ServerThread extends Thread {
	private Socket socket =null;
	public ServerThread(Socket socket) {
		this.socket = socket;
	}
	public void run() {
		try {
			// 创建输入流
			// 获取字节输入流，读取客户端信息
			InputStream in = socket.getInputStream();
			// 转换为字符流
			InputStreamReader isr = new InputStreamReader(in, "utf-8");
			// 增加缓冲区
			BufferedReader br = new BufferedReader(isr);
			/**
			 * 建立输出流回应客户端
			 */
			OutputStream out = socket.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(out, "utf-8");
			PrintWriter pw = new PrintWriter(osw, true);
			InetAddress address = socket.getInetAddress();
			String s = "你好！我是服务端,欢迎" + address.getHostName() + "上线！";
			pw.println(s);
			//读取客户端信息并输出
			String msg = null;
			while ((msg = br.readLine()) != null) {
				System.out.println("客户端说：" + msg);
				pw.println("客户端说：" + msg);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
                  try {
					socket.close();
				} catch (IOException e) {
					System.out.println("线程关闭失败！");
					e.printStackTrace();
				}
		}
	}
}
