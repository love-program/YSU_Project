package com.qst.mapper;
import com.qst.domain.Student;
import com.qst.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

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

}