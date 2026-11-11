package id.ac.upb.asetpinjam.denda;

import id.ac.upb.asetpinjam.kontrak.AturanDenda;
import id.ac.upb.asetpinjam.model.Aset;

/**
 * Minggu 5: denda yang makin berat bila makin lama terlambat.
 *
 * <p>Rumus (keputusan sendiri, dicatat di docs/asumsi.md), tarif harian = aset.hitungDenda(1):
 * hari 1-7 dikenai 1,0x; hari 8-14 dikenai 1,5x; hari 15 ke atas dikenai 2,0x.
 * Tiap tingkat hanya berlaku pada hari yang masuk tingkat itu (bertahap), sehingga denda tidak
 * pernah lebih murah dari flat dan tidak pernah turun saat hari bertambah.
 */
public class DendaProgresif implements AturanDenda {

    private static final int BATAS_TINGKAT_1 = 7;
    private static final int BATAS_TINGKAT_2 = 14;

    @Override
    public long hitung(Aset aset, int hariTerlambat) {
        if (aset == null) {
            throw new IllegalArgumentException("aset tidak boleh null");
        }
        if (hariTerlambat < 0) {
            throw new IllegalArgumentException("hari terlambat tidak boleh negatif");
        }
        long tarifHarian = aset.hitungDenda(1);

        long hariTingkat1 = Math.min(hariTerlambat, BATAS_TINGKAT_1);
        long hariTingkat2 = Math.max(0, Math.min(hariTerlambat, BATAS_TINGKAT_2) - BATAS_TINGKAT_1);
        long hariTingkat3 = Math.max(0, hariTerlambat - BATAS_TINGKAT_2);

        // satuan per hari dalam persepuluh: 10 = 1,0x; 15 = 1,5x; 20 = 2,0x
        long satuan = hariTingkat1 * 10 + hariTingkat2 * 15 + hariTingkat3 * 20;
        return tarifHarian * satuan / 10;
    }
}