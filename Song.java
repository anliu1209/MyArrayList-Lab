public class Song {
    private String name;
    private String artist;
    private String album;

    public Song(String name, String artist, String album) {
        this.name = name;
        this.artist = artist;
        this.album = album;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Song)) {
            return false;
        }

        Song other = (Song) o;
        return sameText(name, other.getName()) && sameText(artist, other.getArtist()) && sameText(album, other.getAlbum());
    }

    private boolean sameText(String first, String second) {
        if (first == null) {
            return second == null;
        }
        return first.equals(second);
    }

    @Override
    public String toString() {
        return name + ", " + artist + ", " + album;
    }
}
