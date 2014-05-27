package in.tanjo.button.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import in.tanjo.button.app.util.Setting;

public class FullscreenActivity extends Activity {

  private Button mButton;
  private TextView mTextView;
  private MediaPlayer mSe;
  private Setting mSetting;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setVolumeControlStream(AudioManager.STREAM_MUSIC);

    getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    setContentView(R.layout.fullscreen_activity);

    mSetting = new Setting(this);

    mSe = MediaPlayer.create(this, mSetting.getSe());

    mButton = (Button) findViewById(R.id.aka_button);
    mButton.setOnTouchListener(mViewOnTouchListener);

    mTextView = (TextView) findViewById(R.id.text);
    mTextView.setVisibility(mSetting.getShowText());
    mTextView.setText(mSetting.getButtonText());
    mTextView.setOnTouchListener(mViewOnTouchListener);
  }

  private View.OnTouchListener mViewOnTouchListener = new View.OnTouchListener() {
    @Override
    public boolean onTouch(View v, MotionEvent event) {
      if (event.getAction() == MotionEvent.ACTION_DOWN) {
        mButton.setBackgroundResource(R.drawable.button);
        mTextView.setTextColor(Color.argb(0xFF, 0xDD, 0xDD, 0x22));
        mSe.start();
      } else if (event.getAction() == MotionEvent.ACTION_UP) {
        mButton.setBackgroundResource(R.drawable.button2);
        mTextView.setTextColor(Color.YELLOW);
        if (mSetting.getIntent() != null) {

        }
      }
      return true;
    }
  };

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    super.onCreateOptionsMenu(menu);

    menu.add(0, 10, 0, "文字変更");
    menu.add(0, 20, 0, "文字表示・非表示");
    menu.add(0, 25, 0, "インテント");
    menu.add(0, 30, 0, "音変更");
    menu.add(0, 40, 0, "終了");

    return true;
  }

  @Override
  public boolean onMenuItemSelected(int featureId, MenuItem item) {
    super.onMenuItemSelected(featureId, item);
    switch (item.getItemId()) {
      case 10:
        if (mTextView != null) {
          final EditText editText = new EditText(this);
          new AlertDialog.Builder(this)
              .setIcon(R.drawable.ic_launcher)
              .setTitle("文字変更")
              .setView(editText)
              .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                  mSetting.setButtonText(editText.getText().toString());
                  mTextView.setText(mSetting.getButtonText());
                }
              })
              .setNegativeButton("キャンセル", null)
              .show();
        }
        break;
      case 20:
        if (mTextView != null) {
          if (mSetting.getShowText() == View.VISIBLE) {
            mSetting.setShowText(View.INVISIBLE);
          } else {
            mSetting.setShowText(View.VISIBLE);
          }
          mTextView.setVisibility(mSetting.getShowText());
        }
        break;
      case 25:
        final EditText editText = new EditText(this);
        new AlertDialog.Builder(this)
            .setIcon(R.drawable.ic_launcher)
            .setTitle("インテント")
            .setView(editText)
            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialog, int which) {
                mSetting.setIntent(editText.getText().toString());
              }
            })
            .setNegativeButton("キャンセル", null)
            .show();
        break;
      case 30:
        if (mSe != null) {
          if (mSetting.getSe() == R.raw.puni) {
            mSetting.setSe(R.raw.pochi);
          } else {
            mSetting.setSe(R.raw.puni);
          }
          mSe = MediaPlayer.create(this, mSetting.getSe());
        }
        break;
      case 40:
        finish();
        break;
    }
    return true;
  }
}
