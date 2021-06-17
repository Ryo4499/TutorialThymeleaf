package com.tutorial.thymeleaf.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.tutorial.thymeleaf.entity.User;
import com.tutorial.thymeleaf.form.UserForm;

@Controller
public class SampleController {
  // localhost:8080/
  @GetMapping("/")
  public String getHelloWorld(Model model) {
    // 引数のModel(Servletでいう暗黙型的なやつ)のaddAttributeでパラメータを設定している
    // この例だと"helloworld"というパラメータの名前に"HelloWorld!!"という値を入れている
    model.addAttribute("helloworld", "HelloWorld!!");
    return "helloworld";
  }

  @GetMapping("/object")
  public String getObject(Model model) {
    // UserFormクラスをインスタンス化
    UserForm userForm = new UserForm();
    // インスタンスをaddAttributeで渡す
    model.addAttribute("userForm", userForm);
    return "object";
  }

  @PostMapping("/object")
  public String postObject(UserForm userForm, BindingResult result, Model model) {
    if (result.hasErrors()) {
      // Validationを組んでるなら(今回はなし!)
      // 指定した検証に引っかかったら､objectページに戻す
      return "object";
    }
    // 今回はサンプルなので､Formを戻す
    model.addAttribute("userForm", userForm);
    // 普通ならここでuserFormをuserクラスに変換してDaoにデータを渡している
    User user = makeUser(userForm);
    return "object-confirm";
  }

  @GetMapping("/if")
  public String getIf(Model model) {
    model.addAttribute("message", "message");
    return "if";
  }

  @GetMapping("/list")
  public String getList(Model model) {
    List<User> users = new ArrayList<>();
    users.add(new User(1, "user1", "user1@example.com", "password"));
    users.add(new User(2, "user2", "user2@example.com", "password"));
    users.add(new User(3, "user3", "user3@example.com", "password"));
    model.addAttribute("users", users);
    return "list";
  }

  @GetMapping("/date")
  public String getDate(Model model) {
    model.addAttribute("today", new Date());
    return "date";
  }

  @GetMapping("/js")
  public String getJs(Model model) {
    model.addAttribute("message", "アラート");
    return "js";
  }

  private User makeUser(UserForm userForm) {
    User user = new User();
    user.setName(user.getName());
    user.setEmail(user.getEmail());
    user.setPassword(user.getPassword());
    return user;
  }

}
