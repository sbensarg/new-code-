package wastickerwhtspppack.allactorsandactress.bollywoodwasticker.recyclerviews;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;

import wastickerwhtspppack.allactorsandactress.bollywoodwasticker.R;

public class PreviewViewHolderRecyclerView extends RecyclerView.ViewHolder {

    public SimpleDraweeView sd_sticPreviewView;

    public PreviewViewHolderRecyclerView(final View itemView) {
        super(itemView);
        sd_sticPreviewView = itemView.findViewById(R.id.sticker_preview);
    }
}