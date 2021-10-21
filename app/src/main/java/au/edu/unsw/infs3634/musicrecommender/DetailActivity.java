package au.edu.unsw.infs3634.musicrecommender;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    public ArrayList<Song> mSongs = Song.getSongs();
    public Song song;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button btnBrowser = findViewById(R.id.btnSearch);
        btnBrowser.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                geniusSearch();
            }
        });

        ImageView btnMusicPlayer = findViewById(R.id.btnPlay);
        btnMusicPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sendSong = song.getSong();
                Intent intent = new Intent(DetailActivity.this, VideoActivity.class);
                intent.putExtra("receiveSong", sendSong);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        String message = intent.getStringExtra("eMessage"); //id

        song = Song.getSong(message);
        setTitle(song.getSong() + " | " + song.getArtist());   //display song and artist in title

        TextView Song = findViewById(R.id.tvTitle);
        Song.setText(song.getSong());

        TextView Artist = findViewById(R.id.tvArtist);
        Artist.setText(song.getArtist());

        TextView Album = findViewById(R.id.tvAlbum);
        Album.setText(song.getAlbum());

        TextView YearProduced = findViewById(R.id.tvYearProduced);
        YearProduced.setText(""+song.getYearProduced());

        TextView Genre = findViewById(R.id.tvGenre);
        Genre.setText(song.getGenre());

        TextView Rating = findViewById(R.id.tvRating);
        Rating.setText(""+song.getRating());

        TextView Review = findViewById(R.id.tvReview);
        Review.setText(song.getReview());

        ImageView ImageSpot = findViewById(R.id.ivAlbumSpot);
        ImageSpot.setImageResource(song.getSpotify());
    }

    public void geniusSearch() {
        Intent intent = getIntent();
        String message = intent.getStringExtra("eMessage"); //id NOT SURE IF THIS LINE IS NEEDED
        Song song = Song.getSong(message);
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(song.getWebsite()));
        startActivity(i);
    }

//    public int getRandomSong() {
//
//    }
}