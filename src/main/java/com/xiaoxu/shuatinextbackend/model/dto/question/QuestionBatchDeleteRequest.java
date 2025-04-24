package com.xiaoxu.shuatinextbackend.model.dto.question;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class QuestionBatchDeleteRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 题目 id 列表
     */
    private List<Long> questionIdList;
}
