package com.blackdotan.elephant.common.mybatis.handler;

import com.blackdotan.elephant.common.EnumFactory;
import com.blackdotan.elephant.common.Identifiable;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 字符型枚举对象Handler
 *
 * @author ryan.
 * <p>
 * Created by ryan on 2018/1/21.
 */
public abstract class CharacterToEnumHandler<T extends Enum<T> & Identifiable<String>> extends BaseTypeHandler<T>  {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, T parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getId());
    }

    @Override
    public T getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String result = rs.getString(columnName);
        if( result!=null ) {
            return EnumFactory.findAccordingValue(getGeneric(), result);
        }
        return null;
    }

    @Override
    public T getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String result = rs.getString(columnIndex);
        if( result!=null ) {
            return EnumFactory.findAccordingValue(getGeneric(), result);
        }
        return null;
    }

    @Override
    public T getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String result = cs.getString(columnIndex);
        if( result!=null ) {
            return EnumFactory.findAccordingValue(getGeneric(), result);
        }
        return null;
    }

    @Override
    public T getResult(ResultSet rs, String columnName) throws SQLException {
        return getNullableResult(rs, columnName);
    }

    @Override
    public T getResult(ResultSet rs, int columnIndex) throws SQLException {
        return getNullableResult(rs, columnIndex);
    }


    public abstract Class<T> getGeneric();
}
