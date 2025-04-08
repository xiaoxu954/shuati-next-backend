package com.xiaoxu.shuatinextbackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoxu.shuatinextbackend.common.ErrorCode;
import com.xiaoxu.shuatinextbackend.exception.ThrowUtils;
import com.xiaoxu.shuatinextbackend.mapper.QuestionBankQuestionMapper;
import com.xiaoxu.shuatinextbackend.model.entity.Question;
import com.xiaoxu.shuatinextbackend.model.entity.QuestionBank;
import com.xiaoxu.shuatinextbackend.model.entity.QuestionBankQuestion;
import com.xiaoxu.shuatinextbackend.service.QuestionBankQuestionService;
import com.xiaoxu.shuatinextbackend.service.QuestionBankService;
import com.xiaoxu.shuatinextbackend.service.QuestionService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 16658
 * @description 针对表【question_bank_question(题库题目)】的数据库操作Service实现
 * @createDate 2025-04-07 14:47:48
 */
@Service
public class QuestionBankQuestionServiceImpl extends ServiceImpl<QuestionBankQuestionMapper, QuestionBankQuestion>
        implements QuestionBankQuestionService {

    @Resource
    @Lazy
    private QuestionService questionService;
    @Resource
    private QuestionBankService questionBankService;

    @Override
    public void validQuestionBankQuestion(QuestionBankQuestion questionBankQuestion, boolean add) {
        ThrowUtils.throwIf(questionBankQuestion == null, ErrorCode.PARAMS_ERROR);
        // 题目和题库必须存在
        Long questionId = questionBankQuestion.getQuestionId();
        if (questionId != null) {
            Question question = questionService.getById(questionId);
            ThrowUtils.throwIf(question == null, ErrorCode.NOT_FOUND_ERROR, "题目不存在");
        }
        Long questionBankId = questionBankQuestion.getQuestionBankId();
        if (questionBankId != null) {
            QuestionBank questionBank = questionBankService.getById(questionBankId);
            ThrowUtils.throwIf(questionBank == null, ErrorCode.NOT_FOUND_ERROR, "题库不存在");
        }
    }


}




