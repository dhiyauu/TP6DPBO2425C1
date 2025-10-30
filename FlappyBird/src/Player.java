import java.awt.*;

/**
 * Kelas Player merepresentasikan karakter utama (burung) dalam game Flappy Bird.
 */
public class Player {
    // Variabel Utama
    private int posX;       // Posisi horizontal (sumbu X) burung di layar
    private int posY;       // Posisi vertikal (sumbu Y) burung di layar
    private int width;      // Lebar karakter burung (dalam piksel)
    private int height;     // Tinggi karakter burung (dalam piksel)
    private Image image;    // Gambar burung yang akan ditampilkan pada layar

    // Fisika Pergerakan
    private int velocityY;  // Kecepatan burung pada sumbu vertikal (positif = turun, negatif = naik)
    private int velocityX;  // Kecepatan burung pada sumbu horizontal (jarang dipakai di Flappy Bird)
    private int speed = 4;  // Kecepatan dasar burung (bisa digunakan jika ingin menambah fitur gerak horizontal)
    private int maxSpeed = 12; // Batas maksimum kecepatan (agar burung tidak terlalu cepat)

    /**
     * Konstruktor Player — digunakan untuk membuat objek burung baru.
     */
    public Player(int posX, int posY, int width, int height, Image image) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.image = image;

        // Nilai awal kecepatan
        this.velocityY = 0;
        this.velocityX = 0;
    }

    // Getter dan Setter
    /** Mengambil posisi X burung */
    public int getPosX() {
        return posX;
    }

    /** Mengubah posisi X burung */
    public void setPosX(int posX) {
        this.posX = posX;
    }

    /** Mengambil posisi Y burung */
    public int getPosY() {
        return posY;
    }

    /** Mengubah posisi Y burung */
    public void setPosY(int posY) {
        this.posY = posY;
    }

    /** Mengambil lebar burung */
    public int getWidth() {
        return width;
    }

    /** Mengubah lebar burung */
    public void setWidth(int width) {
        this.width = width;
    }

    /** Mengambil tinggi burung */
    public int getHeight() {
        return height;
    }

    /** Mengubah tinggi burung */
    public void setHeight(int height) {
        this.height = height;
    }

    /** Mengambil gambar burung */
    public Image getImage() {
        return image;
    }

    /** Mengubah gambar burung */
    public void setImage(Image image) {
        this.image = image;
    }

    /** Mengambil kecepatan vertikal burung */
    public int getVelocityY() {
        return velocityY;
    }

    /** Mengatur kecepatan vertikal burung */
    public void setVelocityY(int velocityY) {
        this.velocityY = velocityY;
    }

    /** Mengambil kecepatan horizontal burung */
    public int getVelocityX() {
        return velocityX;
    }

    /** Mengatur kecepatan horizontal burung */
    public void setVelocityX(int velocityX) {
        this.velocityX = velocityX;
    }

    /** Mengambil kecepatan dasar burung */
    public int getSpeed() {
        return speed;
    }

    /** Mengatur kecepatan dasar burung */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /** Mengambil kecepatan maksimum burung */
    public int getMaxSpeed() {
        return maxSpeed;
    }

    /** Mengatur kecepatan maksimum burung */
    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }
}