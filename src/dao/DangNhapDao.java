/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.KhachHang;
import entity.Thongbao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class DangNhapDao {

	public static String TenUser, MatKhau, quyen;
	static Connection con = DatabaseConnect.getInstance().connect();
	public static ResultSet LayTaiKhoanTheoMa(String TK) {
		String sql = "SELECT * FROM [dbo].[TaiKhoan] where tenTk =N'" + TK + "'";
		Statement statement;
		try {
			statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			return rs;
		} catch (SQLException e) {

			e.printStackTrace();
			return null;
		}
	} 

	public static boolean KT_DangNhap(String TK, String MK) {
		if (TK.trim().equals("") || MK.trim().equals("")) {
			Thongbao.thongbao("Không bỏ trống dữ liệu", "Thông báo");
			return false;
		}
		ResultSet rs = LayTaiKhoanTheoMa(TK);
		try {
			if (rs.next()) {
				if (rs.getString("tenTk").matches(TK)) {
					if (rs.getString("matKhau").matches(MK)) {
						System.out.println("Đăng nhập thành công");
						TenUser=rs.getString("tenTk");
						quyen=rs.getString("quyen");
						MatKhau=rs.getString("matKhau");
						return true;
					}
					Thongbao.thongbao("Tài khoản hoặc mật khẩu sai", "Thông báo");
					return false;

				}
				Thongbao.thongbao("Tài khoản hoặc mật khẩu sai", "Thông báo");
				return false;

			}
		} catch (SQLException ex) {

		}
		Thongbao.thongbao("Tài khoản hoặc mật khẩu sai", "Thông báo");
		return false;
	}
	public static String layQuyen() {
		
		return quyen;
	}
	public static boolean KT_DoiMK(String mkcu,String mkmoi,String nhaplai){
		if (!mkcu.equals(MatKhau)) {
			Thongbao.thongbao("Mật khẩu cũ sai", "");
			return false;
		}
		if (mkmoi.length()<5) {
			Thongbao.thongbao("mật khẩu dài hơn 5 ký tự", "");
			return false;
		}
		if (!mkmoi.equals(nhaplai)) {
			Thongbao.thongbao("Nhập lại mật khẩu", "");
			return false;
		}
		return true;
	}
	public static int getMaNV() {
		int manv = 0;
		Statement statement;
		try {
			statement = con.createStatement();
			ResultSet rs = statement.executeQuery("	SELECT nv.maNV FROM dbo.NhanVien AS nv JOIN dbo.TaiKhoan AS tk ON tk.maNV = nv.maNV where tenTk =N'"+ TenUser+"'");
			while(rs.next()) {
			manv = rs.getInt(1);
		
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return manv;
	}
}
