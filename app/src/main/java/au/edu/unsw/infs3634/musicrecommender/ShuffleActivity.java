package au.edu.unsw.infs3634.musicrecommender;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ShuffleActivity extends AppCompatActivity {
    public Song song;
    public ArrayList<Song> mSongs = Song.getSongs();

    //Initialise variable
    TextView playerPosition, playerDuration,audioSong, audioArtist;
    SeekBar seekBar;
    ImageView btnPrevious, btnPlay, btnPause, btnNext, audioImage;

    MediaPlayer mediaPlayer;
    Handler handler = new Handler();
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shuffle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Assign variable
        playerPosition = findViewById(R.id.player_position);
        playerDuration = findViewById(R.id.player_duration);
        seekBar = findViewById(R.id.seek_bar);
        btnPrevious = findViewById(R.id.btn_previous);
        btnPlay = findViewById(R.id.btn_play);
        btnPause = findViewById(R.id.btn_pause);
        btnNext = findViewById(R.id.btn_next);
        audioSong = findViewById(R.id.tvAudioSong);
        audioArtist = findViewById(R.id.tvAudioArtist);
        audioImage = findViewById(R.id.audioImage);

        Intent intent = getIntent();
        String randomSong = intent.getStringExtra("receiveRandom");
        //Set title as song name
        song = Song.getSong(randomSong);
        audioSong.setText(song.getSong());
        setTitle("Shuffle Mai's Top 10");
        audioArtist.setText(song.getArtist());
        audioImage.setImageResource(song.getImage());

        btnPlay.setVisibility(View.GONE);

        initialiseMediaPlayer();
        initialiseRunnable();
        getDuration();
        startMediaPlayer();
        playButtonClicked();
        pauseButtonClicked();
        nextButtonClicked();
        previousButtonClicked();
        seekbarChanged();
        setSongCompletion();
    }

    //Initialise media player
    public void initialiseMediaPlayer() {
        mediaPlayer = mediaPlayer.create(this,song.getAudio());
    }

    //Initialise runnable
    public void initialiseRunnable(){
        runnable = new Runnable() {
            @Override
            public void run() {
                //Set progress on seek bar
                seekBar.setProgress(mediaPlayer.getCurrentPosition());
                //Handler post delay for 0.5 seconds
                handler.postDelayed(this,500);
            }
        };
    }

    //Get duration of media player
    public void getDuration(){
        int duration = mediaPlayer.getDuration();
        //Convert millisecond to minute and second
        String sDuration = convertFormat(duration);
        //Set duration on text view
        playerDuration.setText(sDuration);
    }

    //Start media player when activity launches
    public void startMediaPlayer(){
        mediaPlayer.start();
        //Set current position on text view
        playerPosition.setText(convertFormat(mediaPlayer.getCurrentPosition()));
        //Set max on seek bar
        seekBar.setMax(mediaPlayer.getDuration());
        //Start handler
        handler.postDelayed(runnable,0);
    }

    //When play button is clicked
    public void playButtonClicked() {
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Hide play button
                btnPlay.setVisibility(View.GONE);
                //Show pause button
                btnPause.setVisibility(View.VISIBLE);
                //Start media player
                mediaPlayer.start();
                //Set max on seek bar
                seekBar.setMax(mediaPlayer.getDuration());
                //Start handler
                handler.postDelayed(runnable,0);
            }
        });
    }

    //When pause button is clicked
    public void pauseButtonClicked() {
        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Hide pause button
                btnPause.setVisibility(View.GONE);
                //Show play button
                btnPlay.setVisibility(View.VISIBLE);
                //Pause media player
                mediaPlayer.pause();
                //Stop handler
                handler.removeCallbacks(runnable);
            }
        });
    }

    //When fast forward button is clicked
    public void nextButtonClicked() {
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                releaseMediaPlayer();

                Random random = new Random();
                int randomPosition = random.nextInt(mSongs.size() + 1);
                String randomSong = mSongs.get(randomPosition).getSong();

                song = Song.getSong(randomSong);
                audioSong.setText(song.getSong());
                audioArtist.setText(song.getArtist());
                audioImage.setImageResource(song.getImage());

                initialiseMediaPlayer();
                initialiseRunnable();
                getDuration();
                startMediaPlayer();
                playButtonClicked();
                pauseButtonClicked();
                nextButtonClicked();
                previousButtonClicked();
                seekbarChanged();
                setSongCompletion();
            }
        });
    }

    //When rewind button is clicked
    public void previousButtonClicked() {
        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                releaseMediaPlayer();

                Random random = new Random();
                int randomPosition = random.nextInt(mSongs.size() - 1);
//                if ((randomPosition - 1) < 0 ? (mSongs.size()) - 1 ) : (randomPosition - 1));
                String randomSong = mSongs.get(randomPosition).getSong();

                song = Song.getSong(randomSong);
                audioSong.setText(song.getSong());
                audioArtist.setText(song.getArtist());
                audioImage.setImageResource(song.getImage());

                initialiseMediaPlayer();
                initialiseRunnable();
                getDuration();
                startMediaPlayer();
                playButtonClicked();
                pauseButtonClicked();
                nextButtonClicked();
                previousButtonClicked();
                seekbarChanged();
                setSongCompletion();
            }
        });
    }

    //When user interacts with seek bar e.g. audio scrubbing
    public void seekbarChanged () {
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //Check condition
                if (fromUser){
                    //When drag the seek bar
                    //Set progress on seek bar
                    mediaPlayer.seekTo(progress);
                }
                //Set current position on text view
                playerPosition.setText(convertFormat(mediaPlayer.getCurrentPosition()));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    //Once the song has finished playing/reached the end
    public void setSongCompletion() {
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                //Hide pause button
                btnPause.setVisibility(View.GONE);
                //Show play button
                btnPlay.setVisibility(View.VISIBLE);
                //Set media player to initial position
                mediaPlayer.seekTo(0);
            }
        });
    }

    //Stops the media player
    private void releaseMediaPlayer() {
        if(mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
    }

    //When back button is clicked, media player is stopped and returns back to detail activity
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id==android.R.id.home) {
            releaseMediaPlayer();
            finish();
        }
        return true;
    }

    //Converting milliseconds
    @SuppressLint("DefaultLocale")
    private String convertFormat(int duration) {
        return String.format("%02d:%02d"
                , TimeUnit.MILLISECONDS.toMinutes(duration)
                , TimeUnit.MILLISECONDS.toSeconds(duration) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration)));
    }
}