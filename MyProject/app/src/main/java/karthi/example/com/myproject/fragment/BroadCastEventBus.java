package karthi.example.com.myproject.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import karthi.example.com.myproject.R;
import karthi.example.com.myproject.adapter.ArrayAdapterClass;

/**
 * Created by AshokKumar on 30/10/2017.
 */

public class BroadCastEventBus extends Fragment {

    private static final String TAG = "BroadCastEventBus";
    private ListView listView;
    private ArrayAdapterClass arrayAdapter;
    private ArrayList<String> strings = new ArrayList<>();

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
//        LocalBroadcastManager.getInstance(getContext()).registerReceiver(broadcastReceiver, new IntentFilter("Test"));
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
//        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(broadcastReceiver);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent1(MessageEvent1 event) {
        Toast.makeText(getContext(), event.message, Toast.LENGTH_SHORT).show();
        Log.e(TAG, "fffffffffffffffffffffff: " + event.message );
        strings.add(event.message);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(container.getContext())
                .inflate(R.layout.listvewlayout, container, false);

        listView = (ListView) view.findViewById(R.id.list);
        arrayAdapter = new ArrayAdapterClass(getContext(),strings);
        listView.setAdapter(arrayAdapter);
        return view;
    }
}
