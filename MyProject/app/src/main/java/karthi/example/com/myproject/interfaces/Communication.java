package karthi.example.com.myproject.interfaces;

import org.json.JSONArray;

/**
 * Created by AshokKumar on 19/10/2017.
 */

public interface Communication {

    void Success(JSONArray array);

    void Error(String error);
}
