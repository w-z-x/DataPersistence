package com.wzx.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzx.entity.Actor;
import com.wzx.service.ActorService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("actor")
public class ActorController {
    @Resource
    private ActorService actorService;

    @GetMapping("page")
    public IPage<Actor> page(@RequestBody Page<Actor> page) {
        return actorService.page(page);
    }
}
