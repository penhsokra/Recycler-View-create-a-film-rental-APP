package filmrental.sokra.com.filmrental.modal;

import android.support.annotation.DrawableRes;

public class Film {
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
}
