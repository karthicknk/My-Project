package karthi.example.com.myproject.data;

/**
 * Created by AshokKumar on 21/10/2017.
 */

public class ListSqlight {

    private String Id,Title;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    @Override
    public String toString() {
        return "ListSqlight{" +
                "Id='" + Id + '\'' +
                ", Title='" + Title + '\'' +
                '}';
    }
}
