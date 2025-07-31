package com.qst.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Student {
    @TableId(value = "uid",type = IdType.AUTO)
    private Long uid;

    @TableField(value = "student_name")
    private String studentName;
    private Integer age;
    private String gender;
    private String class_name;

    @TableLogic
    private Integer is_deleted;
}
