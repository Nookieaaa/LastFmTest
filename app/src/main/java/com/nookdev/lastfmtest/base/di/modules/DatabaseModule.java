package com.nookdev.lastfmtest.base.di.modules;


import android.content.Context;

import com.nookdev.lastfmtest.base.di.qualifiers.DataBaseNameQualifier;
import com.nookdev.lastfmtest.base.di.scopes.ApplicationScope;
import com.nookdev.lastfmtest.models.DaoMaster;
import com.nookdev.lastfmtest.models.DaoSession;
import com.nookdev.lastfmtest.util.Constants;

import org.greenrobot.greendao.database.Database;

import dagger.Module;
import dagger.Provides;

@ApplicationScope
@Module(includes = ContextModule.class)
public class DatabaseModule {

    @Provides
    @ApplicationScope
    public DaoMaster.DevOpenHelper provideDevOpenHelper(Context context, @DataBaseNameQualifier String dbName) {
        return new DaoMaster.DevOpenHelper(context, dbName);
    }

    @Provides
    @ApplicationScope
    public Database provideDatabase(DaoMaster.DevOpenHelper devOpenHelper) {
        return devOpenHelper.getWritableDb();
    }

    @Provides
    @ApplicationScope
    public DaoSession provideDaoSession(Database database) {
        return new DaoMaster(database).newSession();
    }

    @Provides
    @ApplicationScope
    @DataBaseNameQualifier
    public String provideDbName() {
        return Constants.DATABASE_NAME;
    }
}
