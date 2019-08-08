package dhkyhb;

import com.google.gson.Gson;
import dhkyhb.gson.Bean;
import dhkyhb.gson.utils;
import dhkyhb.rxjava.RxjavaSet;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 * Hello world!
 *
 */
public class App 
{

    private static String option2 = "陇南市";
    private static String option3 = "娱乐服务";
    private static Map<String, String> cityMap;
    private static RxjavaSet set = new RxjavaSet();

    public static void main( String[] args )
    {
        func2();
        func3();
    }

    public static void func1() {
        set.observableCreate();
    }

    public static void func2(){
        String jsonStr = "";
        try {
            FileInputStream fis = new FileInputStream("C:\\Users\\hl\\Desktop\\mcc.json");
            jsonStr = utils.jsonToStr(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        Bean bean = gson.fromJson(jsonStr, Bean.class);
//        System.out.println(bean);

        cityMap = new HashMap<>();

        for (Bean.Province province : bean.getProvince()) {
            for (Bean.City city : province.getCityList()) {
                for (Bean.Mcc mcc: city.getMccList()) {
                    cityMap.put(mcc.mccName, mcc.mcc);
                }
            }
        }
//        System.out.println(cityMap);
    }

    private static void func3() {
        List<String> strLists = new ArrayList<>();
        strLists.add("娱乐服务");
        strLists.add("餐馆");
        Iterator<String> mccIterator = strLists.iterator();
        StringBuilder sb = new StringBuilder("");
        while(mccIterator.hasNext()) {
            sb.append(cityMap.get(mccIterator.next()) + ",");
        }
        System.out.println(sb.toString());
    }

}
