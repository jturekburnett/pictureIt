import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;



public class runServer {
	boolean args = true;
	public runServer () {
		try{
			ServerSocket serverSocket = new ServerSocket(51488);
			System.out.println("The server is now running.");
			while(args){
				Socket clientSocket = null;
				if(!args) serverSocket.close();
				clientSocket = serverSocket.accept();

				Random rand = new Random();
				String filename = "in" + Integer.toHexString(rand.nextInt(0x10000000)) 
						+ Integer.toHexString(rand.nextInt(0x10000000)) + ".png";

				InputStream in = clientSocket.getInputStream();
				OutputStream out = new FileOutputStream(filename);


				final byte[] buffer = new byte[16384];
				int end = 0;
				int i = 0;
				while((end = in.read(buffer)) != -1){
					out.write(buffer,0,end);	
				}

				System.out.println("Transfer Complete");
				out.close();
				new runCommands(filename);
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

}
