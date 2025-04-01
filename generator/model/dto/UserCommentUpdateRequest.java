package com.xiaoxu.shuati.model.dto.userComment;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 更新用户评论请求
 *
 * @from <a href="https://www.code-nav.cn">编程导航学习圈</a>
 */
@Data
public class UserCommentUpdateRequest implements Serializable {

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
     * 标签列表
     */
    private List<String> tags;

    private static final long serialVersionUID = 1L;
}