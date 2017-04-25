
function ajaxSubmitEx(form){
	$(form).ajaxSubmit(function(data){
		$("#replace").replaceWith(data);
	});
	return false;
}

function checkMandatory(){
	var ret = true;
	$(".mandatoryInp").each(function(){
		var value = $(this).val();
		if(!value){
			ret = false;
			$(this).addClass("input-error");
		}else{
			$(this).removeClass("input-error");
		}
	});
	
	$("select.mandatorySel").each(function(){
		var value = $(this).select2("val");
		var id = $(this).attr("id");
		if(!value){
			$("#s2id_" + id + ">a").addClass("input-error");
			ret = false;
		}else{
			$("#s2id_" + id + ">a").removeClass("input-error");
		}
	});
	
	$("select.mandatoryDefSel").each(function(){
		var value = $(this).val();
		if(!value){
			ret = false;
			$(this).addClass("input-error");
		}else{
			$(this).removeClass("input-error");
		}
	});
	
	$("select.mandatoryMulSel").each(function(){
		var value = $(this).select2("val");
		var id = $(this).attr("id");
		if(value.length == 0){
			$("#s2id_" + id + ">a").addClass("input-error");
			ret = false;
		}else{
			$("#s2id_" + id + ">a").removeClass("input-error");
		}
	});
	
	return ret;
}

function isSessionAlive(){
	var result;
	
	$.ajax({
		type : "post",
		url : "checkSession",
		async: false,
		success : function(json){
			result = $.parseJSON(json.jsonResult);
		}
	});
	
	return result.isSessionAlive;
}


function ajaxLogin(){
	var username = $("#username").val();
	var password = $("#password").val();
	var ret = true;
	
	if(!username){
		$("#username").addClass("input-error");
		ret = false;
	}else{
		$("#username").removeClass("input-error");
	}
	
	if(!password){
		$("#password").addClass("input-error");
		ret = false;
	}else{
		$("#password").removeClass("input-error");					
	}
	
	if(ret==true){
		$("#ajaxLoginForm").ajaxSubmit(function(json){
			var result = $.parseJSON(json.jsonResult);
			if(result.valid==true){
				$("#loginModal").modal("hide");
				$("#password").val("");
			}else{
				$("#msg").text(result.msg);
			}
		});
	}
}

function getFormatDate(){
	var time = new Date();
	var year = time.getFullYear();
	var month = time.getMonth() + 1;
	month = month < 10 ? "0" + month : month;
	var day = time.getDate() < 10 ? "0" + time.getDate() : time.getDate();
	var hour = time.getHours() < 10 ? "0" + time.getHours() : time.getHours();
	var minute = time.getMinutes() < 10 ? "0" + time.getMinutes() : time.getMinutes();
	var second = time.getSeconds() < 10 ? "0" + time.getSeconds() : time.getSeconds();
	return year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;
}

/*
 * MAP对象，实现MAP功能
 *
 * 接口：
 * size()     获取MAP元素个数
 * isEmpty()    判断MAP是否为空
 * clear()     删除MAP所有元素
 * put(key, value)   向MAP中增加元素（key, value) 
 * remove(key)    删除指定KEY的元素，成功返回True，失败返回False
 * get(key)    获取指定KEY的元素值VALUE，失败返回NULL
 * element(index)   获取指定索引的元素（使用element.key，element.value获取KEY和VALUE），失败返回NULL
 * containsKey(key)  判断MAP中是否含有指定KEY的元素
 * containsValue(value) 判断MAP中是否含有指定VALUE的元素
 * values()    获取MAP中所有VALUE的数组（ARRAY）
 * keys()     获取MAP中所有KEY的数组（ARRAY）
 *
 * 例子：
 * var map = new Map();
 *
 * map.put("key", "value");
 * var val = map.get("key")
 * ……
 *
 */
function Map() {
    this.elements = new Array();

    //获取MAP元素个数
    this.size = function() {
        return this.elements.length;
    };

    //判断MAP是否为空
    this.isEmpty = function() {
        return (this.elements.length < 1);
    };

    //删除MAP所有元素
    this.clear = function() {
        this.elements = new Array();
    };

    //向MAP中增加元素（key, value) 
    this.put = function(_key, _value) {
        this.elements.push( {
            key : _key,
            value : _value
        });
    };

    //删除指定KEY的元素，成功返回True，失败返回False
    this.remove = function(_key) {
        var bln = false;
        try {
            for (i = 0; i < this.elements.length; i++) {
                if (this.elements[i].key == _key) {
                    this.elements.splice(i, 1);
                    return true;
                }
            }
        } catch (e) {
            bln = false;
        }
        return bln;
    };

    //获取指定KEY的元素值VALUE，失败返回NULL
    this.get = function(_key) {
        try {
            for (i = 0; i < this.elements.length; i++) {
                if (this.elements[i].key == _key) {
                    return this.elements[i].value;
                }
            }
        } catch (e) {
            return null;
        }
    };

    //获取指定索引的元素（使用element.key，element.value获取KEY和VALUE），失败返回NULL
    this.element = function(_index) {
        if (_index < 0 || _index >= this.elements.length) {
            return null;
        }
        return this.elements[_index];
    };

    //判断MAP中是否含有指定KEY的元素
    this.containsKey = function(_key) {
        var bln = false;
        try {
            for (i = 0; i < this.elements.length; i++) {
                if (this.elements[i].key == _key) {
                    bln = true;
                }
            }
        } catch (e) {
            bln = false;
        }
        return bln;
    };

    //判断MAP中是否含有指定VALUE的元素
    this.containsValue = function(_value) {
        var bln = false;
        try {
            for (i = 0; i < this.elements.length; i++) {
                if (this.elements[i].value == _value) {
                    bln = true;
                }
            }
        } catch (e) {
            bln = false;
        }
        return bln;
    };

    //获取MAP中所有VALUE的数组（ARRAY）
    this.values = function() {
        var arr = new Array();
        for (i = 0; i < this.elements.length; i++) {
            arr.push(this.elements[i].value);
        }
        return arr;
    };

    //获取MAP中所有KEY的数组（ARRAY）
    this.keys = function() {
        var arr = new Array();
        for (i = 0; i < this.elements.length; i++) {
            arr.push(this.elements[i].key);
        }
        return arr;
    };
}

