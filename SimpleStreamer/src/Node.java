import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JFrame;

import org.apache.commons.codec.binary.Base64;
import org.bridj.Pointer;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.github.sarxos.webcam.ds.buildin.natives.Device;
import com.github.sarxos.webcam.ds.buildin.natives.DeviceList;
import com.github.sarxos.webcam.ds.buildin.natives.OpenIMAJGrabber;

public class Node {
	int serverPort = 6262;
	String remote = "";
	String remotePort = "";
	int width = 320;
	int height = 240;
	double rate = 100;
	static ArrayList<Socket> sockets = new ArrayList<Socket>();
	public Node(int serverPort, String remote, String remotePort,
			int width, int height, double rate) {
		// TODO Auto-generated constructor stub
		this.serverPort = serverPort;
		this.remote = remote;
		this.remotePort = remotePort;
		this.width = width;
		this.height = height;
		this.rate = rate;
	}

	public void startService() {
		// TODO Auto-generated method stub
		ListenThread listenThread = new ListenThread();
		listenThread.start();
		SelfVideoThread selfVideoThread = new SelfVideoThread();
		selfVideoThread.start();
		if(remote!=""){
			String[] remotes = remote.split(",");
			String[] remotePorts = remotePort.split(",");
			for(int i = 0; i < remotes.length; i++){
				String currentRemote = remotes[i];
				int currentRemotePort = 6262;
				if(i<remotePorts.length){
					currentRemotePort = Integer.valueOf(remotePorts[i]);
				}
				
//				creatTCPLink(currentRemote, currentRemotePort);
				
				Socket socket = null;
				JSONObject process = new JSONObject();
				try {
					socket = new Socket(currentRemote, currentRemotePort);
					Thread sendVedio = new SendVideo(socket, width, height, rate);
					sendVedio.start();
					Thread receiveVedio = new ReceiveVideo(socket);
					receiveVedio.start();
					sockets.add(socket);
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					
				}
			}
		}
	}

	
	public void video(){
		Viewer myViewer = new Viewer(width,height);
		JFrame frame = new JFrame("MySelf Vedio");
		frame.setVisible(true);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.add(myViewer);
		frame.addWindowListener(new CloseHandler());
		/**
		 * This example show how to use native OpenIMAJ API to capture raw bytes
		 * data as byte[] array. It also calculates current FPS.
		 */

		OpenIMAJGrabber grabber = new OpenIMAJGrabber();

		Device device = null;
		Pointer<DeviceList> devices = grabber.getVideoDevices();
		for (Device d : devices.get().asArrayList()) {
			device = d;
			break;
		}
        System.out.println("width: "+grabber.getWidth()+"height: "+grabber.getHeight());
		boolean started = grabber.startSession(width, height, rate, Pointer.pointerTo(device));
		if (!started) {
			throw new RuntimeException("Not able to start native grabber!");
		}

		do {
			/* Get a frame from the webcam. */
			grabber.nextFrame();
			/* Get the raw bytes of the frame. */
			byte[] raw_image=grabber.getImage().getBytes(width * height * 3);
			/* Apply a crude kind of image compression. */
			byte[] compressed_image = Compressor.compress(raw_image);
			/* Prepare the date to be sent in a text friendly format. */
			byte[] base64_image = Base64.encodeBase64(compressed_image);
			/*
			 * The image data can be sent to connected clients.
			 */
			
			/*
			 * Assume we received some image data.
			 * Remove the text friendly encoding.
			 */
			byte[] nobase64_image = Base64.decodeBase64(base64_image);
			/* Decompress the image */
			byte[] decompressed_image = Compressor.decompress(nobase64_image);
			/* Give the raw image bytes to the viewer. */

			myViewer.ViewerInput(decompressed_image);
			
			frame.repaint();
		} while (true);

		//grabber.stopSession();
	}
	
