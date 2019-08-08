package dhkyhb.retrofit;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import okhttp3.*;
import sun.rmi.runtime.Log;

import java.io.IOException;

/**
 * Create by hl
 * Data by 2019-05-14
 * Description:
 */
public class DemoService {

    private static String key = "54dc2a857afa02b727d1d9d4afe1bec1";

    private static OkHttpClient client;

    public static void init() {
        client = new OkHttpClient().newBuilder()
                .build();

    }

    public static <T> void TodayNba(Class<T> responseClass, NetCallBack<T> callBack) {
        Request request = new Request.Builder().url("http://op.juhe.cn/onebox/basketball/nba?dtype=&=&key=" + key)
                .get().build();
        client.newCall(request).enqueue(new wrapperOkHttpCallBack<>(responseClass, callBack));
    }

    static class wrapperOkHttpCallBack<T> implements Callback {

        private static Gson gson = new Gson();
        private Class<T> aClass;
        private NetCallBack<T> callBack;

        public wrapperOkHttpCallBack(Class<T> aClass, NetCallBack<T> callBack) {
            this.aClass = aClass;
            this.callBack = callBack;
        }

        @Override
        public void onFailure(Call call, IOException e) {
            System.out.println("WrapperOkHttpCallback, onFailure");
            e.printStackTrace();
            callBack.onFailure(e);
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            JsonReader jsonReader = gson.newJsonReader(response.body().charStream());
            T entity = gson.getAdapter(aClass).read(jsonReader);
            System.out.println("response: " + entity.toString());
            callBack.onSuccess(entity);
        }
    }

}
