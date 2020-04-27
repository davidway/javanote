<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/4/27
  Time: 16:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
   <input type="text" name="fname" value="tete">
</head>
<body>

</body>

<script>
    //只允许string的进入
    class RequiredCondition  {
        work(val){
            if ( val!=null && val!=""){
                val = val.trim();
                return true;
            }else{
                this.error = "此项必填"
                return false;
            }
        }
        getErrorMsg(){
            return this.error;
        }
    }

    class ConditionFactroy{
        getConditionBy(conditionString){
            switch(conditionString){
                case "required":
                    return new RequiredCondition();
                    break;

                default:
                    break;
            }
        }
    }
    class ValidateElement{


        constructor(selector,rule,message){
            this. selector =selector;
            this.rule = rule;
            this.message = message;
        }
        getSelectorParser(selectorString){
            var selector = selectorString[0];
            if ( selector =='#' || selector=='.'){
            var element = selectorString.substring(1,selectorString.length)
            }else{
                var element = selectorString;
            }
            var result ;
            switch(selector){
                case '#':
                    result = document.getElementById(element);
                    break;
                case '.':
                    result  = document.getElementsByClassName(element)
                    break;

                default:
                    result  = document.getElementsByName(element)[0]
                    break;

            }
            return result;
        }
         check(){
             var val = this.getSelectorParser(this.selector).value;

             var conditionArray = this.rule.split("|");
             var conditionFactory = new ConditionFactroy();
             for(var i=0 ; i<conditionArray.length ; i++){
                 var conditionElement = conditionFactory.getConditionBy(conditionArray[i]);
                if ( !conditionElement.work(val)){
                    this.error = conditionElement.getErrorMsg();

                }
             }
        }
         getError(){
             return this.error;
        }
    }


   window.onload=function(){
       var validateElementArray = [new ValidateElement('fname','required','元素必须'),
           new ValidateElement('fname','required','元素必须')];
       for ( var i=0 ; i<validateElementArray.length ; i++){
           if ( !validateElementArray[i].check()){
               alert(validateElementArray[i].getError());
           }
       }
   }

</script>
</html>
