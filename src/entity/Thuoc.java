package entity;

import java.sql.Date;

public class Thuoc {
	private String maThuoc;
	private String tenThuoc;
	private String donViTinh;
	private Date hsd;
	private int soLuong;
	private double giaNhap;
	private double giaBan;
	
	private NhaCungCap nhaCC;
	private Nuoc nuocSX;
	private LoaiThuoc loai;
	
	public LoaiThuoc getLoai() {
		return loai;
	}
	public void setLoai(LoaiThuoc loai) {
		this.loai = loai;
	}
	public String getMaThuoc() {
		return maThuoc;
	}
	public void setMaThuoc(String maThuoc) {
		this.maThuoc = maThuoc;
	}
	public String getTenThuoc() {
		return tenThuoc;
	}
	public void setTenThuoc(String tenThuoc) {
		this.tenThuoc = tenThuoc;
	}
	public String getDonViTinh() {
		return donViTinh;
	}
	public void setDonViTinh(String donViTinh) {
		this.donViTinh = donViTinh;
	}
	public Date getHsd() {
		return hsd;
	}
	public void setHsd(Date hsd) {
		this.hsd = hsd;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public double getGiaNhap() {
		return giaNhap;
	}
	public void setGiaNhap(double giaNhap) {
		this.giaNhap = giaNhap;
	}
	public double getGiaBan() {
		return giaBan;
	}
	public void setGiaBan(double giaBan) {
		this.giaBan = giaBan;
	}
	public NhaCungCap getNhaCC() {
		return nhaCC;
	}
	public void setNhaCC(NhaCungCap nhaCC) {
		this.nhaCC = nhaCC;
	}
	public Nuoc getNuocSX() {
		return nuocSX;
	}
	public void setNuocSX(Nuoc nuocSX) {
		this.nuocSX = nuocSX;
	}
	public Thuoc(String maThuoc, String tenThuoc, String donViTinh, Date hsd, int soLuong, double giaBan,
			double giaNhap) {
		super();
		this.maThuoc = maThuoc;
		this.tenThuoc = tenThuoc;
		this.donViTinh = donViTinh;
		this.hsd = hsd;
		this.soLuong = soLuong;
		this.giaBan = giaBan;
		this.giaNhap = giaNhap;
	}
	public Thuoc(String maThuoc) {
		super();
		this.maThuoc = maThuoc;
	}
	public Thuoc() {
		super();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maThuoc == null) ? 0 : maThuoc.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Thuoc other = (Thuoc) obj;
		if (maThuoc == null) {
			if (other.maThuoc != null)
				return false;
		} else if (!maThuoc.equals(other.maThuoc))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Thuoc [maThuoc=" + maThuoc + ", tenThuoc=" + tenThuoc + ", donViTinh=" + donViTinh + ", hsd=" + hsd
				+ ", soLuong=" + soLuong + ", giaNhap=" + giaNhap + ", giaBan=" + giaBan + ", nhaCC=" + nhaCC
				+ ", nuocSX=" + nuocSX + ", loai=" + loai + "]";
	}



}
