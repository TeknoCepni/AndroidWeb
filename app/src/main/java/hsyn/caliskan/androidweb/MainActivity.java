package hsyn.caliskan.androidweb;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Activity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activity = this;
        Button button_image = (Button) findViewById(R.id.button_image);
        Button button_toast = (Button) findViewById(R.id.button_toast);
        Button button_costum_toast = (Button) findViewById(R.id.button_ctoast);
        Button button_web = (Button) findViewById(R.id.button_web);
        Button button_jsoup = (Button) findViewById(R.id.button_jsoup);


        //Resim Gösterimi
        button_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity,ImageActivity.class);
                startActivity(intent);
            }
        });

        //Toast Gösterimi
        button_toast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(activity,"Bu bir uyarı mesajıdır!",Toast.LENGTH_SHORT).show();

            }
        });



        //Costom Toast Kullanımı
        button_costum_toast.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                LayoutInflater inflater = getLayoutInflater();
                View layoutView = inflater.inflate(R.layout.toast, (ViewGroup) findViewById(R.id.toastrelative));

                TextView textViewToast = (TextView)layoutView.findViewById(R.id.textView_toast);
                ImageView imageViewCustomToast = (ImageView)layoutView.findViewById(R.id.imageViewCustomToast);

                textViewToast.setText("Bu bir custom Toast'dır.");
                textViewToast.setTextColor(Color.WHITE);
                textViewToast.setTextSize(20);

                imageViewCustomToast.setImageResource(R.drawable.android);

                Toast toast = new Toast(activity);
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(layoutView);
                toast.show();

                return false;
            }
        });

        //Web kullanımı
        button_web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity,WebActivity.class);
                startActivity(intent);
            }
        });


        //Jsoup ile web parsing
        button_jsoup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity,JsoupActivity.class);
                startActivity(intent);
            }
        });


    }
}
