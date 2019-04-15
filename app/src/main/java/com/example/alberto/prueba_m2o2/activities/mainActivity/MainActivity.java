package com.example.alberto.prueba_m2o2.activities.mainActivity;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alberto.prueba_m2o2.R;
import com.example.alberto.prueba_m2o2.adapter.ReceipeAdapter;
import com.example.alberto.prueba_m2o2.activities.mainActivity.detailActivity.DetailActivity;
import com.example.alberto.prueba_m2o2.model.Receipe;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainContract.MainView {

    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private TextView noResults;

    private MainContract.presenter presenter;
    private String textToSearch;

    private Fragment mainFragment, detailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeToolbarAndRecyclerView();
        initializeFragments();
        initProgressBar();
        textToSearch ="";
        presenter = new MainPresenterImpl(this, new GetReceipeIntractorImpl(),textToSearch);
    }



    private void initializeToolbarAndRecyclerView() {

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final EditText inputText = toolbar.findViewById(R.id.inputTextET);

        inputText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                textToSearch = inputText.getText().toString();
                presenter.showLoading();
                presenter.requestDataFromServer(textToSearch);
            }
        });

        recyclerView = findViewById(R.id.recycler_view_receipes_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);

        noResults = findViewById(R.id.no_receipesTV);


    }

    private void initializeFragments() {

    }


    private void initProgressBar() {
        progressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleLarge);
        progressBar.setIndeterminate(true);

        RelativeLayout relativeLayout = new RelativeLayout(this);
        relativeLayout.setGravity(Gravity.CENTER);
        relativeLayout.addView(progressBar);

        RelativeLayout.LayoutParams params = new
                RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        progressBar.setVisibility(View.INVISIBLE);

        this.addContentView(relativeLayout, params);
    }


    private RecyclerItemClickListener recyclerItemClickListener = new RecyclerItemClickListener() {
        @Override
        public void onItemClick(Receipe receipe) {
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra("receipe", receipe);
            startActivity(intent);
        }

        @Override
        public void onLinkClick(String url) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        }
    };


    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }


    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }


    @Override
    public void setDataToRecyclerView(ArrayList<Receipe> receipeArrayList) {

        ReceipeAdapter adapter = new ReceipeAdapter(receipeArrayList, recyclerItemClickListener, getApplicationContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setVisibility(View.VISIBLE);
        noResults.setVisibility(View.GONE);

    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Toast.makeText(MainActivity.this,
                "Something went wrong...Error message: " + throwable.getMessage(),
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void noResults(){
        recyclerView.setVisibility(View.GONE);
        noResults.setVisibility(View.VISIBLE);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_search) {
            presenter.showLoading();
            presenter.requestDataFromServer(textToSearch);
        }

        return super.onOptionsItemSelected(item);
    }
}

