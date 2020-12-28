package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import entity.Thuoc;

public class HoaDonDao {
	 private	Connection con = DatabaseConnect.getInstance().connect();
	public HoaDonDao() {
		
	}
	public boolean themHoaDon(HoaDon hoaDon) {
		
		try {
			PreparedStatement stmt = con.prepareStatement("INSERT INTO dbo.HoaDon VALUES  (?,?,?)");
			stmt.setDate(1, hoaDon.getNgayLap());
			
				stmt.setInt(2,hoaDon.getKh().getMaKH());
								
			stmt.setInt(3,hoaDon.getNv().getMaNV());
			
			stmt.executeUpdate();
			//themChiTietHoaDon(ctHD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return true;
	}
	public HoaDon layHoaDonMoiNhat() {
			HoaDon x = null;
			try {
				PreparedStatement stmt = con.prepareStatement("SELECT TOP 1  * FROM dbo.HoaDon ORDER BY maHD  DESC");
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					int maHD = rs.getInt(1);
//					Date ngayLap = rs.getDate(2);
//					String maKh = rs.getString(3);
//					String maNV = rs.getString(4);					
					x = new HoaDon(maHD);				
				}
				//themChiTietHoaDon(ctHD);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			return x;
		}
	public boolean themChiTietHoaDon(ChiTietHoaDon ctHD) {
		
		try {
			PreparedStatement stmt = con.prepareStatement("INSERT dbo.ChiTietHoaDon VALUES  (?,?,?)");
			stmt.setString(3,ctHD.getThuoc().getMaThuoc());
			stmt.setString(2, ctHD.getSoLuong()+"");
			stmt.setInt(1,ctHD.getHoaDon().getMaHD());	
			stmt.executeUpdate();
			updateThuoc(ctHD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	private void updateThuoc(ChiTietHoaDon x) {
		
		try {
			PreparedStatement stmt = con.prepareStatement(" UPDATE dbo.Thuoc SET soLuong = soLuong-"+ x.getSoLuong()+" WHERE maThuoc = '"+x.getThuoc().getMaThuoc()+"'");
		
			stmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public List<HoaDon>  getDSHoaDon(){
		List<HoaDon>  hoaDons = new ArrayList<HoaDon>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from hoadon");
			while (rs.next()) {
				int mahD = rs.getInt(1);
				Date ngaylap = rs.getDate(2);
				int makh = rs.getInt(3);
				int maNV = rs.getInt(4);
				
				KhachHang khachHang = new KhachHangDao().timKhTHeoMaKH(makh);
				NhanVien nv = new NhanVienDao().tim1NV(maNV);
				
				HoaDon hoaDon = new HoaDon(ngaylap);
				hoaDon.setMaHD(mahD);
				hoaDon.setKh(khachHang);
				hoaDon.setNv(nv);
				hoaDons.add(hoaDon);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return hoaDons;
	}
	public  List<ChiTietHoaDon> getCTHD(int parseInt) {
		List<ChiTietHoaDon> chiTietHoaDons = new ArrayList<ChiTietHoaDon>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM dbo.ChiTietHoaDon where maHD ="+ parseInt);
			while (rs.next()) {
				
				int soluong = rs.getInt(2);
				String maTHuoc = rs.getString(3);
				Thuoc thuoc = new ThuocDAO().lay1Thuoc(maTHuoc);
				ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon(thuoc, soluong);
				chiTietHoaDons.add(chiTietHoaDon);
		
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return chiTietHoaDons;
	}
	public HoaDon getHoaDon(int maHD) {
		HoaDon x = null;
		try {
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM dbo.HoaDon where maHD ="+ maHD);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int mahd = rs.getInt(1);
				Date ngayLap = rs.getDate(2);
				String maKh = rs.getString(3);
				String maNV = rs.getString(4);					
				x = new HoaDon(mahd);
			}
			//themChiTietHoaDon(ctHD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		return x;
	}
	public HoaDon getHoaDonInLai(int maHD) {
		HoaDon x = null;
		try {
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM dbo.HoaDon where maHD ="+ maHD);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int mahd = rs.getInt(1);
				Date ngayLap = rs.getDate(2);
				String maKh = rs.getString(3);
				String maNV = rs.getString(4);					
				x = new HoaDon(ngayLap);
				x.setMaHD(mahd);
				x.setKh(new KhachHangDao().timKhTHeoMaKH(Integer.parseInt(maKh)));
				x.setNv(new NhanVienDao().tim1NV(Integer.parseInt(maNV)));
			}
			//themChiTietHoaDon(ctHD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		return x;
	}
	public List<HoaDon>  getDSHoaDonKhongKeDon(){
		List<HoaDon>  hoaDons = new ArrayList<HoaDon>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select hd.maHD ,hd.ngayLap,hd.maKH ,hd.maNV FROM hoadon AS hd JOIN dbo.KhachHang AS kh ON "
					+ "	kh.maKH = hd.maKH  WHERE kh.tenKH LIKE N'Khách Vãng lai'");
			while (rs.next()) {
				int mahD = rs.getInt(1);
				Date ngaylap = rs.getDate(2);
				int makh = rs.getInt(3);
				int maNV = rs.getInt(4);
				
				KhachHang khachHang = new KhachHangDao().timKhTHeoMaKH(makh);
				NhanVien nv = new NhanVienDao().tim1NV(maNV);
				
				HoaDon hoaDon = new HoaDon(ngaylap);
				hoaDon.setMaHD(mahD);
				hoaDon.setKh(khachHang);
				hoaDon.setNv(nv);
				hoaDons.add(hoaDon);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return hoaDons;
	}
	public List<HoaDon>  getDSHoaDonKeDon(){
		List<HoaDon>  hoaDons = new ArrayList<HoaDon>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select hd.maHD ,hd.ngayLap,hd.maKH ,hd.maNV FROM hoadon AS hd JOIN dbo.KhachHang AS kh ON "
					+ "	kh.maKH = hd.maKH  WHERE kh.tenKH not LIKE N'Khách Vãng lai'");
			while (rs.next()) {
				int mahD = rs.getInt(1);
				Date ngaylap = rs.getDate(2);
				int makh = rs.getInt(3);
				int maNV = rs.getInt(4);
				
				KhachHang khachHang = new KhachHangDao().timKhTHeoMaKH(makh);
				NhanVien nv = new NhanVienDao().tim1NV(maNV);
				
				HoaDon hoaDon = new HoaDon(ngaylap);
				hoaDon.setMaHD(mahD);
				hoaDon.setKh(khachHang);
				hoaDon.setNv(nv);
				hoaDons.add(hoaDon);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return hoaDons;
	}
	public List<HoaDon>  getDSHoaDonTheoTenNV(String tenNV){
		List<HoaDon>  hoaDons = new ArrayList<HoaDon>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select hd.maHD ,hd.ngayLap,hd.maKH ,hd.maNV FROM hoadon AS hd JOIN dbo.NhanVien AS nv"
					+ " ON nv.maNV = hd.maNV WHERE nv.tenNV = N'"+ tenNV+"'");
			while (rs.next()) {
				int mahD = rs.getInt(1);
				Date ngaylap = rs.getDate(2);
				int makh = rs.getInt(3);
				int maNV = rs.getInt(4);
				
				KhachHang khachHang = new KhachHangDao().timKhTHeoMaKH(makh);
				NhanVien nv = new NhanVienDao().tim1NV(maNV);
				
				HoaDon hoaDon = new HoaDon(ngaylap);
				hoaDon.setMaHD(mahD);
				hoaDon.setKh(khachHang);
				hoaDon.setNv(nv);
				hoaDons.add(hoaDon);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return hoaDons;
	}
	public List<HoaDon>  getDSHoaDonTheoDK(String tenKh,String maHD){
		List<HoaDon>  hoaDons = new ArrayList<HoaDon>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("	select hd.maHD ,hd.ngayLap,hd.maKH ,hd.maNV FROM hoadon AS hd JOIN dbo.KhachHang AS kh ON "
					+ "kh.maKH = hd.maKH  WHERE kh.tenKH  LIKE N'"+tenKh+"' OR hd.maHD like '"+maHD+"'");
			while (rs.next()) {
				int mahD = rs.getInt(1);
				Date ngaylap = rs.getDate(2);
				int makh = rs.getInt(3);
				int maNV = rs.getInt(4);
				
				KhachHang khachHang = new KhachHangDao().timKhTHeoMaKH(makh);
				NhanVien nv = new NhanVienDao().tim1NV(maNV);			
				HoaDon hoaDon = new HoaDon(ngaylap);
				hoaDon.setMaHD(mahD);
				hoaDon.setKh(khachHang);
				hoaDon.setNv(nv);
				hoaDons.add(hoaDon);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return hoaDons;
	}
	public void xoaHD(String valueAt) {
			xoaCTHD(valueAt);
		try {
			PreparedStatement stmt = con.prepareStatement(" DELETE dbo.HoaDon WHERE maHD = ?");
			stmt.setInt(1, Integer.parseInt(valueAt) );
			
			stmt.executeUpdate();
			//themChiTietHoaDon(ctHD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private void xoaCTHD( String mahd) {
		try {
			PreparedStatement stmt = con.prepareStatement(" DELETE FROM dbo.ChiTietHoaDon WHERE maHD = ?");
			stmt.setInt(1, Integer.parseInt(mahd) );
			
			stmt.executeUpdate();
			//themChiTietHoaDon(ctHD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public List<HoaDon>  getDSHoaDonCanTim(String giatri){
		List<HoaDon>  hoaDons = new ArrayList<HoaDon>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("\r\n"
					+ "	select hd.maHD ,hd.ngayLap,hd.maKH ,hd.maNV FROM hoadon AS hd JOIN dbo.KhachHang AS kh ON"
					+ "	kh.maKH = hd.maKH JOIN dbo.NhanVien AS nv ON nv.maNV = hd.maNV "
					+ "WHERE kh.tenKH  LIKE N'%"+giatri+"%' OR hd.maHD LIKE '"+giatri+"'  OR nv.tenNV LIKE N'%"+giatri+"%' OR hd.ngayLap = '"+giatri+"' OR hd.maKH LIKE '"+giatri+"' OR hd.maNV LIKE'"+giatri+"'" );
			while (rs.next()) {
				int mahD = rs.getInt(1);
				Date ngaylap = rs.getDate(2);
				int makh = rs.getInt(3);
				int maNV = rs.getInt(4);
				
				KhachHang khachHang = new KhachHangDao().timKhTHeoMaKH(makh);
				NhanVien nv = new NhanVienDao().tim1NV(maNV);			
				HoaDon hoaDon = new HoaDon(ngaylap);
				hoaDon.setMaHD(mahD);
				hoaDon.setKh(khachHang);
				hoaDon.setNv(nv);
				hoaDons.add(hoaDon);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return hoaDons;
	}
	public Map<Date, Double> layDoanhThuTheoNgay(String from ,String to ){
		Map<Date, Double>  map = new HashMap<Date, Double>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT hd.ngayLap,tong =sum (t.giaBan*ct.soluong)"
					+ " FROM dbo.HoaDon AS hd JOIN dbo.ChiTietHoaDon AS ct "
					+ "ON ct.maHD = hd.maHD JOIN dbo.Thuoc AS t "
					+ " ON t.maThuoc = ct.maThuoc "
					+ "WHERE hd.ngayLap BETWEEN '"+from+"' and '"+to+"' "
					+ "GROUP BY hd.ngayLap");
			while (rs.next()) {
				Date ngaylap = rs.getDate(1);
				double soTien = rs.getDouble(2);				
				map.put(ngaylap, soTien);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return map;
	}
	public Map<NhanVien, Double> layDoanhThuTheoNV(String from ,String to ){
		Map<NhanVien, Double>  map = new HashMap<NhanVien, Double>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT hd.maNV  ,tong=SUM (t.giaBan*ct.soluong)"
					+ " FROM dbo.HoaDon AS hd JOIN dbo.ChiTietHoaDon AS ct "
					+ " ON ct.maHD = hd.maHD JOIN dbo.Thuoc AS t "
					+ " ON t.maThuoc = ct.maThuoc "
					+ "WHERE hd.ngayLap BETWEEN '"+from+"' and '"+to+"' "
					+ " GROUP   BY hd.maNV");
			while (rs.next()) {
				int manv  = rs.getInt(1);
				double soTien = rs.getDouble(2);
				
				map.put(new NhanVienDao().tim1NV(manv), soTien);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return map;
	}
	public Map<NhanVien, Double> layDoanhThuTheoNV(){
		Map<NhanVien, Double>  map = new HashMap<NhanVien, Double>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT hd.maNV  ,tong=SUM (t.giaBan*ct.soluong)"
					+ " FROM dbo.HoaDon AS hd JOIN dbo.ChiTietHoaDon AS ct "
					+ " ON ct.maHD = hd.maHD JOIN dbo.Thuoc AS t "
					+ " ON t.maThuoc = ct.maThuoc "
					+ " GROUP   BY hd.maNV");
			while (rs.next()) {
				int manv  = rs.getInt(1);
				double soTien = rs.getDouble(2);
				
				map.put(new NhanVienDao().tim1NV(manv), soTien);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return map;
	}
	public Map<Date, Double> layDoanhThuTheoNgay() {
		Map<Date, Double>  map = new HashMap<Date, Double>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT hd.ngayLap,tong =sum (t.giaBan*ct.soluong)"
					+ " FROM dbo.HoaDon AS hd JOIN dbo.ChiTietHoaDon AS ct "
					+ "ON ct.maHD = hd.maHD JOIN dbo.Thuoc AS t "
					+ " ON t.maThuoc = ct.maThuoc "
				
					+ "GROUP BY hd.ngayLap");
			while (rs.next()) {
				Date ngaylap = rs.getDate(1);
				double soTien = rs.getDouble(2);				
				map.put(ngaylap, soTien);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return map;
	}
}
