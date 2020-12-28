package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import dao.DatabaseConnect;
import entity.DataBaoCao;
import entity.Thongbao;

public class BaoCaoDTQuy extends JFrame {
	private DefaultTableModel tblModelNgay, tblModelThang,tblModelNam;
	private JTable tbdthNgay, tbdthThang, tbdthNam;
	private JLabel lbngay, lbdthu,lbThang, lbNam;
	private JTextField dthuNgay,dthuThang,dthuNam, txtNgay, txtThang, txtNam;
	private JButton btnNgay, btnThang, btnNam,btnxuatbc;


	public BaoCaoDTQuy() {
		setTitle("Báo cáo doanh thu");
		setSize(1000, 700);
		setLocationRelativeTo(null);
		setVisible(false);
		setExtendedState(MAXIMIZED_BOTH);

		JTabbedPane tbbPane = new JTabbedPane();

		JPanel pnlDthuNgay = createPnlDthuNgay();

		tbbPane.addTab("Doanh thu trong quý", null, pnlDthuNgay);


		add(tbbPane);
	}

	private JPanel createPnlDthuNgay() {
		JPanel pnldthuNgay = new JPanel();
		pnldthuNgay.setLayout(null);

		pnldthuNgay.add(lbThang= new JLabel("Quý: "));
		pnldthuNgay.add(txtThang=new JTextField());
		pnldthuNgay.add(lbNam= new JLabel("Năm: "));
		pnldthuNgay.add(txtNam=new JTextField());
		pnldthuNgay.add(btnNgay= new JButton("Xem báo cáo"));
		pnldthuNgay.add(btnxuatbc= new JButton("Xuất báo cáo"));


		btnxuatbc.setBounds(600, 50, 150, 20);

		lbThang.setBounds(200, 50, 100, 20);
		txtThang.setBounds(240, 50, 50, 20);

		lbNam.setBounds(300, 50, 100, 20);
		txtNam.setBounds(340, 50, 50, 20);

		btnNgay.setBounds(400, 50, 150, 20);

		JScrollPane scroll;
		String[] headers = "Mã HD;Ngày lập;Mã Thuốc;Tên Thuốc; Số Lượng;Tổng tiền".split(";");
		tblModelNgay = new DefaultTableModel(headers, 0);
		add(scroll = new JScrollPane(tbdthNgay = new JTable(tblModelNgay), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
		tbdthNgay.setRowHeight(20);
		scroll.setBounds(5, 100, 1350, 300);

		pnldthuNgay.add(scroll);

		pnldthuNgay.add(lbdthu= new JLabel("Tổng doanh thu trong quý:"));
		pnldthuNgay.add(dthuNgay=new JTextField());
		btnxuatbc.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					XSSFWorkbook workbook = new XSSFWorkbook();
					XSSFSheet spreadsheet = workbook.createSheet("Doanhthuquy");

					XSSFRow row = null;
					Cell cell = null;

					row = spreadsheet.createRow((short) 1);
					row.setHeight((short) 500);
					cell = row.createCell(2, CellType.STRING);
					cell.setCellValue("NHÀ THUỐC NGUYỄN VĂN BẢO 2");
					row = spreadsheet.createRow((short) 2);
					row.setHeight((short) 500);
					cell=row.createCell(0, CellType.STRING);
					cell.setCellValue("DOANH THU THEO QUÝ 4");

					row = spreadsheet.createRow((short) 4);
					row.setHeight((short) 500);
					cell = row.createCell(0, CellType.STRING);
					cell.setCellValue("Mã HD");
					cell = row.createCell(1, CellType.STRING);
					cell.setCellValue("Ngày lập");
					cell = row.createCell(2, CellType.STRING);
					cell.setCellValue("Mã thuốc");
					cell = row.createCell(3, CellType.STRING);
					cell.setCellValue("Tên thuốc");
					cell = row.createCell(4, CellType.STRING);
					cell.setCellValue("Số lượng");
					cell = row.createCell(5, CellType.STRING);
					cell.setCellValue("Thành tiền");
				
					
					

					int i =5;
					for (DataBaoCao bc : getDs()) {
						row = spreadsheet.createRow((short) i);
						row.setHeight((short) 400);
						row.createCell(0).setCellValue(bc.getMaHD());
						row.createCell(1).setCellValue(bc.getNgaylap().toString());
						row.createCell(2).setCellValue(bc.getMath());
						row.createCell(3).setCellValue(bc.getTentth());
						row.createCell(4).setCellValue(bc.getSoluong());
						row.createCell(5).setCellValue(bc.getThanhtien());
						i++;

					}
					row = spreadsheet.createRow((short) i);
					cell = row.createCell(4, CellType.STRING);
					cell.setCellValue("Doanh thu");
					for (DataBaoCao bc : getDs()) {
						cell = row.createCell(5, CellType.STRING);
						cell.setCellValue(bc.getDoanhthu());
					}


					FileOutputStream out = new FileOutputStream(new File("D:/doanhthuquy.xlsx"));
					workbook.write(out);
					out.close();
					Thongbao.thongbao("Xuất báo cáo thành công!", "Thông báo");
				} catch (Exception ex) {
					ex.printStackTrace();
				}

			}
		});
		btnNgay.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tblModelNgay = (DefaultTableModel) tbdthNgay.getModel();
				tblModelNgay.getDataVector().removeAllElements();

				double tongDT=0.0;
				try {
					Connection con = DatabaseConnect.getInstance().connect();
					Statement stmt = con.createStatement();
					switch(txtThang.getText()) {
					case 1+"":
						String sql = "select ct.maHD as MaHD, h.ngayLap as NgayLap,th.maThuoc as MaThuoc, th.tenThuoc as TenThuoc,\r\n"
								+ "ct.soluong, SUM (th.giaBan*ct.soluong) as Tongtien\r\n"
								+ "from Thuoc th join ChiTietHoaDon ct on th.maThuoc=ct.maThuoc join HoaDon h on ct.maHD=h.maHD\r\n"
								+ "where MONTH(ngayLap) in(1,2,3) and YEAR(ngaylap) in('"+txtNam.getText()+"')\r\n"
								+ "group by ct.maHD, h.ngayLap, th.maThuoc, ct.soluong, th.tenThuoc";
					ResultSet rs = stmt.executeQuery(sql);
					while(rs.next()) {

						int mahd= rs.getInt(1);
						java.util.Date ngay = rs.getDate(2);
						String mathuoc = rs.getString(3);
						String tenth = rs.getString(4);
						int soluong = rs.getInt(5);
						Double tong = rs.getDouble(6);
						tongDT+=rs.getDouble(6);
						String [] row = {mahd+"",ngay+"",mathuoc, tenth,soluong+"", tong+""};

						tblModelNgay.addRow(row);

					}
					tblModelNgay = (DefaultTableModel) tbdthNgay.getModel();

					break;
					case 2+"":
						String sql2 = "select ct.maHD as MaHD, h.ngayLap as NgayLap,th.maThuoc as MaThuoc, th.tenThuoc as TenThuoc,\r\n"
								+ "ct.soluong, SUM (th.giaBan*ct.soluong) as Tongtien\r\n"
								+ "from Thuoc th join ChiTietHoaDon ct on th.maThuoc=ct.maThuoc join HoaDon h on ct.maHD=h.maHD\r\n"
								+ "where MONTH(ngayLap) in(4,5,6) and YEAR(ngaylap) in('"+txtNam.getText()+"')\r\n"
								+ "group by ct.maHD, h.ngayLap, th.maThuoc, ct.soluong, th.tenThuoc";
					ResultSet rs2 = stmt.executeQuery(sql2);
					while(rs2.next()) {

						int mahd= rs2.getInt(1);
						java.util.Date ngay = rs2.getDate(2);
						String mathuoc = rs2.getString(3);
						String tenth = rs2.getString(4);
						int soluong = rs2.getInt(5);
						Double tong = rs2.getDouble(6);
						tongDT+=rs2.getDouble(6);
						String [] row = {mahd+"",ngay+"",mathuoc, tenth,soluong+"", tong+""};
						tblModelNgay = (DefaultTableModel) tbdthNgay.getModel();

					}

					tbdthNgay.setModel(tblModelNgay);
					break;
					case 3+"":
						String sql3 = "select ct.maHD as MaHD, h.ngayLap as NgayLap,th.maThuoc as MaThuoc, th.tenThuoc as TenThuoc,\r\n"
								+ "ct.soluong, SUM (th.giaBan*ct.soluong) as Tongtien\r\n"
								+ "from Thuoc th join ChiTietHoaDon ct on th.maThuoc=ct.maThuoc join HoaDon h on ct.maHD=h.maHD\r\n"
								+ "where MONTH(ngayLap) in(7,8,9) and YEAR(ngaylap) in('"+txtNam.getText()+"')\r\n"
								+ "group by ct.maHD, h.ngayLap, th.maThuoc, ct.soluong, th.tenThuoc";
					ResultSet rs3 = stmt.executeQuery(sql3);
					while(rs3.next()) {

						int mahd= rs3.getInt(1);
						java.util.Date ngay = rs3.getDate(2);
						String mathuoc = rs3.getString(3);
						String tenth = rs3.getString(4);
						int soluong = rs3.getInt(5);
						Double tong = rs3.getDouble(6);
						tongDT+=rs3.getDouble(6);
						String [] row = {mahd+"",ngay+"",mathuoc, tenth,soluong+"", tong+""};
						tblModelNgay = (DefaultTableModel) tbdthNgay.getModel();


					}

					tbdthNgay.setModel(tblModelNgay);
					break;
					case 4+"":
						
						String sql4 = "select ct.maHD as MaHD, h.ngayLap as NgayLap,th.maThuoc as MaThuoc, th.tenThuoc as TenThuoc,\r\n"
								+ "ct.soluong, SUM (th.giaBan*ct.soluong) as Tongtien\r\n"
								+ "from Thuoc th join ChiTietHoaDon ct on th.maThuoc=ct.maThuoc join HoaDon h on ct.maHD=h.maHD\r\n"
								+ "where MONTH(ngayLap) in(10,11,12) and YEAR(ngaylap) in('"+txtNam.getText()+"')\r\n"
								+ "group by ct.maHD, h.ngayLap, th.maThuoc, ct.soluong, th.tenThuoc";
					ResultSet rs4 = stmt.executeQuery(sql4);
					while(rs4.next()) {

						int mahd= rs4.getInt(1);
						java.util.Date ngay = rs4.getDate(2);
						String mathuoc = rs4.getString(3);
						String tenth = rs4.getString(4);
						int soluong = rs4.getInt(5);
						Double tong = rs4.getDouble(6);
						tongDT+=rs4.getDouble(6);
						String [] row = {mahd+"",ngay+"",mathuoc, tenth,soluong+"", tong+""};

						tblModelNgay.addRow(row);

					}

					tbdthNgay.setModel(tblModelNgay);

					break;
					}


				} catch (Exception ex) {
					ex.printStackTrace();
				}

				dthuNgay.setText(tongDT+"");

			}
		});

		dthuNgay.setEditable(false);
		dthuNgay.setFont(new Font("Arial",Font.BOLD, 14));
		dthuNgay.setForeground(Color.RED);
		lbdthu.setBounds(900, 510, 200, 100);
		dthuNgay.setBounds(1100, 545, 150, 30);
		return pnldthuNgay;
	}
	public ArrayList<DataBaoCao> getDs() {
		ArrayList<DataBaoCao> ds= new ArrayList<DataBaoCao>();
		try {
			Connection con = DatabaseConnect.getInstance().connect();
			Statement stmt = con.createStatement();
			String sql = "select ct.maHD as MaHD, h.ngayLap as NgayLap,th.maThuoc as MaThuoc, th.tenThuoc as TenThuoc,\r\n"
					+ "ct.soluong, SUM (th.giaBan*ct.soluong) as Tongtien\r\n"
					+ "from Thuoc th join ChiTietHoaDon ct on th.maThuoc=ct.maThuoc join HoaDon h on ct.maHD=h.maHD\r\n"
					+ "where MONTH(ngayLap) in(10,11,12) and YEAR(ngaylap) in('"+txtNam.getText()+"')\r\n"
					+ "group by ct.maHD, h.ngayLap, th.maThuoc, ct.soluong, th.tenThuoc";
			ResultSet rs = stmt.executeQuery(sql);

			double tongDTth=0.0;
			while(rs.next()) {

				int mahd= rs.getInt(1);
				java.util.Date ngay = rs.getDate(2);
				String mathuoc = rs.getString(3);
				String tenth = rs.getString(4);
				int soluong = rs.getInt(5);
				Double tong = rs.getDouble(6);
				tongDTth+=rs.getDouble(6);

				DataBaoCao bc = new DataBaoCao(mahd, ngay, mathuoc, tenth, soluong, tong);
				bc.setDoanhthu(tongDTth);

				ds.add(bc);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ds;
	}
}
