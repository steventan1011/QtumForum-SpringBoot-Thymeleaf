package com.dcits.qtumforum.mapper;

import com.dcits.qtumforum.dto.NewsQueryDTO;
import com.dcits.qtumforum.model.News;

import java.util.List;

public interface NewsExtMapper {
    int incView(News record);
    Integer countBySearch(NewsQueryDTO newsQueryDTO);
    List<News> selectBySearch(NewsQueryDTO newsQueryDTO);

}
