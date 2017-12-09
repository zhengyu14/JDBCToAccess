import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class UpdateLecture extends Frame{

	Label lbLectureID, lbLectureName, lbLectureTime, lbLectureLocation, resultText;
    TextField tfLectureID, tfLectureName, tfLectureTime, tfLectureLocation;
    Button btnUpdate;
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    String strSql;

    public UpdateLecture() {
    		// Label
    	lbLectureID = new Label("讲座编号：");
		lbLectureName = new Label("讲座名称：");
		lbLectureTime = new Label("讲座时间：");
		lbLectureLocation = new Label("讲座地点：");
		resultText = new Label("");
		
		// TextField
		tfLectureID = new TextField(20);
		tfLectureName = new TextField(20);
		tfLectureTime = new TextField(10);
		tfLectureLocation = new TextField(20);
		
		// Button
		btnUpdate = new Button("更新");

        add(lbLectureID);
        add(tfLectureID);
        add(lbLectureName);
        add(tfLectureName);
        add(lbLectureTime);
        add(tfLectureTime);
        add(lbLectureLocation);
        add(tfLectureLocation);
        add(btnUpdate);
        add(resultText);
        
        btnUpdate.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
            	String lectureID = tfLectureID.getText();
                String lectureName = tfLectureName.getText();
                String lectureTime = tfLectureTime.getText();
                String lectureLocation = tfLectureLocation.getText();
                
                try {
                    Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                    con = DriverManager.getConnection("jdbc:odbc:lecture");
                    strSql = "UPDATE lecture SET lectureName = ?, lectureTime = ?, lectureLocation = ? WHERE lectureID = ?";
                    ps = con.prepareStatement(strSql);
                    ps.setString(1, lectureName);
                    ps.setTimestamp(2, Timestamp.valueOf(lectureTime));
                    ps.setString(3, lectureLocation);
                    ps.setString(4, lectureID);
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
        new UpdateLecture();
    }

}

