package com.xiaoxu.shuatinextbackend.model.dto.questionbankquestion;

import lombok.Data;

import java.io.Serializable;

/**
 * 题库题目查询
 */
@Data
public class QuestionBankQuestionQueryRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    /**
     * 题库 id
     */
    private Long questionBankId;
    /**
     * 题目 id
     */
    private Long questionId;
}