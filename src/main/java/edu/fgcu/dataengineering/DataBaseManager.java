package edu.fgcu.dataengineering;

import java.sql.*;

public class DataBaseManager {

    private Connection con = null;

    public DataBaseManager() throws SQLException {
        con = DriverManager.getConnection("jdbc:sqlite:src/Data/BookStore.db");

        createAuthorTable();
    }



    private void createAuthorTable(){
        try {

            Statement stmnt = con.createStatement();
            stmnt.executeUpdate(("CREATE TABLE IF NOT EXISTS authors" +
                    " (AuthorID INTEGER NOT NULL AUTO_INCREMENT," +
                    " AuthorName VARCHAR(255)," +
                    " AuthorEmail VARCHAR(255)," +
                    "AuthorURL VARCHAR(255)," +
                    "PRIMARY KEY (AuthorID));"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void newAuthor(String name, String email, String url) {
        String query = "insert into authors(AuthorName, AuthorEmail, AuthorURL)" +
                " VALUES " + "(?, ?, ?);";

        try {
            PreparedStatement PrepS = con.prepareStatement(query);
            PrepS.setString(1, name);
            PrepS.setString(2, email);
            PrepS.setString(3, url);


            PrepS.executeUpdate();
            PrepS.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getProductsRS() {
        ResultSet rs = null;

        try {
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM authors;");
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
        return rs;
    }

    private void sqlExceptionHandler(SQLException e) {
        System.out.println(e);
    }
}
