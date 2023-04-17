package com.freanchlearning.models.dao;

import com.freanchlearning.models.models.Teacher;

import java.util.Optional;

public class DaoRunner {
    public static void main(String[] args) {
//        deleteTest();
//        updateTest();
//        findAllTest();

    }

    private static void findAllTest() {
        var teacherDao = TeacherDao.getInstance();
        var teacher = teacherDao.findAll();
        System.out.println(teacher);
    }

    private static void updateTest() {
        var teacherDao = TeacherDao.getInstance();
        var teacher = teacherDao.findById(1L);
        System.out.println(teacher);

        teacher.ifPresent(x -> {
            x.setEmail("stpan.petrov@mail.com");
            teacherDao.update(x);
        });
    }

    private static void deleteTest() {
        var teacherDao = TeacherDao.getInstance();
        var teacher = new Teacher();
        teacher.setFirstName("Ivan");
        teacher.setLastName("Ivanov");
        teacher.setEmail("ivan.ivanov@mail.ru");
        teacher.setPhoneNumber("123-345");
        teacher.setQualification("Professor");

        teacherDao.delete(3L);
    }
}
