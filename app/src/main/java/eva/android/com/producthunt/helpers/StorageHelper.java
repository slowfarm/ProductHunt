package eva.android.com.producthunt.helpers;

import eva.android.com.producthunt.interfaces.DataBaseChangeListener;
import io.realm.Realm;

public class StorageHelper {

    private static volatile StorageHelper instance;
    private Realm mRealm;
    private DataBaseChangeListener mDataBaseChangeListener;

    public void setDataBaseChangeListener(DataBaseChangeListener dataBaseChangeListener) {
        mDataBaseChangeListener = dataBaseChangeListener;
        if (dataBaseChangeListener != null) {
            mRealm = Realm.getDefaultInstance();
            mRealm.addChangeListener(realm1 -> mDataBaseChangeListener.onChange());
        } else {
            mRealm.close();
        }
    }

    public static StorageHelper getInstance() {
        if (instance == null)
            instance = new StorageHelper();
        return instance;
    }


    public void closeRealm() {
        mRealm.close();
    }
}
