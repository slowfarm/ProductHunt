package eva.android.com.producthunt.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class ScreenshotUrl extends RealmObject {

    @SerializedName("300px")
    @Expose
    private String _300px;
    @SerializedName("850px")
    @Expose
    private String _850px;

    public String get300px() {
        return _300px;
    }

    public void set300px(String _300px) {
        this._300px = _300px;
    }

    public String get850px() {
        return _850px;
    }

    public void set850px(String _850px) {
        this._850px = _850px;
    }

}