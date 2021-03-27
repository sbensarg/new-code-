package wastickerwhtspppack.allactorsandactress.bollywoodwasticker.adpters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.text.format.Formatter;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.facebook.drawee.view.SimpleDraweeView;

import wastickerwhtspppack.allactorsandactress.bollywoodwasticker.ConstantFile;
import wastickerwhtspppack.allactorsandactress.bollywoodwasticker.activitys.AppListofPackGridViewActivity;
import wastickerwhtspppack.allactorsandactress.bollywoodwasticker.others.LoaderPackages;
import wastickerwhtspppack.allactorsandactress.bollywoodwasticker.activitys.PackDetailsActivity;
import wastickerwhtspppack.allactorsandactress.bollywoodwasticker.others.PackOfSickerParcelable;
import wastickerwhtspppack.allactorsandactress.bollywoodwasticker.R;
import wastickerwhtspppack.allactorsandactress.bollywoodwasticker.recyclerviews.ListHolderRecyclerView;

import java.util.List;

public class ListPackageAdapter extends RecyclerView.Adapter<ListHolderRecyclerView> {

    @NonNull
    private List<PackOfSickerParcelable> stickerPacks;
    private Activity kActivity;

    @NonNull
    private final OnAddButtonClickedListener onAddButtonClickedListener;

    private int maxNumberOfStickersInARow;

    public ListPackageAdapter(@NonNull List<PackOfSickerParcelable> stickerPacks, @NonNull OnAddButtonClickedListener onAddButtonClickedListener , Activity act) {
        this.stickerPacks = stickerPacks;
        this.onAddButtonClickedListener = onAddButtonClickedListener;
        this.kActivity = act;
    }

    @NonNull
    @Override
    public ListHolderRecyclerView onCreateViewHolder(@NonNull final ViewGroup viewGroup, final int i) {
        final Context context = viewGroup.getContext();
        final LayoutInflater layoutInflater = LayoutInflater.from(context);
        final View stickerPackRow = layoutInflater.inflate(R.layout.list_wasticker_item, viewGroup, false);
        return new ListHolderRecyclerView(stickerPackRow);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListHolderRecyclerView viewHolder, final int index) {
        PackOfSickerParcelable pack = stickerPacks.get(index);
        final Context context = viewHolder.AMir_khan_publisherView.getContext();
        viewHolder.AMir_khan_publisherView.setText(pack.publisher);
        viewHolder.AMir_khan_filesizeView.setText(Formatter.formatShortFileSize(context, pack.getTotalSize()));
        viewHolder.AMir_khan_titleView.setText(pack.name);
        viewHolder.AMir_khan_container.setOnClickListener(view -> {

            Intent intent = new Intent(view.getContext(), PackDetailsActivity.class);
            intent.putExtra(PackDetailsActivity.EXTRA_SHOW_UP_BUTTON, true);
            intent.putExtra(PackDetailsActivity.EXTRA_STICKER_PACK_DATA, pack);

             ConstantFile.facebook_show_Interstitial(view.getContext(), intent , ((AppListofPackGridViewActivity)this.kActivity).kInterstitialAd);

        });
        viewHolder.AMir_khan_imageRowView.removeAllViews();
        int actualNumberOfStickersToShow = Math.min(maxNumberOfStickersInARow, pack.getStickers().size());
        for (int i = 0; i < actualNumberOfStickersToShow; i++) {
            final SimpleDraweeView rowImage = (SimpleDraweeView) LayoutInflater.from(context).inflate(R.layout.listitem_viewholder, viewHolder.AMir_khan_imageRowView, false);
            rowImage.setImageURI(LoaderPackages.getStickerAssetUri(pack.identifier, pack.getStickers().get(i).st_imgFileName));
            final LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) rowImage.getLayoutParams();
            final int marginBetweenImages = (viewHolder.AMir_khan_imageRowView.getMeasuredWidth() - maxNumberOfStickersInARow * viewHolder.AMir_khan_imageRowView.getContext().getResources().getDimensionPixelSize(R.dimen.sticker_pack_list_item_preview_image_size)) / (maxNumberOfStickersInARow - 1) - lp.leftMargin - lp.rightMargin;
            if (i != actualNumberOfStickersToShow - 1 && marginBetweenImages > 0) { //do not set the margin for the last image
                lp.setMargins(lp.leftMargin, lp.topMargin, lp.rightMargin + marginBetweenImages, lp.bottomMargin);
                rowImage.setLayoutParams(lp);
            }
            viewHolder.AMir_khan_imageRowView.addView(rowImage);
        }
        setAddButtonAppearance(viewHolder.AMir_khan_addButton, pack);
    }

    private void setAddButtonAppearance(ImageView addButton, PackOfSickerParcelable pack) {
        if (pack.getIsWhitelisted()) {
            addButton.setImageResource(R.drawable.right_click_icon);
            addButton.setClickable(false);
            addButton.setOnClickListener(null);
            setBackground(addButton, null);
        } else {
            addButton.setImageResource(R.drawable.wasticker_adding);
            addButton.setOnClickListener(v -> onAddButtonClickedListener.onAddButtonClicked(pack));
            TypedValue outValue = new TypedValue();
            addButton.getContext().getTheme().resolveAttribute(android.R.attr.selectableItemBackground, outValue, true);
            addButton.setBackgroundResource(outValue.resourceId);
        }
    }

    private void setBackground(View view, Drawable background) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.setBackground(background);
        } else {
            view.setBackgroundDrawable(background);
        }
    }

    @Override
    public int getItemCount() {
        return stickerPacks.size();
    }

    public void setMaxNumberOfStickersInARow(int maxNumberOfStickersInARow) {
        if (this.maxNumberOfStickersInARow != maxNumberOfStickersInARow) {
            this.maxNumberOfStickersInARow = maxNumberOfStickersInARow;
            notifyDataSetChanged();
        }
    }

    public void setStickerPackList(List<PackOfSickerParcelable> stickerPackList) {
        this.stickerPacks = stickerPackList;
    }

    public interface OnAddButtonClickedListener {
        void onAddButtonClicked(PackOfSickerParcelable stickerPack);
    }
}
