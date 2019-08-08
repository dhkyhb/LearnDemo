package dhkyhb.retrofit;

import okhttp3.Call;

/**
 * Create by hl
 * Data by 2019-05-14
 * Description:
 */
public interface NBAGamesServices {

    @GET("http://op.juhe.cn/onebox/basketball/nba?dtype=&=&key=54dc2a857afa02b727d1d9d4afe1bec1")
    Call todayNBA();

}
