package com.wzx.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("actor")
public class Actor {
    @TableId
    private Long actorId;
    private String firstName;
    private String lastName;
    private LocalDateTime lastUpdate;
}
