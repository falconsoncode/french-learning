package com.freanchlearning.models.dao;

import com.freanchlearning.models.exception.DaoException;
import com.freanchlearning.models.models.Teacher;
import com.freanchlearning.models.util.ConnectionManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TeacherDao {

    private static final TeacherDao INSTANCE = new TeacherDao();
    public static final String DELETE_SQL = """
            DELETE FROM teacher
            WHERE id = ?
            """;

    public static final String SAVE_SQL = """
            INSERT INTO teacher (first_name, last_name, email, phone_number, qualification)
            VALUES (?, ?, ?, ?, ?);
            """;

    public static final String UPDATE_SQL = """
            UPDATE teacher
            SET first_name = ?,
                last_name = ?,
                email = ?,
                phone_number = ?,
                qualification = ?
            WHERE id = ?
            """;

    public static final String FIND_ALL_SQL = """
            SELECT id, first_name, last_name, email, phone_number, qualification
            FROM teacher
            """;

    public static final String FIND_BY_ID_SQL = FIND_ALL_SQL + """
            WHERE id = ?
            """;


    private TeacherDao() {
    }

    public List<Teacher> findAll() {
        try (var connection = ConnectionManager.get()) {
            var preparedStatement = connection.prepareStatement(FIND_ALL_SQL);
            var resultSet = preparedStatement.executeQuery();
            List<Teacher> teachers = new ArrayList<>();
            while (resultSet.next()) {
                teachers.add(buildTeacher(resultSet));
            }
            return teachers;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public Optional<Teacher> findById(Long id) {
        try (var connection = ConnectionManager.get()) {
            var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL);
            preparedStatement.setLong(1, id);

            var resultSet = preparedStatement.executeQuery();
            Teacher teacher = null;
            if (resultSet.next()) {
                teacher = buildTeacher(resultSet);
            }

            return Optional.ofNullable(teacher);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private static Teacher buildTeacher(ResultSet resultSet) throws SQLException {
        return new Teacher(
                resultSet.getLong("id"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name"),
                resultSet.getString("email"),
                resultSet.getString("phone_number"),
                resultSet.getString("qualification")
        );
    }

    public void update(Teacher teacher) {
        try (var connection = ConnectionManager.get()) {
            var preparedStatement = connection.prepareStatement(UPDATE_SQL);
            preparedStatement.setString(1, teacher.getFirstName());
            preparedStatement.setString(2, teacher.getLastName());
            preparedStatement.setString(3, teacher.getEmail());
            preparedStatement.setString(4, teacher.getPhoneNumber());
            preparedStatement.setString(5, teacher.getQualification());
            preparedStatement.setLong(6, teacher.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }

    }

    public Teacher save(Teacher teacher) {
        try (var connection = ConnectionManager.get()) {
            var preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, teacher.getFirstName());
            preparedStatement.setString(2, teacher.getLastName());
            preparedStatement.setString(3, teacher.getEmail());
            preparedStatement.setString(4, teacher.getPhoneNumber());
            preparedStatement.setString(5, teacher.getQualification());

            preparedStatement.executeUpdate();
            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                teacher.setId(generatedKeys.getLong("id"));
            }
            return teacher;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public boolean delete(Long id) {
        try (var connection = ConnectionManager.get()) {
            var preparedStatement = connection.prepareStatement(DELETE_SQL);
            preparedStatement.setLong(1, id);

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public static TeacherDao getInstance() {
        return INSTANCE;
    }
}
