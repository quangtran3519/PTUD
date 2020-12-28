package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;



import dao.DangNhapDao;
import dao.DatabaseConnect;
import entity.Thongbao;



public class Frm_UserLogin extends  JFrame implements ActionListener{
	public static int maHD;
	private JButton btnNewButtonOut;
	private JButton btnNewButtonLogin;
	private JLabel lblChechbox;
	private JPasswordField passwordField;
	private JTextField userName;
	private JComboBox<String> optQuyen;
	static Connection con = DatabaseConnect.getInstance().connect();
	private Container contain;
	public Frm_UserLogin() {
		setTitle("Giao diện đăng nhập");
//		setExtendedState(MAXIMIZED_BOTH);
		setSize(800, 600);
		setVisible(true);
		setLocationRelativeTo(null);
		
		
		
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabelTenPhanMem = new JLabel("PHẦN MỀM QUẢN LÝ QUẦY THUỐC");
		lblNewLabelTenPhanMem.setForeground(Color.BLUE);
		lblNewLabelTenPhanMem.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblNewLabelTenPhanMem.setBounds(120, 20, 700, 93);
		contentPane.add(lblNewLabelTenPhanMem);

		JLabel lblNewLabelTenGiaoDien = new JLabel("ĐĂNG NHẬP");
		lblNewLabelTenGiaoDien.setForeground(Color.red);
		lblNewLabelTenGiaoDien.setFont(new Font("Times New Roman", Font.PLAIN, 35));
		lblNewLabelTenGiaoDien.setBounds(300, 100, 273, 93);
		contentPane.add(lblNewLabelTenGiaoDien);

		userName = new JTextField();
		userName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		userName.setBounds(250, 200, 300, 35);
		contentPane.add(userName);
		userName.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 29));
		passwordField.setBounds(250, 250, 300, 35);
		contentPane.add(passwordField);

		JLabel lblUsername = new JLabel("Tên tài khoản:");
		lblUsername.setBackground(Color.BLACK);
		lblUsername.setForeground(Color.BLACK);
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUsername.setBounds(100, 200, 193, 35);
		contentPane.add(lblUsername);

		JLabel lblPassword = new JLabel("Mật khẩu:");
		lblPassword.setForeground(Color.BLACK);
		lblPassword.setBackground(Color.CYAN);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPassword.setBounds(100, 250, 193, 35);
		contentPane.add(lblPassword);

		lblChechbox = new JLabel("Người sử dụng:");
		lblChechbox.setForeground(Color.BLACK);
		lblChechbox.setBackground(Color.CYAN);
		lblChechbox.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblChechbox.setBounds(100, 300, 193, 35);
		contentPane.add(lblChechbox);

		optQuyen = new JComboBox<String>();
		optQuyen.addItem("Admin");
		optQuyen.addItem("User");
		optQuyen.setBounds(250, 300, 100, 35);
		contentPane.add(optQuyen);

		btnNewButtonLogin = new JButton("Đăng nhập");
		btnNewButtonLogin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButtonLogin.setBounds(250, 400, 120, 35);
		contentPane.add(btnNewButtonLogin);

		btnNewButtonLogin.addActionListener(this);

		btnNewButtonOut = new JButton("Thoát");
		btnNewButtonOut.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButtonOut.setBounds(400, 400, 120, 35);
		btnNewButtonOut.addActionListener(this);
		contentPane.add(btnNewButtonOut);

		JLabel labelBtnLogin = new JLabel("");
		labelBtnLogin.setBounds(0, 0, 1008, 562);
		contentPane.add(labelBtnLogin);
		
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		int a=-1;
		if(o.equals(btnNewButtonLogin)){
			String TenTK, MK;
			TenTK = userName.getText();
			MK = String.valueOf(passwordField.getPassword());
			boolean kt = DangNhapDao.KT_DangNhap(TenTK, MK);
			if (kt) {
				String quyen= DangNhapDao.layQuyen();
				if (optQuyen.getSelectedItem().toString().equalsIgnoreCase("Admin") && quyen.equalsIgnoreCase("Admin")) {
//					Thongbao.thongbao("Đăng nhập thành công!", "Thông báo đăng nhập");
					Form_QuanLy frql = new Form_QuanLy();
					frql.setVisible(true);
					this.dispose();
					a=1;

				}

				if (optQuyen.getSelectedItem().toString().equalsIgnoreCase("User") && quyen.equalsIgnoreCase("User")) {
//					Thongbao.thongbao("Đăng nhập thành công!", "Thông báo đăng nhập");
					Form_NhanVien frnv= new Form_NhanVien();
					frnv.setVisible(true);
					this.dispose();
					a=1;
				} 
				if(a==-1) {
					Thongbao.thongbao("Chọn sai quyền! Mời bạn chọn lại người sử dụng", "Thông báo");
				}
			} 
			

		}                 
		if(o.equals(btnNewButtonOut)){
			System.exit(1);
		}
	}
	private static void EXIT_ON_CLOSE() {
		
		
	}

	public String getTenTK() {
		return userName.getText().toString();	
	}
	private void doiPanel(Container container) {
		getContentPane().removeAll();
		getContentPane().add(container);
		getContentPane().setVisible(true);
		revalidate();
		repaint();

	}
}
