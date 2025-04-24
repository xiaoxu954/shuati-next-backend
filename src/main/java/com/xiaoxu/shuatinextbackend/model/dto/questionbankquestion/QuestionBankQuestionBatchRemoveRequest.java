package com.xiaoxu.shuatinextbackend.model.dto.questionbankquestion;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class QuestionBankQuestionBatchRemoveRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 题库 id
     */
    private Long questionBankId;
    /**
     * 题目 id 列表
     */
    private List<Long> questionIdList;
}
