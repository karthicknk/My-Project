package karthi.example.com.myproject.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import karthi.example.com.myproject.R;
import karthi.example.com.myproject.adapter.CustomAdapter;
import karthi.example.com.myproject.data.CustomData;
import karthi.example.com.myproject.helper.DataBaseCustomHelper;
import karthi.example.com.myproject.interfaces.Communication;
import karthi.example.com.myproject.volley.NetworkController;

/**
 * Created by AshokKumar on 19/10/2017.
 */

public class CustomRecyclerView extends Fragment implements Communication{

    private static final String TAG = "CustomRecyclerView";
    private RecyclerView recyclerView;
    private CustomAdapter customAdapter;
    private List<CustomData> list = new ArrayList<>();
    private String url = "https://api.androidhive.info/json/movies.json";
    private NetworkController networkController;
    private DataBaseCustomHelper dataBaseCustomHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(container.getContext())
                .inflate(R.layout.customlayout, container, false);

        //Title in toolbar
        getActivity().setTitle("Custom RecyclerView");

        dataBaseCustomHelper = new DataBaseCustomHelper(getContext());
        customAdapter = new CustomAdapter(getContext(),list);
        networkController = NetworkController.getInstance(getContext());
        networkController.JsonFunction(url,this);

        recyclerView = (RecyclerView) view.findViewById(R.id.customrecycle);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(lm);
        recyclerView.setAdapter(customAdapter);

        return view;
    }

    @Override
    public void Success(JSONArray array) {

        //Delete AllValues in Sqlight Database
        dataBaseCustomHelper.DeleteCustom();

        for (int i = 0; i < array.length(); i++) {
            try {
                JSONObject jsonObject = array.getJSONObject(i);

                CustomData customData = new CustomData();
                customData.setImage(jsonObject.getString("image"));
                customData.setTitle(jsonObject.getString("title"));
                customData.setRating(jsonObject.getDouble("rating"));
                customData.setYear(jsonObject.getInt("releaseYear"));

                ArrayList<String> strings = new ArrayList<>();
                JSONArray jsonArray = jsonObject.getJSONArray("genre");
                for (int j = 0; j < jsonArray.length(); j++) {
                    String value = (String) jsonArray.get(j);
                    strings.add(value);
                }
                customData.setGenre(strings);

                //Insert in Sqlight Database
                dataBaseCustomHelper.InsertCustom(customData);

                //GetAll Values from Sqlight Database
                List<CustomData> data = dataBaseCustomHelper.GelAllValues();
                CustomData datas = data.get(i);

                list.add(datas);

            } catch (JSONException e) {
                e.printStackTrace();
            }finally {
                customAdapter.notifyDataSetChanged();
            }
        }

    }

    @Override
    public void Error(String error) {

    }
}
