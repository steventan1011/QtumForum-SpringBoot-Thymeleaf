package com.dcits.qtumforum.mapper;

import com.dcits.qtumforum.dto.CommentQueryDTO;
import com.dcits.qtumforum.model.Comment;

public interface CommentExtMapper {
    int incCommentCount(Comment comment);

    Integer countBySearch(CommentQueryDTO commentQueryDTO);

}
