package com.sum.corp.imagessubredditviewer.view.list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.sum.corp.imagessubredditviewer.R;
import com.sum.corp.imagessubredditviewer.data.model.Children;
import com.sum.corp.imagessubredditviewer.databinding.ActivityImageListBinding;
import com.sum.corp.imagessubredditviewer.view.fullImage.FullImageViewActivity;

import java.util.List;

public class ImageListActivity extends AppCompatActivity implements ImageListAdapter.OnItemClick, ImageListContract.View {


    private static final String TAG = ImageListActivity.class.getSimpleName();
    ActivityImageListBinding binding;
    ImageListAdapter adapter;
    ImageListPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_image_list);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//  set status text dark
        getWindow().setStatusBarColor(ContextCompat.getColor(this, android.R.color.white));// set status background white

        init();
    }

    private void init() {
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        binding.recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new ImageListAdapter(this, this);

        binding.recyclerView.setAdapter(adapter);

        presenter = new ImageListPresenter(this, this);

        if (isPermissionGranted())
            presenter.getPostData();
    }

    @Override
    public void onItemClick(int pos, String url) {
        startActivity(new Intent(this, FullImageViewActivity.class).putExtra("image", url));
    }

    @Override
    public void setImages(List<Children> imageList) {
        if (imageList != null) {
            adapter.setImagesList(imageList);
        }
    }


    public boolean isPermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            presenter.getPostData();
        } else {
            isPermissionGranted();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDispose();
    }
}