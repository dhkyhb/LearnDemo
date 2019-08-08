package dhkyhb.retrofit;

/**
 * Create by hl
 * Data by 2019-05-14
 * Description:
 */
public interface NetCallBack<T> {

    void onFailure(Exception e);

    void onSuccess(T data);

}
