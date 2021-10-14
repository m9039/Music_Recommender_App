package au.edu.unsw.infs3634.musicrecommender;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class VideoActivity extends AppCompatActivity {
    //Initialise variable
    TextView playerPosition, playerDuration;
    SeekBar seekBar;
    ImageView btnRewind, btnPlay, btnPause, btnForward;

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

        //Initialise media player
        mediaPlayer = mediaPlayer.create(this,R.raw.ilovekanye);

        //Initialise runnable
    }
}