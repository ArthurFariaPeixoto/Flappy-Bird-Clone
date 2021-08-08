package main;

import java.applet.Applet;
import java.applet.AudioClip;

public class Sound {

    private AudioClip clip;

    public static final Sound point = new Sound("/sfx_point.wav");
    public static final Sound wing = new Sound("/sfx_wing.wav");
    public static final Sound hurt = new Sound("/sfx_hurt.wav");

    private Sound(String name) {
        try {
            clip = Applet.newAudioClip(Sound.class.getResource(name));
        }catch(Throwable e) {}
    }

    public void play() {
        try {
            new Thread(() -> clip.play()).start();
        }catch(Throwable e) {}
    }

    public void loop() {
        try {
            new Thread(() -> clip.loop()).start();
        }catch(Throwable e) {}
    }

    public void stop(){
        clip.stop();
    }
}