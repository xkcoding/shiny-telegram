package com.xkcoding.shiny.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p>
 * 系统用户角色
 * </p>
 *
 * @package: com.xkcoding.shiny.model
 * @description： 系统用户角色
 * @author: yangkai.shen
 * @date: Created in 2018/8/15 下午6:51
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Table(name = "sys_user_role")
@Data
public class SysUserRoleDO {
	/**
	 * 用户ID
	 */
	@Id
	@Column(name = "user_id")
	private Integer userId;

	/**
	 * 角色ID
	 */
	@Id
	@Column(name = "role_id")
	private Integer roleId;

	/**
	 * 获取用户ID
	 *
	 * @return user_id - 用户ID
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * 设置用户ID
	 *
	 * @param userId 用户ID
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * 获取角色ID
	 *
	 * @return role_id - 角色ID
	 */
	public Integer getRoleId() {
		return roleId;
	}

	/**
	 * 设置角色ID
	 *
	 * @param roleId 角色ID
	 */
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
}