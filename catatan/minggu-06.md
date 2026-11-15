# Catatan Minggu 06: UML dan SOLID

## Yang saya kerjakan
Minggu ini saya membuat Class Diagram dan Sequence Diagram untuk Aset-Pinjam, dua-duanya dalam format SVG di `docs/uml/`. Class Diagram saya buat dari kode yang ada: atribut, method, visibility, relasi, dan multiplicity. Sequence Diagram menggambarkan alur pengajuan peminjaman, dengan skenario bentrok dan skenario sukses. Setelah itu saya menulis `solid.md` yang menunjukkan tiga prinsip SOLID di kode saya. Tes `Minggu06DokumenTest` lulus.

## Keputusan desain
Di `solid.md` saya mengangkat tiga prinsip: OCP di class `Aset` dan subclass-nya (tambah jenis baru cukup menambah subclass), DIP di interface `AturanDenda` dan DAO (bergantung pada interface, bukan class konkret), dan SRP di `KatalogAset` dan `JadwalPeminjaman` (masing-masing punya satu tugas). Di Class Diagram, `KatalogAset` dan `JadwalPeminjaman` saya gambar sebagai aggregation, bukan composition, karena objek `Aset` dan `Peminjaman` dibuat di luar lalu dimasukkan lewat `tambah(...)`, jadi tidak ikut hilang saat wadahnya hilang. Di Sequence Diagram saya memakai fragmen `alt` untuk membedakan skenario bentrok dan skenario sukses.

## Kendala
Yang paling bikin bingung adalah menentukan multiplicity. Setelah baca ketentuan sistem, satu peminjam bisa punya banyak peminjaman (1 ke 0..*), dan satu aset bisa tercatat di banyak peminjaman (0..*), sedangkan tiap peminjaman tepat satu aset (1). Saya juga sempat tertukar antara generalization (subclass ke `Aset`, panah segitiga garis tegas) dan realization (`Aset` ke `DapatDipinjam`, garis putus-putus).

## Penggunaan AI
- Alat yang dipakai: Claude
- Bagian yang dibantu AI: draf Class Diagram, Sequence Diagram (SVG), isi `solid.md`, dan draf catatan ini
- Prompt utama: "kerjakan minggu-06 siap salin"; "buatkan svg class diagram dan sequence diagram"
- Yang saya ubah atau tolak dari saran AI, dan alasannya: tidak ada perubahan pada isi diagram; saya memeriksanya dengan kode di repo, lalu mempelajari tiap relasi dan multiplicity agar bisa menjelaskannya.
- Satu hal yang saya pahami lebih baik sekarang: aggregation berarti bagian bisa hidup tanpa wadahnya, sedangkan composition berarti bagian ikut hilang bersama wadahnya