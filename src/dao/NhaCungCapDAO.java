package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import entity.NhaCungCap;

public class NhaCungCapDAO {
	private Connection con;

	public NhaCungCapDAO() {
		con = DatabaseConnect.getInstance().connect();
	}
	public List<NhaCungCap> getDSNCC() throws SQLException {
		List<NhaCungCap> dsNCC = new ArrayList<NhaCungCap>();
		
		try {
			PreparedStatement stmt = con.prepareStatement("select*from NhaCungCap");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				NhaCungCap ncc = new NhaCungCap(rs.getString("maNPP"),rs.getString("tenNPP"));
				dsNCC.add(ncc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsNCC;
	}
	public boolean themNCC(NhaCungCap ncc) {
		try {
			PreparedStatement stmt = con.prepareStatement("insert into NhaCungCap values(?,?)");
			stmt.setString(1, ncc.getMaNCC());
			stmt.setString(2, ncc.getTenNCC());
			
			int n = stmt.executeUpdate();
			if(n>0)
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}
}
