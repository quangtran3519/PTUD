package ui;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

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

import java.awt.Component;


public class Frm_QuanLyHoaDon extends JFrame implements ActionListener,MouseListener{
	private JLabel lblTieuDe, lbltimKiem, lblTenBN, lblmaBN, lblCmnd;
	private JTextField txttimKiem, txtTenKh, txtmakh, txttongtien, txtcmnd, txtthongbao;
	private JButton btnThemDia, btnXoaDia, btnSua, btnTaoPhieu, btnTim,btnRefesh;
	private JTable table;
	private DefaultTableModel tableModel;
	private JLabel lbltimCD;
	private JTextField txtTimCD;
	private JButton btnTimCD;
	private DefaultTableModel dfModel;
	private DefaultTableModel tableModelPT;
	private JComboBox<String> cboTimKH,cbotimCD,cboHoaDon;
	private JTable tablePhieuThue;
	private JLabel lblBanTheoDon,lblTongTien;
	private JLabel lblghichu;
	private JTextArea txtGhiChu;
	private JButton btnLuu;
	private JButton btnLuuin;
	private JTextField txtMaBN;
	private JTextField txttenBN;
	private JLabel lblsoLuongThuoc;
	private JTextField txtsoluongthuoc;
	private JButton btnsuaThuoc;
	private JButton btnxoaThuosc;
	private JButton btnluuTHuoc;
	private JTextField txttimKiem1;
	private JLabel lblNhanVien;
	private JComboBox<String> cboNhanVien;
	private JButton btnchinhsua;
	private JTextField txttimKiemMaHD;
	private JTextField txttimKiemtenKH;
	private JButton btnxoaHD;
	

	public  Frm_QuanLyHoaDon() {
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
		pnorth.add(lblTieuDe = new JLabel("QUẢN LÝ HÓA ĐƠN"));
		lblTieuDe.setFont(new Font("arial", Font.BOLD, 26));
		lblTieuDe.setForeground(Color.red);
		getContentPane().add(pnorth, BorderLayout.NORTH);
		Box b, bl, br, b1, b2, b3, b4, b5, b6, b7, b8, b9,bdon,bmodify;		
		b = Box.createHorizontalBox();
		b.add(bl = Box.createVerticalBox());
		bl.add(bdon = Box.createHorizontalBox());
		bl.add(Box.createVerticalStrut(10));
		bdon.add(lblBanTheoDon = new JLabel("Loại hóa đơn"));
		bdon.add(cboHoaDon= new JComboBox<String>());
		cboHoaDon.addItem("Bán không theo đơn");
		cboHoaDon.addItem("Bán  theo đơn");
		bl.add(b4 = Box.createHorizontalBox());
		bl.add(Box.createVerticalStrut(10));
		b4.add(lblNhanVien = new JLabel("Nhân viên"));
		b4.add(cboNhanVien= new JComboBox<String>());
		NhanVienDao nhanVienDao = new NhanVienDao();
		for(NhanVien x : nhanVienDao.doctuBang()) {
			cboNhanVien.addItem(x.getTenNV());
		}
	
		bl.add(b1 = Box.createHorizontalBox());
		bl.add(Box.createVerticalStrut(10));
		b1.add(lblmaBN = new JLabel("Nhập mã hóa đơn:"));	
		b1.add(txttimKiemMaHD = new JTextField(10));			
		bl.add(b2 = Box.createHorizontalBox());		
		b2.add(lblTenBN = new JLabel("Nhập mã tên khách hàng:"));	
		b2.add(txttimKiemtenKH = new JTextField(10));
		bl.add(Box.createVerticalStrut(10));		
		bl.add(b3 = Box.createHorizontalBox());
		bl.add(Box.createVerticalStrut(10));
		b3.add(btnTim = new JButton("Tìm kiếm"));

		
		bl.add(b8 = Box.createHorizontalBox());
		bl.add(Box.createVerticalStrut(10));
		b8.add(btnxoaHD = new JButton("Xóa Hóa Đơn"));
		b8.add(Box.createHorizontalStrut(5));
		b8.add(btnLuu = new JButton("In hóa đơn"));
		b8.add(Box.createHorizontalStrut(5));
		b8.add(btnLuuin = new JButton("Đặt lại & in Hóa đơn"));
		
		lblNhanVien.setPreferredSize(lblTenBN.getPreferredSize());
		lblBanTheoDon.setPreferredSize(lblTenBN.getPreferredSize());
		lblTenBN.setPreferredSize(lblTenBN.getPreferredSize());
		bl.add(txtthongbao = new JTextField(""));
		txtthongbao.setForeground(Color.red);
		txtthongbao.setBorder(null);
		txtthongbao.setEditable(false);
		txtthongbao.setFont(new Font("arial, time new roman", Font.BOLD, 15));	
		 JPanel pwest = new JPanel();
		pwest.add(bl);			
		pwest.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.BLUE), "Tìm kiếm hóa đơn"));
		getContentPane().add(pwest, BorderLayout.WEST);
		br = Box.createVerticalBox();
		br.add(b9 = Box.createHorizontalBox());	
		b9.add(Box.createHorizontalStrut(5));
