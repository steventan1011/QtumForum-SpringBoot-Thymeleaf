package com.dcits.qtumforum.controller;

import com.dcits.qtumforum.dto.PaginationDTO;
import com.dcits.qtumforum.dto.UserDTO;
import com.dcits.qtumforum.dto.UserQueryDTO;
import com.dcits.qtumforum.exception.CustomizeErrorCode;
import com.dcits.qtumforum.exception.CustomizeException;
import com.dcits.qtumforum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author wadao
 * @version 1.0
 * @date 2020/7/6 20:20
 * @site niter.cn
 */
@Controller
public class JumpController {
    @Autowired
    private UserService userService;

    @GetMapping("/jump")
    public String jump(
            HttpServletRequest request,
            Model model
            ,@RequestParam(name = "type") String type
            ,@RequestParam(name = "target") String target){

        if("user_home".equals(type)){
            UserQueryDTO userQueryDTO = new UserQueryDTO();
            userQueryDTO.setName(target);
            userQueryDTO.convert();
            PaginationDTO paginationDTO = userService.list(userQueryDTO);
            List<UserDTO> userDTOs = paginationDTO.getData();
            if(userDTOs.size()!=1)
                throw new CustomizeException(CustomizeErrorCode.USER_IS_EMPTY);
            return "redirect:/user/"+userDTOs.get(0).getId();
        }



        return "redirect:/forum";
    }



}
