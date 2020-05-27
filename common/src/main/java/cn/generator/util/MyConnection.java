package cn.generator.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 喻浩
 * @create 2020-05-26-7:28
 */
public class MyConnection {
    private final static String url = "jdbc:mysql://47.102.36.32:13306/test?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true";
    private final static String driverClassName = "com.mysql.jdbc.Driver";
    private static Connection conn;
    public static void main(String[] args) throws Exception {
        Map<String,String > map = new HashMap<>();
        map.put(DruidDataSourceFactory.PROP_USERNAME,"root");
        map.put(DruidDataSourceFactory.PROP_PASSWORD,"root");
        map.put(DruidDataSourceFactory.PROP_URL,url);
        map.put(DruidDataSourceFactory.PROP_DRIVERCLASSNAME,driverClassName);
        DataSource source= DruidDataSourceFactory.createDataSource(map);
        conn = source.getConnection();
        // 1、获取数据库所有表
        StringBuffer sbTables = new StringBuffer();
        List<String> tables = new ArrayList<String>();
        try {
            DatabaseMetaData dbMetaData = conn.getMetaData();
            ResultSet rs = dbMetaData.getTables(null, null, null,new String[] { "TABLE" });
            while (rs.next()) {// ///TABLE_TYPE/REMARKS
                sbTables.append("表名：").append(rs.getString("TABLE_NAME")).append("\n");
//                sbTables.append("表类型：" + rs.getString("TABLE_TYPE") + "<br/>");
//                sbTables.append("表所属数据库：" + rs.getString("TABLE_CAT") + "<br/>");
//                sbTables.append("表所属用户名：" + rs.getString("TABLE_SCHEM")+ "<br/>");
//                sbTables.append("表备注：" + rs.getString("REMARKS") + "<br/>");
                tables.add(rs.getString("TABLE_NAME"));
            }
        } catch ( SQLException e) {
            e.printStackTrace();
        }

        System.out.println(sbTables);

        test(tables);

        // 2、遍历数据库表，获取各表的字段等信息
//        StringBuffer sbCloumns = new StringBuffer();
//        String tableName = "file_save";
////        for (String tableName : tables) {
//            String sql = "select * from " + tableName;
//            try {
//                PreparedStatement ps = conn.prepareStatement(sql);
//                ResultSet rs = ps.executeQuery();
//                ResultSetMetaData meta = rs.getMetaData();
//                int columeCount = meta.getColumnCount();
//                sbCloumns.append("表 ").append(tableName).append("共有 ").append(columeCount).append(" 个字段。字段信息如下：<br/>");
//                for (int i = 1; i < columeCount + 1; i++) {
//                    sbCloumns.append("字段名：").append(meta.getColumnName(i)).append("\n");
//                    sbCloumns.append("类型：").append(meta.getColumnType(i)).append("\n");
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
////        }
//
//        System.out.println(sbCloumns);

        conn.close();

    }

    public static void test(List<String> tableNames) throws SQLException {
        DatabaseMetaData metaData = conn.getMetaData();
        for (String tableName : tableNames) {
            System.out.println("----------表名-----"+tableName);
            //通过表名获得所有字段名
            ResultSet columns = metaData.getColumns(null, null, tableName, "%");
            //获得所有字段名
            while (columns.next()) {
                //获得字段名
                String column_name = columns.getString("COLUMN_NAME");
                //获得字段类型
                String type_name = columns.getString("TYPE_NAME");

                System.out.println(column_name + type_name);
            }
        }

    }


}
