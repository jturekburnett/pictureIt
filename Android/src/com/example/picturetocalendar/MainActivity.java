package com.example.picturetocalendar;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {
	public static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	public static final int MEDIA_TYPE_IMAGE = 1;
	private Uri fileUri;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		run();
	}
	
	public void startCamera(View view){
		run();
	}
	
	public void run(){
		Intent cam = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
		Log.d("Activity Result",fileUri.toString());
		cam.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
		startActivityForResult(cam, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data){
		if(requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE){
			if(resultCode == RESULT_OK){
				data.getExtras();
				Log.d("Activity Result","Succeeded");
			} 
			else if (resultCode == RESULT_CANCELED){
				Toast.makeText(this, "Cancelled by User", Toast.LENGTH_LONG).show();
				Log.d("Activity Result","Cancelled");
			}
			else{
				Toast.makeText(this, "Application Failed", Toast.LENGTH_LONG).show();
				Log.d("Activity Result","Failed");
			}
		}

	}

	private static Uri getOutputMediaFileUri(int type){
		return Uri.fromFile(getOutputMediaFile(type));
	}
	
	private static File getOutputMediaFile(int type){
		// To be safe, you should check that the SDCard is mounted
		// using Environment.getExternalStorageState() before doing this.
		File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "PictureToCalendar");
		if (! mediaStorageDir.exists()){
			if (! mediaStorageDir.mkdirs()){
				Log.d("PictureToCalendar", "failed to create directory");	
				return null;
			}
		}

		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		File mediaFile = new File(mediaStorageDir.getPath() + File.separator +
				"IMG_" + timeStamp + ".jpg");
		return mediaFile;
	}

}
