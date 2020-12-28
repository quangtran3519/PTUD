package entity;

public class Nuoc {
	private String maNuoc;
	private String tenNuoc;
	public String getMaNuoc() {
		return maNuoc;
	}
	public void setMaNuoc(String maNuoc) {
		this.maNuoc = maNuoc;
	}
	public String getTenNuoc() {
		return tenNuoc;
	}
	public void setTenNuoc(String tenNuoc) {
		this.tenNuoc = tenNuoc;
	}
	public Nuoc(String maNuoc, String tenNuoc) {
		super();
		this.maNuoc = maNuoc;
		this.tenNuoc = tenNuoc;
	}
	public Nuoc() {
		super();
	}
	public Nuoc(String maNuoc) {
		super();
		this.maNuoc = maNuoc;
	}
	@Override
	public String toString() {
		return "Nuoc [maNuoc=" + maNuoc + ", tenNuoc=" + tenNuoc + "]";
	}
	
	
}
