package edu.fgcu.dataengineering;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;

import java.sql.*;
public class Main {

    public static void main(String[] args) throws IOException, CsvValidationException, SQLException {
        CsvParser csvP = new CsvParser("src/Data/bookstore_report2.csv");
        csvP.printCsv();


        Gson gson = new Gson();
        JsonReader jread = new JsonReader(new FileReader("src/Data/authors.json"));
        AuthorParser[] authors = gson.fromJson(jread, AuthorParser[].class);

        DataBaseManager dbm = new DataBaseManager();

        for (var element : authors) {
            System.out.println(element.getName());
            dbm.newAuthor(element.getName(), element.getEmail(), element.getUrl());
        }
        ResultSet rs = dbm.getProductsRS();
        while (rs.next()){
            System.out.println(rs.getString("AuthorName"));
        }

    }
}
//d