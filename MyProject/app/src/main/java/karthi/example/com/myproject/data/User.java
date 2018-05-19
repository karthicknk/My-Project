package karthi.example.com.myproject.data;

import java.util.Arrays;

/**
 * Created by AshokKumar on 19/10/2017.
 */

public class User {

    private String Name,Email,Password;
    private byte[] image;

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "Name='" + Name + '\'' +
                ", Email='" + Email + '\'' +
                ", Password='" + Password + '\'' +
                ", image=" + Arrays.toString(image) +
                '}';
    }
}
