package wastickerwhtspppack.allactorsandactress.bollywoodwasticker.activitys;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;

import com.facebook.ads.AdSettings;
import com.facebook.ads.AdView;
import com.facebook.ads.InterstitialAd;

import wastickerwhtspppack.allactorsandactress.bollywoodwasticker.others.ListChecking;
import wastickerwhtspppack.allactorsandactress.bollywoodwasticker.others.PackOfSickerParcelable;
import wastickerwhtspppack.allactorsandactress.bollywoodwasticker.R;
import wastickerwhtspppack.allactorsandactress.bollywoodwasticker.ConstantFile;
import wastickerwhtspppack.allactorsandactress.bollywoodwasticker.adpters.ListPackageAdapter;
import wastickerwhtspppack.allactorsandactress.bollywoodwasticker.recyclerviews.ListHolderRecyclerView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AppListofPackGridViewActivity extends AddingStickerActivity {

    public static final String EXTRA_STICKER_PACK_LIST_DATA = "sticker_pack_list";
    private static final int STICKER_PREVIEW_DISPLAY_LIMIT = 5;
    private LinearLayoutManager ll_packLayoutManager;
    private RecyclerView rv_packRecyclerView;
    private ListPackageAdapter allStickerPacksListAdapter;
    private WhiteListCheckAsyncTask whiteListCheckAsyncTask;
    private ArrayList<PackOfSickerParcelable> ar_stickerPackList;
    private AdView adView;
    public InterstitialAd kInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listlist_gridview);

        Log.e("FAN" , "WE ARE HERE??");

        AdSettings.addTestDevice("65b3e391-6eee-4a27-a1c2-9cdd2fd37b8e");

        adView = ConstantFile.Bollwood__load_Banner_Ads((LinearLayout) findViewById(R.id.banner_container), this);

        rv_packRecyclerView = findViewById(R.id.sticker_pack_list);
        ar_stickerPackList = getIntent().getParcelableArrayListExtra(EXTRA_STICKER_PACK_LIST_DATA);
        showStickerPackList(ar_stickerPackList);

        kInterstitialAd = ConstantFile.Bollywood_load_FaceBook_InterstitialAds(AppListofPackGridViewActivity.this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        whiteListCheckAsyncTask = new WhiteListCheckAsyncTask(this);
        whiteListCheckAsyncTask.execute(ar_stickerPackList.toArray(new PackOfSickerParcelable[ar_stickerPackList.size()]));
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (whiteListCheckAsyncTask != null && !whiteListCheckAsyncTask.isCancelled()) {
            whiteListCheckAsyncTask.cancel(true);
        }
    }

    private void showStickerPackList(List<PackOfSickerParcelable> stickerPackList) {
        allStickerPacksListAdapter = new ListPackageAdapter(stickerPackList, onAddButtonClickedListener , this);
        rv_packRecyclerView.setAdapter(allStickerPacksListAdapter);
        ll_packLayoutManager = new LinearLayoutManager(this);
        ll_packLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
                rv_packRecyclerView.getContext(),
                ll_packLayoutManager.getOrientation()
        );
        rv_packRecyclerView.addItemDecoration(dividerItemDecoration);
        rv_packRecyclerView.setLayoutManager(ll_packLayoutManager);
        rv_packRecyclerView.getViewTreeObserver().addOnGlobalLayoutListener(this::recalculateColumnCount);
    }


    private final ListPackageAdapter.OnAddButtonClickedListener onAddButtonClickedListener = pack -> {
        addStickerPackToWhatsApp(pack.identifier, pack.name);
    };

    private void recalculateColumnCount() {
        final int previewSize = getResources().getDimensionPixelSize(R.dimen.sticker_pack_list_item_preview_image_size);
        int firstVisibleItemPosition = ll_packLayoutManager.findFirstVisibleItemPosition();
        ListHolderRecyclerView viewHolder = (ListHolderRecyclerView) rv_packRecyclerView.findViewHolderForAdapterPosition(firstVisibleItemPosition);
        if (viewHolder != null) {
            final int max = Math.max(viewHolder.AMir_khan_imageRowView.getMeasuredWidth() / previewSize, 1);
            int numColumns = Math.min(STICKER_PREVIEW_DISPLAY_LIMIT, max);
            allStickerPacksListAdapter.setMaxNumberOfStickersInARow(numColumns);
        }
    }


    static class WhiteListCheckAsyncTask extends AsyncTask<PackOfSickerParcelable, Void, List<PackOfSickerParcelable>> {
        private final WeakReference<AppListofPackGridViewActivity> stickerPackListActivityWeakReference;

        WhiteListCheckAsyncTask(AppListofPackGridViewActivity stickerPackListActivity) {
            this.stickerPackListActivityWeakReference = new WeakReference<>(stickerPackListActivity);
        }

        @Override
        protected final List<PackOfSickerParcelable> doInBackground(PackOfSickerParcelable... stickerPackArray) {
            final AppListofPackGridViewActivity stickerPackListActivity = stickerPackListActivityWeakReference.get();
            if (stickerPackListActivity == null) {
                return Arrays.asList(stickerPackArray);
            }
            for (PackOfSickerParcelable stickerPack : stickerPackArray) {
                stickerPack.setIsWhitelisted(ListChecking.isWhitelisted(stickerPackListActivity, stickerPack.identifier));
            }
            return Arrays.asList(stickerPackArray);
        }

        @Override
        protected void onPostExecute(List<PackOfSickerParcelable> stickerPackList) {
            final AppListofPackGridViewActivity stickerPackListActivity = stickerPackListActivityWeakReference.get();
            if (stickerPackListActivity != null) {
                stickerPackListActivity.allStickerPacksListAdapter.setStickerPackList(stickerPackList);
                stickerPackListActivity.allStickerPacksListAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    protected void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }
}
