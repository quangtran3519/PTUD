package entity;

import java.sql.Date;
import java.time.LocalDate;

public class HoaDon {
	private int maHD;
	private Date ngayLap;
	private KhachHang kh;
	private NhanVien nv;
	public HoaDon() {
		// TODO Auto-generated constructor stub
	}
	public HoaDon( Date ngayLap) {
		super();
	
		this.ngayLap = ngayLap;
	}
	
	public HoaDon(int maHD) {
		super();
		this.maHD = maHD;
	}
	public int getMaHD() {
		return maHD;
	}
	public void setMaHD(int maHD) {
		this.maHD = maHD;
	}
	public Date getNgayLap() {
		return ngayLap;
	}
	public void setNgayLap(Date ngayLap) {
		this.ngayLap = ngayLap;
	}
	public KhachHang getKh() {
		return kh;
	}
	public void setKh(KhachHang kh) {
		this.kh = kh;
	}
	public NhanVien getNv() {
		return nv;
	}
	public void setNv(NhanVien nv) {
		this.nv = nv;
	}
	@Override
	public String toString() {
		return "HoaDon [maHD=" + maHD + ", ngayLap=" + ngayLap + ", kh=" + kh + ", nv=" + nv + "]";
	}
	
	

}
