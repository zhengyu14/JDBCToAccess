import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class DeleteLecture extends Frame {

	Label lbLectureID, resultText;
    TextField tfLectureID;
    Button btnDelete;
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    String strSql;

    public DeleteLecture( ) {
        // Label
    		lbLectureID = new Label("讲座编号：");
        resultText = new Label("");

        // TextField
        tfLectureID = new TextField(20);

        // Button
        btnDelete = new Button("删除");

        add(lbLectureID);
        add(tfLectureID);
        add(btnDelete);
        add(resultText);

        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            		String lectureID = tfLectureID.getText();

                try {
                    Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                    con = DriverManager.getConnection("jdbc:odbc:lecture");
                    strSql = "DELETE FROM lecture WHERE lectureID = ?";
                    ps = con.prepareStatement(strSql);
                    ps.setString(1, lectureID);
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
        new DeleteLecture();
    }

}

