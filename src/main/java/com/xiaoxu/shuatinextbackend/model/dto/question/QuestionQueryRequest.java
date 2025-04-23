package com.xiaoxu.shuatinextbackend.model.dto.question;

import com.xiaoxu.shuatinextbackend.common.PageRequest;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 题目查询请求
 */
@Data
public class QuestionQueryRequest extends PageRequest implements Serializable {
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
     * 内容
     */
    private String content;
    /**
     * 标签列表（json 数组）
     */
    private List<String> tags;
    /**
     * 推荐答案
     */
    private String answer;
    /**
     * 题库 id
     */
    private Long questionBankId;
}