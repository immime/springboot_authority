<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>Banner列表</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="shortcut icon" href="favicon.ico"> 
    <link href="${ctx!}/assets/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${ctx!}/assets/css/font-awesome.css?v=4.4.0" rel="stylesheet">

    <link href="${ctx!}/assets/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">

    <link href="${ctx!}/assets/css/animate.css" rel="stylesheet">
    <link href="${ctx!}/assets/css/style.css?v=4.1.0" rel="stylesheet">
	
	<style>
		.img-col {
			height: 24px;
		}
	</style>
</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content  animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox ">
                    <div class="ibox-title">
                        <h5>科目2文案</h5>
                    </div>
                    <div class="ibox-content">
                        <div class="row row-lg">
		                    <div class="col-sm-12">
		                    	<div class="example-wrap">
		                            <h4 class="example-title">工具条</h4>
		                            <div class="example">
		                                <div class="bootstrap-table"><div class="fixed-table-toolbar"><div class="bars pull-left"><div class="btn-group hidden-xs" id="exampleToolbar" role="group">
		                                	<@shiro.hasPermission name="content:secret:add">
				                        		<button class="btn btn-outline btn-default" type="button" onclick="add();"><i class="glyphicon glyphicon-plus" aria-hidden="true"></i>&nbsp;添加</button>
				                        	</@shiro.hasPermission>
		                                    <button type="button" class="btn btn-outline btn-default">
		                                        <i class="glyphicon glyphicon-trash" aria-hidden="true">&nbsp;删除</i>
		                                    </button>
		                                </div>
		                              </div>
		                              <div class="columns columns-right btn-group pull-right">
		                              	<button class="btn btn-default btn-outline" type="button" name="search" title="搜索" onclick="search();">搜索</button>
		                              </div>
		                              <div class="pull-right search"><input id="searchText" class="form-control input-outline" type="text" placeholder="输入标题搜索"></div>
		                              </div>
		                              	
		                              	<div class="fixed-table-container" style="padding-bottom: 0px;"><div class="fixed-table-header" style="display: none;"><table></table></div><div class="fixed-table-body"><div class="fixed-table-loading" style="top: 37px; display: none;">正在努力地加载数据中，请稍候……</div>
		                              	<table id="table_list"></table>
		                              	</div>
		                              	<div class="fixed-table-footer" style="display: none;"><table><tbody><tr></tr></tbody></table></div><div class="fixed-table-pagination" style="display: none;"></div></div></div><div class="clearfix"></div>
		                            </div>
		                        </div>
		                    
		                    </div>
	                    </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- 全局js -->
    <script src="${ctx!}/assets/js/jquery.min.js?v=2.1.4"></script>
    <script src="${ctx!}/assets/js/bootstrap.min.js?v=3.3.6"></script>
    
	<!-- Bootstrap table -->
    <script src="${ctx!}/assets/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
    <script src="${ctx!}/assets/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
    <script src="${ctx!}/assets/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>

    <!-- Peity -->
    <script src="${ctx!}/assets/js/plugins/peity/jquery.peity.min.js"></script>

    <script src="${ctx!}/assets/js/plugins/layer/layer.min.js"></script>

    <!-- 自定义js -->
    <script src="${ctx!}/assets/js/content.js?v=1.0.0"></script>

    <!-- Page-Level Scripts -->
    <script>
        $(document).ready(function () {
			initTable();
        });
        
        function initTable() {
        	//初始化表格,动态从服务器加载数据  
			$("#table_list").bootstrapTable({
			    //使用get请求到服务器获取数据  
			    method: "POST",
			    //必须设置，不然request.getParameter获取不到请求参数
			    contentType: "application/x-www-form-urlencoded",
			    //获取数据的Servlet地址  
			    url: "${ctx!}/admin/kemu3/list",
			    //表格显示条纹  
			    striped: true,
			    //启动分页  
			    pagination: true,
			    //每页显示的记录数  
			    pageSize: 10,
			    //当前第几页  
			    pageNumber: 1,
			    //记录数可选列表  
			    pageList: [5, 10, 15, 20, 25],
			    //表示服务端请求  
			    sidePagination: "server",
			    //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder  
			    //设置为limit可以获取limit, offset, search, sort, order  
			    queryParamsType: "undefined",
			    queryParams: function queryParams(params) {   //设置查询参数  
	              var param = {    
	                  pageNumber: params.pageNumber,
	                  pageSize: params.pageSize
	              };    
	              return param;                   
	            },  
			    //json数据解析
			    responseHandler: function(res) {
			        return {
			            "rows": res.list,
			            "total": res.total
			        };
			    },
			    //数据列
			    columns: [{
			        title: "序号",
			        field: "id",
			        formatter: function (value, row, index) {  
                        return index+1;  
                    }
			    },{
			        title: "ID",
			        field: "id",
			        sortable: true
			    },{
			        title: "标题",
			        field: "title"
			    },{
			        title: "操作",
			        field: "empty",
                    formatter: function (value, row, index) {
                    	var operateHtml = '<@shiro.hasPermission name="content:secret:edit"><button class="btn btn-primary btn-xs" type="button" onclick="edit(\''+row.id+'\')"><i class="fa fa-edit"></i>&nbsp;修改</button> &nbsp;</@shiro.hasPermission>';
                    	operateHtml = operateHtml + '<@shiro.hasPermission name="content:secret:deleteBatch"><button class="btn btn-danger btn-xs" type="button" onclick="del(\''+row.id+'\')"><i class="fa fa-remove"></i>&nbsp;删除</button> &nbsp;</@shiro.hasPermission>';
                    	operateHtml = operateHtml + '<@shiro.hasPermission name="content:secret:updateOrder"><button class="btn btn-info btn-xs" type="button" onclick="updateSort(\''+row.id+'\', \'up\')"><i class="glyphicon glyphicon-arrow-up"></i></button></@shiro.hasPermission>';
                    	operateHtml = operateHtml + '<@shiro.hasPermission name="content:secret:updateOrder"><button class="btn btn-info btn-xs" type="button" onclick="updateSort(\''+row.id+'\', \'down\')"><i class="glyphicon glyphicon-arrow-down"></i></button></@shiro.hasPermission>';
                        return operateHtml;
                    }
			    }]
			});
        }
        
        function edit(id){
        	location.href = '${ctx!}/admin/kemu3/edit/' + id;
        }
        function add(){
        	location.href = '${ctx!}/admin/kemu3/add';
        }
        function updateSort(id, upOrDown) {
        	console.log(id, upOrDown);
        	$.ajax({
	    		   type: "POST",
	    		   dataType: "json",
	    		   data: {
	    		     "id": id,
	    		     "upOrDown": upOrDown
	    		   },
	    		   url: "${ctx!}/admin/kemu3/updateOrder",
	    		   success: function(msg){
 	   	    			layer.msg(msg.message, {time: 2000},function(){
 	   	    				$('#table_list').bootstrapTable("refresh");
 	   					});
	    		   }
	    	});
        	$('#table_list').bootstrapTable("refresh");
        }
        function del(id){
        	layer.confirm('确定删除吗?', {icon: 3, title:'提示'}, function(index){
        		$.ajax({
    	    		   type: "POST",
    	    		   dataType: "json",
    	    		   url: "${ctx!}/admin/kemu3/delete/" + id,
    	    		   success: function(msg){
	 	   	    			layer.msg(msg.message, {time: 2000},function(){
	 	   	    				$('#table_list').bootstrapTable("refresh");
	 	   	    				layer.close(index);
	 	   					});
    	    		   }
    	    	});
       		});
        }
        
        function search() {
        	$("#table_list").bootstrapTable("refresh", {
        		query: {
        			pageNumber: 1,
	                searchText: $("#searchText").val()
        		}
        	});
        }
        
    </script>

    
    

</body>

</html>
