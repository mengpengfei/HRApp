package magicgis.hrm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.mapping.FetchType;
import magicgis.hrm.dao.provider.EmployeeDynaSqlProvider;
import magicgis.hrm.domain.Employee;
import static magicgis.hrm.util.common.HrmConstants.EMPLOYEETABLE;

/**   
 * @Description: EmployeeMapper接口
 */
public interface EmployeeDao {

	// 根据参数查询员工总数
	@SelectProvider(type=EmployeeDynaSqlProvider.class,method="count")
	Integer count(Map<String, Object> params);
	
	// 根据参数动态查询员工
	@SelectProvider(type=EmployeeDynaSqlProvider.class,method="selectWhitParam")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="CARD_ID",property="cardId"),
		@Result(column="POST_CODE",property="postCode"),
		@Result(column="QQ_NUM",property="qqNum"),
		@Result(column="BIRTHDAY",property="birthday",javaType=java.util.Date.class),
		@Result(column="CREATE_DATE",property="createDate",javaType=java.util.Date.class),
		@Result(column="DEPT_ID",property="dept",
			one=@One(select="magicgis.hrm.dao.DeptDao.selectById",
		fetchType=FetchType.EAGER)),
		@Result(column="JOB_ID",property="job",
			one=@One(select="magicgis.hrm.dao.JobDao.selectById",
		fetchType=FetchType.EAGER))
	})
	List<Employee> selectByPage(Map<String, Object> params);
	
	// 动态插入员工
	@SelectProvider(type=EmployeeDynaSqlProvider.class,method="insertEmployee")
	void save(Employee employee);

	// 根据id删除员工
	@Delete(" delete from "+EMPLOYEETABLE+" where id = #{id} ")
	void deleteById(Integer id);
	
	// 根据id查询员工
	@Select("select * from "+EMPLOYEETABLE+" where ID = #{id}")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="CARD_ID",property="cardId"),
		@Result(column="POST_CODE",property="postCode"),
		@Result(column="QQ_NUM",property="qqNum"),
		@Result(column="BIRTHDAY",property="birthday",javaType=java.util.Date.class),
		@Result(column="CREATE_DATE",property="createDate",javaType=java.util.Date.class),
		@Result(column="DEPT_ID",property="dept",
			one=@One(select="magicgis.hrm.dao.DeptDao.selectById",
		fetchType=FetchType.EAGER)),
		@Result(column="JOB_ID",property="job",
			one=@One(select="magicgis.hrm.dao.JobDao.selectById",
		fetchType=FetchType.EAGER))
	})
	Employee selectById(Integer id);
	
	// 动态修改员工
	@SelectProvider(type=EmployeeDynaSqlProvider.class,method="updateEmployee")
	void update(Employee employee);

}
