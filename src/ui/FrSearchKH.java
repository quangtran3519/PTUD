package ui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


import dao.DatabaseConnect;



public class FrSearchKH extends JFrame implements ActionListener {
	private DefaultTableModel tableModel;
	private JTable table;
	private JComboBox<String> cbbTimKiem;
	private JTextField txtTimKiem;
	private JButton btnTimKiem;
	public FrSearchKH() {
		setSize(1000, 600);
		setLocationRelativeTo(null);
		uiTkKH();
		setVisible(false);
	}
	
	private void uiTkKH() {
		JPanel pnNorth;
		add(pnNorth = new JPanel(), BorderLayout.NORTH);
		pnNorth.setLayout(null);
		pnNorth.setPreferredSize(new Dimension(1000, 120));
		JLabel lblTieuDe, lblTimKiem;
		pnNorth.add(lblTieuDe = new JLabel("TÌM KIẾM KHÁCH HÀNG"));
		lblTieuDe.setFont(new Font("Arial", Font.BOLD, 30));
		lblTieuDe.setForeground(Color.RED);
		lblTieuDe.setBounds(300, 10, 400, 40);
		
		lblTimKiem = new JLabel("Tìm kiếm theo: ");
		lblTimKiem.setBounds(10, 70, 100, 20);
		pnNorth.add(lblTimKiem);
		
		cbbTimKiem = new JComboBox<String>();
		cbbTimKiem.addItem("Mã khách hàng");
		cbbTimKiem.addItem("Tên khách hàng");
		cbbTimKiem.addItem("Số điện thoại");
		cbbTimKiem.addItem("Chứng minh nhân dân");
		cbbTimKiem.setBounds(100, 70, 120, 20);
		pnNorth.add(cbbTimKiem);
		txtTimKiem = new JTextField();
		txtTimKiem.setBounds(230, 70, 200, 20);
		pnNorth.add(txtTimKiem);
		
		btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.setBounds(435, 70, 100, 20);
		pnNorth.add(btnTimKiem);
		btnTimKiem.addActionListener(this);
		
		JScrollPane scroll;
		String[]  headers = "Mã KH;Họ và Tên;Giới tính;Số điện thoại;Địa chỉ;Chứng minh Nhân dân".split(";");
		tableModel = new DefaultTableModel(headers, 0);
		add(scroll = new JScrollPane(table = new JTable(tableModel), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
		JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.SOUTH);
		scroll.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.blue), "Danh sách thành viên"));
		table.setRowHeight(20);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnTimKiem)) {
		TkThanhVien(txtTimKiem.getText());
		}
	}
	
	private void TkThanhVien(String text) {
		if (txtTimKiem.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "bạn hãy nhập giá trị cần tìm");
		}else {
			try {
				Connection con = DatabaseConnect.getInstance().connect();
				String sql = "SELECT maKH,tenKH, gioitinh, sdt,diachi ,cmnd FROM dbo.Khachhang";
				Statement stmt = con.createStatement();
				if(cbbTimKiem.getSelectedItem().toString() == "Mã khách hàng" ) {
					if (txtTimKiem.getText().length() > 0) {
						sql = sql + " where maKH like '%" + txtTimKiem.getText() + "%'";
					}
				}
				else if (cbbTimKiem.getSelectedItem().toString()=="Tên khách hàng") {
					if (txtTimKiem.getText().length() > 0) {
						sql = sql + " where tenKH like N'%" + txtTimKiem.getText() + "%'";
					}
				}else if (cbbTimKiem.getSelectedItem().toString()=="Số điện thoại") {
					if (txtTimKiem.getText().length() > 0) {
						sql = sql + " where sdt like N'%" + txtTimKiem.getText() + "%'";
					}
				}else if (cbbTimKiem.getSelectedItem().toString()=="Chứng minh nhân dân") {
					if (txtTimKiem.getText().length() > 0) {
						sql = sql + " where cmnd like N'%" + txtTimKiem.getText() + "%'";
					}
				}
					
				ResultSet rs = stmt.executeQuery(sql);
				Vector data = null;
				tableModel.setRowCount(0);
				if (rs.isBeforeFirst() == false) {
					  JOptionPane.showMessageDialog(this, " Không tìm thấy khách hàng!");
					  return;
					 }
				while(rs.next()) {
					data = new Vector();
					data.add(rs.getString(1));
					data.add(rs.getString(2));
					data.add(rs.getInt(3)==0?"Nam":"Nữ");
					data.add(rs.getString(4));
					data.add(rs.getString(5));
					data.add(rs.getString(6));
					tableModel.addRow(data);
				}
			table.setModel(tableModel);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		}
		
	
}
