<%--
  Created by IntelliJ IDEA.
  User: weekendzhu
  Date: 2020/4/17
  Time: 20:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--
  >子代操作符
  兄弟操作符+
  ^返回上级  div>p^div
  * 多少次
  ()分组操作符
  属性操作符 div#header #是id div.news 是属性
  定制属性 a[target='' title='hello world']*3
  {}文本操作符
  $ 数值计算操作符（循环） li.item-$*3  -->  li.item
    $@N*N  li.item-$@3*3 ==> $@N N=3，从3开始 *3 开始计算
    $@-N
--%>
<form id="userForm">
     姓名：<input type="text" name="username" value="${username}"><br/>
    密码：<input type="password" name="password" value="${password}"><br/>
    <input type="submit" value="提交" onclick="printResult()" />
</form>
 请选择性别：<input type="radio" name="sex" value="男">男
<input type="radio" name="sex" value="女">女<br/>
<br/>
<label for="mail">请输入爱好</label>：<br/>音乐影视：<input type="checkbox" name="interesting" value="音乐影视"><br/>
外出旅游：<input type="checkbox" name="interesting" value="外出旅游"><br/>
社交活动：<input type="checkbox" name="interesting" value="社交活动"><br/>
<br/>
请选择省市：
<select name="area">
        <option selected> ---请选择省市</option>
        <optgroup label="北京市" >
                <option value="北京市海淀区">海淀区</option>
            <option value="北京市朝阳区">朝阳区</option>
            <option value="北京市东城区">东城区</option>
            <option value="北京市西城区">西城区</option>
        </optgroup><br/>
</select><br/>
<br/>
自我描述：<textarea name="" id="" cols="30" rows="10"></textarea><br/>
<br/>
文件：<input type="file" name="file"><br/>
<a href=""/>
<img href=""/>
<br/>
<datalist id="browsers">
    <option value="Internet Explorer">
    <option value="Firefox">
    <option value="Chrome">
    <option value="Opera">
    <option value="Safari">
</datalist>


</body>
</html>
