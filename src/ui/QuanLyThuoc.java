
package ui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import dao.DatabaseConnect;
import dao.LoaiDAO;
import dao.NhaCungCapDAO;
import dao.NuocDAO;
import dao.ThuocDAO;
import entity.LoaiThuoc;
import entity.NhaCungCap;
import entity.Nuoc;
import entity.Thuoc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class QuanLyThuoc extends JFrame implements ActionListener, MouseListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6921980593687713119L;
	private JTextField tfMaThuoc, tfTenThuoc, tfSoLuong, tfGiaNhap, tfGiaban, tfTimKiem, tfHSD ;
	private JComboBox<String> cboDonViTinh,cboNSX,cboNCC;
	private JButton btnLuu,btnThem,btnXoa, btnSua, btnTim, btnThoat;
	private JTable table;
	private DefaultTableModel dfModel;

	private ThuocDAO thuocDAO = new ThuocDAO();
	private NhaCungCapDAO nhaCCDAO;
	private NuocDAO nuocDAO;
	private JComboBox<String> cboLoai;
	private JLabel lblTenLoai;
	private LoaiDAO loaiDAO;



	public QuanLyThuoc() {
		UI();
		setTitle("Quản lý thuốc");
		setSize(1000,600);
		//		pack();
		setLocationRelativeTo(null);
		setVisible(false);

	}

	public void UI() {


		JPanel pnWest= new JPanel();
		pnWest.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.BLUE), "Nhập thông tin"));
		Box b = Box.createVerticalBox();
		Box b1 = Box.createHorizontalBox();
		Box b2 = Box.createHorizontalBox();
		Box b3 = Box.createHorizontalBox();
		Box b4 = Box.createHorizontalBox();
		Box b5 = Box.createHorizontalBox();
		Box b6 = Box.createHorizontalBox();
		Box b7 = Box.createHorizontalBox();
		Box b8 = Box.createHorizontalBox();
		Box b9 = Box.createHorizontalBox();
		Box b12 = Box.createHorizontalBox();
		Box b10 = Box.createVerticalBox();
		b10.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY)));
		Box b11 = Box.createHorizontalBox();

		b.add(b1);b.add(Box.createVerticalStrut(5));
		b.add(b2);b.add(Box.createVerticalStrut(5));
		b.add(b12);b.add(Box.createVerticalStrut(5));	
		b.add(b3);b.add(Box.createVerticalStrut(5));
		b.add(b4);b.add(Box.createVerticalStrut(5));
		b.add(b5);b.add(Box.createVerticalStrut(5));
		b.add(b6);b.add(Box.createVerticalStrut(5));
		b.add(b7);b.add(Box.createVerticalStrut(5));
		b.add(b8);b.add(Box.createVerticalStrut(5));
		b.add(b9);b.add(Box.createVerticalStrut(10));
		b.add(b10);b.add(Box.createVerticalStrut(10));
		b.add(b11);b.add(Box.createVerticalStrut(5));
		b11.add(btnLuu= new JButton("Lưu"));
		btnLuu.addActionListener(this);
		btnLuu.setVisible(false);

		JLabel lblMaThuoc, lblTenThuoc,lblSoLuong, lblDVT, lblGiaNhap,lblGiaBan, lblHSD, lblNCC,lblNuocSX, lbTimKiem;

		b1.add(lblMaThuoc= new JLabel("Mã thuốc:"));b1.add(tfMaThuoc= new JTextField());
		tfMaThuoc.setEditable(false);
		b2.add(lblTenThuoc= new JLabel("Tên thuốc:"));b2.add(tfTenThuoc= new JTextField());
		b12.add(lblTenLoai = new JLabel("Loại: "));b12.add(cboLoai = new JComboBox<String>());
		b3.add(lblDVT= new JLabel("Đơn vị tính:  "));b3.add(cboDonViTinh= new JComboBox<String>());
		String[] donviTinh = {"Viên", "Vỉ", "Chai", "Tuýp", "Hộp"};
		for (int i = 0; i < donviTinh.length; i++) {
			cboDonViTinh.addItem(donviTinh[i]);
		}
		b4.add(lblHSD =  new JLabel("Hạn sử dụng")); b4.add(tfHSD = new JTextField());
		b5.add(lblSoLuong = new JLabel("Số lượng:"));b5.add(tfSoLuong = new JTextField());
		b6.add(lblGiaBan= new JLabel("Giá bán"));b6.add(tfGiaban= new JTextField());
		b7.add(lblGiaNhap= new JLabel("Giá nhập:"));b7.add(tfGiaNhap = new JTextField());	
		b8.add(lblNCC= new JLabel("Nhà cung cấp: "));b8.add(cboNCC= new JComboBox<String>());
		b9.add(lblNuocSX= new JLabel("Nước sản xuất  "));b9.add(cboNSX= new JComboBox<String>());

		b10.add(lbTimKiem= new JLabel("Nhập tên thuốc cần tìm:"));b10.add(Box.createVerticalStrut(5));
		b10.add(tfTimKiem=new JTextField(10));b10.add(Box.createVerticalStrut(10));
		b10.add(btnTim= new JButton("Tìm kiếm"));b10.add(Box.createVerticalStrut(10));
		btnTim.addActionListener(this);

		lblTenLoai.setPreferredSize(lblDVT.getPreferredSize());
		lblMaThuoc.setPreferredSize(lblDVT.getPreferredSize());
		lblTenThuoc.setPreferredSize(lblDVT.getPreferredSize());
		lblSoLuong.setPreferredSize(lblDVT.getPreferredSize());
		lblGiaBan.setPreferredSize(lblDVT.getPreferredSize());
		lblHSD.setPreferredSize(lblDVT.getPreferredSize());

		lblGiaNhap.setPreferredSize(lblMaThuoc.getPreferredSize());
		lbTimKiem.setPreferredSize(lblDVT.getPreferredSize());
		pnWest.add(b);

		String[] header = "Mã thuốc;Tên thuốc;Đơn vị tính;Hạn sử dụng;Số lượng;Giá bán;Giá nhập;Nhà cung cấp;Nước sản xuất;Loại".split(";");
		dfModel = new DefaultTableModel(header, 0);
		table = new JTable(dfModel);
		table.setAutoCreateRowSorter(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		JScrollPane scroll = new JScrollPane();
		scroll.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.BLUE),"Danh mục thuốc"));
		scroll.setViewportView(table);
		table.setRowHeight(25);
		table.addMouseListener(this);
		//		add(scroll);

		JPanel pnSouth = new JPanel();
		pnSouth.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.BLUE),"Điều hướng"));
		pnSouth.add(btnThem= new JButton("Thêm thuốc"));btnThem.addActionListener(this);
		pnSouth.add(btnSua= new JButton("Sửa"));btnSua.addActionListener(this);
		pnSouth.add(btnXoa= new JButton("Xóa"));btnXoa.addActionListener(this);
		pnSouth.add(btnThoat= new JButton("Thoát"));btnThoat.setForeground(Color.RED);
		btnThoat.addActionListener(this);
		JPanel pnTitle = new JPanel();
		JLabel title = new JLabel("QUẢN LÝ THUỐC");
		title.setFont(new Font("Arial", Font.BOLD, 24));
		title.setForeground(Color.RED);
		pnTitle.add(title);
		add(pnTitle, BorderLayout.NORTH);
		add(pnSouth, BorderLayout.SOUTH);
		add(pnWest, BorderLayout.WEST);
		add(scroll, BorderLayout.CENTER);

		this.nhaCCDAO = new NhaCungCapDAO();
		this.nuocDAO = new NuocDAO();
		this.loaiDAO = new LoaiDAO();

		napLoaiVaoCb();
		napNCCVaoCb();
		napNuocVaoCb();
		loadData(table);

	}

	private void napThuocVaoTextfield(Thuoc thuoc) {
		tfMaThuoc.setText(thuoc.getMaThuoc());
		tfTenThuoc.setText(thuoc.getTenThuoc());
		cboDonViTinh.setSelectedItem(thuoc.getDonViTinh());
		tfHSD.setText(thuoc.getHsd().toString());
		tfSoLuong.setText(thuoc.getSoLuong()+"");
		tfGiaban.setText(thuoc.getGiaBan()+"");
		tfGiaNhap.setText(thuoc.getGiaNhap()+"");
		cboNCC.setSelectedItem(thuoc.getNhaCC().getMaNCC());
		cboNSX.setSelectedItem(thuoc.getNuocSX().getMaNuoc());
	}
	private void napLoaiVaoCb() {
//		try {
//			cboLoai.addItem("--");
//			List<LoaiThuoc> listLoai = loaiDAO.getDSLoai();
//			for (LoaiThuoc loai : listLoai) {
//				cboLoai.addItem(loai.getMaLoai());
//			}
//			cboLoai.addItem("Thêm mới");
//			napLoaiMoi();
//		} catch (Exception e) {
//			JOptionPane.showMessageDialog(this, e.getMessage());
//			return;
//		}
		try {
			cboLoai.addItem("--");
			LoaiDAO dao = new LoaiDAO();
			List<LoaiThuoc> listLoai = dao.getDSLoai();
			for (LoaiThuoc loai : listLoai) {
				cboLoai.addItem(loai.getMaLoai());
			}
			cboLoai.addItem("Thêm mới");
			napLoaiMoi();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
			return;
		}
	}
	public void napLoaiMoi() {
		cboLoai.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(cboLoai.getSelectedItem().toString().trim().equalsIgnoreCase("Thêm mới")) {
					DialogLoai dialogLoai = new DialogLoai(null, true);
					dialogLoai.setVisible(true);
					cboLoai.removeAllItems();
					napLoaiVaoCb();
				}
			}
		});
	
	}
	private void napNCCVaoCb() {
//		try {
//			cboNCC.addItem("--");
//			List<NhaCungCap> listNCC = nhaCCDAO.getDSNCC();
//			for (NhaCungCap nhaCungCap : listNCC) {
//				cboNCC.addItem(nhaCungCap.getMaNCC());
//			}
//			cboNCC.addItem("Thêm mới");
//			napNCCMoi();
//		} catch (Exception e) {
//			JOptionPane.showMessageDialog(this, e.getMessage());
//			return;
//		}
		try {
			cboNCC.addItem("--");
			NhaCungCapDAO dao = new NhaCungCapDAO();
			List<NhaCungCap> listNCC = dao.getDSNCC();
			for (NhaCungCap ncc : listNCC) {
				cboNCC.addItem(ncc.getMaNCC());
			}
			cboNCC.addItem("Thêm mới");
			napNCCMoi();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
			return;
		}
	}
	public void napNCCMoi() {
		cboNCC.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(cboNCC.getSelectedItem().toString().trim().equalsIgnoreCase("Thêm mới")) {
					DialogNhaCungCap dialogNCC = new DialogNhaCungCap(null, true);
					dialogNCC.setVisible(true);
					cboNCC.removeAllItems();
					napNCCVaoCb(); 
				}
			}
		});
	}
	private void napNuocVaoCb(){
//		try {
//			cboNSX.addItem("--");
//			List<Nuoc> listNuoc = nuocDAO.getDSNuoc();
//			for (Nuoc nuoc : listNuoc) {
//				cboNSX.addItem(nuoc.getMaNuoc());
//			}
//			cboNSX.addItem("Thêm mới");
//			napNuocMoi();
//		} catch (Exception e) {
//			JOptionPane.showMessageDialog(this, e.getMessage());
//			return;
//		}
		try {
			cboNSX.addItem("--");
			NuocDAO dao = new NuocDAO();
			List<Nuoc> listNuoc = dao.getDSNuoc();
			for (Nuoc nuoc : listNuoc) {
				cboNSX.addItem(nuoc.getMaNuoc());
			}
			cboNSX.addItem("Thêm mới");
			napNuocMoi();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
			return;
		}
	}
	public void napNuocMoi() {
		cboNSX.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(cboNSX.getSelectedItem().toString().trim().equalsIgnoreCase("Thêm mới")) {
					DialogNuoc2 nuoc2=new DialogNuoc2(null,true);
					nuoc2.setVisible(true);
					cboNSX.removeAllItems();
					napNuocVaoCb();
				}
			}
		});
	}
	public void loadData( JTable table) {
		dfModel = (DefaultTableModel)table.getModel();
		dfModel.getDataVector().removeAllElements();
		ThuocDAO dsThuoc = new ThuocDAO();
		for (Thuoc thuoc : dsThuoc.layDSThuoc()) {
			SimpleDateFormat sdf = new SimpleDateFormat("d/M/yyyy");
			String date  = sdf.format(thuoc.getHsd());
			String[] rowData= {thuoc.getMaThuoc(),thuoc.getTenThuoc(),thuoc.getDonViTinh(),date,thuoc.getSoLuong()+"",thuoc.getGiaBan()+"",thuoc.getGiaNhap()+"",thuoc.getNhaCC().getMaNCC(), thuoc.getNuocSX().getMaNuoc(),thuoc.getLoai().getMaLoai()};
			dfModel.addRow(rowData);
		}
		table.setModel(dfModel);
	}
	public void xoaTrang(){
		tfMaThuoc.setText("");
		tfTenThuoc.setText("");
		cboDonViTinh.setSelectedIndex(0);
		tfHSD.setText("");
		tfSoLuong.setText("");
		tfGiaban.setText("");
		tfGiaNhap.setText("");	
	}

	@Override
	public void mouseClicked(MouseEvent ev) {
		//		try{
		//			List<Thuoc> dsThuoc = thuocDAO.layDSThuoc();
		//			int row  = table.getSelectedRow();
		//			if(row >=0 && row < dsThuoc.size()) {
		//				Thuoc  thuoc = dsThuoc.get(row);
		//				napThuocVaoTextfield(thuoc);
		//			}
		//		}catch(Exception e){
		//			e.printStackTrace();
		//		}
		Object o = ev.getSource();
		if (o.equals(table)) {
			int row = table.getSelectedRow();
			if (row >=0) {
				tfMaThuoc.setText(table.getValueAt(row, 0)+"");
				tfTenThuoc.setText( table.getValueAt(row, 1)+"");
				cboDonViTinh.setSelectedItem(table.getValueAt(row, 2)+"");
				tfHSD.setText( table.getValueAt(row, 3)+"");
				tfSoLuong.setText(table.getValueAt(row, 4)+"");
				tfGiaban.setText(table.getValueAt(row, 5)+"");
				tfGiaNhap.setText(table.getValueAt(row, 6)+"");
				cboNCC.setSelectedItem(table.getValueAt(row, 7)+"");
				cboNSX.setSelectedItem(table.getValueAt(row, 8)+"");
				cboLoai.setSelectedItem(table.getValueAt(row, 9)+"");
			}
			table.setEnabled(true);
			btnThem.setEnabled(true);
			btnXoa.setEnabled(true);
			btnSua.setEnabled(true);
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {

	}

	@Override
	public void mouseExited(MouseEvent arg0) {

	}

	@Override
	public void mousePressed(MouseEvent arg0) {

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		try {
			if(o.equals(btnThem)){
				xoaTrang();
				tfMaThuoc.setEditable(true);
				btnLuu.setVisible(true);				
			}
			else if(o.equals(btnLuu)){
				if(kiemTraDuLieu() == true) {
					luuDatabase();
					tfMaThuoc.setEditable(false);
					btnLuu.setVisible(false);
				} else {
					JOptionPane.showMessageDialog(null, "Thêm thất bại!!!");
				}
			}
			else if(o.equals(btnSua)) {
				SimpleDateFormat df = new SimpleDateFormat("d/M/yyyy");
				Date hsd = df.parse(tfHSD.getText().trim());
				if(thuocDAO.capNhatThuoc(tfMaThuoc.getText().trim(), tfTenThuoc.getText().trim(), 
						cboDonViTinh.getSelectedItem().toString(), new java.sql.Date(hsd.getTime()),
						Integer.parseInt(tfSoLuong.getText()), Double.parseDouble(tfGiaban.getText()),
						Double.parseDouble(tfGiaNhap.getText()), cboNCC.getSelectedItem().toString(), 
						cboNSX.getSelectedItem().toString(),cboLoai.getSelectedItem().toString())==true) {
					JOptionPane.showMessageDialog(null, "Thành công");
					loadData(table);
				}

			}
			else if(o.equals(btnThoat)){
				System.exit(0);
			}
			else if(o.equals(btnTim)) {
				timKiemThuoc(tfTimKiem, table);
			}
			else if(o.equals(btnXoa)){

				List<Thuoc> dsThuoc = thuocDAO.layDSThuoc();
				int row  = table.getSelectedRow();
				if(row >=0 && row < dsThuoc.size()) {
					Thuoc  thuoc = dsThuoc.get(row);
					int thongBao = JOptionPane.showConfirmDialog(null, "Chắc chắn xóa không ?", "Chú ý", JOptionPane.YES_NO_OPTION);
					if(thongBao == JOptionPane.YES_OPTION) {
						thuocDAO.xoaThuoc(thuoc);
						loadData(table);
						xoaTrang();
					}
				}
			}


		} catch (Exception e2) {

		}

	}
	public void luuDatabase() {
		try {
			thuocDAO.themThuoc(taoThuoc());
			loadData(table);
			xoaTrang();
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
	}

	public void timKiemThuoc(JTextField tfTimkiem, JTable table) {
		if (tfTimkiem.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập thông tin thuốc cần tìm kiếm");

		}else{
			try {
				int flag = -1;
				for (Thuoc thuoc : thuocDAO.layDSThuoc()) {
					if(thuoc.getMaThuoc().equalsIgnoreCase(tfTimkiem.getText().trim())||thuoc.getTenThuoc().equalsIgnoreCase(tfTimkiem.getText().trim())) {
						int viTri = thuocDAO.layDSThuoc().indexOf(thuoc);
						table.setRowSelectionInterval(viTri, viTri);
						table.scrollRectToVisible(table.getCellRect(viTri, viTri, true));
						flag = 1;
						break;
					}
				}
				if(flag == -1) {
					showMessage(null, "Không tìm thấy thuốc!");
					tfTimkiem.requestFocus();
				}
			} catch (Exception e) {

			}
		}
	}

	private Thuoc taoThuoc() throws ParseException {
		String maThuoc=tfMaThuoc.getText();
		String tenThuoc = tfTenThuoc.getText();
		String donViTinh= cboDonViTinh.getSelectedItem().toString();
		SimpleDateFormat df = new SimpleDateFormat("d/M/yyyy");
		Date hsd = df.parse(tfHSD.getText().trim());
		int soLuong = Integer.parseInt(tfSoLuong.getText());
		double giaBan = Double.parseDouble(tfGiaban.getText());
		double giaNhap = Double.parseDouble(tfGiaNhap.getText());

		String maNCC = cboNCC.getSelectedItem().toString().trim();
		NhaCungCap ncc = new NhaCungCap(maNCC);
		
		String maNuoc = cboNSX.getSelectedItem().toString().trim();
		Nuoc nuoc = new Nuoc(maNuoc);
		
		String maLoai = cboLoai.getSelectedItem().toString().trim();
		LoaiThuoc loai = new LoaiThuoc(maLoai);
		
		Thuoc thuoc =  new Thuoc(maThuoc,tenThuoc,donViTinh,new java.sql.Date(hsd.getTime()),soLuong,giaBan,giaNhap);
		thuoc.setNhaCC(ncc);
		thuoc.setNuocSX(nuoc);
		thuoc.setLoai(loai);
		
		return thuoc;
	}

	public boolean kiemTraDuLieu() {
		String maThuoc = tfMaThuoc.getText().trim();
		String tenThuoc = tfTenThuoc.getText().trim();

		String loai = cboLoai.getSelectedItem().toString().trim();
		String ncc = cboNCC.getSelectedItem().toString().trim();
		String nsx = cboNSX.getSelectedItem().toString().trim();
		
		String hsd = tfHSD.getText().trim();
		
		String stringSo = tfSoLuong.getText().trim();
		String stringGia = tfGiaban.getText().trim();
		String stringGiaN = tfGiaNhap.getText().trim();
		
	
		if(!(stringSo.matches("[0-9]*"))||stringSo.equals("")) {
			showMessage(null, "Số lượng phải là ký tự số không âm và không được phép để trống!");
			return false;
		}
		if(!(stringGia.matches("[0-9]*"))||stringGia.equals("")) {
			showMessage(null, "Giá bán phải là ký tự số không âm và không được phép để trống");
			return false;
		}
		if(!(stringGiaN.matches("[0-9]*"))||stringGiaN.equals("")) {
			showMessage(null, "Giá nhập phải là ký tự số không âm và không được phép để trống!");
			return false;
		}
		if(ncc.equals("--")) {
			JOptionPane.showMessageDialog(null, "Mời chọn nhà cung cấp");
			return false;
		}
		if(nsx.equals("--")) {
			JOptionPane.showMessageDialog(null, "Mời chọn nước sản xuất");
			return false;
		}
		if(loai.equals("--")) {
			JOptionPane.showMessageDialog(null, "Mời chọn loại");
			return false;
		}
	
		if(maThuoc.equals("")||tenThuoc.equals("")) {
			JOptionPane.showMessageDialog(this, "Không được phép để trống");
		}
		ThuocDAO dsThuoc = new ThuocDAO();
		for (Thuoc thuoc : dsThuoc.layDSThuoc()) {
			if(maThuoc.equalsIgnoreCase(thuoc.getMaThuoc())) {
				JOptionPane.showMessageDialog(null, "Không được phép trùng mã thuốc!");
				return false;
			}
		}
		
		if(!hsd.matches("[0-9]{1,2}/[0-9]{1,2}/[0-9]{4}")) {
			showMessage(null, "Hạn sử dụng (d/M/yyyy)!");
			return false;
		}

		try {
			SimpleDateFormat df = new SimpleDateFormat("d/M/yyyy");
			df.parse(tfHSD.getText().trim());
		}catch (Exception e) {
			showMessage(null, "Hạn sử dụng không hợp lệ!");
			return false;
		}

		return true;
	}
	private void showMessage(JTextField tf, String mes) {
		JOptionPane.showMessageDialog(this, mes);
		tf.selectAll();
		tf.requestFocus();
	}
//	public static void main(String[] args) {
//		SwingUtilities.invokeLater(()-> new QuanLyThuoc());
//	}
}
