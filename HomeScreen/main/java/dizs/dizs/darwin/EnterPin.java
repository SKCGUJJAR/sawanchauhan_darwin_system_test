package dizs.dizs.darwin;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import static java.lang.Thread.sleep;

public class EnterPin extends AppCompatActivity {
    EditText enter_mpin;
    ImageView i1, i2, i3, i4;
    String userEntered;
    String userPin = "1234";
    final int PIN_LENGTH = 4;
    boolean keyPadLockedFlag = false;
    Context appContext;
    TextView titleView;
    TextView pinBox0;
    TextView pinBox1;
    TextView pinBox2;
    TextView pinBox3;
    TextView statusView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    Button button7;
    Button button8;
    Button button9;
    Button button10;
    Button buttonExit;
    Button backSpace,buttonDelete;
    int buttonno=0;
    EditText passwordInput,passwordInput2,passwordInput3,passwordInput4;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_enter_pin);
        appContext = this;
        userEntered = "";

//        Typeface xpressive=Typeface.createFromAsset(getAssets(), "fonts/XpressiveBold.ttf");

       statusView = (TextView) findViewById(R.id.statusview);
       passwordInput = (EditText) findViewById(R.id.editText1);
       passwordInput2 = (EditText) findViewById(R.id.editText2);
       passwordInput3 = (EditText) findViewById(R.id.editText3);
       passwordInput4 = (EditText) findViewById(R.id.editText4);
     //   buttonDelete = (Button) findViewById(R.id.buttonDeleteBack);
//        i1 = (ImageView) findViewById(R.id.t1);
//        i2 = (ImageView) findViewById(R.id.t2);
//        i3 = (ImageView) findViewById(R.id.t3);
//        i4 = (ImageView) findViewById(R.id.t4);
       // enter_mpin = (EditText) findViewById(R.id.editText_enter_mpin);
     //   enter_mpin.requestFocus();
     //   enter_mpin.setInputType(InputType.TYPE_CLASS_NUMBER);
     //   enter_mpin.setFocusableInTouchMode(true);
        buttonExit = (Button) findViewById(R.id.menu);

        buttonExit.setOnClickListener(new View.OnClickListener() {
                                          public void onClick(View v) {

                                              //Exit app
                                              Intent i = new Intent(EnterPin.this,MainActivity.class);
                                              startActivity(i);
                                              finish();

                                          }

                                      }
        );
  //      buttonExit.setTypeface(xpressive);


        buttonDelete = (Button) findViewById(R.id.buttondelete);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
                                            public void onClick(View v) {

                                                if (keyPadLockedFlag == true) {
                                                    return;
                                                }

                                                if (userEntered.length() > 0) {

                                                    System.out.println("Value is"+buttonno);
                                                    if(buttonno==1) {
                                                        passwordInput.setBackgroundResource(R.drawable.circle);
                                                        buttonno--;
                                                    }
                                                    else if(buttonno==2)
                                                    {
                                                        passwordInput2.setBackgroundResource(R.drawable.circle);
                                                        buttonno--;
                                                    }
                                                    else if(buttonno==3)
                                                    {
                                                        passwordInput3.setBackgroundResource(R.drawable.circle);
                                                        buttonno--;
                                                    }
                                                    else if(buttonno==4)
                                                    {
                                                        passwordInput4.setBackgroundResource(R.drawable.circle);
                                                        buttonno--;
                                                    }
                                                }


                                            }

                                        }
        );

     //   titleView = (TextView) findViewById(R.id.time);
        //titleView.setTypeface(xpressive);


        View.OnClickListener pinButtonHandler = new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            public void onClick(View v) {

                if (keyPadLockedFlag == true) {
                    return;
                }
                Button pressedButton = (Button) v;

                System.out.println(" button no  "+buttonno);
                if (buttonno < PIN_LENGTH) {
                    userEntered = userEntered + pressedButton.getText();
                    Log.v("PinView", "User entered=" + userEntered);

                   buttonno++;
                   if (buttonno == 1) {

                       passwordInput.setBackgroundResource(R.drawable.circle2);
                   } else if (buttonno == 2) {
                       passwordInput2.setBackgroundResource(R.drawable.circle2);
                   } else if (buttonno == 3) {
                       passwordInput3.setBackgroundResource(R.drawable.circle2);
                   } else if (buttonno == 4) {
                       passwordInput4.setBackgroundResource(R.drawable.circle2);
                   }


                    if (buttonno == PIN_LENGTH) {
                        //Check if entered PIN is correct
                        if (userEntered.equals(userPin)) {
                            statusView.setTextColor(Color.GREEN);
                            statusView.setText("Correct");
                            Intent intent=new Intent(EnterPin.this,Send.class);
                            startActivity(intent);
                            Log.v("PinView", "Correct PIN");
                            finish();
                        } else {
                            statusView.setTextColor(Color.RED);
                            statusView.setText("Wrong PIN. Keypad Locked");
                            keyPadLockedFlag = true;
                            Log.v("PinView", "Wrong PIN");
                            try {
                                sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            new LockKeyPadOperation().execute("");
                            Intent intent=new Intent(EnterPin.this,MainActivity.class);
                            startActivity(intent);
                        }
                    }
                } else {
                    //Roll over
                    passwordInput.setText("");

                    userEntered = "";

                    statusView.setText("");

                    userEntered = userEntered + pressedButton.getText();
                    Log.v("PinView", "User entered=" + userEntered);

                    //Update pin boxes
                    passwordInput.setText("8");

                }


            }
        };


        button0 = (Button) findViewById(R.id.button0);
        //button0.setTypeface(xpressive);
        button0.setOnClickListener(pinButtonHandler);

        button1 = (Button) findViewById(R.id.button1);
        //button1.setTypeface(xpressive);
        button1.setOnClickListener(pinButtonHandler);

        button2 = (Button) findViewById(R.id.button2);
        //button2.setTypeface(xpressive);
        button2.setOnClickListener(pinButtonHandler);


        button3 = (Button) findViewById(R.id.button3);
        //button3.setTypeface(xpressive);
        button3.setOnClickListener(pinButtonHandler);

        button4 = (Button) findViewById(R.id.button4);
        //button4.setTypeface(xpressive);
        button4.setOnClickListener(pinButtonHandler);

        button5 = (Button) findViewById(R.id.button5);
        //button5.setTypeface(xpressive);
        button5.setOnClickListener(pinButtonHandler);

        button6 = (Button) findViewById(R.id.button6);
        //button6.setTypeface(xpressive);
        button6.setOnClickListener(pinButtonHandler);

        button7 = (Button) findViewById(R.id.button7);
        //button7.setTypeface(xpressive);
        button7.setOnClickListener(pinButtonHandler);

        button8 = (Button) findViewById(R.id.button8);
        //button8.setTypeface(xpressive);
        button8.setOnClickListener(pinButtonHandler);

        button9 = (Button) findViewById(R.id.button9);
        //button9.setTypeface(xpressive);
        button9.setOnClickListener(pinButtonHandler);


      //  buttonDelete = (Button) findViewById(R.id.buttonDeleteBack);
        //buttonDelete.setTypeface(xpressive);
      //  buttonDelete.setOnClickListener(pinButtonHandler);
