import java.io.IOException;

public class runCommands {
	public runCommands(String filename){
		Runtime r= Runtime.getRuntime();
		try {
			r.exec(String.format("tesseract %s results 1",filename));
			new googleConnectRound2();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
