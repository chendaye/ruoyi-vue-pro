package cn.iocoder.yudao.module.system.dal.mysql.category;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.system.dal.dataobject.category.CategoryDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.system.controller.admin.category.vo.*;

/**
 * 分类 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface CategoryMapper extends BaseMapperX<CategoryDO> {

    default List<CategoryDO> selectList(CategoryListReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<CategoryDO>()
                .likeIfPresent(CategoryDO::getName, reqVO.getName())
                .eqIfPresent(CategoryDO::getParentId, reqVO.getParentId())
                .betweenIfPresent(CategoryDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CategoryDO::getId));
    }

	default CategoryDO selectByParentIdAndName(Long parentId, String name) {
	    return selectOne(CategoryDO::getParentId, parentId, CategoryDO::getName, name);
	}

    default Long selectCountByParentId(Long parentId) {
        return selectCount(CategoryDO::getParentId, parentId);
    }

}