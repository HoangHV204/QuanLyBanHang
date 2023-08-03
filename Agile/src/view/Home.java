/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.NhanVien;
import model.SanPham;
import service.PosService;

/**
 *
 * @author LE MINH
 */
public class Home extends javax.swing.JFrame {
    
    private final PosService service = new PosService();
    private DefaultTableModel tableModelNhanVien = new DefaultTableModel();
    private DefaultTableModel tableModelSanPham = new DefaultTableModel();

    /**
     * Creates new form Home
     *
     * @param maNhanVien
     */
    public Home(String maNhanVien) {
        initComponents();
        this.setLocationRelativeTo(null);
        setVisibleAllPanel();
        Home1.setVisible(true);
        setDataPanelHome(maNhanVien);
        separateRoleWhenLogin(maNhanVien);
        //Định dạng table
        tableModelNhanVien = (DefaultTableModel) tblNhanVien.getModel();
        showDataNhanVien(service.getAllNhanVien());
        tableModelSanPham = (DefaultTableModel) tbSanPham.getModel();
        showAllSanPhamToTable();
    }
    
    private void separateRoleWhenLogin(String maNV) {
        if (service.getRole(maNV).equalsIgnoreCase("user")) {
            btnThem_NhanVien.setEnabled(false);
            btnSua_NhanVien.setEnabled(false);
            btnXoa_NhanVien.setEnabled(false);
            btnLuu_NhanVien.setEnabled(false);
        }
    }
    
    private void setVisibleAllPanel() {
        Home1.setVisible(false);
        SanPham.setVisible(false);
        HoaDon.setVisible(false);
        LichSuHoaDon.setVisible(false);
        DoanhThu.setVisible(false);
        NhanVien.setVisible(false);
        KhachHang.setVisible(false);
    }
    
    private void setDataPanelHome(String maNV) {
        NhanVien nv = service.getNhanVien(maNV);
        txtMaNhanVienHome.setText(nv.getMaNV());
        txtTenNhanVienHome.setText(nv.getTenNV());
        String image = "/icon/" + nv.getImage();
        ImageIcon icon = new ImageIcon(getClass().getResource(image));
        avatarHome.setIcon(icon);
        separateRoleWhenLogin(maNV);
    }
    
    private void showDataNhanVien(List<NhanVien> list) {
        tableModelNhanVien.setRowCount(0);
        for (NhanVien nv : list) {
            tableModelNhanVien.addRow(new Object[]{
                nv.getMaNV(),
                nv.getTenNV(),
                nv.isGioiTinh() == true ? "Nam" : "Nữ",
                nv.getNgaySinh(),
                nv.getDiaChi(),
                nv.getSdt()
            });
        }
    }
    
    private void showDetailNhanVien(int index) {
        NhanVien nv = service.getAllNhanVien().get(index);
        txtMa_NhanVien.setText(nv.getMaNV());
        txtTen_NhanVien.setText(nv.getTenNV());
        dateChooser_NhanVien.setDate(nv.getNgaySinh());
        txtSdt_NhanVien.setText(nv.getSdt());
        txtDiaChi_NhanVien.setText(nv.getDiaChi());
        if (nv.isGioiTinh()) {
            rdNam_NhanVien.setSelected(true);
        } else {
            rdNu_NhanVien.setSelected(true);
        }
        String image = "/icon/" + nv.getImage();
        ImageIcon icon = new ImageIcon(getClass().getResource(image));
        avatarNhanVien.setIcon(icon);
    }
    
