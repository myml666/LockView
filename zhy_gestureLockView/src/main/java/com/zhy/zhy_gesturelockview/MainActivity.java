package com.zhy.zhy_gesturelockview;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.zhy.zhy_gesturelockview.view.GestureLockViewGroup;
import com.zhy.zhy_gesturelockview.view.GestureLockViewGroup.OnGestureLockViewListener;

public class MainActivity extends Activity
{

	private GestureLockViewGroup mGestureLockViewGroup;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mGestureLockViewGroup = (GestureLockViewGroup) findViewById(R.id.id_gestureLockViewGroup);
		mGestureLockViewGroup.setAnswer(new int[] { 1, 2, 3, 4 });
		mGestureLockViewGroup
				.setOnGestureLockViewListener(new OnGestureLockViewListener()
				{

					@Override
					public void onUnmatchedExceedBoundary()
					{
						Toast.makeText(MainActivity.this, "错误5次...",
								Toast.LENGTH_SHORT).show();
						mGestureLockViewGroup.setUnMatchExceedBoundary(5);
					}

					@Override
					public void onGestureEvent(boolean matched)
					{
						Toast.makeText(MainActivity.this, matched+"",
								Toast.LENGTH_SHORT).show();
					}

					@Override
					public void onBlockSelected(int cId)
					{
						Toast.makeText(MainActivity.this, ""+cId, Toast.LENGTH_SHORT).show();
					}
				});
	}

}
