package com.loja.loja_oak.Controller;

import com.loja.loja_oak.user.User;
import com.loja.loja_oak.user.UserNotFoundException;
import com.loja.loja_oak.user.UserRepository;
import com.loja.loja_oak.user.UserService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UserControler<id> {

    @Autowired
    private UserService service;

    @GetMapping("/users")
    public String showUseList(Model model) {
        List<User> listUsers = service.listAll();
        model.addAttribute("listUsers", listUsers);
        return "users";

    }

    @GetMapping("users/new")
    public String showNewForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("pageTitle", "Adicionar novo usuario");
        return "user_form";
    }

    @PostMapping("/users/save")
    public String savedUser(User user, RedirectAttributes ra) {
        ra.addFlashAttribute("message", "Usuario adicionado com suscesso");
        service.save(user);
        return "redirect:/users";

    }

    @GetMapping("/users/edit{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            User user = service.get(id);
            model.addAttribute("user", user);
            model.addAttribute("pageTitle", "Editar usuario (ID: " + id + ")");

            return "user_form";
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", "Dados atualizado com suscesso");
            return "redirect:/users";

        }
    }
        @GetMapping("/users/delete{id}")
        public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes ra) {
            try {
                User user = service.get(id);
                service.delete(id);
                ra.addFlashAttribute("message", "Usuario deletado com suscesso");
            } catch (UserNotFoundException e) {
                ra.addFlashAttribute("message", e.getMessage());
                return "redirect:/users";

            }
            return "redirect:/users";
    }
}