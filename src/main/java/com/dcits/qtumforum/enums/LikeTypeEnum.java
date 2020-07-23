package com.dcits.qtumforum.enums;

/**
 * @author T-bk
 * @version 2.0
 * @date 2020/7/22 16:59
 */

public enum LikeTypeEnum {
    QUESTION(1),
    COMMENT(2),
    SUB_COMMENT(3);
    private Integer type;


    public Integer getType() {
        return type;
    }

    LikeTypeEnum(Integer type) {
        this.type = type;
    }

    public static boolean isExist(Integer type) {
        for (LikeTypeEnum likeTypeEnum : LikeTypeEnum.values()) {
            if (likeTypeEnum.getType() == type) {
                return true;
            }
        }
        return false;
    }
}