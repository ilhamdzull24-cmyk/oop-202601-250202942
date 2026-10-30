package id.ac.upb.asetpinjam.model;

/** Minggu 3: aset berupa laptop. Spesifikasi dan nomor inventaris tidak boleh kosong. */
public class Laptop extends Aset {

    private final String spesifikasi;
    private final String nomorInventaris;

    public Laptop(String kode, String nama, String spesifikasi, String nomorInventaris) {
        super(kode, nama);
        this.spesifikasi = wajibIsi(spesifikasi, "spesifikasi");
        this.nomorInventaris = wajibIsi(nomorInventaris, "nomor inventaris");
    }

    private static String wajibIsi(String nilai, String namaField) {
        if (nilai == null || nilai.isBlank()) {
            throw new IllegalArgumentException(namaField + " tidak boleh kosong");
        }
        return nilai;
    }

    public String getSpesifikasi() {
        return spesifikasi;
    }

    public String getNomorInventaris() {
        return nomorInventaris;
    }

    @Override
    public JenisAset getJenis() {
        return JenisAset.LAPTOP;
    }

    // TODO minggu 4: override hitungDenda(int).
}