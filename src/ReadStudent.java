import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ReadStudent extends Frame {

	Label lbStudentNumber;
    TextField tfStudentNumber;
    Button btnRead;
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    String strSql;

    public ReadStudent( ) {
        // Label
        lbStudentNumber = new Label("学号：");

        // TextField
        tfStudentNumber = new TextField(20);

        // Button
        btnRead = new Button("查询");

        add(lbStudentNumber);
        add(tfStudentNumber);
        add(btnRead);
        
        btnRead.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                String studentNumber = tfStudentNumber.getText();
                
                try
                {
                    Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                    con = DriverManager.getConnection("jdbc:odbc:student");

                    strSql = "SELECT * FROM student WHERE studentNumber = ?";
                    ps = con.prepareStatement(strSql);
                    ps.setString(1, studentNumber);
                    ps.executeUpdate();
                }
                catch(Exception ex)
                {

                }
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
        new ReadStudent();
    }

}