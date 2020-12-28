package ui;

import java.awt.Container;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.LoaiDAO;
import dao.NhaCungCapDAO;
import entity.LoaiThuoc;

public class DialogLoai extends JDialog implements ActionListener {
	private JLabel lblMaLoai, lblTenLoai;
	private JTextField tfLoai, tfTenLoai;
	private JButton btnLuu;
	private LoaiDAO loaiDAO;
	public DialogLoai(Frame frame, boolean b) {
		super(frame,b);
		UI();
		setTitle("Thêm nhà cung cấp mới");
		setSize(300,150);
		setResizable(false);
		setVisible(false);
		setLocationRelativeTo(null);
	}

	public void UI() {
		JPanel panel = new JPanel();

		Box b = Box.createVerticalBox();
		Box b1 = Box.createHorizontalBox();
		Box b2 = Box.createHorizontalBox();
		Box b3 = Box.createHorizontalBox();

		b.add(b1);b.add(Box.createVerticalStrut(5));
		b.add(b2);b.add(Box.createVerticalStrut(20));
		b.add(b3);b.add(Box.createVerticalStrut(5));


		b1.add(lblMaLoai= new JLabel("Mã loại:"));b1.add(tfLoai= new JTextField(15));
		b2.add(lblTenLoai= new JLabel("Tên loại:"));b2.add(tfTenLoai= new JTextField(15));
		b3.add(btnLuu = new JButton("Lưu")); btnLuu.addActionListener(this);

		lblMaLoai.setPreferredSize(lblTenLoai.getPreferredSize());
		panel.add(b);
		Container con = getContentPane();
		con.add(panel);
		this.loaiDAO = new LoaiDAO();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btnLuu)) {
			if(kiemTraDuLieu()==true) {
				String maLoai = tfLoai.getText().trim();
				String tenLoai = tfTenLoai.getText().trim();

				LoaiThuoc loai = new LoaiThuoc(maLoai,tenLoai);
				if(loaiDAO.themLoai(loai))
				{
					JOptionPane.showMessageDialog(this, "Thêm thành công!");
					//					dialog.setVisible(false);
					this.dispose();
				}
				else {
					JOptionPane.showMessageDialog(this, "Thất bại! Vui lòng thao tác lại");
				}
			}
		}

	}
	public boolean kiemTraDuLieu() {
		String maLoai = tfLoai.getText().trim();
		String tenLoai = tfTenLoai.getText().trim();
		if(maLoai.equals("")) {
			JOptionPane.showMessageDialog(tfLoai, "Mã không được phép để trống");
			return false;
		}
		if(tenLoai.equals("")) {
			JOptionPane.showMessageDialog(tfTenLoai, "Tên không được phép để trống");
			return false;
		}
		LoaiDAO dsLoai = new LoaiDAO();
		for(LoaiThuoc loai: dsLoai.getDSLoai()) {
			if(maLoai.equalsIgnoreCase(loai.getMaLoai())) {
				JOptionPane.showMessageDialog(null, "Mã không được phép trùng");
				return false;
			}
		}
		return true;
	}
}
