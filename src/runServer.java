import java.awt.image.BufferedImage;
import java.io.File;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

import javax.imageio.ImageIO;





public class runServer {
	
	public static void main(String[] args) {		
		try(ServerSocket serverSocket = new ServerSocket(51488);
			Socket clientSocket = serverSocket.accept();
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);			
				){
			BufferedImage image = ImageIO.read(clientSocket.getInputStream());
			out.println("Got it");
			
			Random rand = new Random();
			String filename = "in" + rand.nextInt(0x1000000) + ".png";
			ImageIO.write(image, "png", new File(filename));
			new runCommands(filename);
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
}
