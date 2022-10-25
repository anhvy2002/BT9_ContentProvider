package com.test.content_provider;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class DanhBa extends AppCompatActivity {

    private static final int REQUEST_CODE_ASK_PERMISSIONS = 1001;

    ListView lvDanhBa;
    ArrayList<Contact> dsDanhBa;
    ArrayAdapter<Contact> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_ba);

        AddControl();
        showContact();
    }

    private void showContact() {
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Cursor cursor = getContentResolver().query(uri,null, null, null, null);
        dsDanhBa.clear();
        while (cursor.moveToNext()){
            String tenCotName = ContactsContract.Contacts.DISPLAY_NAME;
            String tenCotPhone = ContactsContract.CommonDataKinds.Phone.NUMBER;
            int viTriCotName = cursor.getColumnIndex(tenCotName);
            int viTriCotPhone = cursor.getColumnIndex(tenCotPhone);
            String name = cursor.getString(viTriCotName);
            String phone = cursor.getString(viTriCotPhone);
            Contact contact = new Contact(name, phone);
            dsDanhBa.add(contact);
            adapter.notifyDataSetChanged();
        }
    }

    private void AddControl() {
        lvDanhBa = findViewById(R.id.lvDanhBa);
        dsDanhBa = new ArrayList<>();
        adapter = new ArrayAdapter<>(
                DanhBa.this, android.R.layout.simple_list_item_1, dsDanhBa
        );
        lvDanhBa.setAdapter(adapter);
    }
}