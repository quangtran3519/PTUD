package entity;



public class KhachHang {

	private int maKH;
	private String hoTen;
	private boolean gioiTinh;
	private String soDT;
	private String diaChi;
	private String cmnd;

	public KhachHang(String hoTen, boolean gioiTinh, String soDT, String diaChi, String cmnd) {
		this.hoTen = hoTen;
		this.gioiTinh = gioiTinh;
		this.soDT = soDT;
		this.diaChi = diaChi;
		this.cmnd = cmnd;
	}

	public KhachHang(int maKH, String hoTen, boolean gioiTinh, String soDT, String diaChi, String cmnd) {
		super();
		this.maKH = maKH;
		this.hoTen = hoTen;
		this.gioiTinh = gioiTinh;
		this.soDT = soDT;
		this.diaChi = diaChi;
		this.cmnd = cmnd;
	}

	public KhachHang(String cmnd) {
		this("xxx",true,"111","xxx",cmnd);
	}

	public KhachHang TimTheoTenKhacHang(String sdt) {
		return new KhachHang("xxx", true, sdt, "xxx","xxx");
	}
	public KhachHang() {

	}

	public int getMaKH() {
		return maKH;
	}
	public void setMaKH(int maKH) {
		this.maKH = maKH;
	}
	public String getHoTen() {
		return hoTen;
	}
	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}
	public boolean isGioiTinh() {
		return gioiTinh;
	}
	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	public String getSoDT() {
		return soDT;
	}
	public void setSoDT(String soDT) {
		this.soDT = soDT;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public String getCmnd() {
		return cmnd;
	}
	public void setCmnd(String cmnd) {
		this.cmnd = cmnd;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return hoTen +";"+ gioiTinh+";"+ soDT+";" + diaChi+";" + cmnd  ; 
	}
}
