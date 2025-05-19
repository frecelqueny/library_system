package config;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class connectDB {
    private Connection connect;
    public connectDB(){
        try{
            connect = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/frecel", "root", "");
        }catch(SQLException ex){
                System.out.println("Can't connect to database: " + ex.getMessage());
        }
    }
    public java.sql.Connection getConnection() {
        return connect;
    }
        
    public ResultSet getData(String sql) throws SQLException{
        Statement stmt = (Statement) connect.createStatement();
        ResultSet rst = stmt.executeQuery(sql);
        return rst;
    }
    public int InsertData(String sql){
        int result;
        try{
            PreparedStatement pst = connect.prepareStatement(sql);
            pst.executeUpdate();
            System.out.println("Inserted Successfully! ");
            pst.close();
            result = 1;
        }catch(SQLException ex){
            System.out.println("Connection Error: "+ex);
            result = 0;
        }
        return result;
    }
    public int UpdateData(String query) {
    try {
        Statement stmt = (Statement) getConnection().createStatement();
        return stmt.executeUpdate(query);
    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
        e.printStackTrace();
        return 0;
    }
}
}
