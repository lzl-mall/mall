package cn.lzl.service.impl;
import cn.lzl.common.utils.PinyinHelperUtil;
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
        if(null != brand.getFirstLetter() || ! "".equals(brand.getFirstLetter())){
            String pinYinHeadChar = PinyinHelperUtil.getPinYinHeadChar(brand.getFirstLetter());
        }
        if(null == brand.getId() || "".equals(brand.getId())){
            return brandMapper.insertSelective(brand);
        }
        return brandMapper.updateByPrimaryKeySelective(brand);
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
    public List<PmsBrand> listBrand(int pageNum, int pageSize, PmsBrandExample pmsBrandExample) {
        PageHelper.startPage(pageNum, pageSize);
        return brandMapper.selectAllPage(pmsBrandExample);
    }

    @Override
    public PmsBrand getBrand(Long id) {
        return brandMapper.selectByPrimaryKey(id);
    }

    @Override
    public Long countByExample() {
        return brandMapper.countByExample(new PmsBrandExample());
    }

    @Override
    public int batchDelete(List<Integer> idList) {
        return brandMapper.batchDelete(idList);
    }

    /*@Override
    public List<PmsBrand> queryListByPageAndCondition(BrandParam brandParam) {
        return brandMapper.queryListByPageAndCondition(brandParam);
    }*/
}
