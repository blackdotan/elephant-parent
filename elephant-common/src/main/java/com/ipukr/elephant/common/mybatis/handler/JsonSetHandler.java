package com.ipukr.elephant.common.mybatis.handler;

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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Json数组对象Handler
 *
 * @author ryan.
 * <p>
 * Created by ryan on 2018/1/21.
 */
public abstract class JsonSetHandler<T> extends BaseTypeHandler<Set<T>>  {

    private static final Logger logger = LoggerFactory.getLogger(JsonSetHandler.class);

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Set<T> parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, JsonUtils.parserObj2String(parameter));
    }

    @Override
    public Set<T> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return convert(rs.getString(columnName));
    }

    @Override
    public Set<T> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return convert(rs.getString(columnIndex));

    }

    @Override
    public Set<T> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return convert(cs.getString(columnIndex));

    }

    @Override
    public Set<T> getResult(ResultSet rs, String columnName) throws SQLException {
        return getNullableResult(rs, columnName);
    }

    @Override
    public Set<T> getResult(ResultSet rs, int columnIndex) throws SQLException {
        return getNullableResult(rs, columnIndex);
    }


    public abstract Class<T> getGeneric();

    private Set<T> convert(String text) {
        if(text != null && !text.equals("")) {
            try {
                return JsonUtils.parserString2CollectionWithType(text, Set.class, getGeneric());
            } catch (Exception e) {
                logger.error("JsonSetHandler转换异常, 请验证数据格式", e);
            }
        }
        return new HashSet();
    }
}
