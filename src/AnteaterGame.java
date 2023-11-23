import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Objects;
import javax.swing.ImageIcon;

public class AnteaterGame extends JFrame {
    private JLabel anteater1, anteater2;
    private JButton boopButton;

    public AnteaterGame() {
        setTitle("BOOP BOOP BOOP");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Load anteater images
        ImageIcon originalAnteaterIcon1 = new ImageIcon(Objects.requireNonNull(getClass().getResource("/tamandua2.gif")));
        ImageIcon originalAnteaterIcon2 = new ImageIcon(Objects.requireNonNull(getClass().getResource("/tamandua.gif")));

        try {
            // Create versions of the anteater images
            Image anteaterImage1 = originalAnteaterIcon1.getImage();
            Image anteaterImage2 = originalAnteaterIcon2.getImage();

            ImageIcon anteaterIcon1 = new ImageIcon(getResizedImage(anteaterImage1));
            ImageIcon anteaterIcon2 = new ImageIcon(getResizedImage(anteaterImage2));

            anteater1 = new JLabel(anteaterIcon1);
            anteater2 = new JLabel(anteaterIcon2);

            // Create a panel for centering
            JPanel centerPanel = new JPanel(new GridLayout(1, 2)); //  GridLayout for equal width side-by-side
            centerPanel.add(anteater1);
            centerPanel.add(anteater2);

            // Add the center panel to the content pane with padding
            getContentPane().add(centerPanel, BorderLayout.CENTER);

            boopButton = new JButton("BOOP");
            boopButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    moveAnteater();
                }
            });
            // Set the button size and background color
            boopButton.setPreferredSize(new Dimension(200, 100)); // Set the preferred size
            boopButton.setBackground(Color.PINK); // Set the background colorboopButton.setFont(new Font("Arial", Font.BOLD, 20)); // Set font size and style
            boopButton.setFont(new Font("Arial", Font.BOLD, 80)); // Set font size and style


            // Add the button to the content pane at the bottom
            getContentPane().add(boopButton, BorderLayout.SOUTH);
            // Add the button to the content pane at the bottom
            getContentPane().add(boopButton, BorderLayout.SOUTH);

            // Set the window size and make it visible
            setSize(800, 600);
            setLocationRelativeTo(null); // Center the window on the screen
            setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading or transforming the image.");
        }
    }

    private void moveAnteater() {
        boopButton.setEnabled(false); // Disable the button to prevent multiple clicks

        Timer timer = new Timer(50, new ActionListener() {
            int x = getWidth(); // Starting position at the right border

            @Override
            public void actionPerformed(ActionEvent e) {
                x -= 20; // Adjust the movement speed
                anteater2.setLocation(x, anteater2.getY());

                if (x <= anteater1.getX() + anteater1.getWidth()) {
                    ((Timer) e.getSource()).stop(); // Stop the timer when reaching the other anteater
                    showLoveMessage();
                }
            }
        });
        timer.start();
    }

    private void showLoveMessage() {
        JOptionPane.showMessageDialog(this, "I love you");
        boopButton.setEnabled(true); // Enable the button again
    }

    private Image getResizedImage(Image image) {
        BufferedImage resizedImage = new BufferedImage(400, 300, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resizedImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.drawImage(image, 0, 0, 400, 300, null);
        g2d.dispose();
        return resizedImage;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AnteaterGame::new);
    }
}
