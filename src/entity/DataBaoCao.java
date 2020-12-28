package entity;

import java.util.Date;

public class DataBaoCao {
	private int maHD;
	private Date ngaylap;
	private String math;
	private String tentth;
	private int soluong;
	private double thanhtien;
	private double doanhthu;
	public int getMaHD() {
		return maHD;
	}
	public void setMaHD(int maHD) {
		this.maHD = maHD;
	}
	public Date getNgaylap() {
		return ngaylap;
	}
	public void setNgaylap(Date ngaylap) {
		this.ngaylap = ngaylap;
	}
	public String getMath() {
		return math;
	}
	public void setMath(String math) {
		this.math = math;
	}
	public String getTentth() {
		return tentth;
	}
	public void setTentth(String tentth) {
		this.tentth = tentth;
	}
	public int getSoluong() {
		return soluong;
	}
	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}
	public double getThanhtien() {
		return thanhtien;
	}
	public void setThanhtien(double thanhtien) {
		this.thanhtien = thanhtien;
	}
	public double getDoanhthu() {
		return doanhthu;
	}
	public void setDoanhthu(double doanhthu) {
		this.doanhthu = doanhthu;
	}
	public DataBaoCao(int maHD, Date ngaylap, String math, String tentth, int soluong, double thanhtien) {
		super();
		this.maHD = maHD;
		this.ngaylap = ngaylap;
		this.math = math;
		this.tentth = tentth;
		this.soluong = soluong;
		this.thanhtien = thanhtien;
	}
	public DataBaoCao() {
		
	}
	
	
}
