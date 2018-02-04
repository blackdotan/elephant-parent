package com.ipukr.elephant.common.mybatis.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * 基础类型集合类型 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2018/2/5.
 */
public class SerializableSetHandler<T extends Serializable> extends BaseTypeHandler<Set<T>> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Set<T> parameter, JdbcType jdbcType) throws SQLException {
        StringBuffer buffer = new StringBuffer();
        if(!parameter.isEmpty()) {
            for(T t : parameter) {
                buffer.append(buffer).append(",");
            }
            buffer.deleteCharAt(buffer.length()-1);
        }
        ps.setString(i, buffer.toString());
    }

    @Override
    public Set<T> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String text = rs.getString(columnName);
        Set<T> set = new HashSet<T>();
        if(text!=null && !text.equals("")) {
            for(String item : text.split(",")) {
                set.add((T) item);
            }
        }
        return set;
    }

    @Override
    public Set<T> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String text = rs.getString(columnIndex);
        Set<T> set = new HashSet<T>();
        if(text!=null && !text.equals("")) {
            for(String item : text.split(",")) {
                set.add((T) item);
            }
        }
        return set;
    }

    @Override
    public Set<T> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String text = cs.getString(columnIndex);
        Set<T> set = new HashSet<T>();
        if(text!=null && !text.equals("")) {
            for(String item : text.split(",")) {
                set.add((T) item);
            }
        }
        return set;
    }

    @Override
    public Set<T> getResult(ResultSet rs, String columnName) throws SQLException {
        return getResult(rs, columnName);
    }

    @Override
    public Set<T> getResult(ResultSet rs, int columnIndex) throws SQLException {
        return getResult(rs, columnIndex);
    }

    @Override
    public Set<T> getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return getResult(cs, columnIndex);
    }
}
