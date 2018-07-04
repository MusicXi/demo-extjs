(function repaint() {
	//如果Jquery已初始化,则执行按钮权限控制初始化代码
	if(typeof(jQuery) != 'undefined'){
		$(document).ready(_iniPrivilegePlugin);
	}else{	//否则继续等待下一次函数调用
		setTimeout(repaint, 200);
	}
})();
//初始化权限控制插件
function _iniPrivilegePlugin() {
	debugger;
	$.extend({privilege:{}});
	$.extend($.privilege,{
			masks:[],
			_move:false,
			_position:{},
			changeBtn:function(){
				var me = this;
				me.removeMask();
				me.masks = {};
				//查询所有按钮(type="button"的input标签和button标签)
				var buttons = $(":button");
				for(var i = 0;i<buttons.length;i++){
					var btnId = buttons[i].id;
					var btn = buttons[i];
					//隐藏按钮、操作和保存按钮不处理
					if($(btn).is(':hidden')||btn.id=="operateBtn"||btn.id=="okBtn")continue;
					$.privilege.disableBtn(btn);
				}
			},
			removeMask:function(title){
				var me=this;
				if(me.masks){
					for(var k in me.masks){
						me.masks[k].remove();
					}
				}
			},
			/**
			 * 在按钮上增加一层遮罩，从而实现按钮权限设置的效果
			 */
			disableBtn:function(btn){
				var me = this;
				var $editorMask = $('<div class="translate"></div>');
				if(btn.clientWidth==0||btn.clientHeight==0){
					return;
				}
				//在按钮上增加一层遮罩
				$editorMask.offset($(btn).offset()).width(btn.clientWidth+2).height(btn.clientHeight+2)
					.css({position: 'absolute',cursor:'pointer'}).appendTo(document.body);
				$editorMask.data('btn',btn);
				//检查按钮状态
				me.checkBtn($editorMask,btn);
				//遮罩点击处理函数
				$editorMask.click(function(e){
					e.stopPropagation();
					if($(this).data('status')){
						$(this).toggleClass('translate');
						$(this).toggleClass('disable');
						$(this).data('status',false);
						me.cascadeBy($(this).data('btn'),false);
					}
					else{
						$(this).toggleClass('translate');
						$(this).toggleClass('disable');
						$(this).data('status',true);
						me.cascadeBy($(this).data('btn'),true);
					}
					return false;
				});
		},
		/**
		 * 检查按钮状态，如果之前有设置按钮权限，需要在初始化时将按钮设为禁用状态
		 */
		checkBtn : function($editor, btn) {
            var me = this;
            if (!me.btns) return;
            var key = null;
            if (btn.privilegeCode) {
                key = btn.privilegeCode;
            } else if (btn.id) {
                key = btn.id;
            } else if (btn.textContent) {
                key = btn.textContent;
            } else {
                key = btn.className;
            }
            if (me.masks[key]) {
                $editor.remove();
            } else {
                me.masks[key] = $editor;
            }
            if (key && me.btns[key]) {
                me.masks[key].removeClass('translate');
                me.masks[key].addClass('disable');
                me.masks[key].data('status', true);
            }
        },
		/**
		 * 保存按钮是否禁用的状态
		 * @param {} btn
		 */
		cascadeBy:function(btn,checked,rf){
			var me = this;
			if(!me.btns)me.btns = {};		
			var key;
			if(btn.privilegeCode){
				key = btn.privilegeCode;
			}else if(btn.id){
				key = btn.id;
			}else if(btn.textContent){
				key = btn.textContent;
			}else{
				key = btn.className;
			}
			me.btns[key] = checked;
		},
		getQueryString:function(name) {
		    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
		    var r = window.location.search.substr(1).match(reg);
		    if (r != null) return unescape(r[2]); return null;
		},
		getUrl:function(){
			return window.location.href.substring(0,window.location.href.indexOf('?'));
		},
		saveAction:function(){
			var me = this;
			if(!me.btns)me.btns = {};
			var params = {privilegeId:me.getQueryString('privilegeId'),privilegeName:me.getQueryString('itemId')},list=[];
			for(var k in me.btns){
				if(me.btns[k])
					list.push({privilegeId:params.privilegeId,code:k,name:params.privilegeName,type:'BUTTON',direction:me.getUrl()});
			}
			params['list']=list;
			$.post(ctx+'/base/updatePrivilegeItems.do',{entityStr:JSON.stringify(params)},function(data,textStatus){
				alert('保存成功!');
				me.onSave = false;
			},"json");
		
		},
		loadAction:function(){
			var me=this;
			var privilegeId=me.getQueryString('privilegeId');
			var itemId=me.getQueryString('itemId');
			var params = {privilegeId:privilegeId,name:itemId};
			$.get(ctx+'/base/getPrivilegeList.do?tableName=privilege_resource', {conditionsKey: JSON.stringify(params)}, function(response, textStatus) {
                if(response.success=="true"){
					me.btns = {};
					var data=response.list;
					for(var i=0;i<data.length;i++){
						if(data[i].type=='BUTTON'){
							me.btns[data[i].code]=true;
						}
					}
				}
            });
		}
	});
	_initUI();
}

