package vttp2022.paf.assessment.eshop.respositories;

public class Query {
    public static final String SQL_SELECT_CUSTOMER_EXISTS = "select exists(select * from customers where name = ?) as valid";
    public static final String SQL_INSERT_CUSTOMER_ORDERS = "insert into orders values (?, ?, ?, ?)";
    public static final String SQL_INSERT_CUSTOMER_ORDERS_STATUS = "insert into order_status (delivery_id, status, order_id)  values (?, ?, ?)";
    public static final String SQL_SELECT_ORDERS_STATUS_TIMESTAMP = "select status_update from order_status where delivery_id = ?";
}
