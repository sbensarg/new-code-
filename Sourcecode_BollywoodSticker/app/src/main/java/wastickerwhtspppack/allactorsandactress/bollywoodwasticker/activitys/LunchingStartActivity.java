package wastickerwhtspppack.allactorsandactress.bollywoodwasticker.activitys;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;

import wastickerwhtspppack.allactorsandactress.bollywoodwasticker.R;
import wastickerwhtspppack.allactorsandactress.bollywoodwasticker.others.CheckListOther;
import wastickerwhtspppack.allactorsandactress.bollywoodwasticker.others.PackOfSickerParcelable;
import wastickerwhtspppack.allactorsandactress.bollywoodwasticker.others.LoaderPackages;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class LunchingStartActivity extends BaseStickerActivity {

    static class Lunching__LoadList_AsyncTask extends AsyncTask<Void, Void, Pair<String, ArrayList<PackOfSickerParcelable>>> {
        private final WeakReference<LunchingStartActivity> contextWeakReference;

        Lunching__LoadList_AsyncTask(LunchingStartActivity activity) {
            this.contextWeakReference = new WeakReference<>(activity);
        }

        @SuppressLint("LongLogTag")
        @Override
        protected Pair<String, ArrayList<PackOfSickerParcelable>> doInBackground(Void... voids) {
            ArrayList<PackOfSickerParcelable> stickerPackList;
            try {
                final Context context = contextWeakReference.get();
                if (context != null) {
                    stickerPackList = LoaderPackages.fetchStickerPacks(context);
                    if (stickerPackList.size() == 0) {
                        return new Pair<>("could not find any packs", null);
                    }
                    for (PackOfSickerParcelable stickerPack : stickerPackList) {
                        CheckListOther.verifyStickerPackValidity(context, stickerPack);
                    }
                    return new Pair<>(null, stickerPackList);
                } else {
                    return new Pair<>("could not fetch sticker packs", null);
                }
            } catch (Exception e) {
                Log.e("LunchingStartActivity", "error fetching sticker packs", e);
                return new Pair<>(e.getMessage(), null);
            }
        }

        @Override
        protected void onPostExecute(Pair<String, ArrayList<PackOfSickerParcelable>> stringListPair) {

            final LunchingStartActivity entryActivity = contextWeakReference.get();
            if (entryActivity != null) {
                if (stringListPair.first != null) {
                    entryActivity.showErrorMessage(stringListPair.first);
                } else {
                    entryActivity.showStickerPack(stringListPair.second);
                }
            }
        }
    }

    private Lunching__LoadList_AsyncTask Lunching_loadList_AsyncTask;
    private View Lunching_View_Progress_Bar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_string_luching);

        overridePendingTransition(0, 0);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        Lunching_View_Progress_Bar = findViewById(R.id.entry_activity_progress);
        Lunching_loadList_AsyncTask = new Lunching__LoadList_AsyncTask(this);
        Lunching_loadList_AsyncTask.execute();
    }

    private void showStickerPack(ArrayList<PackOfSickerParcelable> stickerPackList) {
        Lunching_View_Progress_Bar.setVisibility(View.GONE);
        if (stickerPackList.size() > 1) {
            final Intent intent = new Intent(this, AppListofPackGridViewActivity.class);
            intent.putParcelableArrayListExtra(AppListofPackGridViewActivity.EXTRA_STICKER_PACK_LIST_DATA, stickerPackList);
            startActivity(intent);
            finish();
            overridePendingTransition(0, 0);
        } else {
            final Intent intent = new Intent(this, PackDetailsActivity.class);
            intent.putExtra(PackDetailsActivity.EXTRA_SHOW_UP_BUTTON, false);
            intent.putExtra(PackDetailsActivity.EXTRA_STICKER_PACK_DATA, stickerPackList.get(0));
            startActivity(intent);
            finish();
            overridePendingTransition(0, 0);
        }
    }

    @SuppressLint("LongLogTag")
    private void showErrorMessage(String errorMessage) {
        Lunching_View_Progress_Bar.setVisibility(View.GONE);
        Log.e("LunchingStartActivity", "error fetching sticker packs, " + errorMessage);
        final TextView errorMessageTV = findViewById(R.id.error_message);
        errorMessageTV.setText(getString(R.string.Bollywood_error_message, errorMessage));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (Lunching_loadList_AsyncTask != null && !Lunching_loadList_AsyncTask.isCancelled()) {
            Lunching_loadList_AsyncTask.cancel(true);
        }
    }
}
