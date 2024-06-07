package com.test.todolist.controller;

import com.test.todolist.model.Task;
import com.test.todolist.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("tasks", taskService.getAllTasks());
        return "index";
    }

    @GetMapping("/add")
    public String showAddTaskForm(Model model) {
        Task task = new Task();
        model.addAttribute("task", task);
        return "add-task";
    }

    @PostMapping("/saveTask")
    public String saveTask(@ModelAttribute("task") Task task) {
        taskService.saveTask(task);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showEditTaskForm(@PathVariable(value = "id") long id, Model model) {
        Task task = taskService.getTaskById(id).orElse(new Task());
        model.addAttribute("task", task);
        return "edit-task";
    }

    @PostMapping("/updateTask")
    public String updateTask(@ModelAttribute("task") Task task) {
        taskService.saveTask(task);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable(value = "id") long id) {
        taskService.deleteTask(id);
        return "redirect:/";
    }
}
