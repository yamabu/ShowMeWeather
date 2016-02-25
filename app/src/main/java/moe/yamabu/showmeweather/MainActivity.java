package moe.yamabu.showmeweather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import butterknife.Bind;
import butterknife.ButterKnife;
import moe.yamabu.showmeweather.adapter.MyRecyclerAdapter;
import moe.yamabu.showmeweather.bean.Weather;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.rv)
    RecyclerView mRecyclerView;
    public static final String URL = "http://v.juhe.cn";
    public static final String KEY = "2b2101d9a365b42debb50d5879e409ce";
    private Retrofit mRetrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        MyRecyclerAdapter myRecyclerAdapter = new MyRecyclerAdapter(this);
        mRecyclerView.setAdapter(myRecyclerAdapter);
        myRecyclerAdapter.setOnItemClickListener(new MyRecyclerAdapter
                .OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, String data) {
                doNet(view, Integer.parseInt(data));
            }
        });

        mRetrofit = new Retrofit.Builder().baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    public void doNet(final View v, final int position) {
        WebApi webApi = mRetrofit.create(WebApi.class);
        webApi.getWeather(KEY)
                .map(new Func1<Weather, String>() {
                    @Override
                    public String call(Weather weather) {
                        return weather.result.get(position).weather;
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        TextView textView = (TextView) v.findViewById(R.id.tv);
                        textView.setText(s);
                    }
                });
    }

}
