import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

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
		Thread t;
		boolean kill = false;
		public void actionPerformed(ActionEvent e){
			if(e.getSource() == startServer){
				startServer.setEnabled(false);
				stopServer.setEnabled(true);
				
				//JOptionPane.showMessageDialog(null,"The server has been started!");
				if(t == null && kill == false){

					t = new Thread(new Runnable(){
						public void run(){
							runServer l = new runServer();
							//	SwingUtilities.invokeLater(new Runnable(){
							//		public void run(){

							//		}
							//	});

						}
					});
					t.start();
					//BufferedImage img = s.viewImage();
					//add(new JLabel(new ImageIcon(img)));
				}
				else{
					t.stop();
				}
			}
			if(e.getSource() == stopServer){
				kill = true;
				e.setSource(startServer);
				actionPerformed(e);
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