//        buttonDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                System.out.println("hschhe hfhedhf bhujhk jhgfie");
//                System.out.println("hschhe hfhedhf bhujhk jhgfie");
//                System.out.println("hschhe hfhedhf bhujhk jhgfie");
//                System.out.println("hschhe hfhedhf bhujhk jhgfie");
//                System.out.println("hschhe hfhedhf bhujhk jhgfie");
//                System.out.println("hschhe hfhedhf bhujhk jhgfie");
//                buttonno--;
//                if(buttonno==1) {
//                    //passwordInput.setText(passwordInput.getText().toString() + "");
//                    passwordInput.setBackgroundResource(R.drawable.circle);
//                }
//                else if(buttonno==2)
//                {
//                    // passwordInput2.setText(passwordInput.getText().toString() + "");
//                    passwordInput2.setBackgroundResource(R.drawable.circle);
//                }
//                else if(buttonno==3)
//                {
//                    //  passwordInput3.setText(passwordInput.getText().toString() + "");
//                    passwordInput3.setBackgroundResource(R.drawable.circle);
//                }
//                else if(buttonno==4)
//                {
//                    // passwordInput4.setText(passwordInput.getText().toString() + "");
//                    passwordInput4.setBackgroundResource(R.drawable.circle);
//                }
//            }
//        });
    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub

        //App not allowed to go back to Parent activity until correct pin entered.
        return;
        //super.onBackPressed();
    }
    private class LockKeyPadOperation extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            for (int i = 0; i < 2; i++) {
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
            statusView.setText("");

            //Roll over
            passwordInput.setText("");

            userEntered = "";
            keyPadLockedFlag = false;
        }
        @Override
        protected void onPreExecute() {
        }
        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }
}