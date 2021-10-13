package au.edu.unsw.infs3634.musicrecommender;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Button btn = findViewById(R.id.btnSearch);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                geniusSearch();
            }
        });

        Intent intent = getIntent();
        String message = intent.getStringExtra("eMessage"); //id

        Song song = Song.getSong(message);
        setTitle("Mai's Top 10");
//        setTitle(country.getCountryCode()); //displays country code in title

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

    }

    public void geniusSearch() {
        Intent intent = getIntent();
        String message = intent.getStringExtra("eMessage"); //id NOT SURE IF THIS LINE IS NEEDED
        Song song = Song.getSong(message);
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(song.getWebsite()));
        startActivity(i);
    }
}