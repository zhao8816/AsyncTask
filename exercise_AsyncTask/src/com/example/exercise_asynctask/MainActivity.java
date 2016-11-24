package com.example.exercise_asynctask;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

public class MainActivity extends Activity {

	private ImageView image;
	private String url = "http://a4.att.hudong.com/32/73/01300000013093121480731593176_950.gif";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//连接到控件
		image = (ImageView) findViewById(R.id.image);
		//实例化自定义的AsyncTask
		MyAsyncTask myAsyncTask = new MyAsyncTask();
		//调用url
		myAsyncTask.execute(url);
	}
	
	//自定义AsyncTask
	class MyAsyncTask extends AsyncTask<String, Void, Bitmap>{

		/*
		 * 参数解释：参数一 -- 要传入的参数类型；
		 * 		      参数二 -- 显示进度，一般用void，不显示进度；
		 * 		      参数三 -- 请求网络返回的结果。
		 */
		// 后台方法， --子线程
		@Override
		protected Bitmap doInBackground(String... params) {
			// TODO Auto-generated method stub
			Bitmap bitmap=null;
			try {
				//请求网络数据
				URL url2 = new URL(params[0]);
				HttpURLConnection conn = (HttpURLConnection) url2.openConnection();
				conn.setConnectTimeout(3000);
				conn.setReadTimeout(3000);
				if (conn.getResponseCode()==200) {
					//获取流
					InputStream inputStream = conn.getInputStream();
					//将流转化为bitmap
					bitmap=BitmapFactory.decodeStream(inputStream);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return bitmap;
		}
		
		// 前台方法，--主线程
		@Override
		protected void onPostExecute(Bitmap result) {
			image.setImageBitmap(result);
		}
	}
}
