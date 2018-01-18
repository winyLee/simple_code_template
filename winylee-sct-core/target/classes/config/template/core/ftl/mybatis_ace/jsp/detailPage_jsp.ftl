<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/common/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta charset="utf-8">
    <title>详情</title>
    <meta name="description" content="overview &amp; stats">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
</head>

<body class="no-skin" screen_capture_injected="true">
<div class="main-content">
    <div class="breadcrumbs" id="breadcrumbs">
        <div class="row">
            <div class="col-md-8 col-xs-12">
            </div>
            <div class="col-md-4 text-right">

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
                                <ul class="list-unstyled "><#list  fields as field >
                                    <li>
                                        <label>${field.fieldName}�?${r'${data.'}${field.fieldId}}</label>
                                    </li>
                                </#list>
                                </ul>
                            </div>
                            <div class="col-md-4">
                                <ul class="list-unstyled ">
                                <#list  fields as field >
                                    <li>
                                        <label>${field.fieldName}�?${r'${data.'}${field.fieldId}}</label>
                                    </li>
                                </#list>
                                </ul>
                            </div>
                            <div class="col-md-4">
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