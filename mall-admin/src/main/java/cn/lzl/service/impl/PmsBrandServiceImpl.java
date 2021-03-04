package cn.lzl.service.impl;



import cn.lzl.mapper.PmsBrandMapper;
import cn.lzl.model.PmsBrand;
import cn.lzl.model.PmsBrandExample;
import cn.lzl.service.PmsBrandService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
@Service
public class PmsBrandServiceImpl implements PmsBrandService {

    @Resource
    private PmsBrandMapper brandMapper;
    @Override
    public List<PmsBrand> listAllBrand() {
        return brandMapper.selectByExample(new PmsBrandExample());
    }

    /**
     * 得到中文首字母,例如"专科"得到zk返回
     *
     * @param str
     *            中文字符串
     * @return
     */
    public static String getPinYinHeadChar(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char word = str.charAt(i);
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
            if (pinyinArray != null) {
                sb.append(pinyinArray[0].charAt(0));
            } else {
                sb.append(word);
            }
        }
        return sb.toString().toUpperCase();
    }

    @Override
    public int createBrand(PmsBrand brand) {
        if(null != brand.getFirstLetter() || ! "".equals(brand.getFirstLetter())){
            String pinYinHeadChar = getPinYinHeadChar(brand.getFirstLetter());
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
