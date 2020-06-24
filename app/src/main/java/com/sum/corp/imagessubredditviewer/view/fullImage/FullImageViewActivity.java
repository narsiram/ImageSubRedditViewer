package com.sum.corp.imagessubredditviewer.view.fullImage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.widget.Toast;

import com.example.imageloader.ImageLoader;
import com.sum.corp.imagessubredditviewer.R;
import com.sum.corp.imagessubredditviewer.databinding.ActivityFullImageViewBinding;

public class FullImageViewActivity extends AppCompatActivity {


    ActivityFullImageViewBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_full_image_view);

        ImageLoader.get(this)
                .loadUrl(getIntent().getStringExtra("image"))
                .target(binding.image)
                .addListener(new ImageLoader.ImageListener() {
                    @Override
                    public void result(boolean result, final String message) {
                        Toast.makeText(FullImageViewActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                }).execute();
    }

}