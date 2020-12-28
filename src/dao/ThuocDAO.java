package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.LoaiThuoc;
import entity.NhaCungCap;
import entity.Nuoc;
import entity.Thuoc;

public class ThuocDAO {
	private Connection con;
	private List<Thuoc> dsThuoc;
	public ThuocDAO() {
		dsThuoc = new ArrayList<>();
		con = DatabaseConnect.getInstance().connect();
	}
	public boolean themThuoc(Thuoc thuoc) {
		try {
			PreparedStatement stmt = con.prepareStatement("insert into Thuoc values(?,?,?,?,?,?,?,?,?,?)");
			stmt.setString(1, thuoc.getMaThuoc());
			stmt.setString(2, thuoc.getTenThuoc());
			stmt.setString(3, thuoc.getDonViTinh());
			stmt.setDate(4, thuoc.getHsd());
			stmt.setInt(5, thuoc.getSoLuong());
			stmt.setDouble(6, thuoc.getGiaBan());
			stmt.setDouble(7, thuoc.getGiaNhap());
			stmt.setString(8, thuoc.getNhaCC().getMaNCC());
			stmt.setString(9, thuoc.getNuocSX().getMaNuoc());
			stmt.setString(10, thuoc.getLoai().getMaLoai());
			int n = stmt.executeUpdate();
			if(n>0)
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}
	public boolean capNhatThuoc(String maThuoc, String tenThuoc, String donVi, Date hsd, int soLuong, double giaBan, double giaNhap, String maNCC, String maNuoc, String maLoai) {
		try {
			PreparedStatement stmt = con.prepareStatement("update Thuoc set tenThuoc =?, donVi =?, hsd =?, soLuong =?, giaBan =?, giaNhap=?, maNPP =?, maNuoc =?, maLoai =? where maThuoc =?");
			stmt.setString(10,maThuoc );
			stmt.setString(1, tenThuoc);
			stmt.setString(2, donVi);
			stmt.setDate(3, hsd);
			stmt.setInt(4, soLuong);
			stmt.setDouble(5, giaBan);
			stmt.setDouble(6, giaNhap);
			stmt.setString(7, maNCC);
			stmt.setString(8, maNuoc);
			stmt.setString(9, maLoai);
			int n = stmt.executeUpdate();
			if(n>0)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}
	public boolean xoaThuoc(Thuoc thuoc) {
		try {
			PreparedStatement stmt = con.prepareStatement("delete from Thuoc where maThuoc = ?");
			stmt.setString(1, thuoc.getMaThuoc());
			int n = stmt.executeUpdate();
			if(n > 0)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;		
	}
	public List<Thuoc> layDSThuoc() {
		List<Thuoc> listThuoc = new ArrayList<Thuoc>();
		try {
			PreparedStatement stmt = con.prepareStatement("select * from Thuoc");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				Thuoc thuoc = new Thuoc(rs.getString("maThuoc"), rs.getString("tenThuoc"), rs.getString("donVi"), rs.getDate("hsd"), rs.getInt("soLuong"), rs.getDouble("giaBan"), rs.getDouble("giaNhap"));
				NhaCungCap ncc = findNCC(rs.getString("maNPP"));
				thuoc.setNhaCC(ncc);
				Nuoc nuoc = new Nuoc(rs.getString("maNuoc"));
				thuoc.setNuocSX(nuoc);		
				LoaiThuoc loai = new LoaiThuoc(rs.getString("maLoai"));
				thuoc.setLoai(loai);
				listThuoc.add(thuoc);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return listThuoc;
	}
	public Thuoc timKiem(String maThuoc) {
		Thuoc thuoc = new Thuoc(maThuoc);
		if(dsThuoc.contains(thuoc))
			return dsThuoc.get(dsThuoc.indexOf(thuoc));
		return null;
	}
	public Thuoc getThuoc(int i) {
		if (i>=0&&i<dsThuoc.size())
			return dsThuoc.get(i);
		return null;
	}
	private NhaCungCap findNCC(String ma) {
		NhaCungCap nhaCungCap =null;
		try{

			java.sql.Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery("select *from NhaCungCap where maNPP = '" + ma+"'");
			while(rs.next()) {
				String maNPP  = rs.getString(1);
				String tenNPP = rs.getString(2);
				nhaCungCap = new NhaCungCap(maNPP, tenNPP);

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return nhaCungCap;
	}
	public ArrayList<Thuoc> timThuoc(String values) {
		ArrayList<Thuoc> thuocs = new ArrayList<>();

		try {
			java.sql.Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery("select *from Thuoc where  tenThuoc like '%"+values+"%' or maThuoc like '%"+values+"%'");
			while(rs.next()) {
				String maThuoc  = rs.getString(1);
				String tenThuoc = rs.getString(2);
				String donVi  = rs.getString(3);
				Date hsd = rs.getDate(4);
				int soLuong = rs.getInt(5);
				double giaBan  = rs.getDouble(6);
				double giaNhap = rs.getDouble(7);
				String maNPP = rs.getString(8);
				Thuoc thuoc = new Thuoc(maThuoc, tenThuoc, donVi, hsd, soLuong, giaBan, giaNhap);
				NhaCungCap NCC = findNCC(maNPP);
				thuoc.setNhaCC(NCC );
				thuocs.add(thuoc);	
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}


		return thuocs;
	}

	public Thuoc lay1Thuoc(String maThuoc) {
		Thuoc thuoc = null;
		try{

			java.sql.Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery("select *from Thuoc where maThuoc = '" + maThuoc+"'");
			while(rs.next()) {
				String msThuoc  = rs.getString(1);
				String tenThuoc = rs.getString(2);
				String donVi  = rs.getString(3);
				Date hsd = rs.getDate(4);
				int soLuong = rs.getInt(5);
				double giaBan  = rs.getDouble(6);
				double giaNhap = rs.getDouble(7);
				String maNPP = rs.getString(8);
				thuoc = new Thuoc(msThuoc, tenThuoc, donVi, hsd, soLuong, giaBan, giaNhap);
				NhaCungCap NCC = findNCC(maNPP);
				thuoc.setNhaCC(NCC );
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return thuoc;
	}
}
