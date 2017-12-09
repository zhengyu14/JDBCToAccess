import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class DeleteStudent extends Frame {

	Label lbStudentNumber, resultText;
    TextField tfStudentNumber;
    Button btnDelete;
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    String strSql;

    public DeleteStudent( ) {
        // Label
        lbStudentNumber = new Label("学号：");
        resultText = new Label("");

        // TextField
        tfStudentNumber = new TextField(20);

        // Button
        btnDelete = new Button("删除");

        add(lbStudentNumber);
        add(tfStudentNumber);
        add(btnDelete);
        add(resultText);

        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            		String studentNumber = tfStudentNumber.getText();

                try {
                    Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                    con = DriverManager.getConnection("jdbc:odbc:student");
                    strSql = "DELETE FROM student WHERE studentNumber = ?";
                    ps = con.prepareStatement(strSql);
                    ps.setString(1, studentNumber);
                    ps.executeUpdate();
                    resultText.setText("删除成功！");
                    System.out.println("删除成功！");
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
                catch(Exception ex) { }
                
                (e.getWindow()).dispose();
                System.exit(0);
            }
        });
        
        setLayout(new FlowLayout());
        setSize(300,300);
        setVisible(true);
        
    }

    public static void main(String[] args) {
        new DeleteStudent();
    }

}

