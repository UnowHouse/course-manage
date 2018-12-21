/**
 * 基本操作功能的实现
 * by Unow
 * finished in 2018.12.13
 * 
 */

(function($){
	server='http://localhost:8080/'
	listApi = server+'api/list';
	addNodeApi = server+'api/add';
	updateApi = server+'api/edit';
	deleteApi = server+'api/delete';
	logoutApi = server+'manager/logout';

	var main = {
        currentPage:1,
        totalPage:1,
        rows:5,
        data:[]
    };

    onLoad();
    BindEvent();

     /**
      * [sendRequest description:@封装Ajax请求]
      * @param  {[type]}  url         [description]
      * @param  {[type]}  param       [description]
      * @param  {Boolean} async       [description]
      * @param  {String}  type        [description]
      * @param  {[type]}  success_fn  [description]
      * @param  {[type]}  failure_fn  [description]
      * @param  {[type]}  contentType [description]
      * @return {[type]}              [description]
      */
	function sendRequest(url,param,async=true,type='get',success_fn,failure_fn,contentType){
		$.ajax({
			url:url,
			data:param,
			type:type,
			contentType:contentType,
			async:async,
			success:function(data){
				success_fn(data);
			},
			error:function(err_msg){
				failure_fn(err_msg);
			}
		});
	}

	/**
	 * [renderNodeByPid description:@发起ajax请求，并将获取到的数据渲染到树上，并且绑定main对象]
	 * @param  {[type]}  url        [description]
	 * @param  {[type]}  pid        [description]
	 * @param  {[type]}  parentNode [description]
	 * @param  {Boolean} show       [description]
	 * @return {[type]}             [description]
	 */
	function renderNodeByPid(url,pid,parentNode,show=true){

		if(!parentNode[0].nodes&&parentNode[0].isParent){
			sendRequest(url,{pid:pid},true,'get'
			,function(data){
				$.each(data,function(index,val){
					if(show){
						$('#left-tree').treeview('addNode',[val,parentNode]);
						$('#editName').val(parentNode[0].text);
						main.totalPage=Math.ceil(data.length/main.rows);
						main.currentPage = 1;
						showMsg(data);
					}else{
						$('#move-tree').treeview('addNode',[val,parentNode]);
					}
				});             
			},function(failue){
				$.showMsgText(failue.msg);
			});
		}
	}

	/**
	 * [addNode description:@发起增加节点请求，渲染且实时绑定main]
	 * @param {[type]} url        [description]
	 * @param {[type]} param      [description]
	 * @param {[type]} parentNode [description]
	 */
	function addNode(url,param,parentNode){
        sendRequest(url,param,true,'post',function(data){          
          	$('#left-tree').treeview('addNode', [data, parentNode]);
            main.totalPage=Math.ceil(parentNode[0].nodes.length/main.rows);
            showMsg(parentNode[0].nodes);
        },function(failue){
          	$.showMsgText("新增失败！");
        },"application/json");

     }

    /**
     * [updateNode description:@发起更新节点请求，渲染且实时绑定main]
     * @param  {[type]} url     [description]
     * @param  {[type]} oldNode [description]
     * @return {[type]}         [description]
     */
    function updateNode(url,oldNode){
        var param = {_method:"PUT",id:oldNode[0].id,text:$('#editName').val()};
        
        sendRequest(url,param,true,'post',function(data){ 
			oldNode.text=data.text;
			oldNode.recentTime=data.recentTime;
			data = oldNode;      
			$('#left-tree').treeview('updateNode', [ oldNode, data]);
			showMsg(data[0].nodes);
			$('#editName').val(data.text);
			$("#headTitle").text(data.text);
        },function(failue){
          	$.showMsgText("修改失败！");
        });

     }

     /**
      * [deleteNode description:@发起删除节点请求，渲染且实时绑定main]
      * @param  {[type]} url  [description]
      * @param  {[type]} node [description]
      * @return {[type]}      [description]
      */
     function deleteNode(url,node){
        var param = {_method:"DELETE",id:node[0].id}
        sendRequest(url,param,true,'post',function(data){
          	$('#left-tree').treeview('removeNode', [ node, { silent: true } ]);
          	$('#editName').val("");
          	$("#headTitle").text("");
          	showMsg([]);
        },function(){
          	$.showMsgText("删除失败！");
        });
    }

    /**
     * [add0 description:@配合转换时间戳的方法]
     * @param  {[type]} m [description]
     * @return {[type]}   [description]
     */
    function add0(m){
    	return m<10?'0'+m:m 
    }

    /**
     * [timestampToTime description:@转换时间戳]
     * @param  {[type]} timestamp [description]
     * @return {[type]}           [description]
     */
    function timestampToTime(timestamp) {
      
        //timestampÊÇÕûÊý£¬·ñÔòÒªparseInt×ª»»,²»»á³öÏÖÉÙ¸ö0µÄÇé¿ö
        if(!timestamp){
          	return;
        }
        var time = new Date(timestamp);
        var year = time.getFullYear();
        var month = time.getMonth()+1;
        var date = time.getDate();
        var hours = time.getHours();
        var minutes = time.getMinutes();
        var seconds = time.getSeconds();
        return year+'-'+add0(month)+'-'+add0(date);

    }

    /**
     * [onLoad description:@主要加载节点方法，只对id为left-tree有效]
     * @return {[type]} [description]
     */
    function onLoad(){
		sendRequest(listApi,{pid:0},true,'get',function(data){
			$('#left-tree').treeview({
				data:data,
				levels: 1,
				onNodeSelected:function(event, node){
					var parentNode = $('#left-tree').treeview('getSelected');
					renderNodeByPid(listApi,node.id,parentNode);
					$('#editName').val(node.text);
					$("#headTitle").text(node.text);
					if(node){
						if(node.nodes)
							main.totalPage=Math.ceil(node.nodes.length/main.rows);
						else
                            main.totalPage=1
						showMsg(node.nodes);
					}
				},
				showCheckbox:false//是否显示多选
			}); 

		},function(){
			$.showMsgText("加载失败！");
		});           
	}

	/**
	 * [showMsg description:@渲染详情页面]
	 * @param  {[type]} list [description]
	 * @return {[type]}      [description]
	 */
	function showMsg(list){
		if(list)
			list = list.slice(main.currentPage*main.rows-main.rows,main.currentPage*main.rows);
		$("#content").empty();  
		var parentNode = $('#left-tree').treeview('getSelected');
		$.each(list,function(data,val){
			$tr = $("<tr></tr>");
            row0 = "<td>"+val.id+"</td>"; $tr.append(row0)
			row1 = "<td>"+getParents(parentNode,[parentNode[0].text])+"</td>"; $tr.append(row1)
			row2 = "<td>"+val.text+"</td>"; $tr.append(row2)
			row3 = "<td>"+timestampToTime(val.createTime)+"</td>"; $tr.append(row3)
			row4 = "<td>"+timestampToTime(val.recentTime)+"</td>"; $tr.append(row4)

			$("#content").append($tr)
		});
	}

	/**
	 * [getParents description:@遍历父节点,获取所有父节点名称]
	 * @param  {[type]} node      [description]
	 * @param  {[type]} parentArr [description]
	 * @return {[type]}           [description]
	 */
	function getParents(node,parentArr){
		parentNode=$('#left-tree').treeview('getParents', node);
		if(parentNode.length==0){
			parentArr = parentArr.reverse().join('/');

			return parentArr;
		}
		parentArr.push(parentNode[0].text);
		return getParents(parentNode,parentArr);
	}

	/**
	 * [BindEvent description:@注册交互事件]
	 */
	function BindEvent(){
		//保存-新增
		$("#Save").click(function () {
			$('#addOperation-dialog').modal('hide')
			var parentNode = $('#left-tree').treeview('getSelected');
			var param = { text:$('#addName').val(),pid:parentNode[0].id,parentStatu:parentNode[0].isParent }
			addNode(addNodeApi,JSON.stringify(param),parentNode);
		});
		//保存-编辑
		$('#Edit').click(function(){
			var node = $('#left-tree').treeview('getSelected');
			if(node.length==0){
				$.showMsgText("请选择一门学科")
			}
			updateNode(updateApi,node);
		});
		//显示-添加
		$("#btnAdd").click(function(){
			var node = $('#left-tree').treeview('getSelected');
			if (node.length == 0) {
				$.showMsgText('请选择一门学科');
				return;
			}
			$('#addName').val('');
			$('#addOperation-dialog').modal('show');

		});
		//删除
		$("#btnDel").click(function(){
			var node = $('#left-tree').treeview('getSelected');
			if (node.length == 0) {
				$.showMsgText('请选择节点');
				return;
			}
			BootstrapDialog.confirm({
				title: '提示',
				message: '确定删除此节点?',
				size: BootstrapDialog.SIZE_SMALL,
				type: BootstrapDialog.TYPE_DEFAULT,
				closable: true,
				btnCancelLabel: '取消', 
				btnOKLabel: '确定', 
				callback: function (result) {
					if(result){
						deleteNode(deleteApi,node);
					}
				}
			}); 
		});

		//显示-添加根节点
		$("#setting").click(function(){
          $("#addOperation-dialog-root").modal('show');
             
        });

		//保存-添加根节点
		$("#rootSave").click(function(){
			$('#addOperation-dialog-root').modal('hide');

			var param = { text:$('#addRootName').val(),pid:0,parentStatu:true };
			sendRequest(addNodeApi,JSON.stringify(param),true,'post',function(data){  
				$('#left-tree').treeview('remove');       
				onLoad();
			},function(failue){
				$.showMsgText("新增失败！");
			},"application/json");   
		});

		//显示-移动节点
		$("#btnMove").click(function(){
            var node = $('#left-tree').treeview('getSelected');
            if(node.length == 0){
                $.showMsgText("请选择一门学科！");
                return;
            }
			$('#addOperation-dialog-move').modal('show');
			sendRequest(listApi,{pid:0},true,'get',function(data){
				$('#move-tree').treeview({
					data:data,
					levels: 1,
					onNodeSelected:function(event, node){
						var parentNode = $('#move-tree').treeview('getSelected');
						renderNodeByPid(listApi,node.id,parentNode,false);
					},
					showCheckbox:false//是否显示多选
				}); 

			},function(){
				$.showMsgText("加载失败！");
			});          
		}); 

		//保存-移动节点
		$("#moveSave").click(function(){
            var node = $('#move-tree').treeview('getSelected');
            if(node.length == 0){
                $.showMsgText("请选择一门学科！");
                return;
            }
			$('#addOperation-dialog-move').modal('hide');
			var node = $("#left-tree").treeview("getSelected");
			var mvnode = $("#move-tree").treeview("getSelected");
			var param = {_method:"PUT",id:node[0].id,pid:mvnode[0].id}

			sendRequest(updateApi,param,true,'post',function(data){  
			$('#left-tree').treeview('remove');       
				onLoad();
			},function(failue){
				$.showMsgText("修改失败！");
			}); 
		});

        //翻页-上一页
        $("#pre").click(function(){
            if(main.currentPage>1){
                var parentNode = $('#left-tree').treeview('getSelected');
                main.currentPage--;
                $("#viewpage").text(main.currentPage);
                showMsg(parentNode[0].nodes);
            }

        });
        //翻页-下一页
        $("#next").click(function(){
            if(main.currentPage<main.totalPage){
                var parentNode = $('#left-tree').treeview('getSelected');
                main.currentPage++;
                $("#viewpage").text(main.currentPage);
                showMsg(parentNode[0].nodes);
            }
        });

		$("#logout").click(function(){
			console.log(1);
			$.ajax({
				url:logoutApi,
				type:'get',
				success:function() {
					window.location.href = 'entrance.html';
				},
				error:function(){
					$.showMsgText("错误-500");
				}
			})
		});
        $('#input-select-node').change(function(){
            val = $('#input-select-node').val()
			if(val==""){
                $('#left-tree').treeview('clearSearch');
			}
		});
		$("#query").click(function(){
            result = $('#left-tree').treeview('search', [ $('#input-select-node').val(), { ignoreCase: true, exactMatch: false } ]);
			console.log(result);
		});

	}

})(jQuery);