package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import dao.DangNhapDao;
import dao.NhanVienDao;
import dao.UserDao;
import entity.KhachHang;
import entity.NhanVien;
import entity.TaiKhoan;
import entity.Thongbao;

public class FrNhanVien extends JFrame implements ActionListener, MouseListener {
	private  JLabel lblhoTen,lblGioiTinh,lbldt,lbltieuDe,lbldiaChi,lblCmnd ,lbltimkiem, lblCa, lbTenTK,lbMK,lbQuyen;
	private JTextField txtHoTen,txtDt,txtCmnd,txttimKiem,txtTenTK,txtMK;
	private JButton btnthem,btnxoa,btnsua,btntimkiem,btnluu,btnthoat;
	private JButton btnThemTK;
	private JComboBox<String> cbxcaLam, cbxquyen;
	private JTextArea txtDiaChi;
	private JTable table;
	private DefaultTableModel tablemodel;
	private JRadioButton gioiTinh;
	private NhanVienDao dsnv = new NhanVienDao();
	private UserDao dstk = new UserDao();
	public FrNhanVien() {
		UI();
		setExtendedState(MAXIMIZED_BOTH);
		setVisible(false);
	}
	public void UI() {
		// giao diện thêm thông tin
		JPanel pNorth =  new JPanel();
		pNorth.add(lbltieuDe =  new JLabel("THÔNG TIN NHÂN VIÊN "));
		lbltieuDe.setFont(new Font("Arial", Font.BOLD, 26));
		lbltieuDe.setForeground(Color.red);
		add(pNorth,BorderLayout.NORTH);
		JPanel pwest = new JPanel();
		Box br,br1,br2,br3,br4, br5;
		br = Box.createVerticalBox();
		br.add(Box.createVerticalStrut(10));
		br.add(br1 =Box.createHorizontalBox());
		br.add(Box.createVerticalStrut(10));
		br1.add(lblhoTen= new JLabel("Họ và tên:"));
		br1.add(Box.createHorizontalStrut(10));
		br1.add(txtHoTen= new JTextField());		
		br.add(br2 =Box.createHorizontalBox());
		br.add(Box.createVerticalStrut(10));
		br2.add(lbldt= new JLabel("Số điện thoại:"));
		br2.add(Box.createHorizontalStrut(10));
		br2.add(txtDt = new JTextField());

		br.add(br3 =Box.createHorizontalBox());
		br.add(Box.createVerticalStrut(10));
		br3.add(lbldiaChi= new JLabel("Địa chỉ:"));
		br3.add(Box.createHorizontalStrut(10));
		br3.add(txtDiaChi= new JTextArea());
		txtDiaChi.setPreferredSize(new Dimension(10,70));
		br.add(br4 =Box.createHorizontalBox());
		br.add(Box.createVerticalStrut(10));

		br4.add(lblCmnd= new JLabel("Chứng minh nhân dân:"));
		br4.add(Box.createHorizontalStrut(10));
		br4.add(txtCmnd= new JTextField(15));
		txtCmnd.setEditable(false);
		br4.add(lblGioiTinh= new JLabel("Giới tính:"));
		br4.add(gioiTinh= new JRadioButton("Nam"));
		
		br.add(br5 =Box.createHorizontalBox());
		br.add(Box.createVerticalStrut(10));
		br5.add(lblCa=new JLabel("Ca Làm Việc:"));
		br5.add(Box.createHorizontalStrut(10));
		br5.add(cbxcaLam= new JComboBox<String>());
		cbxcaLam.addItem("Sáng:7h-11h30");
		cbxcaLam.addItem("Chiều:13h00-17h00");
		cbxcaLam.addItem("Tối:18h00-20h00");
		cbxcaLam.addItem("Ca 1+2");
		cbxcaLam.addItem("Ca 1+3");
		cbxcaLam.addItem("Ca 2+3");
		cbxcaLam.addItem("Cả ngày");
		lblhoTen.setPreferredSize(lblCmnd.getPreferredSize());
		lblCa.setPreferredSize(lblCmnd.getPreferredSize());
		lbldt.setPreferredSize(lblCmnd.getPreferredSize());
		lbldiaChi.setPreferredSize(lblCmnd.getPreferredSize());

		pwest.add(br);
		pwest.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.black),"Nhập thông tin nhân viên"));
		add(pwest,BorderLayout.WEST);
		//giao diện tác chức năng
		Box bbtn = Box.createVerticalBox();
		Box b1,b3,b4, b2, b5;
		bbtn.add(b1= Box.createHorizontalBox());
		bbtn.add(Box.createVerticalStrut(10));
		b1.add( btnthem= new JButton("Thêm nhân viên"));
		b1.add(Box.createHorizontalStrut(20));
		b1.add( btnxoa= new JButton("Xóa"));
		b1.add(Box.createHorizontalStrut(20));
		b1.add( btnsua= new JButton("Sửa"));
		b1.add(Box.createHorizontalStrut(20));
		b1.add( btnluu= new JButton("Lưu"));
		btnluu.setBackground(Color.ORANGE);
		b1.add(Box.createHorizontalStrut(20));
		bbtn.add(b3= Box.createHorizontalBox());
		bbtn.add(Box.createVerticalStrut(10));
		b3.add( lbltimkiem= new JLabel("Nhập thông tin Nhân viên:"));
		b3.add( txttimKiem= new JTextField(10));
		b3.add( btntimkiem= new JButton("Tìm kiếm"));
		bbtn.add(b4= Box.createHorizontalBox());
		bbtn.add(Box.createVerticalStrut(10));
		
		Box btk = Box.createVerticalBox();
		btk.add(b2=Box.createHorizontalBox());
		b2.add(lbTenTK= new JLabel("Tên tài khoản:"));
		b2.add(Box.createHorizontalStrut(10));
		b2.add(txtTenTK= new JTextField());	
		btk.add(Box.createVerticalStrut(10));
		
		btk.add(b4 =Box.createHorizontalBox());
		b4.add(Box.createVerticalStrut(10));
		b4.add(lbMK= new JLabel("Mật khẩu"));
		b4.add(Box.createHorizontalStrut(10));
		b4.add(txtMK = new JTextField());
		b4.add(Box.createHorizontalStrut(10));
		b4.add(lbQuyen= new JLabel("Quyền:"));
		b4.add(Box.createHorizontalStrut(10));
		b4.add(cbxquyen= new JComboBox<String>());
		cbxquyen.addItem("Admin");
		cbxquyen.addItem("User");
		btk.add(Box.createVerticalStrut(10));
		btk.add(b5 =Box.createVerticalBox());
		b5.add(btnThemTK= new JButton("Thêm Tài khoản"));
		lbMK.setPreferredSize(lbTenTK.getPreferredSize());
	

		bbtn.setBorder(new TitledBorder("Chọn tác vụ"));
		br.add(bbtn);
		br.add(btk);
		// giao diện bảng thông tin khách hàng
		String[]  header = "Mã NV;Họ và Tên;Giới tính;Số điện thoại;Địa chỉ;CMND; Ca làm việc; Tên TK".split(";");
		tablemodel = new DefaultTableModel(header, 0);
		JScrollPane scroll= new JScrollPane(table = new JTable(tablemodel),JScrollPane.VERTICAL_SCROLLBAR_ALWAYS
				,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		table.setRowHeight(20);	
		scroll.setPreferredSize(new Dimension(0,330));
		scroll.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.black),
				"Danh sách nhân viên"));
		add(scroll,BorderLayout.CENTER);
		updateTable(table); 
		btnthem.addActionListener(this);
		btnxoa.addActionListener(this);
		btnsua.addActionListener(this);
		btntimkiem.addActionListener(this);
		btnluu.addActionListener(this);
		btnThemTK.addActionListener(this);
		table.addMouseListener(this);
		txttimKiem.addActionListener(this);
		btnluu.setEnabled(false);btnsua.setEnabled(false);
		btnxoa.setEnabled(false);
		btnThemTK.setEnabled(false);
	}

	public void updateTable(JTable table) {
		tablemodel = (DefaultTableModel) table.getModel();
		tablemodel.getDataVector().removeAllElements();
		NhanVienDao dao = new NhanVienDao();
		UserDao udao = new UserDao();
		for(NhanVien s : dao.doctuBang()) {
			for(TaiKhoan tk:udao.docTK()) {
				if(tk.getMaNV()==s.getMaNV()) {
					String[] rowdata = {s.getMaNV()+"",s.getTenNV(),(s.isGioitinh()==true)?"Nam":"Nữ",s.getSdt() ,s.getDiachi(),s.getCmnd()+"", s.getCalamviec(), tk.getTenTk() };
					tablemodel.addRow(rowdata);
				}
				
			}
			
		}
		table.setModel(tablemodel);
	}
    

	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		if (o.equals(table)) {
			updatetextf();
			table.setEnabled(true);
			btnluu.setEnabled(false);
			btnxoa.setEnabled(true);
			btnsua.setEnabled(true);
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o= e.getSource();

		if (o.equals(btnluu)) {
			if (validata()) {
				int a=-1;
				for (NhanVien s : dsnv.doctuBang()) {
					if(s.getCmnd().equals(txtCmnd.getText())) {
						a=1;
						JOptionPane.showMessageDialog(null, "Trùng chứng minh nhân dân");
						break;
					}
				}
				if (a==-1) {
					
					dsnv.create(nv());
					Thongbao.thongbao("Mời bạn tạo tài khoản cho nhân viên", "Thông báo");
					txtHoTen.setEditable(false);
					txtCmnd.setEditable(false);
					txtDiaChi.setEditable(false);
					txtDt.setEditable(false);
					txttimKiem.setEditable(false);
					txtTenTK.setEditable(true);
					txtMK.setEditable(true);
					btnsua.setEnabled(false);
					btnxoa.setEnabled(false);
					btnthem.setEnabled(false);
					btntimkiem.setEnabled(false);
					btnThemTK.setEnabled(true);	
					btnluu.setEnabled(false);
				}
			}
		}else if(o.equals(btnThemTK)) {
			int kt= -1;
			for (NhanVien nv : dsnv.doctuBang()) {
				if(nv.getCmnd().equals(txtCmnd.getText())) {
					for (TaiKhoan tk : dstk.docTK()) {
						if(tk.getTenTk().equals(txtTenTK.getText())) {
							kt=1;
							Thongbao.thongbao("Tài khoản đã tồn tại, vui lòng nhập lại!", "Thông báo");
							break;
						}
							
					}
					
				}
				
			}
			if(kt==-1){
				
				dstk.Them(tk(dsnv.timNV(txtCmnd.getText()).getMaNV()));
				updateTable(table);
				cleartextf();
				txtTenTK.setEnabled(false);
				txtMK.setEnabled(false);
			}
			
		}
		else if (o.equals(btnsua)) {
			if (dsnv.update(nv())){
				updateTable(table);
				cleartextf();
				btnsua.setEnabled(false);
				btnxoa.setEnabled(false);
				
			}
		}else if (o.equals(btnxoa)) {
			int hoinhac = JOptionPane.showConfirmDialog(null, " Bạn Chắc chắn muốn xóa", "XÓA", JOptionPane.YES_NO_OPTION);
			if (hoinhac== JOptionPane.YES_OPTION) {	
				int row = table.getSelectedRow();
				String cmnd = (String)table.getValueAt(row,5);
				String tentk = (String)table.getValueAt(row,7);
				String tendn=DangNhapDao.TenUser;
				
				if (row >=0) {
					if(tentk.equals(tendn)) {
						Thongbao.thongbao("Tài khoản đang đang nhập, không thể xóa tài khoản này!", "Thông báo");
					}
					else {
						NhanVien nv = dsnv.timNV(cmnd);
						dstk.delete(nv.getMaNV());
						if (dsnv.delete(cmnd)) {
							tablemodel.removeRow(row);
							updateTable(table);
							cleartextf();
							btnxoa.setEnabled(false);
							btnsua.setEnabled(false);
							Thongbao.thongbao("Đã xóa thành công!", "Thông báo");
						}
					}

				}	
			}
		}else if (o.equals(btnthem)) {
			cleartextf();
			txtMK.setEditable(false);
			txtTenTK.setEditable(false);
			txttimKiem.setEnabled(false);
			btntimkiem.setEnabled(false);
			txtCmnd.setEditable(true);
			btnluu.setEnabled(true);
			btnsua.setEnabled(false);
			btnxoa.setEnabled(false);

		}else if (o.equals(btntimkiem)|| o.equals(txttimKiem)) {
			if (txttimKiem.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Bạn Chưa nhập giá trị");

			}else {

				int a=-1;
				for(NhanVien s:dsnv.doctuBang()) {
					if (s.getCmnd().equals(txttimKiem.getText())||s.getSdt().equals(txttimKiem.getText())
							||s.getTenNV().equals(txttimKiem.getText())) {
						int vitri = dsnv.doctuBang().indexOf(s);
						table.setRowSelectionInterval(vitri, vitri);
						table.scrollRectToVisible(table.getCellRect(vitri, vitri, true));
						a=1;
						updatetextf();
						break;
					} 
				}
				if (a!=1) {

					JOptionPane.showMessageDialog(null, " Không Tìm thấy nhân viên");
					txttimKiem.requestFocus();
					txttimKiem.selectAll();
				}else {
					Thongbao.thongbao("Tìm thấy nhân viên!", "Thông báo tìm kiếm");
				}
			}

		}

	}	
	private void updatetextf() {
		int row = table.getSelectedRow();
		if (row>=0) {
			txtHoTen.setText((String) table.getValueAt(row, 1));
			txtDt.setText((String) table.getValueAt(row, 3));
			txtDiaChi.setText((String) table.getValueAt(row, 4));
			txtCmnd.setText((String) table.getValueAt(row, 5));
			if (table.getValueAt(row, 2).equals("Nam")) {
				gioiTinh.setSelected(true);
			}else gioiTinh.setSelected(false);
			txtCmnd.setEditable(false);
			cbxcaLam.setSelectedItem((String)table.getValueAt(row, 6));
			txtTenTK.setText((String) table.getValueAt(row, 7));
		}
		UserDao udao = new UserDao();
		for(TaiKhoan tk:udao.docTK()) {
			if(tk.getTenTk().equals(txtTenTK.getText())) {
				txtMK.setText(tk.getMatKhau());
				cbxquyen.setSelectedItem(tk.getQuyen());
			}
		}
		table.setEnabled(true);
		btnluu.setEnabled(false);
		btnxoa.setEnabled(true);
		btnsua.setEnabled(true);
		txtHoTen.setEditable(true);
		txtDiaChi.setEditable(true);
		txtDt.setEditable(true);
		txtTenTK.setEditable(false);
		txtMK.setEditable(false);
		btnsua.setEnabled(true);
		btnxoa.setEnabled(true);
		btnthem.setEnabled(true);
		btntimkiem.setEnabled(true);
		btnThemTK.setEnabled(false);	
		btnluu.setEnabled(false);

	}
	private void cleartextf() {
		txtHoTen.setText("");
		txtCmnd.setText("");
		txtDiaChi.setText("");
		txtDt.setText("");
		txttimKiem.setText("");
		txtHoTen.setText("");
		gioiTinh.setSelected(false);
		txtHoTen.requestFocus();
		txtTenTK.setText("");
		txtMK.setText("");
		
	}
	public boolean validata() {
		String hoten = txtHoTen.getText();
		String diachi = txtDiaChi.getText();
		String sdt = txtDt.getText();
		String cmnd =txtCmnd.getText();
		if (txtHoTen.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Bạn Chưa nhập họ tên");
			return false;
		}
		if (txtDt.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Bạn Chưa nhập Số điện thoại");
			return false;
		}
		if (txtCmnd.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "bạn Chưa nhập chứng minh nhân dân");
			return false;
		}
		if(!(sdt.matches("[0-9]*"))){
			JOptionPane.showMessageDialog(null, "Bạn hãy nhập SDT là số"); 
			txtDt.requestFocus();
			txtDt.selectAll();
			return false; 
		}
		if (sdt.length()!=10) {
			JOptionPane.showMessageDialog(null, "Bạn hãy nhập SDT có 10 số"); 
			return false; 
		}
		if (txtCmnd.getText().length() !=12 && txtCmnd.getText().length()!=9) {
			JOptionPane.showMessageDialog(null, " Bạn hãy nhập CMND  từ 9 Hoặc 12 chữ số");
			txtCmnd.requestFocus();
			txtCmnd.selectAll();
			return false;
		}
		if(!cmnd.matches("[0-9]*")){
			JOptionPane.showMessageDialog(null, "Bạn hãy nhập CMND là số"); 
			return false; 
		}
		if(!txttimKiem.getText().matches("[0-9]*")){
			JOptionPane.showMessageDialog(null, "Bạn hãy nhập CMND là số"); 
			return false; 
		}

		if(!hoten.matches("[a-zA-ZàáãạảăắằẳẵặâấầẩẫậèéẹẻẽêềếểễệđìíĩỉịòóõọỏôốồổỗộơớờởỡợùúũụủưứừửữựỳỵỷỹýÀÁÃẠẢĂẮẰẲẴẶÂẤẦẨẪẬÈÉẸẺẼÊỀẾỂỄỆĐÌÍĨỈỊÒÓÕỌỎÔỐỒỔỖỘƠỚỜỞỠỢÙÚŨỤỦƯỨỪỬỮỰỲỴỶỸÝ ]*")){
			JOptionPane.showMessageDialog(null, " tên phải nhập chữ"); 
			return false; 
		}


		return true;
	}
	public NhanVien nv() {

		String hoten = txtHoTen.getText();
		boolean gioitinh = (gioiTinh.isSelected() ? true:false);
		String diachi = txtDiaChi.getText();
		String sdt = txtDt.getText();
		String  cmnd =txtCmnd.getText();
		String calamviec = cbxcaLam.getSelectedItem().toString();
		return new NhanVien(hoten, cmnd, sdt, diachi, calamviec, gioitinh);

	}
	public TaiKhoan tk(int manv) {
		String tentk = txtTenTK.getText();
		String mk= txtMK.getText();
		String role = cbxquyen.getSelectedItem().toString();
		return new TaiKhoan(tentk, mk, manv, role);
		
	}
	public static void main(String[] args) {
		new FrNhanVien().setVisible(true);
	}
}
