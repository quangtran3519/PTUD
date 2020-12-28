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



public class TimKiemNhanVien extends JFrame {
	
	private DefaultTableModel tableModel;
	private JTable table;
	private JComboBox<String> cbbTimKiem;
	private JTextField txtTimKiem;
	private JButton btnTimKiem;

	public TimKiemNhanVien() {
		setSize(1000, 600);
		setLocationRelativeTo(null);
		uiTkNV();
	}

	private void uiTkNV() {
		JPanel pnNorth;
		add(pnNorth = new JPanel(), BorderLayout.NORTH);
		pnNorth.setLayout(null);
		pnNorth.setPreferredSize(new Dimension(1000, 120));
		JLabel lblTieuDe, lblTimKiem;
		pnNorth.add(lblTieuDe = new JLabel("TÌM KIẾM NHÂN VIÊN"));
		lblTieuDe.setFont(new Font("Arial", Font.BOLD, 30));
		lblTieuDe.setForeground(Color.RED);
		lblTieuDe.setBounds(300, 10, 400, 40);
		
		lblTimKiem = new JLabel("Tìm kiếm theo: ");
		lblTimKiem.setBounds(10, 70, 100, 20);
		pnNorth.add(lblTimKiem);
		
		cbbTimKiem = new JComboBox<String>();
		cbbTimKiem.addItem("Mã nhân viên");
		cbbTimKiem.addItem("Tên nhân viên");
		cbbTimKiem.addItem("Số điện thoại");
		cbbTimKiem.setBounds(100, 70, 120, 20);
		pnNorth.add(cbbTimKiem);
		txtTimKiem = new JTextField();
		txtTimKiem.setBounds(230, 70, 200, 20);
		pnNorth.add(txtTimKiem);
		
		btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.setBounds(435, 70, 100, 20);
		pnNorth.add(btnTimKiem);

		
		JScrollPane scroll;
		String[] headers = "Mã NV;Tên NV;Giới tính;Ngày sinh;Số điện thoại;Lương;Ghi chú".split(";");
		tableModel = new DefaultTableModel(headers, 0);
		add(scroll = new JScrollPane(table = new JTable(tableModel), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
		JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.SOUTH);
		scroll.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.blue), "Danh sách nhân viên"));
		table.setRowHeight(20);
		
	}


	public static void main(String[] args) {
		new TimKiemNhanVien().setVisible(true);
	}


}
