package dizs.dizs.darwin;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
   ImageButton send,recevie,sell;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        send=(ImageButton)findViewById(R.id.imageView1);
        recevie=(ImageButton)findViewById(R.id.imageView3);
        sell=(ImageButton)findViewById(R.id.imageView5);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,EnterPin.class);
                startActivity(intent);
            }
        });
        recevie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Receive.class);
                startActivity(intent);
            }
        });
    }
}
