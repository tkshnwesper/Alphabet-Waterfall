package in.cryf.alphabetwaterfall;

import android.app.Activity;
import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Saurabh on 17-03-2017.
 * The text bubbles that fall
 */

public class Bubble {

    private String str;
    private TextView textView;
    private Activity activity;

    Bubble(Activity activity) {
        textView = new TextView(activity);
        this.activity = activity;
        LinearLayout lin = (LinearLayout) activity.findViewById(R.id.word_container);
        lin.addView(textView);
    }

    void setString(String str) {
        this.str = str;
    }

    void animate(final float x) {
        final Animation anim = AnimationUtils.loadAnimation(activity, R.anim.letter_falling);
        anim.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                LinearLayout lin = (LinearLayout) activity.findViewById(R.id.word_container);
                lin.removeView(textView);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView.setText(str);
                textView.setTranslationX(x);
                textView.startAnimation(anim);
            }
        });

    }
}
