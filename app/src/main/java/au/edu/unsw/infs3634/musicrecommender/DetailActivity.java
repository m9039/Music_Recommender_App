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

    //Initialise variables
    TextView SongName, Artist, Album, YearProduced, Genre, Rating, Review;
    ImageView ImageSpot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Assign variables
        SongName = findViewById(R.id.tvTitle);
        Artist = findViewById(R.id.tvArtist);
        Album = findViewById(R.id.tvAlbum);
        YearProduced = findViewById(R.id.tvYearProduced);
        Genre = findViewById(R.id.tvGenre);
        Rating = findViewById(R.id.tvRating);
        Review = findViewById(R.id.tvReview);
        ImageSpot = findViewById(R.id.ivAlbumSpot);

        //Launch display data method
        displayData();

        //When user clicks search button, geniusSearch method is launched
        Button btnBrowser = findViewById(R.id.btnSearch);
        btnBrowser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                geniusSearch();
            }
        });

        //When user clicks on play button, the song name is transfewred to AudioActivity where the song is played
        ImageView btnMusicPlayer = findViewById(R.id.btnPlay);
        btnMusicPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sendSong = song.getSong();
                Intent intent = new Intent(DetailActivity.this, AudioActivity.class);
                intent.putExtra("receiveSong", sendSong);
                startActivity(intent);
            }
        });
    }

    //Displays data in textview and imageview
    public void displayData() {
        //Gets the intent from main activity
        Intent intent = getIntent();
        String message = intent.getStringExtra("eMessage"); //id
        song = Song.getSong(message);

        //Set actionbar title as song name | artist
        setTitle(song.getSong() + " | " + song.getArtist());   //display song and artist in title

        //Set data into respective textview/imageview
        SongName.setText(song.getSong());
        Artist.setText(song.getArtist());
        Album.setText(song.getAlbum());
        YearProduced.setText(""+song.getYearProduced());
        Genre.setText(song.getGenre());
        Rating.setText(""+song.getRating());
        Review.setText(song.getReview());
        ImageSpot.setImageResource(song.getSpotify());
    }

    //Implicit intent on sending specific url to the browser
    public void geniusSearch() {
        Intent intent = getIntent();
        String message = intent.getStringExtra("eMessage"); //id NOT SURE IF THIS LINE IS NEEDED
        Song song = Song.getSong(message);
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(song.getWebsite()));
        startActivity(i);
    }
}