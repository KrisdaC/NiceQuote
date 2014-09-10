import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


public class NiceQuote extends JFrame {
	
	JPanel top = new JPanel();
	JPanel top_d = new JPanel();
	Bottom btm = new Bottom();
	JMenuBar menu = new JMenuBar();
	JTextField text = new JTextField();
	JFileChooser chooser = new JFileChooser();
	JPanel buttons = new JPanel();
	JButton inc = new JButton("Increase Font Size");
	JButton dec = new JButton("Decrease Font Size");
	Image img;
	Graphics g;
	int width;
	int height;
	String s = "";
	int a = 1;
	int fontSize = 24;
	int posX = 0;
	int posY = 0;
	Boolean drag = true;
	//Boolean temp = true;
	public NiceQuote() {
		
		super("NiceQuote");
		posX = (this.getWidth()-width)/2+30;
		posY = (this.getHeight()-height)/2+30;
		JMenu file = new JMenu("File");
		
		JMenuItem n = new JMenuItem("New");
		n.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				a = 2;
				btm.repaint();
			}});

		JMenuItem background = new JMenuItem("Load Background");
		background.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				chooser.showOpenDialog(chooser);
				try {
					img = ImageIO.read(chooser.getSelectedFile());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				width = img.getWidth(null);
				height = img.getHeight(null);
				btm.setFrame(getJFrame());
				a = 1;
				btm.repaint();
			}
        });
		
		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
			
		});
		file.add(n);
		file.add(background);
		file.addSeparator();
		file.add(exit);
		menu.add(file);
		top.add(menu);
		
		top_d.setLayout(new BorderLayout());
		top_d.add(new JLabel("  Quote:   "),BorderLayout.WEST);
		
		text.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				s=text.getText();
				btm.repaint();

			}

			@Override
			public void keyTyped(KeyEvent e) {
				//s = text.getText();			
			}});
		
		inc.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				fontSize+=4;
				btm.repaint();
			}});
		
		dec.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				fontSize-=4;
				btm.repaint();
			}});
		
		top_d.add(text,BorderLayout.CENTER);
		top_d.add(buttons,BorderLayout.EAST);
		buttons.setLayout(new GridLayout(1,2));
		buttons.add(inc);
		buttons.add(dec);
		top.add(top_d);
		top.setLayout(new GridLayout(2,1));
		
		this.setLayout(new BorderLayout());
		this.add(top,BorderLayout.NORTH);
		this.add(btm, BorderLayout.CENTER);
		this.setPreferredSize(new Dimension (1000,800));
	}
	public JFrame getJFrame(){
		return this;
	}
	public static void createGUI(){
		NiceQuote app = new NiceQuote();
		app.pack();
		app.setVisible(true);
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				createGUI();
			}
		});
	}
	class Bottom extends JPanel{
		public Bottom(){
			this.addMouseMotionListener(new MouseMotionListener(){

				@Override
				public void mouseDragged(MouseEvent e) {
					// TODO Auto-generated method stub
					posX = e.getXOnScreen();
					posY = e.getYOnScreen();
					btm.repaint();
					
				}

				@Override
				public void mouseMoved(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
	
			});
		}
		public void setFrame(JFrame f){
			f.setSize(width+50,height+100);
		}
		@Override
		protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(a==1){
			g.setFont(new Font("Times New Roman", Font.ITALIC, fontSize));
			g.drawImage(img, (this.getWidth() - width)/2, (this.getHeight() - height)/2, null);
			g.drawString(s, posX, posY);
		}else if(a== 2){
			this.removeAll();
			text.setText("");
			s = "";
			}

		}
		
	}
}
