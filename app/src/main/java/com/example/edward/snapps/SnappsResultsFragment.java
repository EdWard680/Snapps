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
import android.graphics.Matrix;
import android.graphics.PointF;
import android.media.FaceDetector;
import android.widget.Toast;

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
        Log.i("OnCreateView", "View Created");
        View rootView =  inflater.inflate(R.layout.fragment_snapps_results, container, false);
        iv = (ImageView) rootView.findViewById(R.id.cameraResult);
        Bitmap picture = BitmapFactory.decodeFile(MainActivity.getImageFile().toString());
        if(picture == null) {
            Log.wtf("Camera View", "lol no");
            return rootView;
        }
        Matrix mat = new Matrix();
        mat.postRotate(-90);
        Bitmap rotPic = Bitmap.createBitmap(picture, 0, 0, picture.getWidth(), picture.getHeight(), mat, true);
        iv.setImageBitmap(rotPic);

        FaceDetector detector = new FaceDetector(rotPic.getWidth(), rotPic.getHeight(), 5);
        FaceDetector.Face[]  faces = new FaceDetector.Face[5];

        try {
            detector.findFaces(rotPic, faces);
        }
        catch(IllegalArgumentException e) {
            Log.wtf("Snapps.onCreateView", "Illegal argument exception: " + e.toString());
        }

        for(int i = 0; i < 5; i++)
        {
            if(faces[i] != null)
            {
                Log.i("Face Detection", "Found a face");
            }
        }

        return rootView;
    }

}
