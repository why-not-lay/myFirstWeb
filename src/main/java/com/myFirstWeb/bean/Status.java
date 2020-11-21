package com.myFirstWeb.bean;

public final class Status {
    public static class Status_records_view {
        public static final int DELETED = 0;
        public static final int USED = 1;
    }

    public static class Status_records_trade {

        public static final int DELETED = 0;
        public static final int USED = 1;
    }

    public static class Status_records_shopping_cart {
        public static final int  DELETED = 0;
        public static final int  OFF_SHLEF = 1;
        public static final int  NOT_ENOUGH = 2;
//        public static final int  UNSELECTED = 3;
//        public static final int  SELECTED = 4;
        public static final int USED = 5;

    }

    public static class Status_products {
        public static final int DELETED = 0;
        public static final int OFF_SHELF = 1;
        public static final int SOLD_OUT = 2;
        public static final int ON_SHELF = 3;
    }

    public static class Status_Database {
        public static final int SUCCESSFUL = -1;
        public static final int CONN_FAILED = -2;
        public static final int SYNAX_ERROR = -3;
    }

    public static class Status_situation {
        public static final int FAILED = 0;
        public static final int SUCCESSFUL = 1;
        public static final int NOT_EXIST = 2;
    }
}
