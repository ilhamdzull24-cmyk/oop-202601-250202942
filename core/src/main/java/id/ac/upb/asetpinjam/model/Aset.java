package id.ac.upb.asetpinjam.model;

import id.ac.upb.asetpinjam.config.Varian;
import id.ac.upb.asetpinjam.kontrak.DapatDipinjam;

/**
 * Aset yang dapat dipinjam.
 *
 * <p>Minggu 2: lengkapi field, konstruktor, getter/setter, dan validasi (encapsulation).
 * Minggu 3: tambahkan getJenis() di tiap subclass.
 * Minggu 4: hitungDenda() dioverride di tiap subclass.
 * Minggu 5: isi bisaDipinjam() dan ubah menjadi class abstrak
 * (TestSupport.aset(...) di folder test menyesuaikan sendiri, lihat docs/minggu-05.md).
 */
public class Aset implements DapatDipinjam {

    private final String kode;
    private String nama;
    private StatusAset status;
    private Kondisi kondisi;

    /** Kode dan nama tidak boleh null atau kosong (IllegalArgumentException). */
    public Aset(String kode, String nama) {
        this.kode = wajibIsi(kode, "kode").trim().toUpperCase();
        this.nama = wajibIsi(nama, "nama");
        this.status = StatusAset.TERSEDIA;
        this.kondisi = Kondisi.BAIK;
    }

    private static String wajibIsi(String nilai, String namaField) {
        if (nilai == null || nilai.isBlank()) {
            throw new IllegalArgumentException(namaField + " tidak boleh kosong");
        }
        return nilai;
    }

    public String getKode() {
        return kode;
    }

    public String getNama() {
        return nama;
    }

    public StatusAset getStatus() {
        return status;
    }

    public Kondisi getKondisi() {
        return kondisi;
    }

    /** Nama baru tidak boleh kosong. */
    public void setNama(String nama) {
        this.nama = wajibIsi(nama, "nama");
    }

    /** Status tidak boleh null. */
    public void setStatus(StatusAset status) {
        if (status == null) {
            throw new IllegalArgumentException("status tidak boleh null");
        }
        this.status = status;
    }

    /** Kondisi tidak boleh null. */
    public void setKondisi(Kondisi kondisi) {
        if (kondisi == null) {
            throw new IllegalArgumentException("kondisi tidak boleh null");
        }
        this.kondisi = kondisi;
    }

    /** TODO minggu 5: true hanya jika status TERSEDIA dan kondisi bukan RUSAK_BERAT. */
    @Override
    public boolean bisaDipinjam() {
        throw new UnsupportedOperationException("TODO minggu 5");
    }

    /**
     * Jenis aset. Dioverride di Ruang, Laptop, dan AlatLab (minggu 3);
     * Aset sendiri tidak punya jenis, jadi di sini belum diimplementasikan.
     */
    public JenisAset getJenis() {
        throw new UnsupportedOperationException("getJenis() dioverride oleh subclass");
    }

    /**
     * Denda keterlambatan = hari x multiplier jenis x tarif varian.
     * Dioverride di Ruang (1x), Laptop (2x), dan AlatLab (4x).
     */
    public long hitungDenda(int hariTerlambat) {
        throw new UnsupportedOperationException("hitungDenda() dioverride oleh subclass");
    }

    /** Pembantu untuk subclass: tolak hari negatif lalu hitung hari x multiplier x tarif varian. */
    protected long hitungDendaDasar(int hariTerlambat, int multiplier) {
        if (hariTerlambat < 0) {
            throw new IllegalArgumentException("hari terlambat tidak boleh negatif");
        }
        return (long) hariTerlambat * multiplier * Varian.aktif().dendaPerHari;
    }
}