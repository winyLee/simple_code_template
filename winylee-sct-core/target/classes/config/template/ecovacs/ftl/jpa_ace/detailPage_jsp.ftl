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
        <div class="widget-box transparent">
            <div class="widget-header widget-header-large">
                <h3 class="widget-title grey lighter">
                    <i class="ace-icon fa fa-leaf green"></i>
                   详情
                </h3>
            </div>
            <!-- widget 标题 -->
        </div>
        <!-- widget-box  -->
        <div class="space-8"></div>
        <div class="page-content judged-div">
            <!-- 基本信息start -->
            <div class="col-xs-12">
                <!-- PAGE CONTENT BEGINS  -->
                <div class="col-xs-12">
                    <div class="widget-box transparent">
                        <h3 class="header blue lighter">
                            <i class="ace-icon fa fa-leaf green"></i>详情
                        </h3>

                        <div class="row">
                            <div class="col-md-4">
                                <ul class="list-unstyled ">
                                    <#list  fields as field >
                                        <li>
                                            <label>${field.fieldName}�?${r'${data.'}${field.fieldId}}</label>
                                        </li>
                                    </#list>
                                </ul>
                                <ul class="list-unstyled ">
                                <#list  fields as field >
                                    <li>
                                        <label>${field.fieldName}�?${r'${data.'}${field.fieldId}}</label>
                                    </li>
                                </#list>
                                </ul>
                                <ul class="list-unstyled ">
                                <#list  fields as field >
                                    <li>
                                        <label>${field.fieldName}�?${r'${data.'}${field.fieldId}}</label>
                                    </li>
                                </#list>
                                </ul>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
            <!-- 基本信息end -->
            <div class="clearfix"></div>
        </div>
    </div>
    <!-- /.col -->
</div>
</body>
</html>