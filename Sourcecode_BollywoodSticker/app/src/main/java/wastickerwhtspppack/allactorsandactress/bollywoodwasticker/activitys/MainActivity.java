package wastickerwhtspppack.allactorsandactress.bollywoodwasticker.activitys;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.ebanx.swipebtn.OnActiveListener;
import com.ebanx.swipebtn.OnStateChangeListener;
import com.ebanx.swipebtn.SwipeButton;
import com.facebook.ads.AdError;
import com.facebook.ads.AdSettings;
import com.facebook.ads.AdView;
import com.facebook.ads.NativeAd;
import com.facebook.ads.NativeAdScrollView;
import com.facebook.ads.NativeAdsManager;

import wastickerwhtspppack.allactorsandactress.bollywoodwasticker.ConstantFile;
import wastickerwhtspppack.allactorsandactress.bollywoodwasticker.R;

public class MainActivity extends AppCompatActivity {

    Context Bollywood_Context;
    LinearLayout BollywoodLL_Privacy_Policy, BollywoodRate_Us_LL;
    private NativeAdsManager BollywoodFacebook_Manager_;
    private NativeAdScrollView BollywoodFacebook_NativeAd_Scroll_View;
    ImageView BollywoodBanner_Native;
    boolean BollywoodDoubleTOExit = false;
    AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bollywood_Context = this;

        AdSettings.addTestDevice("65b3e391-6eee-4a27-a1c2-9cdd2fd37b8e");

        BollywoodFacebook_Manager_ = new NativeAdsManager(this, getResources().getString(R.string.FAN_Native_Ads), 5);
        BollywoodFacebook_Manager_.setListener(new NativeAdsManager.Listener() {
            @Override
            public void onAdsLoaded() {
                BollywoodFacebook_NativeAd_Scroll_View = new NativeAdScrollView(MainActivity.this, BollywoodFacebook_Manager_, 300);
                LinearLayout hscrollContainer = (LinearLayout) findViewById(R.id.hscrollContainer);
                hscrollContainer.addView(BollywoodFacebook_NativeAd_Scroll_View);
            }

            @Override
            public void onAdError(AdError adError) {

            }
        });
        BollywoodFacebook_Manager_.loadAds(NativeAd.MediaCacheFlag.ALL);


        BollywoodBanner_Native = (ImageView) findViewById(R.id.img_banner);
        BollywoodBanner_Native.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(ConstantFile.Bollwood_APplication_More));
                startActivity(intent);
            }
        });

        BollywoodLL_Privacy_Policy = (LinearLayout) findViewById(R.id.llprivacy);
        BollywoodLL_Privacy_Policy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"set your Privacy Policy",Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                intent.setData(Uri.parse(""));
//                startActivity(intent);
            }
        });

        BollywoodRate_Us_LL = (LinearLayout) findViewById(R.id.rateus);
        BollywoodRate_Us_LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("market://details?id=" + Bollywood_Context.getPackageName());
                Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                        Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                        Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                try {
                    startActivity(goToMarket);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://play.google.com/store/apps/details?id=" + Bollywood_Context.getPackageName())));
                }
            }
        });

        if(getResources().getString(R.string.enablebannerwithnativeads).toLowerCase().equals("oui")){
            findViewById(R.id.banner_container).setVisibility(View.VISIBLE);
            adView = ConstantFile.Bollwood__load_Banner_Ads((LinearLayout) findViewById(R.id.banner_container), this);
        }

        final SwipeButton swipeButton = (SwipeButton) findViewById(R.id.swipe_start);
        swipeButton.setActivated(true);
        swipeButton.setOnActiveListener(new OnActiveListener() {
            @Override
            public void onActive() {
                    MainActivity.this.startActivity(new Intent(MainActivity.this, LunchingStartActivity.class));
            }
        });
        swipeButton.setActivated(false);
        swipeButton.setOnStateChangeListener(new OnStateChangeListener() {
            @Override
            public void onStateChange(boolean active) {
                if (active) {
                    swipeButton.setButtonBackground(ContextCompat.getDrawable(MainActivity.this, (int) R.drawable.downloading_img));
                } else {
                    swipeButton.setButtonBackground(ContextCompat.getDrawable(MainActivity.this, (int) R.drawable.downloading_img));
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (BollywoodDoubleTOExit) {
            super.onBackPressed();
            return;
        }

        this.BollywoodDoubleTOExit = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                BollywoodDoubleTOExit = false;
            }
        }, 2000);
    }

    @Override
    protected void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }

}
