import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;


public class StudentManagementGUI extends JFrame {

    
    // INTERNAL DATA CLASSES

    
    // Static inner class to avoid conflict with other previous assignment
    static class Student {
        String id;
        String name;
        ArrayList<String> enrolledCourses = new ArrayList<>();
        HashMap<String, String> grades = new HashMap<>(); 

        public Student(String id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return name + " (" + id + ")";
        }
    }

    // Static inner class to avoid conflict with other previous assignment
    static class Course {
        String code;
        String name;

        public Course(String code, String name) {
            this.code = code;
            this.name = name;
        }
        
        @Override
        public String toString() {
            return name;
        }
    }


    // GUI COMPONENTS & LOGIC
    // ---------------------------------------------------------
    // Data storage
    ArrayList<Student> students = new ArrayList<>();
    ArrayList<Course> courses = new ArrayList<>();

    // GUI components
    JTextField txtId, txtName, txtGrade;
    JTable studentTable;

    DefaultTableModel tableModel;
    JComboBox<Student> comboEnrollStudent, comboGradeStudent;
    JComboBox<Course> comboEnrollCourse;
    JComboBox<String> comboGradeCourse;
    JTextArea enrollmentLog;

    public StudentManagementGUI() {
        // WINDOW SETUP
        setTitle("Student Management System");
        setSize(900, 650);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // dummy data
        courses.add(new Course("CS101", "Intro to Java"));
        courses.add(new Course("CS102", "Data Structures"));

        courses.add(new Course("MATH101", "Calculus I"));

        // 2. TABS SETUP
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Manage Students", createStudentPanel());
        tabbedPane.addTab("Course Enrollment", createEnrollmentPanel());
        tabbedPane.addTab("Grade Management", createGradePanel());
        add(tabbedPane);
    }

    // --- STUDENT ---
    private JPanel createStudentPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));

        inputPanel.setBorder(BorderFactory.createTitledBorder("Student Details"));
        
        inputPanel.add(new JLabel("Student ID:"));
        txtId = new JTextField();

        inputPanel.add(txtId);
        
        inputPanel.add(new JLabel("Student Name:"));
        txtName = new JTextField();
        inputPanel.add(txtName);
        
        JButton btnAdd = new JButton("Add Student");
        JButton btnUpdate = new JButton("Update Selected");
        inputPanel.add(btnAdd);

        inputPanel.add(btnUpdate);
        
        String[] columns = {"ID", "Name", "Enrolled Courses", "Grades"};
        tableModel = new DefaultTableModel(columns, 0);
        studentTable = new JTable(tableModel);
        
        // Events
        btnAdd.addActionListener(e -> addStudent());
        btnUpdate.addActionListener(e -> updateStudent());
        
        studentTable.getSelectionModel().addListSelectionListener(e -> {
            int row = studentTable.getSelectedRow();

            if (row != -1) {
                txtId.setText((String) tableModel.getValueAt(row, 0));
                txtName.setText((String) tableModel.getValueAt(row, 1));
            }
        });

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(studentTable), BorderLayout.CENTER);
        return panel;
    }

    // --- ENROLLMENT ---
    private JPanel createEnrollmentPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 20, 20));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        panel.add(new JLabel("Select Student:"));
        comboEnrollStudent = new JComboBox<>();

        panel.add(comboEnrollStudent);
        
        panel.add(new JLabel("Select Course:"));
        comboEnrollCourse = new JComboBox<>();
        for (Course c : courses) comboEnrollCourse.addItem(c);
        panel.add(comboEnrollCourse);
        
        JButton btnEnroll = new JButton("Enroll Student");
        panel.add(new JLabel("")); 
        panel.add(btnEnroll);
        
        enrollmentLog = new JTextArea();

        enrollmentLog.setEditable(false);
        enrollmentLog.setBorder(BorderFactory.createTitledBorder("Log"));
        
        btnEnroll.addActionListener(e -> enrollStudent());
        
        JPanel container = new JPanel(new BorderLayout());
        container.add(panel, BorderLayout.NORTH);
        container.add(new JScrollPane(enrollmentLog), BorderLayout.CENTER);
        return container;
    }

    // --- GRADES ---
    private JPanel createGradePanel() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 20, 20));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        panel.add(new JLabel("Select Student:"));
        comboGradeStudent = new JComboBox<>();

        panel.add(comboGradeStudent);
        
        panel.add(new JLabel("Select Enrolled Course:"));
        comboGradeCourse = new JComboBox<>();
        panel.add(comboGradeCourse);
        
        panel.add(new JLabel("Assign Grade:"));
        txtGrade = new JTextField();
        panel.add(txtGrade);
        
        JButton btnAssign = new JButton("Assign Grade");
        panel.add(new JLabel(""));
        panel.add(btnAssign);
        
        comboGradeStudent.addActionListener(e -> updateGradeCourseCombo());
        btnAssign.addActionListener(e -> assignGrade());
        
        JPanel container = new JPanel(new BorderLayout());
        container.add(panel, BorderLayout.NORTH);
        return container;
    }

    // --- logic method ---

    private void addStudent() {
        String id = txtId.getText();
        String name = txtName.getText();
        if (id.isEmpty() || name.isEmpty()) return;

        for (Student s : students) {
            if (s.id.equals(id)) {
                JOptionPane.showMessageDialog(this, "Duplicate ID");
                return;
            }
        }
        students.add(new Student(id, name));
        refreshAllData();
        txtId.setText(""); txtName.setText("");
        JOptionPane.showMessageDialog(this, "Added!");
    }

    private void updateStudent() {
        int row = studentTable.getSelectedRow();
        if (row == -1) return;

        Student s = students.get(row);
        s.id = txtId.getText();
        s.name = txtName.getText();
        refreshAllData();
        JOptionPane.showMessageDialog(this, "Updated!");
    }

    private void enrollStudent() {
        Student s = (Student) comboEnrollStudent.getSelectedItem();
        Course c = (Course) comboEnrollCourse.getSelectedItem();
        if (s == null || c == null) return;
        
        if (!s.enrolledCourses.contains(c.name)) {
            s.enrolledCourses.add(c.name);

            enrollmentLog.append("Enrolled " + s.name + " in " + c.name + "\n");
            refreshAllData();
            JOptionPane.showMessageDialog(this, "Enrolled!");
        } else {
            JOptionPane.showMessageDialog(this, "Already Enrolled!");
        }
    }


    private void updateGradeCourseCombo() {
        comboGradeCourse.removeAllItems();
        Student s = (Student) comboGradeStudent.getSelectedItem();
        if (s != null) {
            for (String c : s.enrolledCourses) comboGradeCourse.addItem(c);
        }
    }


    private void assignGrade() {
        Student s = (Student) comboGradeStudent.getSelectedItem();
        String c = (String) comboGradeCourse.getSelectedItem();
        String g = txtGrade.getText();
        if (s != null && c != null && !g.isEmpty()) {
            s.grades.put(c, g);
            refreshAllData();
            JOptionPane.showMessageDialog(this, "Grade Assigned!");
        }
    }


    private void refreshAllData() {
        tableModel.setRowCount(0);
        comboEnrollStudent.removeAllItems();
        comboGradeStudent.removeAllItems();
        
        for (Student s : students) {
            tableModel.addRow(new Object[]{s.id, s.name, String.join(", ", s.enrolledCourses), s.grades.toString()});
            comboEnrollStudent.addItem(s);
            comboGradeStudent.addItem(s);
        }
        updateGradeCourseCombo();
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StudentManagementGUI().setVisible(true));
        
    }
}