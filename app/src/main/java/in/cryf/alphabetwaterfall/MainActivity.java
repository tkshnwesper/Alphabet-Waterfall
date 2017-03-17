package in.cryf.alphabetwaterfall;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    class ScreenResolution {

        int height;
        int width;

        ScreenResolution() {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            height = displayMetrics.heightPixels;
            width = displayMetrics.widthPixels;
        }
    }

    String randomString() {
        Random rand = new Random();
        int n = 1 + rand.nextInt(5);
        char[] chars = new char[n];
        for (int i = 0; i < n; i++) {
            chars[i] = (char)('a' + rand.nextInt(26));
        }
        return chars.toString();
    }

    void running() {
        ScreenResolution scr = new ScreenResolution();
        Bubble b = new Bubble(this);
        b.setString(randomString());
        b.animate(new Random().nextInt(scr.width));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText et = (EditText) findViewById(R.id.editText);
//        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.showSoftInput(et, InputMethodManager.SHOW_FORCED);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                EditText et = (EditText) findViewById(R.id.editText);
//                et.requestFocus();
////                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
////                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
////                imm.showSoftInput(et, InputMethodManager.SHOW_FORCED);
//            }
//        });

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Bubble b = new Bubble(this);
//                b.setString("vishal");
//                b.animate(300);
//            }
//        }).start();



        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

//                if (charSequence.length() >) {
                    Log.i("str", charSequence.toString());
//                    char lastChar = charSequence.charAt(charSequence.length() - 1);
//                }
                if(charSequence.charAt(i)=='\n') {
                    Log.i("str","you have pressed enter");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    running();
                    try {
                        Thread.sleep(500);
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }

//    void startAnimation(View v) {
//        Button btn = (Button) v;
//        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.letter_falling);
//        btn.startAnimation(anim);
//    }
}
