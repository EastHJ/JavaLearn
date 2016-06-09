package com.imooc.socket.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * 客户端，用来与服务器端进行通信，
 * 
 * @author h
 *
 */
public class Client2 {
	public static void main(String[] args) {
		Socket socket = null;
		try {
			// 1.创建socket与服务端取得连接
			socket = new Socket("localhost", 19892);
			System.out.println("客户端已启动，正在连接服务端······");
			// 2.创建输出流
			OutputStream out = socket.getOutputStream();
			OutputStreamWriter osr = new OutputStreamWriter(out, "UTF-8");
			PrintWriter pw = new PrintWriter(osr, true);
			/**
			 * 创建输入流读取服务端的信息
			 */
			InputStream in = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(in,"utf-8");
			BufferedReader br = new BufferedReader(isr);
			System.out.println(br.readLine());
			// 3.发送
			String msg = null;
			Scanner scan = new Scanner(System.in);
			while ((msg = scan.nextLine()) != null) {
				pw.println(msg);
				if((msg = br.readLine())!=null)
				{
					System.out.println(msg);
				}
			}
			scan.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				socket.close();
				System.out.println("客户端已关闭！");
			} catch (IOException e) {
				System.out.println("客户端关闭失败");
				e.printStackTrace();
			}
		}
	}

}
