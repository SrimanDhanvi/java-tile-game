package my_first2D_game;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {

	Clip clip;
	
	URL sound[] = new URL[30];
	
	public Sound() {
		//BGM
		sound[0] = getClass().getResource("/sounds/TLC_BGM1.wav");
		sound[1] = getClass().getResource("/sounds/TLC_BGM2.wav");
		
		//sound
		sound[2] = getClass().getResource("/sounds/Pickup_Obj1.wav");
		sound[3] = getClass().getResource("/sounds/OldMan_Sound1.wav");
		
		sound[4] = getClass().getResource("/sounds/windSound_better.wav");
	}
	
	public void setFile(int i) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(sound[i]);
			// BGM
			clip = AudioSystem.getClip();
			clip.open(ais);

		} catch(Exception e) {
			
		}
	}
	
	public void play() {
		
		clip.start();
	}
	
	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public void BGMloop(int gapMillis) {
	    new Thread(() -> {
	        while (true) {
	            try {
	        
	                // Music picker
	                int index = (int)(Math.random() * 2);

	                // Clip loader
	                setFile(index); // sets clipBGM

	                // Start
	                if (clip != null) {
	                	clip.start();
	                    
	                    // Wait
	                    Thread.sleep(clip.getMicrosecondLength() / 1000);
	                    }

	                    // Gap
	                    Thread.sleep(gapMillis);

	            } catch (Exception e) {
	                e.printStackTrace();
	                break;
	            }
	        }
	    }).start();
	}
	
	public void stop() {
		clip.stop();
	}
	
}
