import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JFrame;

import org.apache.commons.codec.binary.Base64;
import org.bridj.Pointer;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.github.sarxos.webcam.ds.buildin.natives.Device;
import com.github.sarxos.webcam.ds.buildin.natives.DeviceList;
import com.github.sarxos.webcam.ds.buildin.natives.OpenIMAJGrabber;

public class SendVideo extends Thread {
	Socket socket;
	int width;
	int height;
	double rate;
	PrintWriter out;

	public SendVideo(Socket socket,int width,int height, double rate) {
		this.socket = socket;
		this.width = width;
		this.height = height;
		this.rate = rate;
		try {
			out = new PrintWriter(this.socket.getOutputStream(),true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		JSONObject process = new JSONObject();
		try {
			process.put("width", String.valueOf(width));
			process.put("height", String.valueOf(height));
			process.put("format", "raw");
			process.put("type", "startstream");
			String message = process.toJSONString();
			out.println(message);
			OpenIMAJGrabber grabber = new OpenIMAJGrabber();
	
			Device device = null;
			Pointer<DeviceList> devices = grabber.getVideoDevices();
			for (Device d : devices.get().asArrayList()) {
				device = d;
				break;
			}
	
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
				String transfer = new String(base64_image, "UTF-8");;
				JSONObject send_image = new JSONObject();
				send_image.put("data", transfer);
				send_image.put("type", "image");
				message = send_image.toJSONString();
				out.println(message);
				/*byte[] nobase64_image = Base64.decodeBase64(base64_image);
				 Decompress the image 
				byte[] decompressed_image = Compressor.decompress(nobase64_image);
				 Give the raw image bytes to the viewer. 
				myViewer.ViewerInput(decompressed_image);
				frame.repaint();*/
			} while (true);
	
			/*System.out.print("Client: sending the message...");
			message = process.toJSONString();
			out.println(message);
			// out.flush();
		*/
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (socket != null) {
				try {
					socket.close();
					System.out.println("SendVideo disconneted.");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
