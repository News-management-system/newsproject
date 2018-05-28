package sample.Data;

/**
 * Created by FU YUANQING on 5/18/2018.
 */


import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import sample.Classes.Help;
import sample.Classes.NewsInformation;
import sample.Classes.User;

import java.sql.*;


public class JCDB {

    private final static String dbName = "project";
    private final static String user = "root";
    private final static String password = "root";
    private final static String connectionURL = "jdbc:mysql://localhost/" + dbName + "?user=" + user + "&password=" + password + "&useSSL=false";


    private Help help = new Help();
    public int Administrator (String email, String password)throws SQLException {//管理人员登陆
            Connection conn = establishConnection();
            String query = "SELECT adminisID FROM administrator WHERE email = ? AND password = ?  ";
            int ID = 0;
            try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            System.out.println(statement);
            System.out.println(rs);
            while(rs.next()) {
                ID = rs.getInt(1);
            }
        }
        catch(SQLException e) {
            System.out.println("password wrong."+e);
        }
        return ID;
        }

    public int User (String email, String password)throws SQLException {//用户登陆
        Connection conn = establishConnection();
        String query = "SELECT userID FROM user WHERE email = ? AND password = ?  ";
        int ID = 0;
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            System.out.println(statement);
            System.out.println(rs);
            while(rs.next()) {
                ID = rs.getInt(1);
            }
        }
        catch(SQLException e) {
            System.out.println("password wrong."+e);
        }
        return ID;
    }

    public void managerAddNews(NewsInformation Newsinformation) throws SQLException {//管理者添加新闻
        try{
           // String SQL = "INSERT INTO publisher (Pub_name,Pub_tel,Pub_address,Pub_introduction) VALUES (?,?,?,?)";
            Connection conn = establishConnection();
            String statement = "INSERT INTO NewsInformation(NewsID, NewsTitle,NewsContent,CategoryNewsID,newsman,NewsDateTime)VALUES(?,?,?,?,?,?)";
            PreparedStatement prepStmt = (PreparedStatement) conn.prepareStatement(statement);
            prepStmt.setInt(1,Newsinformation.getNewsID());
            prepStmt.setString(2, Newsinformation.getNewsTitle());
            prepStmt.setString(3, Newsinformation.getNewsContent());
            prepStmt.setInt(4, Newsinformation.getCategoryNewsID());
            prepStmt.setString(5,Newsinformation.getNewsman());
            prepStmt.setString(6, Newsinformation.getNewsDateTime());
            prepStmt.executeUpdate();

            //addcategorynews_has_newsinformation(Newsinformation.getCategoryNewsID(), Newsinformation.getNewsID());
            System.out.println("the data has been moved into database.");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }


    public ResultSet retrieveAllNews(){//展示新闻列表

        try {
            Connection conn = establishConnection();
            String query = "SELECT NewsID,NewsTitle,NewsContent,CategoryNewsID,newsman,NewsDateTime FROM NewsInformation";
            PreparedStatement prepStmt = conn.prepareStatement(query);
            ResultSet rs =prepStmt.executeQuery();
            //System.out.println(query);
            System.out.println("Success");
            return rs;
        } catch (Exception e) {
            System.out.println("Cannot retrieve any book.");
            return null;
        }
    }
    public void ManagerDeleteNews(String NewsTitle){//管理者删除新闻
        Connection conn = null;
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        try {
            String statement = "DELETE FROM newsinformation  WHERE NewsTitle = ?" ;
            conn = establishConnection();
            prepStmt = conn.prepareStatement(statement);
            prepStmt.setString(1, NewsTitle);
            prepStmt.execute();
            System.out.println("Success removed");

        } catch (Exception e) {
            System.out.println("ERROR. Not delete.");
        }
    }
    public ResultSet retrieveAllcomment(){//展示评论列表

        try {
            Connection conn = establishConnection();
            String query = "SELECT commentaryID,commentaryContent,NewsID,commentator,commentaryDateTime FROM NewsCommentary";
            PreparedStatement prepStmt = conn.prepareStatement(query);
            ResultSet rs =prepStmt.executeQuery();
            //System.out.println(query);
            System.out.println("Success");
            return rs;
        } catch (Exception e) {
            System.out.println("Cannot retrieve any book.");
            return null;
        }
    }
    public void ManagerDeleteComment(String commentaryContent){//用户删评论
        //System.out.println(commentary_ID);
        Connection conn = null;
        PreparedStatement prepStmt = null;
        try {
            String SQL = "DELETE FROM NewsCommentary WHERE commentaryContent=?";
            conn = establishConnection();
            prepStmt = conn.prepareStatement(SQL);
            prepStmt.setString(1, commentaryContent);
            prepStmt.execute();
        } catch (Exception e) {
            System.out.println("Cannot remove feedback.");
        }
    }
    public ResultSet retrieveAllUser(){//展示用户列表//////////
        try {
            Connection conn = establishConnection();
            String query = "SELECT userID,name,email,password,major FROM User";
            PreparedStatement prepStmt = conn.prepareStatement(query);
            ResultSet rs =prepStmt.executeQuery();
            //System.out.println(query);
            System.out.println("Success");
            return rs;
        } catch (Exception e) {
            System.out.println("Cannot retrieve any user.");
            return null;
        }
    }
    public void ManagerDeleteUsers(String name){//管理者删除用户
        Connection conn = null;
        PreparedStatement prepStmt = null;
        try {
            String statement = "DELETE FROM User WHERE name =?";
            conn = establishConnection();
            prepStmt = conn.prepareStatement(statement);
            prepStmt.setString(1, name);
            prepStmt.execute();
            System.out.println("Success removed");

        } catch (Exception e) {
            System.out.println("ERROR. Not delete.");
        }
    }

    public void managerAddUser(User user) throws SQLException {//管理者添加用户
        try{
            // String SQL = "INSERT INTO publisher (Pub_name,Pub_tel,Pub_address,Pub_introduction) VALUES (?,?,?,?)";
            Connection conn = establishConnection();
            String statement = "INSERT INTO User(userID, name,email,password,major)VALUES(?,?,?,?,?)";
            PreparedStatement prepStmt = (PreparedStatement) conn.prepareStatement(statement);
            prepStmt.setInt(1,user.getUserID());
            prepStmt.setString(2, user.getName());
            // prepStmt.setDate(3, Help.toSqlDate(Newsinformation.getNewsDateTime()));
            prepStmt.setString(3, user.getEmail());
            prepStmt.setString(4, user.getPassword());
            prepStmt.setString(5,user.getMajor());

            prepStmt.executeUpdate();

            //addcategorynews_has_newsinformation(Newsinformation.getCategoryNewsID(), Newsinformation.getNewsID());
            System.out.println("the data has been moved into database.");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void showuse(TextField getMajor, TextField getName, TextField getEmail) {//创建的新方法
        Connection conn = null;
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        try {
            String statement = "select name,major from user where email=?";
            conn = establishConnection();
            prepStmt = conn.prepareStatement(statement);
            prepStmt.setString(1, getEmail.getText());
            rs = prepStmt.executeQuery();
            while (rs.next()) {
                getName.setText(rs.getString("name"));
                getMajor.setText(rs.getString("major"));
            }
        } catch (Exception e) {
            System.out.println("Cannot ritrive.");
            System.out.println(e);
        }
    }
        public void readFeedback(TextField getEmail, TextArea mearea) {//创建的新方法
            Connection conn = null;
            PreparedStatement prepStmt = null;
            ResultSet rs = null;
            try {
                String statement = "select commentaryContent,commentaryDateTime from newscommentary, user where NewsCommentary_commentaryID = commentaryID and email=?";
                conn = establishConnection();
                prepStmt = conn.prepareStatement(statement);
                prepStmt.setString(1, getEmail.getText());
                rs = prepStmt.executeQuery();
                while (rs.next()) {
                    mearea.appendText("-----------------------------------------------------------------"
                            + "\n" + rs.getString("commentaryContent") + " "
                            + rs.getDate("commentaryDateTime") + "\n");
                }
            } catch (Exception e) {
                System.out.println("Cannot ritrive.");
                System.out.println(e);
            }
        }


        public void showuserinformation(TextField getMajor, TextField getName, TextField getEmail) {//创建的新方法
            Connection conn = null;
            PreparedStatement prepStmt = null;
            ResultSet rs = null;
            try {
                String statement = "select name,major from user where email=?";
                conn = establishConnection();
                prepStmt = conn.prepareStatement(statement);
                prepStmt.setString(1, getEmail.getText());
                rs = prepStmt.executeQuery();
                while (rs.next()) {
                    getName.setText(rs.getString("name"));
                    getMajor.setText(rs.getString("major"));
                }
            } catch (Exception e) {
                System.out.println("Cannot ritrive.");
                System.out.println(e);
            }
        }


        public ResultSet searchingNews (String NewsTitle){//搜索新闻
            Connection conn = null;
            PreparedStatement prepStmt = null;
            ResultSet rs = null;
            try {

                String statement = "SELECT NewsTitle, newsman,NewsDateTime, NewsContent,CategoryNewsID FROM newsinformation " +
                        "WHERE NewsTitle LIKE\"%" + NewsTitle + "%\"";


                conn = establishConnection();
                prepStmt = conn.prepareStatement(statement);
                rs = prepStmt.executeQuery();
                System.out.println("Success");

            } catch (Exception e) {
                System.out.println("Searching news method ... Cannot ritrive any news.");
                e.printStackTrace();
            }
            return rs;
        }
    public ResultSet retrieveOneNewsTitle(){
        try {
            Connection conn = establishConnection();
            String query = "SELECT NewsTitle,NewsDateTime FROM NewsInformation where CategoryNewsID=1 order by NewsDateTime DESC ";
            PreparedStatement prepStmt = conn.prepareStatement(query);
            ResultSet rs =prepStmt.executeQuery();
            //System.out.println(query);
            System.out.println("Success");
            return rs;
        } catch (Exception e) {
            System.out.println("Cannot retrieve any news.");
            return null;
        }
    }
    public ResultSet retrieveFNewsTitle(){
        try {
            Connection conn = establishConnection();
            String query = "SELECT NewsTitle,NewsDateTime FROM NewsInformation where CategoryNewsID=5 order by NewsDateTime DESC ";
            PreparedStatement prepStmt = conn.prepareStatement(query);
            ResultSet rs =prepStmt.executeQuery();
            //System.out.println(query);
            System.out.println("Success");
            return rs;
        } catch (Exception e) {
            System.out.println("Cannot retrieve any news.");
            return null;
        }
    }

























    public Connection establishConnection() {
        Connection conn;

        //Get connection to database
        try {
            conn = DriverManager.getConnection(connectionURL);
            if(conn!=null) {
                System.out.println("connected to database successfully");
                return conn;
            }
        }
        catch (Exception ex) {
            System.out.println("Not connected to database");
        }
        return null;
    }



}













