package com.android.test;

import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String first = "Get on road price";
    String second = "Check Eligibility";
    private TextView textView;
    private TextView sTextView;
    private CardView cardView;
    private int viewWidth;
    private String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cardView = (CardView) findViewById(R.id.cardView);
        textView = (TextView) findViewById(R.id.first);
        sTextView = (TextView) findViewById(R.id.second);
        Log.i(TAG, first+" Text width is "+ getWidthOfText(first));
        getCardWidth();
    }

    private void getCardWidth()
    {
        ViewTreeObserver viewTreeObserver = cardView.getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    cardView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    viewWidth = cardView.getWidth();
                    int halfWidth = viewWidth / 2;
                    Log.i(TAG, "Card width is: "+viewWidth);
                    if(viewWidth > getWidthOfText(first) + getWidthOfText(second) && (getWidthOfText(first) < halfWidth && getWidthOfText(second) < halfWidth))
                    {
                        textView.setText(first);
                        sTextView.setText(second);
                    }
                    else
                    {
                        sTextView.setVisibility(View.GONE);
                        textView.setText(first);
                    }
                }
            });
        }
    }

    private int getWidthOfText(String text)
    {
        Rect bounds = new Rect();
        Paint textPaint = textView.getPaint();
        textPaint.getTextBounds(text,0,text.length(),bounds);
        return bounds.width();
    }
}
