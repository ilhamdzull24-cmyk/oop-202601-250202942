# Catatan Minggu 01: Paradigma Pemrograman dan Setup

## Yang saya kerjakan
Saya menyiapkan JDK 21, Maven, dan Git, lalu mengisi NIM dan nama di `mahasiswa.properties`. Setelah itu saya melengkapi `HelloProsedural`, `HelloOOP`, dan `HelloFungsional` agar ketiganya menghasilkan sapaan yang sama. Semua tes `Minggu01Test` lulus.

## Keputusan desain
Saya memakai format sapaan `Hello Aset-Pinjam, saya <nama> (<nim>)` di ketiga file supaya hasilnya identik dan tes konsistensi lulus. Prosedural memakai method statis, OOP memakai field `private final` lewat konstruktor, dan fungsional memakai lambda `Function<String, String>`.

## Kendala
Saat menjalankan `bash scripts/uji.sh` di PowerShell muncul error `JAVA_HOME`, karena `bash` memanggil WSL yang tidak punya JDK. Saya mengatasinya dengan memakai `.\scripts\uji.ps1` dan memastikan JDK 21 serta `JAVA_HOME` benar di Windows.

## Penggunaan AI
- Alat yang dipakai: Claude
- Bagian yang dibantu AI: panduan pengerjaan, contoh isi `HelloProsedural.sapa`, `HelloOOP`, `HelloFungsional.pembuatSapaan`, dan diagnosis error `JAVA_HOME`
- Prompt utama: "buatkan rangkuman pemahaman pengerjaan minggu-01.md"; 