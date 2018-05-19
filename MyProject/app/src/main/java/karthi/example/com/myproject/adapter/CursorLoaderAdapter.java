package karthi.example.com.myproject.adapter;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import karthi.example.com.myproject.R;
import karthi.example.com.myproject.fragment.CursorLoaderPage;

/**
 * Created by AshokKumar on 21/10/2017.
 */

public class CursorLoaderAdapter extends CursorAdapter{

    private LayoutInflater inflater;

    public CursorLoaderAdapter(Context context, Cursor c) {
        super(context, c);
       inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
       View view = inflater.inflate(R.layout.cursorloaderlayout,viewGroup,false);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView Name = (TextView) view.findViewById(R.id.name);
        TextView Number = (TextView) view.findViewById(R.id.number);

        String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
        String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

        Name.setText(name);
        Number.setText(number);
    }
}
