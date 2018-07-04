Ext.define('UserManager.common.CommonMethod', {
	/**
	 * 查询子节点所有id
	 * @param {} node
	 * @return {}
	 */
	findChildNodesId:function(node){
		var me=this;
		var id=node.internalId;
		var childNodes=node.childNodes;
		if(childNodes.length !=0){
			for(var i=0;i<childNodes.length;i++){
				//id=id+","+childNodes[i].internalId;
				id=id+","+me.findChildNodesId(childNodes[i]);
			}
		}
		return id;
	},
	
	/**
	 * 设置节点全部选中
	 * @param {} record
	 * @param {} checked
	 */
	checkChild : function(record, checked) {
		var me = this;
		record.set('checked', checked);
		var childNodes=record.childNodes;
		if(childNodes.length !=0){
			record.eachChild(function(child) {
						me.checkChild(child,checked);
			})
		}
	}
});