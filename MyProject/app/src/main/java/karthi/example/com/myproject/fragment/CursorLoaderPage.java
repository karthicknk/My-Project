package karthi.example.com.myproject.fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import karthi.example.com.myproject.R;
import karthi.example.com.myproject.adapter.CursorLoaderAdapter;

/**
 * Created by AshokKumar on 21/10/2017.
 */

public class CursorLoaderPage extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{

    private static final int CONTACT_LOADER = 1;
    private ListView listView;
    private CursorLoaderAdapter cursorLoaderAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = LayoutInflater.from(container.getContext())
                .inflate(R.layout.cursorlist, container, false);

        getActivity().setTitle("CursorLoader");// toolbar title
        getLoaderManager().initLoader(CONTACT_LOADER,null,this);
        listView = (ListView) view.findViewById(R.id.cursorlist);

        return view;
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        CursorLoader cursorLoader = new CursorLoader(getContext(),
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null,
                null,
                null,
                null);

        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        cursorLoaderAdapter = new CursorLoaderAdapter(getContext(),data);
        listView.setAdapter(cursorLoaderAdapter);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
