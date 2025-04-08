package com.xiaoxu.shuatinextbackend.model.vo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoxu.shuatinextbackend.model.entity.Question;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 题库视图
 *
 */
@Data
public class QuestionBankVO implements Serializable {
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
     * 题库里的题目列表（分页）
     */
    Page<Question> questionPage;

    /**
     * 创建用户 id
     */
    private Long userId;

    /**
     * 编辑时间
     */
    private Date editTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}