
package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import dao.DatabaseConnect;

//import Database.Data;
//import entity.NhanVien;

import java.awt.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Vector;

public class ThongKeThuoc extends JFrame {

	private DefaultTableModel tblMdTHHan, tblMdTTK, tblMdTHHang;
	private JTable tblTHHan, tblTTK, tblTHHang;

	public ThongKeThuoc() {
		setTitle("Thống kê thuốc");
		setSize(1000, 700);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(false);

		JTabbedPane tbbPane = new JTabbedPane();

		JPanel pnlThuocHetHan = createPnlThuocHetHan();
		JPanel pnlThuocTrongKho = createPnlThuocTrongKho();
		JPanel pnlThuocHetHang = createPnlThuocHetHang();

		tbbPane.addTab("Thuốc tồn kho", null, pnlThuocTrongKho);
		tbbPane.addTab("Thuốc sắp hết hạn sử dụng", null, pnlThuocHetHan);
		tbbPane.addTab("Thuốc sắp hết hàng", null, pnlThuocHetHang);
		add(tbbPane);
	}

	private JPanel createPnlThuocHetHan() {
		JPanel pnlThuocHetHan = new JPanel();
		pnlThuocHetHan.setLayout(null);
		JScrollPane scroll;
		String[] headers = "Mã thuốc;Tên thuốc;Số lượng tồn;Hạn sử dụng; Ngày còn lại".split(";");
		tblMdTHHan = new DefaultTableModel(headers, 0);
		add(scroll = new JScrollPane(tblTHHan = new JTable(tblMdTHHan), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
		tblTHHan.setRowHeight(20);
		scroll.setBounds(5, 5, 1350, 700);

		pnlThuocHetHan.add(scroll);
		loadDataTHHan();
		return pnlThuocHetHan;
	}

	private JPanel createPnlThuocTrongKho() {
		JPanel pnlThuocTrongKho = new JPanel();
		JScrollPane scroll;
		String[] headers = "Mã Thuốc;Tên thuốc;Đơn vị tính;Hạn sử dụng;Số lượng;Giá nhập;Giá bán".split(";");
		tblMdTTK = new DefaultTableModel(headers, 0);
		add(scroll = new JScrollPane(tblTTK = new JTable(tblMdTTK), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
		tblTTK.setRowHeight(20);
		scroll.setPreferredSize(new Dimension(1350, 700));
		pnlThuocTrongKho.add(scroll);
		loadDataTTK();
		return pnlThuocTrongKho;
	}

	private JPanel createPnlThuocHetHang() {
		JPanel pnlThuocHetHang = new JPanel(); 
		JScrollPane scroll;
		String[] headers = "Mã thuốc;Tên thuốc;Đơn vị tính;Số lượng còn lại".split(";");
		tblMdTHHang = new DefaultTableModel(headers, 0);
		add(scroll = new JScrollPane(tblTHHang = new JTable(tblMdTHHang), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
		tblTHHang.setRowHeight(20);
		scroll.setPreferredSize(new Dimension(1350, 700));
		pnlThuocHetHang.add(scroll);
		loadDataTHHang();
		return pnlThuocHetHang;
	}
	private void loadDataTTK() {
		try {		
			Connection con = DatabaseConnect.getInstance().connect();
			Statement stmt = con.createStatement();
			String sql = "SELECT * from Thuoc";
			ResultSet rs = stmt.executeQuery(sql);
			Vector data = null;
			while(rs.next()) {
				data = new Vector();
				data.add(rs.getString(1));
				data.add(rs.getString(2));
				data.add(rs.getString(3));
				data.add(rs.getString(4));
				data.add(rs.getInt(5));
				data.add(rs.getDouble(6));
				data.add(rs.getDouble(7));
				tblMdTTK.addRow(data);
			}
			tblTTK.setModel(tblMdTTK);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void loadDataTHHan() {
		try {		
			Connection con = DatabaseConnect.getInstance().connect();
			Statement stmt = con.createStatement();
			String sql = "SELECT maThuoc, tenThuoc, soLuong, hsd from Thuoc";
			ResultSet rs = stmt.executeQuery(sql);
			Vector data = null;

			while(rs.next()) {
				if(tinhNgayHetHan(LocalDate
						.parse(rs.getString(4)))<20&&tinhNgayHetHan(LocalDate
								.parse(rs.getString(4)))>=0) {
					data = new Vector();
					data.add(rs.getString(1));
					data.add(rs.getString(2));
					data.add(rs.getInt(3));
					data.add(rs.getString(4));
					data.add(tinhNgayHetHan(LocalDate
							.parse(rs.getString(4))));
					tblMdTHHan.addRow(data);
				}				
			}

			tblTHHan.setModel(tblMdTHHan);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static long tinhNgayHetHan(LocalDate hsd) {

		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();

		Date date1 = Date.valueOf(LocalDate.now());
		Date date2 = Date.valueOf(hsd);

		c1.setTime(date1);
		c2.setTime(date2);

		long noDay = (c2.getTime().getTime() - c1.getTime().getTime()) / (24 * 3600 * 1000);

		return noDay;
	}

	private void loadDataTHHang() {
		try {		
			Connection con = DatabaseConnect.getInstance().connect();
			Statement stmt = con.createStatement();
			String sql = "SELECT maThuoc, tenThuoc, donVi,soLuong from Thuoc";
			ResultSet rs = stmt.executeQuery(sql);
			Vector data = null;
			while(rs.next()) {
				if(rs.getString(3).equalsIgnoreCase("viên")&& rs.getInt(4)<=50){
					data = new Vector();
					data.add(rs.getString(1));
					data.add(rs.getString(2));
					data.add(rs.getString(3));
					data.add(rs.getInt(4));
					tblMdTHHang.addRow(data);
				}
				else if(rs.getString(3).equalsIgnoreCase("vỉ")&& rs.getInt(4)<=10){
					data = new Vector();
					data.add(rs.getString(1));
					data.add(rs.getString(2));
					data.add(rs.getString(3));
					data.add(rs.getInt(4));
					tblMdTHHang.addRow(data);
				}
				else if(rs.getString(3).equalsIgnoreCase("chai")&& rs.getInt(4)<=10){
					data = new Vector();
					data.add(rs.getString(1));
					data.add(rs.getString(2));
					data.add(rs.getString(3));
					data.add(rs.getInt(4));
					tblMdTHHang.addRow(data);
				}
				else if(rs.getString(3).equalsIgnoreCase("tuýp")&& rs.getInt(4)<=10){
					data = new Vector();
					data.add(rs.getString(1));
					data.add(rs.getString(2));
					data.add(rs.getString(3));
					data.add(rs.getInt(4));
					tblMdTHHang.addRow(data);
				}
				else if(rs.getString(3).equalsIgnoreCase("hộp")&& rs.getInt(4)<=10){
					data = new Vector();
					data.add(rs.getString(1));
					data.add(rs.getString(2));
					data.add(rs.getString(3));
					data.add(rs.getInt(4));
					tblMdTHHang.addRow(data);
				}
				
			}
			tblTHHang.setModel(tblMdTHHang);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//			public static void main (String [] args) {
//				SwingUtilities.invokeLater(()-> new ThongKeThuoc());
//			}
}
