<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="zh-cn">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>表单</title>
</head>
<body>
<%--http://localhost:8080/javanote/test?user%5Bemail%5D=demo%40example.com&user%5Binfo%5D=+--%>
<form id="myform" >
<label for="email" >Email</label>
<input type="email"   name="email"  value="demo@example.com">
    <br/>
    <label for="email" >密码</label>
    <input type="password"   name="email"  value="demo@example.com">
    <br/>
<label for="skill" >技能</label>
<input type="checkbox"   name="skill[]"  checked="true" value="html5">HTML5
<input type="checkbox"   name="skill[]" checked="true" value="javascript">JAVASCRIPT
<input type="checkbox"   name="skill[]" checked="true" value="php">PHP
<input type="checkbox"   name="skill[]" checked="true"  value="python">python
<input type="checkbox"   name="skill[]"checked="true"   value="python">python
    <br/>

    <label for="skill" >性别：含有是否选择校验与默认值</label>
    男<input type="radio" name="sex" value="man" checked="true">
    女<input type="radio" name="sex" value="woman">
<br/>

<label for="skill" >月薪</label>
<select name="sallary" >
    <option value="" selected>----请选择----</option>
    <option value="5000">5000以下</option>
    <option value="5000-10000">5000-10000</option>
    <option value="10000-20000">10000-20000</option>
</select>
    <br/>
    权限证书：<input type="file" name="userFile"/>
<br/>


<label for="skill" >自我评价</label>
<textarea name="info"></textarea>
    <br/>


</form>
<button type="submit" id="subbtn" >提交</button>
<br/>

结果展示：<p id="result"></p>

填充赋值：

<br/>

<script src="https://libs.baidu.com/jquery/1.10.2/jquery.min.js"></script>
<script src="resources/jquery.serialize-object.js"></script>
<script src="resources/populate.js"></script>
<script>

 $(document).on('click','#subbtn', function(event) {
     //请求参数
     var list = {};
     var form_data = $('#myform').serializeArray();
     debugger;
     var json_data = $('#myform').serializeJSON();
     /*
     * 格式校验：
     * */
     // 左右空格去掉

     //是否是空字符串，或者必填，选填
     //是否是对应类型,范围
    //自己的业务校验

     /*trimData(form_data);
      //

      //
      */
     $.ajax({
         //请求方式
         type : "POST",
         //请求的媒体类型
         contentType: "application/json;charset=UTF-8",
         //请求地址
         url : "${pageContext.request.contextPath}/servlet/getFormDataServlet",
         //数据，json字符串
         data : json_data,
         //请求成功
         success : function(result) {
             console.log(result);
         },
         //请求失败，包含具体的错误信息
         error : function(e){
             console.log(e.status);
             console.log(e.responseText);
         }
     });
 });

</script>
</body>
</html>