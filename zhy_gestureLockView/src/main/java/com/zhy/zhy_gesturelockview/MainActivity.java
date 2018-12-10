package com.zhy.zhy_gesturelockview;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.zhy.zhy_gesturelockview.view.GestureLockViewGroup;
import com.zhy.zhy_gesturelockview.view.GestureLockViewGroup.OnGestureLockViewListener;

import java.util.ArrayList;

public class MainActivity extends Activity
{

	private GestureLockViewGroup mGestureLockViewGroup;
	private ArrayList<Integer> arrayList;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
arrayList=new ArrayList<Integer>();
		mGestureLockViewGroup = (GestureLockViewGroup) findViewById(R.id.id_gestureLockViewGroup);
//		mGestureLockViewGroup.setAnswer(new int[] { 1, 2, 3, 4 });
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
					    if (arrayList.size()<4){
                            Toast.makeText(MainActivity.this, "至少链接4个点", Toast.LENGTH_SHORT).show();
                            arrayList.clear();
                        }else {

							save();
                        }
//						Toast.makeText(MainActivity.this, matched+"",
//								Toast.LENGTH_SHORT).show();
					}

					@Override
					public void onBlockSelected(int cId)
					{
					    arrayList.add(cId);
//						Toast.makeText(MainActivity.this, ""+cId, Toast.LENGTH_SHORT).show();
					}
				});
	}

	private void save() {
		String pass="";
		for (int x=0;x<arrayList.size();x++){
			if(x==arrayList.size()-1){
				pass+=arrayList.get(x)+"";
			}else {
				pass+=arrayList.get(x)+",";
			}
		}
		SharedPreferences sharedPreferences=getSharedPreferences("pass",MODE_PRIVATE);
		SharedPreferences.Editor edit = sharedPreferences.edit();
		edit.putString("pass",pass);
		edit.commit();
		Toast.makeText(MainActivity.this, "设置成功", Toast.LENGTH_SHORT).show();
	}
public void onClick(View view){
		startActivity(new Intent(this,SecondActivity.class));
}
}
