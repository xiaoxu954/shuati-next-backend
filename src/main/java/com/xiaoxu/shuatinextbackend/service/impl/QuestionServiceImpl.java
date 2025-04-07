package com.xiaoxu.shuatinextbackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoxu.shuatinextbackend.mapper.QuestionMapper;
import com.xiaoxu.shuatinextbackend.model.entity.Question;
import com.xiaoxu.shuatinextbackend.service.QuestionService;
import org.springframework.stereotype.Service;

/**
 * @author 16658
 * @description 针对表【question(题目)】的数据库操作Service实现
 * @createDate 2025-04-07 14:47:48
 */
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question>
        implements QuestionService {

}




