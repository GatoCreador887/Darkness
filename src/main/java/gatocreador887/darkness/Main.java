package gatocreador887.darkness;

import java.awt.EventQueue;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Main extends JFrame {
	public Main() {
		StaticFields.main = this;
		this.init();
	}
	
	private void init() {
		System.out.println("Starting Darkness v1.0...");
		this.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
			}
			
			public void keyPressed(KeyEvent e) {
				StaticFields.board.keyEvent(e, true);
			}
			
			public void keyReleased(KeyEvent e) {
				StaticFields.board.keyEvent(e, false);
			}
		});
		this.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {
			}
			
			public void mousePressed(MouseEvent e) {
			}
			
			public void mouseExited(MouseEvent e) {
			}
			
			public void mouseEntered(MouseEvent e) {
			}
			
			public void mouseClicked(MouseEvent e) {
				StaticFields.board.mouseClicked(e);
			}
		});
		this.setFocusable(true);
		this.add(new Board());
		this.setResizable(false);
		this.pack();
		this.setTitle("Darkness v1.0");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Main ex = new Main();
				ex.setVisible(true);
			}
		});
	}
}
