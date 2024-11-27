package cn.iocoder.yudao.module.system.service.category;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.system.controller.admin.category.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.category.CategoryDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 分类 Service 接口
 *
 * @author 芋道源码
 */
public interface CategoryService {

    /**
     * 创建分类
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCategory(@Valid CategorySaveReqVO createReqVO);

    /**
     * 更新分类
     *
     * @param updateReqVO 更新信息
     */
    void updateCategory(@Valid CategorySaveReqVO updateReqVO);

    /**
     * 删除分类
     *
     * @param id 编号
     */
    void deleteCategory(Long id);

    /**
     * 获得分类
     *
     * @param id 编号
     * @return 分类
     */
    CategoryDO getCategory(Long id);

    /**
     * 获得分类列表
     *
     * @param listReqVO 查询条件
     * @return 分类列表
     */
    List<CategoryDO> getCategoryList(CategoryListReqVO listReqVO);

}