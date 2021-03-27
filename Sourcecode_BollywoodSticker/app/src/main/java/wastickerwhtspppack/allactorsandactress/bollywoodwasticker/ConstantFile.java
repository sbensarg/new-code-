package wastickerwhtspppack.allactorsandactress.bollywoodwasticker;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.LinearLayout;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdListener;
import com.facebook.ads.AdSettings;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.kaopiz.kprogresshud.KProgressHUD.Style;

public class ConstantFile {

    public static Intent mIntent;
    public static Context mContext;
    public static InterstitialAd.InterstitialLoadAdConfig kInterstitialLoadAdConfig;
    @SuppressLint("StaticFieldLeak")
    private static KProgressHUD Bollwood_FB_KP_Pro_HUD;
    @SuppressLint("StaticFieldLeak")
    private static KProgressHUD Bollwood_Admob_KP_Pro_HUD;
    @SuppressLint("StaticFieldLeak")
    private static Context Bollwood_Context;

    public static String Bollwood_APplication_More = "https://play.google.com/store/apps/developer?id=Technobytes+Infotech";

    public static AdView Bollwood__load_Banner_Ads(LinearLayout kLinearLayout , Activity kActivity) {

        Log.e("ESSO " , "FAN");
        AdSettings.addTestDevice("65b3e391-6eee-4a27-a1c2-9cdd2fd37b8e");
        AdView adView = new AdView(kActivity, kActivity.getResources().getString(R.string.FAN_BAnner_ADS), AdSize.BANNER_HEIGHT_50);
        // Add the ad view to your activity layout
        kLinearLayout.addView(adView);
        AdListener kAdListener = new AdListener(){
            @Override
            public void onError(Ad ad, AdError adError) {
                ad.loadAd();
                Log.e("FAN" , "onError: "+adError.getErrorMessage());
            }

            @Override
            public void onAdLoaded(Ad ad) {
                Log.e("FAN" , "onAdLoaded");
            }

            @Override
            public void onAdClicked(Ad ad) {

            }

            @Override
            public void onLoggingImpression(Ad ad) {

            }
        };
        AdView.AdViewLoadConfig kAdViewLoadConfig = adView.buildLoadAdConfig().withAdListener(kAdListener).build();
        // Request an ad
        adView.loadAd(kAdViewLoadConfig);

        return adView;
    }

  public static void facebook_show_Interstitial(Context ctx , Intent kIntent, InterstitialAd fbinterstitialAd) {

        mIntent = kIntent;
        mContext = ctx;
        if (fbinterstitialAd.isAdLoaded() ) {
            fbinterstitialAd.show();
        }else{
            ctx.startActivity(kIntent);
        }
    }

    public static InterstitialAd Bollywood_load_FaceBook_InterstitialAds(Context context) {

        final com.facebook.ads.InterstitialAd fbinterstitialAd = new com.facebook.ads.InterstitialAd(context, context.getResources().getString(R.string.FAN_Intertitial_ADS));
        Bollwood_Context = context;
        mContext = context;

        InterstitialAdListener kInterstitialAdListener = new InterstitialAdListener(){
            @Override
            public void onInterstitialDisplayed(Ad ad) {

            }

            @Override
            public void onInterstitialDismissed(Ad ad) {
                fbinterstitialAd.loadAd(kInterstitialLoadAdConfig);
                mContext.startActivity(mIntent);
            }

            @Override
            public void onError(Ad ad, AdError adError) {
            }

            @Override
            public void onAdLoaded(Ad ad) {
                //ConstantFile.facebook_show_Interstitial(kInterstitialAd);
            }

            @Override
            public void onAdClicked(Ad ad) {

            }

            @Override
            public void onLoggingImpression(Ad ad) {

            }
        };

        kInterstitialLoadAdConfig = fbinterstitialAd.buildLoadAdConfig().withAdListener(kInterstitialAdListener).build();

        fbinterstitialAd.loadAd(kInterstitialLoadAdConfig);

        return fbinterstitialAd;
    }
}
