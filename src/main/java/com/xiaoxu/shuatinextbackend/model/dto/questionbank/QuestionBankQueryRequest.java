package com.xiaoxu.shuatinextbackend.model.dto.questionbank;

import com.xiaoxu.shuatinextbackend.common.PageRequest;
import lombok.Data;

import java.io.Serializable;

/**
 * 题库查询请求
 */
@Data
public class QuestionBankQueryRequest extends PageRequest implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 描述
     */
    private String description;

    /**
     * 图片
     */
    private String picture;

    /**
     * 是否要关联查询题目列表
     */
    private boolean needQueryQuestionList;

    private static final long serialVersionUID = 1L;
}