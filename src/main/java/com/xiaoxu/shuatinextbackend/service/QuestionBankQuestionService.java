package com.xiaoxu.shuatinextbackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoxu.shuatinextbackend.model.entity.QuestionBankQuestion;
import com.xiaoxu.shuatinextbackend.model.entity.User;

import java.util.List;

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

    /**
     * 批量添加题目到题库
     *
     * @param questionIdList
     * @param questionBankId
     * @param loginUser
     */
    void batchAddQuestionsToBank(List<Long> questionIdList, Long questionBankId, User loginUser);

    /**
     * 批量从题库移除题目
     *
     * @param questionIdList
     * @param questionBankId
     */
    void batchRemoveQuestionsFromBank(List<Long> questionIdList, Long questionBankId);

}
