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
import dao.HoaDonDao;
import dao.ThuocDAO;
import entity.HoaDon;
import entity.Thuoc;



public class Form_TimHoaDon extends JFrame implements ActionListener {
	private DefaultTableModel tableModel;
	private JTable table;
	private JComboBox<String> cbbTimKiem;
	private JTextField txtTimKiem;
	private JButton btnTimKiem;
	public Form_TimHoaDon() {
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
		pnNorth.add(lblTieuDe = new JLabel("TÌM KIẾM HÓA ĐƠN"));
		lblTieuDe.setFont(new Font("Arial", Font.BOLD, 30));
		lblTieuDe.setForeground(Color.RED);
		lblTieuDe.setBounds(300, 10, 400, 40);
		
		lblTimKiem = new JLabel("Nhập thông tin: ");
		lblTimKiem.setBounds(10, 70, 100, 20);
		pnNorth.add(lblTimKiem);
		
//		cbbTimKiem = new JComboBox<String>();
//		cbbTimKiem.addItem("Mã khách hàng");
//		cbbTimKiem.addItem("Tên khách hàng");
//		cbbTimKiem.addItem("Số điện thoại");
//		cbbTimKiem.addItem("Chứng minh nhân dân");
//		cbbTimKiem.setBounds(100, 70, 120, 20);
//		pnNorth.add(cbbTimKiem);
		txtTimKiem = new JTextField();
		txtTimKiem.setBounds(230, 70, 200, 20);
		pnNorth.add(txtTimKiem);
		
		btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.setBounds(435, 70, 100, 20);
		pnNorth.add(btnTimKiem);
		btnTimKiem.addActionListener(this);
		
		JScrollPane scroll;
		String[] headers = "Mã hóa đơn,Ngày lập , Tên khách hàng, Tên nhân viên".split(",");
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
			HoaDonDao hoaDonDao = new HoaDonDao();
		if (txtTimKiem.getText().equals("")) {
			tableModel = (DefaultTableModel)table.getModel();
			tableModel.getDataVector().removeAllElements();
		
			for(HoaDon hd : hoaDonDao.getDSHoaDon()) {
				String[] rowData = {hd.getMaHD()+"",hd.getNgayLap()+"",hd.getKh().getHoTen(),hd.getNv().getTenNV()};
				tableModel.addRow(rowData);
			}
			table.setModel(tableModel);
		}else {
			tableModel = (DefaultTableModel)table.getModel();
			tableModel.getDataVector().removeAllElements();
			if (hoaDonDao.getDSHoaDonCanTim(txtTimKiem.getText()).size() >0) {
				for(HoaDon hd : hoaDonDao.getDSHoaDonCanTim(txtTimKiem.getText())) {
					String[] rowData = {hd.getMaHD()+"",hd.getNgayLap()+"",hd.getKh().getHoTen(),hd.getNv().getTenNV()};
					tableModel.addRow(rowData);
				}
				table.setModel(tableModel);
			}else {
				JOptionPane.showMessageDialog(null, " Không tìm thấy dữ liệu");
			}
			
		}
		}
	}
	
	
	
}
