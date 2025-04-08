package com.xiaoxu.shuatinextbackend.model.dto.questionbank;

import lombok.Data;

import java.io.Serializable;

/**
 * 题库添加请求
 */
@Data
public class QuestionBankAddRequest implements Serializable {


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


    private static final long serialVersionUID = 1L;
}