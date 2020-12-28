package entity;

public class NhanVien {
	private int maNV;
	private String tenNV;
	private String cmnd;
	private String sdt;
	private String diachi;
	private String calamviec;
	private boolean gioitinh;
	public int getMaNV() {
		return maNV;
	}
	public void setMaNV(int maNV) {
		this.maNV = maNV;
	}
	public String getTenNV() {
		return tenNV;
	}
	public void setTenNV(String tenNV) {
		this.tenNV = tenNV;
	}
	public String getCmnd() {
		return cmnd;
	}
	public void setCmnd(String cmnd) {
		this.cmnd = cmnd;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	public String getDiachi() {
		return diachi;
	}
	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}

	public String getCalamviec() {
		return calamviec;
	}
	public void setCalamviec(String calamviec) {
		this.calamviec = calamviec;
	}
	
	public boolean isGioitinh() {
		return gioitinh;
	}
	public void setGioitinh(boolean gioitinh) {
		this.gioitinh = gioitinh;
	}
	public NhanVien(String tenNV, String cmnd, String sdt, String diachi,String calamviec, boolean gt) {
		super();
		this.tenNV = tenNV;
		this.cmnd = cmnd;
		this.sdt = sdt;
		this.diachi = diachi;
		this.calamviec = calamviec;
		this.gioitinh = gt;
	}
	public NhanVien() {
		
	}
	
	
	
	
}
