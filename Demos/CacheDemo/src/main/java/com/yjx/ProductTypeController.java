package com.yjx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
@Controller
public class ProductTypeController {
    @Autowired
    ProductTypeService productTypeService;

    @RequestMapping("/")
    public String root(){
        return "types";
    }

    @RequestMapping("all")
    @ResponseBody
    public List<ProductType> all(){
        List<ProductType> productTypes = productTypeService.getAll();
        return productTypes;
    }

    @RequestMapping("/add")
    public String add(){
        return "add";
    }

    @RequestMapping("doAdd")
    public String doAdd(String name){
        ProductType productType = new ProductType();
        productType.setTypeName(name);
        productType.setCreateTime(new Date());
        productType.setUpdateTime(new Date());

        productTypeService.add(productType);

        return "redirect:/";
    }


    @RequestMapping("/update")
    public String update(Integer typeId, Model model){
        ProductType productType = productTypeService.getById(typeId);
        model.addAttribute("productType",productType);

        return "update";
    }

    @RequestMapping("doUpdate")
    public String doAdd(Integer id,String name){
        ProductType productType = productTypeService.getById(id);
        productType.setTypeName(name);
        productType.setUpdateTime(new Date());

        productTypeService.updateById(productType);

        return "redirect:/";
    }

    @RequestMapping("delete")
    public String doAdd(Integer typeId){
        productTypeService.deleteById(typeId);

        return "redirect:/";
    }
}
