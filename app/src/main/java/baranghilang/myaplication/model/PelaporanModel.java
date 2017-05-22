package baranghilang.myaplication.model;

/**
 * Created by CMDDJ on 5/22/2017.
 */
public class PelaporanModel {
    String idLapor, idPelapor, idBarang, keterangan, lokHilang, lokDitemukan, tglHilang, tglDitemukan, idPenemu;
    public PelaporanModel(){}

    public void setIdLapor(String idLapor) {
        this.idLapor = idLapor;
    }

    public void setIdPelapor(String idPelapor) {
        this.idPelapor = idPelapor;
    }

    public void setIdBarang(String idBarang) {
        this.idBarang = idBarang;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public void setLokHilang(String lokHilang) {
        this.lokHilang = lokHilang;
    }

    public void setLokDitemukan(String lokDitemukan) {
        this.lokDitemukan = lokDitemukan;
    }

    public void setTglHilang(String tglHilang) {
        this.tglHilang = tglHilang;
    }

    public void setTglDitemukan(String tglDitemukan) {
        this.tglDitemukan = tglDitemukan;
    }

    public void setIdPenemu(String idPenemu) {
        this.idPenemu = idPenemu;
    }

    public String getIdLapor() {
        return idLapor;
    }

    public String getIdPelapor() {
        return idPelapor;
    }

    public String getIdBarang() {
        return idBarang;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public String getLokHilang() {
        return lokHilang;
    }

    public String getLokDitemukan() {
        return lokDitemukan;
    }

    public String getTglHilang() {
        return tglHilang;
    }

    public String getTglDitemukan() {
        return tglDitemukan;
    }

    public String getIdPenemu() {
        return idPenemu;
    }
}
