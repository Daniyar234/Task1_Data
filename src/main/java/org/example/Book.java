package org.example;

public class Book {

    private String id;
    private String title;
    private String author;
    private String genre;
    private String publisher;
    private Integer year;
    private String price;


    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", publisher='" + publisher + '\'' +
                ", year=" + year +
                ", price='" + price + '\'' +
                '}';
    }
    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getGenre() { return genre; }
    public String getPublisher() { return publisher; }
    public Integer getYear() { return year; }
    public String getPrice() { return price; }

    public void setId(String id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
    public void setGenre(String genre) { this.genre = genre; }
    public void setPublisher(String publisher) { this.publisher = publisher; }
    public void setYear(Integer year) { this.year = year; }
    public void setPrice(String price) { this.price = price; }
}
