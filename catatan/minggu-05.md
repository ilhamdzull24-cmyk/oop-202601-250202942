# Catatan Minggu 05: Abstraction

## Yang saya kerjakan
Minggu ini saya mengisi `bisaDipinjam()` di `Aset` lalu menjadikan `Aset` abstract class. Saya juga membuat `DendaFlat` dan `DendaProgresif` yang mengimplementasikan interface `AturanDenda`. Semua tes minggu 1 sampai 5 lulus.

## Keputusan desain
`Aset` saya buat abstract karena punya data bersama (kode, nama, status, kondisi) dan tidak ada aset yang "umum", adanya ruang, laptop, dan alat lab. `AturanDenda` tetap interface karena cuma kontrak perilaku. Untuk denda progresif, hari 1-7 dikali 1,0, hari 8-14 dikali 1,5, dan hari 15 ke atas dikali 2,0, berlaku bertahap per hari. Alasannya ada di `docs/asumsi.md` (A-03).

## Kendala
Setelah `Aset` abstract, konstruktornya tidak bisa dipanggil langsung, jadi saya ubah jadi `protected`. Saya juga harus memastikan denda progresif tidak pernah lebih murah dari flat dan tidak turun saat hari bertambah.

## Penggunaan AI
- Alat yang dipakai: Claude
- Bagian yang dibantu AI: `bisaDipinjam`, `Aset` abstract, `DendaFlat`, `DendaProgresif`, dan draf catatan
- Prompt utama: "buatkan rangkuman pemahaman pengerjaan minggu-05.md";
- Yang saya ubah atau tolak dari saran AI, dan alasannya:tidak ada perubahan pada kode; saya menjalankan tes sampai lulus lalu mempelajari tiap barisnya, terutama rumus progresif bertahap.
- Satu hal yang saya pahami lebih baik sekarang: abstract class untuk hubungan "adalah" yang punya state bersama, interface untuk kontrak perilaku