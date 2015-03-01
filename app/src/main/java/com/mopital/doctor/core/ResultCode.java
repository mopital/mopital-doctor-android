package com.mopital.doctor.core;

/**
 * Created by ahmetkucuk on 01/03/15.
 */
public enum ResultCode {

    FAIL,
    SUCCESS;

    public static ResultCode fromInt(int i ) {

        return ResultCode.values()[i];

    }


}
