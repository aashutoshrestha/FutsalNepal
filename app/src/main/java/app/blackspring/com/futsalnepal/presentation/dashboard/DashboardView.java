package app.blackspring.com.futsalnepal.presentation.dashboard;

import android.Manifest;
import android.content.DialogInterface;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.tbruyelle.rxpermissions.RxPermissions;

import net.gotev.speech.GoogleVoiceTypingDisabledException;
import net.gotev.speech.Speech;
import net.gotev.speech.SpeechDelegate;
import net.gotev.speech.SpeechRecognitionNotAvailable;
import net.gotev.speech.SpeechUtil;
import net.gotev.speech.ui.SpeechProgressView;

import java.util.List;

import app.blackspring.com.futsalnepal.R;
import app.blackspring.com.futsalnepal.presentation.adapter.ViewPagerAdapter;
import app.blackspring.com.futsalnepal.presentation.fragments.ListFragment;
import app.blackspring.com.futsalnepal.presentation.fragments.Map;

public class DashboardView extends AppCompatActivity implements SpeechDelegate {

    TabLayout viewOption;
    ViewPager viewPager;
    FloatingSearchView floatingSearchView;
    private SpeechProgressView progress;
    private LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_view);
        viewOption = findViewById(R.id.tl_view_option);
        viewPager = findViewById(R.id.vp_viewpager);
        floatingSearchView = findViewById(R.id.floating_search_view);
        setupViewPager(viewPager);
        viewOption.setupWithViewPager(viewPager);
        Speech.init(this, getPackageName());


        linearLayout = findViewById(R.id.linearLayout);
        progress =  findViewById(R.id.progress);

        int[] colors = {
                ContextCompat.getColor(this, R.color.colorPrimary),
                ContextCompat.getColor(this, R.color.colorPrimaryDark),
                ContextCompat.getColor(this, R.color.colorPrimary),
                ContextCompat.getColor(this, R.color.colorPrimaryDark),
                ContextCompat.getColor(this, R.color.colorPrimary)

        };
        progress.setColors(colors);

        floatingSearchView.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.action_voice_rec) {
                onButtonClick();
            }
        });

        floatingSearchView.setOnQueryChangeListener((oldQuery, newQuery) -> {
            
        });
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new ListFragment(), "LIST");
        adapter.addFragment(new Map(), "MAP");
        viewPager.setAdapter(adapter);
    }

    private void onButtonClick() {
        if (Speech.getInstance().isListening()) {
            Speech.getInstance().stopListening();
        } else {
            RxPermissions.getInstance(this)
                    .request(Manifest.permission.RECORD_AUDIO)
                    .subscribe(granted -> {
                        if (granted) { // Always true pre-M
                            onRecordAudioPermissionGranted();
                        } else {
                            Toast.makeText(this, R.string.permission_required, Toast.LENGTH_LONG);
                        }
                    });
        }
    }

    private void onRecordAudioPermissionGranted() {
        linearLayout.setVisibility(View.VISIBLE);

        try {
            Speech.getInstance().stopTextToSpeech();
            Speech.getInstance().startListening(progress, this);

        } catch (SpeechRecognitionNotAvailable exc) {
            showSpeechNotSupportedDialog();

        } catch (GoogleVoiceTypingDisabledException exc) {
            showEnableGoogleVoiceTyping();
        }
    }


    @Override
    public void onStartOfSpeech() {
    }

    @Override
    public void onSpeechRmsChanged(float value) {
        //Log.d(getClass().getSimpleName(), "Speech recognition rms is now " + value +  "dB");
    }

    @Override
    public void onSpeechResult(String result) {

        linearLayout.setVisibility(View.GONE);

        floatingSearchView.setSearchText(result);

      /*  if (result.isEmpty()) {
            Speech.getInstance().say(getString(R.string.repeat));

        } else {
            Speech.getInstance().say(result);
        }*/
    }

    @Override
    public void onSpeechPartialResults(List<String> results) {
        floatingSearchView.setSearchText("");
        for (String partial : results) {

        }
    }

    private void showSpeechNotSupportedDialog() {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        SpeechUtil.redirectUserToGoogleAppOnPlayStore(DashboardView.this);
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.speech_not_available)
                .setCancelable(false)
                .setPositiveButton(R.string.yes, dialogClickListener)
                .setNegativeButton(R.string.no, dialogClickListener)
                .show();
    }

    private void showEnableGoogleVoiceTyping() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.enable_google_voice_typing)
                .setCancelable(false)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // do nothing
                    }
                })
                .show();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Speech.getInstance().shutdown();
    }
}
