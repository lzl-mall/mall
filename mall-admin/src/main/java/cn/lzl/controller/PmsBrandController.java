package cn.lzl.controller;
import cn.lzl.common.api.CommonPage;
import cn.lzl.common.api.CommonResult;
import cn.lzl.model.PmsBrand;
import cn.lzl.model.PmsBrandExample;
import cn.lzl.service.PmsBrandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * brand控制层
 * Created by ljy on 2020/11/06.
 */
@RestController
@RequestMapping("/brands")
@CrossOrigin
public class PmsBrandController {
    @Autowired
    private PmsBrandService pmsBrandService;
    Logger LOGGER = LoggerFactory.getLogger(PmsBrandController.class);

    /**
     * 查询所有商品集合
     * @return
     */
    @RequestMapping(value = "list_all", method = RequestMethod.GET)
    public CommonResult getBrandList(){
        return CommonResult.success(pmsBrandService.listAllBrand());
    }
    /**
     * 新增商品
     * @return
     */
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public CommonResult createBrand(PmsBrand pmsBrand){
        CommonResult commonResult;
        int count = pmsBrandService.createBrand(pmsBrand);
        if(count == 1){
            commonResult = CommonResult.success(pmsBrand);
            LOGGER.debug("createBrand success:{}", pmsBrand);
        }else {
            commonResult = CommonResult.failed("插入数据库操作失败");
            LOGGER.debug("createBrand failed:{}", pmsBrand);
        }
        return commonResult;
    }
    /**
     * 根据id修改商品
     * @return
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public CommonResult updateBrand(@PathVariable Long id,PmsBrand pmsBrand){
        CommonResult commonResult;
        int count = pmsBrandService.updateBrand(id,pmsBrand);
        if(count == 1){
            commonResult = CommonResult.success(pmsBrand);
            LOGGER.debug("createBrand success:{}", pmsBrand);
        }else {
            commonResult = CommonResult.failed("修改操作失败");
            LOGGER.debug("createBrand failed:{}", pmsBrand);
        }
        return commonResult;
    }
    /**
     * 根据id删除商品
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public CommonResult updateBrand(@PathVariable Long id){
        CommonResult commonResult;
        int count = pmsBrandService.deleteBrand(id);
        if(count == 1){
            commonResult = CommonResult.success(null);
            LOGGER.debug("createBrand success: id={}", id);
        }else {
            commonResult = CommonResult.failed("修改操作失败");
            LOGGER.debug("createBrand failed: id={}", id);
        }
        return commonResult;
    }
    @RequestMapping(value = "/batchDelete", method = RequestMethod.POST)
    public CommonResult batchDelete(@RequestParam("idList") List<Integer> idList){
        CommonResult commonResult;
        try{
            pmsBrandService.batchDelete(idList);
            commonResult = CommonResult.success(null);
            LOGGER.debug("createBrand success: id={}", idList);
        }catch (Exception e){
            commonResult = CommonResult.failed("修改操作失败");
            LOGGER.debug("createBrand failed: id={}", idList);
        }
        return commonResult;
    }
    /**
     * 分页查询
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public CommonResult<CommonPage<PmsBrand>> queryListByPageAndCondition(CommonPage commonPage, PmsBrandExample pmsBrandExample){
        CommonResult commonResult;
        List<PmsBrand> brandList = pmsBrandService.listBrand(commonPage.getPageNum(), commonPage.getPageSize(),pmsBrandExample);
        Long total = pmsBrandService.countByExample();
        HashMap<String, Object> map = new HashMap<>();
        map.put("total",total);
        map.put("brandList",brandList);
        if(brandList.size()>=0){
            commonResult = CommonResult.success(map);
            LOGGER.debug("createBrand success: brandList={}", map);
        }else {
            commonResult = CommonResult.failed("修改操作失败");
            LOGGER.debug("createBrand failed: {}", "修改操作失败");
        }
        return commonResult;
    }

}
