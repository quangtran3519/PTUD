
package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import dao.DatabaseConnect;
import dao.HoaDonDao;
import entity.NhanVien;

//import Database.Data;
//import entity.NhanVien;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class Form_ThongKeDoanhSo extends JFrame {

	private DefaultTableModel tblMdTHHan, tblMdTTK, tblMdTHHang;
	private JTable tblTHHan, tblTTK, tblTHHang;
	private JLabel lblfrom,lblto;
	private JTextField txtFrom,txtTo;
	private JButton btnLoc;
	private JPanel pnlDsnv,pnlDsngay;
	private DefaultCategoryDataset dataset1 = new DefaultCategoryDataset();
	private DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();
	public Form_ThongKeDoanhSo() {
		setTitle("Thống kê Doanh số");
		setSize(1000, 700);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(false);

		JTabbedPane tbbPane = new JTabbedPane();

		 pnlDsnv = createPnlSDnv();
		 pnlDsngay = createPnlDSngay();	
		tbbPane.addTab("Doanh số ngày", null, pnlDsnv);
		tbbPane.addTab("Doanh số  nhân viên", null, pnlDsngay);
		lblfrom = new JLabel("Từ ngày:");
		txtFrom = new JTextField(10);
		lblto = new JLabel("đến ngày");
		txtTo = new JTextField(10);
		btnLoc = new JButton("Tìm kiếm");
		 JPanel x1 = new JPanel();
		 	x1.add(lblfrom);
			x1.add(txtFrom);
			x1.add(lblto);
			x1.add(txtTo);
			x1.add(btnLoc);
		add(x1,BorderLayout.NORTH);
		//add(pnorth);
		//tbbPane.addTab("", null, pnlThuocHetHang);
		add(tbbPane);
		HoaDonDao hoaDonDao = new HoaDonDao();
		btnLoc.addActionListener(new ActionListener() {
					
				@Override
				public void actionPerformed(ActionEvent e) {
					if (pnlDsnv.isFocusable()) {
						if (txtTo.getText().equals("")&& txtFrom.getText().equals("")) {
							for(int i=0;i<= dataset1.getRowCount()-1 ;i++) {
								dataset1.removeRow(i);
							}
							Map<Date, Double> map = hoaDonDao.layDoanhThuTheoNgay();
							map.forEach((a,b)-> dataset1.addValue(b, "Số tiền", a+""));
							
						}else {
							if (validinput()) {
								for(int i=0;i<= dataset1.getRowCount()-1 ;i++) {
									dataset1.removeRow(i);
								}
								
								Map<Date, Double> map = hoaDonDao.layDoanhThuTheoNgay(txtFrom.getText(), txtTo.getText());
								map.forEach((a,b)-> dataset1.addValue(b, "Số tiền", a+""));
							}
						}	 
					}
					if (pnlDsngay.isFocusable()) {
						if (txtTo.getText().equals("")&& txtFrom.getText().equals("")) {
							for(int i=0;i<= dataset2.getRowCount()-1 ;i++) {
								dataset2.removeRow(i);
							}
							Map<NhanVien, Double> map = hoaDonDao.layDoanhThuTheoNV();
							map.forEach((a,b)-> dataset2.addValue(b, "Số tiền", a.getTenNV()+""));
							
						}else {
							if (validinput()) {
								for(int i=0;i<= dataset2.getRowCount()-1 ;i++) {
									dataset2.removeRow(i);
								}
								Map<NhanVien, Double> map = hoaDonDao.layDoanhThuTheoNV(txtFrom.getText(), txtTo.getText());
								map.forEach((a,b)-> dataset2.addValue(b, "Số tiền", a.getTenNV()+""));
							}
					}
					}
				}		
			});
	}
	private boolean validinput() {
		if (!txtTo.getText().matches("[0-9]{4}[/][0-9]{2}[/][0-9]{2}")) {
			JOptionPane.showMessageDialog(null, "hãy nhập đúng định dạng năm/tháng/ngày");
			return false;
		}
		if (!txtFrom.getText().matches("[0-9]{4}[/][0-9]{2}[/][0-9]{2}")) {
			JOptionPane.showMessageDialog(null, " hãy nhập đúng định dạng năm/tháng/ngày");
			return false;
		}
		return true;
	}
	private JPanel createPnlSDnv() {
		 JPanel x = new JPanel();
		 JFreeChart barChart = ChartFactory.createBarChart(
	                "Doanh thu  ngày",
	                "Ngày", "Số tiền",
	               dataset1, PlotOrientation.VERTICAL, true, true, true);
		 ChartPanel chartPanel = new ChartPanel(barChart);
		 x.add(chartPanel,BorderLayout.CENTER);
		 chartPanel.setPreferredSize(new java.awt.Dimension(1100, 500));
	
		 return x;

	}

	private JPanel createPnlDSngay() {
		
		HoaDonDao hoaDonDao = new HoaDonDao();
//		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//		Map<NhanVien, Double> map = hoaDonDao.layDoanhThuTheoNV("2020/12/05", "2020/12/15");
//		map.forEach((a,b)-> dataset.addValue(b, "Số tiền", a.getTenNV()+""));
		 JFreeChart barChart = ChartFactory.createBarChart(
	                "Doanh thu  Nhân viên",
	                "Nhân viên", "Số tiền",
	               dataset2, PlotOrientation.VERTICAL, true, true, true);
		 ChartPanel chartPanel = new ChartPanel(barChart);
	
		 JPanel x = new JPanel();	
		 x.add(chartPanel,BorderLayout.CENTER);
		 x.add(chartPanel);
		 chartPanel.setPreferredSize(new java.awt.Dimension(1100, 500));
		 return x;
	}	
//			public static void main (String [] args) {
//				new Form_ThongKeDoanhSo();
//			}

		
}
