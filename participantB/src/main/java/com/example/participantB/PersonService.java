package com.example.participantB;

import com.example.participantB.dao.PersonDao;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: Halo
 * @date: 2019/5/25 18:28
 * @Description:
 */
@Service
//@Transactional
public class PersonService {
    @Autowired
    PersonDao persionDao;
    @Autowired
    DataSourceTransactionManager transactionManager;
    public static Map<String,Connection> map = new ConcurrentHashMap();
    public void insert() {
        this.persionDao.insertP();
//        throw new RuntimeException();
    }
    public void testtx(String uuid){
        Connection connection = null;
        try {
            connection = transactionManager.getDataSource().getConnection();
            connection.setAutoCommit(false);
            CallableStatement callableStatement = connection.prepareCall("INSERT INTO test (`id`, `name`) VALUES ('1', '2')");
//            int o = 1/0;
            map.putIfAbsent(uuid,connection);
            callableStatement.execute();
//            callableStatement.execute();
//            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new RuntimeException();
            }
        }
//        finally {
//            try {
//                    connection.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//            }
//        }
    }

    public void testCommit(String id){
        Connection conn = map.get(id);
        try {
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
                throw new RuntimeException("");
            }finally {
                try {
                    conn.close();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            throw new RuntimeException("");
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void testRollback(String id){
        Connection conn = map.get(id);
        try {
            conn.rollback();
        } catch (SQLException e1) {
            e1.printStackTrace();
            throw new RuntimeException("");
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
