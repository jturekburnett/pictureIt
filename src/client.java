import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

public class client {

	public static void main(String[] args) {
		try(Socket cSocket = new Socket(InetAddress.getByName("localhost"),51488);
				ImageOutputStream out = ImageIO.createImageOutputStream(cSocket.getOutputStream());
				BufferedReader in = new BufferedReader(new InputStreamReader(cSocket.getInputStream()));)
						{
			System.out.println(out.length());
			String filename = "GC.jpg";
			ImageIO.write(ImageIO.read(new File(filename)), "jpg", out);
						}
		catch(Exception e){
			e.printStackTrace();
		}

	}
}
