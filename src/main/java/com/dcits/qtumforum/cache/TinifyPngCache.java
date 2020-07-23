package com.dcits.qtumforum.cache;

import com.dcits.qtumforum.dto.TinifyPngDTO;
import com.dcits.qtumforum.dto.UserDTO;
import com.dcits.qtumforum.model.User;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Data
public class TinifyPngCache {
    List<TinifyPngDTO> images=new ArrayList<>();

    public List get(){
        return images;
    }
    public void clear(){
        images.clear();
    }
    public void add(String url, UserDTO user, String fileName){
        if(images.size()<10){//控制最大容量
            TinifyPngDTO png = new TinifyPngDTO();
            png.setUrl(url);
            png.setUser(user);
            png.setFileName(fileName);
            images.add(png);
        }
    }



}
