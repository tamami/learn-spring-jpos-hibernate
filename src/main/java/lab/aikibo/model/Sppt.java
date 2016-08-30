package lab.aikibo.model;

import java.math.BigInteger;

public class Sppt {

  private String nop;
  private String thn;
  private String nama;
  private String alamatOp;
  private BigInteger pokok;
  private BigInteger denda;


  // --- constructor

  public Sppt() {}

  public Sppt(String nop, String thn, String nama, String alamatOp, BigInteger pokok, BigInteger denda) {
    this.nop = nop;
    this.thn = thn;
    this.nama = nama;
    this.alamatOp = alamatOp;
    this.pokok = pokok;
    this.denda = denda;
  }


  // --- setter getter

  public void setNop(String nop) { this.nop = nop; }

  public String getNop() { return nop; }

  public void setThn(String thn) { this.thn = thn; }

  public String getThn() { return thn; }

  public void setNama(String nama) { this.nama = nama; }

  public String getNama() { return nama; }

  public void setAlamatOp(String alamatOp) { this.alamatOp = alamatOp; }

  public String getAlamatOp() { return alamatOp; }

  public void setPokok(BigInteger pokok) { this.pokok = pokok; }

  public BigInteger getPokok() { return pokok; }

  public void setDenda(BigInteger denda) { this.denda = denda; }

  public BigInteger getDenda() { return denda; }

}
