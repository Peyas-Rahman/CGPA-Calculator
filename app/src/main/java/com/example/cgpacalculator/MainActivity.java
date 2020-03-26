package com.example.cgpacalculator;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.cgpacalculator.databinding.ActivityMainBinding;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

import java.util.ArrayList;

    public class MainActivity extends AppCompatActivity implements TextWatcher, RewardedVideoAdListener {
    private ActivityMainBinding mBinding;
    private String beforeText;
    private int cheakNull = 0;
    private EditText[] editTexts = new EditText[9];

    private InterstitialAd mInterstitialAd;
    private RewardedVideoAd mRewardedVideoAd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        mBinding = DataBindingUtil.setContentView ( this, R.layout.activity_main );


        allId ( AppConstraint.VALIDATION );

        //Banner ad Initialization
        MobileAds.initialize( this, "ca-app-pub-8690549944486384~2892307672" );
        AdRequest adRequest = new AdRequest.Builder().build();
        mBinding.adView.loadAd(adRequest);


        // InterstitialAd Ads.
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-8690549944486384/5128971660");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.setAdListener(new AdListener(){
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                mInterstitialAd.show();
            }
        });



        // Use an activity context to get the rewarded video instance.
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        mRewardedVideoAd.setRewardedVideoAdListener(this);
        loadRewardedVideoAd();



    }

    // Reward Add Load
        private void loadRewardedVideoAd() {
            mRewardedVideoAd.loadAd("ca-app-pub-8690549944486384/7090341476",
                    new AdRequest.Builder().build());
        }




        @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        beforeText = s.toString ();
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {
        String text = s.toString ();
        if (text.isEmpty ()) {
            text = "";
        } else if (text.equals ( "." )) {
            s.replace ( 0, s.length (), beforeText );
            Toast.makeText ( this, "Please input valid CGPA", Toast.LENGTH_SHORT ).show ();
        } else if (cheakValid ( Float.parseFloat ( text ) )) {
            allId ( AppConstraint.FIX_BG );
        } else {
            if (!cheakValid ( Float.parseFloat ( text ) )) {
                s.replace ( 0, s.length (), beforeText );
                Toast.makeText ( this, "Please input valid CGPA", Toast.LENGTH_SHORT ).show ();
            } else {

            }
        }
    }

    public boolean cheakValid(float f) {
        if (2 <= f && f <= 4) {
            return true;
        } else {
            return false;
        }
    }

    public void clearAll(View view) {
        mBinding.btnClear.setEnabled(false);
        if (mRewardedVideoAd.isLoaded()) {
            mRewardedVideoAd.show();
        }
        allId ( AppConstraint.CLEAR );
        mBinding.resultTextView.setText ( "" );
        mBinding.resultTextView.setVisibility ( View.GONE );
    }

    public void Calculate(View view) {

        mBinding.btnCalculate.setEnabled(false);
        if (mRewardedVideoAd.isLoaded()) {
            mRewardedVideoAd.show();
        }
        ArrayList<Double> values = getValues ();
        Log.d ( "chk", "total:" + values.size () );
        if (values.size () > 0) {
            double sum = 0;
            int sum_of_v=0;
            for (int i = 0; i < values.size (); i++) {
                int v = 0;
                if (i >= 0 && i <= 2) {
                    v = 5;
                } else if (i == 3) {
                    v = 10;
                }
                else if (i == 4) {
                    v = 15;
                } else if (i == 5) {
                    v = 20;
                } else if (i == 6) {
                    v = 25;
                } else if (i == 7) {
                    v = 15;
                }
                sum_of_v=v+sum_of_v;
                sum = sum + (values.get ( i ) * v);

            }
            double result = sum / sum_of_v;
            String test = String.format ( "%.02f", result );
            allId ( AppConstraint.CHANGEBG );
            mBinding.resultTextView.setVisibility ( View.VISIBLE );
            mBinding.resultTextView.setText ( "Your CGPA is :  " + test );
            InputMethodManager imm = (InputMethodManager) getSystemService ( Context.INPUT_METHOD_SERVICE );
            imm.hideSoftInputFromWindow ( view.getWindowToken (), 0 );
        }


    }

    private ArrayList<Double> getValues() {
        ArrayList<Double> values = new ArrayList<> ();
        if (!mBinding.semester1.getText ().toString ().trim ().isEmpty ()) {
            Double d = Double.parseDouble ( mBinding.semester1.getText ().toString ().trim () );
            values.add ( d );
        }
        if (!mBinding.semester2.getText ().toString ().trim ().isEmpty ()) {
            Double d = Double.parseDouble ( mBinding.semester2.getText ().toString ().trim () );
            values.add ( d );
        }
        if (!mBinding.semester3.getText ().toString ().trim ().isEmpty ()) {
            Double d = Double.parseDouble ( mBinding.semester3.getText ().toString ().trim () );
            values.add ( d );
        }

        if (!mBinding.semester4.getText ().toString ().trim ().isEmpty ()) {
            Double d = Double.parseDouble ( mBinding.semester4.getText ().toString ().trim () );
            values.add ( d );
        }
        if (!mBinding.semester5.getText ().toString ().trim ().isEmpty ()) {
            Double d = Double.parseDouble ( mBinding.semester5.getText ().toString ().trim () );
            values.add ( d );
        }
        if (!mBinding.semester6.getText ().toString ().trim ().isEmpty ()) {
            Double d = Double.parseDouble ( mBinding.semester6.getText ().toString ().trim () );
            values.add ( d );
        }
        if (!mBinding.semester7.getText ().toString ().trim ().isEmpty ()) {
            Double d = Double.parseDouble ( mBinding.semester7.getText ().toString ().trim () );
            values.add ( d );
        }
        if (!mBinding.semester8.getText ().toString ().trim ().isEmpty ()) {
            Double d = Double.parseDouble ( mBinding.semester8.getText ().toString ().trim () );
            values.add ( d );
        }
        return values;

    }

    public void allId(String change) {
        int i;
        for (i = 1; i <= 8; i++) {
            String idName = "semester" + i;
            int resId = getResources ().getIdentifier ( idName, "id", getPackageName () );
            editTexts[i] = findViewById ( resId );

            if (change.contains ( AppConstraint.CLEAR )) {
                (editTexts[i]).setText ( "" );
                editTexts[i].setBackgroundResource ( R.drawable.result_shape );
            } else if (change.equals ( AppConstraint.CHEAK )) {
                if ((editTexts[i]).getText ().toString ().isEmpty ()) {
                    cheakNull = 0;
                } else {
                    cheakNull = 1;
                }
            } else if (change.equals ( AppConstraint.CHANGEBG )) {
                if (editTexts[i].getText ().toString ().isEmpty ()||editTexts[i].getText ().equals ( "" )) {
                    editTexts[i].setBackgroundResource ( R.drawable.edit_bg );
                } else {
                    editTexts[i].setBackgroundResource ( R.drawable.result_shape );
                }
            } else if (change.equals ( AppConstraint.VALIDATION )) {
                editTexts[i].addTextChangedListener ( this );
            } else if (change.equals ( AppConstraint.FIX_BG )) {
                if (editTexts[i].getBackground ().getConstantState ().equals (
                        getResources ().getDrawable ( R.drawable.edit_bg ).getConstantState () ) && !editTexts[i].getText ().toString ().isEmpty ()) {
                    editTexts[i].setBackgroundResource ( R.drawable.result_shape );
                }
            }
        }
    }

        @Override
        public void onRewardedVideoAdLoaded() {
            Toast.makeText(this, "onRewardedVideoAdLoaded", Toast.LENGTH_SHORT).show();
            mBinding.btnCalculate.setEnabled(true);
            mBinding.btnClear.setEnabled(true);

        }

        @Override
        public void onRewardedVideoAdOpened() {
            Toast.makeText(this, "onRewardedVideoAdOpened", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onRewardedVideoStarted() {
            Toast.makeText(this, "onRewardedVideoStarted", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onRewardedVideoAdClosed() {
            Toast.makeText(this, "onRewardedVideoAdClosed", Toast.LENGTH_SHORT).show();
            // Load the next rewarded video ad.
            loadRewardedVideoAd();

        }

        @Override
        public void onRewarded(RewardItem reward) {
            Toast.makeText(this, "onRewarded! currency: " + reward.getType() + "  amount: " +
                    reward.getAmount(), Toast.LENGTH_SHORT).show();
            // Reward the user.

        }

        @Override
        public void onRewardedVideoAdLeftApplication() {
            Toast.makeText(this, "onRewardedVideoAdLeftApplication",
                    Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onRewardedVideoAdFailedToLoad(int i) {
            Toast.makeText(this, "onRewardedVideoAdFailedToLoad", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onRewardedVideoCompleted() {
            Toast.makeText(this, "onRewardedVideoCompleted", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onResume() {
            mRewardedVideoAd.resume(this);
            super.onResume();
        }

        @Override
        public void onPause() {
            mRewardedVideoAd.pause(this);
            super.onPause();
        }

        @Override
        public void onDestroy() {
            mRewardedVideoAd.destroy(this);
            super.onDestroy();
        }

    }
