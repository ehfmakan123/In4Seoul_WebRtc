package com.ssafy.common.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.ssafy.db.entity.Desk;
import com.ssafy.db.entity.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 현재 액세스 토큰으로 부터 인증된 유저의 부가 상세정보(활성화 여부, 만료, 롤 등) 정의.
 */
public class SsafyUserDetails implements UserDetails {



	Staff staff;
	Desk desk;

	boolean accountNonExpired;
    boolean accountNonLocked;
    boolean credentialNonExpired;
    boolean enabled = false;
    List<GrantedAuthority> roles = new ArrayList<>();
    
    public SsafyUserDetails(Staff staff, Desk desk) {
    		super();
			this.staff=staff;
			this.desk=desk;
    }


	public String getStaffId()
	{
		return this.staff.getStaffId();
	}

	public int getStaffPk() {return this.staff.getId(); }

	public String getDeskId()
	{
		return  this.desk.getDeskId();
	}
    public String getDeskPassword() {return this.desk.getPassword();}

	public int getDestPK() {return this.desk.getId() ;}

	public int getDeskAreaId() {
		return this.desk.getArea().getId();
	}

	public int getStaffAreaId() {
		return this.staff.getArea().getId();
	}



	@Override
	public boolean isAccountNonExpired() {
		return this.accountNonExpired;
	}
	@Override
	public boolean isAccountNonLocked() {
		return this.accountNonLocked;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return this.credentialNonExpired;
	}
	@Override
	public boolean isEnabled() {
		return this.enabled;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.roles;
	}

	@Override
	public String getPassword() {
		return null;
	}

	@Override
	public String getUsername() {
		return null;
	}

	public void setAuthorities(List<GrantedAuthority> roles) {
		this.roles = roles;
	}
}
