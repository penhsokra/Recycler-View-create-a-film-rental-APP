package filmrental.sokra.com.filmrental.modal;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.DrawableRes;

public class Film implements Parcelable {
    private String title;
    private String description;
    private String viewCount;
    private @DrawableRes int btnOption;
    private @DrawableRes int filmImage;

    public Film(String title, String description, String viewCount,@DrawableRes int filmImage, @DrawableRes int btnOption) {
        this.title = title;
        this.description = description;
        this.viewCount = viewCount;
        this.filmImage = filmImage;
        this.btnOption = btnOption;

    }

    protected Film(Parcel in) {
        title = in.readString();
        description = in.readString();
        viewCount = in.readString();
        btnOption = in.readInt();
        filmImage = in.readInt();
    }

    public static final Creator<Film> CREATOR = new Creator<Film>() {
        @Override
        public Film createFromParcel(Parcel in) {
            return new Film(in);
        }

        @Override
        public Film[] newArray(int size) {
            return new Film[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getViewCount() {
        return viewCount;
    }

    public void setViewCount(String viewCount) {
        this.viewCount = viewCount;
    }

    public int getBtnOption() {
        return btnOption;
    }

    public void setBtnOption(int btnOption) {
        this.btnOption = btnOption;
    }

    public int getFilmImage() {
        return filmImage;
    }

    public void setFilmImage(int filmImage) {
        this.filmImage = filmImage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(viewCount);
        dest.writeInt(btnOption);
        dest.writeInt(filmImage);
    }
}
