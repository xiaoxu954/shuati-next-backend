package com.xiaoxu.shuati.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoxu.shuati.mapper.QuestionMapper;
import com.xiaoxu.shuati.model.entity.Question;
import com.xiaoxu.shuati.service.QuestionService;
import org.springframework.stereotype.Service;

/**
 * @author 16658
 * @description 针对表【question(题目)】的数据库操作Service实现
 * @createDate 2025-04-01 18:17:35
 */
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question>
        implements QuestionService {

}




