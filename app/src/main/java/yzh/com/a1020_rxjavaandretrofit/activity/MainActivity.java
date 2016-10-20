package yzh.com.a1020_rxjavaandretrofit.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.webkit.URLUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import yzh.com.a1020_rxjavaandretrofit.Constant;
import yzh.com.a1020_rxjavaandretrofit.R;
import yzh.com.a1020_rxjavaandretrofit.adapter.RecyclerViewAdapter;
import yzh.com.a1020_rxjavaandretrofit.info.DouyuBean;
import yzh.com.a1020_rxjavaandretrofit.info.RoomBean;
import yzh.com.a1020_rxjavaandretrofit.util.HttpUtils;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private List<RoomBean> list;
    private Subscription subscription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //使用RxJava与Retrofit结合返回的Call对象
        //initRxJavaForCall();
        //使用RxJava与Retrofit结合返回的Observable对象
        Log.e("Tag","sssssss");
        initView();
        initRxJavaForObservable();





    }

    private void initView() {
        recyclerView= (RecyclerView) findViewById(R.id.recycler);
        list = new ArrayList<>();
        adapter = new RecyclerViewAdapter(MainActivity.this,list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,2));

    }

    //使用RxJava与Retrofit结合返回的Observable对象
    private void initRxJavaForObservable() {
        Map<String, String> map = new HashMap<>();
        map.put(Constant.Params.LIMIT, "20");
        map.put(Constant.Params.OFFSET, "0");
        map.put(Constant.Params.TIME, Constant.DefaultValue.TIME);
        subscription = HttpUtils.newInstance().getFace(map)
                .subscribeOn(Schedulers.io())       //让被观察者在io线程中运行
                .observeOn(AndroidSchedulers.mainThread())//让观察者在主线程中运行
                .subscribe(new Subscriber<DouyuBean>() { //回调方发
                    @Override
                    public void onCompleted() {}
                    @Override
                    public void onError(Throwable e) {}
                    @Override
                    public void onNext(DouyuBean douyuBean) {
                        List<RoomBean> data = douyuBean.getData();
                        list.addAll(data);
                       /* adapter = new RecyclerViewAdapter(MainActivity.this,list);
                        recyclerView.setAdapter(adapter);
                        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,2));*/
                        adapter.notifyDataSetChanged();
                        Log.e("Tag",list.size()+"");
                    }
                });
    }


    //使用RxJava与Retrofit结合返回的Call对象
    private void initRxJavaForCall() {
        //先创建被观察者Observable.create(new Observable.OnSubscribe<Object>()){})
        //绑定观察者.subscribe(new Subscriber<Object>){})
        //返回值是一个Subscription对象，用于关闭RxJava防止内存泄漏

        subscription = Observable.create(new Observable.OnSubscribe<DouyuBean>() {
            @Override
            public void call(Subscriber<? super DouyuBean> subscriber) {
                //被观察者的方法，可以进行耗时的操作

            }
        }).subscribeOn(Schedulers.io())//让被观察者在io线程中运行
                .observeOn(AndroidSchedulers.mainThread())//让观察者在主线程中运行
                .subscribe(new Subscriber<DouyuBean>() {
                    @Override
                    public void onCompleted() {}

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onNext(DouyuBean douyuBean) {
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        subscription.unsubscribe();
    }
}
