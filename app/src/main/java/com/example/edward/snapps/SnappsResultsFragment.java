package com.example.edward.snapps;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

/**
 * A placeholder fragment containing a simple view.
 */
public class SnappsResultsFragment extends Fragment {
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private static ImageView iv;

    public SnappsResultsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.wtf("OnCreateView", "View Created");
        View rootView =  inflater.inflate(R.layout.fragment_snapps_results, container, false);
        iv = (ImageView) rootView.findViewById(R.id.cameraResult);
        Bitmap picture = BitmapFactory.decodeFile(MainActivity.getImageFile().toString());
        iv.setImageBitmap(picture);
        return rootView;
    }

}
