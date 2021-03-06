
package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import dao.DatabaseConnect;
import entity.DataBaoCao;
import entity.Thongbao;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;

public class BaoCaoDoanhThuThang extends JFrame {

	private DefaultTableModel tblModelNgay, tblModelThang,tblModelNam;
	private JTable tbdthNgay, tbdthThang, tbdthNam;
	private JLabel lbngay, lbdthu,lbThang, lbNam;
	private JTextField dthuNgay,dthuThang,dthuNam, txtNgay, txtThang, txtNam;
	private JButton btnNgay, btnThang, btnNam,btnxuatbc;


	public BaoCaoDoanhThuThang() {
		setTitle("Báo cáo doanh thu");
		setSize(1000, 700);
		setLocationRelativeTo(null);
		setVisible(false);
		setExtendedState(MAXIMIZED_BOTH);

		JTabbedPane tbbPane = new JTabbedPane();


		JPanel pnlDthuThang = createPnlDthuThang();

		tbbPane.addTab("Doanh thu trong tháng", null, pnlDthuThang);


		add(tbbPane);
	}


	//Theo tháng
	private JPanel createPnlDthuThang() {
		JPanel pnldthuNgay = new JPanel();
		pnldthuNgay.setLayout(null);
		pnldthuNgay.add(lbThang= new JLabel("Tháng: "));
		pnldthuNgay.add(txtThang=new JTextField());
		pnldthuNgay.add(lbNam= new JLabel("Năm: "));
		pnldthuNgay.add(txtNam=new JTextField());
		pnldthuNgay.add(btnThang= new JButton("Xem báo cáo"));
		pnldthuNgay.add(btnxuatbc= new JButton("Xuất báo cáo"));


		btnxuatbc.setBounds(600, 50, 150, 20);


		lbThang.setBounds(200, 50, 100, 20);
		txtThang.setBounds(240, 50, 50, 20);

		lbNam.setBounds(300, 50, 100, 20);
		txtNam.setBounds(340, 50, 50, 20);

		btnThang.setBounds(400, 50, 150, 20);

		JScrollPane scroll;
		String[] headers = "Mã HD;Ngày lập;Mã Thuốc;Tên Thuốc; Số Lượng;Tổng tiền".split(";");
		tblModelThang = new DefaultTableModel(headers, 0);
		add(scroll = new JScrollPane(tbdthThang = new JTable(tblModelThang), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
		tbdthThang.setRowHeight(20);
		scroll.setBounds(5, 100, 1350, 300);

		pnldthuNgay.add(scroll);

		pnldthuNgay.add(lbdthu= new JLabel("Tổng doanh thu trong Tháng:"));
		pnldthuNgay.add(dthuThang=new JTextField());
		btnxuatbc.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					XSSFWorkbook workbook = new XSSFWorkbook();
					XSSFSheet spreadsheet = workbook.createSheet("Doanhthuthang");

					XSSFRow row = null;
					Cell cell = null;

					row = spreadsheet.createRow((short) 1);
					row.setHeight((short) 500);
					cell = row.createCell(2, CellType.STRING);
					cell.setCellValue("NHÀ THUỐC NGUYỄN VĂN BẢO 2");
					row = spreadsheet.createRow((short) 2);
					row.setHeight((short) 500);
					cell=row.createCell(0, CellType.STRING);
					cell.setCellValue("DOANH THU THEO THÁNG"+txtThang.getText()+"/"+txtNam.getText());

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


					FileOutputStream out = new FileOutputStream(new File("D:/doanhthuthang.xlsx"));
					workbook.write(out);
					out.close();
					Thongbao.thongbao("Xuất báo cáo thành công!", "Thông báo");
				} catch (Exception ex) {
					ex.printStackTrace();
				}

			}
		});
		btnThang.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(valid()==true) {
					tblModelThang = (DefaultTableModel) tbdthThang.getModel();
					tblModelThang.getDataVector().removeAllElements();
					double tongDTth=0.0;
					try {
						Connection con = DatabaseConnect.getInstance().connect();
						Statement stmt = con.createStatement();
						String sql = "select ct.maHD as MaHD, h.ngayLap as NgayLap,th.maThuoc as MaThuoc, th.tenThuoc as TenThuoc,\r\n"
								+ "ct.soluong, SUM (th.giaBan*ct.soluong) as Tongtien\r\n"
								+ "from Thuoc th join ChiTietHoaDon ct on th.maThuoc=ct.maThuoc join HoaDon h on ct.maHD=h.maHD\r\n"
								+ "where MONTH(ngayLap) in('"+txtThang.getText()+"') "
								+ "and YEAR(ngaylap) in('"+txtNam.getText()+"')\r\n"
								+ "group by ct.maHD, h.ngayLap, th.maThuoc, ct.soluong, th.tenThuoc";
						ResultSet rs = stmt.executeQuery(sql);


						while(rs.next()) {

							int mahd= rs.getInt(1);
							java.util.Date ngay = rs.getDate(2);
							String mathuoc = rs.getString(3);
							String tenth = rs.getString(4);
							int soluong = rs.getInt(5);
							Double tong = rs.getDouble(6);
							tongDTth+=rs.getDouble(6);
							String [] row = {mahd+"",ngay+"",mathuoc, tenth,soluong+"", tong+""};

							tblModelThang.addRow(row);

						}

						tbdthThang.setModel(tblModelThang);
					} catch (Exception ex) {
						ex.printStackTrace();
					}

					dthuThang.setText(tongDTth+"");

				}
			}
		});

		dthuThang.setEditable(false);
		dthuThang.setFont(new Font("Arial",Font.BOLD, 14));
		dthuThang.setForeground(Color.RED);
		lbdthu.setBounds(900, 510, 200, 100);
		dthuThang.setBounds(1100, 545, 150, 30);
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
					+ "where MONTH(ngayLap) in('"+txtThang.getText()+"') "
					+ "and YEAR(ngaylap) in('"+txtNam.getText()+"')\r\n"
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
	public boolean valid() {
		if(txtNam.getText().equals("")||txtThang.getText().equals("")) {
			Thongbao.thongbao("Mời bạn nhập đầy đủ thông tin", "Thông báo");
			return false;
		}
		return true;
	}

}
