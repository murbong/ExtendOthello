import java.io.*;

import java.net.*;

public class sock {
	   int cur;
	   String ServerIP;
	   ServerSocket S_Sock;
	   Socket Sock;
	   DataInputStream Dis;
	   DataOutputStream Dos;
	   byte[] data;

	   sock() {
	      data = new byte[2];
	      ServerIP = "127.0.0.1";

	      try {
	         Sock = new Socket(ServerIP, 6000);
	         Dis = new DataInputStream(new BufferedInputStream(Sock.getInputStream()));
	         Dos = new DataOutputStream(new BufferedOutputStream(Sock.getOutputStream()));
	      } catch (UnknownHostException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      } catch (IOException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }

	   }

	   void send_sock() {

	      try {
	         Dos.write(data, 0, 2);
	      } catch (IOException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }

	   }

	   void read_sock() {

	      try {
	         Dis.read(data, 0, 2);
	      } catch (IOException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }

	   }

	   void close_sock() {

	      try {
	         Dis.close();
	         Sock.close();
	      } catch (IOException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }

	   }
	}