package ui;

import model.Profile;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

// Represents a JPanel displaying the users pet
public class PetPanel extends JPanel {
    private Profile user;
    protected JLabel picture;

    // REQUIRES: mainUser must be instantiated
    // EFFECTS : user is set to mainUser; layout is set to BorderLayout;
    //           pet is added to panel
    public PetPanel(Profile mainUser) {
        this.user = mainUser;
        setLayout(new BorderLayout());
        addPet();

        setVisible(true);
    }

    // EFFECTS : adds the correct pet image to Panel based on user's
    //           pet type
    private void addPet() {
        try {
            Image img = ImageIO.read(new File("resources/"
                    + user.getPetType().toString().toLowerCase()
                    + "/pet.png"));
            Image scaledImg = img.getScaledInstance(250,250, Image.SCALE_REPLICATE);
            picture = new JLabel(new ImageIcon(scaledImg));
            add(picture, BorderLayout.CENTER);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
