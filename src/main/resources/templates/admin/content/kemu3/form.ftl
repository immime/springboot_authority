<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title> - 表单验证 jQuery Validation</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="shortcut icon" href="favicon.ico"> 
    <link href="${ctx!}/assets/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${ctx!}/assets/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="${ctx!}/assets/css/animate.css" rel="stylesheet">
    <link href="${ctx!}/assets/css/style.css?v=4.1.0" rel="stylesheet">
	<link href="${ctx!}/assets/js/plugins/bootstrap-fileinput/css/fileinput.min.css" rel="stylesheet">
	<link href="${ctx!}/assets/js/plugins/bootstrap-fileinput/themes/explorer/theme.min.css" rel="stylesheet">
	
</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>编辑须知</h5>
                    </div>
                    <div class="ibox-content">
                        <form class="form-horizontal m-t" id="frm" method="post" action="${ctx!}/admin/kemu3/save">
                        	<input type="hidden" id="id" name="id" value="${entity.id}">
                        	
                            <div class="form-group">
                                <label class="col-sm-2 control-label">标题</label>
                                <div class="col-sm-10">
                                    <input id="title" name="title" class="form-control" type="text" value="${entity.title}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">摘要</label>
                                <div class="col-sm-10">
                                    <input id="title" name="description" class="form-control" type="text" value="${entity.description}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">图片</label>
                                <div class="col-sm-10">
                                	<input type="hidden" id="thumbUrl" name="thumbUrl" value="${entity.thumbUrl}">
                                	<input id="fileInput" name="file" type="file" multiple class="file-loading" accept="image">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">内容</label>
                                <div class="col-sm-10">
                                    <input type="hidden" name="content" >
                                    <script id="content" type="text/plain" style="width:100%;height:500px;">${entity.content}</script>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-10 col-sm-offset-2">
                                    <button id="btnSubmit" class="btn btn-primary" type="submit">提交</button>
                                    <button class="btn" type="button" onclick="javascript:history.go(-1);">返回</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

    </div>


    <!-- 全局js -->
    <script src="${ctx!}/assets/js/jquery.min.js?v=2.1.4"></script>
    <script src="${ctx!}/assets/js/bootstrap.min.js?v=3.3.6"></script>

    <!-- 自定义js -->
    <script src="${ctx!}/assets/js/content.js?v=1.0.0"></script>

    <!-- jQuery Validation plugin javascript-->
    <script src="${ctx!}/assets/js/plugins/validate/jquery.validate.min.js"></script>
    <script src="${ctx!}/assets/js/plugins/validate/messages_zh.min.js"></script>
    <script src="${ctx!}/assets/js/plugins/layer/layer.min.js"></script>
    <!-- fileinput -->
    <script src="${ctx!}/assets/js/plugins/bootstrap-fileinput/js/fileinput.min.js"></script>
    <script src="${ctx!}/assets/js/plugins/bootstrap-fileinput/themes/explorer/theme.min.js"></script>
    <script src="${ctx!}/assets/js/plugins/bootstrap-fileinput/js/locales/zh.js"></script>
    <!-- ueditor -->
    <script src="${ctx!}/assets/js/plugins/ueditor/ueditor.config.js"></script>
    <script src="${ctx!}/assets/js/plugins/ueditor/ueditor.all.min.js"></script>
    
    <script type="text/javascript">
    
    var ue = UE.getEditor('content');
    
    var fileInputOptions = {
		theme: "explorer",
	    language : 'zh',
	    uploadUrl: "/admin/file/uploadPostImg",
	    maxFileCount: 1,
	    allowedFileExtensions: ['jpg', 'png', 'gif'],
	    showBrowse: false,
		browseOnZoneClick: true,
	    overwriteInitial: false,
	    initialPreviewAsData: true
    };
    
    $(document).ready(function () {
    
    	var itemId = $("#id").val();
    	
    	var thumbUrl = $("#thumbUrl").val();
    	var dbIconUrl = "${fileDomain}" + $("#thumbUrl").val();
    	if (thumbUrl) {
    		var extOpitions = $.extend({
	            initialPreview: [dbIconUrl]
	        }, fileInputOptions);
    	
    		$("#fileInput").fileinput(extOpitions).on("filebatchselected", function(event, files) {
	            $(this).fileinput("upload");
	        }).on('fileuploaded', function(event, data) {
	        	console.log(JSON.stringify(data));
		        if(data.response) {
	        		$("#thumbUrl").val(data.response.data);
	        	} else {
	        		console.error("上传失败");
	        	}
		    });
    	} else {
	    	$("#fileInput").fileinput(fileInputOptions).on("filebatchselected", function(event, files) {
	            $(this).fileinput("upload");
	        }).on('fileuploaded', function(event, data) {
	        	console.log(JSON.stringify(data));
	        	if(data.response) {
	        		$("#thumbUrl").val(data.response.data);
	        	} else {
	        		console.error("上传失败");
	        	}
		    });
    	}
    	
	    $("#frm").validate({
    	    rules: {
    	    	title: {
	    	    	required: true,
	    	        minlength: 4,
	    	    	maxlength: 100
	    	    }
    	    },
    	    messages: {},
    	    submitHandler:function(form){
    	    	$.ajax({
   	    		   type: "POST",
   	    		   dataType: "json",
   	    		   url: "${ctx!}/admin/kemu3/save",
   	    		   data: $(form).serialize(),
   	    		   success: function(msg){
	   	    			layer.msg(msg.message, {time: 2000},function(){
	   						history.go(-1);
	   					});
   	    		   }
   	    		});
            } 
    	});
    	
    	$("#btnSubmit").click(function() {
    		var content = ue.getContent();
	    	$("input[name='content']").val(content);
    	});
    	
    });
    </script>

</body>

</html>
