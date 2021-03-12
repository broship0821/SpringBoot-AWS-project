package com.peter.broship.boot.web;

import com.peter.broship.boot.config.auth.LoginUser;
import com.peter.broship.boot.config.auth.dto.SessionUser;
import com.peter.broship.boot.service.posts.PostsService;
import com.peter.broship.boot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user){
        model.addAttribute("posts", postsService.findAllDesc());

        //SessionUser user = (SessionUser) httpSession.getAttribute("user");//애노테이션으로 바꿈

        if(user!=null)
            model.addAttribute("userName",user.getName());

        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
        PostsResponseDto dto = postsService.findById(id);//이걸로 select 끝냄
        model.addAttribute("post", dto);

        return "posts-update";
    }
}
