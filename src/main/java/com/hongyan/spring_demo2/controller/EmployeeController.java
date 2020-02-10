package com.hongyan.spring_demo2.controller;

import com.hongyan.spring_demo2.dao.DepartmentDao;
import com.hongyan.spring_demo2.dao.EmployeeDao;
import com.hongyan.spring_demo2.entities.Department;
import com.hongyan.spring_demo2.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;

    //查询所有员工返回列表界面
    @GetMapping("/emps")
    public String list(Model model){
        Collection<Employee> employeeDaoAll = employeeDao.getAll();
        model.addAttribute("emps",employeeDaoAll);
        return "emp/list";
    }

    @GetMapping("/emp")
    public String toAddPage(Model model){
        //添加页面
        //查出所有的部门，在界面显示
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        return "emp/add";
    }

    @PostMapping("/emp")
    public String addEmp(Employee employee){
        //来到员工列表界面
        //redirect:表示重定向到一个地址，/代表当前项目路径
        //forward: 表示转发到一个地址
        employeeDao.save(employee);
        return "redirect:/emps";
    }
    //来到修改界面，查出当前员工，在界面重显
    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable("id") Integer id,Model model){
        Employee employee = employeeDao.get(id);
        //查出所有的部门，在界面显示
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        model.addAttribute("emp",employee);
        return "emp/update";
    }

    @PutMapping("/emp")
    public String updateEmployee(Employee employee){
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    @DeleteMapping("/emp/{id}")
    public String deleteEmpoyee(@PathVariable("id") Integer id){
        employeeDao.delete(id);
        return "redirect:/emps";
    }
}
