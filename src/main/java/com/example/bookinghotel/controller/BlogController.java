package com.example.bookinghotel.controller;


import com.example.bookinghotel.entities.Blog;
import com.example.bookinghotel.entities.Room;
import com.example.bookinghotel.repositories.BlogRepository;
import com.example.bookinghotel.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;

@Controller
public class BlogController {


    @Autowired
    BlogRepository blogRepository;

    @Autowired
    private UserService userService;


    @GetMapping("/allBlog")
    public String homepageBlog(Model model){
        model.addAttribute("blogs", blogRepository.findAllBlog());
        return "/Pages/Blog/blog";
    }

    private String getPrincipal(){
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();

        } else {
            userName = principal.toString();
        }
        return userName;
    }


    @GetMapping("/manageBlog")
    public String Blog(Model model){
        model.addAttribute("blogs", blogRepository.findAllBlogByUserId(userService.findByUserName(getPrincipal()).getId()));
        return "/Pages/Blog/all-blog";
    }

    @Value("C:/ListRoom/file/")
    private String fileUpload;

    @GetMapping("/createBlog")
    public String showFormCreateRoom( Model model){

        Blog blog = new Blog();

        model.addAttribute("blog", blog);

        return "Pages/Blog/add-blog";
    }

    @PostMapping("/saveBlog")
    public String saveRoom(Model model, @ModelAttribute("blog") Blog blog, RedirectAttributes redirect){

        MultipartFile multipartFile = blog.getImage();
        String fileName = multipartFile.getOriginalFilename();

        try {
            FileCopyUtils.copy(blog.getImage().getBytes(), new File(fileUpload + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        blog.setImgSrc(fileName);
        blog.setUser(userService.findByUserName(getPrincipal()));
        blogRepository.save(blog);
        return "redirect:manageBlog";
    }

    @GetMapping("/findOneBlog/{id}")
    public String findBlogById(@PathVariable("id") long id , Model model){
        model.addAttribute("blog", blogRepository.findById(id).get());

        return "Pages/Blog/edit-blog";
    }

    @GetMapping("/viewDetailBlog/{id}")
    public String viewDetailBlog(@PathVariable("id") long id , Model model){
        model.addAttribute("blog", blogRepository.findById(id).get());

        return "Pages/Blog/blog-details";
    }

    @PostMapping("/saveEditBlog")
    public String updateBlog(@ModelAttribute Blog blog){

        MultipartFile multipartFile = blog.getImage();
        String fileName = multipartFile.getOriginalFilename();

        try {
            FileCopyUtils.copy(blog.getImage().getBytes(), new File(fileUpload + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        blog.setImgSrc(fileName);
        blogRepository.save(blog);


        return "redirect:/manageBlog";
    }

    @GetMapping("/deleteBlog")
    public String deleteBlogById(long id){
        blogRepository.deleteById(id);
        return "redirect:/manageBlog";
    }
}
