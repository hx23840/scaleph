package cn.sliew.breeze.service.meta.impl;

import cn.sliew.breeze.dao.entity.MetaDataSet;
import cn.sliew.breeze.dao.mapper.MetaDataSetMapper;
import cn.sliew.breeze.dao.mapper.MetaDataSetTypeMapper;
import cn.sliew.breeze.service.convert.meta.MetaDataSetConvert;
import cn.sliew.breeze.service.dto.meta.MetaDataSetDTO;
import cn.sliew.breeze.service.meta.MetaDataSetService;
import cn.sliew.breeze.service.param.meta.MetaDataSetParam;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Service
public class MetaDataSetServiceImpl implements MetaDataSetService {

    @Autowired
    private MetaDataSetMapper metaDataSetMapper;
    @Autowired
    private MetaDataSetTypeMapper metaDataSetTypeMapper;

    @Override
    public int insert(MetaDataSetDTO metaDataSetDTO) {
        MetaDataSet set = MetaDataSetConvert.INSTANCE.toDo(metaDataSetDTO);
        return this.metaDataSetMapper.insert(set);
    }

    @Override
    public int update(MetaDataSetDTO metaDataSetDTO) {
        MetaDataSet set = MetaDataSetConvert.INSTANCE.toDo(metaDataSetDTO);
        return this.metaDataSetMapper.updateById(set);
    }

    @Override
    public int deleteById(Long id) {
        return this.metaDataSetMapper.deleteById(id);
    }

    @Override
    public int deleteBatch(Map<Integer, ? extends Serializable> map) {
        return this.metaDataSetMapper.deleteBatchIds(map.values());
    }

    @Override
    public Page<MetaDataSetDTO> listByPage(MetaDataSetParam param) {
        Page<MetaDataSetDTO> result = new Page<>();
        Page<MetaDataSet> list = this.metaDataSetMapper.selectPage(
                new Page<>(param.getCurrent(), param.getPageSize()),
                param.getDataSetTypeCode(),
                param.getDataSetCode(),
                param.getDataSetValue());
        List<MetaDataSetDTO> dtoList = MetaDataSetConvert.INSTANCE.toDto(list.getRecords());
        result.setCurrent(list.getCurrent());
        result.setSize(list.getSize());
        result.setRecords(dtoList);
        result.setTotal(list.getTotal());
        return result;
    }

    @Override
    public List<MetaDataSetDTO> listByType(Long dataSetTypeId) {
        List<MetaDataSet> list = this.metaDataSetMapper.selectList(
                new LambdaQueryWrapper<MetaDataSet>()
                        .eq(MetaDataSet::getDataSetTypeId, dataSetTypeId)
        );
        return MetaDataSetConvert.INSTANCE.toDto(list);
    }
}
