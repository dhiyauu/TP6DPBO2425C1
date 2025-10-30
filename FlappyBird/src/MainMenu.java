import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {
    // Komponen ini HARUS sudah di-bind dari GUI Form
    private JPanel mainPanel;
    private JButton playButton;
    private JButton exitButton;

    private final Image backgroundImage;
    private final Image birdImage;

    // posisi & kecepatan burung
    private int birdX = 100;
    private int birdY = 200;
    private int birdVelocityY = 2;
    private boolean movingDown = true;

    public MainMenu() {
        // Load aset
        backgroundImage = new ImageIcon(getClass().getResource("assets/background.png")).getImage();
        ImageIcon titleImageIcon = new ImageIcon(getClass().getResource("assets/flappybird_title.png"));
        birdImage = new ImageIcon(getClass().getResource("assets/bird.png")).getImage();

        try {
            Font flappyFont = Font.createFont(Font.TRUETYPE_FONT,
                            getClass().getResourceAsStream("/assets/flappy_fonts.ttf"))
                    .deriveFont(26f);
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(flappyFont);

            // Terapkan font ke tombol GUI Designer
            playButton.setFont(flappyFont.deriveFont(24f));
            exitButton.setFont(flappyFont.deriveFont(24f));
        } catch (Exception e) {
            System.out.println("Gagal memuat font Flappy Bird: " + e);
        }

        // Gunakan panel dari GUI Form
        setContentPane(mainPanel);

        // Ubah background menjadi gambar
        mainPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);

                g.drawImage(birdImage, birdX, birdY, 40, 30, null);
            }
        };

        // Tambahkan judul
        JLabel titleLabel = new JLabel(titleImageIcon);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(80, 0, 20, 0)); // geser agak ke bawah
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Ambil tombol dari GUI Form
        // (pastikan tombol sudah ada di .form dan di-bind ke field ini)
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        buttonPanel.add(Box.createVerticalStrut(60)); // jarak dari atas
        buttonPanel.add(playButton);
        buttonPanel.add(Box.createVerticalStrut(20));
        buttonPanel.add(exitButton);

        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        // Frame setup
        setTitle("Flappy Bird - Main Menu");
        setSize(360, 640);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(mainPanel);

        Timer birdTimer = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (movingDown) {
                    birdY += birdVelocityY;
                    if (birdY > 250) movingDown = false;
                } else {
                    birdY -= birdVelocityY;
                    if (birdY < 150) movingDown = true;
                }

                birdX += 1;
                if (birdX > getWidth()) birdX = -40; // reset ke kiri
                mainPanel.repaint();
            }
        });
        birdTimer.start();

        // Aksi tombol
        playButton.addActionListener(e -> {
            dispose();
            App.startGame();
        });

        exitButton.addActionListener(e -> System.exit(0));
    }
}
