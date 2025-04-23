package com.xiaoxu.shuatinextbackend.model.dto.questionbankquestion;

import lombok.Data;

import java.io.Serializable;

/**
 * 删除题目题库关系请求
 */
@Data
public class QuestionBankQuestionRemoveRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 题库 id
     */
    private Long questionBankId;
    /**
     * 题目 id
     */
    private Long questionId;
}
