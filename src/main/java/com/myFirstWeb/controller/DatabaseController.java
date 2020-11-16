package com.myFirstWeb.controller;
import java.sql.*;
import java.util.Date;
import java.util.ArrayList;

import com.myFirstWeb.bean.*;

/**
 * Table:
 * --users
 * --products
 * --records_trade
 * --records_view
 * --records_shopping_cart
 * */

/**
 * user:
 * uid(bigint)
 * username(varchar)
 * password(varchar)
 * email(varchar)
 * date_create(date)
 * date_login(date)
 * */

/**
 * products:
 * pid(bigint)
 * uid(bigint)
 * productname(varchar)
 * description(varchar)
 * price(int)
 * num(int)
 * status(int)
 * -- 0 - remove
 * -- 1 - off shelf
 * -- 2 - sold out
 * -- 3 - on shelf
 * */

/**
 * records_trade:
 * pid(bigint)
 * uid_buyer(bigint)
 * uid_seller(bigint)
 * num(int)
 * date_trade(date)
 * cost(int)
 * status(int)
 * */

/**
 * records_view:
 * uid(bigint)
 * pid(bigint)
 * date_view(date)
 * status(int)
 * */

/**
 * records_shopping_cart:
 * uid(bigint)
 * pid(bigint)
 * num(int)
 * status(int)
 * */

public class DatabaseController {
    private static String JDBC_URL = "jdbc:mysql://localhost:3306/myfirstweb";
    private static String JDBC_USER = "root";
    private static String JDBC_PASSWORD = "luoYI";
    private static String JDBC_PATH = "com.mysql.cj.jdbc.Driver";

    public static void InsertUser(User user)throws SQLException,ClassNotFoundException  {
        Class.forName(JDBC_PATH);
        try(Connection conn = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD)) {
            try(PreparedStatement ps = conn.prepareStatement("insert into users ( username, passwd, email, date_create, date_login) values(?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS)) {
                ps.setObject(1,user.getName());
                ps.setObject(2, user.getPassword());
                ps.setObject(3,user.getEmail());
                ps.setObject(4, user.getDate_login());
                ps.setObject(5, user.getDate_create());

                ps.executeUpdate();
                try(ResultSet rs = ps.getGeneratedKeys()) {
                    if(rs.next()) {
                        user.setId(rs.getLong(1));
                    }
                }
            }
        }
    }

    public static void InsertProduct( Product product) throws SQLException,ClassNotFoundException {
        Class.forName(JDBC_PATH);
        try(Connection conn = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD)) {
            try(PreparedStatement ps = conn.prepareStatement("insert into products (uid,product_name,description,price,num,status) values(?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS)) {
                ps.setObject(1, product.getUid());
                ps.setObject(2, product.getName());
                ps.setObject(3, product.getDescription());
                ps.setObject(4, product.getPrice());
                ps.setObject(5, product.getNum());
                ps.setObject(6, product.getStatus());
                ps.executeUpdate();
                try(ResultSet rs = ps.getGeneratedKeys()) {
                    if(rs.next()) {
                        product.setPid(rs.getLong(1));
                    }
                }
            }

        }
    }

