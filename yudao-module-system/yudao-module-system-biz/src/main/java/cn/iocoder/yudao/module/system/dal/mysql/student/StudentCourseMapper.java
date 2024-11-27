package cn.iocoder.yudao.module.system.dal.mysql.student;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.system.dal.dataobject.student.StudentCourseDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 学生课程 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface StudentCourseMapper extends BaseMapperX<StudentCourseDO> {

    default List<StudentCourseDO> selectListByStudentId(Long studentId) {
        return selectList(StudentCourseDO::getStudentId, studentId);
    }

    default int deleteByStudentId(Long studentId) {
        return delete(StudentCourseDO::getStudentId, studentId);
    }

}