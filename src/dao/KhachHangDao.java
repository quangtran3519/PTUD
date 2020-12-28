package dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import entity.KhachHang;

public class KhachHangDao  {
	Connection con = DatabaseConnect.getInstance().connect();
	ArrayList<KhachHang> dsKhacHang;
	KhachHang kh;
	public KhachHangDao() {
		dsKhacHang = new ArrayList<KhachHang>();
		kh= new KhachHang();
	}
	public ArrayList<KhachHang> doctuBang(){
		try {
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM dbo.KhachHang WHERE  cmnd NOT LIKE ''");
			while(rs.next()) {
			int ma = rs.getInt(1);
			String hoten = rs.getString(2);
			int gioitinh = rs.getInt(3);
			String sdt =rs.getString(4);
			String diachi = rs.getString(6);
			String cmnd = rs.getString(5);
			 KhachHang s = new KhachHang(ma, hoten, gioitinh==0?true:false, sdt, diachi, cmnd);
			 dsKhacHang.add(s);
			}		
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("loi doc tu bang");
		}
		return dsKhacHang;
	}
	public boolean ThemKh(KhachHang x) {
		int n=0;		
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement("insert into khachhang values (?,?,?,?,?)");
			stmt.setString(1, x.getHoTen());
			stmt.setInt(2, (x.isGioiTinh()==true) ?0:1);
			stmt.setString(3, x.getSoDT());
			stmt.setString(5, x.getDiaChi());
			stmt.setString(4, x.getCmnd());
			n = stmt.executeUpdate();
			 
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			}

		return n>0;
		
	}
	public boolean XoaKH(String cmnd) {
		PreparedStatement stmt = null;
		int n=0;
		try {

			stmt = con.prepareStatement("delete from khachHang where cmnd=? ");
			stmt.setString(1, cmnd);
			n= stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n>0;
	}
	public KhachHang  timkiemKH(String x) {
		KhachHang kh = null;
		Statement statement;
		try {
			statement = con.createStatement();
			ResultSet rs = statement.executeQuery("select * from Khachhang where cmnd ='"+x+"' or maKH ='"+x+"'");
			while(rs.next()) {
			int ma = rs.getInt(1);
			String hoten = rs.getString(2);
			int gioitinh = rs.getInt(3);
			String sdt =rs.getString(4);
			String diachi = rs.getString(5);
			String cmnd = rs.getString(6);
			 kh = new KhachHang(hoten, (gioitinh==0 ? true:false), sdt, diachi, cmnd);
			 kh.setMaKH(ma);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return kh;
	}
	public KhachHang  timkiem1KH(String x) {
		KhachHang kh = null;
		Statement statement;
		try {
			statement = con.createStatement();
			ResultSet rs = statement.executeQuery("	select * from Khachhang where tenKH LIKE N'"+x+"'");
			while(rs.next()) {
			int ma = rs.getInt(1);
			String hoten = rs.getString(2);
			int gioitinh = rs.getInt(3);
			String sdt =rs.getString(4);
			String diachi = rs.getString(5);
			String cmnd = rs.getString(6);
			 kh = new KhachHang(hoten, (gioitinh==0 ? true:false), sdt, diachi, cmnd);
			 kh.setMaKH(ma);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return kh;
	}
	public KhachHang  timKhTHeoMaKH(int x) {
		KhachHang kh = null;
		Statement statement;
		try {
			statement = con.createStatement();
			ResultSet rs = statement.executeQuery("	select * from Khachhang where maKH ="+x);
			while(rs.next()) {
			int ma = rs.getInt(1);
			String hoten = rs.getString(2);
			int gioitinh = rs.getInt(3);
			String sdt =rs.getString(4);
			String diachi = rs.getString(5);
			String cmnd = rs.getString(6);
			 kh = new KhachHang(hoten, (gioitinh==0 ? true:false), sdt, diachi, cmnd);
			 kh.setMaKH(ma);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return kh;
	}
	public boolean capNhapKh(KhachHang x) {		
		PreparedStatement stmt = null;
		int n=0;
		try {
			stmt = con.prepareStatement("update khachHang set tenKH =?,gioitinh=?,sdt=?,diaChi=? where cmnd =?");
			stmt.setString(1, x.getHoTen());
			stmt.setInt(2, (x.isGioiTinh()==true) ?0:1);
			stmt.setString(3, x.getSoDT());
			stmt.setString(4, x.getDiaChi());
			stmt.setString(5, x.getCmnd());
			n = stmt.executeUpdate();	
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			
		}
		return n>0;
	}
	
 
}
