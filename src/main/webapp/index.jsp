<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
    <title>Form表单</title>
</head>

<body>
<form action="${pageContext.request.contextPath}/servlet/doFormServlet" method="post">
    用户名：<input type="text" name="username">
    <input type="submit" value="提交" id="submit">
</form>

<form method="post" action="${pageContext.request.contextPath}/servlet/doFormServlet" onsubmit="return dosubmit()" method="post">
    javascript 防止表单提交：<input type="text" name="username">
    <input type="submit" value="提交" >
   <script>
       var isCommitted = false;//表单是否已经提交标识，默认为false
       function dosubmit(){
           if(isCommitted==false){
               isCommitted = true;//提交表单后，将表单是否已经提交标识设置为true
               return true;//返回true让表单正常提交
           }else{
               return false;//返回false那么表单将不提交
           }
       }
   </script>
</form>

<form method="post" action="${pageContext.request.contextPath}/servlet/TestF5Flash">
    订单名称：<input type="text" name="orderName"/>
    <input type="submit"/>
</form>


<form method="post" action="${pageContext.request.contextPath}/servlet/unieKeyTest">
    唯一索引表单提交测试：<input type="text" name="username"/>
    <input type="submit"/>
</form>
<form action="${pageContext.request.contextPath}/servlet/tokenSolveServlet" method="post">
    <input type="hidden" name="token" value="${token}"/>
    Token表单重复提交测试 用户名：<input type="text" name="username">
    <input type="submit" value="提交">
</form>


</body>
</html>