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
		//���ӵ��ؼ�
		image = (ImageView) findViewById(R.id.image);
		//ʵ�����Զ����AsyncTask
		MyAsyncTask myAsyncTask = new MyAsyncTask();
		//����url
		myAsyncTask.execute(url);
	}
	
	//�Զ���AsyncTask
	class MyAsyncTask extends AsyncTask<String, Void, Bitmap>{

		/*
		 * �������ͣ�����һ -- Ҫ����Ĳ������ͣ�
		 * 		      ������ -- ��ʾ���ȣ�һ����void������ʾ���ȣ�
		 * 		      ������ -- �������緵�صĽ����
		 */
		// ��̨������ --���߳�
		@Override
		protected Bitmap doInBackground(String... params) {
			// TODO Auto-generated method stub
			Bitmap bitmap=null;
			try {
				//������������
				URL url2 = new URL(params[0]);
				HttpURLConnection conn = (HttpURLConnection) url2.openConnection();
				conn.setConnectTimeout(3000);
				conn.setReadTimeout(3000);
				if (conn.getResponseCode()==200) {
					//��ȡ��
					InputStream inputStream = conn.getInputStream();
					//����ת��Ϊbitmap
					bitmap=BitmapFactory.decodeStream(inputStream);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return bitmap;
		}
		
		// ǰ̨������--���߳�
		@Override
		protected void onPostExecute(Bitmap result) {
			image.setImageBitmap(result);
		}
	}
}