//		b9.add(cbotimCD = new JComboBox<String>());
//		cbotimCD.addItem("Mã CD ");
//		cbotimCD.addItem("Tên CD");
		String[] header = "Mã hóa đơn,Ngày lập , Tên khách hàng, Tên nhân viên".split(",");
		dfModel = new DefaultTableModel(header, 0);
		table = new JTable(dfModel);
		table.setAutoCreateRowSorter(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		JScrollPane scroll = new JScrollPane();
		scroll.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.BLUE), "Danh sách hóa đơn"));
		scroll.setViewportView(table);
		br.add(scroll);
		
		br.add(bmodify = Box.createHorizontalBox());
		br.add(Box.createHorizontalStrut(10));
		bmodify.add(lblsoLuongThuoc = new JLabel("Tổng tiền :  "));
		bmodify.add(txttongtien = new JTextField(10));
		txttongtien.setEditable(false);
		bmodify.add(Box.createHorizontalStrut(5));
//		bmodify.add(btnluuTHuoc= new JButton("Chọn"));
//		bmodify.add(Box.createHorizontalStrut(5));
//		bmodify.add(btnsuaThuoc= new JButton("Sửa thuốc"));
//		bmodify.add(Box.createHorizontalStrut(5));
//		bmodify.add(btnxoaThuosc= new JButton("Xóa thuốc"));
		br.add(bmodify);
		String[] headerCTPT = "Mã Thuốc,Tên thuốc , Số lượng, Nhà cung cấp,Giá".split(",");
		tableModelPT = new DefaultTableModel(headerCTPT, 0);
		tablePhieuThue = new JTable(tableModelPT);
		JScrollPane srcollCt = new JScrollPane(tablePhieuThue);
		srcollCt.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.BLUE), "Danh sách thuốc đã mua"));
		br.add(srcollCt);
		getContentPane().add(br, BorderLayout.CENTER);
		updateTableHoaDon();
		table.addMouseListener(this);
		btnTim.addActionListener(this);
		btnLuuin.addActionListener(this);
		btnLuu.addActionListener(this);
		cboHoaDon.addActionListener(this);
		cboNhanVien.addActionListener(this);
		btnxoaHD.addActionListener(this);
		btnLuuin.setEnabled(false);
		btnLuu.setEnabled(false);
		btnxoaHD.setEnabled(false);
		
	}
	


	private void updateTableHoaDon() {
		dfModel = (DefaultTableModel) table.getModel();
		dfModel.getDataVector().removeAllElements();
		HoaDonDao hoaDonDao = new HoaDonDao();
		for(HoaDon hd : hoaDonDao.getDSHoaDon()) {
			String[] rowData = {hd.getMaHD()+"",hd.getNgayLap()+"",hd.getKh().getHoTen(),hd.getNv().getTenNV()};
			dfModel.addRow(rowData);
		}
		table.setModel(dfModel);
		
	}
	private void updateTableHoaDonKhongKeDon() {
		dfModel = (DefaultTableModel) table.getModel();
		dfModel.getDataVector().removeAllElements();
		HoaDonDao hoaDonDao = new HoaDonDao();
		for(HoaDon hd : hoaDonDao.getDSHoaDonKhongKeDon()) {
			String[] rowData = {hd.getMaHD()+"",hd.getNgayLap()+"",hd.getKh().getHoTen(),hd.getNv().getTenNV()};
			dfModel.addRow(rowData);
		}
		table.setModel(dfModel);
		
	}
	private void updateTableHoaDonKeDon() {
		dfModel = (DefaultTableModel) table.getModel();
		dfModel.getDataVector().removeAllElements();
		HoaDonDao hoaDonDao = new HoaDonDao();
		for(HoaDon hd : hoaDonDao.getDSHoaDonKeDon()) {
			String[] rowData = {hd.getMaHD()+"",hd.getNgayLap()+"",hd.getKh().getHoTen(),hd.getNv().getTenNV()};
			dfModel.addRow(rowData);
		}
		table.setModel(dfModel);
		
	}



	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Frm_QuanLyHoaDon().setVisible(true);

	}



	@Override
	public void mouseClicked(MouseEvent e) {
		Object o =e.getSource();
		if (o.equals(table)) {
			btnLuuin.setEnabled(true);
			btnLuu.setEnabled(true);
			btnxoaHD.setEnabled(true);
			int row = table.getSelectedRow();
			String maHD = (String) table.getValueAt(row, 0);
			if (row>=0) {
				tableModelPT = (DefaultTableModel) tablePhieuThue.getModel();
				tableModelPT.getDataVector().removeAllElements();
				HoaDonDao hoaDonDao = new HoaDonDao();
				for(ChiTietHoaDon x : hoaDonDao.getCTHD(Integer.parseInt(maHD))) {
					
						String[] rowdata = { x.getThuoc().getMaThuoc()+"",x.getThuoc().getTenThuoc()
								,x.getSoLuong()+"",x.getThuoc().getNhaCC().getTenNCC(),x.getThuoc().getGiaBan()*x.getSoLuong() +""};
						tableModelPT.addRow(rowdata);					
				}
				tablePhieuThue.setModel(tableModelPT);
			}
			double tongTien =0;
			for (int i = 0; i < tablePhieuThue.getRowCount(); i++) {						
				tongTien += Double.parseDouble( (String) tablePhieuThue.getValueAt(i, 4));						
			}
			txttongtien.setText(tongTien+"");
		}else if (o.equals(tablePhieuThue)) {
			
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
		Object o = e.getSource();
		if (o.equals(btnLuu)) {
			XuatHoaDon();
			JOptionPane.showMessageDialog(null, "Đã xuất hóa đơn");
			btnLuuin.setEnabled(false);
			btnLuu.setEnabled(false);
			btnxoaHD.setEnabled(false);
			
		}else if (o.equals(btnLuuin)) {
			int row = table.getSelectedRow();
			DangNhapDao dangNhapDao = new DangNhapDao();
			HoaDonDao hoaDonDao = new HoaDonDao();
			KhachHangDao khachHangDao = new KhachHangDao();
			ThuocDAO thuocDao = new ThuocDAO();
			NhanVienDao nhanVienDao = new NhanVienDao();
			
			
			Date date = new Date(Calendar.getInstance().getTime().getTime());
			HoaDon hoaDon = new HoaDon(date);
			HoaDon hoaDon2 = hoaDonDao.getHoaDonInLai(Integer.parseInt((String) table.getValueAt(row, 0)));
			//JOptionPane.showMessageDialog(null, hoaDon2.toString());
			KhachHang khachHang = hoaDon2.getKh();
			NhanVien nv = nhanVienDao.tim1NV(dangNhapDao.getMaNV());
			hoaDon.setKh(khachHang);
			hoaDon.setNv(nv);
			hoaDonDao.themHoaDon(hoaDon);
			ChiTietHoaDon ctHD = new ChiTietHoaDon();
			for (int i = 0; i < tablePhieuThue.getRowCount(); i++) {
				
				Thuoc x = thuocDao.lay1Thuoc((String)tablePhieuThue.getValueAt(i, 0)+"");
				ctHD.setThuoc(x);
				ctHD.setSoLuong(Integer.parseInt((String) tablePhieuThue.getValueAt(i, 2)));
				ctHD.setHoaDon(hoaDonDao.layHoaDonMoiNhat());
				hoaDonDao.themChiTietHoaDon(ctHD);				
			}
		
			XuatHoaDonTaoLai();
			updateTableHoaDon();
			JOptionPane.showMessageDialog(null, "Tạo hóa đơn thành công");
			btnLuuin.setEnabled(false);
			btnLuu.setEnabled(false);
			btnxoaHD.setEnabled(false);
		}else if (o.equals(cboHoaDon)) {
			btnLuuin.setEnabled(false);
			btnLuu.setEnabled(false);
			btnxoaHD.setEnabled(false);
			if (cboHoaDon.getSelectedIndex()==0) {
				updateTableHoaDonKhongKeDon();
				
			}else {
				updateTableHoaDonKeDon();
			}
			for (int i = tablePhieuThue.getRowCount() - 1; i >= 0; i--) {
			    tableModelPT.removeRow(i);
			}
		}else if (o.equals(cboNhanVien)) {
			btnLuuin.setEnabled(false);
			btnLuu.setEnabled(false);
			btnxoaHD.setEnabled(false);
			String tenNV = cboNhanVien.getSelectedItem().toString();
			
			dfModel = (DefaultTableModel) table.getModel();
			dfModel.getDataVector().removeAllElements();
			HoaDonDao hoaDonDao = new HoaDonDao();
			if (hoaDonDao.getDSHoaDonTheoTenNV(tenNV).size() >0) {
				for(HoaDon hd : hoaDonDao.getDSHoaDonTheoTenNV(tenNV)) {
					String[] rowData = {hd.getMaHD()+"",hd.getNgayLap()+"",hd.getKh().getHoTen(),hd.getNv().getTenNV()};
					dfModel.addRow(rowData);
				}
				table.setModel(dfModel);		
			}else {
				JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu");
				
			}
			for (int i = tablePhieuThue.getRowCount() - 1; i >= 0; i--) {
			    tableModelPT.removeRow(i);
			}			
		}else if (o.equals(btnTim)) {
			btnLuuin.setEnabled(false);
			btnLuu.setEnabled(false);
			btnxoaHD.setEnabled(false);
			if (txttimKiemMaHD.getText().equals("") && txttimKiemtenKH.getText().equals("")) {
				updateTableHoaDon();
				for (int i = tablePhieuThue.getRowCount() - 1; i >= 0; i--) {
				    tableModelPT.removeRow(i);
				}	
			}else {
				 if (!txttimKiemMaHD.getText().matches("[0-9]+")) {
					txtthongbao.setText("Bạn hãy nhập mã hóa đơn là số");
				 }else {
				dfModel = (DefaultTableModel) table.getModel();
				dfModel.getDataVector().removeAllElements();
				HoaDonDao hoaDonDao = new HoaDonDao();
				if (hoaDonDao.getDSHoaDonTheoDK(txttimKiemtenKH.getText(), txttimKiemMaHD.getText()).size() >0) {
					for(HoaDon hd :hoaDonDao.getDSHoaDonTheoDK(txttimKiemtenKH.getText(), txttimKiemMaHD.getText())) {
						String[] rowData = {hd.getMaHD()+"",hd.getNgayLap()+"",hd.getKh().getHoTen(),hd.getNv().getTenNV()};
						dfModel.addRow(rowData);
					}
					table.setModel(dfModel);		
				}else {
					JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu");
					
				}
				for (int i = tablePhieuThue.getRowCount() - 1; i >= 0; i--) {
				    tableModelPT.removeRow(i);
				}	
				
			}
			}
		}else if (o.equals(btnxoaHD)) {
			int row = table.getSelectedRow();
			HoaDonDao hoaDonDao = new HoaDonDao();
			
			 int x =JOptionPane.showConfirmDialog(null, " Xác nhận xóa ?","YES",JOptionPane.YES_NO_OPTION);
			if (x== JOptionPane.YES_OPTION) {
				
				hoaDonDao.xoaHD( (String)table.getValueAt(row, 0));
				
				updateTableHoaDon();
			}
			
			
		}
	}
	



	private void XuatHoaDonTaoLai() {
		int row = table.getSelectedRow();
		// TODO Auto-generated method stub
		if (row >0) {
			String mahd = (String) table.getValueAt(row, 0);
			Map<String, Object> map = new Hashtable<String, Object>();
			try {
				JasperReport report = JasperCompileManager.compileReport("src/ui/hd.jrxml");
				map.put("mahd",new HoaDonDao().layHoaDonMoiNhat().getMaHD()+"");
				map.put("tongtien",txttongtien.getText()+"");
				map.put("ghichu","In lại hóa đơn");
				JasperPrint p = JasperFillManager.fillReport(report,map, new DatabaseConnect().connect());
				JasperViewer.viewReport(p,false);
				JasperExportManager.exportReportToPdfFile(p,"hoadon.pdf");
			} catch (JRException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else {
			txtthongbao.setText("Bạn phải chọn hóa đơn cần in");
		}
		
	}



	private void XuatHoaDon() {
		int row = table.getSelectedRow();
		// TODO Auto-generated method stub
		if (row >0) {
			String mahd = (String) table.getValueAt(row, 0);
			Map<String, Object> map = new Hashtable<String, Object>();
			try {
				JasperReport report = JasperCompileManager.compileReport("src/ui/hd.jrxml");
				map.put("mahd",mahd);
				map.put("tongtien",txttongtien.getText()+"");
				map.put("ghichu","In lại hóa đơn");
				JasperPrint p = JasperFillManager.fillReport(report,map, new DatabaseConnect().connect());
				JasperViewer.viewReport(p,false);
				JasperExportManager.exportReportToPdfFile(p,"hoadon.pdf");
			} catch (JRException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else {
			txtthongbao.setText("Bạn phải chọn hóa đơn cần in");
		}
		
	
	
	}
}