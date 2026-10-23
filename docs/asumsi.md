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
- Keputusan: (isi)
- Alasan: (isi)
- Dampak ke kode: (isi)
- Tes yang membuktikan: (isi)

## Asumsi lain

Tambahkan di sini keputusan lain yang Anda ambil: rumus denda progresif, perubahan skema database, format error API, dan seterusnya.

## A-02: normalisasi kode aset
- Pertanyaan: apakah kode aset di-trim dan diubah ke huruf besar?
- Keputusan: ya, kode di-trim dan diubah ke huruf besar saat konstruktor dipanggil. Nama tidak dinormalisasi.
- Alasan: kode berfungsi sebagai identitas aset, jadi " a-01 " dan "A-01" harus dianggap aset yang sama agar pencarian dan perbandingan konsisten.
- Dampak ke kode: konstruktor Aset (trim dan toUpperCase pada kode).
- Tes yang membuktikan: Minggu02AsetTest.menyimpanKodeDanNamaDenganNilaiAwal (kode "A-01" tetap "A-01").
