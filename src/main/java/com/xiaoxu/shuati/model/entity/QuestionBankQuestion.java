package com.xiaoxu.shuati.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 题库题目
 *
 * @TableName question_bank_question
 */
@TableName(value = "question_bank_question")
@Data
public class QuestionBankQuestion implements Serializable {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 题库 id
     */
    @TableField(value = "questionBankId")
    private Long questionBankId;

    /**
     * 题目 id
     */
    @TableField(value = "questionId")
    private Long questionId;

    /**
     * 创建用户 id
     */
    @TableField(value = "userId")
    private Long userId;

    /**
     * 创建时间
     */
    @TableField(value = "createTime")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "updateTime")
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}