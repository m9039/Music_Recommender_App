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
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements SongAdapter.clickListener {

    //Initialise variables
    public ArrayList<Song> mSongs = Song.getSongs();
    public ArrayList<Song> mShuffleSongs = Song.getSongs();
    public RecyclerView mRecyclerView;
    public SongAdapter songAdapter;
    ImageButton ibShuffle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Instantiate variable
        ibShuffle = findViewById(R.id.ibShuffle);

        //Instantiate recyclerview
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        songAdapter = new SongAdapter(this, mSongs, this);
        mRecyclerView.setAdapter(songAdapter);

        //Launce shuffleButtonClicked method
        shuffleButtonClicked();
    }

    //When user clicks on a song in recycler view
    public void onClick(int position){
        String message = mSongs.get(position).getSong();
        //Launch detail activity and pass data over through an intent
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra("eMessage", message);
        startActivity(intent);
    }

    //Adds the menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();

        //Specifies our menu resource
        inflater.inflate(R.menu.menu_main, menu);

        //Initialise variable
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();

        //Listen for text changes in search view
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            //When user types something and presses enter
            public boolean onQueryTextSubmit(String query) {
                songAdapter.getFilter().filter(query);
                return false;
            }
            @Override
            //When there are text changes
            public boolean onQueryTextChange(String newText) {
                songAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    //React to user interaction with the menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            //When user wants to sort by name
            case R.id.sortName:
                //Calls upon sort method 1 from the songAdaptor
                songAdapter.sort(1);
                return true;
            //When user want to sort by artist
            case R.id.sortArtist:
                //Calls upon sort method 2 from the songAdaptor
                songAdapter.sort(2);
                return true;
            //When user wants to sort by genre
            case R.id.sortGenre:
                //Calls upon sort method 3 from the songAdaptor
                songAdapter.sort(3);
                return true;
            //When user wants to sort by rating: high-low
            case R.id.sortRatingHigh:
                //Calls upon sort method 4 from the songAdaptor
                songAdapter.sort(4);
                return true;
            //When user wants to sort by rating: low-high
            case R.id.sortRatingLow:
                //Calls upon sort method 5 from the songAdaptor
                songAdapter.sort(5);
                return true;
            //When user wants to sort by default
            case R.id.sortDefault:
                //Calls upon sort method 6 from the songAdaptor
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
                //Shuffles the arraylist
                Collections.shuffle(mShuffleSongs);
                //Updates the array with the new shuffled order
                mShuffleSongs.toArray(Song.getSongs().toArray(new Song[0]));
                //Gets the first shuffled song and passes it through the intent to be displayed in shuffle activity
                String randomSong = mShuffleSongs.get(0).getSong();
                Intent intent = new Intent(MainActivity.this, ShuffleActivity.class);
                intent.putExtra("receiveRandom", randomSong);
                startActivity(intent);
            }
        });
    }
}