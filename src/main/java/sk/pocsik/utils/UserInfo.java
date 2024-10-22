package sk.pocsik.utils;

import lombok.Getter;


public abstract class UserInfo {
    @Getter
    private static Long userId =  null;
    @Getter
    private static String userName = null;

    public static void setUserInfo(Long userId, String userName) {
        UserInfo.userId = userId;
        UserInfo.userName = userName;
    }

}
