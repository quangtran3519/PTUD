package ui;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;
import javax.swing.text.Utilities;

import dao.DangNhapDao;
import dao.DatabaseConnect;
import dao.HoaDonDao;
import dao.KhachHangDao;
import dao.NhanVienDao;
import dao.ThuocDAO;

import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import entity.Thuoc;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;


public class Frm_TaoHoaDon extends JFrame implements ActionListener,MouseListener {
	private JLabel lblTieuDe;
	private JLabel lblBanTheoDon;
	private JComboBox<String> cboHoaDon;
	private JLabel lbltimKiem;
	private JComboBox<String> cboTimKH;
	private JTextField txttimKiem;
	private JLabel lblmaBN;
	private JButton btnTim;
	private JTextField txtMaBN;
	private JLabel lblTenBN;
	private JTextComponent txttenBN;
	private JLabel lblCmnd;
	private JTextField txtcmnd;
	private JLabel lblTongTien;
	public JTextField txttongtien;
	private JLabel lblghichu;
	private JTextArea txtGhiChu;
	private JButton btnLuu;
	private JButton btnLuuin;
	private JTextField txtthongbao;
	private JLabel lbltimThuoc;
	private JTextField txtTimThuoc;
	private JButton btnTimTHuoc;
	private JButton btnRefesh;
	private DefaultTableModel dfModel;
	private JTable table;
	private DefaultTableModel tableModelPM;
	private Component tablePhieuThue;
	private JTable tableThuocDaChon;
	private JLabel lblsoLuongThuoc;
	private JTextField txtsoluongthuoc;
	private JButton btnluuTHuoc;
	private JButton btnsuaThuoc;
	private JButton btnxoaThuosc;
	private HoaDonDao hoaDonDao = new HoaDonDao();
	private ThuocDAO thuocDao = new ThuocDAO();
	private KhachHangDao khachHangDao = new KhachHangDao();
	private HoaDon hoaDon;
	private ChiTietHoaDon ctHD;
	private DangNhapDao dangNhapDao = new DangNhapDao();

