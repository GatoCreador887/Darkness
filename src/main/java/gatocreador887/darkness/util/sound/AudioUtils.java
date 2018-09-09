package gatocreador887.darkness.util.sound;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineEvent.Type;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioUtils {
	public static void playClip(URL path, float volume) throws IOException, UnsupportedAudioFileException, LineUnavailableException, InterruptedException {
		class AudioListener implements LineListener {
			private boolean done = false;
			
			public synchronized void update(LineEvent event) {
				Type eventType = event.getType();
				
				if (eventType == Type.STOP || eventType == Type.CLOSE) {
					this.done = true;
					this.notifyAll();
				}
			}
			
			public synchronized void waitUntilDone() throws InterruptedException {
				while (!this.done) {
					this.wait();
				}
			}
		}

		AudioListener listener = new AudioListener();
		AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(path);
		
		try {
			Clip clip = AudioSystem.getClip();
			clip.addLineListener(listener);
			clip.open(audioInputStream);
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(volume);
			
			try {
				clip.start();
				listener.waitUntilDone();
			} finally {
				clip.close();
			}
			
		} finally {
			audioInputStream.close();
		}
	}
	
	public static void playSound(URL path, float volume) {
		Thread soundThread = new Thread(new Runnable() {
			public void run() {
				try {
					playClip(path, volume);
				} catch (IOException | UnsupportedAudioFileException | LineUnavailableException | InterruptedException e) {
					e.printStackTrace();
				}
				
				Thread.yield();
			}
		}, "Sound Thread");
		soundThread.setDaemon(true);
		soundThread.start();
		soundThread = null;
	}
}
