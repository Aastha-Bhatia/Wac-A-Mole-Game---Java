//imported all the libraries
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class WelcomePage {
    JFrame welcomeFrame=new JFrame("MARIO : WHAC A MOLE");
    JButton startButton=new JButton("START GAME");
    ImageIcon plantIcon;
    ImageIcon moleIcon;
    JTextField nameField = new JTextField(); // text field for the player name

    public WelcomePage(){
        welcomeFrame.setSize(600,650);
        welcomeFrame.setLocationRelativeTo(null);
        welcomeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        welcomeFrame.setLayout(new BorderLayout());

        // icon image currently not added due to complications
        // Set the icon image
        ImageIcon icon = new ImageIcon(getClass().getResource("/monty.png"));
        welcomeFrame.setIconImage(icon.getImage());

        JLabel titleLabel = new JLabel("Mario: Whac A Mole",JLabel.CENTER);
        titleLabel.setFont(new Font("Arial",Font.BOLD,40));
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setOpaque(true);
        titleLabel.setBackground(Color.decode("#F5F5DC"));
        welcomeFrame.add(titleLabel, BorderLayout.NORTH);

        JLabel aboutText = new JLabel(
            "<html><div style='text-align:center;'>" +
    "In a magical garden deep in the Mushroom Kingdom,<br>" +
    "mischievous moles have started popping up<br>" +
    "from the ground, playing hide and seek with everyone.<br><br>" +
    "But there's a twist! A sneaky, poisonous plant<br>" +
    "named Piranha has also made its way into the garden.<br><br>" +
    "Your mission is to catch the playful moles by<br>" +
    "whacking them on the head, but be careful—don't hit<br>" +
    "the Piranha plant, or the game will be over!<br><br>" +
    "Get ready for a fun and exciting adventure where<br>" +
    "quick reflexes are key to winning!<br><br><br>" +
    "Good luck and click on Start Game!<br><br>" +
    "</div></html>",JLabel.CENTER);

        aboutText.setFont(new Font("Arial",Font.PLAIN,20));
        aboutText.setForeground(Color.decode("#F5F5DC"));
        aboutText.setOpaque(true);
        aboutText.setBackground(Color.BLACK);
        welcomeFrame.add(aboutText, BorderLayout.CENTER);

        JPanel aboutPanel=new JPanel(new BorderLayout());
        aboutPanel.setBackground(Color.BLACK);
        aboutPanel.setBorder(new EmptyBorder(0,20,0,20));
        aboutPanel.add(aboutText,BorderLayout.CENTER);
        welcomeFrame.add(aboutPanel,BorderLayout.CENTER);

        // Panel to hold both the start button and footer label
        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.setBackground(Color.BLACK);

        JLabel footerLabel = new JLabel("Developed by Aastha", JLabel.CENTER);
        footerLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        footerLabel.setForeground(Color.decode("#F5F5DC"));
        footerLabel.setOpaque(true);
        footerLabel.setBackground(Color.BLACK);
        footerLabel.setEnabled(false);
        southPanel.add(footerLabel, BorderLayout.NORTH);

        startButton.setFont(new Font("Arial", Font.PLAIN, 30));
        startButton.setBackground(Color.decode("#F5F5DC"));
        startButton.setForeground(Color.BLACK);
        startButton.setFocusable(false);
        southPanel.add(startButton, BorderLayout.SOUTH);

        welcomeFrame.add(southPanel, BorderLayout.SOUTH);

        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                welcomeFrame.dispose(); // closes
                new WhacAMole(); // start the game
            }
        });

        welcomeFrame.setVisible(true);
    }
    }
