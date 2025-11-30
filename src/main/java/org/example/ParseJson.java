package org.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.util.List;

public class ParseJson {


    public List<Book> parse(){
        Gson gson = new Gson();

        try(FileReader reader   = new FileReader("task1_d.json")){

            List<Book> books = gson.fromJson(reader,  new TypeToken<List<Book>>(){}.getType());
            return books;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
