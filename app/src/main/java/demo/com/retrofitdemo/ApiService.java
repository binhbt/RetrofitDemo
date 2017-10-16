package demo.com.retrofitdemo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by leobui on 10/16/2017.
 */

public interface ApiService {
    public static final String BASE_URL ="https://drive.google.com/";
    @GET("uc")
    Call<List<Student>> listStudent(@Query("export") String export, @Query("id") String id);
}
