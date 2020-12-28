package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.LoaiThuoc;

public class LoaiDAO {
	private Connection con;
	public LoaiDAO() {
		con = DatabaseConnect.getInstance().connect();
	}
	public List<LoaiThuoc> getDSLoai(){
		List<LoaiThuoc> dsLoai = new ArrayList<LoaiThuoc>();
		try {
			PreparedStatement stmt = con.prepareStatement("select*from Loai");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				LoaiThuoc loai = new LoaiThuoc(rs.getString("maLoai"), rs.getString("tenLoai"));
				dsLoai.add(loai);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsLoai;
	}
	public boolean themLoai(LoaiThuoc loai) {
		try {
			PreparedStatement stmt = con.prepareStatement("insert into Loai values(?,?)");
			stmt.setString(1, loai.getMaLoai());
			stmt.setString(2, loai.getTenLoai());
			
			int n = stmt.executeUpdate();
			if(n>0)
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}
}
