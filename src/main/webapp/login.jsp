<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	String path = request.getContextPath(); 
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; 
%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<!-- <title>Myron®ExtJs学习系统</title> -->
	<title>Java代码演示Demo</title>
	<%@ include file="/common/common.jsp"%>
	<script type="text/javascript">
	if(window.top.location.href!="<%=basePath%>"+"login.jsp"){
		window.top.location.href="<%=basePath%>"+'login.jsp';
	} 
	</script>
	
	<script type="text/javascript">
		Ext.Loader.setConfig({
			enabled : true,
			disableCaching:true,
			paths : {
				'ZTEsoft' : webRoot+'/common/js/extjs/public',
				'Ext.ux':webRoot+'/common/js/extjs/examples/ux',
				//'workflow':ctx+'/workflow'
			}
		});
	</script>
	
	<script>
	Ext.onReady(function() {  
		
		//定义验证码控件  
		var verifyCode=Ext.create('ZTEsoft.VerifyCode', {
			fieldLabel : '验证码',
			name : 'verifycode',
			id : 'verifycode',
			blankText : '验证码不能为空',
			codeImgUrl : 'verificationCode.do',
			labelAlign:'right',  
            labelWidth:65,  
            allowBlank: false  
			//tabIndex : 3,
			//width : 200
		});

	    var loginWindow=Ext.create('Ext.window.Window', {  
	        title : 'Myron®ExtJs学习系统',  
	        width : 300,  
	        height : 240,  
	        layout: 'fit',  
	        plain : true,  
	        modal : true,  
	        maximizable : false,  
	        draggable : false,  
	        closable : false,  
	        resizable : false, 
	        border:false,
	        items: {  
                //title : "身份认证",  
                xtype : 'form',  
                id : 'loginForm',  
                defaults : {  
                    width : 260,  
                    margin: '12 0 10 0'  
                },  
                // The fields  
                defaultType : 'textfield',  
                labelWidth : 40,  
                items: [{  
                    fieldLabel: '用户名',  
                    cls : 'user',  
                    name: 'username',  
                    labelAlign:'right',  
                    labelWidth:65,  
                    maxLength : 30,  
                    emptyText:'请在这里填写用户名',  
                    maxLengthText : '账号的最大长度为30个字符',  
                    blankText:"用户名不能为空，请填写！",//错误提示信息，默认为This field is required!  
                    allowBlank: false  
                },{  
                    fieldLabel: '密    码',  
                    cls : 'key',  
                    name: 'password',  
                    inputType:"password",  
                    labelWidth:65,  
                    labelAlign:'right',  
                    emptyText:'请在这里填写密码',  
                    maxLengthText :'密码长度不能超过20',  
                    maxLength : 20,  
                    blankText:"密码不能为空，请填写！",//错误提示信息，默认为This field is required!  
                    allowBlank: false  
                }/* ,{  
                    xtype:"combo",  
                    fieldLabel: '角   色',  
                    cls : 'Userkey',  
                    name: 'role',  
                    labelAlign:'right',  
                    labelWidth:65,  
                    store:["管理员","校领导","教职工"],//数据源为一数组  
                    emptyText:'请选择角色.',  
                    blankText:"请选择角色！",//错误提示信息，默认为This field is required!  
                    allowBlank: false  
                } */, {
                    xtype: "checkbox",
                    name: "rememberMe",
                    fieldLabel: "记住我",
                    uncheckedValue: false,   //未勾选时提交的默认值
                    labelWidth:65, 
                    labelAlign:'right'
                },verifyCode]  
            },  
	        // 重置 和 登录 按钮.  
	        buttonAlign:'center',
	        buttons: [{  
	            text: '重置',  
	            iconCls : 'Wrench',  
	            handler: function() {  
	                debugger;
	                var formPanel=loginWindow.down('form');
	                var form=formPanel.getForm();
	                form.reset();
	            }  
	        }, {  
	            text: '登录',  
	            iconCls : 'User',  
	            handler: function() { 
	            	var form=loginWindow.down('form');
	            	if (form.isValid()) {
	    				var submitValues = form.getValues();
	    				// 提交表单
	    				form.submit({
	    					url : "login.do",
	    					method : "POST",
	    					waitMsg : "正在登入,请稍后...",
	    					params : submitValues,
	    				    success:function(form, action) {
	    				    	
	    				    	location.href=action.result.view;
	    					},
	    					failure:function(form, action) {
	    						debugger;
	    						Ext.Msg.alert('提示', action.result.msg);

	    					} 
	    				});

	    			}
	            	
	                /* Ext.Msg.wait("请等待", "温馨提示", {  
	                    text:'正在登录……'  
	                });   */
	            }  
	        }]  
	    }).show();  
	});  
	</script>
	
	
</head>
<body>

</body>
</html>