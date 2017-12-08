import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class UpdateStudent extends Frame{

    Label lbSno,lbName;
    TextField tfNo,tfSname;
    Button btnUpdate;
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    String strSql;

    public UpdateStudent()
    {
        lbSno=new Label("请输入学号：");
        lbName=new Label("姓名：");
        tfNo=new TextField(20);
        tfSname=new TextField(20);
        btnUpdate=new Button("修改");
        add(lbSno);
        add(tfNo);
        add(lbName);
        add(tfSname);
        add(btnUpdate);
        btnUpdate.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                String sno=tfNo.getText();
                String sname=tfSname.getText();
                //String age=tfAge.getText();
                try
                {
                    Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                    con=DriverManager.getConnection("jdbc:odbc:st");

                    strSql="update student set sname=? where sno=?";
                    ps=con.prepareStatement(strSql);
                    ps.setString(1, sname);
                    //ps.setInt(2, Integer.parseInt(age));
                    ps.setString(2, sno);
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
        new UpdateStudent();
    }

}

