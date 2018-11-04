package org.mcare.acl.util;

import org.mcare.acl.permission.CustomPermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthenticationManagerUtil {

	public static boolean isPermitted(String permissionName){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomPermissionEvaluator customPermissionEvaluator = new CustomPermissionEvaluator();		
		return customPermissionEvaluator.hasPermission(auth, "returnObject", permissionName);		
	}
}
