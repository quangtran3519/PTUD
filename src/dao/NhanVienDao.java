package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import entity.NhanVien;
import entity.TaiKhoan;



public class NhanVienDao {
	Connection con = DatabaseConnect.getInstance().connect();
	ArrayList<NhanVien> dsNV;
	NhanVien nv;
	public NhanVienDao() {
		dsNV = new ArrayList<NhanVien>();
		nv = new NhanVien();
	}
	
	public ArrayList<NhanVien> doctuBang() {
		try {

			String sql = "select * from NhanVien";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			
			while(rs.next()) {
				int maNV = rs.getInt(1);
				String tenNV = rs.getString(2);
				String cmnd = rs.getString(3);
				String sdt = rs.getString(4);
				String diachi = (rs.getString(5));
				String calamviec = rs.getString(6);
				int gioitinh= rs.getInt(7);
				NhanVien s = new NhanVien(tenNV,cmnd, sdt, diachi,calamviec,gioitinh==1?true:false);
				s.setMaNV(maNV);
				if (!dsNV.contains(s)) {
					dsNV.add(s);
				}
				
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return dsNV;
	}
	
	public boolean create (NhanVien s) {

		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("insert into NhanVien values(?,?,?,?,?,?)");
		
			stmt.setString(1, s.getTenNV());
			stmt.setString(2, s.getCmnd());
			stmt.setString(3, s.getSdt());
			stmt.setString(4, s.getDiachi());
			stmt.setString(5, s.getCalamviec());
			stmt.setInt(6, s.isGioitinh()==true?1:0);
			n = stmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
		}
		return n > 0;
	}
	
	public boolean update (NhanVien nv) {
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("update NhanVien set tenNV = ?, "
					+ "sdt = ?, diaChi = ?, caLamViec = ?,gioitinh=? where cmnd = ?");
			stmt.setString(1, nv.getTenNV());
			stmt.setString(2, nv.getSdt());
			stmt.setString(3, nv.getDiachi());
			stmt.setString(4, nv.getCalamviec());
			stmt.setInt(5, nv.isGioitinh()?1:0);
			stmt.setString(6, nv.getCmnd());
			n = stmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
		}
		return n > 0;
	}
	
	public boolean delete (String x) {
		
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("delete from NhanVien where maNV like '%"+x+"%' or cmnd like '%"+x+"%'");
			
			n = stmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
		}
		return n > 0;
	}
	public NhanVien timNV(String cmnd) {
		NhanVien nv = null;
		String sql = "select * from NhanVien where cmnd ="+ cmnd;
		try {
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			
			while(rs.next()) {
				int manv = rs.getInt(1);
				String tenNV = rs.getString(2);
				String sdt = rs.getString(4);
				String diachi = (rs.getString(5));
				String calamviec = rs.getString(6);
				int gioitinh= rs.getInt(7);
				boolean gt =false;
				if (gioitinh==1) {
					gt=true;
				}else {
					gt=false;
				}
				nv = new NhanVien(tenNV, cmnd, sdt, diachi, calamviec,gt);
				nv.setMaNV(manv);
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	
		return nv;
	}

	public NhanVien tim1NV( int maNV) {
		NhanVien nv = null;
		String sql = "select * from NhanVien where maNV ="+ maNV;
		try {
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			
			while(rs.next()) {
				int manv = rs.getInt(1);
				String tenNV = rs.getString(2);
				String cmnd = rs.getString(3);
				String sdt = rs.getString(4);
				String diachi = (rs.getString(5));
				String calamviec = rs.getString(6);
				int gioitinh= rs.getInt(7);
				boolean gt =false;
				if (gioitinh==1) {
					gt=true;
				}else {
					gt=false;
				}
				nv = new NhanVien(tenNV, cmnd, sdt, diachi, calamviec,gt);
				nv.setMaNV(manv);
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	
		return nv;
	}

}
