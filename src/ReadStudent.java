import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ReadStudent extends Frame {

	Label lbStudentNumber, queryResult;
    TextField tfStudentNumber;
    Button btnRead;
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    String strSql;

    public ReadStudent( ) {
    	this.setLayout(new BorderLayout());
        // Label
        lbStudentNumber = new Label("学号：");
        queryResult = new Label("");
        
        // TextField
        tfStudentNumber = new TextField(20);

        // Button
        btnRead = new Button("查询");
        
        add(lbStudentNumber);
        add(tfStudentNumber);
        add(btnRead);
        
        btnRead.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String studentNumber = tfStudentNumber.getText();
                
                try {
                	String result = null;
                    Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                    con = DriverManager.getConnection("jdbc:odbc:student");
                    strSql = "SELECT * FROM student WHERE studentNumber = ?";
                    ps = con.prepareStatement(strSql);
                    ps.setString(1, studentNumber);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                    	result = "姓名: " + rs.getString(2) + "; 性别: " + rs.getString(3) + "; 年龄: " + rs.getInt(4) + ";";
                    	System.out.println(result);
					}
                    queryResult.setText(result);
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
        add(queryResult);
        setLayout(new FlowLayout());
        setSize(300,300);
        setVisible(true);
        
    }

    public static void main(String[] args) {
        new ReadStudent();
    }

}