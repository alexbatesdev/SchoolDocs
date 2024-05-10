import java.sql.*;

public class Program {
    static String url = "jdbc:mysql://localhost:3306/testdb?allowPublicKeyRetrieval=true&useSSL=false";
    static String user = "root";
    static String password = "eeppow123";
    public static void main(String[] args) {

        //testConnection();
        //selectAuthorsSC();
        //selectAuthorsPS();
        //CreateAuthor();
        //updateBook();
        //deleteBook();
    }

    public static void deleteBook() {
        String sql = "delete from books where id=(?)";

        try {
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setInt(1, 1);
            pst.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public static void updateBook() {
        String sql = "update books set title=(?) where id=(?)";

        try {
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, "Ready Player Two");
            pst.setInt(2, 2);
            pst.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void CreateAuthor() {
        String sql = "INSERT INTO testdb.authors(Name) Values(?)";

        try {
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, "Skibbity Bop Wow");
            pst.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void selectAuthorsPS() {
        String sql = "Select id, name from authors where id=(?) and name=(?)";

        try {
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, 4);
            pst.setString(2, "Skibbity");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                System.out.print(rs.getInt("Id")); // or rs.getInt(1);
                System.out.print(": ");
                System.out.println(rs.getString("name"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void selectAuthorsSC() {
        String sql = "Select id, name from authors where Id=" + "4";

        try {
            Connection con = DriverManager.getConnection(url, user, password);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                System.out.print(rs.getInt("Id")); // or rs.getInt(1);
                System.out.print(": ");
                System.out.println(rs.getString("name"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void testConnection() {

        String sql = "Select Version()";

        try {
            Connection con = DriverManager.getConnection(url, user, password);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                System.out.println(rs.getString(1));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
