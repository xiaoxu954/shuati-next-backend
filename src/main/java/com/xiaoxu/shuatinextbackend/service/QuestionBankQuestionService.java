package com.xiaoxu.shuatinextbackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoxu.shuatinextbackend.model.entity.QuestionBankQuestion;

/**
 * @author 16658
 * @description 针对表【question_bank_question(题库题目)】的数据库操作Service
 * @createDate 2025-04-07 14:47:48
 */
public interface QuestionBankQuestionService extends IService<QuestionBankQuestion> {
    /**
     * 校验
     *
     * @param questionBankQuestion
     * @param add
     */
    void validQuestionBankQuestion(QuestionBankQuestion questionBankQuestion, boolean add);


}
