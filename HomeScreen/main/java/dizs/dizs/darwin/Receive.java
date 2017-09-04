package dizs.dizs.darwin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class Receive extends AppCompatActivity {
ImageButton copy;
    Button buttonExit;
    EditText ed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive);
        buttonExit = (Button) findViewById(R.id.menu);
        copy = (ImageButton) findViewById(R.id.copy);
        ed = (EditText) findViewById(R.id.addresssqr);
        buttonExit.setOnClickListener(new View.OnClickListener() {
                                          public void onClick(View v) {

                                              //Exit app
                                              Intent i = new Intent(Receive.this,MainActivity.class);
                                              startActivity(i);
                                              finish();

                                          }

                                      }
        );

        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text=ed.getText().toString();
                Log.d("Copy","");
            }
        });
    }
}
