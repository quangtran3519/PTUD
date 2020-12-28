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
import java.time.format.DateTimeFormatter;
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
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import dao.DatabaseConnect;





public class FormTimKiemThuoc extends JFrame implements ActionListener{

	private DefaultTableModel tableModel;
	private JTable table;
	private JComboBox<String> cbbTimKiem;
	private JTextField tfTimKiem;
	private JButton btnTimKiem;

	public  FormTimKiemThuoc() {
		setSize(1000, 600);
		setLocationRelativeTo(null);
		setVisible(false);

		JPanel pnNorth;
		add(pnNorth = new JPanel(), BorderLayout.NORTH);
		pnNorth.setLayout(null);
		pnNorth.setPreferredSize(new Dimension(1000, 120));
		JLabel lblTieuDe, lblTimKiem;
		pnNorth.add(lblTieuDe = new JLabel("TÌM KIẾM THUỐC"));
		lblTieuDe.setFont(new Font("Arial", Font.BOLD, 30));
		lblTieuDe.setForeground(Color.RED);
		lblTieuDe.setBounds(300, 10, 400, 40);

		lblTimKiem = new JLabel("Tìm kiếm theo: ");
		lblTimKiem.setBounds(10, 70, 100, 20);
		pnNorth.add(lblTimKiem);

		cbbTimKiem = new JComboBox<String>();
		cbbTimKiem.addItem("Tên thuốc");
		cbbTimKiem.addItem("Mã thuốc");
		cbbTimKiem.setBounds(100, 70, 140, 20);
		pnNorth.add(cbbTimKiem);
		tfTimKiem = new JTextField();
		tfTimKiem.setBounds(250, 70, 200, 20);
		pnNorth.add(tfTimKiem);

		btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.setBounds(460, 70, 100, 20);
		pnNorth.add(btnTimKiem);
		btnTimKiem.addActionListener(this);

		JScrollPane scroll;
		String[] headers = "Mã Thuốc;Tên thuốc;Đơn vị tính;Hạn sử dụng;Số lượng;Giá bán;Giá nhập;Nhà phân phối".split(";");
		tableModel = new DefaultTableModel(headers, 0);
		add(scroll = new JScrollPane(table = new JTable(tableModel), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.SOUTH);
		scroll.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.blue), "Danh sách thuốc"));
		table.setRowHeight(20);
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnTimKiem)) {
			if (tfTimKiem.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "phải nhập trước khi tìm");
			}else timKiemThuoc(tfTimKiem.getText());
		}}


	private void timKiemThuoc (String s) {
		try {
			Connection con = DatabaseConnect.getInstance().connect();
			String sql = "select * from Thuoc";
			Statement stmt = con.createStatement();
			if(cbbTimKiem.getSelectedItem().toString() == "Mã thuốc" ) {
				if (tfTimKiem.getText().length() > 0) {
					sql = sql + " where maThuoc like '%" + tfTimKiem.getText() + "%'";
				}
			}
					else {
							if (tfTimKiem.getText().length() > 0) {
								sql = sql + " where tenThuoc like '%" + tfTimKiem.getText() + "%'";
						}
						}				
						ResultSet rs = stmt.executeQuery(sql);
						Vector data = null;
						tableModel.setRowCount(0);
						if (rs.isBeforeFirst() == false) {
							  JOptionPane.showMessageDialog(null, "Không tìm thấy!");
							  return;
							 }
						while(rs.next()) {
							data = new Vector();
							data.add(rs.getString("maThuoc"));
							data.add(rs.getString("tenThuoc"));
							data.add(rs.getString("donVi"));
							data.add(rs.getDate("hsd"));
							data.add(rs.getInt("soLuong"));
							data.add(rs.getDouble("giaBan"));
							data.add(rs.getDouble("giaNhap"));
							data.add(rs.getString("maNPP"));
							tableModel.addRow(data);
						}
					table.setModel(tableModel);
					} catch (Exception e) {
						e.printStackTrace();
		}

	}
//	public static void main(String[] args) {
//		SwingUtilities.invokeLater(()-> new FormTimKiemThuoc());
//	}


}
