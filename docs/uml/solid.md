# Prinsip SOLID di Aset-Pinjam

## 1. Open/Closed Principle (OCP)
Class `Aset` abstrak dengan subclass `Ruang`, `Laptop`, dan `AlatLab`. Tiap subclass meng-override `getJenis()` dan `hitungDenda()` sendiri. Kalau nanti ada jenis aset baru (misalnya `Sensor`), cukup menambah subclass baru. `KatalogAset`, `DendaFlat`, dan `DendaProgresif` tidak perlu diubah karena mereka hanya memanggil `aset.hitungDenda(...)` lewat tipe `Aset`. Sistem terbuka untuk ditambah tetapi tertutup dari perubahan, dan tidak ada rantai `if`/`instanceof` yang harus diedit tiap jenis baru.

## 2. Dependency Inversion Principle (DIP)
`DendaFlat` dan `DendaProgresif` mengimplementasikan interface `AturanDenda`. `LayananDenda` dirancang menerima `AturanDenda` lewat konstruktornya (minggu 10), jadi ia bergantung pada abstraksi, bukan pada class konkret. Hal yang sama berlaku di lapisan data: `AsetDAO` dan `PeminjamanDAO` adalah interface, sedangkan `JdbcAsetDAO` dan `JdbcPeminjamanDAO` hanya salah satu implementasinya. Akibatnya aturan denda atau penyimpanan data bisa diganti, dan pengujian bisa memakai implementasi tiruan tanpa database, sehingga testability meningkat.

## 3. Single Responsibility Principle (SRP)
Tiap class punya satu alasan untuk berubah: `KatalogAset` hanya mengelola daftar dan pencarian aset, `JadwalPeminjaman` hanya mengelola jadwal dan aturan bentrok/kuota, `Varian` hanya membaca parameter per mahasiswa, dan `DendaFlat`/`DendaProgresif` hanya menghitung denda.

## Refactor
Tidak ada refactor tambahan; struktur kode yang sudah ada (class abstrak `Aset`, interface `AturanDenda`, dan interface DAO) sudah memperlihatkan prinsip di atas.