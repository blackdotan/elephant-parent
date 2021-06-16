package com.blackdotan.elephant.common.mybatis.handler;

import com.blackdotan.elephant.utils.JsonUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Json数组对象Handler
 *
 * @author ryan.
 * <p>
 * Created by ryan on 2018/1/21.
 */
public abstract class JsonArrayHandler<T> extends BaseTypeHandler<List<T>>  {

    private static final Logger logger = LoggerFactory.getLogger(JsonArrayHandler.class);

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<T> parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, JsonUtils.parserObj2String(parameter));
    }

    @Override
    public List<T> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return convert(rs.getString(columnName));
    }

    @Override
    public List<T> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {

        return convert(rs.getString(columnIndex));
    }

    @Override
    public List<T> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return convert(cs.getString(columnIndex));
    }

    @Override
    public List<T> getResult(ResultSet rs, String columnName) throws SQLException {
        return getNullableResult(rs, columnName);
    }

    @Override
    public List<T> getResult(ResultSet rs, int columnIndex) throws SQLException {
        return getNullableResult(rs, columnIndex);
    }


    public abstract Class<T> getGeneric();

    private List<T> convert(String text) {
        if(text != null && !text.equals("")) {
            List<T> ts = null;
            try {
                ts = JsonUtils.parserString2CollectionWithType(text, List.class, getGeneric());
                return ts;
            } catch (Exception e) {
                logger.error("JsonArrayHandler转换异常, 请验证数据格式", e);
            }
        }
        return new ArrayList<>();
    }
}
