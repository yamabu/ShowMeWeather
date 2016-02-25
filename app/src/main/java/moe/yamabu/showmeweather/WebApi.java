package moe.yamabu.showmeweather;

import moe.yamabu.showmeweather.bean.Weather;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface WebApi {
    @GET("/weather/uni")
    Observable<Weather> getWeather(@Query("key") String key);
}
