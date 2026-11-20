# Catatan Minggu 07: Koleksi

## Yang saya kerjakan
Minggu ini saya ngisi empat method di `JadwalPeminjaman`: `bentrok`, `semuaUrutMulai`, `perAset`, dan `hapus`. Saya juga membuat 9 tes sendiri di `BatasBentrokTest` buat menguji batas waktu bentrok dan status peminjaman yang masih memakan jadwal. Semua tes minggu 1 sampai 7 lulus.

## Keputusan desain
Saya menyimpan data dalam satu `List<Peminjaman>`. Alih-alih membuat `Map` terpisah untuk `perAset`, saya membentuknya dari list menggunakan `groupingBy` menjadi `TreeMap` setiap kali dipanggil. Tujuannya adalah menghindari adanya dua salinan data yang berpotensi tidak sinkron, serta memastikan kunci kode aset terurut. Fungsi `semuaUrutMulai` memanfaatkan `Comparator.comparing(Peminjaman::getMulai)` tanpa mengubah list asli. Kelemahannya adalah deteksi `bentrok` memerlukan pemeriksaan seluruh isi list (O(n)), namun hal ini masih aman untuk skala tugas ini. Jika data bertambah banyak, dapat ditambahkan `Map` per kode aset sebagai indeks.

Mengenai batas waktu, saya menganggap rentangnya setengah terbuka [mulai, selesai). Dengan demikian, peminjaman 09:00-11:00 dan 11:00-13:00 pada aset yang sama tidak akan bentrok (misalnya A-01). Rumus yang digunakan adalah `a.mulai < b.selesai` dan `b.mulai < a.selesai`. Peminjaman berstatus DITOLAK atau DIBATALKAN juga tidak menempati jadwal (A-04), sedangkan DIAJUKAN tetap menempati.


## Kendala
Hal yang paling menyulitkan adalah menentukan kondisi tumpang tindih yang tepat, terutama ketika kasus melibatkan interval yang saling berisi. Sebagai solusi, saya menerapkan satu rumus perbandingan antara dua rentang, lalu menguji setiap kasus batas secara terpisah. Selain itu, saya baru menyadari bahwa implementasi `groupingBy` bawaan menggunakan `HashMap`, sehingga saya beralih ke `TreeMap` untuk memastikan konsistensi urutan kunci.

## Penggunaan AI
- Alat yang dipakai: Claude
- Bagian yang dibantu AI: `bentrok`, `semuaUrutMulai`, `perAset`, `hapus`, isi `BatasBentrokTest`
- Prompt utama: "buatkan rangkuman pemahaman pengerjaan minggu-07.md"
- Yang saya ubah atau tolak dari saran AI, dan alasannya: - Yang saya ubah atau tolak dari saran AI, dan alasannya: nggak ada perubahan di kode. Saya jalanin tes sampai lulus, terus pelajari tiap baris, terutama rumus tumpang tindih dan kenapa `perAset` pakai `TreeMap`.
- Satu hal yang saya pahami lebih baik sekarang: memilih struktur data itu tergantung kebutuhan, List buat urutan dan daftar biasa, Map buat mencari atau mengelompokan setiap kunci, dan Comparator buat menentukan aturan urutan