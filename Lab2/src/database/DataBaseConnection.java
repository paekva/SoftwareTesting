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

    public Boolean checkForWordInDictionary(Word word){
        try{
            String SQL = "SELECT * from words "
                    + "WHERE word = ? ";
            List<String> args = new ArrayList<>();
            args.add(word.getWord());
            ResultSet rs = select(SQL, args);

            Boolean tmp = rs.next();
            System.out.println("in dict "+tmp);
            return tmp;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    private void insert(String SQL, List<String> args){
        try{
            PreparedStatement pstmt = connection.prepareStatement(SQL);
            for(int i=0;i<args.size();i++){
                pstmt.setString(i+1, args.get(i));
            }

            pstmt.executeUpdate();
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    private ResultSet select(String SQL, List<String> args){
        try {
            PreparedStatement pstmt = connection.prepareStatement(SQL);
            for(int i=0;i<args.size();i++){
                pstmt.setString(i+1, args.get(i));
            }

            return pstmt.executeQuery();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Word getWord(String word){
        Word result = null;
        String SQL = "SELECT * from words "
                + "WHERE word = ? ";
        List<String> args = new ArrayList<>();
        args.add(word);
        ResultSet rs = select(SQL, args);

        try{
            if(rs == null)
                return result;

            while(rs.next()) {
                result = getWord(rs);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

        return result;
    }

    public List<Word> findSameRootWords(Word word)
    {
        List<Word> strings = new ArrayList<>();
        try{
            String SQL = "SELECT word, root from words "
                    + "WHERE root = ? "
                    + "and not word = ?";

            List<String> args = new ArrayList<String>();
            args.add(word.getRoot());
            args.add(word.getWord());

            ResultSet rs = select(SQL, args);

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

    public void addWord(Word word){
        String st = "INSERT INTO words " + "(word, root, adddate, searched)"
                + " VALUES (?, ?," + changeRepresentation( word.getDate().toString() ) +","+word.getSearched()+")";

        List<String> args = new ArrayList<>();
        args.add(word.getWord());
        args.add(word.getRoot());

        insert(st, args);
    }

    public void addPhrase(String phrase){
        String st = "INSERT INTO phrases " + "(phrase)"
                + " VALUES (?)";

        List<String> args = new ArrayList<>();
        args.add(phrase);

        insert(st, args);
    }

    private String changeRepresentation(String str){
        return "\'"+str+"\'";
    }

    private Word getWord(ResultSet rs){
        String word="", root="", partOfSpeech="";
        try{
            ResultSetMetaData rsMetaData = rs.getMetaData();
            int numberOfColumns = rsMetaData.getColumnCount();

            for (int i = 1; i < numberOfColumns + 1; i++) {
                String columnName = rsMetaData.getColumnName(i);

                if ("partofspeech".equals(columnName)) {
                    partOfSpeech = rs.getString("partofspeech");
                }
            }

            word = rs.getString("word");
            root = rs.getString("root");
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return new Word(word, root, partOfSpeech);
    }
}