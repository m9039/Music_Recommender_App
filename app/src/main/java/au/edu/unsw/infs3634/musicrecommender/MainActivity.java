package au.edu.unsw.infs3634.musicrecommender;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SearchView;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements SongAdapter.clickListener {

    public ArrayList<Song> mSongs = Song.getSongs();
    public RecyclerView mRecyclerView;
    public SongAdapter songAdapter;
    ImageButton ibShuffle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ibShuffle = findViewById(R.id.ibShuffle);

        //Instantiate recyclerview
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        songAdapter = new SongAdapter(this, mSongs, this);
        mRecyclerView.setAdapter(songAdapter);

        shuffleButtonClicked();
    }

    //When user clicks on a song
    public void onClick(int position){
        String message = mSongs.get(position).getSong();
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra("eMessage", message);
        startActivity(intent);
    }

    //adds the menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);       //specifies our menu resource

        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        //listen to text changes in search view
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {        //when user types something and presses enter
                songAdapter.getFilter().filter(query);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {            //when there are text changes
                songAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    //react to user interaction with the menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.sortName:
                songAdapter.sort(1);
                return true;
            case R.id.sortArtist:
                songAdapter.sort(2);
                return true;
            case R.id.sortGenre:
                songAdapter.sort(3);
                return true;
            case R.id.sortRatingHigh:
                songAdapter.sort(4);
                return true;
            case R.id.sortRatingLow:
                songAdapter.sort(5);
                return true;
            case R.id.sortDefault:
                songAdapter.sort(6);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //When shuffle play button is clicked
    public void shuffleButtonClicked() {
        ibShuffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random random = new Random();
                int randomPosition = random.nextInt(mSongs.size());
                String randomSong = mSongs.get(randomPosition).getSong();
                Intent intent = new Intent(MainActivity.this, ShuffleActivity.class);
                intent.putExtra("receiveRandom", randomSong);
                startActivity(intent);
            }
        });
    }

    public int getRandomSong(int integer) {
        Random random = new Random();
        //Generates random number between 0 and mSongs.size
        return random.nextInt(integer + 1);
    }
}