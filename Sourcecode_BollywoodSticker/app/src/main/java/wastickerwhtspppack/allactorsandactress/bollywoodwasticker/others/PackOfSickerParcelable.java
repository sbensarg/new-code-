package wastickerwhtspppack.allactorsandactress.bollywoodwasticker.others;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class PackOfSickerParcelable implements Parcelable {

   public String identifier;
    public String name;
    public String publisher;
    public String trayImageFile;
    public  final String publisherEmail;
    public  final String publisherWebsite;
    public   final String privacyPolicyWebsite;
    public    final String licenseAgreementWebsite;

    public String iosAppStoreLink;
    private List<StickerParcelable> stickers;
    private long totalSize;
    public  String androidPlayStoreLink;
    private boolean isWhitelisted;

    PackOfSickerParcelable(String identifier, String name, String publisher, String trayImageFile, String publisherEmail, String publisherWebsite, String privacyPolicyWebsite, String licenseAgreementWebsite) {
        this.identifier = identifier;
        this.name = name;
        this.publisher = publisher;
        this.trayImageFile = trayImageFile;
        this.publisherEmail = publisherEmail;
        this.publisherWebsite = publisherWebsite;
        this.privacyPolicyWebsite = privacyPolicyWebsite;
        this.licenseAgreementWebsite = licenseAgreementWebsite;
    }

    public void setIsWhitelisted(boolean isWhitelisted) {
        this.isWhitelisted = isWhitelisted;
    }

    public boolean getIsWhitelisted() {
        return isWhitelisted;
    }

    protected PackOfSickerParcelable(Parcel in) {
        identifier = in.readString();
        name = in.readString();
        publisher = in.readString();
        trayImageFile = in.readString();
        publisherEmail = in.readString();
        publisherWebsite = in.readString();
        privacyPolicyWebsite = in.readString();
        licenseAgreementWebsite = in.readString();
        iosAppStoreLink = in.readString();
        stickers = in.createTypedArrayList(StickerParcelable.CREATOR);
        totalSize = in.readLong();
        androidPlayStoreLink = in.readString();
        isWhitelisted = in.readByte() != 0;
    }

    public static final Creator<PackOfSickerParcelable> CREATOR = new Creator<PackOfSickerParcelable>() {
        @Override
        public PackOfSickerParcelable createFromParcel(Parcel in) {
            return new PackOfSickerParcelable(in);
        }

        @Override
        public PackOfSickerParcelable[] newArray(int size) {
            return new PackOfSickerParcelable[size];
        }
    };

    void setStickers(List<StickerParcelable> stickers) {
        this.stickers = stickers;
        totalSize = 0;
        for (StickerParcelable sticker : stickers) {
            totalSize += sticker.size;
        }
    }

    public void setAndroidPlayStoreLink(String androidPlayStoreLink) {
        this.androidPlayStoreLink = androidPlayStoreLink;
    }

    public void setIosAppStoreLink(String iosAppStoreLink) {
        this.iosAppStoreLink = iosAppStoreLink;
    }

    public List<StickerParcelable> getStickers() {
        return stickers;
    }

    public long getTotalSize() {
        return totalSize;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(identifier);
        dest.writeString(name);
        dest.writeString(publisher);
        dest.writeString(trayImageFile);
        dest.writeString(publisherEmail);
        dest.writeString(publisherWebsite);
        dest.writeString(privacyPolicyWebsite);
        dest.writeString(licenseAgreementWebsite);
        dest.writeString(iosAppStoreLink);
        dest.writeTypedList(stickers);
        dest.writeLong(totalSize);
        dest.writeString(androidPlayStoreLink);
        dest.writeByte((byte) (isWhitelisted ? 1 : 0));
    }
}
