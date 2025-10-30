import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

// Kelas View bertanggung jawab untuk menampilkan seluruh elemen visual game Flappy Bird.
public class View extends JPanel {
    // Ukuran layar game
    int width = 360;
    int height = 640;

    // Referensi ke kelas lain
    private Logic logic;
    private Image backgroundImage;
    private Font flappyFont;

    // Konstruktor View — mempersiapkan tampilan utama game.
    public View(Logic logic) {
        this.logic = logic;

        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
        addKeyListener(logic);

        // Load background
        backgroundImage = new ImageIcon(getClass().getResource("assets/background.png")).getImage();

        // Load Flappy Font sekali saja
        try {
            flappyFont = Font.createFont(Font.TRUETYPE_FONT,
                    getClass().getResourceAsStream("assets/flappy_fonts.ttf")).deriveFont(Font.BOLD, 24f);
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(flappyFont);
        } catch (Exception e) {
            flappyFont = new Font("Arial", Font.BOLD, 24);
            System.out.println("Gagal memuat font Flappy Bird: " + e);
        }
    }

    // Method bawaan Swing yang otomatis dipanggil saat tampilan perlu digambar ulang.
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g); // Gambar semua elemen utama (player, pipe, background, skor)

        // Jika game over
        if (logic.isGameOver) {
            // Gambar tulisan "Game Over"
            Image gameOverImage = new ImageIcon(getClass().getResource("assets/game_over.png")).getImage();

            int imgWidth = gameOverImage.getWidth(null);
            int imgHeight = gameOverImage.getHeight(null);
            int x = (getWidth() - imgWidth) / 2;
            int y = (getHeight() - imgHeight) / 2 - 50;
            g.drawImage(gameOverImage, x, y, null);

            // Teks restart
            String restartText = "PRESS 'R' TO RESTART";

            g.setFont(flappyFont.deriveFont(18f));
            FontMetrics fm = g.getFontMetrics();
            int textWidth = fm.stringWidth(restartText);
            int textX = (getWidth() - textWidth) / 2;
            int textY = y + imgHeight + 40;

            // Outline hitam
            g.setColor(Color.BLACK);
            int outline = 2;
            for (int dx = -outline; dx <= outline; dx++) {
                for (int dy = -outline; dy <= outline; dy++) {
                    g.drawString(restartText, textX + dx, textY + dy);
                }
            }

            // Isi putih
            g.setColor(Color.WHITE);
            g.drawString(restartText, textX, textY);
        }
    }

    // Menggambar seluruh elemen game (background, player, pipes, skor).
    public void draw(Graphics g) {
        // Gambar background
        g.drawImage(backgroundImage, 0, 0, width, height, null);

        // Gambar player
        Player player = logic.getPlayer();
        if (player != null) {
            g.drawImage(player.getImage(), player.getPosX(), player.getPosY(),
                    player.getWidth(), player.getHeight(), null);
        }

        // Gambar pipa
        ArrayList<Pipe> pipes = logic.getPipes();
        if (pipes != null) {
            for (Pipe pipe : pipes) {
                g.drawImage(pipe.getImage(), pipe.getPosX(), pipe.getPosY(),
                        pipe.getWidth(), pipe.getHeight(), null);
            }
        }

        // Gambar skor 
        g.setFont(flappyFont.deriveFont(Font.BOLD, 28f));
        String scoreText = "Score: " + logic.score;

        FontMetrics fm = g.getFontMetrics();
        int textWidth = fm.stringWidth(scoreText);
        int x = (width - textWidth) / 2;  // center horizontal
        int y = 50; // posisi dari atas

        // Outline hitam
        g.setColor(Color.BLACK);
        int outline = 2;
        for (int dx = -outline; dx <= outline; dx++) {
            for (int dy = -outline; dy <= outline; dy++) {
                g.drawString(scoreText, x + dx, y + dy);
            }
        }

        // Isi putih
        g.setColor(Color.WHITE);
        g.drawString(scoreText, x, y);
    }
}