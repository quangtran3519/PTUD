package ui;

import java.awt.Font;

import dao.DangNhapDao;
import dao.UserDao;
import entity.Thongbao;
public class Frm_DoiMatKhau extends javax.swing.JFrame {

	/**
	 * Creates new form Frm_DoiMatKhau
	 */
	public Frm_DoiMatKhau() {
		initComponents();
	}

	@SuppressWarnings("unchecked")
	private void initComponents() {

		pnl_Nen = new javax.swing.JPanel();
		lbl_MKcu = new javax.swing.JLabel();
		lbl_MKmoi = new javax.swing.JLabel();
		lbl_NhapLai = new javax.swing.JLabel();
		lbl_DoiMK = new javax.swing.JLabel();
		btn_Doi = new javax.swing.JButton();
		btn_Huy = new javax.swing.JButton();
		txt_MKmoi = new javax.swing.JPasswordField();
		txt_MKcu = new javax.swing.JPasswordField();
		txt_NhapLai = new javax.swing.JPasswordField();
		lbl_Nen = new javax.swing.JLabel();

		setTitle("Đổi mật khẩu");

		pnl_Nen.setBackground(new java.awt.Color(255, 255, 255));
		pnl_Nen.setMinimumSize(new java.awt.Dimension(500, 400));
		pnl_Nen.setLayout(null);

		lbl_MKcu.setFont(new java.awt.Font("Arial", 0, 14));
		lbl_MKcu.setText("Mật khẩu cũ:");
		pnl_Nen.add(lbl_MKcu);
		lbl_MKcu.setBounds(10, 70, 100, 17);

		lbl_MKmoi.setFont(new java.awt.Font("Arial", 0, 14)); 
		lbl_MKmoi.setText("Mật khẩu mới:");
		pnl_Nen.add(lbl_MKmoi);
		lbl_MKmoi.setBounds(10, 142, 153, 17);

		lbl_NhapLai.setFont(new java.awt.Font("Arial", 0, 14)); 
		lbl_NhapLai.setText("Nhập lại mật khẩu:");
		pnl_Nen.add(lbl_NhapLai);
		lbl_NhapLai.setBounds(10, 220, 150, 17);

		lbl_DoiMK.setFont(new java.awt.Font("Arial", Font.BOLD, 26));
		lbl_DoiMK.setText("Đổi mật khẩu");
		pnl_Nen.add(lbl_DoiMK);
		lbl_DoiMK.setBounds(169, 11, 200, 30);

		btn_Doi.setFont(new java.awt.Font("Arial", 0, 14));
		btn_Doi.setText("Đổi");
		btn_Doi.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btn_DoiActionPerformed(evt);
			}
		});
		pnl_Nen.add(btn_Doi);
		btn_Doi.setBounds(170, 250, 70, 30);

		btn_Huy.setFont(new java.awt.Font("Arial", 0, 14));
		btn_Huy.setText("Hủy");
		btn_Huy.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btn_HuyActionPerformed(evt);
			}
		});
		pnl_Nen.add(btn_Huy);
		btn_Huy.setBounds(250, 250, 100, 30);

		txt_MKmoi.setFont(new java.awt.Font("Tahoma", 0, 14));
		pnl_Nen.add(txt_MKmoi);
		txt_MKmoi.setBounds(169, 131, 221, 30);

		txt_MKcu.setFont(new java.awt.Font("Tahoma", 0, 14));
		pnl_Nen.add(txt_MKcu);
		txt_MKcu.setBounds(169, 59, 221, 30);

		txt_NhapLai.setFont(new java.awt.Font("Tahoma", 0, 14));
		pnl_Nen.add(txt_NhapLai);
		txt_NhapLai.setBounds(169, 209, 221, 30);

		lbl_Nen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HinhAnh/1.jpg")));
		pnl_Nen.add(lbl_Nen);
		lbl_Nen.setBounds(0, 0, 500, 400);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(pnl_Nen, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(pnl_Nen, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				);

		pack();
		setLocationRelativeTo(null);
	}

	private void btn_DoiActionPerformed(java.awt.event.ActionEvent evt) {
		String MKcu = String.valueOf(txt_MKcu.getPassword());
		String MKmoi =  String.valueOf(txt_MKmoi.getPassword());
		String nhaplai = String.valueOf(txt_NhapLai.getPassword());
		if (DangNhapDao.KT_DoiMK(MKcu, MKmoi, nhaplai)) {
			UserDao.SuaMK(MKmoi, DangNhapDao.TenUser);
			this.dispose();
			
		} else {
		}
	}

	private void btn_HuyActionPerformed(java.awt.event.ActionEvent evt) {
		this.dispose();
		
	}

	public static void main(String args[]) {

		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Frm_DoiMatKhau().setVisible(true);
			}
		});
	}


	private javax.swing.JButton btn_Doi;
	private javax.swing.JButton btn_Huy;
	private javax.swing.JLabel lbl_DoiMK;
	private javax.swing.JLabel lbl_MKcu;
	private javax.swing.JLabel lbl_MKmoi;
	private javax.swing.JLabel lbl_Nen;
	private javax.swing.JLabel lbl_NhapLai;
	private javax.swing.JPanel pnl_Nen;
	private javax.swing.JPasswordField txt_MKcu;
	private javax.swing.JPasswordField txt_MKmoi;
	private javax.swing.JPasswordField txt_NhapLai;
}
