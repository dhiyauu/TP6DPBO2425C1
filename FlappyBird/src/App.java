// Saya Dhiya Ulhaq dengan NIM 2407716 Mengerjakan Tugas Praktikum 6 (Java Swing GUI Game Developer) dalam Mata Kuliah Desain Pemrograman Berorientasi Objek Untuk Keberkahan-Nya Maka Saya Tidak Akan Melakukan Kecurangan Seperti Yang Telah Di Spesifikasikan.

import javax.swing.*;
import java.awt.*;

// Kelas utama yang menjadi entry point (titik awal) program Flappy Bird.
public class App {
    public static void main(String[] args) {
        // Objek MainMenu adalah JFrame yang berisi tombol "Play" dan "Exit".
        new MainMenu().setVisible(true);
    }

    public static void startGame() {
        // Membuat jendela utama (frame)
        JFrame frame = new JFrame("Flappy Bird");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(360, 640);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        // Buat logika dan tampilan
        Logic logika = new Logic();
        View tampilan = new View(logika);
        logika.setView(tampilan);

        // Setup panel utama
        tampilan.requestFocus();
        tampilan.addKeyListener(logika);
        frame.add(tampilan);

        // Menampilkan game ke layar
        frame.pack();
        frame.setVisible(true);
    }
}