package com.vin.auth.web;

import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;

import java.util.HashMap;
import java.util.Map;

/**
 * <br>
 *
 * @author levin
 * @since 1.0
 */
public class FilterChainDefinition implements ShiroFilterChainDefinition {

    /**
     * anon -- org.apache.shiro.web.filter.authc.AnonymousFilter
     * authc -- org.apache.shiro.web.filter.authc.FormAuthenticationFilter
     * authcBasic -- org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter
     * perms -- org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter
     * port -- org.apache.shiro.web.filter.authz.PortFilter
     * rest -- org.apache.shiro.web.filter.authz.HttpMethodPermissionFilter
     * roles -- org.apache.shiro.web.filter.authz.RolesAuthorizationFilter
     * ssl -- org.apache.shiro.web.filter.authz.SslFilter
     * user -- org.apache.shiro.web.filter.authc.UserFilter
     * logout -- org.apache.shiro.web.filter.authc.LogoutFilter
     */
    private Map<String, String> filter = new HashMap<>();

    @Override
    public Map<String, String> getFilterChainMap() {
        return filter;
    }

    public Map<String, String> getFilter() {
        return filter;
    }

    public void setFilter(Map<String, String> filter) {
        this.filter = filter;
    }
}
