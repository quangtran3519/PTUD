package ui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.event.ActionEvent;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JLayeredPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import net.sf.jasperreports.compilers.JavaScriptCallableThisDecorator;

public class Form_QuanLy extends JFrame {
	private Container contain;
	public Form_QuanLy() {
		setTitle("QUẢN LÝ NHÀ THUỐC_Quản lý");
		//		setSize(1100, 700);
		setLocationRelativeTo(null);
		setExtendedState(MAXIMIZED_BOTH);
		contain = this.getContentPane();
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnHeThong = new JMenu("Hệ thống");
		menuBar.add(mnHeThong);

		JMenuItem mntmDoiMK = new JMenuItem("Đổi mật khẩu");
		mntmDoiMK.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				new Frm_DoiMatKhau().setVisible(true);

			}
		});
		mnHeThong.add(mntmDoiMK);
		JMenuItem mntmDX = new JMenuItem("Đăng xuất");
		mntmDX.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				hienThiDangNhap();

			}

		});
		
		mnHeThong.add(mntmDX);
		JMenuItem mntmThoat = new JMenuItem("Thoát");
		mntmThoat.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);

			}
		});
		mnHeThong.add(mntmThoat);
		JMenu mnDanhMuc = new JMenu("Danh Mục");
		menuBar.add(mnDanhMuc);

		JMenuItem mntmQLNV = new JMenuItem("Nhân viên");
		mntmQLNV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doiPanel(new FrNhanVien().getContentPane());
			}
		});
		mnDanhMuc.add(mntmQLNV);

		JMenuItem mntmQLCD = new JMenuItem("Thuốc");
		mntmQLCD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doiPanel(new QuanLyThuoc().getContentPane());
			}
		});
		mnDanhMuc.add(mntmQLCD);
		JMenuItem mntmQLKH = new JMenuItem("Khách hàng");
		mntmQLKH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doiPanel(new FrKhachHang().getContentPane());
			}


		});
		mnDanhMuc.add(mntmQLKH);


		JMenuItem mntmQLPhieuThue = new JMenuItem(" Hóa đơn");
		mnDanhMuc.add(mntmQLPhieuThue);
		mntmQLPhieuThue.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				doiPanel(new Frm_QuanLyHoaDon().getContentPane());

			}
		});


		JMenu mnHoaDon = new JMenu("Xử lý");
		menuBar.add(mnHoaDon);

		JMenuItem mntmPhieuThue = new JMenuItem(" Bán thuốc");
		mnHoaDon.add(mntmPhieuThue);
		mntmPhieuThue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doiPanel(new Frm_TaoHoaDon().getContentPane());
			}
		});


		//
		JMenu mnTimkiem = new JMenu("Tìm kiếm");
		menuBar.add(mnTimkiem);

		JMenuItem mntmTimKH = new JMenuItem("Tìm khách hàng");
		mnTimkiem.add(mntmTimKH);
		mntmTimKH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doiPanel(new FrSearchKH().getContentPane());
			}
		});
		JMenuItem mntmtimCd = new JMenuItem("Tìm Thuốc");
		mnTimkiem.add(mntmtimCd);
		mntmtimCd.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				doiPanel(new FormTimKiemThuoc().getContentPane());

			}
		});
		JMenuItem mntmtimNv = new JMenuItem("Tìm nhân viên");
		mnTimkiem.add(mntmtimNv);
		mntmtimNv.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				doiPanel(new TimKiemNhanVien().getContentPane());

			}
		});
		JMenuItem mntmtimHD = new JMenuItem("Tìm hóa đơn");
		mnTimkiem.add(mntmtimHD);
		mntmtimHD.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				doiPanel(new Form_TimHoaDon().getContentPane());

			}
		});
		JMenu mnThongKe = new JMenu("Thống kê");
		menuBar.add(mnThongKe);

		JMenuItem mntmTieuchi = new JMenuItem("Thống Kê Thuốc");
		mnThongKe.add(mntmTieuchi);
		mntmTieuchi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				doiPanel(new ThongKeThuoc().getContentPane());
			
			}
		});
		JMenuItem mntmTkDoanhTHu = new JMenuItem("Thống Kê Doanh Thu");
		mnThongKe.add(mntmTkDoanhTHu);
		mntmTkDoanhTHu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				doiPanel(new Form_ThongKeDoanhSo().getContentPane());
			
			}
		});
		
		JMenu mntmBaoCao= new JMenu("Báo cáo");
		menuBar.add(mntmBaoCao);

		JMenuItem mntmBcao = new JMenuItem("Báo cáo doanh thu theo ngày");
		mntmBaoCao.add(mntmBcao);
		mntmBcao.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				doiPanel(new BaoCaoDoanhThuNgay().getContentPane());
			
			}
		});
		JMenuItem mntmBcaothang = new JMenuItem("Báo cáo doanh thu theo tháng");
		mntmBaoCao.add(mntmBcaothang);
		mntmBcaothang.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				doiPanel(new BaoCaoDoanhThuThang().getContentPane());
			
			}
		});
		JMenuItem mntmBcaoNam = new JMenuItem("Báo cáo doanh thu theo năm");
		mntmBaoCao.add(mntmBcaoNam);
		mntmBcaoNam.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				doiPanel(new BaoCaoDoanhThuNam().getContentPane());
			
			}
		});
		JMenuItem mntmBcaoQuy = new JMenuItem("Báo cáo doanh thu theo Quý");
		mntmBaoCao.add(mntmBcaoQuy);
		mntmBcaoQuy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				doiPanel(new BaoCaoDTQuy().getContentPane());
			
			}
		});
		mntmBaoCao.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				doiPanel(new ThongKeThuoc().getContentPane());

			}
		});
		
		JLayeredPane layeredPane = new JLayeredPane();

		JLabel label = new JLabel();
		label.setForeground(UIManager.getColor("CheckBox.foreground"));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBackground(Color.GRAY);
		label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HinhAnh/AnhNen.jpg")));

		getContentPane().add(label);
		layeredPane.setBounds(0, 0, 700, 550);
		getContentPane().add(layeredPane, BorderLayout.WEST);
		layeredPane.setLayout(null);
		JLabel lblNewLabel = new JLabel("PHẦN MỀM QUẢN LÝ NHÀ THUỐC",JLabel.CENTER);

		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 25));
		lblNewLabel.setForeground(Color.blue);

		getContentPane().add(lblNewLabel, BorderLayout.NORTH);

	}
	protected void EXIT_ON_CLOSE() {
		System.exit(1);

	}
	private void hienThiDangNhap() {
		this.dispose();
		new Frm_UserLogin();
	}
	private void doiPanel(Container container) {
		getContentPane().removeAll();
		getContentPane().add(container);
		revalidate();
		repaint();

	}

	public static void main(String[] args) {
		new Form_QuanLy().setVisible(true);
	}
}

