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
 * tid(bigint)
 * pid(bigint)
 * uid_buyer(bigint)
 * uid_seller(bigint)
 * num(int)
 * date_trade(date)
 * cost(int)
 * status(int)
 * --0:remove
 * --1:successful
 * --2:return
 * */

/**
 * records_view:
 * vid(bigint)
 * uid(bigint)
 * pid(bigint)
 * date_view(date)
 * status(int)
 * --0:remove
 * --1:successful
 * */

/**
 * records_shopping_cart:
 * sid(bigint)
 * uid(bigint)
 * pid(bigint)
 * num(int)
 * status(int)
 * --0:remove
 * --1:off shelf/had sol
 * --2:off
 * --3:on
 * */

public class DatabaseController {
    private static String JDBC_URL = "jdbc:mysql://localhost:3306/myfirstweb";
    private static String JDBC_USER = "root";
    private static String JDBC_PASSWORD = "luoYI";
    private static String JDBC_PATH = "com.mysql.cj.jdbc.Driver";

    public static int InsertUser(User user)throws SQLException,ClassNotFoundException  {
        Class.forName(JDBC_PATH);
        try(Connection conn = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD)) {
            try(PreparedStatement ps = conn.prepareStatement("insert into users ( username, passwd, email, date_create, date_login) values(?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS)) {
                ps.setObject(1,user.getName());
                ps.setObject(2, user.getPassword());
                ps.setObject(3,user.getEmail());
                ps.setObject(4, user.getDate_login());
                ps.setObject(5, user.getDate_create());

                int flag = ps.executeUpdate();
                try(ResultSet rs = ps.getGeneratedKeys()) {
                    if(rs.next()) {
                        user.setId(rs.getLong(1));
                    }
                }
                return flag;
            } catch (Exception e) {
                e.printStackTrace();
                return Status.Status_Database.SYNAX_ERROR;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Status.Status_Database.CONN_FAILED;
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

    public static int RemoveTradeRecord(long tid) throws SQLException,ClassNotFoundException{
        Class.forName(JDBC_PATH);
        try(Connection conn = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD)) {
            try(PreparedStatement ps = conn.prepareStatement("update into records_trade set status=? where sid=?")) {
                ps.setObject(1, Status.Status_records_trade.DELETED);
                ps.setObject(2, tid);
                return ps.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
                return Status.Status_Database.SYNAX_ERROR;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Status.Status_Database.CONN_FAILED;
        }

    }

    public static int InsertTradeRecord(Records_trade trade) throws SQLException,ClassNotFoundException{
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
                return ps.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
                return Status.Status_Database.SYNAX_ERROR;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Status.Status_Database.CONN_FAILED;
        }
    }

    public static int UpdateProduct(Product product) throws SQLException,ClassNotFoundException{
        Class.forName(JDBC_PATH);
        try(Connection conn = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD)) {
            try(PreparedStatement ps = conn.prepareStatement("update products set product_name=?,description=?,price=?,num=? where pid=?")) {
                ps.setObject(1, product.getName());
                ps.setObject(2, product.getDescription());
                ps.setObject(3, product.getPrice());
                ps.setObject(4,product.getNum());
                ps.setObject(5, product.getId());

                return ps.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
                return Status.Status_Database.SYNAX_ERROR;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Status.Status_Database.CONN_FAILED;
        }
    }

    public static int RemoveProduct(long pid)throws SQLException,ClassNotFoundException{
        Class.forName(JDBC_PATH);
        try(Connection conn = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD)) {
            try(PreparedStatement ps = conn.prepareStatement("update products set status=? where pid=?")) {
                ps.setObject(1, Status.Status_products.DELETED);
                ps.setObject(2, pid);

                return ps.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
                return Status.Status_Database.SYNAX_ERROR;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Status.Status_Database.CONN_FAILED;
        }

    }

    public static int InsertViewRecord(Records_view view) throws SQLException,ClassNotFoundException{
        Class.forName(JDBC_PATH);
        try(Connection conn = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD)) {
            try(PreparedStatement ps = conn.prepareStatement("insert into records_view (uid, pid, date_view, status) values (?,?,?,?)")) {
                ps.setObject(1, view.getUid());
                ps.setObject(2, view.getPid());
                ps.setObject(3, view.getDate());
                ps.setObject(4, view.getStatus());

                return ps.executeUpdate();

            } catch (Exception e) {
                e.printStackTrace();
                return Status.Status_Database.SYNAX_ERROR;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Status.Status_Database.CONN_FAILED;
        }

    }

    public static int RemoveViewRecord(long vid) throws SQLException,ClassNotFoundException{
        Class.forName(JDBC_PATH);
        try(Connection conn = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD)) {
            try(PreparedStatement ps = conn.prepareStatement("update records_view set status=? where sid=?")) {
                ps.setObject(1, Status.Status_records_view.DELETED);
                ps.setObject(2, vid);
                return ps.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
                return Status.Status_Database.SYNAX_ERROR;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Status.Status_Database.CONN_FAILED;
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

    public static ArrayList<Product> GetSellerProducts(long uid, int status,int num, int page)throws SQLException,ClassNotFoundException{
        Class.forName(JDBC_PATH);
        try(Connection conn = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD)) {
            try(PreparedStatement ps = conn.prepareStatement("select pid, uid, product_name,description, price, num, status from products where status = ? and uid = ? limit ?,?")) {
                ps.setObject(1, status);
                ps.setObject(2, uid);
                ps.setObject(3, page);
                ps.setObject(4, num);
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

    public static ArrayList<Product> GetProducts(int status, int num, int page)throws SQLException,ClassNotFoundException{
        Class.forName(JDBC_PATH);
        try(Connection conn = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD)) {
            try(PreparedStatement ps = conn.prepareStatement("select pid, uid, product_name,description, price, num, status from products where status=? limit ?,?")) {
                ps.setObject(1, status);
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

    public static int InsertShoppingCartRecord(Records_shopping_cart record)throws SQLException, ClassNotFoundException {
        Class.forName(JDBC_PATH);
        try(Connection conn = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD)) {
            try (PreparedStatement ps = conn.prepareStatement("Insert into records_shopping_cart (uid,pid,num,status) values (?,?,?,?)")) {
                ps.setObject(1, record.getUid());
                ps.setObject(2, record.getPid());
                ps.setObject(3, record.getNum());
                ps.setObject(4, record.getStatus());

                return ps.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
                return Status.Status_Database.SYNAX_ERROR;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Status.Status_Database.CONN_FAILED;
        }
    }

    public static ArrayList<Records_shopping_cart> GetShoppingCartRecords(long uid, int status, int num, int page) throws SQLException, ClassNotFoundException {
        Class.forName(JDBC_PATH);
        try(Connection conn = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD)) {
            try(PreparedStatement ps = conn.prepareStatement("select sid,uid, pid, num, status from records_shopping_cart where status = ? and uid=? limit ?,?")) {
                ps.setObject(1, status);
                ps.setObject(2, uid);
                ps.setObject(3, page);
                ps.setObject(4, num);
                try(ResultSet rs = ps.executeQuery()) {
                    ArrayList<Records_shopping_cart> records = new ArrayList<Records_shopping_cart>();
                    while(rs.next()) {
                        Records_shopping_cart record = new Records_shopping_cart();
                        record.setSid(rs.getLong("sid"));
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

    public static int UpdateShoppingCartRecords(Records_shopping_cart shopping)throws SQLException, ClassNotFoundException {
        Class.forName(JDBC_PATH);
        try(Connection conn = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD)) {
            try(PreparedStatement ps = conn.prepareStatement("update records_shopping_cart set uid=?,pid=?,num=?,status=? where sid=?")) {
                ps.setObject(1, shopping.getUid());
                ps.setObject(2, shopping.getPid());
                ps.setObject(3, shopping.getNum());
                ps.setObject(4, shopping.getStatus());
                return ps.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
                return Status.Status_Database.SYNAX_ERROR;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Status.Status_Database.CONN_FAILED;
        }
    }



    public static int RemoveShoppingCartRecord(long sid)throws SQLException,ClassNotFoundException {
        Class.forName(JDBC_PATH);
        try(Connection conn = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD)) {
            try(PreparedStatement ps = conn.prepareStatement("update records_shopping_cart set status=? where sid=?")) {
                ps.setObject(1, Status.Status_records_shopping_cart.DELETED);
                ps.setObject(2, sid);
                ps.executeUpdate();

                return Status.Status_Database.SUCCESSFUL;
            } catch (Exception e) {
                e.printStackTrace();
                return Status.Status_Database.SYNAX_ERROR;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Status.Status_Database.CONN_FAILED;
        }

    }


    public static Integer CountProducts(int status)throws SQLException, ClassNotFoundException {
        Class.forName(JDBC_PATH);
        try(Connection conn = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD)) {
            try(PreparedStatement ps = conn.prepareStatement("select count(*) from products where status=? ")) {
                ps.setObject(1, status);
                try(ResultSet rs = ps.executeQuery()) {
                    if(rs.next()) {
                        return rs.getInt(1);
                    } else {
                        return null;
                    }
                }
            }
        }
    }


    public static Integer CountSellerProducts(long uid, int status)throws SQLException, ClassNotFoundException {
        Class.forName(JDBC_PATH);
        try(Connection conn = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD)) {
            try(PreparedStatement ps = conn.prepareStatement("select count(*) from products where status = ? and uid=? ")) {
                ps.setObject(1, status);
                ps.setObject(2, uid);
                try(ResultSet rs = ps.executeQuery()) {
                    if(rs.next()) {
                        return rs.getInt(1);
                    } else {
                        return null;
                    }
                }
            }
        }
    }

    public static Integer CountShoppingCartRecords(long uid, int status)throws SQLException, ClassNotFoundException {
        Class.forName(JDBC_PATH);
        try(Connection conn = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD)) {
            try(PreparedStatement ps = conn.prepareStatement("select count(*) from records_shopping_cart where status = ? and uid=?")) {
                ps.setObject(1, status);
                ps.setObject(2, uid);
                try(ResultSet rs = ps.executeQuery()) {
                    if(rs.next()) {
                        return rs.getInt(1);
                    } else {
                        return null;
                    }
                }
            }
        }

    }

    public static Integer CountShoppingCartRecordsNot(long uid, int status)throws SQLException, ClassNotFoundException {
        Class.forName(JDBC_PATH);
        try(Connection conn = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD)) {
            try(PreparedStatement ps = conn.prepareStatement("select count(*) from records_shopping_cart where status = ? and uid!=?")) {
                ps.setObject(1, status);
                ps.setObject(2, uid);
                try(ResultSet rs = ps.executeQuery()) {
                    if(rs.next()) {
                        return rs.getInt(1);
                    } else {
                        return null;
                    }
                }
            }
        }

    }

    public static Records_trade SearchTradeRecord(long tid)throws SQLException, ClassNotFoundException {
        Class.forName(JDBC_PATH);
        try(Connection conn = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD)) {
            try(PreparedStatement ps = conn.prepareStatement("select pid, uid_buyer, uid_seller, num, date_trade, cost, status from records_trade where tid=?")) {
                ps.setObject(1, tid);
                try(ResultSet rs = ps.executeQuery()) {
                    Records_trade trade = null;
                    if(rs.next()) {
                        trade = new Records_trade();
                        trade.setPid(rs.getLong("pid"));
                        trade.setUid_buyer(rs.getLong("uid_buyer"));
                        trade.setUid_seller(rs.getLong("uid_seller"));
                        trade.setNum(rs.getInt("num"));
                        trade.setDate_trade(rs.getDate("date_trade"));
                        trade.setCost(rs.getInt("cost"));
                        trade.setStatus(rs.getInt("status"));
                    }
                    return trade;
                }
            }
        }

    }


    public static Records_shopping_cart SearchShoppingCartRecord(long sid)throws SQLException, ClassNotFoundException {
        Class.forName(JDBC_PATH);
        try(Connection conn = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD)) {
            try(PreparedStatement ps = conn.prepareStatement("select sid,uid,pid,num,status from records_shopping_cart where sid=?")) {
                ps.setObject(1, sid);
                try(ResultSet rs = ps.executeQuery()) {
                    Records_shopping_cart shopping = null;
                    if(rs.next()) {
                        shopping = new Records_shopping_cart();
                        shopping.setSid(sid);
                        shopping.setNum(rs.getInt("num"));
                        shopping.setPid(rs.getLong("pid"));
                        shopping.setUid(rs.getLong("uid"));
                        shopping.setStatus(rs.getInt("status"));
                    }
                    return shopping;
                }

            }
        }

    }

    public static ArrayList<Records_shopping_cart> GetShoppingCartRecords(long pid, int status)throws SQLException,ClassNotFoundException {
        Class.forName(JDBC_PATH);
        try(Connection conn = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD)) {
            try(PreparedStatement ps = conn.prepareStatement("select sid,uid,pid,num,status from records_shopping_cart where pid=? and status = ?")) {
                ps.setObject(1, pid);
                ps.setObject(2, status);
                try(ResultSet rs = ps.executeQuery()) {
                    ArrayList<Records_shopping_cart> shoppings = new ArrayList<Records_shopping_cart>();
                    while(rs.next()) {
                        Records_shopping_cart shopping = new Records_shopping_cart();
                        shopping.setPid(pid);
                        shopping.setSid(rs.getLong("sid"));
                        shopping.setUid(rs.getLong("uid"));
                        shopping.setNum(rs.getInt("num"));
                        shopping.setStatus(rs.getInt("status"));
                        shoppings.add(shopping);
                    }
                    return shoppings;

                }

            }
        }

    }

    public static ArrayList<Records_shopping_cart> GetShoppingCartRecordsNot(long pid, int status)throws SQLException,ClassNotFoundException {
        Class.forName(JDBC_PATH);
        try(Connection conn = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD)) {
            try(PreparedStatement ps = conn.prepareStatement("select sid,uid,pid,num,status from records_shopping_cart where pid=? and status != ?")) {
                ps.setObject(1, pid);
                ps.setObject(2, status);
                try(ResultSet rs = ps.executeQuery()) {
                    ArrayList<Records_shopping_cart> shoppings = new ArrayList<Records_shopping_cart>();
                    while(rs.next()) {
                        Records_shopping_cart shopping = new Records_shopping_cart();
                        shopping.setPid(pid);
                        shopping.setSid(rs.getLong("sid"));
                        shopping.setUid(rs.getLong("uid"));
                        shopping.setNum(rs.getInt("num"));
                        shopping.setStatus(rs.getInt("status"));
                        shoppings.add(shopping);
                    }
                    return shoppings;

                }

            }
        }

    }

    public static int UpdateUser(User user)throws SQLException, ClassNotFoundException {
        Class.forName(JDBC_PATH);
        try(Connection conn = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD)) {
            try(PreparedStatement ps = conn.prepareStatement("update users set email=? ,date_login=?, passwd=? where uid=?")) {
                ps.setObject(1, user.getEmail());
                ps.setObject(2, user.getDate_login());
                ps.setObject(3, user.getPassword());
                ps.setObject(4, user.getId());
                return ps.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
                return Status.Status_Database.SYNAX_ERROR;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Status.Status_Database.CONN_FAILED;
        }

    }

    public static ArrayList<Records_view> GetViewRecords(long uid,int status,int num, int page)throws SQLException,ClassNotFoundException {
        Class.forName(JDBC_PATH);
        try(Connection conn = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD)) {
            try(PreparedStatement ps = conn.prepareStatement("select vid, uid, pid, date, status from records_view where status = ? and uid=? limit ?,?")) {
                ps.setObject(1, status);
                ps.setObject(2, uid);
                ps.setObject(3, page);
                ps.setObject(4, num);
                try(ResultSet rs = ps.executeQuery()) {
                    ArrayList<Records_view> views = new ArrayList<Records_view>();
                    while(rs.next()) {
                        Records_view view= new Records_view();
                        view.setPid(rs.getLong("pid"));
                        view.setUid(rs.getLong("uid"));
                        view.setVid(rs.getLong("vid"));
                        view.setDate(rs.getDate("date"));
                        view.setStatus(rs.getInt("status"));
                        views.add(view);
                    }
                    return views;
                }
            }
        }
    }

    public static ArrayList<Records_trade> GetTradeRecords(long uid,int status)throws SQLException,ClassNotFoundException {
        Class.forName(JDBC_PATH);
        try(Connection conn = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD)) {
            try(PreparedStatement ps = conn.prepareStatement("select tid, pid, uid_buyer,uid_seller,num,date_trade, cost, status from records_trade where status = ? ans uid=?")) {
                ps.setObject(1, status);
                ps.setObject(2, uid);
                try(ResultSet rs = ps.executeQuery()) {
                    ArrayList<Records_trade> trades  = new ArrayList<Records_trade>();
                    while(rs.next()) {
                        Records_trade trade = new Records_trade();
                        trade.setNum(rs.getInt("num"));
                        trade.setTid(rs.getLong("tid"));
                        trade.setPid(rs.getLong("pid"));
                        trade.setUid_buyer(rs.getLong("uid_buyer"));
                        trade.setUid_seller(rs.getLong("uid_seller"));
                        trade.setDate_trade(rs.getDate("date_trade"));
                        trade.setCost(rs.getInt("cost"));
                        trade.setStatus(rs.getInt("status"));
                        trades.add(trade);
                    }
                    return trades;
                }
            }
        }
    }

    public static ArrayList<Records_trade> GetTradeRecords(long uid,int status,int num, int page)throws SQLException,ClassNotFoundException {
        Class.forName(JDBC_PATH);
        try(Connection conn = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD)) {
            try(PreparedStatement ps = conn.prepareStatement("select tid, pid, uid_buyer,uid_seller,num,date_trade, cost, status from records_trade where status = ? ans uid=? limit ?,?")) {
                ps.setObject(1, status);
                ps.setObject(2, uid);
                ps.setObject(3, page);
                ps.setObject(3, num);
                try(ResultSet rs = ps.executeQuery()) {
                    ArrayList<Records_trade> trades  = new ArrayList<Records_trade>();
                    while(rs.next()) {
                        Records_trade trade = new Records_trade();
                        trade.setNum(rs.getInt("num"));
                        trade.setTid(rs.getLong("tid"));
                        trade.setPid(rs.getLong("pid"));
                        trade.setUid_buyer(rs.getLong("uid_buyer"));
                        trade.setUid_seller(rs.getLong("uid_seller"));
                        trade.setDate_trade(rs.getDate("date_trade"));
                        trade.setCost(rs.getInt("cost"));
                        trade.setStatus(rs.getInt("status"));
                        trades.add(trade);
                    }
                    return trades;
                }
            }
        }
    }


    public static ArrayList<Records_shopping_cart> GetShoppingCartRecords(long uid, long pid, int status)throws SQLException, ClassNotFoundException {
        Class.forName(JDBC_URL);
        try(Connection conn = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD)) {
            try(PreparedStatement ps = conn.prepareStatement("select sid, uid, pid, num, status from records_shopping_cart where status = ? and uid=? and pid=?")) {
                ps.setObject(1, status);
                ps.setObject(2, uid);
                ps.setObject(3, pid);
                try(ResultSet rs = ps.executeQuery()) {
                    ArrayList<Records_shopping_cart> shoppings = new ArrayList<Records_shopping_cart>();
                    while(rs.next()) {
                        Records_shopping_cart shopping = new Records_shopping_cart();
                        shopping.setNum(rs.getInt("int"));
                        shopping.setSid(rs.getLong("sid"));
                        shopping.setUid(rs.getLong("uid"));
                        shopping.setPid(rs.getLong("pid"));
                        shopping.setStatus(rs.getInt("status"));
                        shoppings.add(shopping);
                    }
                    return shoppings;

                }
            }
        }

    }