function _initUI(){
	//权限资源配置界面加载的菜单会加上proxy参数，此时需要提供可以配置和保存按钮权限的按钮
	if(window.location.href.indexOf('proxy')>-1){
		$('<div id="nav" class="drag" style="border: 2px solid #000; cursor: move; position: absolute; right: 0;top: 0;z-index:99999999"><button id="operateBtn" >操作</button>&nbsp;<button id="okBtn" >保存</button></div>').appendTo(document.body);
		$(".drag").mousedown(function(e) {
		      $.privilege._move = true;
		      $.privilege._position={x:e.pageX,y:e.pageY,right:parseInt($(".drag").css("right")),top:parseInt($(".drag").css("top"))};
		     //$(".drag").fadeTo(20, 0.5); //点击后开始拖动并透明显示
		});
		var bodywidth = $(document.body).width() - 95;
		var bodyheight = $(document.body).height();
		 $(document).mousemove(function(e) {
		     if ( $.privilege._move) {
		         var x = $.privilege._position.right - (e.pageX - $.privilege._position.x); //移动时根据鼠标位置计算控件左上角的绝对位置
		        var y = $.privilege._position.top+e.pageY - $.privilege._position.y;
		        //防止控件被拖出窗口外
		        if(x < 0){
		        	x = 0;
		        }else if(x > bodywidth){
		        	x = bodywidth;
		        }
		        if(y < 0){
		        	y = 0;
		        }else if(y > bodyheight){
		        	y = bodyheight;
		        }
		        $(".drag").css({ top: y, right: x}); //控件新位置
		    }
		 }).mouseup(function(e) {
		      $.privilege._move =false;
		     //$(".drag").fadeTo("fast", 1); //松开鼠标后停止移动并恢复成不透明
		     _x=parseInt($(".drag").css("right"));
		     _y=parseInt($(".drag").css("top"));
		     if( $.privilege._position.right==_x&& $.privilege._position.top==_y){
		     	 $.privilege._move=false;
		     }
		});
		$.privilege.loadAction();
		//操作按钮点击处理函数
		$("#operateBtn").click(function(){
			if($.privilege._move===false){
				$.privilege.changeBtn();
			}
		});
		//保存按钮点击处理函数
		$("#okBtn").click(function(){
			$.privilege.saveAction();
		});
	}else{
        buttonControl();
	}
}
/**
 * 控制界面按钮是否有权使用
 */
function buttonControl(){
	//查询所有按钮(type="button"的input标签和button标签)
	var buttons = $(":button");
	for (var i = 0; i < buttons.length; i++) {
        var btn = buttons[i];
        if ($(btn).is(':hidden'))continue;
        _changeBtn(btn);
    }
}
/**
 * 改变按钮是否可用的状态
 */
function _changeBtn(btn) {
    var key = btn.privilegeCode || btn.id || btn.textContent || btn.className;
    var index = window.location.href.indexOf("?");
    var url = index > -1 ? window.location.href.substring(0, index) : window.location.href;
    url = url.substring(webRoot.length - 1);
    //如果有设置按钮权限，则将按钮禁用
    if (session.buttonMap && session.buttonMap[url] && $.inArray(key,session.buttonMap[url]) >= 0) {
        btn.disabled = true;
    }
}