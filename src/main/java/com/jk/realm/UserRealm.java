package com.jk.realm;

import com.jk.bean.PermissionBean;
import com.jk.bean.RoleBean;
import com.jk.bean.UserBean;
import com.jk.service.ZzhService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * <pre>项目名称：
 * 类名称：:Administrator
 * 创建人：李健
 * 创建时间：2019/6/2-10:52
 * 修改备注：
 * @version </pre>
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private ZzhService zzhService;

    /**
     * 认证器
     **/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行认证");

        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        UserBean bean = zzhService.findByName(token.getUsername());

        if(bean == null){
            throw new UnknownAccountException();
        }
        ByteSource credentialsSalt = ByteSource.Util.bytes(bean.getName());

        String name = getName();//自己创建的Realm名字
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(bean,bean.getPassword(),credentialsSalt, name);//放入shiro.调用CredentialsMatcher检验密码

        SecurityUtils.getSubject().getSession().setAttribute("login", bean);
        return authenticationInfo;
    }


    // 模拟Shiro用户加密，假设用户密码为123456
    public static void main(String[] args){
        // 用户名
        String username = "aini";
        // 用户密码
        String password = "123123";
        // 加密方式
        String hashAlgorithName = "MD5";
        // 加密次数
        int hashIterations = 1;
        ByteSource credentialsSalt = ByteSource.Util.bytes(username);
        Object obj = new SimpleHash(hashAlgorithName, password,credentialsSalt, hashIterations);
        System.out.println(obj);
    }



    /**
     * 授权器
     **/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权");

        Subject subject = SecurityUtils.getSubject();
        UserBean user = (UserBean)subject.getPrincipal();
        if(user != null){
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            // 角色字符串集合
            Collection<String> rolesCollection = new HashSet<>();
            //权限字符串集合
            Collection<String> premissionCollection = new HashSet<>();

            Set<RoleBean> roles =user.getRoles();

            for(RoleBean role : roles){
                rolesCollection.add(role.getName());
                Set<PermissionBean> permissions = role.getPermissions();

                for (PermissionBean permission : permissions){
                    premissionCollection.add(permission.getUrl());
                }

                info.addStringPermissions(premissionCollection);
            }

            info.addRoles(rolesCollection);
            return info;
        }

        return null;
    }


}
