import java.util.HashMap;
import java.util.Map;

interface MediaPlayer {
    void play(String audioType, String fileName);
}

interface AdvancedMediaPlayer {
    void playVlc(String fileName);
    void playMp4(String fileName);
}

class VlcPlayer implements AdvancedMediaPlayer {
    @Override
    public void playVlc(String fileName) {
        PlaybackSimulator.simulatePlayback("VLC", fileName);
    }

    @Override
    public void playMp4(String fileName) {
        // do nothing
    }
}

class Mp4Player implements AdvancedMediaPlayer {
    @Override
    public void playVlc(String fileName) {
        // do nothing
    }

    @Override
    public void playMp4(String fileName) {
        PlaybackSimulator.simulatePlayback("MP4", fileName);
    }
}

class MediaAdapter implements MediaPlayer {
    AdvancedMediaPlayer advancedMusicPlayer;

    public MediaAdapter(String audioType) {
        if (audioType.equalsIgnoreCase("vlc")) {
            advancedMusicPlayer = new VlcPlayer();
        } else if (audioType.equalsIgnoreCase("mp4")) {
            advancedMusicPlayer = new Mp4Player();
        }
    }

    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("vlc")) {
            advancedMusicPlayer.playVlc(fileName);
        } else if (audioType.equalsIgnoreCase("mp4")) {
            advancedMusicPlayer.playMp4(fileName);
        }
    }
}

class AudioPlayer implements MediaPlayer {
    MediaAdapter mediaAdapter;

    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("mp3")) {
            PlaybackSimulator.simulatePlayback("MP3", fileName);
        } else if (audioType.equalsIgnoreCase("vlc") || audioType.equalsIgnoreCase("mp4")) {
            mediaAdapter = new MediaAdapter(audioType);
            mediaAdapter.play(audioType, fileName);
        } else {
            System.out.println("Invalid media. " + audioType + " format not supported");
        }
    }
}

public class AdapterPatternDemo {
    private static Map<String, MediaFile> mediaLibrary = new HashMap<>();

    static {
        mediaLibrary.put("song.mp3", new MediaFile("song.mp3", "MP3", 3.5, 5.2));
        mediaLibrary.put("movie.mp4", new MediaFile("movie.mp4", "MP4", 1800.0, 1500.0));
        mediaLibrary.put("video.vlc", new MediaFile("video.vlc", "VLC", 60.0, 85.7));
    }

    public static void main(String[] args) {
        AudioPlayer audioPlayer = new AudioPlayer();

        audioPlayer.play("mp3", "song.mp3");
        audioPlayer.play("mp4", "movie.mp4");
        audioPlayer.play("vlc", "video.vlc");
        audioPlayer.play("avi", "mind me.avi");
    }

    public static MediaFile getMediaFile(String fileName) {
        return mediaLibrary.get(fileName);
    }
}

class PlaybackSimulator {
    public static void simulatePlayback(String format, String fileName) {
        MediaFile file = AdapterPatternDemo.getMediaFile(fileName);
        if (file != null) {
            System.out.println("Playing " + format + " file. Name: " + fileName);
            System.out.printf("File size: %.1f MB, Duration: %.1f seconds%n", file.size, file.duration);
            System.out.println("Playback started...");
            try {
                Thread.sleep(1000); // Simulate playback for 1 second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Playback finished for " + fileName);
        } else {
            System.out.println("File " + fileName + " not found in the media library.");
        }
    }
}

class MediaFile {
    String name;
    String type;
    double duration; // in seconds
    double size; // in MB

    MediaFile(String name, String type, double duration, double size) {
        this.name = name;
        this.type = type;
        this.duration = duration;
        this.size = size;
    }
}
