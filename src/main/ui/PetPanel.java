package ui;

import model.Profile;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

// Represents a JPanel displaying the users pet
public class PetPanel extends JPanel {
    private Profile user;           //main user profile
    protected JLabel picture;       //picture of pet to be displayed

    // REQUIRES: mainUser must be instantiated
    // EFFECTS : user is set to mainUser; layout is set to BorderLayout;
    //           background is set and pet is added to panel
    public PetPanel(Profile mainUser) {
        this.user = mainUser;
        setLayout(new BorderLayout());
        addPet();

        setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255), 4));
        setBackground(new Color(127, 196, 102));
        setOpaque(true);

        JPanel sky = new JPanel();
        Dimension size = new Dimension();
        size.height = PetAppGUI.HEIGHT / 6;
        sky.setPreferredSize(size);
        sky.setBackground(new Color(0x91D6F8));
        sky.setOpaque(true);
        sky.setVisible(true);
        add(sky, BorderLayout.NORTH);


        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS : adds the correct pet image to Panel based on user's pet type
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
