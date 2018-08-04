package reviewapp.test.bence.reviewapp.application;


import android.app.Application;

import reviewapp.test.bence.reviewapp.dagger.DaggerMainComponent;
import reviewapp.test.bence.reviewapp.dagger.InjectHelper;
import reviewapp.test.bence.reviewapp.dagger.MainModule;


public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        InjectHelper.setComponent(DaggerMainComponent.builder().mainModule(new MainModule(this)).build());
    }

}
