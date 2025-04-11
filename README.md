# 🎮 Flappy Bird Java - Program Design Documentation

## 📦 1. Class `App` (Main Class)

**Fungsi**: Class utama yang menjalankan program.

### 🧠 Method:
- `main(String[] args)`  
  - Memanggil `StartMenu.showStartMenu()` untuk menampilkan menu awal.

---

## 🧩 2. Class `StartMenu`

**Fungsi**: Menampilkan GUI menu awal sebelum game dimulai.

### 🧱 Atribut:
- Tidak ada atribut khusus (hanya komponen Swing seperti `JFrame`, `JButton`, dll).

### 🧠 Method:
- `showStartMenu()`  
  - Membuat `JFrame` untuk menu awal.  
  - Menambahkan tombol **"START GAME"** yang saat diklik akan:  
    ✔ Menutup menu (`startFrame.dispose()`)  
    ✔ Membuka game utama (`startGame()`)

- `startGame()`  
  - Membuat `JFrame` game Flappy Bird (`FlappyBird.java`)

---

## 🐦 3. Class `FlappyBird` (Game Logic & GUI)

**Fungsi**: Mengatur logika game, tampilan, dan interaksi pemain.

### 🧱 Atribut:

| Atribut          | Tipe               | Deskripsi                                      |
|------------------|--------------------|------------------------------------------------|
| `frameWidth`     | `int`              | Lebar layar game                               |
| `frameHeight`    | `int`              | Tinggi layar game                              |
| `backgroundImage`| `Image`            | Gambar latar belakang                          |
| `birdImage`      | `Image`            | Gambar burung (pemain)                         |
| `lowerPipeImage` | `Image`            | Gambar pipa bawah                              |
| `upperPipeImage` | `Image`            | Gambar pipa atas                               |
| `player`         | `Player`           | Objek pemain                                   |
| `pipes`          | `ArrayList<Pipe>`  | Daftar pipa yang aktif                         |
| `gameloop`       | `Timer`            | Timer untuk update game (60 FPS)               |
| `pipesCooldown`  | `Timer`            | Timer untuk spawn pipa baru                    |
| `gravity`        | `int`              | Gaya gravitasi yang memengaruhi burung         |
| `gameOver`       | `boolean`          | Status game over (true/false)                  |
| `score`          | `int`              | Skor pemain                                    |
| `scoreLabel`     | `JLabel`           | Label untuk menampilkan skor                   |

### 🧠 Method Utama:

| Method         | Deskripsi                                                    |
|----------------|---------------------------------------------------------------|
| `resetGame()`  | Mengatur ulang game ke kondisi awal                           |
| `draw(Graphics g)` | Menggambar semua elemen game (pemain, pipa, latar belakang) |
| `move()`       | Mengupdate posisi pemain dan pipa, serta mengecek tabrakan    |
| `placePipes()` | Membuat pipa baru di posisi acak                              |
| `collision()`  | Mengecek tabrakan antara pemain dan pipa                      |
| `gameOver()`   | Menghentikan game dan menampilkan layar game over            |

---

## 🐤 4. Class `Player` (Pemain/Burung)

**Fungsi**: Merepresentasikan burung yang dikontrol pemain.

### 🧱 Atribut:

| Atribut    | Tipe   | Deskripsi                          |
|------------|--------|-------------------------------------|
| `posX`     | `int`  | Posisi horizontal burung           |
| `posY`     | `int`  | Posisi vertikal burung             |
| `width`    | `int`  | Lebar burung                       |
| `height`   | `int`  | Tinggi burung                      |
| `image`    | `Image`| Gambar burung                      |
| `velocityY`| `int`  | Kecepatan vertikal (naik/turun)    |

### 🧠 Method:
- Getter & Setter (misalnya `getPosX()`, `setVelocityY()`, dll)

---

## 🧱 5. Class `Pipe` (Pipa Penghalang)

**Fungsi**: Merepresentasikan pipa yang harus dihindari pemain.

### 🧱 Atribut:

| Atribut    | Tipe   | Deskripsi                                |
|------------|--------|-------------------------------------------|
| `posX`     | `int`  | Posisi horizontal pipa                   |
| `posY`     | `int`  | Posisi vertikal pipa                     |
| `width`    | `int`  | Lebar pipa                               |
| `height`   | `int`  | Tinggi pipa                              |
| `image`    | `Image`| Gambar pipa (atas/bawah)                 |
| `velocityX`| `int`  | Kecepatan horizontal (gerakan ke kiri)   |
| `passed`   | `boolean` | Status apakah pipa sudah dilewati pemain |

### 🧠 Method:
- Getter & Setter (misalnya `getPosX()`, `setPassed()`, dll).
