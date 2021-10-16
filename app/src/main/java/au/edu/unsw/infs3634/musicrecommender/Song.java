package au.edu.unsw.infs3634.musicrecommender;

import java.util.ArrayList;

public class Song {
    public Song(Integer id, String song, String artist, String album, Integer yearProduced, String genre, Integer rating, String website, Integer image, Integer spotify, Integer audio) {
        this.id = id;
        this.song = song;
        this.artist = artist;
        this.album = album;
        this.yearProduced = yearProduced;
        this.genre = genre;
        this.rating = rating;
        this.website = website;
        this.image = image;
        this.spotify = spotify;
        this.audio = audio;
    }

    private Integer id;
    private String song;
    private String artist;
    private String album;
    private Integer yearProduced;
    private String genre;
    private Integer rating;
    private String website;
    private Integer image;
    private Integer spotify;
    private Integer audio;


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

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    public Integer getSpotify() {
        return spotify;
    }

    public void setSpotify(Integer spotify) {
        this.spotify = spotify;
    }

    public Integer getAudio() { return audio; }

    public void setAudio(Integer audio) {this.audio = audio;}



    public static ArrayList<Song> getSongs() {
        ArrayList<Song> songs = new ArrayList<>();
        songs.add(new Song(1, "I Love Kanye", "Kanye West", "The Life Of Pablo", 2016, "Hip-Hop/Rap", 5, "https://genius.com/Kanye-west-i-love-kanye-lyrics", R.drawable.ilovekanye_kanyewest, R.drawable.kanye, R.raw.ilovekanye));
        songs.add(new Song(2, "Call Out My Name", "The Weeknd", "My Dear Melancholy,", 2018, "R&B/Soul", 5, "https://genius.com/The-weeknd-call-out-my-name-lyrics", R.drawable.calloutmyname_theweeknd, R.drawable.theweeknd, R.raw.calloutmyname));
        songs.add(new Song(3, "Thinkin Bout You", "Frank Ocean", "channel ORANGE", 2012, "R&B", 5, "https://genius.com/Frank-ocean-thinkin-bout-you-lyrics", R.drawable.thinkinboutyou_frankocean, R.drawable.frankocean, R.raw.thinkinboutyou));
        songs.add(new Song(4, "See You Again", "Tyler, The Creator, Kali Uchis", "Flower Boy", 2017, "Hip-Hop", 5,"https://genius.com/Tyler-the-creator-see-you-again-lyrics", R.drawable.seeyouagain_tyler, R.drawable.tylerthecreator, R.raw.seeyouagain));
        songs.add(new Song(5, "Don't", "Bryson Tiller", "T R A P S O U L", 2015, "R&B/Soul", 5,"https://genius.com/Bryson-tiller-dont-lyrics", R.drawable.dont_brysontiller, R.drawable.brysontiller, R.raw.dont));
        songs.add(new Song(6, "I Fall Apart", "Post Malone", "Stoney", 2016, "Hip-Hop/Rap", 4,"https://genius.com/Post-malone-i-fall-apart-lyrics", R.drawable.ifallapart_postmalone, R.drawable.postmalone, R.raw.ifallapart));
        songs.add(new Song(7, "ICY GRL", "Saweetie", "High Maintenance", 2018, "Hip-Hop/Rap", 5,"https://genius.com/Saweetie-icy-grl-lyrics", R.drawable.icygrl_saweetie, R.drawable.saweetie, R.raw.icygrl));
        songs.add(new Song(8, "Every Kind Of Way", "H.E.R.", "H.E.R. Volume 2", 2017, "R&B/Soul", 4,"https://genius.com/Her-every-kind-of-way-lyrics", R.drawable.everykindofway_her, R.drawable.her, R.raw.everykindofway));
        songs.add(new Song(9, "3005", "Childish Gambino", "Because The Internet", 2013, "Hip-Hop/Rap", 5,"https://genius.com/Childish-gambino-v-3005-lyrics", R.drawable.v3005_childishgambino, R.drawable.childishgambino, R.raw.threethousandfive));
        songs.add(new Song(10, "As Long As You Love Me", "Justin Bieber", "Believe", 2012, "Pop", 4,"https://genius.com/Justin-bieber-as-long-as-you-love-me-lyrics", R.drawable.aslongasyouloveme_justinbieber, R.drawable.justinbieber, R.raw.aslongasyouloveme));

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
