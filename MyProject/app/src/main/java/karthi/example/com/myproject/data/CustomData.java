package karthi.example.com.myproject.data;

import java.util.ArrayList;

/**
 * Created by AshokKumar on 19/10/2017.
 */

public class CustomData {

    private String id,title,image;
    private double rating;
    private int year;
    private ArrayList<String> genre;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public ArrayList<String> getGenre() {
        return genre;
    }

    public void setGenre(ArrayList<String> genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "CustomData{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", rating=" + rating +
                ", year=" + year +
                ", genre=" + genre +
                '}';
    }
}
