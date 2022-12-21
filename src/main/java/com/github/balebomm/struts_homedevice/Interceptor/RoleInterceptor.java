package com.github.balebomm.struts_homedevice.Interceptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.github.balebomm.struts_homedevice.Models.NguoiDung;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class RoleInterceptor extends AbstractInterceptor {

  private List<String> allowedRoles = new ArrayList<String>();
  private List<String> disallowedRoles = new ArrayList<String>();

  public void setAllowedRoles(String roles) {
    this.allowedRoles = stringToList(roles);
  }

  public void setDisallowedRoles(String roles) {
    this.disallowedRoles = stringToList(roles);
  }

  public String intercept(ActionInvocation invocation) throws Exception {
      HttpServletRequest request = ServletActionContext.getRequest();
      HttpServletResponse response = ServletActionContext.getResponse();
      String result = null;
      if (!isAllowed(request, invocation.getAction())) {
          result = handleRejection(invocation, response);
      } else {
          result = invocation.invoke();
      }
      return result;
  }

  protected List<String> stringToList(String val) {
      if (val != null) {
          String[] list = val.split("[ ]*,[ ]*");
          return Arrays.asList(list);
      } else {
          return this.castList(String.class, Collections.EMPTY_LIST);
      }
  }

  protected <T> List<T> castList(Class<? extends T> clazz, Collection<?> c) {
    List<T> r = new ArrayList<T>(c.size());
    for(Object o: c)
      r.add(clazz.cast(o));
    return r;
  }


  protected boolean isAllowed(HttpServletRequest request, Object action) {
    HttpSession session=request.getSession(false);
    boolean result = false;
    NguoiDung nguoidung=null;
    if(session!=null){
      nguoidung=(NguoiDung)session.getAttribute("nguoidung");
    }
    
    if (allowedRoles.size() > 0) {
        if(session==null || nguoidung==null){
          return result;
        }

        for (String role : allowedRoles) {
          if (role.equalsIgnoreCase(nguoidung.getLoaiquyen())) {
            result = true;
          }
        }
        return result;
    } else if (disallowedRoles.size() > 0) {
      if(session!=null || nguoidung!=null){
        for (String role : disallowedRoles) {
          if (role.equalsIgnoreCase(nguoidung.getLoaiquyen())) {
            return false;
          }
        }
      }
    }

    return true;
  }

  protected String handleRejection(ActionInvocation invocation,
    HttpServletResponse response)
    throws Exception {

    return "interceptor-fail";
  }

}
