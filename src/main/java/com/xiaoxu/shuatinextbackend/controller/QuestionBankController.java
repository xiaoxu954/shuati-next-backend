package com.xiaoxu.shuatinextbackend.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoxu.shuatinextbackend.annotation.AuthCheck;
import com.xiaoxu.shuatinextbackend.common.BaseResponse;
import com.xiaoxu.shuatinextbackend.common.DeleteRequest;
import com.xiaoxu.shuatinextbackend.common.ErrorCode;
import com.xiaoxu.shuatinextbackend.common.ResultUtils;
import com.xiaoxu.shuatinextbackend.constant.UserConstant;
import com.xiaoxu.shuatinextbackend.exception.BusinessException;
import com.xiaoxu.shuatinextbackend.exception.ThrowUtils;
import com.xiaoxu.shuatinextbackend.model.dto.question.QuestionQueryRequest;
import com.xiaoxu.shuatinextbackend.model.dto.questionbank.QuestionBankAddRequest;
import com.xiaoxu.shuatinextbackend.model.dto.questionbank.QuestionBankQueryRequest;
import com.xiaoxu.shuatinextbackend.model.dto.questionbank.QuestionBankUpdateRequest;
import com.xiaoxu.shuatinextbackend.model.entity.Question;
import com.xiaoxu.shuatinextbackend.model.entity.QuestionBank;
import com.xiaoxu.shuatinextbackend.model.entity.User;
import com.xiaoxu.shuatinextbackend.model.vo.QuestionBankVO;
import com.xiaoxu.shuatinextbackend.service.QuestionBankService;
import com.xiaoxu.shuatinextbackend.service.QuestionService;
import com.xiaoxu.shuatinextbackend.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 题库接口
 */
@Api(tags = "题库接口")
@RestController
@RequestMapping("/questionBank")
@Slf4j
public class QuestionBankController {


    @Resource
    private UserService userService;

    @Resource
    private QuestionService questionService;

    @Resource
    private QuestionBankService questionBankService;
    


    // region 增删改查

    /**
     * 添加题库
     *
     * @param questionBankAddRequest
     * @param request
     * @return
     */
    @ApiOperation(value = "添加题库")
    @PostMapping("/add")
    public BaseResponse<Long> addQuestionBank(@RequestBody QuestionBankAddRequest questionBankAddRequest, HttpServletRequest request) {
        if (questionBankAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        QuestionBank question = new QuestionBank();
        BeanUtils.copyProperties(questionBankAddRequest, question);


        questionBankService.validQuestionBank(question, true);
        User loginUser = userService.getLoginUser(request);
        question.setUserId(loginUser.getId());
        boolean result = questionBankService.save(question);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        long newQuestionBankId = question.getId();
        return ResultUtils.success(newQuestionBankId);
    }

    /**
     * 删除题库
     *
     * @param deleteRequest
     * @param request
     * @return
     */
    @ApiOperation(value = "删除题库")
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteQuestionBank(@RequestBody DeleteRequest deleteRequest, HttpServletRequest request) {
        if (deleteRequest == null || deleteRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User user = userService.getLoginUser(request);
        long id = deleteRequest.getId();
        // 判断是否存在
        QuestionBank oldQuestionBank = questionBankService.getById(id);
        ThrowUtils.throwIf(oldQuestionBank == null, ErrorCode.NOT_FOUND_ERROR);
        // 仅本人或管理员可删除
        if (!oldQuestionBank.getUserId().equals(user.getId()) && !userService.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        boolean b = questionBankService.removeById(id);
        return ResultUtils.success(b);
    }

    /**
     * 更新（仅管理员）
     *
     * @param questionBankUpdateRequest
     * @return
     */
    @ApiOperation(value = "更新题库（仅管理员）")
    @PostMapping("/update")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> updateQuestionBank(@RequestBody QuestionBankUpdateRequest questionBankUpdateRequest) {
        if (questionBankUpdateRequest == null || questionBankUpdateRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        QuestionBank question = new QuestionBank();
        BeanUtils.copyProperties(questionBankUpdateRequest, question);

        // 参数校验
        questionBankService.validQuestionBank(question, false);
        long id = questionBankUpdateRequest.getId();
        // 判断是否存在
        QuestionBank oldQuestionBank = questionBankService.getById(id);
        ThrowUtils.throwIf(oldQuestionBank == null, ErrorCode.NOT_FOUND_ERROR);
        boolean result = questionBankService.updateById(question);
        return ResultUtils.success(result);
    }

    /**
     * 根据 id 获取题库
     *
     * @param questionBankQueryRequest
     * @return
     */
    @ApiOperation(value = "根据 id 获取题库")
    @GetMapping("/get/vo")
    public BaseResponse<QuestionBankVO> getQuestionBankVOById(QuestionBankQueryRequest questionBankQueryRequest, HttpServletRequest request) {
        ThrowUtils.throwIf(questionBankQueryRequest == null, ErrorCode.PARAMS_ERROR);
        Long id = questionBankQueryRequest.getId();
        ThrowUtils.throwIf(id <= 0, ErrorCode.PARAMS_ERROR);
        // 查询数据库
        QuestionBank questionBank = questionBankService.getById(id);
        ThrowUtils.throwIf(questionBank == null, ErrorCode.NOT_FOUND_ERROR);
        // 查询题库封装类
        QuestionBankVO questionBankVO = questionBankService.getQuestionBankVO(questionBank, request);
        // 是否要关联查询题库下的题目列表
        boolean needQueryQuestionList = questionBankQueryRequest.isNeedQueryQuestionList();
        if (needQueryQuestionList) {
            QuestionQueryRequest questionQueryRequest = new QuestionQueryRequest();
            questionQueryRequest.setQuestionBankId(id);
            Page<Question> questionPage = questionService.listQuestionByPage(questionQueryRequest);
            questionBankVO.setQuestionPage(questionPage);
        }
        // 获取封装类
        return ResultUtils.success(questionBankVO);
    }

    /**
     * 分页获取列表（仅管理员）
     *
     * @param questionBankQueryRequest
     * @return
     */
    @ApiOperation(value = "分页获取列表（仅管理员）")
    @PostMapping("/list/page")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Page<QuestionBank>> listQuestionBankByPage(@RequestBody QuestionBankQueryRequest questionBankQueryRequest) {
        Page<QuestionBank> questionPage = questionBankService.listQuestionBankByPage(questionBankQueryRequest);
        return ResultUtils.success(questionPage);
    }

    /**
     * 分页获取列表（封装类）
     *
     * @param questionBankQueryRequest
     * @param request
     * @return
     */
    @ApiOperation(value = "分页获取列表（封装类）")
    @PostMapping("/list/page/vo")
    public BaseResponse<Page<QuestionBankVO>> listQuestionBankVOByPage(@RequestBody QuestionBankQueryRequest questionBankQueryRequest,
                                                                       HttpServletRequest request) {
        long current = questionBankQueryRequest.getCurrent();
        long size = questionBankQueryRequest.getPageSize();
        // 限制爬虫
        ThrowUtils.throwIf(size > 20, ErrorCode.PARAMS_ERROR);
        Page<QuestionBank> questionPage = questionBankService.page(new Page<>(current, size),
                questionBankService.getQueryWrapper(questionBankQueryRequest));
        return ResultUtils.success(questionBankService.getQuestionBankVOPage(questionPage, request));
    }

    // endregion


}
