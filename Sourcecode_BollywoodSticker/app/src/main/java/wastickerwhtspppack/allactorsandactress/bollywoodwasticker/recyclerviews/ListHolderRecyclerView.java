package wastickerwhtspppack.allactorsandactress.bollywoodwasticker.recyclerviews;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import wastickerwhtspppack.allactorsandactress.bollywoodwasticker.R;

public class ListHolderRecyclerView extends RecyclerView.ViewHolder {

    public LinearLayout AMir_khan_imageRowView;
    public TextView AMir_khan_publisherView;
    public View AMir_khan_container;
    public ImageView AMir_khan_addButton;
    public TextView AMir_khan_filesizeView;
    public TextView AMir_khan_titleView;

    public ListHolderRecyclerView(final View itemView) {
        super(itemView);
        AMir_khan_container = itemView;
        AMir_khan_titleView = itemView.findViewById(R.id.sticker_pack_title);
        AMir_khan_publisherView = itemView.findViewById(R.id.sticker_pack_publisher);
        AMir_khan_filesizeView = itemView.findViewById(R.id.sticker_pack_filesize);
        AMir_khan_addButton = itemView.findViewById(R.id.add_button_on_list);
        AMir_khan_imageRowView = itemView.findViewById(R.id.sticker_packs_list_item_image_list);
    }
}