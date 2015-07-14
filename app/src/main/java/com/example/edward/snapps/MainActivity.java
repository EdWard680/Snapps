package com.example.edward.snapps;

import java.io.File;

import android.app.Activity;
import android. content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.*;
import android.widget.ImageView;
import android.widget.Toast;
import android.util.Log;
import android.provider.MediaStore;


public class MainActivity extends Activity {
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private static File imageFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

        return super.onOptionsItemSelected(item);
    }

    public void openCamera(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        imageFile = new File(Environment.getExternalStorageDirectory(), "SnappsCamera_" + System.currentTimeMillis());

        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imageFile));
        startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Log.i("Photo", "Saved in: " + imageFile);
                Intent intent = new Intent (this, SnappsResults.class);
                this.startActivity(intent);
                this.finish();
            } else if (resultCode == RESULT_CANCELED) {
                Log.i("Photo", "User cancelled the image capture");
                return;
            } else {
                Toast.makeText(this, "Cannot capture image", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void deleteImage(View view) {
        imageFile.delete();
        Log.i("Photo", "DELETE_DELETE_DELETE");
    }

    public static File getImageFile () {
        return imageFile;
    }

}