import java.awt.image.BufferedImage;
import java.io.File;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

import javax.imageio.ImageIO;



public class runServer {
	private BufferedImage image;
	boolean args = true;
	public runServer () {
		try{
			ServerSocket serverSocket = new ServerSocket(51488);
			while(args){
				Socket clientSocket = null;
				if(!args) serverSocket.close();
				clientSocket = serverSocket.accept();

				image = ImageIO.read(clientSocket.getInputStream());
				Random rand = new Random();
				String filename = "in" + Integer.toHexString(rand.nextInt(0x10000000)) 
						+ Integer.toHexString(rand.nextInt(0x10000000)) + ".png";
				ImageIO.write(image, "png", new File(filename));
				new runCommands(filename);
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	public BufferedImage viewImage(){
		return image;
	}
}
