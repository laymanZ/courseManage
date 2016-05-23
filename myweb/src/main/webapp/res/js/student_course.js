/**
 * 
 */
//存放所有课程
var users = users || {};
//操作类型
var operateType = "";
//存放搜索对象
var searchUsers = searchUsers || {};
//用户构造方法
var Course = {
        Create:function(courseCode,courseName,hadSNum,mostSNum,teacherCode,xueFen){
            this.courseCode = courseCode;
            this.courseName = courseName;
            this.hadSNum = hadSNum;
            this.mostSNum = mostSNum;
            this.teacherCode = teacherCode;
            this.xueFen = xueFen;
        },
        //选课
        selectCourseData:function (){
        	var data = {courseCode:this.courseCode}
        	$.ajax({
      	  	  url : 'selectCourse',
      	  	  type : "POST" ,
      	  	  dataType:'json',                	       
      	  	  data : data,
      	  	  success : function(data){
      	  		 data=eval(data);
      	  		 alert(data.msg);
      	  		 loadUserDatas();
      	  	  }
      	    }); 
        },
        //编辑用户
        editUserData:function(){
            for(var i in users){
                if(this.courseCode == users[i].courseCode){
                    users[i].courseName = this.courseName;
                    users[i].hadSNum = this.hadSNum;
                    users[i].mostSNum = this.mostSNum;
                    users[i].teacherCode = this.teacherCode;
                    users[i].xueFen = this.xueFen;
                }
            }
        },
        //查找用户
        findUserData:function(data){
        searchUsers = {};
            for(var i in users){
                if(data.courseCode.indexOf(users[i].courseCode) >= 0 || 
                        data.teacherCode.indexOf(users[i].teacherCode) >= 0){
                       //alert(i);
                    searchUsers[users[i].courseCode] = users[i];
                    refreshDatas(searchUsers);
                }
            }
        }
};

function New(aClass,aParams){
    function new_(){
        aClass.Create.apply(this,aParams);
    }
    new_.prototype = aClass;
    return new new_();
}



/**
 * 首次加载页面执行方法
 */
function loadUserDatas(){   
	users = {};
	$.post('loadCourses',
			null,//如果你需要传参数的话，可以写在这里，格式为：{参数名：值,参数名：值...}                  
			function(data){//执行成功之后的回调函数           
				//alert(data); 
				var models = eval('('+data+')');// 对Servlet回传的字符串转换为json对象数组
				var us = models[0];
				for(k in us){
					var u = us[k];
					var initUser = New(Course,[u.courseCode,u.courseName,u.hadSNum,u.mostSNum,u.teacherCode,u.xueFen]);
				    users[k] = initUser;
				}
				addRowData(users);
				refreshDatas(users);              
			}
	);
	
    return users;
}

/**
 * 往表格添加一行html数据
 */
function addRowData (datas){
    var tbodyElement = document.getElementById("tbody");
    var html = "";
    var color = "warning";
    var flag = true;
    for(var i in datas){
        if(flag){
            color = "info";
        }else{
            color = "warning";
        }
        html = html +  "<tr class='"+ color +"'><td style='width:30px;'><input type='checkbox'></td><td id='courseCode'>"
                + datas[i].courseCode +"</td><td id='courseName'>"
                + datas[i].courseName +"</td><td id='teacherCode'>"
                + datas[i].teacherCode +"</td><td id='xueFen'>"
                + datas[i].xueFen +"</td><td id='mostSNum'>"
                + datas[i].mostSNum +"</td><td id='hadSNum'>"
                + datas[i].hadSNum +"</td>" 
                +"</tr>";
                
        flag = !flag;//颜色转换
    }
    tbodyElement.innerHTML = html;
}
/**
 * 刷新用户数据
 */
function refreshDatas(datas){
    addRowData(datas);
};

/**
 * 收集一行数据
 */
function collectionRowData(param){
    var tdElement = param.getElementsByTagName("td");
    var userArr = [];
    for(var i=1;i<tdElement.length;i++){
        var temp =  tdElement[i].textContent;
        userArr[i-1] = temp;
    }
    var user = New(Course,userArr);
   // alert(user);
    return user;
}
/**
 * 用户操作方法
 */
function optionUserData(param){
    //获得操作类别
    var optionType = param.getAttribute("id");
    if(optionType == "user_select"){
        var checkRowData = isCheckedData();
        var user = collectionRowData(checkRowData);
        user.selectCourseData();        
    }else if(optionType == "user_edit"){
        operateType = "edit";
        var checkRowData = isCheckedData();
        var user = collectionRowData(checkRowData);
        var modal_body = document.getElementById("modal-body");
        var inputElements=  modal_body.getElementsByTagName("input");
        for(var i=0;i<inputElements.length;i++){
            var temp = inputElements[i].id.substring(2,inputElements[i].id.length)
            inputElements[i].value = user[temp];
        }
        user.editUserData();
    }else if(optionType == "user_find"){
        var s_code =  document.getElementById("s_code").value;
        var s_userName =  document.getElementById("s_userName").value;
        var s_all=  document.getElementById("s_all").value;
        //搜索数据
        var s_data = s_data || {};
        s_data.courseCode = s_code;
        s_data.teacherCode = s_userName;
        s_data.all = s_all;
        var user = New(Course,[]);
        user.findUserData(s_data);
    }else{
        
    }
}

/**
 * 是否选中数据,返回选中数据的行
 */
function isCheckedData(){
    var tbodyElement =document.getElementById("tbody");
    var trElements = tbodyElement.getElementsByTagName("tr");
    var flag = false;
    for(var i=0;i<trElements.length;i++){
        var inputElement = trElements[i].getElementsByTagName("input")[0];
        if(inputElement.checked){
            flag = true;
            return trElements[i];
        }
    }
    if(!flag){
        alert("请选择一条记录！");
        $('#myModal').unbind("on");
    }
}
