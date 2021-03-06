package main.com.spbstu.storage.project;

import main.com.spbstu.project.Lesson;
import main.com.spbstu.project.Notification;
import main.com.spbstu.project.Request;
import main.com.spbstu.storage.DataGateway;
import main.com.spbstu.storage.Mapper;
import main.com.spbstu.user.User;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LessonMapper implements Mapper<Lesson> {
    private static Set<Lesson> lessons = new HashSet<>();
    private Connection connection;

    public LessonMapper() throws SQLException, IOException {
        DataGateway gateway = DataGateway.getInstance();
        connection = gateway.getDataSource().getConnection();
    }

    public int addLesson(Lesson lesson) throws SQLException {
        String insertSQL = "INSERT INTO LESSONS(id_teacher, theme, status, commentary, date_of_lesson) VALUES (?, ?, ?, ?, ?);";
        PreparedStatement insertStatement = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
        insertStatement.setInt(1, lesson.getIdTeacher());
        insertStatement.setString(2, lesson.getTheme());
        insertStatement.setString(3, lesson.getStatus());
        insertStatement.setString(4, "");
        insertStatement.setDate(5, lesson.getDateOfLesson());
        insertStatement.execute();
        ResultSet rs = insertStatement.getGeneratedKeys();
        if (rs.next()) {
            long id = rs.getLong(1);
            lesson.setId((int) id);
        }
        return lesson.getId();
    }

    public Integer findIDLesson(int idTeacher, Date date) throws SQLException {
        String lessonSelectStatement = "SELECT id FROM LESSONS WHERE id_teacher = ? AND date_of_lesson = ?;";
        PreparedStatement extractLessonStatement = connection.prepareStatement(lessonSelectStatement);
        extractLessonStatement.setInt(1, idTeacher);
        extractLessonStatement.setDate(2, (java.sql.Date) date);
        ResultSet rs = extractLessonStatement.executeQuery();
        if (!rs.next()) return null;
        return rs.getInt("id");

    }

    public Lesson findById (int id) throws SQLException {
        for (Lesson it : lessons) {
            if (it.getId()==(id))
                return it;
        }

        String complaintsSelectStatement = "SELECT * FROM LESSONS WHERE id = ?;";
        PreparedStatement extractLessonsStatement = connection.prepareStatement(complaintsSelectStatement);
        extractLessonsStatement.setInt(1, id);
        ResultSet rs = extractLessonsStatement.executeQuery();

        if (!rs.next()) return null;
        String theme = rs.getString("theme");
        int idTeacher = rs.getInt("id_teacher");
        String commentary = rs.getString("commentary");
        String status = rs.getString("status");
        Date dateOfLesson = rs.getDate("date_of_lesson");
        Lesson newLesson = new Lesson(id, idTeacher, theme, commentary, status, (java.sql.Date) dateOfLesson);
        lessons.add(newLesson);

        return newLesson;
    }

    public List<Lesson> findAll() throws SQLException {
        List<Lesson> all = new ArrayList<>();

        String lessonSelectStatement = "SELECT id FROM lessons;";
        Statement extractLessonStatement = connection.createStatement();
        ResultSet rs = extractLessonStatement.executeQuery(lessonSelectStatement);

        while (rs.next()) {
            all.add(findById(rs.getInt("id")));
        }
        return all;
    }
    @Override
    public void update(Lesson lesson) throws SQLException {
       /* if (!(lessons.contains(lesson))) {
            addLesson(lesson);
            lessons.add(lesson);
        } else {*/
            String updateSQL = "UPDATE LESSONS set theme = ?, commentary = ?, status = ?  WHERE id = ?;";
            PreparedStatement updateStatus = connection.prepareStatement(updateSQL);
            updateStatus.setString(1, lesson.getTheme());
            updateStatus.setString(2, lesson.getCommentary());
            updateStatus.setString(3, lesson.getStatus());
            updateStatus.setInt(4, lesson.getId());
            updateStatus.execute();
            lessons.clear();
            List<Lesson> list = findAll();
            for (Lesson it : list) {
            lessons.add(it);
        }
    }

    @Override
    public void update() throws SQLException {
        for (Lesson it : lessons)
        update(it);
    }

    @Override
    public void clear() {
        lessons.clear();
    }
}

