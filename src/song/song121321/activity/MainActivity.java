package song.song121321.activity;

import android.app.TabActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;
import song.song121321.R;

import song.song121321.app.MyApplication;
import song.song121321.util.AccountSharedPreferenceHelper;

@SuppressWarnings("deprecation")
public class MainActivity extends TabActivity {
    private TabHost mTabHost;
    private RadioGroup mTabButtonGroup;
    public static final String TAB_CONSUME = "consume";
    public static final String TAB_GIS = "gis";
    public static final String TAB_DEVICE = "device";
    public static final String TAB_PERSON = "person";
    private RadioButton rButtonConsume, rButtonGis, rButtonFacility, rButtonI;
    AccountSharedPreferenceHelper asph;
    MaterialDialog.Builder mBuilder;
    MaterialDialog mMaterialDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById();
        initView();
        MyApplication.getInstance().addActivity(this);


    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();

    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();

    }

    private void findViewById() {
        mTabButtonGroup = (RadioGroup) findViewById(R.id.home_radio_button_group);
        rButtonConsume = (RadioButton) findViewById(R.id.home_tab_consume);
        rButtonGis = (RadioButton) findViewById(R.id.home_tab_gis);
        rButtonFacility = (RadioButton) findViewById(R.id.home_tab_facility);
        rButtonI = (RadioButton) findViewById(R.id.home_tab_i);
    }

    private void initView() {

        mTabHost = getTabHost();

        Intent iConsume = new Intent(this, ConsumeActivity.class);
        Intent iGis = new Intent(this, GisActivity.class);
        Intent iDevice = new Intent(this, DeviceActivity.class);
        Intent i_wojia = new Intent(this, IActivity.class);

        mTabHost.addTab(mTabHost.newTabSpec(TAB_CONSUME)
                .setIndicator(TAB_CONSUME).setContent(iConsume));
        mTabHost.addTab(mTabHost.newTabSpec(TAB_GIS)
                .setIndicator(TAB_GIS).setContent(iGis));
        mTabHost.addTab(mTabHost.newTabSpec(TAB_DEVICE).setIndicator(TAB_DEVICE)
                .setContent(iDevice));
        mTabHost.addTab(mTabHost.newTabSpec(TAB_PERSON).setIndicator(TAB_PERSON)
                .setContent(i_wojia));
        mTabButtonGroup
                .setOnCheckedChangeListener(new OnCheckedChangeListener() {
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        switch (checkedId) {
                            case R.id.home_tab_consume:
                                mTabHost.setCurrentTabByTag(TAB_CONSUME);
                                changeTextColor(1);
                                break;
                            case R.id.home_tab_gis:
                                mTabHost.setCurrentTabByTag(TAB_GIS);
                                changeTextColor(2);
                                break;
                            case R.id.home_tab_facility:
                                mTabHost.setCurrentTabByTag(TAB_DEVICE);
                                changeTextColor(3);
                                break;

                            case R.id.home_tab_i:
                                mTabHost.setCurrentTabByTag(TAB_PERSON);
                                changeTextColor(4);
                                break;
                            default:
                                break;
                        }
                    }
                });
    }

    private void changeTextColor(int index) {
        switch (index) {
            case 1:
                rButtonConsume.setTextColor(getResources().getColor(
                        R.color.actionbar_bg));
                rButtonGis.setTextColor(getResources().getColor(
                        R.color.main_textcolor_normal));
                rButtonFacility.setTextColor(getResources().getColor(
                        R.color.main_textcolor_normal));
                rButtonI.setTextColor(getResources().getColor(
                        R.color.main_textcolor_normal));
                break;
            case 2:
                rButtonConsume.setTextColor(getResources().getColor(
                        R.color.main_textcolor_normal));
                rButtonGis.setTextColor(getResources().getColor(
                        R.color.actionbar_bg));
                rButtonFacility.setTextColor(getResources().getColor(
                        R.color.main_textcolor_normal));
                rButtonI.setTextColor(getResources().getColor(
                        R.color.main_textcolor_normal));
                break;
            case 3:
                rButtonConsume.setTextColor(getResources().getColor(
                        R.color.main_textcolor_normal));
                rButtonGis.setTextColor(getResources().getColor(
                        R.color.main_textcolor_normal));
                rButtonFacility.setTextColor(getResources().getColor(
                        R.color.actionbar_bg));
                rButtonI.setTextColor(getResources().getColor(
                        R.color.main_textcolor_normal));
                break;

            case 4:
                rButtonConsume.setTextColor(getResources().getColor(
                        R.color.main_textcolor_normal));
                rButtonGis.setTextColor(getResources().getColor(
                        R.color.main_textcolor_normal));
                rButtonFacility.setTextColor(getResources().getColor(
                        R.color.main_textcolor_normal));
                rButtonI.setTextColor(getResources().getColor(
                        R.color.actionbar_bg));
                break;

            default:
                break;
        }
    }


    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN
                && event.getRepeatCount() == 0) {
            mBuilder = new MaterialDialog.Builder(MainActivity.this);
            mBuilder.title(R.string.system_prompt);
            mBuilder.content(R.string.system_sureifexit);
            mBuilder.positiveText(R.string.system_sure);
            mBuilder.titleGravity(GravityEnum.CENTER);
            mBuilder.buttonsGravity(GravityEnum.START);
            mBuilder.negativeText(R.string.system_cancel);
            mBuilder.theme(Theme.LIGHT);
            mBuilder.cancelable(false);
            mMaterialDialog = mBuilder.build();
            mMaterialDialog.show();
            mBuilder.onAny(new MaterialDialog.SingleButtonCallback() {
                @Override
                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                    if (which == DialogAction.POSITIVE) {
                        MyApplication.getInstance().exit();
                    } else if (which == DialogAction.NEGATIVE) {
                        mMaterialDialog.dismiss();
                    }
                }
            });
        }

        return super.dispatchKeyEvent(event);
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        MyApplication.getInstance().removeActivity(this);
        //unregisterReceiver(TabReceiver);
    }

    BroadcastReceiver TabReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub
            // MyApplication.getInstance().type=2;

        }
    };
}
