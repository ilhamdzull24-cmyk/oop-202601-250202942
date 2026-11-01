# Catatan Minggu 04: Polymorphism

## Yang saya kerjakan
Saya meng-override `hitungDenda(int)` di `Ruang`, `Laptop`, dan `AlatLab` dengan multiplier 1x, 2x, dan 4x dari tarif denda varian saya (`Varian.aktif().dendaPerHari`). Hari negatif ditolak dengan `IllegalArgumentException`, dan 0 hari menghasilkan denda 0. Saya juga membuat dua overload `cari(...)` di `KatalogAset`: satu berdasarkan kata, satu berdasarkan kata dan jenis. Semua tes `Minggu04PolimorfismeTest` lulus.

## Keputusan desain
Rumus denda dan pengecekan hari negatif saya taruh di satu method pembantu `hitungDendaDasar` di `Aset`, sehingga subclass hanya menentukan multiplier-nya. Dengan begitu aturan tidak ditulis tiga kali dan perubahan rumus cukup di satu tempat. Hasilnya dihitung dengan `long` supaya tidak overflow. Untuk pencarian, `cari(kata, jenis)` memakai `cari(kata)` lalu menyaring jenisnya, jadi logika pencocokan nama tidak diulang. Pencocokan nama memakai huruf kecil agar tidak membedakan huruf besar dan kecil.

## Kendala
Saya perlu membedakan overloading dan overriding. `cari(String)` dan `cari(String, JenisAset)` adalah overloading: nama sama, parameter berbeda, dipilih saat kompilasi. `hitungDenda` di subclass adalah overriding: signature sama, yang dijalankan ditentukan oleh jenis objek saat program berjalan (dynamic binding). Saya juga memastikan tidak menambah field non-private di `Aset` karena tes minggu 2 memeriksa semua field.

## Penggunaan AI
- Alat yang dipakai: Claude
- Bagian yang dibantu AI: override `hitungDenda`, method `hitungDendaDasar`, dua overload `cari`, dan draf catatan ini
- Prompt utama: "buatkan rangkuman pemahaman pengerjaan minggu-04.md";
- Yang saya ubah atau tolak dari saran AI, dan alasannya: tidak ada perubahan pada kode; saya menyalin hasilnya, menjalankan tes sampai lulus, lalu mempelajari tiap baris agar bisa menjelaskannya.
- Satu hal yang saya pahami lebih baik sekarang: pemanggilan `aset.hitungDenda(3)` pada variabel bertipe `Aset` menjalankan versi milik `Ruang`, `Laptop`, atau `AlatLab` sesuai objek aslinya, bukan sesuai tipe variabel