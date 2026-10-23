# Catatan Minggu 02: Struktur OOP: Class, Object, Encapsulation

## Yang saya kerjakan
Saya melengkapi class `Aset` dengan empat field private (kode, nama, status, kondisi), konstruktor, getter, dan setter yang memvalidasi input. Kode dan nama yang null, kosong, atau hanya spasi ditolak dengan `IllegalArgumentException`, sedangkan status dan kondisi awal diisi TERSEDIA dan BAIK. Semua tes `Minggu02AsetTest` lulus, begitu juga tes minggu 1.

## Keputusan desain
Field `kode` saya buat `final` dan tanpa setter karena kode adalah identitas aset yang tidak boleh berubah. Kode juga saya normalisasi (trim dan huruf besar) supaya " a-01 " dan "A-01" dianggap sama; alasannya dicatat di `docs/asumsi.md`. Di setter saya melakukan validasi dulu baru mengisi field, sehingga nilai lama tidak rusak ketika input ditolak. Pengecekan teks kosong saya pusatkan di satu method private `wajibIsi` agar tidak ditulis berulang.

## Kendala
Saya perlu memperhatikan bahwa tes memakai spasi saja sebagai input tidak sah, jadi pengecekannya memakai `isBlank()`, bukan `isEmpty()`. Saya juga memastikan semua field `private` karena tes memeriksanya lewat refleksi.

## Penggunaan AI
- Alat yang dipakai: Claude
- Bagian yang dibantu AI: panduan pengerjaan dan contoh isi konstruktor, getter, setter, serta method `wajibIsi` di `Aset`
- Prompt utama: "buatkan rangkuman pemahaman pengerjaan minggu-02.md";
