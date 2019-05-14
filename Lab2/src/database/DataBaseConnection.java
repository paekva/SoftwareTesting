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

    public void insert(String SQL, List<String> args){
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

    public ResultSet select(String SQL, List<String> args){
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

    // TODO: исправить, что он ищет в колонках, не проверяя их наличие
    Word getWord(ResultSet rs){
        String word="", root="", meaning = "", partOfSpeech="";
        try{
            ResultSetMetaData rsMetaData = rs.getMetaData();
            int numberOfColumns = rsMetaData.getColumnCount();

            /*for (int i = 1; i < numberOfColumns + 1; i++) {
                String columnName = rsMetaData.getColumnName(i);

                if ("partofspeech".equals(columnName)) {
                    partOfSpeech = rs.getString("partofspeech");
                }
            }*/

            word = rs.getString("word");
            root = rs.getString("root");
            meaning = rs.getString("meaning");
        }
        catch(Exception e){
            System.out.println("Error in getWord");
            System.out.println(e.getMessage());
        }
        return new Word(word, root, meaning, partOfSpeech);
    }
}