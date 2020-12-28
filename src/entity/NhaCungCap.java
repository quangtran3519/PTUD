package entity;

public class NhaCungCap {
	private String maNCC;
	private String tenNCC;
	
	public String getMaNCC() {
		return maNCC;
	}

	public void setMaNCC(String maNCC) {
		this.maNCC = maNCC;
	}

	public String getTenNCC() {
		return tenNCC;
	}

	public void setTenNCC(String tenNCC) {
		this.tenNCC = tenNCC;
	}

	public NhaCungCap() {
		super();
	}

	public NhaCungCap(String maNCC) {
		super();
		this.maNCC = maNCC;
	}

	public NhaCungCap(String maNCC, String tenNCC) {
		super();
		this.maNCC = maNCC;
		this.tenNCC = tenNCC;
	}

	@Override
	public String toString() {
		return "NhaCungCap [maNCC=" + maNCC + ", tenNCC=" + tenNCC + "]";
	}
	 
}
