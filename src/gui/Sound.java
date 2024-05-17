package gui;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	Clip clip;
	URL soundURL[] = new URL[30];
	
	public Sound() {
		// background sounds
		soundURL[0] = getClass().getResource("/sound/background_sound/background_music_loop.wav");
		soundURL[1] = getClass().getResource("/sound/background_sound/warrior_medium-192841.wav");
		// enemy sounds
		soundURL[2] = getClass().getResource("/sound/enemy_sound/bite.wav");
		soundURL[3] = getClass().getResource("/sound/enemy_sound/chick-death.wav");
		soundURL[4] = getClass().getResource("/sound/enemy_sound/chicken-death1.wav");
		soundURL[5] = getClass().getResource("/sound/enemy_sound/chicken-death2.wav");
		soundURL[6] = getClass().getResource("/sound/enemy_sound/chicken-hit1.wav");
		soundURL[7] = getClass().getResource("/sound/enemy_sound/chicken-hit2.wav");
		soundURL[8] = getClass().getResource("/sound/enemy_sound/chicken-hit3.wav");
		soundURL[9] = getClass().getResource("/sound/enemy_sound/chicken-hit4.wav");
		soundURL[10] = getClass().getResource("/sound/enemy_sound/Egg_Splash.wav");
		soundURL[11] = getClass().getResource("/sound/enemy_sound/EggDeath.wav");
		soundURL[12] = getClass().getResource("/sound/enemy_sound/egg-drop.wav");
		soundURL[13] = getClass().getResource("/sound/enemy_sound/EggHit.wav");
		soundURL[14] = getClass().getResource("/sound/enemy_sound/SuperChicken_Death.wav");
		
		// bullet sound
		soundURL[15] = getClass().getResource("/sound/bullet_sound/boom.wav");
		soundURL[16] = getClass().getResource("/sound/bullet_sound/Lazer.wav");
		soundURL[17] = getClass().getResource("/sound/bullet_sound/Neutron.wav");
		
		
	}
	
	public void setFile(int i) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
		
		} catch (Exception e) {
		}
	}
	public void play() {
		clip.start();
	}
	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	public void stop() {
		clip.stop();
	}
}
