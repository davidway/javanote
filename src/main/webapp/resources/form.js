/**
 * 最后我们用一个userform的类来做一个入口，在构造的时候传入Field对象的列表，并且将
 每一个控件的onblur事件绑定到validate的包装器上
 * @param items
 * @constructor
 */



function Form(items){
    this.f_item=items;                             //把字段验证对象数组复制给属性
    for(idx=0;idx<this.f_item.length;idx++){       //循环数组
        var fc=this.get_check(this.f_item[idx]);   //获取封装后的回调事件
        $("#"+this.f_item[idx].field_id).blur(fc); //绑定到控件上
    }
}
//绑定验证事件的处理器，为了避开循环对闭包的影响
Form.prototype.get_check=function(v){
    return function(){   //返回包装了调用validate方法的事件
        v.validate();
    }
}

Form.prototype.set_submit=function(bid,bind){
    var self=this;
    $("#"+bid).click(
        function(){
            if(self.validate()){
                bind();
            }
        }
    );
}

Form.prototype.validate=function(){
    for(idx in this.f_item){             //循环每一个验证器
        this.f_item[idx].validate();     //再检测一遍
        if(!this.f_item[idx].checked){
            return false;                //如果错误就返回失败，阻止提交
        }
    }
    return true;                         //一个都没错就返回成功执行提交
}