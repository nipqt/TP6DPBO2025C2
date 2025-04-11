# Janji

Saya Hanif Ahmad Syauqi dengan NIM 2304330 mengerjakan soal Tugas Praktikum 6 dalam mata kuliah Desain Pemrograman Berorientasi Objek untuk keberkahanNya maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin.

# Desain Program

- Class `App` (Main Class)
**Fungsi**: Class utama yang menjalankan program.
  - Method:
    - `main(String[] args)`  
      Memanggil `StartMenu.showStartMenu()` untuk menampilkan menu awal.
      
- Class `StartMenu`
**Fungsi**: Menampilkan GUI menu awal sebelum game dimulai.
  - Atribut:
    Tidak ada atribut khusus (hanya komponen Swing seperti `JFrame`, `JButton`, dll).
  - Method:
    - `showStartMenu()`  
      - Membuat `JFrame` untuk menu awal.  
      - Menambahkan tombol **"START GAME"** yang saat diklik akan:  
        ✔ Menutup menu (`startFrame.dispose()`)  
        ✔ Membuka game utama (`startGame()`)
    - `startGame()`  
      - Membuat `JFrame` game Flappy Bird (`FlappyBird.java`)

- Class `FlappyBird` (Game Logic & GUI)
**Fungsi**: Mengatur logika game, tampilan, dan interaksi pemain.
  - Atribut:
    
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

  - Method Utama:
  
  | Method         | Deskripsi                                                    |
  |----------------|---------------------------------------------------------------|
  | `resetGame()`  | Mengatur ulang game ke kondisi awal                           |
  | `draw(Graphics g)` | Menggambar semua elemen game (pemain, pipa, latar belakang) |
  | `move()`       | Mengupdate posisi pemain dan pipa, serta mengecek tabrakan    |
  | `placePipes()` | Membuat pipa baru di posisi acak                              |
  | `collision()`  | Mengecek tabrakan antara pemain dan pipa                      |
  | `gameOver()`   | Menghentikan game dan menampilkan layar game over            |

- Class `Player` (Pemain/Burung)
**Fungsi**: Merepresentasikan burung yang dikontrol pemain.
  - Atribut:

  | Atribut    | Tipe   | Deskripsi                          |
  |------------|--------|-------------------------------------|
  | `posX`     | `int`  | Posisi horizontal burung           |
  | `posY`     | `int`  | Posisi vertikal burung             |
  | `width`    | `int`  | Lebar burung                       |
  | `height`   | `int`  | Tinggi burung                      |
  | `image`    | `Image`| Gambar burung                      |
  | `velocityY`| `int`  | Kecepatan vertikal (naik/turun)    |

  - Method:
    Getter & Setter (misalnya `getPosX()`, `setVelocityY()`, dll)

- Class `Pipe` (Pipa Penghalang)
**Fungsi**: Merepresentasikan pipa yang harus dihindari pemain.
  - Atribut:

  | Atribut    | Tipe   | Deskripsi                                |
  |------------|--------|-------------------------------------------|
  | `posX`     | `int`  | Posisi horizontal pipa                   |
  | `posY`     | `int`  | Posisi vertikal pipa                     |
  | `width`    | `int`  | Lebar pipa                               |
  | `height`   | `int`  | Tinggi pipa                              |
  | `image`    | `Image`| Gambar pipa (atas/bawah)                 |
  | `velocityX`| `int`  | Kecepatan horizontal (gerakan ke kiri)   |
  | `passed`   | `boolean` | Status apakah pipa sudah dilewati pemain |

  - Method:
    Getter & Setter (misalnya `getPosX()`, `setPassed()`, dll).

# Alur Program
- Menu Awal:
  - Program dimulai dengan menampilkan **Start Menu**
  - Terdapat tombol **"START GAME"** di tengah layar
  - User menekan tombol untuk memulai game

- Game Utama:
  - Burung muncul di tengah layar
  - Pipa muncul secara acak dari kanan layar
  - User menekan **spasi** untuk membuat burung terbang
  - **Skor** bertambah saat burung berhasil melewati pipa
  - **Game Over** jika burung menabrak pipa atau jatuh ke tanah

- Game Over:
  - Tampilan **Game Over** muncul
  - Menampilkan **skor akhir**
  - User bisa menekan tombol **'R'** untuk restart game
