package com.zz.service;

import com.zz.entity.AdminPerms;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Set;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zZeng
 * @since 2021-12-06
 */
public interface AdminPermsService extends IService<AdminPerms> {

    /**
     * 确定客户端请求是否拥有权限，是否需要拦截
     * @param requestUri：一个特定的uri，由客户端请求
     * @return 在数据库中找到该path时返回true
     */
    boolean needInterceptor(String requestUri);

    /**
     * 通过角色id查找所有权限
     * @param rid：角色id
     * @return list
     */
    List<AdminPerms> listPermsByRid(int rid);

    /**
     * 通过用户名查找权限路径
     * @param username：用户名
     * @return set
     */
    Set<String> listPermsUrlsByUser(String username);
}
