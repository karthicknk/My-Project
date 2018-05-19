package karthi.example.com.myproject.volley;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

import karthi.example.com.myproject.interfaces.Communication;

/**
 * Created by AshokKumar on 19/10/2017.
 */

public class NetworkController {

    private Context context;
    private RequestQueue requestQueue;
    private static NetworkController networkController;

    public NetworkController(Context context) {
        this.context = context;
        this.requestQueue = getRequestQueue();
    }

    private RequestQueue getRequestQueue() {
        if (requestQueue == null){
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }

    public static NetworkController getInstance(Context context) {
        if (networkController == null){
            networkController = new NetworkController(context);
        }
        return networkController;
    }

    public void JsonFunction(String url, final Communication communication){

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                communication.Success(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                communication.Error(error.getMessage());
            }
        });
        requestQueue.add(jsonArrayRequest);
    }
}