    public static ArrayList<Records_shopping_cart> GetShoppingCartRecordsNot(long uid, long pid, int status)throws SQLException, ClassNotFoundException {
        Class.forName(JDBC_URL);
        try(Connection conn = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD)) {
            try(PreparedStatement ps = conn.prepareStatement("select sid, uid, pid, num, status from records_shopping_cart where status = ? and uid=? and pid != ?")) {
                ps.setObject(1, status);
                ps.setObject(2, uid);
                ps.setObject(3, pid);
                try(ResultSet rs = ps.executeQuery()) {
                    ArrayList<Records_shopping_cart> shoppings = new ArrayList<Records_shopping_cart>();
                    while(rs.next()) {
                        Records_shopping_cart shopping = new Records_shopping_cart();
                        shopping.setNum(rs.getInt("int"));
                        shopping.setSid(rs.getLong("sid"));
                        shopping.setUid(rs.getLong("uid"));
                        shopping.setPid(rs.getLong("pid"));
                        shopping.setStatus(rs.getInt("status"));
                        shoppings.add(shopping);
                    }
                    return shoppings;

                }
            }
        }
    }

    public static int SetShoppingCartRecords(long pid, int new_status, int old_status)throws SQLException, ClassNotFoundException {
        Class.forName(JDBC_PATH);
        try(Connection conn = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD)) {
            try (PreparedStatement ps = conn.prepareStatement("update records_shopping_cart set status = ? where pid=? and status = ?")) {
                ps.setObject(1, new_status);
                ps.setObject(2, pid);
                ps.setObject(3, old_status);
                return ps.executeUpdate();

            } catch (Exception e) {
                e.printStackTrace();
                return Status.Status_Database.SYNAX_ERROR;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Status.Status_Database.CONN_FAILED;
        }

    }


    public static int SetShoppingCartRecordsNot(long pid, int new_status, int old_status)throws SQLException, ClassNotFoundException {
        Class.forName(JDBC_PATH);
        try(Connection conn = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD)) {
            try (PreparedStatement ps = conn.prepareStatement("update records_shopping_cart set status = ? where pid=? and status != ?")) {
                ps.setObject(1, new_status);
                ps.setObject(2, pid);
                ps.setObject(3, old_status);
                return ps.executeUpdate();

            } catch (Exception e) {
                e.printStackTrace();
                return Status.Status_Database.SYNAX_ERROR;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Status.Status_Database.CONN_FAILED;
        }

    }
}
