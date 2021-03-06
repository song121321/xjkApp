package song.song121321.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.jtslkj.R;

import song.song121321.app.MyApplication;
import song.song121321.fragment.ISettingSafetyFragment;
import song.song121321.fragment.ISettingUniverseFragment;

public class ISettingContentActivity extends FragmentActivity {
	private TextView mTextView;
	private ImageView iv_top_back;
	private static Fragment[] fragments;
	private static FragmentTransaction transaction;
	public MaterialDialog.Builder mBuilder;
	public MaterialDialog mMaterialDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_i_setting_content);
		MyApplication.getInstance().addActivity(this);
		FragmentManager fragmentManager = getSupportFragmentManager();
		String[] naviTexts = getResources().getStringArray(R.array.setting_arr);
		transaction = fragmentManager.beginTransaction();
		initFragmentArray();
		Intent i = getIntent();
		int position = i.getIntExtra("position", -1);
		transaction.replace(R.id.fl_wojia_setting_container,
				PlaceholderFragment.newInstance(position)).commit();
		initBarTxt();
		mTextView = (TextView) findViewById(R.id.tv_actionbar_usual_title);
		mTextView.setText(naviTexts[position]);
	}

	public static class PlaceholderFragment {
		public static Fragment newInstance(int position) {
			return showOrHide(transaction, position);
		}
	}

	private static Fragment showOrHide(FragmentTransaction transaction,
			int position) {
		transaction.show(fragments[position]);
		for (int i = 0; i < fragments.length && i != position; i++) {
			transaction.hide(fragments[i]);
		}
		return fragments[position];
	}

	private void initFragmentArray() {
		Fragment f0 = new ISettingUniverseFragment();
		Fragment f1 = new ISettingSafetyFragment();
		// Fragment f4 = new ISetting_ShareAccount();
		// Fragment f5 = new ISetting_AboutUs();
		// Fragment f6 = new ISetting_DeleteRestore();
		// Fragment f7 = new ISetting_Safety();
		fragments = new Fragment[4];
		fragments[0] = f0;
		fragments[1] = f1;
		// fragments[4] = f4;
		// fragments[5] = f5;
		// fragments[6] = f6;
		// fragments[7] = f7;
	}

	private void initBarTxt() {
		iv_top_back = (ImageView) findViewById(R.id.iv_actionbar_left);
		iv_top_back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});

	}
}
