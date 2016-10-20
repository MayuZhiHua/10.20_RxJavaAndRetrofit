package yzh.com.a1020_rxjavaandretrofit.util;

import android.util.Log;

import java.util.Map;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import yzh.com.a1020_rxjavaandretrofit.Constant;
import yzh.com.a1020_rxjavaandretrofit.info.DouyuBean;
import yzh.com.a1020_rxjavaandretrofit.listener.RetrofitApi;

/**
 * 使用Retrofit和RxJava进行网络连接的工具类
 * Created by HP on 2016/10/20.
 */

public class HttpUtils {

    private Retrofit retrofit;
    private RetrofitApi retrofitApi;

    //单开模式
    //1.私有化构造方法
    //2.私有化静态对象
    //3.公有的静态方法判断对象是否存在
    private static HttpUtils httpUtils;
    private HttpUtils(){

        //使用Retrofit访问网络的步骤
        //1.实例化Retrofit对象
        //2.实例化接口
        //3.使用接口调用方法
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())//使接口中的返回中范型为DouyuBean类型
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//使接口中的Call返回值变为RxJava的被观察者类型
                .baseUrl(Constant.BASE_URL)
                .build();
        retrofitApi = retrofit.create(RetrofitApi.class);
    }
    public synchronized static HttpUtils newInstance(){
        if (httpUtils == null){
            httpUtils = new HttpUtils();
        }
        return  httpUtils;
    }

    public Observable<DouyuBean> getFace(Map<String,String> map){
        return retrofitApi.getDouyuData(map);
    }

}
