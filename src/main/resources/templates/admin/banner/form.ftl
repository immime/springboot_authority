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
    <link href="${ctx!}/assets/js/plugins/dropzone/min/dropzone.min.css" rel="stylesheet">

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>完整验证表单</h5>
                    </div>
                    <div class="ibox-content">
                        <form class="form-horizontal m-t" id="frm" method="post" action="${ctx!}/admin/banner/save">
                        	<input type="hidden" id="id" name="id" value="${banner.id}">
                        	
                            <div class="form-group">
                                <label class="col-sm-3 control-label">图片:</label>
                                <input type="hidden" id="iconUrl" name="iconUrl" value="${banner.iconUrl}">
                                <div class="col-sm-8">
	                                <div class="dropzone" id="myDropzone">
									  <div class="am-text-success dz-message">
									    将文件拖拽到此处<br>或点此打开文件管理器选择文件
									  </div>
									</div>
								</div>
                            </div>
							
                            <div class="form-group">
                                <label class="col-sm-3 control-label">跳转链接：</label>
                                <div class="col-sm-8">
                                    <input id="targetUrl" name="targetUrl" class="form-control" type="text" value="${banner.targetUrl}" <#if banner?exists> readonly="readonly"</#if> >
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">排序：</label>
                                <div class="col-sm-8">
                                    <input id="sortOrder" name="sortOrder" class="form-control" value="${banner.sortOrder}">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-8 col-sm-offset-3">
                                    <button class="btn btn-primary" type="submit">提交</button>
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
    
    <!-- dropzone -->
    <script src="${ctx!}/assets/js/plugins/dropzone/min/dropzone.min.js"></script>
    
    <script type="text/javascript">
    
    $(document).ready(function () {
    
    	var itemId = $("#id").val();
    
	    Dropzone.autoDiscover = false;
        var myDropzone = new Dropzone("#myDropzone", {
          url: "/admin/UploadCtrl/banner",
          addRemoveLinks: true,
          method: 'post',
          filesizeBase: 1024,
          sending: function(file, xhr, formData) {
            formData.append("filesize", file.size);
          },
          success: function (file, response, e) {
        	if (response && response.statusCode === 1) {
        		$("#iconUrl").val(response.content);
        	}
        	else {
              $(file.previewTemplate).children('.dz-error-mark').css('opacity', '1')
            }
          },
        });
        
        if (itemId) {
        	var dbIconUrl = "${fileDomain}" + $("#iconUrl").val();
        	if (dbIconUrl && dbIconUrl !=='') {
        		// Create the mock file:
                var mockFile = { name: "Filename", size: 12345 };
             	// And optionally show the thumbnail of the file:
             	myDropzone.emit("addedfile", mockFile);
                myDropzone.emit("thumbnail", mockFile, dbIconUrl);
        	}
        }
	  	
	    $("#frm").validate({
    	    rules: {
    	    	targetUrl: {
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
   	    		   url: "${ctx!}/admin/banner/save",
   	    		   data: $(form).serialize(),
   	    		   success: function(msg){
	   	    			layer.msg(msg.message, {time: 2000},function(){
	   						var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
	   						parent.layer.close(index); 
	   					});
   	    		   }
   	    		});
            } 
    	});
    });
    </script>

</body>

</html>
