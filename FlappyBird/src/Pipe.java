import java.awt.*;

/**
 * Kelas Pipe merepresentasikan objek "pipa" pada game Flappy Bird.
 */
public class Pipe {
    // Variabel Utama
    private int posX;       // Posisi horizontal (X) pipa di layar
    private int posY;       // Posisi vertikal (Y) pipa di layar
    private int width;      // Lebar pipa (dalam piksel)
    private int height;     // Tinggi pipa (dalam piksel)
    private Image image;    // Gambar pipa yang akan ditampilkan
    private int velocityX;  // Kecepatan pergerakan pipa ke arah kiri (biasanya negatif)
    boolean passed;         // Status apakah pipa ini sudah dilewati oleh burung (untuk menghitung skor)

    /**
     * Konstruktor Pipe untuk menginisialisasi sebuah pipa baru.
     */
    public Pipe(int posX, int posY, int width, int height, Image image) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.image = image;

        this.velocityX = 0;     // Awalnya diam, pergerakan diatur oleh Logic
        this.passed = false;    // Belum dilewati oleh pemain
    }

    // ======== Getter dan Setter ========

    /** Mengambil posisi X pipa */
    public int getPosX() {
        return posX;
    }

    /** Mengubah posisi X pipa */
    public void setPosX(int posX) {
        this.posX = posX;
    }

    /** Mengambil posisi Y pipa */
    public int getPosY() {
        return posY;
    }

    /** Mengubah posisi Y pipa */
    public void setPosY(int posY) {
        this.posY = posY;
    }

    /** Mengambil lebar pipa */
    public int getWidth() {
        return width;
    }

    /** Mengubah lebar pipa */
    public void setWidth(int width) {
        this.width = width;
    }

    /** Mengambil tinggi pipa */
    public int getHeight() {
        return height;
    }

    /** Mengubah tinggi pipa */
    public void setHeight(int height) {
        this.height = height;
    }

    /** Mengambil gambar (sprite) dari pipa */
    public Image getImage() {
        return image;
    }

    /** Mengubah gambar (sprite) dari pipa */
    public void setImage(Image image) {
        this.image = image;
    }

    /** Mengambil kecepatan pipa (arah horizontal) */
    public int getVelocityX() {
        return velocityX;
    }

    /** Mengatur kecepatan pipa (biasanya negatif agar bergerak ke kiri) */
    public void setVelocityX(int velocityX) {
        this.velocityX = velocityX;
    }

    /** Mengecek apakah pipa sudah dilewati oleh pemain (untuk penambahan skor) */
    public boolean getPassed() {
        return passed;
    }

    /** Menandai apakah pipa sudah dilewati atau belum */
    public void setPassed(boolean passed) {
        this.passed = passed;
    }
}
