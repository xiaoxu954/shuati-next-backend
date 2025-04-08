package com.xiaoxu.shuatinextbackend.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xiaoxu.shuatinextbackend.annotation.AuthCheck;
import com.xiaoxu.shuatinextbackend.common.BaseResponse;
import com.xiaoxu.shuatinextbackend.common.ErrorCode;
import com.xiaoxu.shuatinextbackend.common.ResultUtils;
import com.xiaoxu.shuatinextbackend.constant.UserConstant;
import com.xiaoxu.shuatinextbackend.exception.BusinessException;
import com.xiaoxu.shuatinextbackend.exception.ThrowUtils;
import com.xiaoxu.shuatinextbackend.model.dto.questionbankquestion.QuestionBankQuestionAddRequest;
import com.xiaoxu.shuatinextbackend.model.dto.questionbankquestion.QuestionBankQuestionRemoveRequest;
import com.xiaoxu.shuatinextbackend.model.entity.QuestionBankQuestion;
import com.xiaoxu.shuatinextbackend.model.entity.User;
import com.xiaoxu.shuatinextbackend.service.QuestionBankQuestionService;
import com.xiaoxu.shuatinextbackend.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 题库题目关系接口
 */
@Api(tags = "题库题目关系接口")
@RestController
@RequestMapping("/questionBankQuestion")
@Slf4j
public class QuestionBankQuestionController {

    @Resource
    private UserService userService;

    @Resource
    private QuestionBankQuestionService questionBankQuestionService;


    // region 增删改查

    /**
     * 添加题库题目关系
     *
     * @param questionBankAddRequest
     * @param request
     * @return
     */
    @ApiOperation(value = "添加题库题目关系")
    @PostMapping("/add")
    public BaseResponse<Long> addQuestionBankQuestion(@RequestBody QuestionBankQuestionAddRequest questionBankAddRequest, HttpServletRequest request) {
        if (questionBankAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        QuestionBankQuestion question = new QuestionBankQuestion();
        BeanUtils.copyProperties(questionBankAddRequest, question);

        questionBankQuestionService.validQuestionBankQuestion(question, true);
        User loginUser = userService.getLoginUser(request);
        question.setUserId(loginUser.getId());
        boolean result = questionBankQuestionService.save(question);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        long newQuestionBankQuestionId = question.getId();
        return ResultUtils.success(newQuestionBankQuestionId);
    }

    /**
     * 删除题库题目关系
     *
     * @param questionBankQuestionRemoveRequest
     * @return
     */

    @ApiOperation(value = "删除题库题目关系")
    @PostMapping("/remove")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> removeQuestionBankQuestion(@RequestBody QuestionBankQuestionRemoveRequest questionBankQuestionRemoveRequest) {
        // 参数校验
        ThrowUtils.throwIf(questionBankQuestionRemoveRequest == null, ErrorCode.PARAMS_ERROR);
        Long questionBankId = questionBankQuestionRemoveRequest.getQuestionBankId();
        Long questionId = questionBankQuestionRemoveRequest.getQuestionId();
        ThrowUtils.throwIf(questionBankId == null || questionId == null, ErrorCode.PARAMS_ERROR);
        // 构造查询
        LambdaQueryWrapper<QuestionBankQuestion> lambdaQueryWrapper = Wrappers.lambdaQuery(QuestionBankQuestion.class)
                .eq(QuestionBankQuestion::getQuestionId, questionId)
                .eq(QuestionBankQuestion::getQuestionBankId, questionBankId);
        boolean result = questionBankQuestionService.remove(lambdaQueryWrapper);
        return ResultUtils.success(result);
    }

    // endregion

}
