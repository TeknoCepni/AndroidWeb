package hsyn.caliskan.androidweb;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.Queue;

//Jsoup kütüphanesi bu siteden incelenebilir.
//http://jsoup.org/cookbook/extracting-data/selector-syntax

public class JsoupActivity extends AppCompatActivity {
    String URL = "http://drbinnazridvanegeal.meb.k12.tr/meb_iys_dosyalar/06/06/323469/icerikler/bilgi-ve-iletisim-teknolojileri_1988130.html?CHK=346ea1561d4333a8124437f515f2b297";
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jsoup);

        new Fetch().execute();

    }

    private class Fetch extends AsyncTask<Void, Void, Void> {

        Bitmap bitmap;
        String isim = "";
        String gorev = "";
        Queue<String> textQueue =new LinkedList<String>();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(JsoupActivity.this);
            progressDialog.setTitle("LOGO");
            progressDialog.setMessage("Logo Çekiliyor...");
            progressDialog.setIndeterminate(false);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {

            try{

                Document doc  = Jsoup.connect(URL).get();
                Elements elements = doc.select("div#araSayfaNrmlicerik").select("img[src]");
                Elements elementText = doc.select("div#araSayfaNrmlicerik").select("p[style=text-align: center;]");
                String imgSrc = elements.attr("src");


                InputStream input = new java.net.URL(imgSrc).openStream();
                bitmap = BitmapFactory.decodeStream(input);


                for (Element txt : elementText)
                {
                    textQueue.add(txt.text());
                }
            }catch (Exception e){

                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            ImageView image = (ImageView)findViewById(R.id.imageView_jsoup);
            TextView textView_isim = (TextView) findViewById(R.id.textView_jsoup_isim);
            TextView textView_gorev = (TextView) findViewById(R.id.textView_jsoup_gorev);

            image.setImageBitmap(bitmap);
            image.setScaleType(ImageView.ScaleType.FIT_CENTER);
            isim = textQueue.poll();
            gorev = textQueue.poll();

            textView_isim.setText(isim);
            textView_gorev.setText(gorev);

            progressDialog.dismiss();
        }
    }
}
