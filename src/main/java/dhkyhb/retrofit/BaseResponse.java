package dhkyhb.retrofit;

import lombok.Data;
import lombok.ToString;

/**
 * Create by hl
 * Data by 2019-05-14
 * Description:基础响应类
 */
@Data
@ToString
public class BaseResponse<T> {

    public String reason;
    public T result;
    public int error_code;

}
