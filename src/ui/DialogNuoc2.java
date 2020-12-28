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

import dao.NuocDAO;
import entity.Nuoc;

public class DialogNuoc2 extends JDialog implements ActionListener {
	private JLabel lblMaNuoc, lblTenNuoc;
	private JTextField tfNuoc, tfTenNuoc;
	private JButton btnLuu;
	private NuocDAO nuocDAO;
	boolean rs=false;
	public DialogNuoc2(Frame frame,boolean b) {
		super(frame, b);
		UI();
		setTitle("Thêm nước sản xuất mới");
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
		

		b1.add(lblMaNuoc= new JLabel("Mã nước:"));b1.add(tfNuoc= new JTextField(10));
		b2.add(lblTenNuoc= new JLabel("Tên nước:"));b2.add(tfTenNuoc= new JTextField(10));
		b3.add(btnLuu = new JButton("Lưu")); btnLuu.addActionListener(this);
		
		lblMaNuoc.setPreferredSize(lblTenNuoc.getPreferredSize());
		panel.add(b);
		
		Container con = getContentPane();
		con.add(panel);
		this.nuocDAO = new NuocDAO();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btnLuu)) {
			if(kiemTraDuLieu()==true) {
				String maNuoc = tfNuoc.getText().trim();
				String tenNuoc = tfTenNuoc.getText().trim();
				
				Nuoc nuoc = new Nuoc(maNuoc,tenNuoc);
				if(nuocDAO.themNCC(nuoc))
					{
						JOptionPane.showMessageDialog(this, "Thêm thành công!");
						rs=true;
						
//						dialog.setVisible(false);
						this.dispose();
					}
					else {
						JOptionPane.showMessageDialog(this, "Thất bại! Vui lòng thao tác lại");
					}
			}
			
		}
		
	}
	public boolean kiemTraDuLieu() {
		String maNuoc = tfNuoc.getText().trim();
		String tenNuoc = tfTenNuoc.getText().trim();
		if(maNuoc.equals("")) {
			JOptionPane.showMessageDialog(tfNuoc, "Mã không được phép để trống");
			return false;
		}
		if(tenNuoc.equals("")) {
			JOptionPane.showMessageDialog(tfTenNuoc, "Tên không được phép để trống");
			return false;
		}
		NuocDAO dsNuoc = new NuocDAO();
		for(Nuoc nuoc: dsNuoc.getDSNuoc()) {
			if(maNuoc.equalsIgnoreCase(nuoc.getMaNuoc())) {
				JOptionPane.showMessageDialog(null, "Mã không được phép trùng");
				return false;
			}
		}
		return true;
	}
}