	public void listen(){
		System.out.println("port: "+serverPort);
		ServerSocket serversocket = null;
		Socket socket = null;
		try {
			serversocket = new ServerSocket(serverPort);
			System.out.println("Server's listening...");
			while (true) {
				socket = serversocket.accept();
				System.out.println("Connected.");
				Thread sendVedio = new SendVideo(socket, width, height, rate);
				sendVedio.start();
				Thread receiveVedio = new ReceiveVideo(socket);
				receiveVedio.start();
				sockets.add(socket);
			}
			// out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (socket != null)
				try {
					socket.close();
					serversocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
	/* The ListenThread class: it creates a thread to always listen the packet
	 * from a socket. 
	 */
	public class ListenThread extends Thread{
		 public void run(){
			listen();
		 }
	}/* The ListenThread class: it creates a thread to always listen the packet
	 * from a socket. 
	 */
	public class SelfVideoThread extends Thread{
		 public void run(){
			video();
		 }
	}
	
	protected static class CloseHandler extends WindowAdapter{
		Socket socket = null;
		CloseHandler(){
			
		}
		CloseHandler(Socket socket){
			this.socket = socket;
		}
		public void windowClosing(final WindowEvent event){
			System.out.println("closing");
			PrintWriter out;
			if(socket == null){
				System.out.println("aaaaaaaaaaa");
				for(Socket currentSocket: sockets){
					try {
						if(currentSocket.isClosed()){
							sockets.remove(currentSocket);
						}else{
							out = new PrintWriter(currentSocket.getOutputStream(),true);
							JSONObject send_image = new JSONObject();
							send_image.put("type", "stopstream");
							String message = send_image.toJSONString();
							out.println(message);
							System.out.println(message);
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
	
				System.exit(0);
			}else{
				System.out.println("bbbbbbbbbbb");
				try {
					if(socket.isClosed()){
						sockets.remove(socket);
					}else{
						out = new PrintWriter(socket.getOutputStream(),true);
						JSONObject send_image = new JSONObject();
						send_image.put("type", "stopstream");
						String message = send_image.toJSONString();
						out.println(message);
						System.out.println(message);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public class ReceiveVideo extends Thread {
		Socket socket;
		BufferedReader in;

		public ReceiveVideo(Socket socket) {
			this.socket = socket;
			try {
				in = new BufferedReader(new InputStreamReader(
						this.socket.getInputStream()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public void run() {
			JSONParser parser = new JSONParser();
			JSONObject obj;
			String msg = null;
			byte[] base64_image=null;
			Viewer myViewer = null;
			JFrame frame = null;
			try {
				while (true) {
					msg = in.readLine();
					if(msg == null){
						break;
					}
					obj = (JSONObject) parser.parse(msg.trim());
					if (myViewer == null){
						System.out.println(msg);
					}
					if (myViewer == null && obj.get("type").equals("startstream")){
						int width = Integer.valueOf(obj.get("width").toString());
						int height = Integer.valueOf(obj.get("height").toString());
						myViewer = new Viewer(width,height);
						frame = new JFrame("Client Vedio");
						frame.setVisible(true);
						frame.setSize(width, height);
						frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						frame.add(myViewer);
						frame.addWindowListener(new CloseHandler(socket));
					}else if (myViewer != null && obj.get("type").equals("image")) {
						base64_image = obj.get("data").toString().trim().getBytes();
						byte[] nobase64_image = Base64.decodeBase64(base64_image);
						/* Decompress the image */
						byte[] decompressed_image = Compressor.decompress(nobase64_image);
						/* Give the raw image bytes to the viewer. */
						myViewer.ViewerInput(decompressed_image);
						frame.repaint();
					}else if(obj.get("type").equals("stopstream")){
						frame.dispose();
						sockets.remove(socket);
						break;
					}
				}

				// out.println("OK");

			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				// alert the user
				e.printStackTrace();

			}finally {
				try {
					socket.close();
					System.out.println("Receive Video disconneted.");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	
}
