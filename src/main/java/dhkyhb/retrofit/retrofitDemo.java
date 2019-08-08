package dhkyhb.retrofit;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;

import java.io.IOException;

/**
 * Create by hl
 * Data by 2019-05-14
 * Description:
 */
public class retrofitDemo {

    private Gson gson = new Gson();

    public static void main(String[] args) {
        retrofit();
    }

    public static void demo1() {
        DemoService.init();
        DemoService.TodayNba(NBAReponse.class, new NetCallBack<NBAReponse>() {
            @Override
            public void onFailure(Exception e) {

            }

            @Override
            public void onSuccess(NBAReponse data) {

            }
        });
    }

    public static void retrofit() {
        OkHttpClient client = new OkHttpClient.Builder().build();
        Retrofit retrofit = new Retrofit(client);
        retrofit.createService(NBAGamesServices.class).todayNBA().enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("WrapperOkHttpCallback, onFailure");
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson = new Gson();
                JsonReader jsonReader = gson.newJsonReader(response.body().charStream());
                NBAReponse entity = gson.getAdapter(NBAReponse.class).read(jsonReader);
                System.out.println("response: " + entity.toString());
            }
        });
    }

}
