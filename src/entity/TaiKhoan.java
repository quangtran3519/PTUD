package entity;

public class TaiKhoan {
	private String tenTk;
	private String matKhau;
	private int maNV;
	private String quyen;
	public String getTenTk() {
		return tenTk;
	}
	public void setTenTk(String tenTk) {
		this.tenTk = tenTk;
	}
	public String getMatKhau() {
		return matKhau;
	}
	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}
	public int getMaNV() {
		return maNV;
	}
	public void setMaNV(int maNV) {
		this.maNV = maNV;
	}
	public String getQuyen() {
		return quyen;
	}
	public void setQuyen(String maQuyen) {
		this.quyen = maQuyen;
	}
	public TaiKhoan(String tenTk, String matKhau, int maNV, String quyen) {
		super();
		this.tenTk = tenTk;
		this.matKhau = matKhau;
		this.maNV = maNV;
		this.quyen = quyen;
	}
	public TaiKhoan(String tenTk, String matKhau, String quyen) {
		super();
		this.tenTk = tenTk;
		this.matKhau = matKhau;
		this.quyen = quyen;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + maNV;
		result = prime * result + ((tenTk == null) ? 0 : tenTk.hashCode());
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
		TaiKhoan other = (TaiKhoan) obj;
		if (maNV != other.maNV)
			return false;
		if (tenTk == null) {
			if (other.tenTk != null)
				return false;
		} else if (!tenTk.equals(other.tenTk))
			return false;
		return true;
	}
	
	
	
}