	public  Frm_TaoHoaDon() {
		UI();
		setBackground(Color.white);
		setSize(1200, 800);
		setTitle("Tạo phiếu thuê");
		setVisible(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	
	
	private void UI() {
		JPanel pnorth = new JPanel();
		pnorth.add(lblTieuDe = new JLabel("TẠO HÓA ĐƠN"));
		lblTieuDe.setFont(new Font("arial", Font.BOLD, 26));
		lblTieuDe.setForeground(Color.red);
		add(pnorth, BorderLayout.NORTH);
		Box b, bl, br, b1, b2, b3, b4, b5, b6, b7, b8, b9,bdon,bmodify;
		
		b = Box.createHorizontalBox();
		b.add(bl = Box.createVerticalBox());
		bl.add(bdon = Box.createHorizontalBox());
		bl.add(Box.createVerticalStrut(10));
		bdon.add(lblBanTheoDon = new JLabel("Loại hóa đơn"));
		bdon.add(cboHoaDon= new JComboBox<String>());
		cboHoaDon.addItem("Bán không theo đơn");
		cboHoaDon.addItem("Bán  theo đơn");
		
		bl.add(b1 = Box.createHorizontalBox());
		bl.add(Box.createVerticalStrut(10));
		b1.add(lbltimKiem = new JLabel("Nhập mã bệnh nhân hoặc chứng minh nhân dân:"));
				
		bl.add(b2 = Box.createHorizontalBox());
		b2.add(txttimKiem = new JTextField());
		bl.add(Box.createVerticalStrut(10));
		b2.add(btnTim = new JButton("Tìm kiếm"));
		bl.add(b3 = Box.createHorizontalBox());
		b3.add(lblmaBN = new JLabel("Mã BN:"));
		bl.add(Box.createVerticalStrut(10));
		b3.add(txtMaBN = new JTextField());
		txtMaBN.setEditable(false);
		bl.add(b4 = Box.createHorizontalBox());
		b4.add(lblTenBN = new JLabel("Tên bệnh nhân:"));
		bl.add(Box.createVerticalStrut(10));
		b4.add(txttenBN = new JTextField());
		txttenBN.setEditable(false);
		bl.add(b5 = Box.createHorizontalBox());
		b5.add(lblCmnd = new JLabel("CMND:"));
		bl.add(Box.createVerticalStrut(10));
		b5.add(txtcmnd = new JTextField(20));
		txtcmnd.setEditable(false);
		bl.add(b6 = Box.createHorizontalBox());
		b6.add(lblTongTien = new JLabel("Tổng tiền:"));
		bl.add(Box.createVerticalStrut(10));
		b6.add(txttongtien= new JTextField(20));
		
		bl.add(b7= Box.createHorizontalBox());
		b7.add(lblghichu = new JLabel("Ghi chú"));
		txtGhiChu = new JTextArea();
		txtGhiChu.setWrapStyleWord(true);
		JScrollPane areaScrollPane = new JScrollPane(txtGhiChu);
		areaScrollPane.setVerticalScrollBarPolicy(
		                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		areaScrollPane.setPreferredSize(new Dimension(150, 50));
		b7.add(areaScrollPane);
		bl.add(b8 = Box.createHorizontalBox());
		bl.add(Box.createVerticalStrut(10));
		b8.add(btnLuu = new JButton("Lưu"));
		b8.add(Box.createHorizontalStrut(5));
		b8.add(btnLuuin = new JButton("Lưu và In"));
		
		
		lbltimKiem.setPreferredSize(lblTenBN.getPreferredSize());
		lblmaBN.setPreferredSize(lblTenBN.getPreferredSize());
		lblCmnd.setPreferredSize(lblTenBN.getPreferredSize());
		lblTongTien.setPreferredSize(lblTenBN.getPreferredSize());
		lblghichu.setPreferredSize(lblTenBN.getPreferredSize());
		lblBanTheoDon.setPreferredSize(lblTenBN.getPreferredSize());
		bl.add(txtthongbao = new JTextField("Thông báo"));
		txtthongbao.setForeground(Color.red);
		txtthongbao.setBorder(null);
		txtthongbao.setEditable(false);
		txtthongbao.setFont(new Font("arial, time new roman", Font.BOLD, 15));
	
		 JPanel pwest = new JPanel();
		pwest.add(bl);	
		
		pwest.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.BLUE), "Thông tin hóa đơn"));
		add(pwest, BorderLayout.WEST);
		br = Box.createVerticalBox();
		br.add(b9 = Box.createHorizontalBox());
		b9.add(lbltimThuoc = new JLabel("Chọn:"));
		b9.add(Box.createHorizontalStrut(5));
//		b9.add(cbotimCD = new JComboBox<String>());
//		cbotimCD.addItem("Mã CD ");
//		cbotimCD.addItem("Tên CD");
		b9.add(txtTimThuoc = new JTextField());
		b9.add(Box.createHorizontalStrut(5));
		b9.add(btnTimTHuoc= new JButton("Tìm Thuốc"));
		b9.add(Box.createHorizontalStrut(5));
	

		String[] header = "Mã thuốc,Tên Thuốc,Số lượng ,Nhà sản xuất,Đơn vị,Giá bán".split(",");
		dfModel = new DefaultTableModel(header, 0);
		table = new JTable(dfModel);
		table.setAutoCreateRowSorter(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		JScrollPane scroll = new JScrollPane();
		scroll.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.BLUE), "Danh sách thuốc"));
		scroll.setViewportView(table);
		br.add(scroll);
		String[] headerCTPT = "Mã thuốc,Tên Thuốc,Số lượng ,Nhà sản xuất,Đơn vị,Giá bán".split(",");
		tableModelPM = new DefaultTableModel(headerCTPT, 0);
		tableThuocDaChon = new JTable(tableModelPM);
		JScrollPane srcollCt = new JScrollPane(tableThuocDaChon);
		srcollCt.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.BLUE), "Danh sách Thuốc đã chọn"));
		
		br.add(bmodify = Box.createHorizontalBox());
		br.add(Box.createHorizontalStrut(10));
		bmodify.add(lblsoLuongThuoc = new JLabel(" Nhập số lượng thuốc "));
		bmodify.add(txtsoluongthuoc = new JTextField(10));
		bmodify.add(Box.createHorizontalStrut(5));
		bmodify.add(btnluuTHuoc= new JButton("Chọn"));
		bmodify.add(Box.createHorizontalStrut(5));
		bmodify.add(btnsuaThuoc= new JButton("Sửa thuốc"));
		bmodify.add(Box.createHorizontalStrut(5));
		bmodify.add(btnxoaThuosc= new JButton("Xóa thuốc"));
		br.add(srcollCt);
		add(br, BorderLayout.CENTER);
		txttongtien.setEditable(false);
		txtGhiChu.setEnabled(false);
		btnTim.setEnabled(false);
		btnLuu.setEnabled(false);
		btnLuuin.setEnabled(false);
		txtMaBN.setEditable(false);
		txttenBN.setEditable(false);
		txtcmnd.setEditable(false);
		btnsuaThuoc.setEnabled(false);
		btnxoaThuosc.setEnabled(false);
		
		// tao su kien
		cboHoaDon.addActionListener(this);
		addDataThuoc(table);
		btnluuTHuoc.addActionListener(this);
		btnLuu.addActionListener(this);
		btnTim.addActionListener(this);
		btnTimTHuoc.addActionListener(this);
		tableThuocDaChon.addMouseListener(this);
		btnsuaThuoc.addActionListener(this);
		btnxoaThuosc.addActionListener(this);
		btnLuuin.addActionListener(this);
	}



	private void addDataThuoc(JTable table) {
		dfModel = (DefaultTableModel)table.getModel();
		dfModel.getDataVector().removeAllElements();
		ThuocDAO thuocDao = new ThuocDAO();
		for (Thuoc thuoc : thuocDao.layDSThuoc()) {
			String[] rowData= {thuoc.getMaThuoc(),thuoc.getTenThuoc(),thuoc.getSoLuong()+"",thuoc.getNhaCC().getTenNCC(),thuoc.getDonViTinh(),thuoc.getGiaBan()+""};
			dfModel.addRow(rowData);
		}
		table.setModel(dfModel);
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Frm_TaoHoaDon().setVisible(true);

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object  o = e.getSource();
		if (o.equals(cboHoaDon)) {
			
			if (cboHoaDon.getSelectedIndex()==0) {
				btnTim.setEnabled(false);
				txtGhiChu.setEnabled(true);				
				txtMaBN.setEditable(false);
				txttenBN.setEditable(false);
				txtcmnd.setEditable(false);
				txttenBN.setText("");
				txtMaBN.setText("");
				txtcmnd.setText("");				
			}
			else  {
				txtGhiChu.setEnabled(true);
				btnTim.setEnabled(true);
				txtMaBN.setEditable(false);
				txttenBN.setEditable(false);
				txtcmnd.setEditable(false);
			} 
		}else if (o.equals(btnluuTHuoc)) {	
				int row = table.getSelectedRow();
				String sLuong = txtsoluongthuoc.getText();
				if (row <0) {
					txtthongbao.setText("Bạn hãy chọn thuốc trước");
				}else
				if (txtsoluongthuoc.getText().equals("")) {
						txtthongbao.setText("Bạn hãy nhập số lượng thuốc");
				}else
				
				if (!txtsoluongthuoc.getText().matches("[0-9]+")) {
						txtthongbao.setText("Bạn hãy lập số lượng thuốc là số ");
				}else
					if (Integer.parseInt(txtsoluongthuoc.getText())<0) {
						txtthongbao.setText("Bạn hãy lập số lượng thuốc lớn hơn 0");
				}else
				 {
					if (Integer.parseInt(txtsoluongthuoc.getText()) > 
					Integer.parseInt( (String) table.getValueAt(row, 2))) {
						txtthongbao.setText(" Thuốc vượt quá số lượng hiện có");
					}
					else {
						updateTableThuocDaChon();
						btnLuu.setEnabled(true);
						btnLuuin.setEnabled(true);
						txtthongbao.setText("");
						double tongTien =0;
						for (int i = 0; i < tableThuocDaChon.getRowCount(); i++) {						
							tongTien += Double.parseDouble( (String) tableThuocDaChon.getValueAt(i, 5));						
						}
						txttongtien.setText(tongTien+"");
						
					}
				}
			
			
		}else if (o.equals(btnLuu)) {
			NhanVien nhanVien = new NhanVienDao().tim1NV(dangNhapDao.getMaNV());
			
			Date date = new Date(Calendar.getInstance().getTime().getTime());			
			hoaDon = new HoaDon(date);
			if (cboHoaDon.getSelectedIndex()==0) {
				hoaDon.setKh(khachHangDao.timkiem1KH("Khách Vãng lai"));
				hoaDon.setNv(nhanVien);
			}else{
				hoaDon.setKh(khachHangDao.timkiemKH(txtMaBN.getText()));
				hoaDon.setNv(nhanVien);
			}
			hoaDonDao.themHoaDon(hoaDon);
			ChiTietHoaDon ctHD = new ChiTietHoaDon();
			for (int i = 0; i < tableThuocDaChon.getRowCount(); i++) {
				
				Thuoc x = thuocDao.lay1Thuoc((String)tableThuocDaChon.getValueAt(i, 0)+"");
				ctHD.setThuoc(x);
				ctHD.setSoLuong(Integer.parseInt((String) tableThuocDaChon.getValueAt(i, 2)));
				ctHD.setHoaDon(hoaDonDao.layHoaDonMoiNhat());
				hoaDonDao.themChiTietHoaDon(ctHD);				
			}
			for (int i = tableThuocDaChon.getRowCount() - 1; i >= 0; i--) {
			    tableModelPM.removeRow(i);
			}
			txtthongbao.setText("Tạo hóa đơn thành công");
			btnLuu.setEnabled(false);
			btnLuuin.setEnabled(false);
			txttongtien.setText("");
			tableModelPM = (DefaultTableModel)tableThuocDaChon.getModel();
			tableModelPM.getDataVector().removeAllElements();
			xoaTrangALL();
			addDataThuoc(table);
			
		}else if (o.equals(btnTim)) {
			if (txttimKiem.getText().equals("")) {
				txtthongbao.setText("Hãy nhập mã , cmnd của khách hàng");
			}
				else {
					 KhachHang kh =	khachHangDao.timkiemKH(txttimKiem.getText());
					 if (kh==null) {
						txtthongbao.setText("Không tìm thấy khách hàng");
					}else {
						 txtMaBN.setText(kh.getMaKH()+"");
						 txttenBN.setText(kh.getHoTen()+"");
						 txtcmnd.setText(kh.getCmnd()+"");
						 txtthongbao.setText("");
					}
				
				}
		
			
		}else if (o.equals(btnLuuin)) {
			NhanVien nhanVien = new NhanVienDao().tim1NV(dangNhapDao.getMaNV());
			
			Date date = new Date(Calendar.getInstance().getTime().getTime());			
			hoaDon = new HoaDon(date);
			if (cboHoaDon.getSelectedIndex()==0) {
				hoaDon.setKh(khachHangDao.timkiem1KH("Khách Vãng lai"));
				hoaDon.setNv(nhanVien);
			}else{
				hoaDon.setKh(khachHangDao.timkiemKH(txtMaBN.getText()));
				hoaDon.setNv(nhanVien);
			}
			hoaDonDao.themHoaDon(hoaDon);
			ChiTietHoaDon ctHD = new ChiTietHoaDon();
			for (int i = 0; i < tableThuocDaChon.getRowCount(); i++) {
				
				Thuoc x = thuocDao.lay1Thuoc((String)tableThuocDaChon.getValueAt(i, 0)+"");
				ctHD.setThuoc(x);
				ctHD.setSoLuong(Integer.parseInt((String) tableThuocDaChon.getValueAt(i, 2)));
				ctHD.setHoaDon(hoaDonDao.layHoaDonMoiNhat());
				hoaDonDao.themChiTietHoaDon(ctHD);				
			}
			
			XuatHoaDon();
			for (int i = tableThuocDaChon.getRowCount() - 1; i >= 0; i--) {
			    tableModelPM.removeRow(i);
			}	
			txtthongbao.setText("Tạo hóa đơn thành công");
			btnLuu.setEnabled(false);
			btnLuuin.setEnabled(false);
			txttongtien.setText("");
			xoaTrangALL();
			addDataThuoc(table);
			
		}else if (o.equals(btnTimTHuoc)) {
			if (validInput()) {
				 ArrayList<Thuoc> dsThuocCanTim =	thuocDao.timThuoc(txtTimThuoc.getText());
				 dfModel = (DefaultTableModel)table.getModel();
					dfModel.getDataVector().removeAllElements();
					ThuocDAO thuocDao = new ThuocDAO();
					for (Thuoc thuoc : dsThuocCanTim) {
						String[] rowData= {thuoc.getMaThuoc(),thuoc.getTenThuoc(),thuoc.getSoLuong()+"",thuoc.getNhaCC().getTenNCC(),thuoc.getDonViTinh(),thuoc.getGiaBan()+""};
						dfModel.addRow(rowData);
					}
					table.setModel(dfModel);
					xoaTrangALL();
			}
		}else if (o.equals(btnsuaThuoc)) {
			if (txtsoluongthuoc.getText().equals("")) {
				txtthongbao.setText(" Bạn chưa nhập số lượng");
			}else if (!txtsoluongthuoc.getText().matches("[0-9]+")) {
				txtthongbao.setText(" Bạn hãy nhập số lượng là số");
			}else if (Integer.parseInt( txtsoluongthuoc.getText())<=0) {
				txtthongbao.setText("Bạn hãy nhập số thuốc lớn hơn 0 ");
			}else{				
				int row = tableThuocDaChon.getSelectedRow();
				double giaban =0;
				if (Integer.parseInt(txtsoluongthuoc.getText())<= Integer.parseInt((String) tableThuocDaChon.getValueAt(row, 2))) {									
					for (int i = 0; i < table.getRowCount(); i++) {
						if (table.getValueAt(i, 0).equals(tableThuocDaChon.getValueAt(row, 0))) {
							table.setValueAt(Integer.parseInt((String) tableThuocDaChon.getValueAt(row,2))
									-Integer.parseInt(txtsoluongthuoc.getText())+Integer.parseInt((String) table.getValueAt(i, 2))+"" , i, 2);
							giaban =Double.parseDouble((String) table.getValueAt(i, 5));

						}					
					}
					tableThuocDaChon.setValueAt(Integer.parseInt(txtsoluongthuoc.getText())+"" , row, 2);
					tableThuocDaChon.setValueAt(Integer.parseInt(txtsoluongthuoc.getText())*giaban  +"" , row, 5);
				}else {
					for (int i = 0; i < table.getRowCount(); i++) {
						if (table.getValueAt(i, 0).equals(tableThuocDaChon.getValueAt(row, 0))) {
							giaban =Double.parseDouble((String) table.getValueAt(i, 5));
							table.setValueAt(Integer.parseInt((String) table.getValueAt(i, 2))+Integer.parseInt((String) tableThuocDaChon.getValueAt(row,2))-Integer.parseInt(txtsoluongthuoc.getText())+"", i, 2);
						}					
					}
					tableThuocDaChon.setValueAt(Integer.parseInt(txtsoluongthuoc.getText())+"" , row, 2);
					tableThuocDaChon.setValueAt(Integer.parseInt(txtsoluongthuoc.getText())*giaban  +"" , row, 5);
					
				}
				double tongTien =0;
				for (int i = 0; i < tableThuocDaChon.getRowCount(); i++) {						
					tongTien += Double.parseDouble( (String) tableThuocDaChon.getValueAt(i, 5));						
				}
				txttongtien.setText(tongTien+"");		
				btnsuaThuoc.setEnabled(false);
				btnxoaThuosc.setEnabled(false);
				txtthongbao.setText("");
				//updateTableThuocDaChon();
			}		
		}else if (o.equals(btnxoaThuosc)) {
			int row = tableThuocDaChon.getSelectedRow();
			String sl = (String) tableThuocDaChon.getValueAt(row, 2);
		
			
			for (int i = 0; i < table.getRowCount(); i++) {
				if (table.getValueAt(i, 0).equals(tableThuocDaChon.getValueAt(row, 0))) {
					String slCu = (String) table.getValueAt(i, 2);
					table.setValueAt((Integer.parseInt(sl)+ Integer.parseInt(slCu) )+"", i, 2);
				}			
			}
			
			tableModelPM.removeRow(row);	
			double tongTien =0;
			for (int i = 0; i < tableThuocDaChon.getRowCount(); i++) {						
				tongTien += Double.parseDouble( (String) tableThuocDaChon.getValueAt(i, 5));						
			}
			if (tableThuocDaChon.getRowCount()==0) {
				btnLuu.setEnabled(false);
				btnLuuin.setEnabled(false);
			}
			txttongtien.setText(tongTien+"");	
			btnsuaThuoc.setEnabled(false);
			btnxoaThuosc.setEnabled(false);
			txtthongbao.setText("");
			txtsoluongthuoc.setText("");
		}
	}
	private void XuatHoaDon() {
		// TODO Auto-generated method stub
		
		Map<String, Object> map = new Hashtable<String, Object>();
		try {
			JasperReport report = JasperCompileManager.compileReport("src/ui/hd.jrxml");
			map.put("mahd", hoaDonDao.layHoaDonMoiNhat().getMaHD()+"");
			map.put("tongtien",txttongtien.getText());
			map.put("ghichu",txtGhiChu.getText());
			JasperPrint p = JasperFillManager.fillReport(report,map, new DatabaseConnect().connect());
			JasperViewer.viewReport(p,false);
			JasperExportManager.exportReportToPdfFile(p,"hoadon.pdf");
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}



	private boolean kiemtrasoLuong() {
		 
		int row = tableThuocDaChon.getSelectedRow();
		for (int i = 0; i < table.getRowCount(); i++) {
			int slthuoc =Integer.parseInt( (String) table.getValueAt(i, 2));
			int slSua = Integer.parseInt(txtsoluongthuoc.getText());
			if (table.getValueAt(i, 0).equals(tableThuocDaChon.getValueAt(row, 0))) {
				if (slthuoc<slSua ) {
					return false;
				}
			}
		}
		return true;
	}



	private void xoaTrangALL() {
		txtcmnd.setText("");
		txtGhiChu.setText("");
		txtMaBN.setText("");
		txtsoluongthuoc.setText("");
		txttenBN.setText("");		
		txttimKiem.setText("");
		txttongtien.setText("");
		
	}



	private boolean validInput() {
		// TODO Auto-generated method stub
		return true;
	}



	private void updateTableThuocDaChon() {
		int flag = 1;
		int rowThuoc =0;
		tableModelPM = (DefaultTableModel)tableThuocDaChon.getModel();
		
		int row = table.getSelectedRow();
		for (int i = 0; i < tableThuocDaChon.getRowCount(); i++) {
			if (table.getValueAt(row, 0).equals(tableThuocDaChon.getValueAt(i, 0)) ) {
				flag =0;
				rowThuoc =i;
			}
		}
		if (flag==1) {
			double giaban = Double.parseDouble( (String) table.getValueAt(row, 5)) *Integer.parseInt(txtsoluongthuoc.getText());
			String[] rowData= {table.getValueAt(row, 0)+"",table.getValueAt(row, 1)+"",txtsoluongthuoc.getText()
					,table.getValueAt(row, 3)+"",table.getValueAt(row, 4)+"",giaban+""};
			tableModelPM.addRow(rowData);		
			tableThuocDaChon.setModel(tableModelPM);
			table.setValueAt( (Integer.parseInt((String) table.getValueAt(row, 2))- Integer.parseInt(txtsoluongthuoc.getText()))+"" , row, 2);
					
		}else {
			tableThuocDaChon.setValueAt(Integer.parseInt(txtsoluongthuoc.getText())+ 
					Integer.parseInt((String) tableThuocDaChon.getValueAt(rowThuoc, 2))+"", rowThuoc, 2);
			tableThuocDaChon.setValueAt(Integer.parseInt((String) tableThuocDaChon.getValueAt(rowThuoc, 2))* 
					Double.parseDouble((String) table.getValueAt(row, 5))+"", rowThuoc, 5);
			table.setValueAt( (Integer.parseInt((String) table.getValueAt(row, 2))- Integer.parseInt(txtsoluongthuoc.getText()))+"" , row, 2);	
			
		}
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		if (o.equals(tableThuocDaChon)) {
			int row =tableThuocDaChon.getSelectedRow();
			txtsoluongthuoc.setText((String) tableThuocDaChon.getValueAt(row, 2));
			btnsuaThuoc.setEnabled(true);
			btnxoaThuosc.setEnabled(true);
		}
			
	}@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}