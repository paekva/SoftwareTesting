package database;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBaseConnection {

    static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/dictionary";
    static final String USER = "postgres";
    static final String PASS = "rfnz98grf";
    private Connection connection;

    public void connect() throws SQLException {
        connection = DriverManager
                .getConnection(DB_URL, USER, PASS);
    }

    public void insert(Word word){
        try{
            String st = "INSERT INTO words " + "(word, root, adddate, searched)"
                    + " VALUES ("
                    + word.getWord()+","
                    + word.getRoot()+","
                    + word.getAddTime()+","
                    + word.getSearched()
                    +")";
            System.out.println(st);
            Statement statement = connection.createStatement();
            statement.executeUpdate(st);

        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    public List<String> findSameRootWords(Word word)
    {
        List<String> strings = new ArrayList<>();
        try{
            String st = "SELECT * from words WHERE root="+word.getRoot()+" and not word="+word.getWord();
            System.out.println(st);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(st);

            while (rs.next()) {
                strings.add(rs.getString("word"));
            }

        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }

        return strings;
    }
}