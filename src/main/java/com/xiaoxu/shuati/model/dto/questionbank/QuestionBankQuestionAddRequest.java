package com.xiaoxu.shuati.model.dto.questionbank;

import lombok.Data;

import java.io.Serializable;

/**
 * 题库新增请求
 */
@Data
public class QuestionBankQuestionAddRequest implements Serializable {
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
     * 创建用户 id
     */
    private Long userId;


    private static final long serialVersionUID = 1L;
}