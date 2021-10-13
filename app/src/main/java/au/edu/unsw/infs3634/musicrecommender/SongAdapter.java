package au.edu.unsw.infs3634.musicrecommender;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SongAdapter extends RecyclerView.Adapter <SongAdapter.MyViewHolder> implements Filterable{
    public ArrayList<Song> mSongs;
    public ArrayList<Song> mSongsFiltered;
    public clickListener mListener;

    public SongAdapter(Context context, ArrayList<Song> songs, clickListener listener ){
        this.mSongs = songs;
        this.mSongsFiltered = songs;            //this is so that everytime you search, you keep original copy
        this.mListener = listener;
    }

    //inflate the row layout when needed
    @NonNull
    @Override
    public SongAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
        return new MyViewHolder(view, this, mListener);
    }

    //bind data to textview elements in each row
    @Override
    public void onBindViewHolder(@NonNull SongAdapter.MyViewHolder holder, int position) {
        Song song = mSongsFiltered.get(position);

        int songIDs = song.getId();
        holder.ID.setText(String.valueOf(songIDs));

        String songNames = song.getSong();
        holder.song.setText(songNames);

        String songArtists = song.getArtist();
        holder.artist.setText(songArtists);

        String songGenres = song.getGenre();
        holder.genre.setText(songGenres);

        int songRatings = song.getRating();
        holder.rating.setText(String.valueOf(songRatings));
    }

    //counts total number of rows on the list
    @Override
    public int getItemCount() {
        return mSongsFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();                //converting sequence to string
                if (charString.isEmpty()) {                                 //check to make sure char string not empty
                    mSongsFiltered = mSongs;                        //returns original list
                } else{
                    ArrayList<Song> filteredList = new ArrayList<>();
                    for(Song song : mSongs) {
                        if(song.getSong().toLowerCase().contains(charString.toLowerCase())){    //if search value is included in song names
                            filteredList.add(song);                                                //adds it to the filtered list
                        }
                    }
                    mSongsFiltered = filteredList;
                }
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

    //this method creates a view holder
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public final TextView ID, song, artist, genre, rating;
        clickListener mListener;
        final SongAdapter songAdapter;

        public MyViewHolder(@NonNull View view, SongAdapter songAdapter, clickListener mListener) {
            super(view);
            ID = view.findViewById(R.id.tvRecyclerID);
            song = view.findViewById(R.id.tvRecyclerSong);
            artist = view.findViewById(R.id.tvRecyclerArtist);
            genre = view.findViewById(R.id.tvRecyclerGenre);
            rating = view.findViewById(R.id.tvRecyclerRating);
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

    //sort method
    public void sort(final int sortMethod){
        if(mSongsFiltered.size() > 0){              //check that it is not empty
            Collections.sort(mSongsFiltered, new Comparator<Song>() {
                @Override
                public int compare(Song o1, Song o2) {
                    if (sortMethod == 1){
                        return o1.getSong().compareTo(o2.getSong());
                    } else if (sortMethod == 2){
                        return o1.getArtist().compareTo(o2.getArtist());
                    } else if (sortMethod == 3){
                        return o1.getGenre().compareTo(o2.getGenre());
                    } else if (sortMethod == 4){                        //rating high to low
                        return o2.getRating().compareTo(o1.getRating());
                    } else if (sortMethod == 5){                        //rating low to high
                        return o1.getRating().compareTo(o2.getRating());
                    }
                    return o1.getId().compareTo(o2.getId()); //if not specified it will sort by song ID
                }
            });
        }
        notifyDataSetChanged();
    }

}
