package wastickerwhtspppack.allactorsandactress.bollywoodwasticker.others;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class StickerParcelable implements Parcelable {

    public String st_imgFileName;
    public List<String> emojis;
    public long size;

    public StickerParcelable(String imageFileName, List<String> emojis) {
        this.st_imgFileName = imageFileName;
        this.emojis = emojis;
    }

    protected StickerParcelable(Parcel in) {
        st_imgFileName = in.readString();
        emojis = in.createStringArrayList();
        size = in.readLong();
    }

    public static final Creator<StickerParcelable> CREATOR = new Creator<StickerParcelable>() {
        @Override
        public StickerParcelable createFromParcel(Parcel in) {
            return new StickerParcelable(in);
        }

        @Override
        public StickerParcelable[] newArray(int size) {
            return new StickerParcelable[size];
        }
    };

    public void setSize(long size) {
        this.size = size;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(st_imgFileName);
        dest.writeStringList(emojis);
        dest.writeLong(size);
    }
}
