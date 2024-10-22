package com.quiz.Controller;

import com.quiz.Model.DTO.PaginationDTO;
import com.quiz.Model.Entity.UserEntity;
import com.quiz.Model.Response.UserResponse;
import com.quiz.Service.Role.IRoleService;
import com.quiz.Service.User.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "${admin.prefix}/users")
public class UserController {
    private final IUserService userService;
    private final IRoleService roleService;

    @GetMapping(value = "/list")
    public ModelAndView UserList(@RequestParam(value = "page", required = false) Integer page,
                                 @RequestParam(value = "limit", required = false) Integer limit) {
        ModelAndView view = new ModelAndView("/admin/users/list");
        Page<UserResponse> userList = userService.getAllUsers(page, limit);
        view.addObject("listUsers", userList.getContent());
        PaginationDTO pagination = new PaginationDTO(userList.getPageable().getPageNumber() + 1, userList.getPageable().getPageSize(), userList.getTotalPages());
        view.addObject("pagination", pagination);
        return view;
    }
    @GetMapping(value = "/update/{id}")
    public ModelAndView updateUser(@PathVariable(value = "id") String id) {
        UserEntity user = userService.getUserEntityById(id);
        ModelAndView view = new ModelAndView("/admin/users/create");
        view.addObject("user", user);
        return view;
    }

    @GetMapping(value = "/create")
    public ModelAndView addUser() {
        ModelAndView view = new ModelAndView("/admin/users/create");
        Map<String, String> roles = roleService.getAllRoles();
        view.addObject("roles", roles);
        return view;
    }
}
