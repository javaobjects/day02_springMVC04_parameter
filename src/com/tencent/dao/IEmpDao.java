package com.tencent.dao;

import java.util.List;

import com.tencent.pojo.Emp;

/**
 * 
* <p>Title: IEmpDao</p>  
* <p>
*	Description: 
*  数据访问层--接口
*   
* </p> 
* @author xianxian 
* @date 2019年10月16日
 */
public interface IEmpDao {

	/**
	 * 
	 * <p>Title: selectEmps</p>  
	 * <p>
	 *	Description: 
	 *  查询所有雇员信息
	 *  
	 * </p> 
	 * @return 雇员list集合
	 */
	public List<Emp> selectEmps();


	/**
	 * 
	 * <p>Title: selectEmpByempIndex</p>  
	 * <p>
	 *	Description: 
	 *  查询单个雇员
	 * </p> 
	 * @param empIndex
	 * @return 雇员信息
	 */
	public Emp selectEmpByempIndex(Integer empIndex);
	
}
