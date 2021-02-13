package br.com.leogmsantos.languagesstats.model.dto;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class GITRepositoryOwnerDTO implements Parcelable {

    @SerializedName("avatar_url")
    private String avatarUrl;

    @SerializedName("url")
    private String url;

    public GITRepositoryOwnerDTO() {
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    protected GITRepositoryOwnerDTO(Parcel in) {
        avatarUrl = in.readString();
        url = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(avatarUrl);
        dest.writeString(url);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<GITRepositoryOwnerDTO> CREATOR = new Creator<GITRepositoryOwnerDTO>() {
        @Override
        public GITRepositoryOwnerDTO createFromParcel(Parcel in) {
            return new GITRepositoryOwnerDTO(in);
        }

        @Override
        public GITRepositoryOwnerDTO[] newArray(int size) {
            return new GITRepositoryOwnerDTO[size];
        }
    };
}
