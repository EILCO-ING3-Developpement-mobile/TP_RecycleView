
package com.example.android.recyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements GreenAdapter.ListItemClickListener{


    private static final int Nom_LIST_ITEMS = 14;

    /*
     * References to RecyclerView and Adapter to reset the list to its
     * "pretty" state when the reset menu item is clicked.
     */
    private GreenAdapter mAdapter;
    private RecyclerView mNomsList;
    private Toast toast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNomsList = (RecyclerView) findViewById(R.id.rv_noms);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mNomsList.setLayoutManager(layoutManager);

        mNomsList.setHasFixedSize(true);

        mAdapter = new GreenAdapter(Nom_LIST_ITEMS,this);

        mNomsList.setAdapter(mAdapter);
    }

    @Override
    public void onListItemClick(String name) {
        String message = "Tu as cliqué sur : " + name;
        if (toast != null)
            toast.cancel();
        toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        toast.show();
    }
}
