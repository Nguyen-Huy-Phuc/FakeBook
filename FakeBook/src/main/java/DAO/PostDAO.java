/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Comment;
import Model.Like;
import Model.Post;
import Model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import utilities.JDBCUtil;

public class PostDAO {

    public static PostDAO getInstance() {
        return new PostDAO();
    }

    //post
    public int insert(Post post) {
        try {
            Connection connection = JDBCUtil.getConnection();
            String insertQuery = "INSERT INTO POST (content, img, like_num, comment, user_name)"
                    + "VALUES ( ?, ?, ?, ?, ?)";
            // Tạo PreparedStatement
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement(insertQuery);
            // Thiết lập các tham số trong truy vấn
            preparedStatement.setString(1, post.getContent());
            preparedStatement.setString(2, post.getImg());
            preparedStatement.setInt(3, post.getLikeNum());
            preparedStatement.setInt(4, post.getCommentNum());
            preparedStatement.setString(5, post.getUser_name());

            // Thực hiện INSERT
            int rowsInserted = preparedStatement.executeUpdate();
            JDBCUtil.closeConnection(connection);
            return rowsInserted;
        } catch (SQLException ex) {
            System.out.println("error: " + ex.getMessage());
        }
        return 0;
    }

    public int update(Post post) {
        try {
            Connection connection = JDBCUtil.getConnection();
            String insertQuery = "UPDATE POST p SET p.content = ?, p.img = ? WHERE p.id_post = ?";
            // Tạo PreparedStatement 
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement(insertQuery);
            // Thiết lập các tham số trong truy vấn
            preparedStatement.setString(1, post.getContent());
            preparedStatement.setString(2, post.getImg());
            preparedStatement.setInt(3, post.getId_post());

            // Thực hiện INSERT
            int rowsInserted = preparedStatement.executeUpdate();
            JDBCUtil.closeConnection(connection);
            return rowsInserted;
        } catch (SQLException ex) {
            System.out.println("error: " + ex.getMessage());
        }
        return 0;
    }

    public int updateByID(String content, String img, int posID) {
        try {
            Connection connection = JDBCUtil.getConnection();
            String insertQuery = "UPDATE POST p SET p.content = ?, p.img = ? WHERE p.id_post = ?";
            // Tạo PreparedStatement 
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement(insertQuery);
            // Thiết lập các tham số trong truy vấn
            preparedStatement.setString(1, content);
            preparedStatement.setString(2, img);
            preparedStatement.setInt(3, posID);

            // Thực hiện INSERT
            int rowsInserted = preparedStatement.executeUpdate();
            JDBCUtil.closeConnection(connection);
            return rowsInserted;
        } catch (SQLException ex) {
            System.out.println("error: " + ex.getMessage());
        }
        return 0;
    }

    public List<Post> selectPostById(int postId) {
        List<Post> list = new ArrayList<>();

        try {
            Connection connection = JDBCUtil.getConnection();
            Statement stmt = connection.createStatement();
            // get data from table 'customer'
            ResultSet rs = stmt.executeQuery("  select * from post WHERE id_post= " + postId);
            // map customer data
            while (rs.next()) {
                Post post = new Post();
                post.setId_post(rs.getInt(1));
                post.setContent(rs.getString(2));
                post.setImg(rs.getString(3));
                post.setLikeNum(rs.getInt(4));
                post.setCommentNum(rs.getInt(5));
                post.setUser_name(rs.getString(6));
                post.setCreatedTime(rs.getTimestamp(7));
                list.add(post);
                break;
            }
            JDBCUtil.closeConnection(connection);
        } catch (SQLException e) {
            System.out.println("errror:: " + e.getMessage());
        }
        return list;
    }

    public List<Map<String, Object>> selectAllPost() {
        List<Map<String, Object>> list = new ArrayList<>();
        try {
            Connection connection = JDBCUtil.getConnection();
            Statement stmt = connection.createStatement();
            // get data from table 'customer'
            ResultSet rs = stmt.executeQuery("  select * from POST p , user_account u WHERE p.user_name = u.user_name   ORDER BY createdTime DESC;");
            // map customer data
            while (rs.next()) {
                Map<String, Object> rowMap = new HashMap<>();
                rowMap.put("id_post", rs.getObject(1));
                rowMap.put("content", rs.getString(2));
                rowMap.put("img", rs.getString(3));
                rowMap.put("like_num", rs.getInt(4));
                rowMap.put("comment", rs.getInt(5));
                rowMap.put("user_name", rs.getString(6));
                rowMap.put("createdTime", rs.getDate(7));
                rowMap.put("id_user", rs.getString(8));
                rowMap.put("first_name", rs.getString(15));
                rowMap.put("last_name", rs.getString(16));
                rowMap.put("avatar", rs.getString(17));
                list.add(rowMap);
            }
            JDBCUtil.closeConnection(connection);
        } catch (SQLException e) {
            System.out.println("errror:: " + e.getMessage());
        }
        return list;
    }

