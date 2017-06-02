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
    <link href="${ctx!}/assets/js/plugins/fancybox/jquery.fancybox.min.css" rel="stylesheet">
	
	<style>
	  .lightBoxGallery img {
		    margin: 5px;
		    width: 160px;
		}
	</style>
	
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
                        <form class="form-horizontal m-t">
                        	<input type="hidden" id="id" name="id" value="${entity.id}">
                        	
                            <div class="form-group">
                                <label class="col-sm-2 control-label">提交时间</label>
                                <div class="col-sm-10">
                                    ${entity.gmtCreate}
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">用户名</label>
                                <div class="col-sm-10">
                                    ${entity.nickname}
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">联系方式</label>
                                <div class="col-sm-10">
                                    ${entity.phone}
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">平台类型</label>
                                <div class="col-sm-10">
                                    ${entity.platform}
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">内容</label>
                                <div class="col-sm-10">
                                    ${entity.content}
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">截图</label>
                                <div class="col-sm-10 lightBoxGallery">
                                	<#list entity.imgs as imgUrl>
                                	<a data-fancybox="gallery" href="${fileDomain}${imgUrl}">
									    <img alt="image${imgUrl_index}" src="${fileDomain}${imgUrl}" data-action="zoom" />
									</a>
                                	</#list>
								</div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-10 col-sm-offset-2">
                                    <button class="btn" type="button" onclick="javascript:location.href='/admin/feedback/index'">返回</button>
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

    <script src="${ctx!}/assets/js/plugins/layer/layer.min.js"></script>
    
    <script src="${ctx!}/assets/js/plugins/fancybox/jquery.fancybox.min.js"></script>
   	
   	<script type="text/javascript">
	
    </script>
</body>

</html>
