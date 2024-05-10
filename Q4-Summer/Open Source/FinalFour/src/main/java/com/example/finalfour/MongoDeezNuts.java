package com.example.finalfour;

import java.sql.*;

public class MongoDeezNuts {
    static final private String url = "jdbc:mysql://localhost:3306/javafxlogin?allowPublicKeyRetrieval=true&useSSL=false";
    static final private String user = "root";
    static final private String password = "eeppow123";
    private Connection con;
    private PreparedStatement statement;
    private int paramIndex = 1;

    public MongoDeezNuts() {
        connect();
    }

    public void connect(String url_in, String user_in, String password_in) {
        try {
            con = DriverManager.getConnection(url_in, user_in, password_in);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void connect() {
        try {
            con = DriverManager.getConnection(url, user, password);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public boolean prep(String sql_in) {
        try {
            statement = con.prepareStatement(sql_in);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean addParam(String str_in) {
        try {
            statement.setString(paramIndex, str_in);
            paramIndex++;
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean addParam(int int_in) {
        try {
            statement.setInt(paramIndex, int_in);
            paramIndex++;
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean go() {
        paramIndex = 1;
        try {
            statement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public ResultSet get() {
        paramIndex = 1;
        ResultSet results = null;
        try {
            results = statement.executeQuery();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return results;
    }

    public void addAccount(String firstName, String lastName, String email, String username, String password_in) {
        String sql = "insert into accounts (FirstName, LastName, Email, Username, Password) VALUES (?, ?, ?, ?, ?);";
        prep(sql);
        addParam(firstName);
        addParam(lastName);
        addParam(email);
        addParam(username);
        addParam(password_in);
        go();
    }

    public ResultSet getCreds(String username) {
        String sql = "select * from accounts where Username = ?";
        prep(sql);
        addParam(username);
        return get();
    }

    public ResultSet getAll(String username) throws SQLException {
        String sql = "select * from javafxlogin.accounts";
        statement = con.prepareStatement(sql);
        return statement.executeQuery();
    }

    public void insertScore(String username, int score, int missed, int tricked) {
        String sql = "insert into leaderboard (Username, Score, Missed, Tricked) VALUES (?, ?, ?, ?);";
        prep(sql);
        addParam(username);
        addParam(score);
        addParam(missed);
        addParam(tricked);
        go();
    }

    public ResultSet getLeaderBoard() throws SQLException {
        String sql = "select * from javafxlogin.leaderboard";
        statement = con.prepareStatement(sql);
        return statement.executeQuery();
    }



    public void clear() throws SQLException {
        String sql = "delete from javafxlogin.leaderboard";
        statement = con.prepareStatement(sql);
        statement.executeUpdate();
    }

    public boolean verifyUsername(String username) {
        String sql = "select * from accounts where Username = ?";
        prep(sql);
        addParam(username);
        ResultSet results = get();
        try {
            if (results.next()) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
