import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ReadLecture extends Frame {

	Label lbLectureID, queryResult;
    TextField tfLectureID;
    Button btnRead;
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    String strSql;

    public ReadLecture( ) {
    	this.setLayout(new BorderLayout());
        // Label
    		lbLectureID = new Label("讲座编号：");
        queryResult = new Label("");
        
        // TextField
        tfLectureID = new TextField(20);

        // Button
        btnRead = new Button("查询");
        
        add(lbLectureID);
        add(tfLectureID);
        add(btnRead);
        
        btnRead.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String lectureID = tfLectureID.getText();
                
                try {
                	String result = null;
                    Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                    con = DriverManager.getConnection("jdbc:odbc:lecture");
                    strSql = "SELECT * FROM lecture WHERE lectureID = ?";
                    ps = con.prepareStatement(strSql);
                    ps.setString(1, lectureID);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                    	result = "讲座名称：" + rs.getString(2) + "; 讲座时间：" + rs.getTimestamp(3).toString() + "; 讲座地点：" + rs.getString(4) + ";";
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
        new ReadLecture();
    }

}