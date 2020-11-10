package cn.lzl.service.impl;


import cn.lzl.mapper.PmsBrandMapper;
import cn.lzl.model.PmsBrand;
import cn.lzl.model.PmsBrandExample;
import cn.lzl.service.PmsBrandService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
@Service
public class PmsBrandServiceImpl implements PmsBrandService {

    @Resource
    private PmsBrandMapper brandMapper;
    @Override
    public List<PmsBrand> listAllBrand() {
        return brandMapper.selectByExample(new PmsBrandExample());
    }

    @Override
    public int createBrand(PmsBrand brand) {
        return brandMapper.insertSelective(brand);
    }

    @Override
    public int updateBrand(Long id, PmsBrand brand) {
        brand.setId(id);
        return brandMapper.updateByPrimaryKeySelective(brand);
    }

    @Override
    public int deleteBrand(Long id) {
        return brandMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<PmsBrand> listBrand(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return brandMapper.selectByExample(new PmsBrandExample());
    }

    @Override
    public PmsBrand getBrand(Long id) {
        return brandMapper.selectByPrimaryKey(id);
    }

    /*@Override
    public List<PmsBrand> queryListByPageAndCondition(BrandParam brandParam) {
        return brandMapper.queryListByPageAndCondition(brandParam);
    }*/
}
