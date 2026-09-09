public class Runner {
    public static void main(String[] args) {
        MyArrayList<Song> songs = new MyArrayList<Song>();
        addStartingSongs(songs);

        System.out.println("Starting playlist:");
        displaySongs(songs);

        randomize(songs);
        System.out.println("\nRandomized playlist:");
        displaySongs(songs);

        java.util.Scanner input = new java.util.Scanner(System.in);
        System.out.println("\nAdd a song to the playlist.");
        System.out.print("Song name: ");
        String name = input.nextLine();
        System.out.print("Artist: ");
        String artist = input.nextLine();
        System.out.print("Album: ");
        String album = input.nextLine();
        System.out.print("Location (1 to " + (songs.size() + 1) + "): ");

        if (input.hasNextInt()) {
            int location = input.nextInt();
            try {
                addSong(songs, name, artist, album, location);
                System.out.println("\nSong added successfully:");
                displaySongs(songs);
            } catch (IllegalArgumentException error) {
                System.out.println(error.getMessage());
            }
        } else {
            System.out.println("The location must be a whole number.");
        }

        input.close();
    }

    private static void addStartingSongs(MyArrayList<Song> songs) {
        songs.add(new Song("Blinding Lights", "The Weeknd", "After Hours"));
        songs.add(new Song("Levitating", "Dua Lipa", "Future Nostalgia"));
        songs.add(new Song("As It Was", "Harry Styles", "Harry's House"));
        songs.add(new Song("bad guy", "Billie Eilish", "WHEN WE ALL FALL ASLEEP, WHERE DO WE GO?"));
        songs.add(new Song("Save Your Tears", "The Weeknd", "After Hours"));
        songs.add(new Song("Watermelon Sugar", "Harry Styles", "Fine Line"));
        songs.add(new Song("Anti-Hero", "Taylor Swift", "Midnights"));
    }

    private static void displaySongs(MyArrayList<Song> songs) {
        for (int i = 0; i < songs.size(); i++) {
            System.out.println((i + 1) + ". " + songs.get(i));
        }
    }

    private static void randomize(MyArrayList<Song> songs) {
        for (int i = songs.size() - 1; i > 0; i--) {
            int randomIndex = (int) (Math.random() * (i + 1));
            Song temporary = songs.get(i);
            songs.set(i, songs.get(randomIndex));
            songs.set(randomIndex, temporary);
        }
    }

    private static void addSong(MyArrayList<Song> songs, String name,
            String artist, String album, int location) {
        if (location < 1 || location > songs.size() + 1) {
            throw new IllegalArgumentException(
                    "Location must be between 1 and " + (songs.size() + 1) + ".");
        }

        songs.add(location - 1, new Song(name, artist, album));
    }
}
