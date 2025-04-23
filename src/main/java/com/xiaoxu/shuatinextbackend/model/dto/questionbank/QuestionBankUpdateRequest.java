package com.xiaoxu.shuatinextbackend.model.dto.questionbank;

import lombok.Data;

import java.io.Serializable;

/**
 * 题库更新请求
 */
@Data
public class QuestionBankUpdateRequest implements Serializable {
    private static final long serialVersionUID = 1L;
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
}