package storage;

import model.Book;

public class BookStorage {
    private static Book[] books = new Book[10];

    private int size = 0;

    public void add(Book book){
        if(books.length == size){
            extend();
        }
        books[size++] = book;
    }

    private void extend(){
        Book[] temp = new Book[books.length + 10];
        for(int i = 0; i < size; i++){
            temp[i] = books[i];
        }
        books = temp;
    }

    public void printBooksByAuthorName(String authorName){
        for(int i = 0; i < size; i++){
            if(books[i].getAuthor().equals(authorName)){
                System.out.println(books[i]);
            }
        }
    }

    public void printBooksByGenre(String genre){
        for(int i = 0; i < size; i++){
            if(genre.equals(books[i].getGenre())){
                System.out.println(books[i]);
            }
        }
    }

    public void printBooksByPriceRange(double priceMin, double priceMax){
        for(int i = 0; i < size; i++){
            if(books[i].getPrice() >= priceMin && books[i].getPrice() <= priceMax){
                System.out.println(books[i]);
            }
        }
    }

    public void printBooks(){
        for(int i = 0; i < size; i++){
            System.out.println(books[i].toString());
        }
        System.out.println();
    }




}