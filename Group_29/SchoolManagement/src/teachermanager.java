
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class teachermanager {

    private JFrame frame;
    private JTable table;
    private DefaultTableModel tableModel;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                teachermanager window = new teachermanager();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the application.
     * @wbp.parser.entryPoint
     */
    public teachermanager() {
        initialize();
        loadTeachersFromFile("src/data/Teacher.txt");
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
     
        frame.getContentPane().setBackground(Color.LIGHT_GRAY);
        frame.setBounds(100, 100, 800, 500);
        frame.setSize(1000,1200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JButton btnAdd = new JButton("Thêm");
        btnAdd.setBounds(550, 90, 89, 23);
        frame.getContentPane().add(btnAdd);

        JButton btnEdit = new JButton("Sửa");
        btnEdit.setBounds(443, 90, 89, 23);
        frame.getContentPane().add(btnEdit);

        JButton btnDelete = new JButton("Xóa");
        btnDelete.setBounds(649, 90, 89, 23);
        frame.getContentPane().add(btnDelete);

        JLabel lblTitle = new JLabel("Danh sách giảng viên");
        lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblTitle.setBounds(136, 73, 246, 51);
        frame.getContentPane().add(lblTitle);

        JLabel lblHeader = new JLabel("     HỆ THỐNG QUẢN LÝ");
        lblHeader.setForeground(Color.WHITE);
        lblHeader.setBounds(0, 0, 126, 62);
        frame.getContentPane().add(lblHeader);

        JButton btnMenu = new JButton("Giảng Viên");
        btnMenu.setBackground(Color.LIGHT_GRAY);
        btnMenu.setBounds(0, 62, 126, 30);
        frame.getContentPane().add(btnMenu);

        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);
        panel.setBounds(0, 0, 126, 460);
        frame.getContentPane().add(panel);

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(Color.CYAN);
        panel_1.setBounds(125, 0, 659, 62);
        frame.getContentPane().add(panel_1);

        String[] columns = {"ID", "Họ và Tên", "Khoa", "Email"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(136, 135, 638, 325);
        frame.getContentPane().add(scrollPane);
        
        JPanel panel_2 = new JPanel();
        panel_2.setBounds(136, 73, 638, 387);
        frame.getContentPane().add(panel_2);

        // Thêm sự kiện cho nút "Thêm"
        btnAdd.addActionListener(e -> addTeacher());

        // Thêm sự kiện cho nút "Sửa"
        btnEdit.addActionListener(e -> editTeacher());

        // Thêm sự kiện cho nút "Xóa"
        btnDelete.addActionListener(e -> deleteTeacher());
    }

    /**
     * Tải dữ liệu giảng viên từ file
     */
    private void loadTeachersFromFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(new File(fileName)))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\t");
                if (parts.length == 4) {
                    tableModel.addRow(new Object[]{parts[0], parts[1], parts[2], parts[3]});
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Không thể đọc file: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Thêm giảng viên mới
     */
    private void addTeacher() {
        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField departmentField = new JTextField();
        JTextField emailField = new JTextField();

        Object[] message = {
                "ID:", idField,
                "Họ và Tên:", nameField,
                "Khoa:", departmentField,
                "Email:", emailField
        };

        int option = JOptionPane.showConfirmDialog(frame, message, "Thêm giảng viên", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String id = idField.getText();
            String name = nameField.getText();
            String department = departmentField.getText();
            String email = emailField.getText();

            if (!id.isEmpty() && !name.isEmpty() && !department.isEmpty() && !email.isEmpty()) {
                tableModel.addRow(new Object[]{id, name, department, email});
            } else {
                JOptionPane.showMessageDialog(frame, "Vui lòng điền đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Sửa thông tin giảng viên
     */
    private void editTeacher() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(frame, "Vui lòng chọn giảng viên cần sửa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String id = (String) tableModel.getValueAt(selectedRow, 0);
        String name = (String) tableModel.getValueAt(selectedRow, 1);
        String department = (String) tableModel.getValueAt(selectedRow, 2);
        String email = (String) tableModel.getValueAt(selectedRow, 3);

        JTextField idField = new JTextField(id);
        JTextField nameField = new JTextField(name);
        JTextField departmentField = new JTextField(department);
        JTextField emailField = new JTextField(email);

        Object[] message = {
                "ID:", idField,
                "Họ và Tên:", nameField,
                "Khoa:", departmentField,
                "Email:", emailField
        };

        int option = JOptionPane.showConfirmDialog(frame, message, "Sửa thông tin giảng viên", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            tableModel.setValueAt(idField.getText(), selectedRow, 0);
            tableModel.setValueAt(nameField.getText(), selectedRow, 1);
            tableModel.setValueAt(departmentField.getText(), selectedRow, 2);
            tableModel.setValueAt(emailField.getText(), selectedRow, 3);
        }
    }

    /**
     * Xóa giảng viên
     */
    private void deleteTeacher() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(frame, "Vui lòng chọn giảng viên cần xóa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(frame, "Bạn có chắc muốn xóa giảng viên này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            tableModel.removeRow(selectedRow);
        }
    }
}
