package gatocreador887.darkness.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import gatocreador887.darkness.Board;
import gatocreador887.darkness.CustomLevel;
import gatocreador887.darkness.Level;
import gatocreador887.darkness.StaticFields;

public class UICustomLevel extends UI {
	private int mode;
	private String level =
			"+----+\n" +
			"|Sb k|\n" +
			"++g+-+\n" +
			":| |::\n" +
			":|F|::\n" +
			":+-+::";
	private String difficulty = "1.0";
	private String name = "Custom Level";
	
	public UICustomLevel() {
		this.buttons.add(new TextButton(this, "play", Color.DARK_GRAY, Board.WIDTH / 2 - 40, Board.HEIGHT / 2 + 30, 80, 20, "Play", Color.WHITE));
		this.buttons.add(new TextButton(this, "open", Color.DARK_GRAY, Board.WIDTH / 2 - 100, Board.HEIGHT / 2 - 10, 80, 20, "Open", Color.WHITE));
		this.buttons.add(new TextButton(this, "save", Color.DARK_GRAY, Board.WIDTH / 2 + 20, Board.HEIGHT / 2 - 10, 80, 20, "Save", Color.WHITE));
	}
	
	public void keyEvent(KeyEvent e, boolean pressed) {
		super.keyEvent(e, pressed);
		
		if (pressed) {
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				this.mode++;
			} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				this.mode--;
			} else if (this.mode == 0) {
				if (e.getKeyCode() == KeyEvent.VK_V && e.isControlDown()) {
					Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					
					try {
						this.level += clipboard.getData(DataFlavor.stringFlavor);
					} catch (UnsupportedFlavorException | IOException err) {
						System.err.println("Unable to paste clipboard. Stacktrace:");
						err.printStackTrace();
					}
				} else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
					this.level = this.level.substring(0, this.level.length() - 1);
				} else if (e.getKeyChar() != KeyEvent.CHAR_UNDEFINED) {
					this.level += e.getKeyChar();
				}
			} else if (this.mode == 1) {
				if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
					this.difficulty = this.difficulty.substring(0, this.difficulty.length() - 1);
				} else if (e.getKeyChar() != KeyEvent.CHAR_UNDEFINED) {
					this.difficulty += e.getKeyChar();
				}
			} else if (this.mode == 2) {
				if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
					this.name = this.name.substring(0, this.name.length() - 1);
				} else if (e.getKeyChar() != KeyEvent.CHAR_UNDEFINED) {
					this.name += e.getKeyChar();
				}
			}
			
			if (this.mode < 0) {
				this.mode = 0;
			} else if (this.mode > 2) {
				this.mode = 2;
			}
		}
	}
	
	protected void buttonClicked(Button b) {
		JFileChooser fileChooser = new JFileChooser();
		FileFilter fileFilter = new FileFilter() {
			public String getDescription() {
				return "TXT Files";
			}
			
			public boolean accept(File f) {
				return (f.getName().length() >= 4 ? f.getName().substring(f.getName().length() - 4).equals(".txt") : false) || f.isDirectory();
			}
		};
		fileChooser.addChoosableFileFilter(fileFilter);
		fileChooser.setFileFilter(fileFilter);
		fileChooser.setCurrentDirectory(getCustomLevelsDirectory());
		int state;
		File selectedFile;
		
		switch (b.getId()) {
			case "play":
				StaticFields.board.setActiveUI(null);
				StaticFields.board.setLevel(new Level(new CustomLevel(this.level, Float.parseFloat(this.difficulty), this.name)));
				break;
			case "open":
				fileChooser.setDialogTitle("Open Custom Level");
				state = fileChooser.showOpenDialog(StaticFields.board);
				selectedFile = fileChooser.getSelectedFile();
				
				if (selectedFile != null && state == JFileChooser.APPROVE_OPTION) {
					ArrayList<String> lines = new ArrayList<String>();
					
					try {
						lines.addAll(Files.readAllLines(selectedFile.toPath()));
					} catch (IOException e) {
						System.err.println("Unable to read file '" + selectedFile.toPath() + "'. Stacktrace:");
						e.printStackTrace();
					}
					
					try {
						this.level = "";
						boolean dataMode = false;
						
						for (String line : lines) {
							if (dataMode) {
								if (line.equals("]")) {
									dataMode = false;
									continue;
								}
								
								this.level += line + "\n";
							} else {
								String[] entry = line.split("=");
								String id = entry[0];
								String value = entry[1];
								
								if (id.equals("data") && value.equals("[")) {
									dataMode = true;
								} else if (id.equals("difficulty")) {
									this.difficulty = value;
								} else if (id.equals("name")) {
									this.name = value;
								}
							}
						}
						
						this.level = this.level.substring(0, this.level.length() - 1);
					} catch (ArrayIndexOutOfBoundsException e) {
						System.err.println("Unable to open custom level from '" + selectedFile.toPath() + "'. Stacktrace:");
						e.printStackTrace();
					}
				}
				
				break;
			case "save":
				fileChooser.setDialogTitle("Save Custom Level");
				state = fileChooser.showSaveDialog(StaticFields.board);
				selectedFile = fileChooser.getSelectedFile();
				
				if (selectedFile != null && state == JFileChooser.APPROVE_OPTION) {
					try {
						if (!selectedFile.getName().endsWith(".txt")) {
							selectedFile = new File(selectedFile.getAbsolutePath() + ".txt");
						}
						
						if (!selectedFile.exists()) {
							selectedFile.createNewFile();
						}
						
						ArrayList<String> lines = new ArrayList<String>();
						lines.add("data=[");
						
						for (String line : this.level.split("\n")) {
							lines.add(line);
						}
						
						lines.add("]");
						lines.add("difficulty=" + this.difficulty);
						lines.add("name=" + this.name);
						
						Files.write(selectedFile.toPath(), lines);
					} catch (IOException e) {
						System.err.println("Unable to save custom level to '" + selectedFile.toPath() + "'. Stacktrace:");
						e.printStackTrace();
					}
				}
				
				break;
		}
	}
	
	public void render(Graphics2D g2d) {
		super.render(g2d);
		
		String[] lines = this.level.split("\n");
		
		Font prevFont = g2d.getFont();
		g2d.setFont(new Font("courier new", Font.PLAIN, 12));
		
		for (int y = 0; y < lines.length; y++) {
			String line = lines[y];
			
			g2d.drawString(line, 10, 10 + y * 12);
		}
		
		g2d.setFont(prevFont);
		g2d.drawString(this.difficulty, Board.WIDTH / 2, 10);
		g2d.drawString(this.name, Board.WIDTH - 150, 10);
		
		String mode = "";
		
		if (this.mode == 0) {
			mode = "Editing Data";
		} else if (this.mode == 1) {
			mode = "Editing Difficulty";
		} else if (this.mode == 2) {
			mode = "Editing Name";
		}
		
		g2d.drawString(mode, Board.WIDTH / 2 - 35, Board.HEIGHT / 2 - 40);
	}
	
	public static File getCustomLevelsDirectory() {
		File customLevelsDirectory = Paths.get("Custom Levels").toFile();
		
		if (!customLevelsDirectory.exists()) {
			customLevelsDirectory.mkdirs();
		}
		
		return customLevelsDirectory;
	}
}
