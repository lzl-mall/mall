package cn.lzl.service;

import cn.lzl.model.PmsBrand;
import cn.lzl.model.PmsBrandExample;

import java.util.List;

/**
 * PmsBrandService
 * Created by ljy on 2020.11.3.
 */
public interface PmsBrandService {
    List<PmsBrand> listAllBrand();

    int createBrand(PmsBrand brand);

    int updateBrand(Long id, PmsBrand brand);

    int deleteBrand(Long id);

    List<PmsBrand> listBrand(int pageNum, int pageSize, PmsBrandExample pmsBrandExample);

    PmsBrand getBrand(Long id);

    Long countByExample();

    int batchDelete(List<Integer> idList);

    /*List<PmsBrand> queryListByPageAndCondition(BrandParam brandParam);*/
}
