package au.edu.unsw.infs3634.musicrecommender;

import java.util.ArrayList;

public class Song {
    public Song(Integer id, String song, String artist, String album, Integer yearProduced, String genre, Integer rating, String website) {
        this.id = id;
        this.song = song;
        this.artist = artist;
        this.album = album;
        this.yearProduced = yearProduced;
        this.genre = genre;
        this.rating = rating;
        this.website = website;
    }

    private Integer id;
    private String song;
    private String artist;
    private String album;
    private Integer yearProduced;
    private String genre;
    private Integer rating;
    private String website;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSong() {
        return song;
    }

    public void setSong(String title) {
        this.song = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public Integer getYearProduced() {
        return yearProduced;
    }

    public void setYearProduced(Integer yearProduced) {
        this.yearProduced = yearProduced;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }


    public static ArrayList<Song> getSongs() {
        ArrayList<Song> songs = new ArrayList<>();
        songs.add(new Song(1, "I Love Kanye", "Kanye West", "The Life Of Pablo", 2016, "Hip-Hop/Rap", 5, "https://genius.com/Kanye-west-i-love-kanye-lyrics"));
        songs.add(new Song(2, "Call Out My Name", "The Weeknd", "My Dear Melancholy,", 2018, "R&B/Soul", 5, "https://genius.com/The-weeknd-call-out-my-name-lyrics"));
        songs.add(new Song(3, "Thinkin Bout You", "Frank Ocean", "channel ORANGE", 2012, "R&B", 5, "https://genius.com/Frank-ocean-thinkin-bout-you-lyrics"));
        songs.add(new Song(4, "See You Again (feat. Kali Uchis)", "Tyler, The Creator, Kali Uchis", "Flower Boy", 2017, "Hip-Hop/Rap", 5,"https://genius.com/Tyler-the-creator-see-you-again-lyrics"));
        songs.add(new Song(5, "Don't", "Bryson Tiller", "T R A P S O U L", 2015, "R&B/Soul", 5,"https://genius.com/Bryson-tiller-dont-lyrics"));
        songs.add(new Song(6, "I Fall Apart", "Post Malone", "Stoney", 2016, "Hip-Hop/Rap", 4,"https://genius.com/Post-malone-i-fall-apart-lyrics"));
        songs.add(new Song(7, "ICY GRL", "Saweetie", "High Maintenance", 2018, "Hip-Hop/Rap", 5,"https://genius.com/Saweetie-icy-grl-lyrics"));
        songs.add(new Song(8, "Every Kind Of Way", "H.E.R.", "H.E.R. Volume 2", 2017, "R&B/Soul", 4,"https://genius.com/Her-every-kind-of-way-lyrics"));
        songs.add(new Song(9, "3005", "Childish Gambino", "Because The Internet", 2013, "Hip-Hop/Rap", 5,"https://genius.com/Childish-gambino-v-3005-lyrics"));
        songs.add(new Song(10, "As Long As You Love Me", "Justin Bieber", "Believe", 2012, "Pop", 4,"https://genius.com/Justin-bieber-as-long-as-you-love-me-lyrics"));

        return songs;
    }

    public static Song getSong (String Song) {
        Song assignedSong = null;
        for(Song song: getSongs()){
            if(Song.equals(song.getSong())){
                assignedSong = song;
            }
        }
        return assignedSong;
    }

}
