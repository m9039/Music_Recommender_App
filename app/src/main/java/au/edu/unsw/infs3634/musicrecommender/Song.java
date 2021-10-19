package au.edu.unsw.infs3634.musicrecommender;

import java.util.ArrayList;

public class Song {
    public Song(Integer id, String song, String artist, String album, Integer yearProduced, String genre, Integer rating, String website, Integer image, Integer spotify, Integer audio, String review) {
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
        this.review = review;
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
    private String review;


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

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }



    public static ArrayList<Song> getSongs() {
        ArrayList<Song> songs = new ArrayList<>();
        songs.add(new Song(1, "I Love Kanye", "Kanye West", "The Life Of Pablo", 2016, "Hip-Hop/Rap", 5, "https://genius.com/Kanye-west-i-love-kanye-lyrics", R.drawable.ilovekanye_kanyewest, R.drawable.kanye, R.raw.ilovekanye, "The \"I Love Kanye\" lyrics move from the comments on his music to the comments about him making news. But this being Kanye, he's able to turn it all around and make it super meta. I love Kanye is the most Kanye song of all time. RHYMED KANYE WITH KANYE 25 TIMES ON \"I LOVE KANYE\" this is real life \uD83D\uDE4F"));
        songs.add(new Song(2, "Call Out My Name", "The Weeknd", "My Dear Melancholy,", 2018, "R&B/Soul", 5, "https://genius.com/The-weeknd-call-out-my-name-lyrics", R.drawable.calloutmyname_theweeknd, R.drawable.theweeknd, R.raw.calloutmyname, "My favourite song from him I wanna go back to 2018!!!! ‘Call Out My Name’ recalls the instrumentals from his previous hit song ‘Earned It’ with an even better twist and a relatable backstory. The opening track of this album, “Call Out My Name” is one of my favourite songs by the Weeknd, because it is a sad song, and therefore a beautiful one. "));
        songs.add(new Song(3, "Thinkin Bout You", "Frank Ocean", "channel ORANGE", 2012, "R&B", 5, "https://genius.com/Frank-ocean-thinkin-bout-you-lyrics", R.drawable.thinkinboutyou_frankocean, R.drawable.frankocean, R.raw.thinkinboutyou, "The fact that nearly every single Gen Z and Millennial teenager that's ever thought they were in love has distinct memories behind this song speaks as a testament to how truly memorable it is. It's one of the defining songs of the previous decade. Seriously, ask any cuffed pant van wearing 20 something if they have memories about THIS song and you will access a nearly infinite archive of insufferable heartbreak stories. Me included."));
        songs.add(new Song(4, "See You Again", "Tyler, The Creator, Kali Uchis", "Flower Boy", 2017, "Hip-Hop", 5,"https://genius.com/Tyler-the-creator-see-you-again-lyrics", R.drawable.seeyouagain_tyler, R.drawable.tylerthecreator, R.raw.seeyouagain, "Introspective, open, vulnerable, self-deprecating, lush, poignant, real. Tyler made an album that not only shows who he is beneath the exterior he built up for years with gritty, hard-hitting rap tracks, but made an album that will age like the finest of wines. This song felt really nostalgic and sounded great. "));
        songs.add(new Song(5, "Don't", "Bryson Tiller", "T R A P S O U L", 2015, "R&B/Soul", 5,"https://genius.com/Bryson-tiller-dont-lyrics", R.drawable.dont_brysontiller, R.drawable.brysontiller, R.raw.dont, "Modern classic Tiller kills it on this. The more romantic tracks are way better than the bravado ones – ‘Don’t’ is fire"));
        songs.add(new Song(6, "I Fall Apart", "Post Malone", "Stoney", 2016, "Hip-Hop/Rap", 4,"https://genius.com/Post-malone-i-fall-apart-lyrics", R.drawable.ifallapart_postmalone, R.drawable.postmalone, R.raw.ifallapart, "It really is the very best Posty has to offer, before his music turned generic and passable, the millions I guess diluted his care for good music. Here we have some great production, even emotional tracks mixed with beats that massage the brain and go really well with a sunset and a blunt. "));
        songs.add(new Song(7, "ICY GRL", "Saweetie", "High Maintenance", 2018, "Hip-Hop/Rap", 5,"https://genius.com/Saweetie-icy-grl-lyrics", R.drawable.icygrl_saweetie, R.drawable.saweetie, R.raw.icygrl, "Saweetie is a really interesting artist. She's a really good rapper, her flows are fluid and engaging. Her distinct personality is moulded on songs like \"Icy Grl\" which is absolutely a fire takeaway from this EP. "));
        songs.add(new Song(8, "Every Kind Of Way", "H.E.R.", "H.E.R. Volume 2", 2017, "R&B/Soul", 4,"https://genius.com/Her-every-kind-of-way-lyrics", R.drawable.everykindofway_her, R.drawable.her, R.raw.everykindofway, "Such a music experience is meant to be appreciated alone, shades and headphones on, sat in the backseat of a Uber, or by the window of a bus. You can discover a girl passionately in love, physically and mentally. One day wild and on fire, the other day cold, silent and distant, you can feel her excitement, her pain and her surprises."));
        songs.add(new Song(9, "3005", "Childish Gambino", "Because The Internet", 2013, "Hip-Hop/Rap", 5,"https://genius.com/Childish-gambino-v-3005-lyrics", R.drawable.v3005_childishgambino, R.drawable.childishgambino, R.raw.threethousandfive, "This is the peak of rap Childish Gambino, this song is just perfect and it recontextualizes Gambinos sometimes goofy and corny punchline rap to just be this like vale of trying to deal with just this never-ending anxiety of just life. The beat is just so pop-y and smooth but perfect with its squeaky chords and the perfect chorus, it just has this really pop-y upbeat feel to it while being incredibly melancholic and bittersweet. Just a straight up perfect song, perfect for the constant existential dread."));
        songs.add(new Song(10, "As Long As You Love Me", "Justin Bieber", "Believe", 2012, "Pop", 4,"https://genius.com/Justin-bieber-as-long-as-you-love-me-lyrics", R.drawable.aslongasyouloveme_justinbieber, R.drawable.justinbieber, R.raw.aslongasyouloveme, "The song itself tells the simple story of a young couple struggling to stay strong and stay together out there in the cold, hard world. The slower pace of this song sets it apart from Bieber's pop-song pack and highlights the heartthrob's strong vocals. They play well off of Big Sean's rhymes, resulting in one of the stronger tunes to come from this album so far. "));

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
