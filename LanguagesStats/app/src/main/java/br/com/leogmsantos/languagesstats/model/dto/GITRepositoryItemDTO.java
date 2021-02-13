package br.com.leogmsantos.languagesstats.model.dto;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import br.com.leogmsantos.languagesstats.model.base.DTO;

public class GITRepositoryItemDTO implements DTO {

    @SerializedName("id")
    private Integer id;

    @SerializedName("name")
    private String name;

    @SerializedName("fullName")
    private String fullName;

    @SerializedName("description")
    private String description;

    @SerializedName("owner")
    private GITRepositoryOwnerDTO owner;

    @SerializedName("forks")
    private Integer forks;

    @SerializedName("watchers")
    private Integer watchers;

    public GITRepositoryItemDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public GITRepositoryOwnerDTO getOwner() {
        return owner;
    }

    public void setOwner(GITRepositoryOwnerDTO owner) {
        this.owner = owner;
    }

    public Integer getForks() {
        return forks;
    }

    public void setForks(Integer forks) {
        this.forks = forks;
    }

    public Integer getWatchers() {
        return watchers;
    }

    public void setWatchers(Integer watchers) {
        this.watchers = watchers;
    }

    protected GITRepositoryItemDTO(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        name = in.readString();
        fullName = in.readString();
        description = in.readString();
        owner = in.readParcelable(GITRepositoryOwnerDTO.class.getClassLoader());
        if (in.readByte() == 0) {
            forks = null;
        } else {
            forks = in.readInt();
        }
        if (in.readByte() == 0) {
            watchers = null;
        } else {
            watchers = in.readInt();
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(name);
        dest.writeString(fullName);
        dest.writeString(description);
        dest.writeParcelable(owner, flags);
        if (forks == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(forks);
        }
        if (watchers == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(watchers);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<GITRepositoryItemDTO> CREATOR = new Creator<GITRepositoryItemDTO>() {
        @Override
        public GITRepositoryItemDTO createFromParcel(Parcel in) {
            return new GITRepositoryItemDTO(in);
        }

        @Override
        public GITRepositoryItemDTO[] newArray(int size) {
            return new GITRepositoryItemDTO[size];
        }
    };
}