    public static void RemoveTradeRecord(long uid, long pid) throws SQLException,ClassNotFoundException{
        Class.forName(JDBC_PATH);
        try(Connection conn = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD)) {
            try(PreparedStatement ps = conn.prepareStatement("update into records_trade set status=? where uid=? and pid=?")) {
                ps.setObject(1, 0);
                ps.setObject(2, uid);
                ps.setObject(3, pid);
                ps.executeUpdate();

            }

        }

    }

    public static void InsertTradeRecord(Records_trade trade) throws SQLException,ClassNotFoundException{
        Class.forName(JDBC_PATH);
        try(Connection conn = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD)) {
            try(PreparedStatement ps = conn.prepareStatement("insert into records_trade (pid,uid_buyer,uid_seller,num,date_trade,cost,status) values(?,?,?,?,?,?,?)")) {
                ps.setObject(1,trade.getPid());
                ps.setObject(2,trade.getUid_buyer());
                ps.setObject(3,trade.getUid_seller());
                ps.setObject(4, trade.getNum());
                ps.setObject(5, trade.getDate_trade());
                ps.setObject(6, trade.getCost());
                ps.setObject(7, trade.getStatus());
                ps.executeUpdate();
            }

        }


    }

    public static void UpdateProduct(Product product) throws SQLException,ClassNotFoundException{
        Class.forName(JDBC_PATH);
        try(Connection conn = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD)) {
            try(PreparedStatement ps = conn.prepareStatement("update products set product_name=?,description=?,price=?,num=? where pid=?")) {
                ps.setObject(1, product.getName());
                ps.setObject(2, product.getDescription());
                ps.setObject(3, product.getPrice());
                ps.setObject(4,product.getNum());
                ps.setObject(5, product.getId());

                ps.executeUpdate();
            }

        }
    }

    public static void RemoveProduct(long pid)throws SQLException,ClassNotFoundException{
        Class.forName(JDBC_PATH);
        try(Connection conn = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD)) {
            try(PreparedStatement ps = conn.prepareStatement("update products set status=? where pid=?")) {
                ps.setObject(1, 0);
                ps.setObject(2, pid);

                ps.executeUpdate();
            }
        }

    }

    public static void InsertViewRecord(Records_view view) throws SQLException,ClassNotFoundException{
        Class.forName(JDBC_PATH);
        try(Connection conn = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD)) {
            try(PreparedStatement ps = conn.prepareStatement("insert into records_view (uid, pid, date_view, status) values (?,?,?,?)")) {
                ps.setObject(1, view.getUid());
                ps.setObject(2, view.getPid());
                ps.setObject(3, view.getDate());
                ps.setObject(4, view.getStatus());

                ps.executeUpdate();

            }

        }

    }

    public static void RemoveViewRecord(long uid, long pid) throws SQLException,ClassNotFoundException{
        Class.forName(JDBC_PATH);
        try(Connection conn = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD)) {
            try(PreparedStatement ps = conn.prepareStatement("update records_view set status=? where uid=? and pid=?")) {
                ps.setObject(1, 0);
                ps.setObject(2, uid);
                ps.setObject(3, pid);
                ps.executeUpdate();
            }

        }

    }




    public static User SearchUser(String username) throws SQLException,ClassNotFoundException{
        Class.forName(JDBC_PATH);
        try(Connection conn = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD)) {
            try(PreparedStatement ps = conn.prepareStatement("select uid, username, passwd,email,date_create, date_login from users where username = ? ")) {
                ps.setObject(1,username);
                try(ResultSet rs = ps.executeQuery()) {
                    User user = null;
                    while(rs.next()) {
                        user = new User();
                        user.setId(rs.getLong("uid"));
                        user.setName(rs.getString("username"));
                        user.setPassword(rs.getString("passwd"));
                        user.setEmail(rs.getString("email"));
                        user.setDate_login(rs.getDate("date_login"));
                        user.setDate_create(rs.getDate("date_create"));
                    }
                    return user;
                }

            }

        }
    }
    public static User SearchUser(long uid) throws SQLException,ClassNotFoundException{
        Class.forName(JDBC_PATH);
        try(Connection conn = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD)) {
            try(PreparedStatement ps = conn.prepareStatement("select username, passwd,email,date_create, date_login from users where uid = ? ")) {
                ps.setObject(1,uid);
                try(ResultSet rs = ps.executeQuery()) {
                    User user = null;
                    while(rs.next()) {
                        user = new User();
                        user.setId(uid);
                        user.setName(rs.getString("username"));
                        user.setPassword(rs.getString("passwd"));
                        user.setEmail(rs.getString("email"));
                        user.setDate_login(rs.getDate("date_login"));
                        user.setDate_create(rs.getDate("date_create"));
                    }
                    return user;
                }

            }

        }
    }

    public static Product SearchProduct(long pid)throws SQLException,ClassNotFoundException{
        Class.forName(JDBC_PATH);
        try(Connection conn = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD)) {
            try(PreparedStatement ps = conn.prepareStatement("select uid, product_name,description, price, num, status from products where status>0 and pid=?")) {
                ps.setObject(1, pid);
                try(ResultSet rs = ps.executeQuery()) {
                    Product product = null;
                    while(rs.next()) {
                        product = new Product();
                        product.setPid(pid);
                        product.setUid(rs.getLong("uid"));
                        product.setName(rs.getString("product_name"));
                        product.setDescription(rs.getString("description"));
                        product.setPrice(rs.getInt("price"));
                        product.setNum(rs.getInt("num"));
                        product.setStatus(rs.getInt("status"));
                    }
                    return product;
                }
            }
        }
    }

    public static ArrayList<Product> GetSellerProducts(int num, int page, long uid)throws SQLException,ClassNotFoundException{
        Class.forName(JDBC_PATH);
        try(Connection conn = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD)) {
            try(PreparedStatement ps = conn.prepareStatement("select pid, uid, product_name,description, price, num, status from products where status > 0 and uid = ? limit ?,?")) {
                ps.setObject(1, uid);
                ps.setObject(2, page);
                ps.setObject(3, num);
                try(ResultSet rs = ps.executeQuery()) {
                    ArrayList<Product> products = new ArrayList<Product>();
                    while(rs.next()) {
                        Product product = new Product();
                        product.setPid(rs.getLong("pid"));
                        product.setUid(rs.getLong("uid"));
                        product.setName(rs.getString("product_name"));
                        product.setDescription(rs.getString("description"));
                        product.setPrice(rs.getInt("price"));
                        product.setNum(rs.getInt("num"));
                        product.setStatus(rs.getInt("status"));

                        products.add(product);
                    }
                    return products;
                }
            }
        }
    }

    public static Product GetProduct(long pid) throws SQLException, ClassNotFoundException {
        Class.forName(JDBC_PATH);
        try(Connection conn = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD)) {
            try(PreparedStatement ps = conn.prepareStatement("select pid, uid, product_name,description, price, num, status from products where pid=?")) {
                ps.setObject(1, pid);
                try(ResultSet rs = ps.executeQuery()) {
                    Product product = null;
                    if(rs.next()) {
                        product = new Product();
                        product.setPid(rs.getLong("pid"));
                        product.setUid(rs.getLong("uid"));
                        product.setName(rs.getString("product_name"));
                        product.setDescription(rs.getString("description"));
                        product.setPrice(rs.getInt("price"));
                        product.setNum(rs.getInt("num"));
                        product.setStatus(rs.getInt("status"));
                    }
                    return product;
                }
            }
        }

    }

    public static ArrayList<Product> GetProducts(int num, int page)throws SQLException,ClassNotFoundException{
        Class.forName(JDBC_PATH);
        try(Connection conn = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD)) {
            try(PreparedStatement ps = conn.prepareStatement("select pid, uid, product_name,description, price, num, status from products where status=3 limit ?,?")) {
                ps.setObject(1, page);
                ps.setObject(2, num);
                try(ResultSet rs = ps.executeQuery()) {
                    ArrayList<Product> products = new ArrayList<Product>();
                    while(rs.next()) {
                        Product product = new Product();
                        product.setPid(rs.getLong("pid"));
                        product.setUid(rs.getLong("uid"));
                        product.setName(rs.getString("product_name"));
                        product.setDescription(rs.getString("description"));
                        product.setPrice(rs.getInt("price"));
                        product.setNum(rs.getInt("num"));
                        product.setStatus(rs.getInt("status"));

                        products.add(product);
                    }
                    return products;
                }
            }
        }
    }

    public static void InsertRecordShoppingCart(Records_shopping_cart record)throws SQLException, ClassNotFoundException {
        Class.forName(JDBC_PATH);
        try(Connection conn = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD)) {
            try (PreparedStatement ps = conn.prepareStatement("Insert into records_shopping_cart (uid,pid,num,status) values (?,?,?,?)")) {
                ps.setObject(1, record.getUid());
                ps.setObject(2, record.getPid());
                ps.setObject(3, record.getNum());
                ps.setObject(4, record.getStatus());

                ps.executeUpdate();
            }
        }
    }

    public static ArrayList<Records_shopping_cart> GetRecordsShoppingCart(long uid, int num, int page) throws SQLException, ClassNotFoundException {
        Class.forName(JDBC_PATH);
        try(Connection conn = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD)) {
            try(PreparedStatement ps = conn.prepareStatement("select uid, pid, num, status from records_shopping_cart where status > 0 and uid=? limit ?,?")) {
                ps.setObject(1, uid);
                ps.setObject(2, page);
                ps.setObject(3, num);
                try(ResultSet rs = ps.executeQuery()) {
                    ArrayList<Records_shopping_cart> records = new ArrayList<Records_shopping_cart>();
                    while(rs.next()) {
                        Records_shopping_cart record = new Records_shopping_cart();
                        record.setUid(rs.getLong("uid"));
                        record.setPid(rs.getLong("pid"));
                        record.setNum(rs.getInt("num"));
                        record.setStatus(rs.getInt("status"));
                        records.add(record);
                    }
                    return records;
                }
            }
        }
    }

    public static void UpdateRecordsShoppingCartNum(long uid, long pid, long num)throws SQLException, ClassNotFoundException {
        Class.forName(JDBC_PATH);
        try(Connection conn = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD)) {
            try(PreparedStatement ps = conn.prepareStatement("update records_shopping_cart set num=? where uid=? and pid=?")) {
                ps.setObject(1, num);
                ps.setObject(2, uid);
                ps.setObject(3, pid);
                ps.executeUpdate();
            }
        }

    }

    public static void RemoveRecordShoppingCart(long uid, long pid)throws SQLException,ClassNotFoundException {
        Class.forName(JDBC_PATH);
        try(Connection conn = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD)) {
            try(PreparedStatement ps = conn.prepareStatement("update records_shopping_cart set status=0 where uid=? and pid=?")) {
                ps.setObject(1, uid);
                ps.setObject(2, pid);
                ps.executeUpdate();
            }
        }

    }

    public static int CountProducts()throws SQLException, ClassNotFoundException {
        Class.forName(JDBC_PATH);
        try(Connection conn = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD)) {
            try(PreparedStatement ps = conn.prepareStatement("select count(*) from products where status=3 ")) {
                try(ResultSet rs = ps.executeQuery()) {
                    if(rs.next()) {
                        return rs.getInt(1);
                    } else {
                        return -1;
                    }
                }
            }
        }
    }


    public static int CountSellerProducts(long uid)throws SQLException, ClassNotFoundException {
        Class.forName(JDBC_PATH);
        try(Connection conn = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD)) {
            try(PreparedStatement ps = conn.prepareStatement("select count(*) from products where status > 0 and uid=? ")) {
                ps.setObject(1, uid);
                try(ResultSet rs = ps.executeQuery()) {
                    if(rs.next()) {
                        return rs.getInt(1);
                    } else {
                        return -1;
                    }
                }
            }
        }
    }

    public static int CountRecordsShoppingCart(long uid)throws SQLException, ClassNotFoundException {
        Class.forName(JDBC_PATH);
        try(Connection conn = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD)) {
            try(PreparedStatement ps = conn.prepareStatement("select count(*) from records_shopping_cart where status > 0 and uid=?")) {
                ps.setObject(1, uid);
                try(ResultSet rs = ps.executeQuery()) {
                    if(rs.next()) {
                        return rs.getInt(1);
                    } else {
                        return -1;
                    }
                }
            }
        }

    }
}
