package com.ipukr.elephant.common.mybatis.handler;

import com.ipukr.elephant.common.EnumFactory;
import com.ipukr.elephant.common.Identifiable;
import com.ipukr.elephant.utils.JsonUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Json对象Handler
 *
 * @author ryan.
 * <p>
 * Created by ryan on 2018/1/21.
 */
public abstract class JsonObjectHandler<T extends Object> extends BaseTypeHandler<T>  {

    private static final Logger logger = LoggerFactory.getLogger(JsonObjectHandler.class);

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, T parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, JsonUtils.parserObj2String(parameter));
    }

    @Override
    public T getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return convert(rs.getString(columnName));
    }

    @Override
    public T getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return convert(rs.getString(columnIndex));
    }

    @Override
    public T getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return convert(cs.getString(columnIndex));
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

    private T convert(String text) {
        if(text != null && !text.equals("")) {
            try {
                if (JsonUtils.validate(text, getGeneric())) {
                    return JsonUtils.parserString2Obj(text, getGeneric());
                }
            } catch (Exception e) {
                logger.error("Json转换异常", e);
            }
        }
        return null;
    }
}
