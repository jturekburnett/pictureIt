import java.io.IOException;


public class runCommands {
	
	public runCommands(String filename){
		Runtime r= Runtime.getRuntime();
		try {
			Process p = r.exec(String.format("tesseract %s results.txt 1",filename));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		
		
	}

}
