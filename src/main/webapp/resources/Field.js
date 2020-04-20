function Field(params){
    this.field_id=params.fid;     //要验证的字段的ID
    this.validators=params.val;   //验证器对象数组
    this.on_suc=params.suc;       //当验证成功的时候执行的事件
    this.on_error=params.err;     //当验证失败的时候执行的事件
    this.checked=false;           //是否通过验证
}

/**
 * 关于验证器对象我们在后面来讨论，接下来我们扩展这个类，加入validate方法
 */
Field.prototype.validate=function(){
    //循环每一个验证器
    for(item in this.validators){
        //给验证器附加验证成功和验证失败的回调事件
        this.set_callback(this.validators[item]);
        //执行验证器上的Validate方法，验证是否符合规则
        if(!this.validators[item].validate(this.data())){
            break; //一旦任意一个验证器失败就停止
        }
    }
}
/**
 * 再加入一个获取字段值的方法：
 * @returns {*}
 */
Field.prototype.data=function(){
    return document.getElementById(this.field_id).value;
}

/**
 * 设置验证器回调函数的方法set_callback如下：
 * @param val
 */
Field.prototype.set_callback=function(val){
    var self=this;              //换一个名字来存储this，不然函数的闭包中会覆盖这个名字
    val.on_suc=function(){      //验证成功执行的方法
        self.checked=true;      //将字段设置为验证成功
        self.on_suc(val.tips);  //执行验证成功的事件
    }
    val.on_error=function(){    //验证失败的时候执行的方法
        self.checked=false;     //字段设置为验证失败
        self.on_error(val.tips);//执行验证失败的事件
    }
}
