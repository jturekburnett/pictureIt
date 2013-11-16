import java.io.IOException;

public class runCommands {
	
	public runCommands(String filename){
		Runtime r= Runtime.getRuntime();
		try {
<<<<<<< HEAD
			Process p = r.exec(String.format("tesseract %s results -psm 1",filename));
=======
			r.exec(String.format("tesseract %s results 1",filename));
			
>>>>>>> 24a7b5648a0f085ee0859497b37fc47c7425e98f
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
