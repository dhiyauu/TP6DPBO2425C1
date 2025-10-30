# TP5DPBO2425C1

## Janji
Saya Dhiya Ulhaq dengan NIM 2407716 Mengerjakan Tugas Praktikum 6 (Java Swing GUI Game Developer) dalam Mata Kuliah Desain Pemrograman Berorientasi Objek Untuk Keberkahan-Nya Maka Saya Tidak Akan Melakukan Kecurangan Seperti Yang Telah Di Spesifikasikan. Amiiin

## Penjelasan Desain Program
Program ini adalah permainan Flappy Bird versi sederhana yang menggunakan Java Swing. Pemain menekan tombol Space untuk membuat burung terbang melewati pipa tanpa menabrak. Struktur kelas yang dibuat diantaranya:

### `App`
Fungsi : Titik masuk (entry point) program. Menampilkan `MainMenu`, dan memulai permainan dengan `startGame()`.

### `MainMenu`
Fungsi : Tampilan menu awal dengan background, tombol *Start* dan *Exit*, serta animasi burung terbang.

### `Logic`
Fungsi : Mengatur logika permainan, seperti gravitasi, gerak burung, pipa, skor, dan deteksi tabrakan.

### `Player`
Menyimpan data pemain (Burung).

**Atribut**

Di dalamnya terdapat beberapa atribut, diantaranya:
1. **posX**, **posY** : Menyimpan koordinat posisi burung di layar (X = posisi horizontal, Y = posisi vertikal).
2. **width**, **height** : Ukuran gambar burung di layar (lebar dan tinggi).
3. **image** : Gambar burung Flappy. Digunakan oleh View saat menggambar burung.
4. **velocityY** : Kecepatan vertikal (atas-bawah). Nilai positif berarti jatuh ke bawah, negatif berarti naik.
5. **velocityX** : Kecepatan horizontal.
6. **speed** : Kecepatan dasar pergerakan (bisa digunakan untuk mempercepat atau memperlambat burung).
7. **maxSpeed** : Batas kecepatan maksimum burung agar tidak terlalu cepat jatuh atau naik.

**Getter & Setter**
- Getter : `getPosX()`, `getPosY()`, `getWidth()`, `getHeight()`, `getImage()`, `getVelocityY()`, `getVelocityX()`, `getSpeed()`, `getMaxSpeed()`.
- Setter : `setPosX(posX)`, `setPosY(posY)`, `setWidth(width)`, `setHeight(height)`, `setImage(image)`, `setVelocityY(velocityY)`, `setVelocityX(velocityX)`, `setSpeed(speed)`, `setMaxSpeed(maxSpeed)`.

Membuat Getter yang berfungsi mengambil nilai masing-masing atribut. Sedangkan, Setter berfungsi untuk menetapkan atau mengubah nilai suatu atribut.

### `Pipe`
Menyimpan data tiap pipa.

**Atribut**

Di dalamnya terdapat beberapa atribut, diantaranya:
1. **posX**, **posY** : Menyimpan koordinat posisi pipa di layar (X = posisi horizontal, Y = posisi vertikal).
2. **width**, **height** : Ukuran gambar pipa di layar (lebar dan tinggi).
3. **image** : Gambar pipa atas dan bawah.

**Getter & Setter**
- Getter : `getPosX()`, `getPosY()`, `getWidth()`, `getHeight()`, `getImage()`.
- Setter : `setPosX(posX)`, `setPosY(posY)`, `setWidth(width)`, `setHeight(height)`, `setImage(image)`.

Membuat Getter yang berfungsi mengambil nilai masing-masing atribut. Sedangkan, Setter berfungsi untuk menetapkan atau mengubah nilai suatu atribut.

### `View`
Fungsi : Menggambar semua elemen di layar (background, burung, pipa, skor, dan tampilan game over).

### Diagram Hubungan Antar Kelas
jangan lupa gambar

- App membuat jendela utama.
- MainMenu menampilkan tampilan awal dengan animasi burung.
- Saat “Play” diklik → App.startGame() dijalankan → membuat Logic dan View.
- Logic mengatur pergerakan dan status game.
- View menggambar semua objek berdasarkan data dari Logic.

## Alur Program

### a. Program dimulai di `App`
```java
public static void main(String[] args) {
    new MainMenu().setVisible(true);
}
```
Menampilkan menu utama (`MainMenu`) dengan tombol *Start* dan *Exit*.

### b. Menampilkan Main Menu
Terdapat backround dan judul untuk permainan Flappy Bird agar terlihat menarik. Selain itu, menambahkan gambar burung Flappy yang terbang bolak-balik menggunakan `Timer` kecil. Lalu **playButton** memanggil `app.startGame()` dan **exitButton** uuntuk menutup aplikasi.

### c. Gamedimulai
```java
Logic logika = new Logic();
View tampilan = new View(logika);
logika.setView(tampilan);
```
`App.startGame()` membuat Frame baru dibuat dengan ukuran 360x640. Lalu, `Logic` mengatur fisika dan alur game dan `View` menggambar tampilan sesuai data dari `Logic`.

### d. Timer game berjalan
Dalam `Logic` ada dua timer penting:
- `gameLoop = new Timer(1000/60, this)` berfungsi untuk Dipanggil setiap 1/60 detik (~60 FPS), menggerakkan burung dan pipa.
- `pipesCooldown = new Timer(1500, actionEvent -> placePipes())` berfungsi untuk menambahkan pasangan pipa baru setiap 1.5 detik.

### e. Gravitasi dan Lompatan
```java
player.setVelocityY(player.getVelocityY() + gravity);
player.setPosY(player.getPosY() + player.getVelocityY());
```
Di `Logic.move()` setiap frame, burung tertarik ke bawah oleh gravitasi.

```java
player.setVelocityY(-10);
```
Ketika user menekan Space, maka gambar burung flappy akan meloncat ke atas

### f. Gerak dan Tabrakan
```java
Rectangle playerRect = new Rectangle(...);
Rectangle pipeRect = new Rectangle(...);
if (playerRect.intersects(pipeRect)) isGameOver = true;
```
Semua pipa digerakkan ke kiri. Ketika burung menabrak pipa atau jatuh ke bawah maka dianggap **Game Over**. Namun jika burung melewati pipa bawah, skor bertamah 1.

### g. Tampilan (View)
`View.paintComponent()` digambar ulang tiap frame:
1. Background (`background.png`)
2. Pipa (`lowerPipe.png` dan `upperPipe.png`)
3. Burung (`bird.png`)
4. Posisi skor di tengah atas
5. Jika game over, maka gambar `game_over.png` dan menampilkan teks *“PRESS R TO RESTART”*

### h. Akhir permainan
Saat `isGameOver = true`, semua timer berhenti. Namun, jika pemain menekan **R**, maka `restartGame();`agar dapat mengatur ulang posisi burung, menghapus semua pipa, menyetel skor ke 0, dan menjalankan ulang `gameLoop` dan `pipesCooldown`.

## Dokumentasi
