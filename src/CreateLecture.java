import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class CreateLecture extends Frame {

    Label lbLectureID, lbLectureName, lbLectureTime, lbLectureLocation, resultText;
    TextField tfLectureID, tfLectureName, tfLectureTime, tfLectureLocation;
    Button btnCreate;
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    String strSql;

    public CreateLecture( ) {
        // Label
    		lbLectureID = new Label("讲座编号：");
    		lbLectureName = new Label("讲座名称：");
    		lbLectureTime = new Label("讲座时间：");
    		lbLectureLocation = new Label("讲座地点：");
    		resultText = new Label();

        // TextField
    		tfLectureID = new TextField(20);
    		tfLectureName = new TextField(20);
    		tfLectureTime = new TextField(10);
    		tfLectureLocation = new TextField(20);

        // Button
        btnCreate = new Button("创建");

        add(lbLectureID);
        add(tfLectureID);
        add(lbLectureName);
        add(tfLectureName);
        add(lbLectureTime);
        add(tfLectureTime);
        add(lbLectureLocation);
        add(tfLectureLocation);
        add(btnCreate);
        add(resultText);

        btnCreate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String lectureID = tfLectureID.getText();
                String lectureName = tfLectureName.getText();
                Timestamp lectureTime = Timestamp.valueOf(tfLectureTime.getText());
//                String lectureTime = tfLectureTime.getText();
                String lectureLocation = tfLectureLocation.getText();

                try {
                	    Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                    con = DriverManager.getConnection("jdbc:odbc:lecture");
                    System.out.println("1");
                    strSql = "INSERT INTO lecture ( lectureID, lectureName, lectureTime, lectureLocation ) VALUES (?,?,?,?);";
                    ps = con.prepareStatement(strSql);
                    ps.setString(1, lectureID);
                    ps.setString(2, lectureName);
                    ps.setTimestamp(3, lectureTime);
                    ps.setString(4, lectureLocation);
                    ps.executeUpdate();
                    resultText.setText("创建成功！");
                    System.out.println("创建成功！");
                }
                catch(Exception ex){ }
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
        new CreateLecture();
    }

}

