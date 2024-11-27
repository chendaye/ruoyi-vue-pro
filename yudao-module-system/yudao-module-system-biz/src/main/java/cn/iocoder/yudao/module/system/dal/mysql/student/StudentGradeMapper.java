package cn.iocoder.yudao.module.system.dal.mysql.student;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.system.dal.dataobject.student.StudentGradeDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 学生班级 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface StudentGradeMapper extends BaseMapperX<StudentGradeDO> {

    default StudentGradeDO selectByStudentId(Long studentId) {
        return selectOne(StudentGradeDO::getStudentId, studentId);
    }

    default int deleteByStudentId(Long studentId) {
        return delete(StudentGradeDO::getStudentId, studentId);
    }

}