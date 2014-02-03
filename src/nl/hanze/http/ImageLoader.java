package nl.hanze.http;

import java.io.InputStream;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;

public class ImageLoader {
	public static Bitmap load(String url) {
		try {
	        URL source = new URL(url);
	        HttpGet httpRequest = null;

	        httpRequest = new HttpGet(source.toURI());

	        HttpClient httpclient = new DefaultHttpClient();
	        HttpResponse response = (HttpResponse) httpclient
	                .execute(httpRequest);

	        HttpEntity entity = response.getEntity();
	        BufferedHttpEntity b_entity = new BufferedHttpEntity(entity);
	        InputStream input = b_entity.getContent();

	        return BitmapFactory.decodeStream(input);       
	    } catch (Exception ex) {
	    	Log.e("ImageLoader", ex.getMessage());
	    } finally {
	    	return null;
	    }
	}
	
}
