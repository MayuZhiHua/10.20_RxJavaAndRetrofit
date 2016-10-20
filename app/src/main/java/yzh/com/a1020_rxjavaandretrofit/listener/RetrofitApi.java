package yzh.com.a1020_rxjavaandretrofit.listener;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;
import yzh.com.a1020_rxjavaandretrofit.Constant;
import yzh.com.a1020_rxjavaandretrofit.info.DouyuBean;

/**
 * Retrofit的自定义接口
 * Created by HP on 2016/10/20.
 */

public interface RetrofitApi {

    //自定义接口使用RxJava进行网络连接并进行Gson解析，返回被观察者
    @GET(Constant.Path.ULR_HOT)
    Observable<DouyuBean> getDouyuData(@QueryMap() Map<String,String> map);
}
