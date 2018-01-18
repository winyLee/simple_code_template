<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/common/taglibs.jsp" %>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta charset="utf-8">
    <title></title>
    <meta name="description" content="overview &amp; stats">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
</head>
<body class="no-skin" screen_capture_injected="true">
<div class="main-content">
    <div class="breadcrumbs" id="breadcrumbs">
        <div class="row">
            <div class="col-md-8 col-xs-12">
                <ul class="breadcrumb ">
                    <li>
                        <i class="ace-icon fa fa-home home-icon"></i>
                    </li>
                </ul>
            </div>

            <div class="col-md-4 text-right">
                <a href="${mappingUrl}/newPage"   class="btn btn-primary btn-sm no-border"><i
                        class="ace-icon glyphicon glyphicon-plus"></i> 新增</a>
            </div>

        </div>
    </div>

    <div class="page-content">
        <div class="page-header">
            <div class="row">
                <div class="col-xs-12 page-search">
                    <form class="form-inline" id="${className}Form" action="${mappingUrl}/listPage" method="post">
                        <input name="currentPage" type="hidden" value="1">

                    <#list  fields as field >
                        <div class="form-group">
                            <label>${field.fieldName}：</label>
                            <input name="${field.fieldId}" value="${r'${'}${field.fieldId}}" class="input-sm  form-control" type="text">
                        </div>
                    </#list>

                        <div class="form-group">
                            <button type="submit" class="btn btn-info btn-xs no-border">
                                <i class="ace-icon fa fa-search bigger-120"></i>
                                <span class="bigger-120">搜索</span>
                            </button>

                            <button type="button" class="btn btn-info btn-xs no-border" onclick="searchFormClear()">
                                <i class="ace-icon fa fa-repeat  bigger-120"></i>
                                <span class="bigger-120">清空</span>
                            </button>
                        </div>

                    </form>
                </div>
                <!-- /.page-search -->
            </div>
            <!-- /.row page-search -->

            <div class="clearfix"></div>
        </div>
        <!-- /.page-header -->


        <div class="row tables-wrapper">
            <div class="col-xs-12">
                <!-- PAGE CONTENT BEGINS  -->

                <div class="table-responsive">

                    <table id="table-1" class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                        <#list  fields as field >
                            <th>${field.fieldName}</th>
                        </#list>
                            <th width="180">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:choose>
                            <c:when test="${r'${empty page.list}'}">
                                <tr>
                                    <td class="center" colspan="16">对不起,数据为空!</td>
                                </tr>
                            </c:when>
                            <c:otherwise>
                                <c:forEach items="${r'${page.list}'}" var="data" >
                                    <tr>
                                    <#list  fields as field >
                                        <td>${r"${data."}${field.fieldId}}</td>
                                    </#list>
                                        <td>
                                            <a class="btn btn-minier btn-danger" href="delData(${r'${data.id}'})">删除</a>
                                            <a class="btn btn-minier btn-success" href="${mappingUrl}/editPage?id=${r'${data.id}'}">编辑</a>
                                            <a class="btn btn-purple btn-minier" href="${mappingUrl}/detailPage?id=${r'${data.id}'}">详情</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                        </tbody>
                    </table>


                </div>
                <!-- /.page-paging 开始 分页 -->
                <winylee:page _formId="${className}Form"/>
            </div>
        </div>
        <!-- /.col -->
    </div>
    <!-- /.row -->
    <!-- PAGE CONTENT ENDS -->
</div>
<script type="text/javascript" src="common/js/commonForm.js"></script>
<script type="text/javascript">

    function delData(id){
        nsDialog.jConfirm("是否确认删除","提示",function(v){
            if(v){
                $.ajax({
                    timeout: 40000,
                    url:"${mappingUrl}/del",
                    data:{id:id},
                    complete: function (obj, status) {
                        if (status == 'timeout') {
                            //超时,status还有success,error等值的情况
                            nsDialog.jAlert("超时异常,请刷新重试", "错误");
                            return;
                        }
                        if (status == 'error') {
                            //超时,status还有success,error等值的情况
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
        })
    }

    function searchFormClear(){
        commonForm.resetForm("${className}Form");
    }

</script>
</body>
</html>