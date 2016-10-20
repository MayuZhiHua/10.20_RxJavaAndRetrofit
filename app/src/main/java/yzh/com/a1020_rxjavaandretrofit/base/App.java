package yzh.com.a1020_rxjavaandretrofit.base;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.litesuits.orm.LiteOrm;

/**
 *
 * Created by HP on 2016/10/20.
 */

public class App extends Application{
    //实例化数据库
    public static LiteOrm liteOrm;

    @Override
    public void onCreate() {
        //创建数据库
        liteOrm = LiteOrm.newSingleInstance(this, "douyu.db");
        //初始化Fresco框架
        Fresco.initialize(this);
    }
}
