package id.ac.upb.asetpinjam.mahasiswa;

import static id.ac.upb.asetpinjam.TestSupport.pinjam;
import static org.assertj.core.api.Assertions.assertThat;

import id.ac.upb.asetpinjam.TestSupport;
import id.ac.upb.asetpinjam.config.Varian;
import id.ac.upb.asetpinjam.jadwal.JadwalPeminjaman;
import id.ac.upb.asetpinjam.model.Peminjaman;
import id.ac.upb.asetpinjam.model.StatusPeminjaman;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/** Tes buatan sendiri untuk keputusan A-01: rentang setengah terbuka [mulai, selesai). */
@Tag("week7")
class BatasBentrokTest {

    private JadwalPeminjaman jadwal;

    @BeforeEach
    void siapkan() {
        jadwal = new JadwalPeminjaman(Varian.aktif().kuotaLaptop);
    }

    @Test
    void selesaiSamaDenganMulaiTidakBentrok() {
        jadwal.tambah(pinjam("1", TestSupport.peminjam("P1"), TestSupport.ruang("R-1"), 9, 11));
        Peminjaman baru = pinjam("2", TestSupport.peminjam("P2"), TestSupport.ruang("R-1"), 11, 13);
        assertThat(jadwal.bentrok(baru)).isFalse();
    }

    @Test
    void mulaiSamaDenganSelesaiYangLainTidakBentrok() {
        jadwal.tambah(pinjam("1", TestSupport.peminjam("P1"), TestSupport.ruang("R-1"), 11, 13));
        Peminjaman baru = pinjam("2", TestSupport.peminjam("P2"), TestSupport.ruang("R-1"), 9, 11);
        assertThat(jadwal.bentrok(baru)).isFalse();
    }

    @Test
    void rentangIdentikBentrok() {
        jadwal.tambah(pinjam("1", TestSupport.peminjam("P1"), TestSupport.ruang("R-1"), 9, 11));
        Peminjaman baru = pinjam("2", TestSupport.peminjam("P2"), TestSupport.ruang("R-1"), 9, 11);
        assertThat(jadwal.bentrok(baru)).isTrue();
    }

    @Test
    void mulaiSamaSelesaiBerbedaBentrok() {
        jadwal.tambah(pinjam("1", TestSupport.peminjam("P1"), TestSupport.ruang("R-1"), 9, 11));
        Peminjaman baru = pinjam("2", TestSupport.peminjam("P2"), TestSupport.ruang("R-1"), 9, 10);
        assertThat(jadwal.bentrok(baru)).isTrue();
    }

    @Test
    void tumpangTindihSatuJamBentrok() {
        jadwal.tambah(pinjam("1", TestSupport.peminjam("P1"), TestSupport.ruang("R-1"), 9, 11));
        Peminjaman baru = pinjam("2", TestSupport.peminjam("P2"), TestSupport.ruang("R-1"), 10, 12);
        assertThat(jadwal.bentrok(baru)).isTrue();
    }

    @Test
    void peminjamanDitolakTidakMenempatiJadwal() {
        Peminjaman ditolak = pinjam("1", TestSupport.peminjam("P1"), TestSupport.ruang("R-1"), 9, 11);
        ditolak.setStatusDariPenyimpanan(StatusPeminjaman.DITOLAK);
        jadwal.tambah(ditolak);
        Peminjaman baru = pinjam("2", TestSupport.peminjam("P2"), TestSupport.ruang("R-1"), 9, 11);
        assertThat(jadwal.bentrok(baru)).isFalse();
    }

    @Test
    void peminjamanDibatalkanTidakMenempatiJadwal() {
        Peminjaman batal = pinjam("1", TestSupport.peminjam("P1"), TestSupport.ruang("R-1"), 9, 11);
        batal.setStatusDariPenyimpanan(StatusPeminjaman.DIBATALKAN);
        jadwal.tambah(batal);
        Peminjaman baru = pinjam("2", TestSupport.peminjam("P2"), TestSupport.ruang("R-1"), 10, 12);
        assertThat(jadwal.bentrok(baru)).isFalse();
    }

    @Test
    void peminjamanDiajukanMasihMenempatiJadwal() {
        jadwal.tambah(pinjam("1", TestSupport.peminjam("P1"), TestSupport.ruang("R-1"), 9, 11));
        Peminjaman baru = pinjam("2", TestSupport.peminjam("P2"), TestSupport.ruang("R-1"), 10, 12);
        assertThat(jadwal.bentrok(baru)).isTrue();
    }

    @Test
    void hapusIdYangTidakAdaMengembalikanFalse() {
        jadwal.tambah(pinjam("1", TestSupport.peminjam("P1"), TestSupport.ruang("R-1"), 9, 11));
        assertThat(jadwal.hapus("tidak-ada")).isFalse();
        assertThat(jadwal.semuaUrutMulai()).hasSize(1);
    }
}