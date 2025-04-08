package com.xiaoxu.shuatinextbackend.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoxu.shuatinextbackend.model.dto.questionbank.QuestionBankQueryRequest;
import com.xiaoxu.shuatinextbackend.model.entity.QuestionBank;
import com.xiaoxu.shuatinextbackend.model.vo.QuestionBankVO;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 16658
 * @description 针对表【question_bank(题库)】的数据库操作Service
 * @createDate 2025-04-07 14:47:48
 */
public interface QuestionBankService extends IService<QuestionBank> {
    /**
     * 校验
     *
     * @param questionBank
     * @param add
     */
    void validQuestionBank(QuestionBank questionBank, boolean add);

    /**
     * 获取查询条件
     *
     * @param questionBankQueryRequest
     * @return
     */
    QueryWrapper<QuestionBank> getQueryWrapper(QuestionBankQueryRequest questionBankQueryRequest);


    /**
     * 获取题库封装
     *
     * @param questionBank
     * @param request
     * @return
     */
    QuestionBankVO getQuestionBankVO(QuestionBank questionBank, HttpServletRequest request);

    /**
     * 分页获取题库列表
     * @param questionBankQueryRequest
     * @return
     */
    Page<QuestionBank> listQuestionBankByPage(QuestionBankQueryRequest questionBankQueryRequest);
    /**
     * 分页获取题库封装
     *
     * @param questionBankPage
     * @param request
     * @return
     */
    Page<QuestionBankVO> getQuestionBankVOPage(Page<QuestionBank> questionBankPage, HttpServletRequest request);
}