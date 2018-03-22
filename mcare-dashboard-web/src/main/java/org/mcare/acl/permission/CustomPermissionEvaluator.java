package org.mcare.acl.permission;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

@Service
public class CustomPermissionEvaluator implements PermissionEvaluator {
	
	@Override
	public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
		System.err.println("permission:" + permission.toString());
		System.err.println("targetDomainObject:" + targetDomainObject.toString());
		System.err.println("getAuthorities:" + authentication.getAuthorities());

		Collection<? extends GrantedAuthority> role = authentication.getAuthorities();
		for (GrantedAuthority grantedAuthority : role) {
			
			if (grantedAuthority.getAuthority().equalsIgnoreCase(permission.toString())) {
				System.err.println("AUTHO:" + grantedAuthority.getAuthority());
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
		return false;
	}
	
}