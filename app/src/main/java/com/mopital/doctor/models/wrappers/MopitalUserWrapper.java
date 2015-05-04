package com.mopital.doctor.models.wrappers;

import com.mopital.doctor.models.MopitalUser;

import java.util.List;

/**
 * Created by AlperCem on 4.5.2015.
 */
public class MopitalUserWrapper {

    private List<MopitalUser> userList;

    public MopitalUserWrapper(List<MopitalUser> userList) {
        this.userList = userList;
    }

    public List<MopitalUser> getUserList() {
        return userList;
    }

    public void setUserList(List<MopitalUser> userList) {
        this.userList = userList;
    }
}
