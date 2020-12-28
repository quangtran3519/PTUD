package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Nuoc;

public class NuocDAO {
	private Connection con;
	public NuocDAO() {
		con = DatabaseConnect.getInstance().connect();
	}
	public List<Nuoc> getDSNuoc(){
		List<Nuoc> dsNuoc = new ArrayList<Nuoc>();
		try {
			PreparedStatement stmt = con.prepareStatement("select*from Nuoc");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Nuoc nuoc = new Nuoc(rs.getString("maNuoc"), rs.getString("tenNuoc"));
				dsNuoc.add(nuoc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsNuoc;
	}
	public boolean themNCC(Nuoc nuoc) {
		try {
			PreparedStatement stmt = con.prepareStatement("insert into Nuoc values(?,?)");
			stmt.setString(1, nuoc.getMaNuoc());
			stmt.setString(2, nuoc.getTenNuoc());
			
			int n = stmt.executeUpdate();
			if(n>0)
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}
}