    public List<Map<String, Object>> selectAllPostByContent(String user_name, String content) {
        List<Map<String, Object>> list = new ArrayList<>();
        try {
            Connection connection = JDBCUtil.getConnection();
            Statement stmt = connection.createStatement();
            // get data from table 'customer'
            ResultSet rs = stmt.executeQuery("  select * from POST p , user_account u WHERE p.user_name = u.user_name AND p.content LIKE '%" + content + "%' AND u.user_name <> '" + user_name + "' ORDER BY createdTime DESC;");
            // map customer data
            while (rs.next()) {
                Map<String, Object> rowMap = new HashMap<>();
                rowMap.put("id_post", rs.getObject(1));
                rowMap.put("content", rs.getString(2));
                rowMap.put("img", rs.getString(3));
                rowMap.put("like_num", rs.getInt(4));
                rowMap.put("comment", rs.getInt(5));
                rowMap.put("user_name", rs.getString(6));
                rowMap.put("createdTime", rs.getDate(7));
                rowMap.put("id_user", rs.getString(8));
                rowMap.put("first_name", rs.getString(15));
                rowMap.put("last_name", rs.getString(16));
                rowMap.put("avatar", rs.getString(17));
                list.add(rowMap);
            }
            JDBCUtil.closeConnection(connection);
        } catch (SQLException e) {
            System.out.println("errror:: " + e.getMessage());
        }
        return list;
    }
    public List<Map<String, Object>> selectAllPostByUserName(String userName) {
        List<Map<String, Object>> list = new ArrayList<>();
        try {
            Connection connection = JDBCUtil.getConnection();
            Statement stmt = connection.createStatement();
            // get data from table 'customer'
            ResultSet rs = stmt.executeQuery("select * from POST p , user_account u WHERE p.user_name = u.user_name and u.user_name = '" + userName +"' ORDER BY createdTime DESC;");
            // map customer data
            while (rs.next()) {
                Map<String, Object> rowMap = new HashMap<>();
                rowMap.put("id_post", rs.getObject(1));
                rowMap.put("content", rs.getString(2));
                rowMap.put("img", rs.getString(3));
                rowMap.put("like_num", rs.getInt(4));
                rowMap.put("comment", rs.getInt(5));
                rowMap.put("user_name", rs.getString(6));
                rowMap.put("createdTime", rs.getDate(7));
                rowMap.put("id_user", rs.getString(8));
                rowMap.put("first_name", rs.getString(15));
                rowMap.put("last_name", rs.getString(16));
                rowMap.put("avatar", rs.getString(17));
                list.add(rowMap);
            }
            JDBCUtil.closeConnection(connection);
        } catch (SQLException e) {
            System.out.println("errror:: " + e.getMessage());
        }
        return list;
    }

    public int deleteByID(int id) {

        try {
            Connection connection = JDBCUtil.getConnection();
            Statement stmt = connection.createStatement();

            String insertQuery = "DELETE FROM POST WHERE id_post = ?";
            // Tạo PreparedStatement
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement(insertQuery);
            // Thiết lập các tham số trong truy vấn
            preparedStatement.setInt(1, id);

            // Thực hiện delete
            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted;
        } catch (SQLException ex) {
            System.out.println("error: " + ex.getMessage());
        }
        return 0;

    }

    //like
    public int insertLikeByID(int id_post, String user_name) {
        try {
            Connection connection = JDBCUtil.getConnection();
            String insertQuery = "INSERT INTO LIKE_status (id_post,user_name)"
                    + "VALUES ( ?, ?)";
            // Tạo PreparedStatement
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement(insertQuery);
            // Thiết lập các tham số trong truy vấn
            preparedStatement.setInt(1, id_post);
            preparedStatement.setString(2, user_name);
            // Thực hiện INSERT
            int rowsInserted = preparedStatement.executeUpdate();
            JDBCUtil.closeConnection(connection);
            return rowsInserted;
        } catch (SQLException ex) {
            System.out.println("error: " + ex.getMessage());
        }
        return 0;
    }

