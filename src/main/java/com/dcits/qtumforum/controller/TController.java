package com.dcits.qtumforum.controller;

import com.dcits.qtumforum.cache.HotTagCache;
import com.dcits.qtumforum.cache.LoginUserCache;
import com.dcits.qtumforum.dto.PaginationDTO;
import com.dcits.qtumforum.dto.QuestionDTO;
import com.dcits.qtumforum.dto.UserDTO;
import com.dcits.qtumforum.model.User;
import com.dcits.qtumforum.model.UserAccount;
import com.dcits.qtumforum.service.QuestionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author T-bk
 * @version 2.0
 * @date 2020/7/23 11:12
 */

@Controller
public class TController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private HotTagCache hotTagCache;

    @Autowired
    private LoginUserCache loginUserCache;

    @GetMapping("/t")
    public String tIndex(HttpServletRequest request, Model model,
                         @RequestParam(name = "page",defaultValue = "1")Integer page,
                         @RequestParam(name = "size",defaultValue = "10")Integer size,
                         @RequestParam(name = "column",defaultValue = "0") Integer column2,
                         @RequestParam(name = "search", defaultValue = "") String search,
                         @RequestParam(name = "tag", defaultValue = "") String tag,
                         @RequestParam(name = "sort", defaultValue = "new") String sort) {
        List<QuestionDTO> topQuestions = questionService.listTopwithColumn(search, tag, sort,column2);
        // UserAccount userAccount = (UserAccount)request.getSession().getAttribute("userAccount");
        //PaginationDTO pagination = questionService.listwithColumn(search, tag, sort, page,size,column2,userAccount);
        List<String> tags = hotTagCache.getHots();
        List<User> loginUsers = loginUserCache.getLoginUsers();
        //System.out.println("users"+loginUsers);
        model.addAttribute("loginUsers", loginUsers);
        //model.addAttribute("pagination",pagination);
        model.addAttribute("search", search);
        model.addAttribute("tag", tag);
        model.addAttribute("tags", tags);
        model.addAttribute("sort", sort);
        model.addAttribute("column", column2);
        model.addAttribute("page", page);
        model.addAttribute("topQuestions", topQuestions);
        model.addAttribute("navtype", "communitynav");
        //return "index";
        return "t/index";
    }

    @GetMapping("/t/list")
    @ResponseBody
    public Map<String,Object> tList(HttpServletRequest request, Model model,
                                    @RequestParam(name = "page",defaultValue = "1")Integer page,
                                    @RequestParam(name = "size",defaultValue = "10")Integer size,
                                    @RequestParam(name = "column", required = false) Integer column2,
                                    @RequestParam(name = "search", required = false) String search,
                                    @RequestParam(name = "tag", required = false) String tag,
                                    @RequestParam(name = "sort", required = false) String sort) {
        //List<QuestionDTO> topQuestions = questionService.listTopwithColumn(search, tag, sort,column2);
        Map<String,Object> map  = new HashMap<>();
        UserDTO loginuser = (UserDTO) request.getAttribute("loginUser");
        UserAccount userAccount =null;
        if(loginuser!=null){
            userAccount = new UserAccount();
            BeanUtils.copyProperties(loginuser,userAccount);
            userAccount.setUserId(loginuser.getId());
        }        PaginationDTO pagination = questionService.listwithColumn(search, tag, sort, page,size,column2,userAccount);
        map.put("questions",pagination.getData());
        map.put("totalPage",pagination.getTotalPage());
        map.put("search",search);
        map.put("tag",tag);
        map.put("sort",sort);
        map.put("column",column2);

        return map;
    }



}
