import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class serverGUI extends JFrame{

	private JButton startServer;
	private JButton stopServer;
	private listener l = new listener();
	private static final long serialVersionUID = 2664222353817266801L;

	public serverGUI() throws IOException{
		startServer = new JButton("Start Server");
		stopServer = new JButton("Stop Server");
		stopServer.setEnabled(false);
		startServer.addActionListener(l);
		stopServer.addActionListener(l);
		JPanel p = new JPanel();
		p.add(startServer);
		p.add(stopServer);
		add(p);
		pack();
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}


	private class listener implements ActionListener{
		Thread t = new Thread(new Runnable(){
			public void run(){
				new runServer();
			}
		});
		
		public void actionPerformed(ActionEvent e){
			if(e.getSource() == startServer){
				startServer.setEnabled(false);
				stopServer.setEnabled(true);
				t.start();
			}
			if(e.getSource() == stopServer){
				startServer.setEnabled(true);
				stopServer.setEnabled(false);
			}
		}
	}

	public static void main(String args[]) throws IOException{
		//	runServer s = new runServer();
		new serverGUI();
	}
}
