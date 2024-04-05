package com.wzx;

import com.wzx.entity.Actor;
import com.wzx.mapper.ActorMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;

@SpringBootTest
public class SampleTest {

    @Autowired
    private ActorMapper actorMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<Actor> actorList = actorMapper.selectList(null);
        Assert.isTrue(!actorList.isEmpty(), "");
        actorList.forEach(System.out::println);
    }

}
