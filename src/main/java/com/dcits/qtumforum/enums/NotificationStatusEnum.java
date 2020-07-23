package com.dcits.qtumforum.enums;

/**
 * @author T-bk
 * @version 2.0
 * @date 2020/7/22 17:00
 */

public enum NotificationStatusEnum {
    UNREAD(0), READ(1);
    private int status;

    public int getStatus() {
        return status;
    }

    NotificationStatusEnum(int status) {
        this.status = status;
    }
}
