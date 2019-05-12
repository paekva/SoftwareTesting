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

    public Word getWord(String word){
        String st = "SELECT * from words WHERE word="+changeRepresentation( word );
        ResultSet rs = query(st);
        Word w = new Word("","");
        try{
            w = getWord(rs);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return w;
    }

    public List<Word> findSameRootWords(Word word)
    {
        List<Word> strings = new ArrayList<>();
        try{
            String st = "SELECT word, root from words WHERE root="+changeRepresentation( word.getRoot() )+" and not word="+changeRepresentation( word.getWord() );
            ResultSet rs = query(st);

            while (rs.next()) {
                strings.add(new Word( rs.getString("word"), rs.getString("root")));
            }

        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }

        return strings;
    }

    public void changePartOfSpeech(String word, String partOfSpeech){
        String SQL = "UPDATE words "
                + "SET partofspeech = ? "
                + "WHERE word = ?";
        try(PreparedStatement pstmt = connection.prepareStatement(SQL)) {

                pstmt.setString(1, partOfSpeech);
                pstmt.setString(2, word);
                pstmt.executeUpdate();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void insert(Word word){
        try{
            String st = "INSERT INTO words " + "(word, root, adddate, searched)"
                    + " VALUES ("
                    + changeRepresentation( word.getWord() )+","
                    + changeRepresentation( word.getRoot() )+","
                    + changeRepresentation( word.getDate().toString() )+","
                    + word.getSearched()
                    +")";

            Statement statement = connection.createStatement();
            statement.executeUpdate(st);

        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    private ResultSet query(String query){
        try {
            Statement statement = connection.createStatement();
            return statement.executeQuery(query);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    private String changeRepresentation(String str){
        return "\'"+str+"\'";
    }

    private Word getWord(ResultSet rs){
        String word="", root="", partOfSpeech="";
        try{
            rs.next();
            word = rs.getString("word");
            root = rs.getString("root");
            partOfSpeech = rs.getString("partofspeech");
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return new Word(word, root, partOfSpeech);
    }
}