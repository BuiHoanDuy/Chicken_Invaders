package gui;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
    private Clip bg;
    private Clip se;
    private URL soundURL[] = new URL[30];
    private boolean isMuted = false;
    private long bgFramePosition = 0;

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
        soundURL[17] = getClass().getResource("/sound/bullet_sound/bulletSound_5.wav");
        soundURL[23] = getClass().getResource("/sound/bullet_sound/bulletSound_1.wav");
        soundURL[24] = getClass().getResource("/sound/bullet_sound/bulletSound_2.wav");
        soundURL[25] = getClass().getResource("/sound/bullet_sound/bulletSound_3.wav");
        soundURL[26] = getClass().getResource("/sound/bullet_sound/bulletSound_4.wav");
        soundURL[27] = getClass().getResource("/sound/bullet_sound/ultiSound.wav");
		
        soundURL[18] = getClass().getResource("/sound/enemy_sound/bulletOnShield.wav");
		soundURL[19] = getClass().getResource("/sound/enemy_sound/bulletOnEgg.wav");
		soundURL[20] = getClass().getResource("/sound/enemy_sound/bang.wav");
		soundURL[21] = getClass().getResource("/sound/enemy_sound/gameOver.wav");
		soundURL[22] = getClass().getResource("/sound/background_sound/victory.wav");
		soundURL[23] = getClass().getResource("/sound/enemy_sound/gatling.wav");
		soundURL[24] = getClass().getResource("/sound/enemy_sound/laserPiu.wav");
    }

    public void setFile(boolean whatSound, int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            if (whatSound) {
                bg = AudioSystem.getClip();
                bg.open(ais);
            } else {
                se = AudioSystem.getClip();
                se.open(ais);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play(boolean whatSound) {
        if (isMuted) return;

        if (whatSound) {
            bg.setFramePosition((int) bgFramePosition);
            bg.start();
        } else {
            se.start();
        }
    }

    public void loop() {
        if (!isMuted) {
            bg.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void off() {
        if (!isMuted) {
            if (bg != null && bg.isRunning()) {
                bgFramePosition = bg.getFramePosition();
                bg.stop();
            }
            if (se != null && se.isRunning()) {
                se.stop();
            }
            isMuted = true;
        }
    }

    public void on() {
        if (isMuted) {
            isMuted = false;
            if (bg != null) {
                bg.setFramePosition((int) bgFramePosition);
                bg.start();
                bg.loop(Clip.LOOP_CONTINUOUSLY);
            }
        }
    }

    public void stop() {
        if(bg != null)
            bg.stop();
    }
}