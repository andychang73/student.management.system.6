package com.abstractionizer.studentInformationSystem6.handlers;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@MappedTypes({Set.class})
@MappedJdbcTypes({JdbcType.VARCHAR})
public class GrantedAuthorityTypeHandler extends BaseTypeHandler<Set<GrantedAuthority>> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Set<GrantedAuthority> authorities, JdbcType jdbcType) throws SQLException {

    }

    @Override
    public Set<GrantedAuthority> getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return Arrays.stream(resultSet.getString(s).split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
    }

    @Override
    public Set<GrantedAuthority> getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return Arrays.stream(resultSet.getString(i).split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
    }

    @Override
    public Set<GrantedAuthority> getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return Arrays.stream(callableStatement.getString(i).split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
    }
}
