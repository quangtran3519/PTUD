package entity;

public class ChiTietHoaDon {
	private Thuoc thuoc ;
	private int soLuong ;
	private HoaDon hoaDon;
	public ChiTietHoaDon() {
		// TODO Auto-generated constructor stub
	}
	
	public ChiTietHoaDon(Thuoc thuoc, int soLuong, HoaDon hoaDon) {
		super();
		this.thuoc = thuoc;
		this.soLuong = soLuong;
		this.hoaDon = hoaDon;
	}

	

	public ChiTietHoaDon(Thuoc thuoc, int soLuong) {
		
		this.thuoc = thuoc;
		this.soLuong = soLuong;
	}

	public Thuoc getThuoc() {
		return thuoc;
	}
	public void setThuoc(Thuoc thuoc) {
		this.thuoc = thuoc;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public HoaDon getHoaDon() {
		return hoaDon;
	}
	public void setHoaDon(HoaDon hoaDon) {
		this.hoaDon = hoaDon;
	}

	@Override
	public String toString() {
		return "ChiTietHoaDon [thuoc=" + thuoc + ", soLuong=" + soLuong + ", hoaDon=" + hoaDon + "]";
	}
	

}
