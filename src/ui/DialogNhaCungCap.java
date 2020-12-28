package ui;

import java.awt.Container;
import java.awt.Frame;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.NhaCungCapDAO;
import entity.NhaCungCap;

public class DialogNhaCungCap extends JDialog implements ActionListener {
	private JLabel lblMaNCC, lblTenNCC;
	private JTextField tfNCC, tfTenNCC;
	private JButton btnLuu;
	private NhaCungCapDAO nhaCCDAO;
	public DialogNhaCungCap(Frame frame, boolean b) {
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


		b1.add(lblMaNCC= new JLabel("Mã nhà cung cấp:"));b1.add(tfNCC= new JTextField(15));
		b2.add(lblTenNCC= new JLabel("Tên nhà cung cấp:"));b2.add(tfTenNCC= new JTextField(15));
		b3.add(btnLuu = new JButton("Lưu")); btnLuu.addActionListener(this);

		lblMaNCC.setPreferredSize(lblTenNCC.getPreferredSize());
		panel.add(b);
		Container con = getContentPane();
		con.add(panel);
		this.nhaCCDAO = new NhaCungCapDAO();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btnLuu)) {
			if(kiemTraDuLieu()==true) {
				String maNCC = tfNCC.getText().trim();
				String tenNCC = tfTenNCC.getText().trim();

				NhaCungCap ncc = new NhaCungCap(maNCC,tenNCC);
				if(nhaCCDAO.themNCC(ncc))
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
		String maNcc = tfNCC.getText().trim();
		String tenNCC = tfTenNCC.getText().trim();
		if(maNcc.equals("")) {
			JOptionPane.showMessageDialog(tfNCC, "Mã không được phép để trống");
			return false;
		}
		if(tenNCC.equals("")) {
			JOptionPane.showMessageDialog(tfTenNCC, "Tên không được phép để trống");
			return false;
		}
		NhaCungCapDAO dsNCC = new NhaCungCapDAO();
		try {
			for(NhaCungCap ncc: dsNCC.getDSNCC()) {
				if(maNcc.equalsIgnoreCase(ncc.getMaNCC())) {
					JOptionPane.showMessageDialog(null, "Mã không được phép trùng");
					return false;
				}
			}
		} catch (HeadlessException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
}
