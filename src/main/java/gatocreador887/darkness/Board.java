package gatocreador887.darkness;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.Timer;

import gatocreador887.darkness.ui.UI;
import gatocreador887.darkness.ui.UIMainMenu;

@SuppressWarnings("serial")
public class Board extends JPanel implements ActionListener {
	public static final int WIDTH = 600;
	public static final int HEIGHT = 600;
	public static final int DELAY = 30;
	public static final int RENDER_DELAY = 10;
	private boolean running;
	private Timer timer;
	private Timer renderTimer;
	private int step;
	private Thread renderThread;
	private Level level;
	private UI activeUI;
	
	public Board() {
		StaticFields.board = this;
		this.init();
	}
	
	private void init() {
		this.running = true;
		this.renderTimer = new Timer(RENDER_DELAY, new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				Board.this.repaint();
			}
		});
		this.renderThread = new Thread(new Runnable() {
			public void run() {
				Board.this.renderTimer.start();
			}
		}, "RenderThread");
		this.renderThread.setDaemon(true);
		this.renderThread.start();
		this.setBackground(Color.BLACK);
		this.setName("Board");
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.timer = new Timer(DELAY, this);
		this.timer.start();
		this.activeUI = new UIMainMenu();
		System.out.println("Darkness v1.0 started.");
	}
	
	public void keyEvent(KeyEvent e, boolean pressed) {
		if (this.level != null) {
			this.level.keyEvent(e, pressed);
		}
		
		if (this.activeUI != null) {
			this.activeUI.keyEvent(e, pressed);
		}
	}
	
	public void mouseClicked(MouseEvent e) {
		Point p = e.getPoint();
		p.y -= 25;
		
		if (this.activeUI != null) {
			this.activeUI.mouseClicked(e, p);
		}
	}
	
	private void update() {
		if (this.level != null) {
			this.level.update(step);
		}
		
		this.step++;
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setFont(new Font(StaticFields.FONT_NAME, Font.BOLD, 12));
		
		if (this.level != null) {
			this.level.render(g2d, this.step);
		} else if (this.activeUI != null) {
			this.activeUI.render(g2d);
		}
		
		Toolkit.getDefaultToolkit().sync();
	}
	
	public void actionPerformed(ActionEvent event) {
		if (!this.running) {
			this.timer.stop();
		}
		
		this.update();
	}
	
	public int getStep() {
		return this.step;
	}
	
	public Level getLevel() {
		return this.level;
	}
	
	public void setLevel(Level level) {
		this.level = level;
	}
	
	public void setActiveUI(UI ui) {
		this.activeUI = ui;
	}
}
