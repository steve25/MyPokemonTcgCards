package sk.pocsik.utils;

import lombok.Getter;
import sk.pocsik.models.User;


public abstract class UserInfo {
    @Getter
    private static User user =  null;

    public static void setUserInfo(User user) {
        UserInfo.user = user;
    }

}
