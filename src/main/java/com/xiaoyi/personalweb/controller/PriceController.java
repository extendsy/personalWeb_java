package com.xiaoyi.personalweb.controller;

import com.xiaoyi.personalweb.entity.Price;
import com.xiaoyi.personalweb.entity.Result;
import com.xiaoyi.personalweb.service.PriceService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("price")
public class PriceController {

    @Resource
    private PriceService priceService;

    @PostMapping
    public Result add(Price price){
        return priceService.addPrice(price);
    }

    @DeleteMapping
    public Result delById(Integer id){
        return priceService.delById(id);
    }

    @PutMapping
    public Result updateById(Price price){
        return priceService.updateById(price);
    }

    @GetMapping
    public Result queryByPage(Price price){
        return priceService.queryByPage(price);
    }

}
