package com.example.participantC.tx;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.support.AbstractPlatformTransactionManager;
import org.springframework.transaction.support.DefaultTransactionStatus;
import org.springframework.transaction.support.ResourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author: Halo
 * @date: 2019/5/15 16:20
 * @Description:
 */
public class TwoPcTransactionManager extends AbstractPlatformTransactionManager implements ResourceTransactionManager, InitializingBean {
    private DataSource dataSource;

    @Override
    protected Object doGetTransaction() throws TransactionException {
        return null;
    }

    @Override
    protected void doBegin(Object o, TransactionDefinition transactionDefinition) throws TransactionException {

    }

    @Override
    protected void doCommit(DefaultTransactionStatus defaultTransactionStatus) throws TransactionException {

    }

    @Override
    protected void doRollback(DefaultTransactionStatus defaultTransactionStatus) throws TransactionException {

    }


    public TwoPcTransactionManager(){

    }
    public TwoPcTransactionManager(DataSource dataSource) {
        this();
        setDataSource(dataSource);
        afterPropertiesSet();
    }
    //设置数据源
    public void setDataSource(DataSource dataSource) {
        if (dataSource instanceof TransactionAwareDataSourceProxy) {
            //如果数据源是一个事务包装数据源代理，则获取事务包装代理的目标数据源
            this.dataSource = ((TransactionAwareDataSourceProxy) dataSource).getTargetDataSource();
        }
        else {
            this.dataSource = dataSource;
        }
    }
    //获取数据源
    public DataSource getDataSource() {
        return this.dataSource;
    }
    //数据源事务处理器对象构造方法的回调函数
    @Override
    public void afterPropertiesSet() {
        if (getDataSource() == null) {
            throw new IllegalArgumentException("Property 'dataSource' is required");
        }
    }
    @Override
    public Object getResourceFactory() {
        return getDataSource();
    }
}
