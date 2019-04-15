package com.example.alberto.prueba_m2o2.activities.mainActivity.detailActivity;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alberto.prueba_m2o2.R;
import com.example.alberto.prueba_m2o2.activities.mainActivity.MainActivity;
import com.example.alberto.prueba_m2o2.model.Receipe;
import com.squareup.picasso.Picasso;

public class DetailActivity extends MainActivity {

    Receipe mReceipe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        mReceipe = (Receipe) getIntent().getSerializableExtra("receipe");

        Toolbar toolbar = (Toolbar)findViewById(R.id.collapsing_toolbar);

        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar_layout);
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(R.color.colorTransparent));

        ImageView imageViewCT = (ImageView)findViewById(R.id.collapsing_toolbar_image);
        if(mReceipe.getThumbnail() != null && !mReceipe.getThumbnail().isEmpty())
            Picasso.get().load(mReceipe.getThumbnail()).into(imageViewCT);

        TextView titleTV = findViewById(R.id.titleTV);
        if(mReceipe.getTitle() != null && !mReceipe.getTitle().isEmpty())
            titleTV.setText(mReceipe.getTitle());

        TextView ingredientsTV = findViewById(R.id.ingredientsTV);
        if(mReceipe.getIngredients() != null && !mReceipe.getIngredients().isEmpty())
            ingredientsTV.setText(mReceipe.getIngredients());

        TextView webReferenceTV = findViewById(R.id.webReferenceTV);
        if(mReceipe.getHref() != null && !mReceipe.getHref().isEmpty())
            webReferenceTV.setText(mReceipe.getHref());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id  = item.getItemId();

        if (id == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