    public int deleteLikeByID(int id_post, String user_name) {

        try {
            Connection connection = JDBCUtil.getConnection();
            Statement stmt = connection.createStatement();

            String insertQuery = "DELETE FROM LIKE_status WHERE user_name = ? and id_post = ?";
            // Tạo PreparedStatement
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement(insertQuery);
            // Thiết lập các tham số trong truy vấn
            preparedStatement.setString(1, user_name);
            preparedStatement.setInt(2, id_post);
            // Thực hiện delete
            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted;
        } catch (SQLException ex) {
            System.out.println("error: " + ex.getMessage());
        }
        return 0;
    }

    public List<Like> selectLikeByID(int postID, String user_name) {
        List<Like> list = new ArrayList<>();

        try {
            Connection connection = JDBCUtil.getConnection();
            Statement stmt = connection.createStatement();
            // get data from table 'customer'
            ResultSet rs = stmt.executeQuery("  select * from LIKE_status WHERE user_name= '" + user_name + "' and id_post = " + postID);
            // map customer data
            while (rs.next()) {
                Like like = new Like();
                like.setPostID(rs.getString(1));
                like.setUser_name(rs.getString(2));
                list.add(like);
                break;
            }
            JDBCUtil.closeConnection(connection);
        } catch (SQLException e) {
            System.out.println("errror:: " + e.getMessage());
        }
        return list;
    }

    public List<Like> selectAllLikeByID(int postID) {
        List<Like> list = new ArrayList<>();

        try {
            Connection connection = JDBCUtil.getConnection();
            Statement stmt = connection.createStatement();
            // get data from table 'customer'
            ResultSet rs = stmt.executeQuery("  select * from LIKE_status WHERE id_post = " + postID);
            // map customer data
            while (rs.next()) {
                Like like = new Like();
                like.setPostID(rs.getString(1));
                like.setUser_name(rs.getString(2));
                list.add(like);
            }
            JDBCUtil.closeConnection(connection);
        } catch (SQLException e) {
            System.out.println("errror:: " + e.getMessage());
        }
        return list;
    }

    //comment
    public int insertComment(int id_post, String user_name, String content) {
        try {
            Connection connection = JDBCUtil.getConnection();
            String insertQuery = "INSERT INTO comment_status (id_post, user_name, content)"
                    + "VALUES (?, ?, ?)";
            // Tạo PreparedStatement
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement(insertQuery);
            // Thiết lập các tham số trong truy vấn
            preparedStatement.setInt(1, id_post);
            preparedStatement.setString(2, user_name);
            preparedStatement.setString(3, content);
            // Thực hiện INSERT
            int rowsInserted = preparedStatement.executeUpdate();
            JDBCUtil.closeConnection(connection);
            return rowsInserted;
        } catch (SQLException ex) {
            System.out.println("error: " + ex.getMessage());
        }
        return 0;
    }

    public int deleteComment(int id_comment) {
        try {
            Connection connection = JDBCUtil.getConnection();
            String insertQuery = "DELETE from comment_status where id_comment = ?";
            // Tạo PreparedStatement
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement(insertQuery);
            // Thiết lập các tham số trong truy vấn
            preparedStatement.setInt(1, id_comment);
            // Thực hiện DELETE
            int rowsInserted = preparedStatement.executeUpdate();
            JDBCUtil.closeConnection(connection);
            return rowsInserted;
        } catch (SQLException ex) {
            System.out.println("error: " + ex.getMessage());
        }
        return 0;
    }

    public List<Comment> selectAllComment(int id_post) {
        List<Comment> list = new ArrayList<>();

        try {
            Connection connection = JDBCUtil.getConnection();
            Statement stmt = connection.createStatement();
            // get data from table 'customer'
            ResultSet rs = stmt.executeQuery("  select * from comment_status WHERE id_post = " + id_post + " ORDER BY createdTime DESC");
            // map customer data
            while (rs.next()) {
                Comment comment = new Comment();
                comment.setIdComment(rs.getInt(1));
                comment.setIdPost(rs.getInt(2));
                comment.setUserName(rs.getString(3));
                comment.setContent(rs.getString(4));
                comment.setCreatedTime(rs.getTimestamp(5));
                list.add(comment);
            }
            JDBCUtil.closeConnection(connection);
        } catch (SQLException e) {
            System.out.println("errror:: " + e.getMessage());
        }
        return list;
    }
}
