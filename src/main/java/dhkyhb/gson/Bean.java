package dhkyhb.gson;

import java.util.List;

/**
 * Create by hl
 * Data by 2019-06-26
 * Description:
 */
public class Bean {

    public List<Province> province;

    public List<Province> getProvince() {
        return province;
    }

    public void setProvince(List<Province> province) {
        this.province = province;
    }

    @Override
    public String toString() {
        return "bean{" +
                "province=" + province +
                '}';
    }

    public static class Province{
        public List<City> cityList;
        public String provinceId;
        public String provinceName;

        public List<City> getCityList() {
            return cityList;
        }

        public void setCityList(List<City> cityList) {
            this.cityList = cityList;
        }

        public String getProvinceId() {
            return provinceId;
        }

        public void setProvinceId(String provinceId) {
            this.provinceId = provinceId;
        }

        public String getProvinceName() {
            return provinceName;
        }

        public void setProvinceName(String provinceName) {
            this.provinceName = provinceName;
        }

        @Override
        public String toString() {
            return "Province{" +
                    "cityList=" + cityList +
                    ", provinceId='" + provinceId + '\'' +
                    ", provinceName='" + provinceName + '\'' +
                    '}';
        }
    }

    public static class City{
        public String cityId;
        public String cityName;
        public List<Mcc> mccList;

        public String getCityId() {
            return cityId;
        }

        public void setCityId(String cityId) {
            this.cityId = cityId;
        }

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        public List<Mcc> getMccList() {
            return mccList;
        }

        public void setMccList(List<Mcc> mccList) {
            this.mccList = mccList;
        }

        @Override
        public String toString() {
            return "City{" +
                    "cityId='" + cityId + '\'' +
                    ", cityName='" + cityName + '\'' +
                    ", mccList=" + mccList +
                    '}';
        }
    }

    public static class Mcc{
        public String mcc;
        public String mccName;

        public String getMcc() {
            return mcc;
        }

        public void setMcc(String mcc) {
            this.mcc = mcc;
        }

        public String getMccName() {
            return mccName;
        }

        public void setMccName(String mccName) {
            this.mccName = mccName;
        }

        @Override
        public String toString() {
            return "Mcc{" +
                    "mcc='" + mcc + '\'' +
                    ", mccName='" + mccName + '\'' +
                    '}';
        }
    }
}
