package id.ac.upb.asetpinjam.jadwal;

import id.ac.upb.asetpinjam.model.Peminjaman;
import id.ac.upb.asetpinjam.model.StatusPeminjaman;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * Jadwal peminjaman di memori.
 *
 * <p>Minggu 7: bentrok, semuaUrutMulai, perAset, hapus. Minggu 9: ajukan (melempar exception).
 * tambah() DISEDIAKAN dan sengaja TIDAK memeriksa apa pun.
 */
public class JadwalPeminjaman {

    private final List<Peminjaman> daftar = new ArrayList<>();
    private final int kuotaLaptop;

    public JadwalPeminjaman(int kuotaLaptop) {
        this.kuotaLaptop = kuotaLaptop;
    }

    protected int kuotaLaptop() {
        return kuotaLaptop;
    }

    protected List<Peminjaman> daftarMutable() {
        return daftar;
    }

    /** Menambah apa adanya tanpa pemeriksaan. */
    public void tambah(Peminjaman p) {
        daftar.add(p);
    }

    /**
     * True jika {@code baru} bentrok dengan peminjaman yang sudah ada untuk ASET YANG SAMA.
     *
     * <p>Rentang dianggap setengah terbuka [mulai, selesai): peminjaman yang selesai tepat saat
     * peminjaman lain mulai TIDAK bentrok (asumsi A-01). Peminjaman berstatus DITOLAK atau
     * DIBATALKAN tidak lagi menempati jadwal.
     */
    public boolean bentrok(Peminjaman baru) {
        if (baru == null) {
            throw new IllegalArgumentException("peminjaman baru tidak boleh null");
        }
        String kodeAset = baru.getAset().getKode();
        for (Peminjaman ada : daftar) {
            if (!menempatiJadwal(ada)) {
                continue;
            }
            if (!ada.getAset().getKode().equals(kodeAset)) {
                continue;
            }
            if (tumpangTindih(ada, baru)) {
                return true;
            }
        }
        return false;
    }

    private static boolean menempatiJadwal(Peminjaman p) {
        return p.getStatus() != StatusPeminjaman.DITOLAK && p.getStatus() != StatusPeminjaman.DIBATALKAN;
    }

    private static boolean tumpangTindih(Peminjaman a, Peminjaman b) {
        return a.getMulai().isBefore(b.getSelesai()) && b.getMulai().isBefore(a.getSelesai());
    }

    /** Semua peminjaman, diurutkan menurut waktu mulai (menaik). Daftar asli tidak diubah. */
    public List<Peminjaman> semuaUrutMulai() {
        return daftar.stream()
                .sorted(Comparator.comparing(Peminjaman::getMulai))
                .toList();
    }

    /** Peminjaman dikelompokkan menurut kode aset (kunci terurut menurut kode). */
    public Map<String, List<Peminjaman>> perAset() {
        return daftar.stream()
                .collect(Collectors.groupingBy(
                        p -> p.getAset().getKode(),
                        TreeMap::new,
                        Collectors.toList()));
    }

    /** Hapus peminjaman berdasarkan id. True jika ada yang terhapus. */
    public boolean hapus(String id) {
        return daftar.removeIf(p -> p.getId().equals(id));
    }

    /**
     * TODO minggu 9: periksa lalu tambahkan.
     * Bentrok: PeminjamanBentrokException. Peminjam sudah memegang {@link #kuotaLaptop()} laptop yang
     * aktif (status DISETUJUI atau DIPINJAM) dan mengajukan laptop lagi: KuotaHabisException.
     */
    public void ajukan(Peminjaman baru) {
        throw new UnsupportedOperationException("TODO minggu 9: ajukan");
    }
}