    private NhanVien getDataFormNhanVien() {
        String maNV = txtMa_NhanVien.getText().trim();
        if (maNV.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã nhân viên");
            return null;
        }
        String ten = txtTen_NhanVien.getText().trim();
        if (ten.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên nhân viên");
            return null;
        }
        String sdt = txtSdt_NhanVien.getText().trim();
        if (sdt.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số điện thoại");
            return null;
        } else if (!sdt.matches("0\\d{9}")) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không đúng định dạng");
            return null;
        }
        String diaChi = txtDiaChi_NhanVien.getText().trim();
        if (diaChi.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập địa chỉ");
            return null;
        }
        if (rdNam_NhanVien.isSelected() == false && rdNu_NhanVien.isSelected() == false) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn giới tính");
            return null;
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String ngaySinh = dateFormat.format(dateChooser_NhanVien.getDate());
        boolean gioiTinh = rdNam_NhanVien.isSelected();
        String image = "girl.png";
        if (gioiTinh) {
            image = "boy.png";
        }
        return new NhanVien(maNV, ten, gioiTinh, Date.valueOf(ngaySinh), diaChi, sdt, image);
    }
    
    private void clearFormNhanVien() {
        txtMa_NhanVien.setText("");
        txtDiaChi_NhanVien.setText("");
        txtSdt_NhanVien.setText("");
        txtTen_NhanVien.setText("");
        txtTim_NhanVien.setText("");
    }
    
    private void showAllSanPhamToTable() {
        tableModelSanPham.setRowCount(0);
        for (SanPham sp : service.getAllSanPham()) {
            tableModelSanPham.addRow(new Object[]{
                sp.getMaSanPham(),
                sp.getTenSanPham(),
                sp.getLoai(),
                sp.getSoLuong(),
                sp.getGiaBan()
            });
        }
    }
    
    private void showDetailSanPham(int index) {
        SanPham sp = service.getAllSanPham().get(index);
        txtMaSanPham.setText(sp.getMaSanPham());
        txtTenSanPham.setText(sp.getTenSanPham());
        cbLoai.setSelectedItem(sp.getLoai());
        txtSoLuongSanPham.setText(String.valueOf(sp.getSoLuong()));
        txtGiaSanPham.setText(String.valueOf(sp.getGiaBan()));
        if (sp.getTrangThai().equalsIgnoreCase("Còn")) {
            rdConSanPham.setSelected(true);
        } else {
            rdHetSanPham.setSelected(true);
        }
        String image = "/icon/" + sp.getImage();
        ImageIcon icon = new ImageIcon(getClass().getResource(image));
        imageSanPham.setIcon(icon);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnHome = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        btnHoaDon = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        btnSanPham = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        btnNhanVien = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        btnLichSuHoaDon = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        btnKhachHang = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        btnDoanhThu = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        Home1 = new javax.swing.JPanel();
        panelRound1 = new custom.PanelRound();
        avatarHome = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtTenNhanVienHome = new javax.swing.JTextField();
        txtMaNhanVienHome = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jLabel53 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        SanPham = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        txtTimSanPham = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        txtMaSanPham = new javax.swing.JTextField();
        txtTenSanPham = new javax.swing.JTextField();
        txtGiaSanPham = new javax.swing.JTextField();
        cbLoai = new javax.swing.JComboBox<>();
        rdConSanPham = new javax.swing.JRadioButton();
        rdHetSanPham = new javax.swing.JRadioButton();
        btnThemSanPham = new javax.swing.JButton();
        btnLuuSanPham = new javax.swing.JButton();
        btnXoaSanPham = new javax.swing.JButton();
        btnSuaSanPham = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbSanPham = new javax.swing.JTable();
        btnSearchSanPham = new view.MyButton();
        jPanel11 = new javax.swing.JPanel();
        imageSanPham = new javax.swing.JLabel();
        txtSoLuongSanPham = new javax.swing.JTextField();
        LichSuHoaDon = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        DoanhThu = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        HoaDon = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jLabel31 = new javax.swing.JLabel();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jRadioButton5 = new javax.swing.JRadioButton();
        jRadioButton6 = new javax.swing.JRadioButton();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jButton15 = new javax.swing.JButton();
        panelRound2 = new custom.PanelRound();
        jLabel41 = new javax.swing.JLabel();
        NhanVien = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtMa_NhanVien = new javax.swing.JTextField();
        txtTen_NhanVien = new javax.swing.JTextField();
        txtSdt_NhanVien = new javax.swing.JTextField();
        txtDiaChi_NhanVien = new javax.swing.JTextField();
        rdNam_NhanVien = new javax.swing.JRadioButton();
        rdNu_NhanVien = new javax.swing.JRadioButton();
        panelRound3 = new custom.PanelRound();
        avatarNhanVien = new javax.swing.JLabel();
        txtTim_NhanVien = new javax.swing.JTextField();
        btnThem_NhanVien = new javax.swing.JButton();
        btnLuu_NhanVien = new javax.swing.JButton();
        btnSua_NhanVien = new javax.swing.JButton();
        btnXoa_NhanVien = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblNhanVien = new javax.swing.JTable();
        btnSearch_NhanVien = new view.MyButton();
        dateChooser_NhanVien = new com.toedter.calendar.JDateChooser();
        KhachHang = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jTextField13 = new javax.swing.JTextField();
        jTextField14 = new javax.swing.JTextField();
        jTextField15 = new javax.swing.JTextField();
        jTextField16 = new javax.swing.JTextField();
        jRadioButton9 = new javax.swing.JRadioButton();
        jRadioButton10 = new javax.swing.JRadioButton();
        panelRound4 = new custom.PanelRound();
        jLabel58 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable7 = new javax.swing.JTable();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jTextField17 = new javax.swing.JTextField();
        myButton2 = new view.MyButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1365, 697));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(253, 8, 8));

        jPanel2.setBackground(new java.awt.Color(253, 8, 8));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-home-30.png"))); // NOI18N
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btnHome.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnHome.setForeground(new java.awt.Color(255, 255, 255));
        btnHome.setText("Home");
        btnHome.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHomeMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jLabel1)
                .addGap(34, 34, 34)
                .addComponent(btnHome, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnHome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(38, 38, 38))
        );

        jPanel3.setBackground(new java.awt.Color(253, 8, 8));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-bill-30 (1).png"))); // NOI18N
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btnHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnHoaDon.setForeground(new java.awt.Color(255, 255, 255));
        btnHoaDon.setText("Hoá Đơn");
        btnHoaDon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHoaDonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jLabel3)
                .addGap(34, 34, 34)
                .addComponent(btnHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(38, 38, 38))
        );

        jPanel4.setBackground(new java.awt.Color(253, 8, 8));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-bill-30.png"))); // NOI18N
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btnSanPham.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnSanPham.setForeground(new java.awt.Color(255, 255, 255));
        btnSanPham.setText("Sản Phẩm");
        btnSanPham.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSanPhamMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jLabel5)
                .addGap(33, 33, 33)
                .addComponent(btnSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(253, 8, 8));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-employee-30.png"))); // NOI18N
        jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btnNhanVien.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnNhanVien.setForeground(new java.awt.Color(255, 255, 255));
        btnNhanVien.setText("Nhân Viên");
        btnNhanVien.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnNhanVienMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jLabel7)
                .addGap(34, 34, 34)
                .addComponent(btnNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(38, 38, 38))
        );

        jPanel6.setBackground(new java.awt.Color(253, 8, 8));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-time-machine-30.png"))); // NOI18N
        jLabel9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btnLichSuHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnLichSuHoaDon.setForeground(new java.awt.Color(255, 255, 255));
        btnLichSuHoaDon.setText("Lịch sử hoá đơn");
        btnLichSuHoaDon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLichSuHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLichSuHoaDonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jLabel9)
                .addGap(34, 34, 34)
                .addComponent(btnLichSuHoaDon)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLichSuHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(38, 38, 38))
        );

        jPanel7.setBackground(new java.awt.Color(253, 8, 8));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-group-30.png"))); // NOI18N
        jLabel11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btnKhachHang.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnKhachHang.setForeground(new java.awt.Color(255, 255, 255));
        btnKhachHang.setText("Khách hàng");
        btnKhachHang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnKhachHangMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jLabel11)
                .addGap(34, 34, 34)
                .addComponent(btnKhachHang)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(38, 38, 38))
        );

        jPanel8.setBackground(new java.awt.Color(253, 8, 8));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-bonds-30.png"))); // NOI18N
        jLabel13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btnDoanhThu.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnDoanhThu.setForeground(new java.awt.Color(255, 255, 255));
        btnDoanhThu.setText("Doanh Thu");
        btnDoanhThu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDoanhThu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDoanhThuMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jLabel13)
                .addGap(34, 34, 34)
                .addComponent(btnDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(55, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDoanhThu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(38, 38, 38))
        );

        jPanel9.setBackground(new java.awt.Color(255, 102, 102));
        jPanel9.setLayout(new javax.swing.OverlayLayout(jPanel9));

        Home1.setBackground(new java.awt.Color(217, 217, 217));

        panelRound1.setBackground(new java.awt.Color(255, 255, 255));
        panelRound1.setRoundBottomLeft(60);
        panelRound1.setRoundBottomRight(60);
        panelRound1.setRoundTopLeft(60);
        panelRound1.setRoundTopRight(60);

        avatarHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/girl.png"))); // NOI18N

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(avatarHome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(avatarHome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel18.setText("Mã Nhân Viên:");

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel19.setText("Tên Nhân Viên:");

        txtTenNhanVienHome.setEditable(false);

        txtMaNhanVienHome.setEditable(false);

        jPanel10.setBackground(new java.awt.Color(242, 79, 206));

        jLabel53.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/banner (1).jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel53, javax.swing.GroupLayout.DEFAULT_SIZE, 1094, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel53, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel20.setText("Giờ");

        jLabel48.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-time-machine-30.png"))); // NOI18N

        jLabel49.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel49.setText("Ngày");

        jLabel50.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-date-30.png"))); // NOI18N

        javax.swing.GroupLayout Home1Layout = new javax.swing.GroupLayout(Home1);
        Home1.setLayout(Home1Layout);
        Home1Layout.setHorizontalGroup(
            Home1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(Home1Layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(Home1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Home1Layout.createSequentialGroup()
                        .addGap(169, 169, 169)
                        .addGroup(Home1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Home1Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(Home1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Home1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(txtMaNhanVienHome, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(Home1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTenNhanVienHome, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(Home1Layout.createSequentialGroup()
                        .addGap(678, 678, 678)
                        .addGroup(Home1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Home1Layout.createSequentialGroup()
                                .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel49))
                            .addGroup(Home1Layout.createSequentialGroup()
                                .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel20)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Home1Layout.setVerticalGroup(
            Home1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Home1Layout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(111, 111, 111)
                .addGroup(Home1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Home1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(Home1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Home1Layout.createSequentialGroup()
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(51, 51, 51)
                                .addGroup(Home1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTenNhanVienHome, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(Home1Layout.createSequentialGroup()
                        .addComponent(txtMaNhanVienHome, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(156, 156, 156)
                        .addComponent(jLabel49)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Home1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20)
                    .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(54, Short.MAX_VALUE))
        );

        jPanel9.add(Home1);

        SanPham.setBackground(new java.awt.Color(217, 217, 217));

        jLabel15.setBackground(new java.awt.Color(255, 0, 0));
        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 51, 51));
        jLabel15.setText("QUẢN LÝ ĐIỆN THOẠI");

        jLabel16.setBackground(new java.awt.Color(0, 0, 0));
        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel16.setText("Thông tin sản phẩm:");

        jLabel17.setText("Mã SP:");

        jLabel21.setText("Tên SP:");

        jLabel22.setText("Loại:");

        jLabel23.setText("Số lượng:");

        jLabel24.setText("Giá:");

        jLabel25.setText("Trạng thái:");

        cbLoai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "new", "like new", " " }));

        buttonGroup4.add(rdConSanPham);
        rdConSanPham.setText("Còn");

        buttonGroup4.add(rdHetSanPham);
        rdHetSanPham.setText("Hết");

        btnThemSanPham.setText("Thêm");

        btnLuuSanPham.setText("Lưu");

        btnXoaSanPham.setText("Xoá");

        btnSuaSanPham.setText("Sửa");

        tbSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Hãng", "Tên sản phẩm", "Loại", "Số lượng", "Giá bán"
            }
        ));
        tbSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbSanPhamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbSanPham);

        btnSearchSanPham.setBackground(new java.awt.Color(0, 0, 0));
        btnSearchSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-magnifying-glass-38.png"))); // NOI18N
        btnSearchSanPham.setBorderColor(new java.awt.Color(0, 0, 0));
        btnSearchSanPham.setRadius(38);

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imageSanPham, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imageSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout SanPhamLayout = new javax.swing.GroupLayout(SanPham);
        SanPham.setLayout(SanPhamLayout);
        SanPhamLayout.setHorizontalGroup(
            SanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SanPhamLayout.createSequentialGroup()
                .addContainerGap(111, Short.MAX_VALUE)
                .addComponent(txtTimSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 933, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
            .addGroup(SanPhamLayout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(btnSearchSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(299, 299, 299)
                .addComponent(jLabel15)
                .addContainerGap(444, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SanPhamLayout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(SanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(SanPhamLayout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtSoLuongSanPham))
                    .addGroup(SanPhamLayout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addGap(42, 42, 42)
                        .addComponent(txtGiaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(SanPhamLayout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addGap(29, 29, 29)
                        .addComponent(rdConSanPham)
                        .addGap(29, 29, 29)
                        .addComponent(rdHetSanPham))
                    .addGroup(SanPhamLayout.createSequentialGroup()
                        .addComponent(btnThemSanPham)
                        .addGap(43, 43, 43)
                        .addComponent(btnLuuSanPham)
                        .addGap(40, 40, 40)
                        .addComponent(btnSuaSanPham)
                        .addGap(49, 49, 49)
                        .addComponent(btnXoaSanPham))
                    .addGroup(SanPhamLayout.createSequentialGroup()
                        .addGroup(SanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addComponent(jLabel21)
                            .addComponent(jLabel22))
                        .addGap(25, 25, 25)
                        .addGroup(SanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTenSanPham)
                            .addComponent(txtMaSanPham)
                            .addComponent(cbLoai, 0, 399, Short.MAX_VALUE)))
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(158, 158, 158))
            .addGroup(SanPhamLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        SanPhamLayout.setVerticalGroup(
            SanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SanPhamLayout.createSequentialGroup()
                .addGroup(SanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(SanPhamLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(SanPhamLayout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(SanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtTimSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSearchSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(SanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(SanPhamLayout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE))
                            .addGroup(SanPhamLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(SanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel17)
                                    .addComponent(txtMaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(SanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel21)
                                    .addComponent(txtTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(SanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel22)
                                    .addComponent(cbLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(9, 9, 9)
                                .addGroup(SanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel23)
                                    .addComponent(txtSoLuongSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(SanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel24)
                                    .addComponent(txtGiaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(SanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel25)
                                    .addComponent(rdConSanPham)
                                    .addComponent(rdHetSanPham))
                                .addGap(27, 27, 27)
                                .addGroup(SanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnThemSanPham)
                                    .addComponent(btnLuuSanPham)
                                    .addComponent(btnSuaSanPham)
                                    .addComponent(btnXoaSanPham))
                                .addGap(18, 18, 18)))))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65))
        );

        jPanel9.add(SanPham);

        LichSuHoaDon.setBackground(new java.awt.Color(217, 217, 217));

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 0, 0));
        jLabel26.setText("LỊCH SỬ BÁN HÀNG");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "HÃNG", "TÊN SẢN PHẨM", "LOẠI", "SỐ LƯỢNG", "GIÁ BÁN"
            }
        ));
        jScrollPane2.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(4).setResizable(false);
        }

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "HÃNG", "TÊN SẢN PHẨM", "LOẠI", "SỐ LƯỢNG", "GIÁ BÁN"
            }
        ));
        jScrollPane3.setViewportView(jTable3);
        if (jTable3.getColumnModel().getColumnCount() > 0) {
            jTable3.getColumnModel().getColumn(4).setResizable(false);
        }

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel27.setText("18/07/2023");

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel28.setText("19/07/2023");

        jLabel44.setText("Tổng:");

        jLabel45.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel45.setText("1,000,000 VNĐ");

        jLabel46.setText("Tổng:");

        jLabel47.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel47.setText("1,000,000 VNĐ");

        javax.swing.GroupLayout LichSuHoaDonLayout = new javax.swing.GroupLayout(LichSuHoaDon);
        LichSuHoaDon.setLayout(LichSuHoaDonLayout);
        LichSuHoaDonLayout.setHorizontalGroup(
            LichSuHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LichSuHoaDonLayout.createSequentialGroup()
                .addContainerGap(65, Short.MAX_VALUE)
                .addGroup(LichSuHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel28)
                    .addGroup(LichSuHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LichSuHoaDonLayout.createSequentialGroup()
                            .addComponent(jLabel27)
                            .addGap(953, 953, 953))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LichSuHoaDonLayout.createSequentialGroup()
                            .addComponent(jLabel26)
                            .addGap(404, 404, 404)))
                    .addGroup(LichSuHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(LichSuHoaDonLayout.createSequentialGroup()
                            .addComponent(jLabel44)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 954, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(LichSuHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(LichSuHoaDonLayout.createSequentialGroup()
                            .addComponent(jLabel46)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 959, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        LichSuHoaDonLayout.setVerticalGroup(
            LichSuHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LichSuHoaDonLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(jLabel27)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(LichSuHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44)
                    .addComponent(jLabel45))
                .addGap(18, 18, 18)
                .addComponent(jLabel28)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(LichSuHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46)
                    .addComponent(jLabel47))
                .addContainerGap(134, Short.MAX_VALUE))
        );

        jPanel9.add(LichSuHoaDon);

        DoanhThu.setBackground(new java.awt.Color(217, 217, 217));

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 0, 0));
        jLabel29.setText("BÁO CÁO KẾT QUẢ KINH DOANH");

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "THÁNG", "DOANH THU", "CHIẾT KHẤU", "LỢI NHUẬN", "THUẾ", "PHÍ GIAO HÀNG", "VOUCHER", "TỒN KHO"
            }
        ));
        jScrollPane4.setViewportView(jTable4);

        jButton13.setText("Sửa");

        jButton14.setText("Xoá");

        javax.swing.GroupLayout DoanhThuLayout = new javax.swing.GroupLayout(DoanhThu);
        DoanhThu.setLayout(DoanhThuLayout);
        DoanhThuLayout.setHorizontalGroup(
            DoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DoanhThuLayout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addGroup(DoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DoanhThuLayout.createSequentialGroup()
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(329, 329, 329))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DoanhThuLayout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 1034, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DoanhThuLayout.createSequentialGroup()
                        .addComponent(jButton13)
                        .addGap(43, 43, 43)
                        .addComponent(jButton14)
                        .addGap(122, 122, 122))))
        );
        DoanhThuLayout.setVerticalGroup(
            DoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DoanhThuLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(DoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton13)
                    .addComponent(jButton14))
                .addContainerGap(127, Short.MAX_VALUE))
        );

        jPanel9.add(DoanhThu);

        HoaDon.setBackground(new java.awt.Color(217, 217, 217));

        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 0, 0));
        jLabel30.setText("THÔNG TIN HOÁ ĐƠN");

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "HÃNG", "TÊN SẢN PHẨM", "LOẠI", "SỐ LƯỢNG", "GIÁ BÁN"
            }
        ));
        jScrollPane5.setViewportView(jTable5);

        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel31.setText("Cách thức nhận hàng:");

        buttonGroup2.add(jRadioButton3);
        jRadioButton3.setText("Giao hàng tận nơi");

        buttonGroup2.add(jRadioButton4);
        jRadioButton4.setText("Nhận hàng tại cửa hàng");

        buttonGroup2.add(jRadioButton5);
        jRadioButton5.setText("Thanh toán khi nhận hàng");

        buttonGroup2.add(jRadioButton6);
        jRadioButton6.setText("Chuyển khoản");

        jLabel32.setText("MB Bank:");

        jLabel33.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel33.setText("01062004666");

        jLabel34.setText("Techcombank:");

        jLabel35.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel35.setText("01062004666");

        jButton15.setText("Thanh toán");

        jLabel41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/qr.png"))); // NOI18N

        javax.swing.GroupLayout panelRound2Layout = new javax.swing.GroupLayout(panelRound2);
        panelRound2.setLayout(panelRound2Layout);
        panelRound2Layout.setHorizontalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
        );
        panelRound2Layout.setVerticalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout HoaDonLayout = new javax.swing.GroupLayout(HoaDon);
        HoaDon.setLayout(HoaDonLayout);
        HoaDonLayout.setHorizontalGroup(
            HoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HoaDonLayout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addGroup(HoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HoaDonLayout.createSequentialGroup()
                        .addGroup(HoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(HoaDonLayout.createSequentialGroup()
                                .addGap(329, 329, 329)
                                .addComponent(jLabel30)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(HoaDonLayout.createSequentialGroup()
                                .addComponent(jLabel31)
                                .addGap(159, 159, 159)
                                .addGroup(HoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jRadioButton3)
                                    .addComponent(jRadioButton5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 231, Short.MAX_VALUE)
                                .addGroup(HoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jRadioButton6)
                                    .addComponent(jRadioButton4))))
                        .addGap(217, 217, 217))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HoaDonLayout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 1034, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25))))
            .addGroup(HoaDonLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton15)
                .addGap(52, 52, 52)
                .addComponent(panelRound2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(HoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(HoaDonLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(HoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel35)
                            .addComponent(jLabel33)
                            .addComponent(jLabel34)))
                    .addGroup(HoaDonLayout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jLabel32)))
                .addGap(246, 246, 246))
        );
        HoaDonLayout.setVerticalGroup(
            HoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HoaDonLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addGroup(HoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButton3)
                    .addComponent(jRadioButton4))
                .addGroup(HoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(HoaDonLayout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addGroup(HoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRadioButton5)
                            .addComponent(jRadioButton6))
                        .addGap(41, 41, 41)
                        .addGroup(HoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelRound2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(HoaDonLayout.createSequentialGroup()
                                .addComponent(jLabel32)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel34)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(22, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HoaDonLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton15)
                        .addGap(117, 117, 117))))
        );

        jPanel9.add(HoaDon);

        NhanVien.setBackground(new java.awt.Color(217, 217, 217));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 0, 0));
        jLabel8.setText("QUẢN LÝ NHÂN VIÊN");

        jLabel2.setText("Họ và tên:");

        jLabel4.setText("Mã Nhân Viên:");

        jLabel6.setText("Ngày sinh:");

        jLabel10.setText("Số điện thoại");

        jLabel12.setText("Địa chỉ:");

        jLabel14.setText("Giới tính:");

        buttonGroup3.add(rdNam_NhanVien);
        rdNam_NhanVien.setText("Nam");

        buttonGroup3.add(rdNu_NhanVien);
        rdNu_NhanVien.setText("Nữ");

        panelRound3.setBackground(new java.awt.Color(255, 255, 255));
        panelRound3.setRoundBottomLeft(50);
        panelRound3.setRoundBottomRight(50);
        panelRound3.setRoundTopLeft(50);
        panelRound3.setRoundTopRight(50);

        javax.swing.GroupLayout panelRound3Layout = new javax.swing.GroupLayout(panelRound3);
        panelRound3.setLayout(panelRound3Layout);
        panelRound3Layout.setHorizontalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(avatarNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
        );
        panelRound3Layout.setVerticalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(avatarNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
        );

        btnThem_NhanVien.setText("Thêm");
        btnThem_NhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem_NhanVienActionPerformed(evt);
            }
        });

        btnLuu_NhanVien.setText("Lưu");
        btnLuu_NhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuu_NhanVienActionPerformed(evt);
            }
        });

        btnSua_NhanVien.setText("Sửa");
        btnSua_NhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSua_NhanVienActionPerformed(evt);
            }
        });

        btnXoa_NhanVien.setText("Xoá");
        btnXoa_NhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoa_NhanVienActionPerformed(evt);
            }
        });

        tblNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã NV", "Họ tên", "Giới tính", "Ngày sinh", "Địa chỉ", "Số ĐT"
            }
        ));
        tblNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanVienMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tblNhanVien);

        btnSearch_NhanVien.setBackground(new java.awt.Color(0, 0, 0));
        btnSearch_NhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-magnifying-glass-38.png"))); // NOI18N
        btnSearch_NhanVien.setBorderColor(new java.awt.Color(0, 0, 0));
        btnSearch_NhanVien.setColor(new java.awt.Color(0, 0, 0));
        btnSearch_NhanVien.setRadius(38);
        btnSearch_NhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearch_NhanVienActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout NhanVienLayout = new javax.swing.GroupLayout(NhanVien);
        NhanVien.setLayout(NhanVienLayout);
        NhanVienLayout.setHorizontalGroup(
            NhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NhanVienLayout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(NhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(NhanVienLayout.createSequentialGroup()
                        .addGroup(NhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnThem_NhanVien)
                            .addGroup(NhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4)
                                .addComponent(jLabel2)
                                .addComponent(jLabel6)
                                .addComponent(jLabel10)
                                .addComponent(jLabel12)
                                .addComponent(jLabel14)))
                        .addGap(27, 27, 27)
                        .addGroup(NhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(NhanVienLayout.createSequentialGroup()
                                .addComponent(btnLuu_NhanVien)
                                .addGap(30, 30, 30)
                                .addComponent(btnSua_NhanVien)
                                .addGap(35, 35, 35)
                                .addComponent(btnXoa_NhanVien)
                                .addContainerGap())
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, NhanVienLayout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(448, 448, 448))
                            .addGroup(NhanVienLayout.createSequentialGroup()
                                .addGroup(NhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(NhanVienLayout.createSequentialGroup()
                                        .addComponent(rdNam_NhanVien)
                                        .addGap(71, 71, 71)
                                        .addComponent(rdNu_NhanVien))
                                    .addComponent(txtDiaChi_NhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE)
                                    .addComponent(txtSdt_NhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE)
                                    .addComponent(txtTen_NhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE)
                                    .addComponent(txtMa_NhanVien)
                                    .addComponent(dateChooser_NhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(NhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(NhanVienLayout.createSequentialGroup()
                                        .addGap(83, 83, 83)
                                        .addComponent(btnSearch_NhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtTim_NhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(114, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, NhanVienLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(panelRound3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(197, 197, 197))))))
                    .addGroup(NhanVienLayout.createSequentialGroup()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 962, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        NhanVienLayout.setVerticalGroup(
            NhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NhanVienLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addGroup(NhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(NhanVienLayout.createSequentialGroup()
                        .addGroup(NhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMa_NhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(NhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTen_NhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(NhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateChooser_NhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(NhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSdt_NhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(NhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDiaChi_NhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(NhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdNam_NhanVien)
                            .addComponent(rdNu_NhanVien)))
                    .addComponent(panelRound3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(NhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(NhanVienLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(NhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnSearch_NhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTim_NhanVien))
                        .addGap(57, 57, 57))
                    .addGroup(NhanVienLayout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(NhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnThem_NhanVien)
                            .addComponent(btnLuu_NhanVien)
                            .addComponent(btnSua_NhanVien)
                            .addComponent(btnXoa_NhanVien))))
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67))
        );

        jPanel9.add(NhanVien);

        KhachHang.setBackground(new java.awt.Color(217, 217, 217));

        jLabel38.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(255, 0, 0));
        jLabel38.setText("QUẢN LÍ KHÁCH HÀNG");

        jLabel39.setText("Họ và tên:");

        jLabel42.setText("Số điện thoại:");

        jLabel54.setText("Ngày sinh:");

        jLabel56.setText("Địa chỉ:");

        jLabel57.setText("Giới tính:");

        buttonGroup1.add(jRadioButton9);
        jRadioButton9.setText("Nam");

        buttonGroup1.add(jRadioButton10);
        jRadioButton10.setText("Nữ");

        panelRound4.setRoundBottomLeft(50);
        panelRound4.setRoundBottomRight(50);
        panelRound4.setRoundTopLeft(50);
        panelRound4.setRoundTopRight(50);

        jLabel58.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/boy.png"))); // NOI18N

        javax.swing.GroupLayout panelRound4Layout = new javax.swing.GroupLayout(panelRound4);
        panelRound4.setLayout(panelRound4Layout);
        panelRound4Layout.setHorizontalGroup(
            panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel58, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
        );
        panelRound4Layout.setVerticalGroup(
            panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel58, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTable7.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Họ tên", "Ngày sinh", "Địa chỉ", "Số ĐT", "Giới tính"
            }
        ));
        jScrollPane7.setViewportView(jTable7);

        jButton9.setText("Thêm");

        jButton10.setText("Lưu");

        jButton11.setText("Sửa");

        jButton12.setText("Xoá");

        myButton2.setBackground(new java.awt.Color(0, 0, 0));
        myButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-magnifying-glass-38.png"))); // NOI18N
        myButton2.setBorderColor(new java.awt.Color(0, 0, 0));
        myButton2.setRadius(38);

        javax.swing.GroupLayout KhachHangLayout = new javax.swing.GroupLayout(KhachHang);
        KhachHang.setLayout(KhachHangLayout);
        KhachHangLayout.setHorizontalGroup(
            KhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(KhachHangLayout.createSequentialGroup()
                .addGroup(KhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(KhachHangLayout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(KhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel39)
                            .addComponent(jLabel54)
                            .addComponent(jLabel42)
                            .addComponent(jLabel56)
                            .addComponent(jLabel57))
                        .addGap(22, 22, 22)
                        .addGroup(KhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(KhachHangLayout.createSequentialGroup()
                                .addComponent(jRadioButton9)
                                .addGap(75, 75, 75)
                                .addComponent(jRadioButton10))
                            .addComponent(jTextField13, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
                            .addComponent(jTextField14)
                            .addComponent(jTextField15)
                            .addComponent(jTextField16)))
                    .addGroup(KhachHangLayout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(jButton9)
                        .addGap(53, 53, 53)
                        .addComponent(jButton10)
                        .addGap(46, 46, 46)
                        .addComponent(jButton11)
                        .addGap(29, 29, 29)
                        .addComponent(jButton12)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 117, Short.MAX_VALUE)
                .addComponent(panelRound4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(182, 182, 182))
            .addGroup(KhachHangLayout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 754, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, KhachHangLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(KhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, KhachHangLayout.createSequentialGroup()
                        .addComponent(jLabel38)
                        .addGap(407, 407, 407))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, KhachHangLayout.createSequentialGroup()
                        .addComponent(myButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(150, 150, 150))))
        );
        KhachHangLayout.setVerticalGroup(
            KhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(KhachHangLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addGroup(KhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(KhachHangLayout.createSequentialGroup()
                        .addComponent(panelRound4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(KhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(myButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31))
                    .addGroup(KhachHangLayout.createSequentialGroup()
                        .addGroup(KhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel39)
                            .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(KhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel54)
                            .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(KhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel42)
                            .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(KhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel56)
                            .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(KhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel57)
                            .addComponent(jRadioButton9)
                            .addComponent(jRadioButton10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(KhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton9)
                            .addComponent(jButton10)
                            .addComponent(jButton11)
                            .addComponent(jButton12))
                        .addGap(68, 68, 68)))
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
        );

        jPanel9.add(KhachHang);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 1094, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(7, Short.MAX_VALUE))
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 680));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSanPhamMouseClicked
        // TODO add your handling code here:
        setVisibleAllPanel();
        SanPham.setVisible(true);
    }//GEN-LAST:event_btnSanPhamMouseClicked

    private void btnHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHomeMouseClicked
        // TODO add your handling code here:
        setVisibleAllPanel();
        Home1.setVisible(true);
    }//GEN-LAST:event_btnHomeMouseClicked

    private void btnHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHoaDonMouseClicked
        // TODO add your handling code here:
        setVisibleAllPanel();
        HoaDon.setVisible(true);
    }//GEN-LAST:event_btnHoaDonMouseClicked

    private void btnNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNhanVienMouseClicked
        // TODO add your handling code here:
        setVisibleAllPanel();
        NhanVien.setVisible(true);
        showDataNhanVien(service.getAllNhanVien());
    }//GEN-LAST:event_btnNhanVienMouseClicked

    private void btnKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnKhachHangMouseClicked
        // TODO add your handling code here:
        setVisibleAllPanel();
        KhachHang.setVisible(true);
    }//GEN-LAST:event_btnKhachHangMouseClicked

    private void btnLichSuHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLichSuHoaDonMouseClicked
        // TODO add your handling code here:
        setVisibleAllPanel();
        LichSuHoaDon.setVisible(true);
    }//GEN-LAST:event_btnLichSuHoaDonMouseClicked

    private void btnDoanhThuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDoanhThuMouseClicked
        // TODO add your handling code here:
        setVisibleAllPanel();
        DoanhThu.setVisible(true);
    }//GEN-LAST:event_btnDoanhThuMouseClicked

    private void tblNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVienMouseClicked
        // TODO add your handling code here
        int index = tblNhanVien.getSelectedRow();
        showDetailNhanVien(index);
    }//GEN-LAST:event_tblNhanVienMouseClicked

    private void btnThem_NhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem_NhanVienActionPerformed
        // TODO add your handling code here:
        if (service.addNhanVien(getDataFormNhanVien())) {
            showDataNhanVien(service.getAllNhanVien());
            clearFormNhanVien();
            JOptionPane.showMessageDialog(this, "Thêm thành công");
        } else {
            JOptionPane.showMessageDialog(this, "Thêm thất bại");
        }
    }//GEN-LAST:event_btnThem_NhanVienActionPerformed

    private void btnLuu_NhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuu_NhanVienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLuu_NhanVienActionPerformed

    private void btnSua_NhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSua_NhanVienActionPerformed
        // TODO add your handling code here:
        if (service.updateNhanVien(getDataFormNhanVien())) {
            showDataNhanVien(service.getAllNhanVien());
            clearFormNhanVien();
            JOptionPane.showMessageDialog(this, "Sửa thành công");
        } else {
            JOptionPane.showMessageDialog(this, "Sửa thất bại");
        }
    }//GEN-LAST:event_btnSua_NhanVienActionPerformed

    private void btnXoa_NhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoa_NhanVienActionPerformed
        // TODO add your handling code here:
        if (service.deleteNhanVien(txtMa_NhanVien.getText().trim())) {
            showDataNhanVien(service.getAllNhanVien());
            clearFormNhanVien();
            JOptionPane.showMessageDialog(this, "Xoá thành công");
        } else {
            JOptionPane.showMessageDialog(this, "Xoá thất bại");
        }
    }//GEN-LAST:event_btnXoa_NhanVienActionPerformed

    private void btnSearch_NhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearch_NhanVienActionPerformed
        // TODO add your handling code here:
        String findName = txtTim_NhanVien.getText().trim();
        if (findName != null) {
            showDataNhanVien(service.findNhanVien(findName));
        }
    }//GEN-LAST:event_btnSearch_NhanVienActionPerformed

    private void tbSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbSanPhamMouseClicked
        // TODO add your handling code here:
        int index = tbSanPham.getSelectedRow();
        showDetailSanPham(index);
    }//GEN-LAST:event_tbSanPhamMouseClicked

    /**
     * @param args the command line arguments
     * @param maNhanVien
     */
    public static void main(String args[], String maNhanVien) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Home(maNhanVien).setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel DoanhThu;
    private javax.swing.JPanel HoaDon;
    private javax.swing.JPanel Home1;
    private javax.swing.JPanel KhachHang;
    private javax.swing.JPanel LichSuHoaDon;
    private javax.swing.JPanel NhanVien;
    private javax.swing.JPanel SanPham;
    private javax.swing.JLabel avatarHome;
    private javax.swing.JLabel avatarNhanVien;
    private javax.swing.JLabel btnDoanhThu;
    private javax.swing.JLabel btnHoaDon;
    private javax.swing.JLabel btnHome;
    private javax.swing.JLabel btnKhachHang;
    private javax.swing.JLabel btnLichSuHoaDon;
    private javax.swing.JButton btnLuuSanPham;
    private javax.swing.JButton btnLuu_NhanVien;
    private javax.swing.JLabel btnNhanVien;
    private javax.swing.JLabel btnSanPham;
    private view.MyButton btnSearchSanPham;
    private view.MyButton btnSearch_NhanVien;
    private javax.swing.JButton btnSuaSanPham;
    private javax.swing.JButton btnSua_NhanVien;
    private javax.swing.JButton btnThemSanPham;
    private javax.swing.JButton btnThem_NhanVien;
    private javax.swing.JButton btnXoaSanPham;
    private javax.swing.JButton btnXoa_NhanVien;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.JComboBox<String> cbLoai;
    private com.toedter.calendar.JDateChooser dateChooser_NhanVien;
    private javax.swing.JLabel imageSanPham;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRadioButton10;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JRadioButton jRadioButton6;
    private javax.swing.JRadioButton jRadioButton9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTable jTable7;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private view.MyButton myButton2;
    private custom.PanelRound panelRound1;
    private custom.PanelRound panelRound2;
    private custom.PanelRound panelRound3;
    private custom.PanelRound panelRound4;
    private javax.swing.JRadioButton rdConSanPham;
    private javax.swing.JRadioButton rdHetSanPham;
    private javax.swing.JRadioButton rdNam_NhanVien;
    private javax.swing.JRadioButton rdNu_NhanVien;
    private javax.swing.JTable tbSanPham;
    private javax.swing.JTable tblNhanVien;
    private javax.swing.JTextField txtDiaChi_NhanVien;
    private javax.swing.JTextField txtGiaSanPham;
    private javax.swing.JTextField txtMaNhanVienHome;
    private javax.swing.JTextField txtMaSanPham;
    private javax.swing.JTextField txtMa_NhanVien;
    private javax.swing.JTextField txtSdt_NhanVien;
    private javax.swing.JTextField txtSoLuongSanPham;
    private javax.swing.JTextField txtTenNhanVienHome;
    private javax.swing.JTextField txtTenSanPham;
    private javax.swing.JTextField txtTen_NhanVien;
    private javax.swing.JTextField txtTimSanPham;
    private javax.swing.JTextField txtTim_NhanVien;
    // End of variables declaration//GEN-END:variables
}
