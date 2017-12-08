import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ReadStudent extends Frame {

	Label lbStudentNumber, lbStudentName, lbStudentGender, lbStudentAge;
    TextField tfStudentNumber, tfStudentName, tfStudentGender, tfStudentAge;
    Button btnDelete;
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
        btnDelete = new Button("创建");

        add(lbStudentNumber);
        add(tfStudentNumber);
        add(btnDelete);

        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                String studentNumber = tfStudentNumber.getText();
                String studentName = tfStudentName.getText();
                String studentGender = tfStudentGender.getText();
                String studentAge = tfStudentAge.getText();

                try
                {
                    Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                    con = DriverManager.getConnection("jdbc:odbc:st");

                    strSql="DELETE FROM student WHERE "
                        + "studentNumber" + " = " + studentName;
                    ps = con.prepareStatement(strSql);
                    ps.setString(1, studentNumber);
                    ps.executeUpdate();
                }
                catch(Exception ex)
                {

                }
            }
        });
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                try
                {
                    con.commit();
                    con.close();
                }
                catch(Exception ex)
                {

                }
                (e.getWindow()).dispose();
                System.exit(0);
            }
        });
        setLayout(new FlowLayout());
        setSize(300,300);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new ReadStudent();
    }

}

