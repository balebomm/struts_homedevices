package com.github.balebomm.struts_homedevice.Interceptor;

import java.util.Map;

import com.github.balebomm.struts_homedevice.Models.NguoiDung;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AuthenticatedInterceptor extends AbstractInterceptor {
  @Override
  public String intercept(ActionInvocation invocation) throws Exception {
    Map<String, Object> session = invocation.getInvocationContext().getSession();
    NguoiDung nd = (NguoiDung) session.get("nguoidung");

    if (nd == null) {
      return "interceptor-fail";
    }

    if(nd.getLoaiquyen().equals("admin")) {
      return "interceptor-admin";
    }

    return "interceptor-khach"; 
  }
}
