<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta charset="utf-8">
    <title>新增</title>
    <meta name="description" content="overview &amp; stats">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
</head>

<body class="no-skin" screen_capture_injected="true">
<div class="main-content">
    <div class="breadcrumbs" id="breadcrumbs">
        <div class="row">
            <div class="col-md-8 col-xs-12">
                <!-- /.breadcrumb -->
            </div>
            <div class="col-md-4 text-right">

            </div>
        </div>
    </div>

    <div class="page-content">

        <div class="row">
            <div class="col-xs-12 ">
                <div class="page-box">
                    <h3 class="header blue lighter">
                        <i class="ace-icon fa fa-edit smaller-90"></i>基本信息
                    </h3>
                </div>

            </div>
            <div class="col-xs-6 text-right"></div>

        </div>
        <div class="clearfix"></div>

        <div class="row">
            <form class="form-horizontal" id="${className}AddForm" action="product/save" method="post">
                <div class="col-md-8">
                <#list  fields as field >
                    <div class="form-group row">
                        <label class="col-md-2 col-xs-2 text-right"><b class="red">*</b>${field.fieldName}：</label>

                        <div class="col-md-10  col-xs-10">
                            <input name="${field.fieldId}" type="text" class="col-xs-12 col-sm-6 col-md-6"/>
                        </div>
                    </div>
                </#list>

                    <div class="clearfix form-actions">
                        <div class="col-md-offset-3 col-md-10">
                            <button class="btn btn-info" type="button" onclick="submitData()">
                                <i class="ace-icon fa fa-check bigger-110"></i> 提交保存
                            </button>
                        </div>
                    </div>
            </form>
        </div>
    </div>
    <!-- /.col -->
    <!-- /.row -->

</div>
<!-- /.page-content -->
</div>
<script type="text/javascript">
    $(function () {
        $("#${className}AddForm").validate({
            rules: {
                <#list  fields as field >
                    "${field.fieldId}": {
                        required: true
                    },
                </#list>
            },
            messages: {
                <#list  fields as field >
                    "${field.fieldId}": {
                        required: "请输入${field.fieldId}"
                    },
                </#list>
            },
            errorPlacement: function (error, element) {
                var errplace = element.siblings('.v_tip');
                errplace.html("<i class='glyphicon glyphicon-ban-circle'></i>" + error.text());
                errplace.addClass('text-danger').removeClass('text-success');
            },
            success: function (label, element) {
                var obj = $(element).siblings('.v_tip');
                obj.html("<i class='glyphicon glyphicon-ok-circle'></i>");
                obj.addClass('text-success').removeClass('text-danger');
            },
            submitHandler: function () {
            }
        });
    });

    function submitData() {
        var s = $("#${className}AddForm").valid();
        if (s) {
            $("#${className}AddForm")[0].submit();
        }
    }
</script>
<style type="text/css">
    .from-ul li {
        padding-bottom: 8px
    }
</style>
</body>
</html>