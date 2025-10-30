import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

// Mengatur seluruh logika permainan (gravitasi burung, pergerakan pipa, skor, dan game over)
public class Logic implements ActionListener, KeyListener{
    // Ukuran layar permainan
    int frameWidth = 360; int frameHeight = 640;
    // Ukuran dan posisi awal pemain (burung)
    int playerWidth = 34;
    int playerHeight = 24;

    int playerStartPosX = (frameWidth  - playerWidth)  / 2; // center X
    int playerStartPosY = (frameHeight - playerHeight) / 2; // center Y

    // Ukuran dan posisi awal pipa
    int pipeStartPosX = frameWidth;
    int pipeStartPosY = 0;
    int pipeWidth = 64;
    int pipeHeight = 512;

    int score = 0;
    JLabel scoreLabel; // Label GUI untuk menampilkan skor

    // Komponen utama game
    View view;
    Image birdImage;
    Player player;
    
    Image lowerPipeImage;
    Image upperPipeImage;
    ArrayList<Pipe> pipes;
    
    // Timer utama (loop game dan spawn pipa)
    Timer gameLoop;
    Timer pipesCooldown;
    int gravity = 1;
    
    // Gravitasi dan status permainan
    int pipeVelocityX = -2;
    boolean isGameOver = false;

    // inisialisasi seluruh elemen utama permainan.
    public Logic() {
        // Load gambar burung dan buat objek Player
        birdImage = new ImageIcon(getClass().getResource("assets/bird.png")).getImage();
        player = new Player(playerStartPosX, playerStartPosY, playerWidth, playerHeight, birdImage);
        
        // Load gambar pipa atas & bawah
        lowerPipeImage = new ImageIcon(getClass().getResource( "assets/lowerPipe.png")).getImage();
        upperPipeImage = new ImageIcon(getClass().getResource( "assets/upperPipe.png")).getImage();
        pipes = new ArrayList<Pipe>();
        
        // Timer untuk menambahkan pipa baru setiap 1.5 detik
        pipesCooldown = new Timer (1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionevent) {
                System.out.println("Pipa");
                placePipes();
            }
        });
        pipesCooldown.start();
        
        // Timer utama game (60 FPS)
        gameLoop = new Timer(1000/60, this);
        gameLoop.start();
    }
    
    // Setter untuk menghubungkan logic dengan view
    public void setView(View view) {
        this.view = view;
    }

    // Getter untuk mendapatkan pemain
    public Player getPlayer() {
        return player;
    }

    // Getter untuk daftar pipa
    public ArrayList<Pipe> getPipes() { return pipes; }

    // Menambahkan sepasang pipa (atas dan bawah) dengan jarak acak di antara keduanya.
    public void placePipes() {
        // Tentukan posisi Y acak untuk pipa atas
        int randomPosY = (int) (pipeStartPosY - pipeHeight / 4 - Math.random() * (pipeHeight / 2));
        int openingSpace = frameHeight / 4;
        
        // Pipa atas
        Pipe upperPipe = new Pipe(pipeStartPosX, randomPosY, pipeWidth, pipeHeight, upperPipeImage);
        pipes.add(upperPipe);
        
        // Pipa bawah
        Pipe lowerPipe = new Pipe(pipeStartPosX, (randomPosY + openingSpace + pipeHeight), pipeWidth, pipeHeight,
                lowerPipeImage);
        pipes.add(lowerPipe);
    }

    // Mengecek apakah pemain menabrak pipa.
    public boolean checkCollision(Player player, Pipe pipe) {
        Rectangle playerRect = new Rectangle(player.getPosX(), player.getPosY(), player.getWidth(), player.getHeight());
        Rectangle pipeRect = new Rectangle(pipe.getPosX(), pipe.getPosY(), pipe.getWidth(), pipe.getHeight());
        return playerRect.intersects(pipeRect);
    }

    // Mengatur label skor.
    public void setScoreLabel(JLabel label) {
        this.scoreLabel = label;
    }

    // Fungsi utama pergerakan game — dipanggil setiap frame (60x per detik)
    public void move() {
        if (isGameOver) return;

        // Pergerakan pemain
        player.setVelocityY(player.getVelocityY() + gravity);
        player.setPosY(player.getPosY() + player.getVelocityY());
        player.setPosY(Math.max(player.getPosY(), 0));
        
        // Gerakan semua pipa ke kiri
        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            pipe.setPosX(pipe.getPosX() + pipeVelocityX);
        }

        // Cek apakah pemain jatuh ke bawah layar
        if (player.getPosY() + player.getHeight() >= frameHeight) {
            isGameOver = true;
            gameLoop.stop();
            pipesCooldown.stop();
            System.out.println("Game Over: jatuh ke bawah");
            return;
        }

        // Gerakkan pipa dan cek tabrakan
        for (Pipe pipe : pipes) {
            pipe.setPosX(pipe.getPosX() + pipeVelocityX);

            // Tambah skor hanya untuk lower pipe
            if (!pipe.getPassed() && pipe.getPosX() + pipe.getWidth() < player.getPosX()) {
                // Anggap lower pipe punya Y lebih besar dari 0
                if (pipe.getPosY() > 0) {
                    pipe.setPassed(true);
                    score++;
                    if (scoreLabel != null) {
                        scoreLabel.setText("Score: " + score);
                    }
                }
            }

            // Cek tabrakan burung dengan pipa
            if (checkCollision(player, pipe)) {
                isGameOver = true;
                gameLoop.stop();
                pipesCooldown.stop();
                System.out.println("Game Over: menabrak pipa");
                return;
            }
        }
        
    }

    // Restart permainan setelah game over.
    public void restartGame() {
        // Reset status
        isGameOver = false;

        // Reset posisi pemain
        player.setPosX(playerStartPosX);
        player.setPosY(playerStartPosY);
        player.setVelocityY(0);

        // Hapus semua pipa
        pipes.clear();

        score = 0;
        if (scoreLabel != null) {
            scoreLabel.setText("Score: 0");
        }

        // Mulai ulang timer
        gameLoop.start();
        pipesCooldown.start();
    }

    // memperbarui logika dan me-refresh tampilan.
    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        if (view != null) {
            view.repaint();
        }
    }
    
    //Event dari keyboard
    @Override
    public void keyTyped(KeyEvent e) {}
    // Deteksi tombol yang ditekan
    public void keyPressed(KeyEvent e) {
        // Jika game sudah berakhir dan pemain menekan R untuk restart
        if (isGameOver) {
            if (e.getKeyCode() == KeyEvent.VK_R) {
                restartGame();
            }
            return;
        }
        // Jika tombol spasi ditekan, maka burung melompat ke atas
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            player.setVelocityY(-10);
        }
    }
    public void keyReleased(KeyEvent e) {}
}
