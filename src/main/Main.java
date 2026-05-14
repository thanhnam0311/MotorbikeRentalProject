package main;

import com.formdev.flatlaf.FlatLightLaf;
import gui.common.LoginFrame;
import javax.swing.UIManager;
import javax.swing.SwingUtilities;
import java.awt.Font;

// Thêm thư viện để test kết nối
import java.sql.Connection;
import dao.MySQLConnect;

public class Main {
    public static void main(String[] args) {
        
        // --- BẮT ĐẦU PHẦN KIỂM TRA KẾT NỐI DATABASE ---
        System.out.println("Đang kiểm tra kết nối CSDL...");
        Connection conn = MySQLConnect.getConnection();
        
        if (conn != null) {
            System.out.println("✅ CHÚC MỪNG! KẾT NỐI DATABASE THÀNH CÔNG!");
            try {
                conn.close(); // Tạm đóng kết nối sau khi test xong
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("❌ KẾT NỐI THẤT BẠI. Vui lòng kiểm tra lại XAMPP/MySQL hoặc db.properties!");
        }
        // --- KẾT THÚC PHẦN KIỂM TRA ---


        // 1. Thiết lập giao diện hiện đại với FlatLaf
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());

            // Tùy chọn: Set font chữ mặc định cho toàn bộ ứng dụng (giúp chữ không bị lỗi font hoặc quá nhỏ)
            UIManager.put("defaultFont", new Font("Segoe UI", Font.PLAIN, 14));

        } catch (Exception ex) {
            System.err.println("Không thể khởi tạo giao diện FlatLaf");
        }

        // 2. Khởi chạy màn hình đăng nhập trong luồng an toàn của Swing
        SwingUtilities.invokeLater(() -> {
            LoginFrame login = new LoginFrame();
            login.setVisible(true);
        });
        
    }
}