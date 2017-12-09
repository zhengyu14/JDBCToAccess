import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class UpdateStudent extends Frame{

	Label lbStudentNumber, lbStudentName, lbStudentGender, lbStudentAge, resultText;
    TextField tfStudentNumber, tfStudentName, tfStudentGender, tfStudentAge;
    Button btnUpdate;
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    String strSql;

    public UpdateStudent() {
    		// Label
    		lbStudentNumber = new Label("学号：");
    		lbStudentName = new Label("姓名：");
    		lbStudentGender = new Label("性别：");
    		lbStudentAge = new Label("年龄：");
    		resultText = new Label("");
    		
    		// TextField
    		tfStudentNumber = new TextField(20);
        tfStudentName = new TextField(20);
        tfStudentGender = new TextField(2);
        tfStudentAge = new TextField(2);
        
        // Button
        btnUpdate = new Button("修改");
        
        add(lbStudentNumber);
        add(tfStudentNumber);
        add(lbStudentName);
        add(tfStudentName);
        add(lbStudentGender);
        add(tfStudentGender);
        add(lbStudentAge);
        add(tfStudentAge);
        add(btnUpdate);
        add(resultText);
        
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String studentNumber = tfStudentNumber.getText();
                String studentName = tfStudentName.getText();
                String studentGender = tfStudentGender.getText();
                String studentAge = tfStudentAge.getText();
                
                try {
                    Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                    con = DriverManager.getConnection("jdbc:odbc:student");
                    strSql = "UPDATE student SET studentName = ?, studentGender = ?, studentAge = ? WHERE studentNumber = ?";
                    ps = con.prepareStatement(strSql);
                    ps.setString(1, studentName);
                    ps.setString(2, studentGender);
                    ps.setInt(3, Integer.parseInt(studentAge));
                    ps.setString(4, studentNumber);
                    ps.executeUpdate();
                    resultText.setText("更新成功！");
                    System.out.println("更新成功！");
                }
                catch(Exception ex) { }
            }
        });
        
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                try {
                    con.commit();
                    con.close();
                }
                catch(Exception ex) {
                	
                }
                
                (e.getWindow()).dispose();
                System.exit(0);
            }
        });
        
        setLayout(new FlowLayout());
        setSize(300,300);
        setVisible(true);
        
    }

    public static void main(String[] args) {
        new UpdateStudent();
    }

}

