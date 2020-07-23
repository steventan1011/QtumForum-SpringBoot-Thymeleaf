package com.dcits.qtumforum.mapper;

import com.dcits.qtumforum.model.Comment;
import com.dcits.qtumforum.model.Question;

public interface ThumbExtMapper {
    int incLikeCount(Comment comment);

    int incQuestionLikeCount(Question question);
}
