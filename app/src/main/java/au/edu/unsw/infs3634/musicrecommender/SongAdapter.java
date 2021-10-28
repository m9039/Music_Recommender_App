package au.edu.unsw.infs3634.musicrecommender;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SongAdapter extends RecyclerView.Adapter <SongAdapter.MyViewHolder> implements Filterable{
    //Initialise variables
    public ArrayList<Song> mSongs;
    public ArrayList<Song> mSongsFiltered;
    public clickListener mListener;

    public SongAdapter(Context context, ArrayList<Song> songs, clickListener listener ){
        this.mSongs = songs;

        //Everytime you search, you keep original copy
        this.mSongsFiltered = songs;
        this.mListener = listener;
    }

    //Inflate the row layout when needed
    @NonNull
    @Override
    public SongAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
        return new MyViewHolder(view, this, mListener);
    }

    //Bind data to textview elements in each row
    @Override
    public void onBindViewHolder(@NonNull SongAdapter.MyViewHolder holder, int position) {
        Song song = mSongsFiltered.get(position);

        int songPosition = position + 1;
        holder.ID.setText(String.valueOf(songPosition));

        String songNames = song.getSong();
        holder.song.setText(songNames);

        String songArtists = song.getArtist();
        holder.artist.setText(songArtists);

        String songGenres = song.getGenre();
        holder.genre.setText(" · "+ songGenres);

        int songRatings = song.getRating();
        holder.rating.setText(String.valueOf(songRatings));

        int songImage = song.getImage();
        holder.image.setImageResource(songImage);
    }

    //Counts total number of rows on the list
    @Override
    public int getItemCount() {
        return mSongsFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                //Converting sequence to string
                String charString = charSequence.toString();
                //Check to make sure char string not empty
                if (charString.isEmpty()) {
                    //Returns original list
                    mSongsFiltered = mSongs;
                } else {
                    ArrayList<Song> filteredList = new ArrayList<>();
                    for (Song song : mSongs) {
                        //If search value is included in song names
                        if (song.getSong().toLowerCase().contains(charString.toLowerCase())) {
                            //Adds it to the new filtered list
                            filteredList.add(song);
                        //If search value is included in artist names
                        } else if (song.getArtist().toLowerCase().contains(charString.toLowerCase())) {
                            //Adds it to the new filtered list
                            filteredList.add(song);
                        //If search value is included in genre names
                        } else if (song.getGenre().toLowerCase().contains(charString.toLowerCase())) {
                            //Adds it to the new filtered list
                            filteredList.add(song);
                        }
                        mSongsFiltered = filteredList;
                    }
                }
                //Displays the filtered list of search terms
                FilterResults filterResults = new FilterResults();
                filterResults.values = mSongsFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults results) {
                mSongsFiltered = (ArrayList<Song>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    //This method creates a view holder
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        //Initialise variables
        public final TextView ID, song, artist, genre, rating;
        public final ImageView image;
        clickListener mListener;
        final SongAdapter songAdapter;

        public MyViewHolder(@NonNull View view, SongAdapter songAdapter, clickListener mListener) {
            super(view);
            //Assign variables
            ID = view.findViewById(R.id.tvRecyclerID);
            song = view.findViewById(R.id.tvRecyclerSong);
            artist = view.findViewById(R.id.tvRecyclerArtist);
            genre = view.findViewById(R.id.tvRecyclerGenre);
            rating = view.findViewById(R.id.tvRecyclerRating);
            image = view.findViewById(R.id.ivAlbumArt);
            this.songAdapter = songAdapter;
            this.mListener = mListener;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mListener.onClick(getAdapterPosition());
        }
    }

    public interface clickListener{
        void onClick(int position);
    }

    //Sort method
    public void sort(final int sortMethod){
        //Check that it is not empty
        if(mSongsFiltered.size() > 0){
            Collections.sort(mSongsFiltered, new Comparator<Song>() {
                @Override
                public int compare(Song o1, Song o2) {
                    if (sortMethod == 1){
                        //Sort by song name
                        return o1.getSong().compareTo(o2.getSong());
                    } else if (sortMethod == 2){
                        //Sort by artist
                        return o1.getArtist().compareTo(o2.getArtist());
                    } else if (sortMethod == 3){
                        //Sort by genre
                        return o1.getGenre().compareTo(o2.getGenre());
                    } else if (sortMethod == 4){
                        //Sort by rating high to low
                        return o2.getRating().compareTo(o1.getRating());
                    } else if (sortMethod == 5){
                        //Sort by rating low to high
                        return o1.getRating().compareTo(o2.getRating());
                    } else if (sortMethod == 6){
                        //Sort by default
                        return o1.getId().compareTo(o2.getId());
                    }
                    //If not specified it will sort by song ID i.e. default
                    return o1.getId().compareTo(o2.getId());
                }
            });
        }
        notifyDataSetChanged();
    }
}
