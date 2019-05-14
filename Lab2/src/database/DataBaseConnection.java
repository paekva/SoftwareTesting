package database;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnection {

    static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/dictionary";
    static final String USER = "postgres";
    static final String PASS = "rfnz98grf";

    private Connection connection;

    public void connect() throws SQLException {
        connection = DriverManager
                .getConnection(DB_URL, USER, PASS);
    }

    void insert(String SQL, List<String> args){
        try{
            PreparedStatement pstmt = connection.prepareStatement(SQL);
            for(int i=0;i<args.size();i++){
                pstmt.setString(i+1, args.get(i));
            }
            pstmt.executeUpdate();
        }
        catch (SQLException ex){
            System.out.println("An error in insert:");
            System.out.println(ex.getMessage());
        }
    }

    ResultSet select(String SQL, List<String> args){
        try {
            PreparedStatement pstmt = connection.prepareStatement(SQL);
            for(int i=0;i<args.size();i++){
                pstmt.setString(i+1, args.get(i));
            }

            System.out.println(pstmt);
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

    public List<String> getMeanings(String root) {
        List<String> result = new ArrayList<>();
        String SQL = "SELECT DISTINCT * FROM words "
                + "WHERE root = ? ";
        List<String> args = new ArrayList<>();
        args.add(root);
        ResultSet rs = select(SQL, args);

        try{
            if(rs == null){
                System.out.println(rs);
                return result;
            }

            while(rs.next()) {
                result.add( getWord(rs).getMeaning() );
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
                strings.add(new Word( rs.getString("word"), rs.getString("root"), ""));
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

    public void changeOrigin(String word, String origin, String originLanguage){
        String SQL = "UPDATE words "
                + "SET origin = ?,"
                + "origin_lang = ?"
                + "WHERE word = ?";
        try(PreparedStatement pstmt = connection.prepareStatement(SQL)) {
            pstmt.setString(1, origin);
            pstmt.setString(2, originLanguage);
            pstmt.setString(3, word);
            pstmt.executeUpdate();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
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
        String word="", root="", meaning = "", partOfSpeech="";
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
            meaning = rs.getString("meaning");
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return new Word(word, root, meaning, partOfSpeech);
    }
}