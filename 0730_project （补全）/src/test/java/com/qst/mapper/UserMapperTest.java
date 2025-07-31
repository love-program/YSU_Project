package com.qst.mapper;
import com.qst.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void testSelect() {
        List<User> users = userMapper.selectList(null);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testInsert()
    {
        User user = new User(3, "灵珠", 10, "lz@163.com");

        int result = userMapper.insert(user);
        System.out.println(result);
        System.out.println("Id为："+user.getId());
    }

    @Test
    public void testDelete()
    {
        int result = userMapper.deleteById(6);
        System.out.println(result);
        System.out.println("successful delete");
    }

    @Test
    public void testDeleteBatchIds(){
        List<Long> list = Arrays.asList(1L,2L,3L);
        int result = userMapper.deleteBatchIds(list);
        System.out.println(result);
        System.out.println("successful delete");
    }

    @Test
    public void testDeleteByMap() {
        //根据map集合中所设置的条件删除记录
        //DELETE FROM user WHERE name = ? AND age = ?
        Map<String, Object> map = new HashMap<>();
        map.put("age", 48);
        map.put("name", "岳不群");

        int result = userMapper.deleteByMap(map);
        System.out.println("受影响行数：" + result);
    }

    @Test
    public void testUpdateById()
    {
        int result = userMapper.updateById(new User(4, "小王", 18, "mowan@qq.com"));
        System.out.println("受影响行数：" + result);
    }

    @Test
    public void testSelectById()
    {
        User user = userMapper.selectById(4);
        System.out.println(user);
    }

    @Test
    public void testSelectBatchIds()
    {
        List<Integer> list = Arrays.asList(1, 2, 3);
        List<User> users = userMapper.selectBatchIds(list);
        users.forEach(System.out::println);
    }

    @Test
    public void testSelectByMap() {

        Map<String, Object> map = new HashMap<>();
        map.put("age", 40);
        map.put("name", "申公豹");
        List<User> list = userMapper.selectByMap(map);
        list.forEach(System.out::println);
    }

    @Test
    public void testSelectList()
    {
        List<User> list = userMapper.selectList(null);
        list.forEach(System.out::println);
    }
}