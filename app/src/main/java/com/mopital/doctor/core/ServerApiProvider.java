package com.mopital.doctor.core;

/**
 * Created by ahmetkucuk on 02/03/15.
 */
public class ServerApiProvider {

    private static final DefaultServerApi apiProvider = new DefaultServerApi();

    public static ServerApi serverApi() {
        return apiProvider;
    }

}
