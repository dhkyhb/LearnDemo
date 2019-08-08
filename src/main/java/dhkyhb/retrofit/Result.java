package dhkyhb.retrofit;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * Create by hl
 * Data by 2019-05-14
 * Description:
 */
@Data
@ToString
public class Result {

    private String title;
    private Statuslist statuslist;
    private List<Info> list;
    private List<Teammatch> teammatch;

}
