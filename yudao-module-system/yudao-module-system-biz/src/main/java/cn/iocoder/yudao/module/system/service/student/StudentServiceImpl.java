package cn.iocoder.yudao.module.system.service.student;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.system.controller.admin.student.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.student.StudentDO;
import cn.iocoder.yudao.module.system.dal.dataobject.student.StudentCourseDO;
import cn.iocoder.yudao.module.system.dal.dataobject.student.StudentGradeDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.system.dal.mysql.student.StudentMapper;
import cn.iocoder.yudao.module.system.dal.mysql.student.StudentCourseMapper;
import cn.iocoder.yudao.module.system.dal.mysql.student.StudentGradeMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.system.enums.ErrorCodeConstants.*;

/**
 * 学生 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class StudentServiceImpl implements StudentService {

    @Resource
    private StudentMapper studentMapper;
    @Resource
    private StudentCourseMapper studentCourseMapper;
    @Resource
    private StudentGradeMapper studentGradeMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createStudent(StudentSaveReqVO createReqVO) {
        // 插入
        StudentDO student = BeanUtils.toBean(createReqVO, StudentDO.class);
        studentMapper.insert(student);

        // 插入子表
        createStudentCourseList(student.getId(), createReqVO.getStudentCourses());
        createStudentGrade(student.getId(), createReqVO.getStudentGrade());
        // 返回
        return student.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateStudent(StudentSaveReqVO updateReqVO) {
        // 校验存在
        validateStudentExists(updateReqVO.getId());
        // 更新
        StudentDO updateObj = BeanUtils.toBean(updateReqVO, StudentDO.class);
        studentMapper.updateById(updateObj);

        // 更新子表
        updateStudentCourseList(updateReqVO.getId(), updateReqVO.getStudentCourses());
        updateStudentGrade(updateReqVO.getId(), updateReqVO.getStudentGrade());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteStudent(Long id) {
        // 校验存在
        validateStudentExists(id);
        // 删除
        studentMapper.deleteById(id);

        // 删除子表
        deleteStudentCourseByStudentId(id);
        deleteStudentGradeByStudentId(id);
    }

    private void validateStudentExists(Long id) {
        if (studentMapper.selectById(id) == null) {
            throw exception(STUDENT_NOT_EXISTS);
        }
    }

    @Override
    public StudentDO getStudent(Long id) {
        return studentMapper.selectById(id);
    }

    @Override
    public PageResult<StudentDO> getStudentPage(StudentPageReqVO pageReqVO) {
        return studentMapper.selectPage(pageReqVO);
    }

    // ==================== 子表（学生课程） ====================

    @Override
    public List<StudentCourseDO> getStudentCourseListByStudentId(Long studentId) {
        return studentCourseMapper.selectListByStudentId(studentId);
    }

    private void createStudentCourseList(Long studentId, List<StudentCourseDO> list) {
        list.forEach(o -> o.setStudentId(studentId));
        studentCourseMapper.insertBatch(list);
    }

    private void updateStudentCourseList(Long studentId, List<StudentCourseDO> list) {
        deleteStudentCourseByStudentId(studentId);
		list.forEach(o -> o.setId(null).setUpdater(null).setUpdateTime(null)); // 解决更新情况下：1）id 冲突；2）updateTime 不更新
        createStudentCourseList(studentId, list);
    }

    private void deleteStudentCourseByStudentId(Long studentId) {
        studentCourseMapper.deleteByStudentId(studentId);
    }

    // ==================== 子表（学生班级） ====================

    @Override
    public StudentGradeDO getStudentGradeByStudentId(Long studentId) {
        return studentGradeMapper.selectByStudentId(studentId);
    }

    private void createStudentGrade(Long studentId, StudentGradeDO studentGrade) {
        if (studentGrade == null) {
            return;
        }
        studentGrade.setStudentId(studentId);
        studentGradeMapper.insert(studentGrade);
    }

    private void updateStudentGrade(Long studentId, StudentGradeDO studentGrade) {
        if (studentGrade == null) {
			return;
        }
        studentGrade.setStudentId(studentId);
        studentGrade.setUpdater(null).setUpdateTime(null); // 解决更新情况下：updateTime 不更新
        studentGradeMapper.insertOrUpdate(studentGrade);
    }

    private void deleteStudentGradeByStudentId(Long studentId) {
        studentGradeMapper.deleteByStudentId(studentId);
    }

}