package demo.com.retrofitdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    //https://drive.google.com/uc?export=download&id=0B3WHkXVKw10ddnV1bVd4Rk5adUE
    public static final String FILE_ID = "0B3WHkXVKw10ddnV1bVd4Rk5adUE";
    private TextView txtContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtContent = (TextView)findViewById(R.id.txt_content);
        findViewById(R.id.btn_request).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doRequest();
            }
        });
    }
    private void doRequest(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService service = retrofit.create(ApiService.class);
        Call<List<Student>> call= service.listStudent("download",FILE_ID);
        call.enqueue(new Callback<List<Student>>() {
            @Override
            public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
                Log.e("asdsa",response.message());
                disPlayStudent(response.body());
            }

            @Override
            public void onFailure(Call<List<Student>> call, Throwable t) {
                t.printStackTrace();
            }

        });
    }
    private void disPlayStudent(List<Student> studentList){
        for (Student student:studentList
             ) {
            txtContent.append(student.getFirstname()+"--"+student.getLastname()+"---"+student.getAge()+"\n");
        }
    }
}
