package wastickerwhtspppack.allactorsandactress.bollywoodwasticker.adpters;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import wastickerwhtspppack.allactorsandactress.bollywoodwasticker.others.LoaderPackages;
import wastickerwhtspppack.allactorsandactress.bollywoodwasticker.others.PackOfSickerParcelable;
import wastickerwhtspppack.allactorsandactress.bollywoodwasticker.R;
import wastickerwhtspppack.allactorsandactress.bollywoodwasticker.recyclerviews.PreviewViewHolderRecyclerView;

public class HolderPreviewAdpter extends RecyclerView.Adapter<PreviewViewHolderRecyclerView> {

    @NonNull
    private PackOfSickerParcelable wa_stickerPack;
    private final int cellSize;
    private int cellLimit;
    private int cellPadding;
    private final int errorResource;
    private final LayoutInflater layoutInflater;
    public HolderPreviewAdpter(
            @NonNull final LayoutInflater layoutInflater,
            final int errorResource,
            final int cellSize,
            final int cellPadding,
            @NonNull final PackOfSickerParcelable stickerPack) {
        this.cellSize = cellSize;
        this.cellPadding = cellPadding;
        this.cellLimit = 0;
        this.layoutInflater = layoutInflater;
        this.errorResource = errorResource;
        this.wa_stickerPack = stickerPack;
    }

    @NonNull
    @Override
    public PreviewViewHolderRecyclerView onCreateViewHolder(@NonNull final ViewGroup viewGroup, final int i) {
        View itemView = layoutInflater.inflate(R.layout.app_preview_holder, viewGroup, false);
        PreviewViewHolderRecyclerView vh = new PreviewViewHolderRecyclerView(itemView);
        ViewGroup.LayoutParams layoutParams = vh.sd_sticPreviewView.getLayoutParams();
        layoutParams.height = cellSize;
        layoutParams.width = cellSize;
        vh.sd_sticPreviewView.setLayoutParams(layoutParams);
        vh.sd_sticPreviewView.setPadding(cellPadding, cellPadding, cellPadding, cellPadding);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final PreviewViewHolderRecyclerView stickerPreviewViewHolder, final int i) {
        stickerPreviewViewHolder.sd_sticPreviewView.setImageResource(errorResource);
        stickerPreviewViewHolder.sd_sticPreviewView.setImageURI(LoaderPackages.getStickerAssetUri(wa_stickerPack.identifier, wa_stickerPack.getStickers().get(i).st_imgFileName));
    }

    @Override
    public int getItemCount() {
        int numberOfPreviewImagesInPack;
        numberOfPreviewImagesInPack = wa_stickerPack.getStickers().size();
        if (cellLimit > 0) {
            return Math.min(numberOfPreviewImagesInPack, cellLimit);
        }
        return numberOfPreviewImagesInPack;
    }
}
