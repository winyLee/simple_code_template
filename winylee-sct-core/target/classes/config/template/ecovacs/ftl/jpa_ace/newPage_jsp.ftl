<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ include file="/WEB-INF/common/taglibs.jsp" %>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta charset="utf-8">
    <title>
        <%@ include file="/WEB-INF/common/title.jsp" %>
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
</head>
<body class="no-skin" screen_capture_injected="true">
<div class="main-content">
    <div class="breadcrumbs" id="breadcrumbs">
        <div class="row">
            <div class="col-md-8 col-xs-12">
                <%@ include file="/WEB-INF/common/breadcrumb.jsp" %>
            </div>
            <div class="col-md-4 text-right">

                <a href="crmOrderConfig/addPage" class="btn btn-info btn-sm no-border"><i
                        class="ace-icon glyphicon glyphicon-plus"></i>新增</a>
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
            <form class="form-horizontal" id="${className}AddForm" action="${mappingUrl}/add" method="post">
                <div class="col-md-8">
                <#list  fields as field >
                    <div class="form-group row">
                        <label class="col-md-2 col-xs-2 text-right"><b class="red">*</b>${field.fieldName}�?</label>

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
    });

    function submitData() {
        $("#${className}AddForm").ajaxSubmit({
            timeout: 40000,
            complete: function (obj, status) {

                if (status == 'timeout') {
                    //超时,status还有success,error等�?�的情况
                    nsDialog.jAlert("超时异常,请刷新重�?", "错误");
                    return;
                }

                if (status == 'error') {
                    //超时,status还有success,error等�?�的情况
                    nsDialog.jAlert(obj.responseText + "<br>请重试后，联系管理员", "错误");
                    return;
                }

                var responseText = jQuery.parseJSON(obj.responseText);
                if (responseText.success == true) {
                    window.location.href = "${mappingUrl}/listPage";
                } else {
                    nsDialog.jAlert(responseText.msg, "提示")
                }
            }
        })
    }
</script>
<style type="text/css">
    .from-ul li {
        padding-bottom: 8px
    }
</style>
</body>
</html>