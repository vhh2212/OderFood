package com.chungnguyen.orderfoodserver.Common;

import com.chungnguyen.orderfoodserver.Model.User;

/**
 * Created by Admin on 1/3/2018.
 */

public class Common {
    public static User currentUser;
    public static final String UPDATE = "Update";
    public static final String DELETE = "Delete";
    public static final int PICK_IMAGE_REQUEST =71;
    public static String convertCodeToStatus(String code){
        if (code.equals("0"))
            return "Đã đặt hàng";
        else if (code.equals("1"))
            return "Đang gửi thức ăn";
        else
            return "Đã gửi thức ăn";

    }
}
