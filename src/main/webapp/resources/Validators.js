/**
 * 接下来我们就来看看验证器，验证器是真正执行验证过程的类，根据一般的验证过程，我们
 可以将其分类成，长度验证（包括必填验证），正则表达式验证，自定义函数验证，Ajax远
 程验证这几种，所以我们定义这几种验证器类，Ajax远程验证为了方便引用了jQuery，其他
 部分也有用到：
 * @param items
 * @constructor
 */



//长度验证的验证器类
function Len_val(min_l,max_l,tip){
    this.min_v=min_l;
    this.max_v=max_l;
    this.tips=tip;
    this.on_suc=null;
    this.on_error=null;
}
Len_val.prototype.validate=function(fd){
    if(fd.length<this.min_v||fd.length>this.max_v){
        this.on_error();
        return false;
    }
    this.on_suc();
    return true;
}
//正则表达式验证器
function Exp_val(expresion,tip){
    this.exps=expresion;
    this.tips=tip;
    this.on_suc=null;
    this.on_error=null;
}
Exp_val.prototype.validate=function(fd){
    if(!fd){
        this.on_suc();
        return true;
    }
    if(this.exps.test(fd)){
        this.on_suc();
        return true;
    }else{
        this.on_error();
        return false;
    }
}
//远程验证器
function Remote_val(url,tip){
    this.p_url=url;
    this.tips=tip;
    this.on_suc=null;
    this.on_error=null;
}
Remote_val.prototype.validate=function(fd){
    var self=this;
    $.post(this.p_url,{f:fd},
        function(data){
            if(data.rs){
                self.on_suc();
                return;
            }else{
                self.on_error();
            }
        },"json"
    );
    return false;
}
//自定义函数验证器
function Man_val(tip,func){
    this.tips=tip;
    this.val_func=func;
    this.on_suc=null;
    this.on_error=null;
}
Man_val.prototype.validate=function(fd){
    if(this.val_func(fd)){
        this.on_suc();
    }else{
        this.on_error();
    }
}