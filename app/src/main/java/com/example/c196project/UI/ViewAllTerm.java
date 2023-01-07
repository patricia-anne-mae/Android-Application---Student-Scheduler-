package com.example.c196project.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.c196project.Database.Repository;
import com.example.c196project.Entity.Terms;
import com.example.c196project.R;

import java.util.List;

public class ViewAllTerm extends AppCompatActivity {

    Repository repository;
    List<Terms> allTerms;
    private RecyclerView recyclerView;
    private TermsAdapter termsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_term);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        repository = new Repository(getApplication());
        allTerms = repository.getAllTerms();

        recyclerView = findViewById(R.id.TermsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        termsAdapter = new TermsAdapter(this);
        recyclerView.setAdapter(termsAdapter);
        termsAdapter.setTerms(allTerms);

    }

    public void OnClickAddTerm(View view) {
        Intent intent = new Intent(ViewAllTerm.this, AddTerms.class);
        startActivity(intent);
    }
}






