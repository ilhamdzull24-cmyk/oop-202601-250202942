# Asumsi dan Keputusan Desain

Isi bagian ini sendiri. Satu entri per keputusan. Dinilai dari kejelasan alasan dan konsistensi dengan kode dan tes Anda,
bukan dari jawaban "benar".

Format tiap entri:

```
## A-NN: judul singkat
- Pertanyaan: ...
- Keputusan: ...
- Alasan: ...
- Dampak ke kode: ... (kelas/metode)
- Tes yang membuktikan: ... (nama tes di folder mahasiswa/)
```

## Aturan ambigu dari docs/aturan-bisnis.md

Salin setiap pertanyaan ambigu (nomor 1 sampai 7) ke sini dan jawab. Contoh:

## A-01: batas waktu bentrok
- Pertanyaan: 09:00-11:00 dan 11:00-13:00 pada aset yang sama bentrok atau tidak?
- Keputusan: tidak bentrok
- Alasan: waktu selesai peminjaman pertama sama dengan waktu mulai peminjaman kedua, tidak tumpang tindih
- Dampak ke kode: `JadwalPeminjaman.bentrok` pakai rentang setengah terbuka [mulai, selesai), yaitu bentrok kalau `a.mulai < b.selesai` dan `b.mulai < a.selesai`.
- Tes yang membuktikan: `BatasBentrokTest.selesaiSamaDenganMulaiTidakBentrok`, `BatasBentrokTest.mulaiSamaDenganSelesaiYangLainTidakBentrok`


## A-02: normalisasi kode aset
- Pertanyaan: apakah kode aset di-trim dan diubah ke huruf besar?
- Keputusan: ya, kode di-trim dan diubah ke huruf besar saat konstruktor dipanggil. Nama tidak dinormalisasi.
- Alasan: kode berfungsi sebagai identitas aset, jadi " a-01 " dan "A-01" harus dianggap aset yang sama agar pencarian dan perbandingan konsisten.
- Dampak ke kode: konstruktor Aset (trim dan toUpperCase pada kode).
- Tes yang membuktikan: Minggu02AsetTest.menyimpanKodeDanNamaDenganNilaiAwal (kode "A-01" tetap "A-01").
  

## A-03: rumus denda progresif
- Pertanyaan: bagaimana rumus denda progresif yang makin berat saat makin lama?
- Keputusan: denda dihitung bertahap per hari. Hari 1-7 dikenai 1,0x tarif harian, hari 8-14 dikenai 1,5x, dan hari 15 ke atas dikenai 2,0x. Tarif harian diambil dari aset.hitungDenda(1), jadi multiplier jenis aset tetap berlaku.
- Alasan: tiap tingkat hanya mengenai hari yang masuk tingkat itu, sehingga denda tidak pernah lebih murah dari flat, tidak pernah turun, dan tidak melonjak tiba-tiba di batas hari. Pada hari ke-30 hasilnya lebih mahal dari flat.
- Dampak ke kode: DendaProgresif.java
- Tes yang membuktikan: Minggu05AbstraksiTest.dendaProgresifMemenuhiSyaratMinimal

 ## A-04: status yang menempati jadwal
- Pertanyaan: apakah peminjaman berstatus DITOLAK atau DIBATALKAN masih menempati jadwal?
- Keputusan: tidak. Hanya peminjaman selain DITOLAK dan DIBATALKAN yang menempati jadwal. DIAJUKAN tetap menempati jadwal.
- Alasan: pengajuan yang ditolak atau dibatalkan tidak akan terjadi, jadi aset itu bebas dipinjam pada waktu yang sama. DIAJUKAN tetap menempati supaya dua pengajuan tidak saling berebut slot yang sama.
- Dampak ke kode: JadwalPeminjaman.bentrok (method pembantu menempatiJadwal).
- Tes yang membuktikan: BatasBentrokTest.peminjamanDitolakTidakMenempatiJadwal, BatasBentrokTest.peminjamanDibatalkanTidakMenempatiJadwal, BatasBentrokTest.peminjamanDiajukanMasihMenempatiJadwal 

## Asumsi lain

Tambahkan di sini keputusan lain yang Anda ambil: rumus denda progresif, perubahan skema database, format error API, dan seterusnya.

