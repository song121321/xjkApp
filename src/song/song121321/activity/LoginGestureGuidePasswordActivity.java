package song.song121321.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.jtslkj.R;

import song.song121321.app.MyApplication;
import song.song121321.util.LoginGesturePatternUtils;

public class LoginGestureGuidePasswordActivity extends Activity {
	LoginGesturePatternUtils lgp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		lgp = new LoginGesturePatternUtils(this);
		MyApplication.getInstance().addActivity(this);
		setContentView(R.layout.logingesture_password_guide);
		findViewById(R.id.gesturepwd_guide_btn).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				lgp.clearLock();
				Intent intent = new Intent(LoginGestureGuidePasswordActivity.this,
						LoginGestureCreatePasswordActivity.class);
				// 打开新的Activity
				startActivity(intent);
				finish();
			}
		});
	}

}
