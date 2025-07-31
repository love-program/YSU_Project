package com.qst.mapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.qst.domain.Student;
import com.qst.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class StudentMapperTest {

    @Autowired
    private StudentMapper studentMapper;

    @Test
    public void testSelect() {
        List<Student> students = studentMapper.selectList(null);
        for (Student student : students) {
            System.out.println(student);
        }
    }

    @Test
    public void testInsert() {
        Student student = new Student(11L, "大李", 18, "男", "软件工程2023",0);
        int result = studentMapper.insert(student);
        System.out.println(result);
        System.out.println(student);
    }

    @Test
    public void testDeleteById() {
        int result = studentMapper.deleteById(3L);
        System.out.println(result);
        System.out.println(studentMapper.selectList(null));
    }

    @Test
    public void testDeleteBatchIds() {
        int result = studentMapper.deleteBatchIds(Arrays.asList(1, 2, 3));
        System.out.println(result);
        System.out.println(studentMapper.selectList(null));
    }

    @Test
    public void test1()
    {
        //查询用户名包含东，年龄在30到50之间，并且邮箱不为null的用户信息
        //SELECT id,username AS name,age,email,is_deleted FROM t_user WHERE is_deleted=0 AND (username LIKE ? AND age BETWEEN ? AND ? AND email IS NOT NULL)
        QueryWrapper<Student> queryWrapper = new QueryWrapper<Student>();
        queryWrapper.like("student_name", "魔")
                .between("age", 10, 50)
                .isNotNull("class_name");
        List<Student> list = studentMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    public void test2()
    {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<Student>();
        queryWrapper.orderByAsc("age")
                .orderByDesc("uid");
        List<Student> list = studentMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }
    @Test
    public void test03() {
        //删除email为空的用户
        //DELETE FROM t_user WHERE (email IS NULL)
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("student_name", "魔");
        //条件构造器也可以构建删除语句的条件
        int result = studentMapper.delete(queryWrapper);
        System.out.println("受影响的行数：" + result);
    }

    @Test
    public void test04() {
        //查询用户名包含a，年龄在20-30之间，邮箱信息不为null的用户信息
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("student_name", "魔")
                .between("age", 20, 30)
                .isNotNull("email");
        List<Student> students = studentMapper.selectList(queryWrapper);
        students.forEach(System.out::println);
    }

    @Test
    public void test05() {
        //查询用户信息的username和age字段
        //SELECT username,age FROM t_user
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("student_name", "age");
        //selectMaps()返回Map集合列表，通常配合select()使用，避免User对象中没有被查询到的列值 为null
        List<Map<String, Object>> maps = studentMapper.selectMaps(queryWrapper);
        maps.forEach(System.out::println);
    }

    @Test
    public void test06() {
        //查询id小于等于3的用户信息
        //SELECT id,username AS name,age,email,is_deleted FROM t_user WHERE (id IN (select id from t_user where id <= 3))
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.inSql("uid", "select uid from student where uid > 3");
        List<Student> list = studentMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }
    @Test
    public void test07() {
        //将（年龄大于20或邮箱为null）并且用户名中包含有东的用户信息修改
        //组装set子句以及修改条件
        UpdateWrapper<Student> updateWrapper = new UpdateWrapper<>();
        //lambda表达式内的逻辑优先运算
        updateWrapper
                .set("age", 18)
                .set("class_name", "软件工程2023")
                .and(i -> i.gt("age", 10).or().isNull("gender"));
        int result = studentMapper.update(null, updateWrapper);
        System.out.println(result);
    }
}