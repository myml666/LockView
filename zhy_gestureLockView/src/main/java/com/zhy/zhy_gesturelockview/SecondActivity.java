package com.zhy.zhy_gesturelockview;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.zhy.zhy_gesturelockview.view.GestureLockViewGroup;

import java.util.ArrayList;

public class SecondActivity extends Activity {
    private GestureLockViewGroup mGestureLockViewGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGestureLockViewGroup = (GestureLockViewGroup) findViewById(R.id.id_gestureLockViewGroup);
        String string = getSharedPreferences("pass", MODE_PRIVATE).getString("pass", "");
        String[] split = string.split(",");
        int[] sss=new int[split.length];
        for (int x=0;x<split.length;x++){
            sss[x]=Integer.valueOf(split[x]);
        }
        mGestureLockViewGroup.setAnswer(sss);
        mGestureLockViewGroup
                .setOnGestureLockViewListener(new GestureLockViewGroup.OnGestureLockViewListener()
                {

                    @Override
                    public void onUnmatchedExceedBoundary()
                    {
                        Toast.makeText(SecondActivity.this, "错误5次...",
                                Toast.LENGTH_SHORT).show();
                        mGestureLockViewGroup.setUnMatchExceedBoundary(5);
                    }

                    @Override
                    public void onGestureEvent(boolean matched)
                    {
						Toast.makeText(SecondActivity.this, matched+"",
								Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onBlockSelected(int cId)
                    {
                    }
                });
    }
}
