package com.xuanvu.simplecontact;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ContactAdapter.OnItemClickListener {

    public static final int REQUEST_CODE = 1;
    public static final int RESULT_CODE_ADD = 1;
    public static int RESULT_CODE_UPDATE = 1;

    List<Contact> ListContact;
    ContactAdapter contactAdapter;
    RecyclerView mRecyclerView;
    MyDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        mRecyclerView = findViewById( R.id.mRecyclerView );

        Toolbar toolbar = findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );

        ListContact = new ArrayList<Contact>();
        db = new MyDatabase( this );
        ListContact = db.getAllContacts();

        initContact();


        FloatingActionButton fab = findViewById( R.id.fab );
        fab.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( MainActivity.this, AddContact.class );
                startActivityForResult( intent, REQUEST_CODE );

            }

        } );
    }

    private void initContact() {
        contactAdapter = new ContactAdapter( this, R.layout.item_contact, ListContact, this );
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager( this );
        mRecyclerView.setLayoutManager( layoutManager );
        mRecyclerView.setAdapter( contactAdapter );
        contactAdapter.notifyDataSetChanged();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult( requestCode, resultCode, data );
        if (requestCode == RESULT_CODE_ADD) {
            if (data != null) {
                ListContact.clear();
                ListContact = db.getAllContacts();
                initContact();
                Toast.makeText( this, "", Toast.LENGTH_SHORT ).show();
            }

            if (requestCode == RESULT_CODE_UPDATE) {
                if (data != null) {
                    ListContact.clear();
                    ListContact = db.getAllContacts();
                    initContact();
                    Toast.makeText( this, "", Toast.LENGTH_SHORT ).show();
                }
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate( R.menu.menu_main, menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected( item );
    }

    @Override
    public void onItemClick(Contact item) {
        Intent intent = new Intent( MainActivity.this, EditContact.class );
        intent.putExtra( "name", item.getmFullname() );
        intent.putExtra( "phone", item.getmMobile() );
        intent.putExtra( "email", item.getmEmail() );
        startActivityForResult( intent, REQUEST_CODE );
    }
}
