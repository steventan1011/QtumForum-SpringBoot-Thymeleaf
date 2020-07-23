package com.dcits.qtumforum.cache;

import com.dcits.qtumforum.dto.HotTagDTO;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author T-bk
 * @version 2.0
 * @date 2020/7/22 15:27
 */

@Component
@Data
public class HotTagCache {
    private List<String> hots = new ArrayList<>();

    public void updateTags(Map<String, Integer> tags) {
        // 最多12个热门标签 按优先级排序
        int max = 12;
        PriorityQueue<HotTagDTO> priorityQueue = new PriorityQueue<>(max);

        tags.forEach((name, priority) -> {
            HotTagDTO hotTagDTO = new HotTagDTO();
            hotTagDTO.setName(name);
            hotTagDTO.setPriority(priority);
            if (priorityQueue.size() < max) {//热门标签个数小于max就直接往里面放当前标签
                priorityQueue.add(hotTagDTO);
            } else {//热门标签个数大于等于max
                HotTagDTO minHot = priorityQueue.peek();//拿出热门标签中的最小标签
                if (hotTagDTO.compareTo(minHot) > 0) {//当前标签比最小标签大
                    priorityQueue.poll();
                    priorityQueue.add(hotTagDTO);
                }
            }
        });

        List<String> sortedTags = new ArrayList<>();

        //一个一个拿出热门标签
        HotTagDTO poll = priorityQueue.poll();
        while (poll != null) {
            sortedTags.add(0, poll.getName());
            poll = priorityQueue.poll();
        }
        hots = sortedTags;
    }
}
