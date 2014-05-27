package in.tanjo.button.app.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;

import in.tanjo.button.app.R;

public class Setting {

  private Context mContext;
  private SharedPreferences mPreferences;

  public Setting(Context context) {
    mContext = context;
    mPreferences = mContext.getSharedPreferences("button_setting.setting", Context.MODE_PRIVATE);
  }

  public int getShowText() {
    return mPreferences.getInt("ISSHOWTEXT", View.INVISIBLE);
  }

  public void setShowText(int showText) {
    mPreferences.edit().putInt("ISSHOWTEXT", showText).commit();
  }

  public void setButtonText(String text) {
    mPreferences.edit().putString("BUTTONTEXT", text).commit();
  }

  public String getButtonText() {
    return mPreferences.getString("BUTTONTEXT", "O K");
  }

  public int getSe() {
    return mPreferences.getInt("SERESOURCE", R.raw.puni);
  }

  public void setSe(int se) {
    mPreferences.edit().putInt("SERESOURCE", se).commit();
  }

  public String getIntent() {
    return mPreferences.getString("INTENT", null);
  }

  public void setIntent(String intent) {
    if (intent.length() > 0) {
      mPreferences.edit().putString("INTENT", intent).commit();
    } else {
      mPreferences.edit().putString("INTENT", null).commit();
    }
  }
}
