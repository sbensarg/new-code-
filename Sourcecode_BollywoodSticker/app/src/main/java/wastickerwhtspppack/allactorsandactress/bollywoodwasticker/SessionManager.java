package wastickerwhtspppack.allactorsandactress.bollywoodwasticker;

import android.content.Context;
import android.content.SharedPreferences;


public class SessionManager {
    // Shared Preferences
    SharedPreferences mPref;
    // Editor for Shared preferences
    SharedPreferences.Editor mEditor;
    // Context
    Context mContext;
    // Shared pref mode
    int PRIVATE_MODE = 0;
    // Shared Preferences file name
//    private static final String PREF_NAME = "insta";
//    private static final String ad_network = "ad_network";
//    private static final String inter_fb = "inter_fb";
//    private static final String native_banner_fb = "native_banner_fb";
//    private static final String native_ad_fb = "native_ad_fb";

    private static final String FAN_BAnner_ADS = "FAN_BAnner_ADS";
    private static final String enable_banner = "true";
    private static final String FAN_Intertitial_ADS = "FAN_Intertitial_ADS";
    private static final String enable_inter = "true";
    private static final String FAN_Native_Ads = "FAN_Native_Ads";
    private static final String enable_native = "true";



    private static final String CLICK_COUNT = "click";

    public static final String AD_NETWORK_KIND_ADMOB = "admob";


    // Constructor
    public SessionManager(Context context) {
        this.mContext = context;
        mPref = mContext.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        mEditor = mPref.edit();
    }


    public void setClickcount(int clickcount) {
        mEditor.putInt(CLICK_COUNT, clickcount);
        mEditor.commit();
    }

    public int getClickCount() {
        return mPref.getInt(CLICK_COUNT, 0);
    }


        public void setenable_banner(String network) {
        mEditor.putString(enable_banner, network);
        mEditor.commit();
    }

    public String getenable_banner() {
        return mPref.getString(enable_banner, "");
    }

    public void setenable_inter(String network2) {
        mEditor.putString(enable_inter, network2);
        mEditor.commit();
    }

    public String getenable_inter() {
        return mPref.getString(enable_inter, "");
    }

    public void setenable_native(String network3) {
        mEditor.putString(enable_native, network3);
        mEditor.commit();
    }

    public String getenable_native() {
        return mPref.getString(enable_native, "");
    }



    public void setFAN_Intertitial_ADS(String interfb) {
        mEditor.putString(FAN_Intertitial_ADS, interfb);
        mEditor.commit();
    }

    public String getFAN_Intertitial_ADS() {
        return mPref.getString(FAN_Intertitial_ADS, "");
    }


    public void setFAN_Native_Ads(String nbf) {
        mEditor.putString(FAN_Native_Ads, nbf);
        mEditor.commit();
    }

    public String getFAN_Native_Ads() {
        return mPref.getString(FAN_Native_Ads, "");
    }


    public void setFAN_BAnner_ADS(String nafb) {
        mEditor.putString(FAN_BAnner_ADS, nafb);
        mEditor.commit();
    }

    public String getFAN_BAnner_ADS() {
        return mPref.getString(FAN_BAnner_ADS, "");
    }


}
