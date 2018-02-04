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
 * 请描述类 <br>
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
        String result = rs.getString(columnName);
        if(result != null && !result.equals("")) {
            try {
                return JsonUtils.parserString2CollectionWithType(result, List.class, getGeneric());
            } catch (IOException e) {
                logger.error("JsonArrayHandler转换异常, 请验证数据格式", e);
            }
        }
        return new ArrayList<>();
    }

    @Override
    public List<T> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String result = rs.getString(columnIndex);
        if(result != null && !result.equals("")) {
            try {
                return JsonUtils.parserString2CollectionWithType(result, List.class, getGeneric());
            } catch (IOException e) {
                logger.error("JsonArrayHandler转换异常, 请验证数据格式", e);
            }
        }
        return new ArrayList<>();
    }

    @Override
    public List<T> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String result = cs.getString(columnIndex);
        if(result != null && !result.equals("")) {
            try {
                return JsonUtils.parserString2CollectionWithType(result, List.class, getGeneric());
            } catch (IOException e) {
                logger.error("JsonArrayHandler转换异常, 请验证数据格式", e);
            }
        }
        return new ArrayList<>();
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
}
