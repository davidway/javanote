<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>test</title>
    <script src="https://libs.baidu.com/jquery/1.10.2/jquery.min.js"></script>
    <script type="text/javascript" src="resources/Field.js"></script>
    <script type="text/javascript" src="resources/Form.js"></script>
    <script type="text/javascript" src="resources/Validators.js"></script>

    <style>
        .suc { background-color:#00ff00;}
        .error { background-color:#ff0000;}
    </style>
</head>
<body>
<input type="text" id="f1" name="f1"/><span id="t1"></span><br/>
<input type="button" id="bt" value="提交"/>


<script type="text/javascript">
    var form;
    $(
        function(){

            var re = /^[0-9]+.?[0-9]*/
            var uf=new Form([new Field({
                fid:"f1",
                val:[new Len_val(1,5,"长度错误"),new Exp_val(re,"不是数字")],
                suc:function(text){
                    $('#t1').text('');
                    $('#t1').attr('class','suc');
                },
                err:function(text){
                    $('#t1').text(text);
                    $('#t1').attr('class','error');
                }
            })
            ]);
            uf.set_submit(
                "bt",
                function(form){
                    alert("表单已经提交了");
                }
            );
        }
    );
</script>
</body>
</html>