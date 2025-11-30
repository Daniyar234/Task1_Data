package org.example;

import java.sql.*;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        List<Book> books = new ParseJson().parse();
        Connection conn = DriverManager.getConnection("jdbc:sqlite:books.db");
        Statement st = conn.createStatement();

        st.execute("""
                CREATE TABLE IF NOT EXISTS books (
                    id TEXT PRIMARY KEY, title TEXT, author TEXT, genre TEXT, publisher TEXT, year INTEGER,price TEXT)""");

        PreparedStatement ps = conn.prepareStatement(
                "INSERT OR IGNORE INTO books(id, title, author, genre, publisher, year, price) VALUES (?, ?, ?, ?, ?, ?, ?)"
        );
        for(Book b : books){
            ps.setString(1, b.getId());
            ps.setString(2, b.getTitle());
            ps.setString(3, b.getAuthor());
            ps.setString(4, b.getGenre());
            ps.setString(5, b.getPublisher());
            ps.setInt(6, b.getYear());
            ps.setString(7, b.getPrice());
            ps.addBatch();
        }
        ps.executeBatch();

        st.execute("""
                CREATE TABLE IF NOT EXISTS summary AS
                SELECT year AS publication_year, COUNT(*) AS book_count, ROUND(AVG( CASE
                               WHEN price LIKE '€%' THEN CAST(SUBSTR(price, 2) AS REAL) * 1.2
                               WHEN price LIKE '$%' THEN CAST(SUBSTR(price, 2) AS REAL)
                               ELSE 0 END), 2) AS average_price_usd
                FROM books GROUP BY year ORDER BY year""");

        ResultSet rs = st.executeQuery("SELECT * FROM summary");
        while(rs.next()){
            System.out.printf("%4d | %5d | %.2f\n", rs.getInt("publication_year"),
                    rs.getInt("book_count"),
                    rs.getDouble("average_price_usd")
            );
        }
        conn.close();
    }
}
