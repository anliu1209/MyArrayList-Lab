import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Screen extends JPanel implements ActionListener {
    private JTextField nameField, artistField, albumField, locationField;
    private JButton btnAdd,btnRemoveLoc, btnRemoveDet, btnSort, btnRand;

    private MyArrayList<Song> playlist;

    public Screen() {
        playlist = new MyArrayList<Song>();
        
        playlist.add(new Song("Blinding Lights", "The Weeknd", "After Hours"));
        playlist.add(new Song("Levitating", "Dua Lipa", "Future Nostalgia"));
        playlist.add(new Song("As It Was", "Harry Styles", "Harry's House"));
        playlist.add(new Song("bad guy", "Billie Eilish", "WHEN WE ALL FALL ASLEEP"));
        playlist.add(new Song("Anti-Hero", "Taylor Swift", "Midnights"));

        nameField = new JTextField(8);
        artistField = new JTextField(8);
        albumField = new JTextField(8);
        locationField = new JTextField(3);

        btnAdd = new JButton("Add");
        btnRemoveLoc = new JButton("Delete by Number");
        btnRemoveDet = new JButton("Delete by Details");
        btnSort = new JButton("Sort");
        btnRand = new JButton("Randomize");

        add(new JLabel("Name:"));
        add(nameField);
        add(new JLabel("Artist:"));
        add(artistField);
        add(new JLabel("Album:"));
        add(albumField);
        add(new JLabel("Location:"));
        add(locationField);
        add(btnAdd);
        add(btnRemoveLoc);
        add(btnRemoveDet);
        add(btnSort);
        add(btnRand);

        btnAdd.addActionListener(this);
        btnRemoveLoc.addActionListener(this);
        btnRemoveDet.addActionListener(this);
        btnSort.addActionListener(this);
        btnRand.addActionListener(this);
    }

    public Dimension getPreferredSize() {
        return new Dimension(650, 300);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.drawString("Playlist:", 50, 100);
        
        for (int i = 0; i < playlist.size(); i++) {
            String songText = (i + 1) + ". " + playlist.get(i).toString();
            g.drawString(songText, 50, 130 + i*25);
        }
    }

    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == btnAdd) {
                playlist.add(Integer.parseInt(locationField.getText()) - 1, new Song(nameField.getText(), artistField.getText(), albumField.getText()));

                nameField.setText("");
                artistField.setText("");
                albumField.setText("");
                locationField.setText("");
            } 
            else if (e.getSource() == btnRemoveLoc) {
                playlist.remove(Integer.parseInt(locationField.getText()) - 1);
                    
                locationField.setText("");
            } 
            else if (e.getSource() == btnRemoveDet) {
                playlist.remove(new Song(nameField.getText(), artistField.getText(), albumField.getText()));

                nameField.setText("");
                artistField.setText("");
                albumField.setText("");
            } 
            else if (e.getSource() == btnSort) {
                for (int i = 0; i < playlist.size() - 1; i++) {
                    int min = i;
                    for (int j = i + 1; j < playlist.size(); j++) {

                        String s1 = playlist.get(j).toString().toLowerCase();
                        String s2 = playlist.get(min).toString().toLowerCase();
                        
                        if (s1.compareTo(s2) < 0) {
                            min = j;
                        }
                    }
                    Song temp = playlist.get(min);
                    playlist.set(min, playlist.get(i));
                    playlist.set(i, temp);
                }
            } 
            else if (e.getSource() == btnRand) {
                for (int i = playlist.size() - 1; i > 0; i--) {
                    int rand = (int) (Math.random() * (i + 1));
                    Song temp = playlist.get(i);
                    playlist.set(i, playlist.get(rand));
                    playlist.set(rand, temp);
                }
            }
        } catch (Exception ex) {
            System.out.println("Error");
        }

        repaint();
    }
}