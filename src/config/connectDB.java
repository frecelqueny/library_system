    package config;

    import com.mysql.jdbc.Connection;
    import com.mysql.jdbc.Statement;
    import java.sql.DriverManager;
    import java.sql.PreparedStatement;
    import java.sql.ResultSet;
    import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

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
        
         public void insertLog(int userId, String action) {
            try {
                java.sql.Connection cn = getConnection();
                String sql = "INSERT INTO logs (user_id, action, date_time) VALUES (?, ?, ?)";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, userId);
                pst.setString(2, action);
                pst.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
                pst.executeUpdate();
                pst.close();
                cn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
