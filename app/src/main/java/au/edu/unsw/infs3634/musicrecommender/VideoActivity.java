package au.edu.unsw.infs3634.musicrecommender;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import java.util.concurrent.TimeUnit;

public class VideoActivity extends AppCompatActivity {
    public Song song;

    //Initialise variable
    TextView playerPosition, playerDuration,audioSong, audioArtist, audioRating;
    SeekBar seekBar;
    ImageView btnRewind, btnPlay, btnPause, btnForward, audioImage;

    MediaPlayer mediaPlayer;
    Handler handler = new Handler();
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Assign variable
        playerPosition = findViewById(R.id.player_position);
        playerDuration = findViewById(R.id.player_duration);
        seekBar = findViewById(R.id.seek_bar);
        btnRewind = findViewById(R.id.btn_rewind);
        btnPlay = findViewById(R.id.btn_play);
        btnPause = findViewById(R.id.btn_pause);
        btnForward = findViewById(R.id.btn_fastforward);
        audioSong = findViewById(R.id.tvAudioSong);
        audioArtist = findViewById(R.id.tvAudioArtist);
        audioImage = findViewById(R.id.audioImage);

        Intent intent = getIntent();
        String sendSong = intent.getStringExtra("receiveSong");
        //Set title as song name
        song = Song.getSong(sendSong);
        audioSong.setText(song.getSong());
        setTitle(song.getSong());
        audioArtist.setText(song.getArtist());
        audioImage.setImageResource(song.getImage());

        //Initialise media player
        mediaPlayer = mediaPlayer.create(this,song.getAudio());

        //Initialise runnable
        runnable = new Runnable() {
            @Override
            public void run() {
                //Set progress on seek bar
                seekBar.setProgress(mediaPlayer.getCurrentPosition());
                //Handler post delay for 0.5 seconds
                handler.postDelayed(this,500);
            }
        };

        //Get duration of media player
        int duration = mediaPlayer.getDuration();
        //Convert millisecond to minute and second
        String sDuration = convertFormat(duration);
        //Set duration on text view
        playerDuration.setText(sDuration);

        //Start media player when activity launches
        mediaPlayer.start();
        //Set current position on text view
        playerPosition.setText(convertFormat(mediaPlayer.getCurrentPosition()));
        //Set max on seek bar
        seekBar.setMax(mediaPlayer.getDuration());
        //Start handler
        handler.postDelayed(runnable,0);

        //When play button is clicked
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

        //When pause button is clicked
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

        ////When fast forward button is clicked
        btnForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Get current position of media player
                int currentPosition = mediaPlayer.getCurrentPosition();
                //Get duration of media player
                int duration = mediaPlayer.getDuration();
                //Check condition
                if (mediaPlayer.isPlaying() && duration != currentPosition){
                    //When media player is playing and duration is not equal to current position
                    //Fast forward for 5 seconds
                    currentPosition = currentPosition + 5000;
                    //Set current position on text view
                    playerPosition.setText(convertFormat(currentPosition));
                    //Set progress on seek bar
                    mediaPlayer.seekTo(currentPosition);
                }
            }
        });

        //When rewind button is clicked
        btnRewind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Get current position of media player
                int currentPosition = mediaPlayer.getCurrentPosition();
                //Check condition
                if (mediaPlayer.isPlaying() && currentPosition > 5000){
                    //When media is playing and current position is greater than 5 seconds
                    //Rewind for 5 seconds
                    currentPosition = currentPosition - 5000;
                    //Get current position on text view
                    playerPosition.setText(convertFormat(currentPosition));
                    //Set progress on seek bar
                    mediaPlayer.seekTo(currentPosition);
                }
            }
        });

        //When user interacts with seek bar e.g. audio scrubbing
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
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //Once the song has finished playing/reached the end
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

    //Converting milliseconds
    @SuppressLint("DefaultLocale")
    private String convertFormat(int duration) {
        return String.format("%02d:%02d"
        , TimeUnit.MILLISECONDS.toMinutes(duration)
        , TimeUnit.MILLISECONDS.toSeconds(duration) -
        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration)));
    }
}