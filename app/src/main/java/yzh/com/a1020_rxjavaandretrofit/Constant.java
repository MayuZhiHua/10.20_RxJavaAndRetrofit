package yzh.com.a1020_rxjavaandretrofit;

/**
 * Api接口的常量类
 * Created by HP on 2016/10/20.
 */

public class Constant {
    //    http://capi.douyucdn.cn/api/v1/getVerticalRoom?limit=20&offset=0&time=1470800460
    public static final String BASE_URL = "http://capi.douyucdn.cn/";

    public static class Path {
        public static final String ULR_HOT = "api/v1/getVerticalRoom";
    }

    public static class Params {
        public static final String LIMIT = "limit";
        public static final String OFFSET = "offset";
        public static final String TIME = "time";
    }

    public static class DefaultValue {
        public static final String LIMIT = "20";
        public static final String OFFSET = "0";
        public static final String TIME = "1470800460";
    }
}
