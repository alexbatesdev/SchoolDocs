package com.example.fantasyapi;

import com.example.fantasyapi.Models.Devil;
import com.example.fantasyapi.Models.Location;
import com.example.fantasyapi.Models.Person;
import com.example.fantasyapi.Models.User;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseConnect {
    static String url = "jdbc:mysql://localhost:3306/chainsawdb?allowPublicKeyRetrieval=true&useSSL=false";
    static String user = "root";
    static String password = "eeppow123";



    /* People */
    //Create
    public static void insertPerson(Person person) {
        String sql = "INSERT INTO people (name, isDeceased, description) VALUES (?, ?, ?)";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, person.getName());
            statement.setBoolean(2, person.getIsDeceased());
            statement.setString(3, person.getDescription());

            statement.executeUpdate();
        } catch (Exception e) {
            System.err.println("Oops");
        }
    }

    public static void insertPerson(String name, boolean isDeceased, String description) {
        String sql = "INSERT INTO people (name, isDeceased, description) VALUES (?, ?, ?)";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, name);
            statement.setBoolean(2, isDeceased);
            statement.setString(3, description);

            statement.executeUpdate();
        } catch (Exception e) {
            System.err.println("Oops " + e);
        }
    }

    public static void insertPeople(ArrayList<Person> people) {
        String sql = "INSERT INTO people (name, isDeceased, description) VALUES (?, ?, ?)";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement statement = conn.prepareStatement(sql);

            for (Person person : people) {
                statement.setString(1, person.getName());
                statement.setBoolean(2, person.getIsDeceased());
                statement.setString(3, person.getName());

                statement.executeUpdate();
            }
        } catch (Exception e) {
            System.err.println("Oops");
        }
    }

    //Read
    public static Person selectPerson(int id) {
        String sql = "SELECT * FROM people where id=(?)";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet result = statement.executeQuery();

            Person person = null;
            while (result.next()) {
                person = new Person(result.getInt("id"), result.getString("name"), result.getBoolean("isDeceased"), result.getString("description"));
            }
            return person;
        } catch (Exception e) {
            System.err.println("Oops");
            return null;
        }
    }
    
    public static ArrayList<Person> selectAllPeople() {
        String sql = "SELECT * FROM people";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement statement = conn.prepareStatement(sql);

            ResultSet result = statement.executeQuery();

            ArrayList<Person> people = new ArrayList<Person>();
            while (result.next()) {
                people.add(new Person(result.getInt("id"), result.getString("name"), result.getBoolean("isDeceased"), result.getString("description")));
            }
            return people;
        } catch (Exception e) {
            System.err.println("Oops " + e);
            return null;
        }
    }

    //Update
    public static void updatePerson(Person person) {
        String sql = "UPDATE people SET name=(?), isDeceased=(?), description=(?) WHERE id=(?)";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, person.getName());
            statement.setBoolean(2, person.getIsDeceased());
            statement.setString(3, person.getDescription());
            statement.setInt(4, person.getPersonId());

            statement.executeUpdate();
        } catch (Exception e) {
            System.err.println("Oops");
        }
    }

    public static void updatePerson(int id, String name, boolean isDeceased, String description) {
        String sql = "UPDATE people SET name=(?), isDeceased=(?), description=(?) WHERE id=(?)";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, name);
            statement.setBoolean(2, isDeceased);
            statement.setString(3, description);
            statement.setInt(4, id);

            statement.executeUpdate();
        } catch (Exception e) {
            System.err.println("Oops");
        }
    }

    //Delete
    public static void deletePerson(int id) {
        String sql = "DELETE FROM people WHERE id=(?)";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setInt(1, id);

            statement.executeUpdate();
        } catch (Exception e) {
            System.err.println("Oops");
        }
    }



    /* Devils */
    //Create
    public static void insertDevil(Devil devil) {
        String sql = "INSERT INTO devils (name, type, description) VALUES (?, ?, ?)";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, devil.getName());
            statement.setString(2, devil.getType());
            statement.setString(3, devil.getDescription());

            statement.executeUpdate();
        } catch (Exception e) {
            System.err.println("Oops");
        }
    }

    public static void insertDevil(String name, String type, String description) {
        String sql = "INSERT INTO devils (name, type, description) VALUES (?, ?, ?)";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, name);
            statement.setString(2, type);
            statement.setString(3, description);

            statement.executeUpdate();
        } catch (Exception e) {
            System.err.println("Oops " + e);
        }
    }

    public static void insertDevils(ArrayList<Devil> devils) {
        String sql = "INSERT INTO devils (name, type, description) VALUES (?, ?, ?)";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement statement = conn.prepareStatement(sql);

            for (Devil devil : devils) {
                statement.setString(1, devil.getName());
                statement.setString(2, devil.getType());
                statement.setString(3, devil.getDescription());

                statement.executeUpdate();
            }
        } catch (Exception e) {
            System.err.println("Oops");
        }
    }

    //Read
    public static Devil selectDevil(int id) {
        String sql = "SELECT * FROM devils where id=(?)";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet result = statement.executeQuery();

            Devil devil = null;
            while (result.next()) {
                devil = new Devil(result.getInt("id"), result.getString("name"), result.getString("type"), result.getString("description"));
            }
            return devil;
        } catch (Exception e) {
            System.err.println("Oops");
            return null;
        }
        
    }
    
    public static ArrayList<Devil> selectAllDevils() {
        String sql = "SELECT * FROM devils";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement statement = conn.prepareStatement(sql);

            ResultSet result = statement.executeQuery();

            ArrayList<Devil> devils = new ArrayList<Devil>();
            while (result.next()) {
                devils.add(new Devil(result.getInt("id"), result.getString("name"), result.getString("description"), result.getString("type")));
            }
            return devils;
        } catch (Exception e) {
            System.err.println("Oops " + e);
            return null;
        }
    }

    //Update
    public static void updateDevil(Devil devil) {
        String sql = "UPDATE devils SET name=(?), type=(?), description=(?) WHERE id=(?)";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, devil.getName());
            statement.setString(2, devil.getType());
            statement.setString(3, devil.getDescription());
            statement.setInt(4, devil.getDevilId());

            statement.executeUpdate();
        } catch (Exception e) {
            System.err.println("Oops");
        }
    }

    public static void updateDevil(int id, String name, String type, String description) {
        String sql = "UPDATE devils SET name=(?), type=(?), description=(?) WHERE id=(?)";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, name);
            statement.setString(2, type);
            statement.setString(3, description);
            statement.setInt(4, id);

            statement.executeUpdate();
        } catch (Exception e) {
            System.err.println("Oops");
        }
    }

    //Delete
    public static void deleteDevil(int id) {
        String sql = "DELETE FROM devils WHERE id=(?)";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setInt(1, id);

            statement.executeUpdate();
        } catch (Exception e) {
            System.err.println("Oops");
        }
    }



    /* Locations */
    //Create
    public static void insertLocation(Location location) {
        String sql = "INSERT INTO locations (name, description) VALUES (?, ?)";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, location.getName());
            statement.setString(2, location.getDescription());

            statement.executeUpdate();
        } catch (Exception e) {
            System.err.println("Oops");
        }
    }

    public static void insertLocation(String name, String description) {
        String sql = "INSERT INTO locations (name, description) VALUES (?, ?)";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, name);
            statement.setString(2, description);

            statement.executeUpdate();
        } catch (Exception e) {
            System.err.println("Oops " + e);
        }
    }

    public static void insertLocations(ArrayList<Location> locations) {
        String sql = "INSERT INTO locations (name, description) VALUES (?, ?)";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement statement = conn.prepareStatement(sql);

            for (Location location : locations) {
                statement.setString(1, location.getName());
                statement.setString(2, location.getDescription());

                statement.executeUpdate();
            }
        } catch (Exception e) {
            System.err.println("Oops");
        }
    }

    //Read
    public static Location selectLocation(int id) {
        String sql = "SELECT * FROM locations where id=(?)";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet result = statement.executeQuery();

            Location location = null;
            while (result.next()) {
                location = new Location(result.getInt("id"), result.getString("name"), result.getString("description"));
            }
            return location;
        } catch (Exception e) {
            System.err.println("Oops");
            return null;
        }
    }
    
    public static ArrayList<Location> selectAllLocations() {
        String sql = "SELECT * FROM locations";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement statement = conn.prepareStatement(sql);

            ResultSet result = statement.executeQuery();

            ArrayList<Location> locations = new ArrayList<Location>();
            while (result.next()) {
                locations.add(new Location(result.getInt("id"), result.getString("name"), result.getString("description")));
            }
            return locations;
        } catch (Exception e) {
            System.err.println("Oops");
            return null;
        }
    }

    //Update
    public static void updateLocation(Location location) {
        String sql = "UPDATE locations SET name=(?), description=(?) WHERE id=(?)";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, location.getName());
            statement.setString(2, location.getDescription());
            statement.setInt(3, location.getLocationId());

            statement.executeUpdate();
        } catch (Exception e) {
            System.err.println("Oops");
        }
    }

    public static void updateLocation(int id, String name, String description) {
        String sql = "UPDATE locations SET name=(?), description=(?) WHERE id=(?)";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, name);
            statement.setString(2, description);
            statement.setInt(3, id);

            statement.executeUpdate();
        } catch (Exception e) {
            System.err.println("Oops");
        }
    }

    //Delete
    public static void deleteLocation(int id) {
        String sql = "DELETE FROM locations WHERE id=(?)";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setInt(1, id);

            statement.executeUpdate();
        } catch (Exception e) {
            System.err.println("Oops");
        }
    }
    
    
    
    // //////////////////////////////////////////////////////

    /* Users */
    //Create
    public static void insertUser(String username, String password) {
        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, username);
            statement.setString(2, password);

            statement.executeUpdate();
        } catch (Exception e) {
            System.err.println("Oops");
        }
    }

    public static void insertUser(User userAcc) {
        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, userAcc.getUsername());
            statement.setString(2, userAcc.getPassword());

            statement.executeUpdate();
        } catch (Exception e) {
            System.err.println("Oops");
        }
    }

    //Read
    public static User selectUser(String username) {
        String sql = "SELECT * FROM users where username=(?)";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, username);

            ResultSet result = statement.executeQuery();

            User user = null;
            while (result.next()) {
                user = new User(result.getInt("id"), result.getString("username"), result.getString("password"));
            }
            return user;
        } catch (Exception e) {
            System.err.println("Oops");
            return null;
        }
    }

    public static ArrayList<User> selectAllUsers() {
        String sql = "SELECT * FROM users";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement statement = conn.prepareStatement(sql);

            ResultSet result = statement.executeQuery();

            ArrayList<User> users = new ArrayList<User>();
            while (result.next()) {
                users.add(new User(result.getInt("id"), result.getString("username"), result.getString("password")));
            }
            return users;
        } catch (Exception e) {
            System.err.println("Oops");
            return null;
        }
    }

    //Update
    public static void updateUser(User userAcc) {
        String sql = "UPDATE users SET username=(?), password=(?) WHERE id=(?)";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, userAcc.getUsername());
            statement.setString(2, userAcc.getPassword());
            statement.setInt(3, userAcc.getUserId());

            statement.executeUpdate();
        } catch (Exception e) {
            System.err.println("Oops");
        }
    }

    public static void updateUser(int id, String username, String password) {
        String sql = "UPDATE users SET username=(?), password=(?) WHERE id=(?)";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, username);
            statement.setString(2, password);
            statement.setInt(3, id);

            statement.executeUpdate();
        } catch (Exception e) {
            System.err.println("Oops");
        }
    }

    //Delete
    public static void deleteUser(int id) {
        String sql = "DELETE FROM users WHERE id=(?)";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setInt(1, id);

            statement.executeUpdate();
        } catch (Exception e) {
            System.err.println("Oops");
        }
    }
}
