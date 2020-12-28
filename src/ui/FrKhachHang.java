package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
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


import dao.KhachHangDao;
import entity.KhachHang;
import entity.Thongbao;



public class FrKhachHang extends JFrame implements ActionListener, MouseListener {
	private  JLabel lblhoTen,lblGioiTinh,lbldt,lbltieuDe,lbldiaChi,lblCmnd ,lbltimkiem;
	private JTextField txtHoTen,txtDt,txtCmnd,txttimKiem;
	private JButton btnthem,btnxoa,btnsua,btntimkiem,btnluu;
	private JTextArea txtDiaChi;
	private JTable table;
	private DefaultTableModel tablemodel;
	private JRadioButton gioiTinh;
	private KhachHangDao dsKH = new KhachHangDao();

	public FrKhachHang() {
		// TODO Auto-generated constructor stub

		UI();
		setSize(1000, 600);
		setVisible(false);
	}
	public void UI() {
		// giao diện thêm thông tin
		JPanel pNorth =  new JPanel();
		pNorth.add(lbltieuDe =  new JLabel("THÔNG TIN KHÁCH HÀNG "));
		lbltieuDe.setFont(new Font("Arial", Font.BOLD, 26));
		lbltieuDe.setForeground(Color.red);
		add(pNorth,BorderLayout.NORTH);
		JPanel pwest = new JPanel();
		Box br,bl,br1,br2,br3,br4;
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
		lblhoTen.setPreferredSize(lblCmnd.getPreferredSize());
		lbldt.setPreferredSize(lblCmnd.getPreferredSize());
		lbldiaChi.setPreferredSize(lblCmnd.getPreferredSize());

		pwest.add(br);
		pwest.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.black),"Nhập thông tin khách Hàng"));
		add(pwest,BorderLayout.WEST);
		//giao diện tác chức năng
		Box bbtn = Box.createVerticalBox();
		Box b1,b2,b3,b4,b5;
		bbtn.add(b1= Box.createHorizontalBox());
		bbtn.add(Box.createVerticalStrut(10));
		b1.add( btnthem= new JButton("Tạo KH mới"));
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
		b3.add( lbltimkiem= new JLabel("Nhập thông tin KH cần tìm:"));
		b3.add( txttimKiem= new JTextField(10));
		b3.add( btntimkiem= new JButton("Tìm kiếm"));
		bbtn.add(b4= Box.createHorizontalBox());
		bbtn.add(Box.createVerticalStrut(10));



		bbtn.setBorder(new TitledBorder("Chọn tác vụ"));
		br.add(bbtn);
		// giao diện bảng thông tin khách hàng
		String[]  header = "Mã KH;Họ và Tên;Giới tính;Số điện thoại;Địa chỉ;CMND".split(";");
		tablemodel = new DefaultTableModel(header, 0);
		JScrollPane scroll= new JScrollPane(table = new JTable(tablemodel),JScrollPane.VERTICAL_SCROLLBAR_ALWAYS
				,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		table.setRowHeight(20);	
		scroll.setPreferredSize(new Dimension(0,330));
		scroll.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.black),
				"Danh sách Khách Hàng"));
		add(scroll,BorderLayout.CENTER);
		updateTable(table); 
		btnthem.addActionListener(this);
		btnxoa.addActionListener(this);
		btnsua.addActionListener(this);
		btntimkiem.addActionListener(this);
		btnluu.addActionListener(this);
		table.addMouseListener(this);
		txttimKiem.addActionListener(this);
		btnluu.setEnabled(false);btnsua.setEnabled(false);
		btnxoa.setEnabled(false);
	}

	public void updateTable(JTable table) {
		tablemodel = (DefaultTableModel) table.getModel();
		tablemodel.getDataVector().removeAllElements();
		KhachHangDao dao = new KhachHangDao();
		for(KhachHang s : dao.doctuBang()) {
			String[] rowdata = {s.getMaKH()+"",s.getHoTen(),(s.isGioiTinh()==true)?"Nam":"Nữ",s.getSoDT() 
					,s.getDiaChi(),s.getCmnd()+"" };
			tablemodel.addRow(rowdata);
		}
		table.setModel(tablemodel);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		if (o.equals(table)) {
			int row = table.getSelectedRow();
			if (row >=0) {
				txtHoTen.setText( table.getValueAt(row, 1)+"");
				txtDt.setText( table.getValueAt(row, 3)+"");
				txtDiaChi.setText( table.getValueAt(row, 4)+"");
				txtCmnd.setText( table.getValueAt(row, 5)+"");
				if (table.getValueAt(row, 2).equals("Nam")) {
					gioiTinh.setSelected(true);
				}else gioiTinh.setSelected(false);
				txtCmnd.setEditable(false);
			}
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
				for (KhachHang s : dsKH.doctuBang()) {
					if(s.getCmnd().equals(txtCmnd.getText())) {
						a=1;
						JOptionPane.showMessageDialog(null, "Trùng chứng minh nhân dân");
						break;
					}
				}
				if (a==-1) {
					dsKH.ThemKh(kh());
					updateTable(table);
					cleartextf();
					/* xuatThe(kh().getHoTen()); */
					btnluu.setEnabled(false);
				}
			}
		}else if (o.equals(btnsua)) {
			if (dsKH.capNhapKh(kh())){
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
				if (row >=0) {
					int a=1;
					if (a==1) {
						if (dsKH.XoaKH(cmnd)) {
							tablemodel.removeRow(row);
							updateTable(table);
							cleartextf();
							btnxoa.setEnabled(false);
							btnsua.setEnabled(false);
						}
					}

				}	
			}	
		}else if (o.equals(btnthem)) {
			cleartextf();
			txtCmnd.setEditable(true);
			btnluu.setEnabled(true);
			btnsua.setEnabled(false);
			btnxoa.setEnabled(false);

		}else if (o.equals(btntimkiem)|| o.equals(txttimKiem)) {
			if (txttimKiem.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Bạn Chưa nhập giá trị");

			}else {

				int a=-1;
				for(KhachHang s : dsKH.doctuBang()) {
					if (s.getCmnd().equals(txttimKiem.getText())||s.getSoDT().equals(txttimKiem.getText())||
							s.getHoTen().equals(txttimKiem.getText())) {
						int vitri = dsKH.doctuBang().indexOf(s);
						table.setRowSelectionInterval(vitri, vitri);
						table.scrollRectToVisible(table.getCellRect(vitri, vitri, true));
						a=1;
						updatetextf();
						break;
					} 
				}
				if (a==-1) {

					JOptionPane.showMessageDialog(null, " Không Tìm thấy Khách Hàng");
					txttimKiem.requestFocus();
					txttimKiem.selectAll();
				}else {
					Thongbao.thongbao("Tìm thấy khách hàng!", "Thông báo tìm kiếm");
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
		}
		table.setEnabled(true);
		btnluu.setEnabled(false);
		btnxoa.setEnabled(true);
		btnsua.setEnabled(true);


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
	public KhachHang kh() {

		String hoten = txtHoTen.getText();
		boolean gioitinh = (gioiTinh.isSelected() ? true:false);
		String diachi = txtDiaChi.getText();
		String sdt = txtDt.getText();
		String  cmnd =txtCmnd.getText();
		return new KhachHang(hoten, gioitinh, sdt, diachi, cmnd);

	}
}
