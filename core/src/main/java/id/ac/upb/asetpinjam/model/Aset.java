package id.ac.upb.asetpinjam.model;

import id.ac.upb.asetpinjam.config.Varian;
import id.ac.upb.asetpinjam.kontrak.DapatDipinjam;

/**
 * Aset yang dapat dipinjam. Class abstrak: tidak ada aset "umum", yang ada Ruang, Laptop, dan AlatLab.
 *
 * <p>Minggu 2: field, konstruktor, getter/setter, dan validasi (encapsulation).
 * Minggu 3: getJenis() di tiap subclass.
 * Minggu 4: hitungDenda() dioverride di tiap subclass.
 * Minggu 5: bisaDipinjam() diisi dan class dijadikan abstrak.
 */
public abstract class Aset implements DapatDipinjam {

    private final String kode;
    private String nama;
    private StatusAset status;
    private Kondisi kondisi;

    /** Kode dan nama tidak boleh null atau kosong (IllegalArgumentException). */
    protected Aset(String kode, String nama) {
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

    /** True hanya jika status TERSEDIA dan kondisi bukan RUSAK_BERAT. */
    @Override
    public boolean bisaDipinjam() {
        return status == StatusAset.TERSEDIA && kondisi != Kondisi.RUSAK_BERAT;
    }

    /** Jenis aset, ditentukan oleh tiap subclass. */
    public abstract JenisAset getJenis();

    /**
     * Denda keterlambatan = hari x multiplier jenis x tarif varian.
     * Ruang 1x, Laptop 2x, AlatLab 4x. Hari negatif ditolak.
     */
    public abstract long hitungDenda(int hariTerlambat);

    /** Pembantu untuk subclass: tolak hari negatif lalu hitung hari x multiplier x tarif varian. */
    protected long hitungDendaDasar(int hariTerlambat, int multiplier) {
        if (hariTerlambat < 0) {
            throw new IllegalArgumentException("hari terlambat tidak boleh negatif");
        }
        return (long) hariTerlambat * multiplier * Varian.aktif().dendaPerHari;
    }
}