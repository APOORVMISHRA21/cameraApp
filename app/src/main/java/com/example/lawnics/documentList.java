package com.example.lawnics;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.lawnics.databinding.ActivityDocumentListBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import Adapters.DocAdapter;
import Models.Documents;

public class documentList extends AppCompatActivity {

    ActivityDocumentListBinding binding;

    ArrayList<Documents> docList = new ArrayList<>();
    private DocAdapter adapter;

    FirebaseDatabase database;
    private DatabaseReference reference;


    private ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {

            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                String docName = dataSnapshot.child("docName").getValue().toString();
                String docImage = dataSnapshot.child("docImage").getValue().toString();

                Documents doc = new Documents(docName, docImage);

                Log.i("^^^^______________^^^^", "onDataChange: " + dataSnapshot.getKey());
                doc.setDocId(dataSnapshot.getKey());
                docList.add(doc);
            }

            if(adapter!=null){
                adapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDocumentListBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        androidx.appcompat.widget.Toolbar toolbar = (androidx.appcompat.widget.Toolbar) findViewById(R.id.doclistToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Lawnics");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        database = FirebaseDatabase.getInstance();

        reference = database.getReference().child("Images");

        reference.addValueEventListener(valueEventListener);

        adapter = new DocAdapter(docList, documentList.this);
        binding.taskRecyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(documentList.this);
        binding.taskRecyclerView.setLayoutManager(layoutManager);

        adapter.notifyDataSetChanged();

        binding.fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(documentList.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tool_menu_doclist, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if(id == R.id.home) {
            documentList.this.finish();
        }

        if (id == R.id.search) {
            Toast.makeText(documentList.this, "Search clicked", Toast.LENGTH_LONG).show();
            return true;
        }

        if (id == R.id.filter) {
            Toast.makeText(documentList.this, "Filter clicked", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}