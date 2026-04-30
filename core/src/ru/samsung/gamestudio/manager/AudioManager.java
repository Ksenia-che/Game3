package ru.samsung.gamestudio.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import ru.samsung.gamestudio.GameResources;

public class AudioManager {
    public boolean isSoundOn;
    public boolean isMusicOn;
    public Music backgroundMusic;
    public Sound shootSound;
    public Sound explosionSound;
    public AudioManager() {
        isMusicOn = true;
        isSoundOn = true;
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal(GameResources.BACKGROUND_MUSIC_PATH));
        shootSound = Gdx.audio.newSound(Gdx.files.internal(GameResources.SHOOT_SOUND_PATH));
        explosionSound = Gdx.audio.newSound(Gdx.files.internal(GameResources.DESTROY_SOUND_PATH));

        backgroundMusic.setVolume(0.2f);
        backgroundMusic.setLooping(true);

        backgroundMusic.play();
    }
    public void updateMusicFlag() {
        if (isMusicOn) backgroundMusic.play();
        else backgroundMusic.stop();
    }
}
