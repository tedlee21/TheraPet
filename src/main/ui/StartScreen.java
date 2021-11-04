package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartScreen extends JFrame implements ActionListener {
    private JLabel label;
    private JTextField field;

    public StartScreen() {
        super("Digital Pet");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 400));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(new FlowLayout());
        JButton newGame = new JButton("New Game");
        JButton loadGame = new JButton("Load Game");
        newGame.setActionCommand("newButton");
        newGame.addActionListener(this); // Sets "this" object as an action listener for btn
        // so that when the btn is clicked,
        // this.actionPerformed(ActionEvent e) will be called.
        // You could also set a different object, if you wanted
        // a different object to respond to the button click
        loadGame.setActionCommand("loadButton");
        loadGame.addActionListener(this);
        add(newGame);
        add(loadGame);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("newButton")) {
            new PetAppGUI(false);
        } else if (e.getActionCommand().equals("loadButton")) {
            new PetAppGUI(true);
        }
        setVisible(false);
    }
}
