import java.sql.*;

public class DataBased {

    static String url = "jdbc:mysql://localhost:3306/chainsawdb?allowPublicKeyRetrieval=true&useSSL=false";
    static String user = "root";
    static String password = "eeppow123";

    public static void selectPerson() {
        String sql = "SELECT ID, Name, isDeceased, Description FROM people where id=(?) and name=(?) and isDeceased=(?) and description=(?)";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, 1);
            statement.setString(2, "Denji");
            statement.setBoolean(3, false);
            statement.setString(4, "He's a chainsaw guy");

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                System.out.println(result.getInt("id") + " " + result.getString("name") + " " + result.getBoolean("isDeceased") + " " + result.getString("description"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
