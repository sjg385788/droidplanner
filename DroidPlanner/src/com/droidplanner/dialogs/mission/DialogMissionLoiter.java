package com.droidplanner.dialogs.mission;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.droidplanner.R;
import com.droidplanner.widgets.SeekBarWithText.SeekBarWithText;
import com.droidplanner.widgets.SeekBarWithText.SeekBarWithText.OnTextSeekBarChangedListner;

public class DialogMissionLoiter extends DialogMission implements
		OnTextSeekBarChangedListner, OnCheckedChangeListener{

	private SeekBarWithText loiterRadiusSeekBar;
	private CheckBox loiterCCW;

	@Override
	protected int getResource() {
		return R.layout.dialog_mission_loiter;
	}
	
	protected View buildView() {
		super.buildView();		

		loiterCCW = (CheckBox) view.findViewById(R.string.loiter_ccw);
		if (wp.missionItem.param3 < 0) {
			loiterCCW.setChecked(true);
		} else {
			loiterCCW.setChecked(false);
		}
		loiterCCW.setOnCheckedChangeListener(this);


		loiterRadiusSeekBar = (SeekBarWithText) view
				.findViewById(R.id.loiterRadius);
		loiterRadiusSeekBar .setOnChangedListner(this);
		loiterRadiusSeekBar.setAbsValue(wp.missionItem.param3);


		return view;
	}
	
	

	@Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		wp.missionItem.param3 = (float) loiterRadiusSeekBar.getValue();
		if (loiterCCW.isChecked()) {
			wp.missionItem.param3 *= -1.0;
		}
    }
	
	@Override
	public void onSeekBarChanged() {
		wp.missionItem.param3 = (float) loiterRadiusSeekBar.getValue();
		if (loiterCCW.isChecked()) {
			wp.missionItem.param3 *= -1.0;
		}

	}
}
