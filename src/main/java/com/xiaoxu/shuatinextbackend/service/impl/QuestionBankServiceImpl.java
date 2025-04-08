package com.xiaoxu.shuatinextbackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoxu.shuatinextbackend.mapper.QuestionBankMapper;
import com.xiaoxu.shuatinextbackend.model.dto.questionbank.QuestionBankQueryRequest;
import com.xiaoxu.shuatinextbackend.model.entity.QuestionBank;
import com.xiaoxu.shuatinextbackend.model.vo.QuestionBankVO;
import com.xiaoxu.shuatinextbackend.service.QuestionBankService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 16658
 * @description 针对表【question_bank(题库)】的数据库操作Service实现
 * @createDate 2025-04-07 14:47:48
 */
@Service
public class QuestionBankServiceImpl extends ServiceImpl<QuestionBankMapper, QuestionBank>
        implements QuestionBankService {


    @Override
    public void validQuestionBank(QuestionBank questionBank, boolean add) {

    }

    @Override
    public QueryWrapper<QuestionBank> getQueryWrapper(QuestionBankQueryRequest questionBankQueryRequest) {
        return null;
    }

    @Override
    public QuestionBankVO getQuestionBankVO(QuestionBank questionBank, HttpServletRequest request) {
        QuestionBankVO questionBankVO = new QuestionBankVO();
        BeanUtils.copyProperties(questionBank, questionBankVO);
        return questionBankVO;


    }

    @Override
    public Page<QuestionBank> listQuestionBankByPage(QuestionBankQueryRequest questionBankQueryRequest) {
        return null;
    }

    @Override
    public Page<QuestionBankVO> getQuestionBankVOPage(Page<QuestionBank> questionBankPage, HttpServletRequest request) {
        return null;
    }
}




