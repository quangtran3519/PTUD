/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entity.TaiKhoan;
import entity.Thongbao;

public class UserDao {
	static Connection con = DatabaseConnect.getInstance().connect();
	ArrayList<TaiKhoan> dstk;
	public UserDao() {
		dstk = new ArrayList<TaiKhoan>();
	}
	public ArrayList<TaiKhoan> docTK(){
		try {
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery("select *from TaiKhoan");
			while(rs.next()) {
				String tentk = rs.getString(1);
				String pass = rs.getString(2);
				int manv = rs.getInt(3);
				String quyen = rs.getString(4);
				TaiKhoan tk = new TaiKhoan(tentk, pass, manv, quyen);
				if(!dstk.contains(tk)) {
					dstk.add(tk);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dstk;
	}
	public boolean Them(TaiKhoan s) {
		PreparedStatement stmt = null;
		int n = 0;
			try {
				
				stmt = con.prepareStatement("insert into TaiKhoan values(?,?,?,?)");

				stmt.setString(1, s.getTenTk());
				stmt.setString(2, s.getMatKhau());
				stmt.setInt(3, s.getMaNV());
				stmt.setString(4, s.getQuyen());

				n = stmt.executeUpdate();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		return n > 0;

	}

public boolean delete (int ma) {
		
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("delete from TaiKhoan where maNV like '%"+ma+"%'");
			
			n = stmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
		}
		return n > 0;
	}


	public static void SuaMK(String MK, String TenUser) {
		String sql = "UPDATE [dbo].[TaiKhoan] SET  "
				+ "[matKhau] =N'" + MK + "' "
				+ " "
				+ " WHERE tenTk=N'" + TenUser + "'";
		Statement statement;
		int kq=-1;
		try {
			statement = con.createStatement();
			int rs = statement.executeUpdate(sql);
			kq=rs;
		} catch (SQLException e) {

			e.printStackTrace();
			System.out.println("Lỗi truy vấn!");
		}

		if (kq > 0) {
			Thongbao.thongbao("Thành công", "");

		} else {
			Thongbao.thongbao("Thất bại", "");
		}

	}
}
