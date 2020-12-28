package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class DatabaseConnect {
	public static Connection con =null;
	private static DatabaseConnect instance = new DatabaseConnect();
	public static DatabaseConnect getInstance() {
		return instance;
	}
	public Connection connect() {
		String url ="jdbc:sqlserver://localhost:1433;databaseName=QLQT";
		String user ="sa";
		String password= "123";
		//System.out.println("ket noi db");
		try {
			con =DriverManager.getConnection(url, user, password);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("ket noi db fail");
		}
		return con;
	}
	public static void main(String[] args) {
		Connection con = DatabaseConnect.getInstance().connect();
	}
//	 public static ResultSet Getdata(String cauTruyVan){
//	        try {
//	            Statement stm=con.createStatement();
//	            //thực thicaau truy vấn select dc truyền vào từ
//	            //tham số cautruyvan
//	            //trả về kết quả là ResultSet
//	            ResultSet rs=stm.executeQuery(cauTruyVan);
//	            return rs;//trả về resultset nếu thành công
//	        } catch (SQLException ex) {
//	            System.out.println("lỗi truy vấn");
//	            return null;
//	        }
//	    
//	    }
}
