package com.nicomincuzzi.frameworkless.dao;

import com.nicomincuzzi.frameworkless.domain.repository.ResultEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ResultDao extends BaseDao {
    private static final String TABLE_NAME = "result";
    private static final String ROOM_ID = "room_id";
    private static final String ROOM_NAME = "room_name";
    private static final String ITEM = "item";
    private static final String USER_ID = "user_id";

    private final String INSERT_INTO_RESULT = buildQuery(
            "INSERT INTO @ (@, @, @, @) VALUES (?, ?, ?, ?);", TABLE_NAME, ROOM_ID, ROOM_NAME, ITEM, USER_ID
    );

    private final String SELECT_BY_USER_ID = buildQuery(
            "SELECT @, @, @, @ FROM @ WHERE @ = ?", ROOM_ID, ROOM_NAME, ITEM, USER_ID, TABLE_NAME, USER_ID
    );

    private final Connection connection;

    public ResultDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(ResultEntity resultEntity) {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_INTO_RESULT)) {
            statement.setInt(1, resultEntity.getRoomId());
            statement.setString(2, resultEntity.getRoomName());
            statement.setString(3, resultEntity.getItem());
            statement.setString(4, resultEntity.getUserId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ResultEntity> readBy(String userId) throws Exception {
        try (PreparedStatement st = connection.prepareStatement(SELECT_BY_USER_ID)) {
            st.setString(1, userId);
            ResultSet resultSet = st.executeQuery();

            while (resultSet.next()) {
                System.out.println(resultSet.getString(1));
            }
            resultSet.close();
            return null;
        } catch (SQLException throwables) {
            throw new Exception();
        }
    }
}
