# Catatan Minggu 03: Inheritance

## Yang saya kerjakan
Saya melengkapi tiga subclass dari `Aset`, yaitu `Ruang`, `Laptop`, dan `AlatLab`. Masing-masing punya field sendiri, konstruktor yang memanggil `super(kode, nama)` lalu memvalidasi datanya, getter, dan override `getJenis()`. Semua tes `Minggu03HierarkiTest` lulus, begitu juga tes minggu 1 dan 2.

## Keputusan desain
Field yang dimiliki semua jenis aset (kode, nama, status, kondisi) tetap di `Aset` karena itu sifat umum, sedangkan data khusus ditaruh di subclass: kapasitas di `Ruang`, spesifikasi dan nomor inventaris di `Laptop`, laboratorium dan tingkat risiko di `AlatLab`. Dengan begitu validasi kode dan nama tidak ditulis ulang dan tetap berlaku di semua subclass lewat `super`. Field subclass saya buat `private final` tanpa setter karena datanya tidak berubah setelah aset dibuat.

Satu contoh inheritance yang kurang tepat dibanding komposisi: aturan denda dan batas durasi per jenis aset. Kalau semuanya dipaksakan lewat subclass, kelas bertambah setiap ada aturan baru, dan satu aset tidak bisa mengganti aturannya tanpa membuat subclass lagi. Lebih fleksibel jika `Aset` memegang objek kebijakan (misalnya `KebijakanDenda`) yang bisa diganti. Itu hubungan "punya", bukan "adalah".

## Kendala
Saya perlu memastikan validasi dilakukan setelah `super(...)` dipanggil, karena pemanggilan `super` harus menjadi baris pertama konstruktor. Saya juga memakai `isBlank()` agar input yang hanya berisi spasi ikut ditolak.

## Penggunaan AI
- Alat yang dipakai: Claude
- Bagian yang dibantu AI: isi konstruktor, getter, dan `getJenis()` di `Ruang`, `Laptop`, `AlatLab`, serta draf catatan ini
- Prompt utama: "buatkan rangkuman pemahaman pengerjaan minggu-03.md";
- Yang saya ubah atau tolak dari saran AI, dan alasannya: 
- Satu hal yang saya pahami lebih baik sekarang: subclass memakai ulang kode induk lewat `super`, sehingga validasi `Aset` otomatis berlaku, tetapi hubungan "adalah" ini bisa kaku jika yang berubah adalah perilaku, bukan jenis
