package com.xiaoxu.shuatinextbackend.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoxu.shuatinextbackend.common.ErrorCode;
import com.xiaoxu.shuatinextbackend.constant.CommonConstant;
import com.xiaoxu.shuatinextbackend.exception.BusinessException;
import com.xiaoxu.shuatinextbackend.exception.ThrowUtils;
import com.xiaoxu.shuatinextbackend.mapper.QuestionBankMapper;
import com.xiaoxu.shuatinextbackend.model.dto.questionbank.QuestionBankQueryRequest;
import com.xiaoxu.shuatinextbackend.model.entity.QuestionBank;
import com.xiaoxu.shuatinextbackend.model.vo.QuestionBankVO;
import com.xiaoxu.shuatinextbackend.service.QuestionBankService;
import com.xiaoxu.shuatinextbackend.utils.SqlUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
        if (questionBank == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String title = questionBank.getTitle();
        String description = questionBank.getDescription();
        String picture = questionBank.getPicture();

        // 创建时，参数不能为空
        if (add) {
            ThrowUtils.throwIf(StringUtils.isAnyBlank(title, description, picture), ErrorCode.PARAMS_ERROR);
        }
        // 有参数则校验
        if (StringUtils.isNotBlank(title) && title.length() > 80) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "标题过长");
        }
        if (StringUtils.isNotBlank(description) && description.length() > 8192) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "描述过长");
        }

    }

    @Override
    public QueryWrapper<QuestionBank> getQueryWrapper(QuestionBankQueryRequest questionBankQueryRequest) {
        if (questionBankQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求参数为空");
        }
        Long id = questionBankQueryRequest.getId();
        String title = questionBankQueryRequest.getTitle();
        String description = questionBankQueryRequest.getDescription();
        String picture = questionBankQueryRequest.getPicture();
        String sortField = questionBankQueryRequest.getSortField();
        String sortOrder = questionBankQueryRequest.getSortOrder();

        QueryWrapper<QuestionBank> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(id != null, "id", id);
        queryWrapper.eq(StringUtils.isNotBlank(title), "title", title);
        queryWrapper.eq(StringUtils.isNotBlank(description), "description", description);
        queryWrapper.eq(StringUtils.isNotBlank(picture), "picture", picture);

        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC),
                sortField);
        return queryWrapper;

    }

    @Override
    public QuestionBankVO getQuestionBankVO(QuestionBank questionBank, HttpServletRequest request) {
        if (questionBank == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        QuestionBankVO questionBankVO = new QuestionBankVO();
        BeanUtils.copyProperties(questionBank, questionBankVO);
        return questionBankVO;
    }

    @Override
    public Page<QuestionBank> listQuestionBankByPage(QuestionBankQueryRequest questionBankQueryRequest) {

        if (questionBankQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        QueryWrapper<QuestionBank> queryWrapper = getQueryWrapper(questionBankQueryRequest);

        return this.page(new Page<>(questionBankQueryRequest.getCurrent(), questionBankQueryRequest.getPageSize()), queryWrapper);
    }

    @Override
    public Page<QuestionBankVO> getQuestionBankVOPage(Page<QuestionBank> questionBankPage, HttpServletRequest request) {
        List<QuestionBank> questionBanks = questionBankPage.getRecords();

        Page<QuestionBankVO> questionBankVOPage = new Page<>(questionBankPage.getCurrent(), questionBankPage.getSize(), questionBankPage.getTotal());
        if (CollUtil.isEmpty(questionBanks)) {
            return questionBankVOPage;
        }
        // 实现 QuestionBank 到 QuestionBankVO 的转换逻辑
        List<QuestionBankVO> newQuestionVO = questionBanks.stream().filter(Objects::nonNull)
                .map(questionBank -> {
                    QuestionBankVO vo = new QuestionBankVO();
                    // 假设 QuestionVO 需要从 Question 复制字段
                    BeanUtils.copyProperties(questionBank, vo);
                    return vo;
                })
                .collect(Collectors.toList());
        questionBankVOPage.setRecords(newQuestionVO);
        return questionBankVOPage;
    }


}





