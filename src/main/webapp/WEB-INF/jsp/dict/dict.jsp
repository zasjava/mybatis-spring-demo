<%@ page import="java.util.Date" %>
<%@page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>
<%@page isELIgnored="false" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>字典管理</title>
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8"/>
    <c:set var="path" value="${pageContext.request.contextPath}"/>
    <link rel="stylesheet" href="${path}/plugins/bootstrap-3.3.7-dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="${path}/plugins/bootstrap-table/bootstrap-table.min.css"/>
    <script src="${path}/plugins/jquery-3.4.1.min.js"></script>
    <script src="${path}/plugins/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <script src="${path}/plugins/bootstrap-table/bootstrap-table.min.js"></script>
    <script src="${path}/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
    <script src="${path}/plugins/layui/lay/modules/layer.js"></script>

</head>
<body>
<div id="toolbar" class="btn-group">
    <button id="btn_add" type="button" class="btn btn-default btn-sm">
        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
    </button>
    <button id="btn_delete" type="button" class="btn btn-default btn-sm">
        <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>删除
    </button>
    <button id="btn_refresh" type="button" class="btn btn-default btn-sm">
        <span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>刷新
    </button>
    <button id="btn_clean" type="button" class="btn btn-default btn-sm">清空</button>
    <button id="btn_init" type="button" class="btn btn-default btn-sm">初始化</button>
    <button id="btn_toggleview" type="button" class="btn btn-default btn-sm">切换视图</button>
    <button id="btn_togglepage" type="button" class="btn btn-default btn-sm">显隐分页</button>
</div>

<div class="modal fade" id="addAndUpdate" tabindex="-1" role="dialog" aria-labelledby="addAndUpdateLabel">
    <input type="hidden" name="id" class="form-control" id="id" placeholder="字典ID">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">×</span></button>
                <h4 class="modal-title" id="addAndUpdateLabel">新增字典信息</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label for="code">字典编码</label>
                    <input type="text" name="code" class="form-control" id="code" placeholder="字典ID">
                </div>
                <div class="form-group">
                    <label for="name">字典名称</label>
                    <input type="text" name="name" class="form-control" id="name" placeholder="字典编码">
                </div>
                <div class="form-group">
                    <label for="value2">字典值</label>
                    <input type="text" name="value2" class="form-control" id="value2" placeholder="字典编码">
                </div>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default btn-sm" data-dismiss="modal"><span
                        class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭
                </button>
                <button type="button" id="btn_add_update_submit" class="btn btn-primary btn-sm"
                        data-dismiss="modal"><span
                        class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>保存
                </button>
            </div>
        </div>
    </div>
</div>

<table id="mytab"></table>
</body>
<script>
    var $table = $('#mytab');
    $(function () {
        tableInit();
    })

    function tableInit() {
        //先销毁表格
        $table.bootstrapTable('destroy');
        //再初始化表格
        $table.bootstrapTable({
            //请求地址,此处数据为本地加载
            url: "${path}/dict/lists",
            //请求方式
            method: "POST",
            //请求内容类型
            contentType: "application/x-www-form-urlencoded",
            //数据类型
            dataType: "json",
            //table高度：如果没有设置，表格自动根据记录条数觉得表格高度
            height: '582',
            toolbar:"#toolbar",
            //是否显示行间隔色
            striped: true,
            //是否启用排序
            sortable: true,
            //排序方式
            sortOrder: "asc",
            //是否使用缓存
            cache: false,
            //每行的唯一标识
            uniqueId: "id",
            //显示刷新按钮
            showRefresh: true,
            //切换显示样式
            showToggle: true,
            //默认显示详细视图
            cardView: false,
            //是否显示搜索
            search: true,
            //是否显示分页
            pagination: true,
            //是否启用点击选中行
            clickToSelect: false,
            //最少要显示的列数
            minimumCountColumns: 2,
            //显示隐藏列
            showColumns: true,
            //cell没有值时显示
            undefinedText: '-',
            //分页方式：client客户端分页，server服务端分页
            sidePagination: "server",
            //每页的记录行数
            pageSize: 6,
            //初始化加载第1页，默认第1页
            pageNumber: 1,
            //可供选择的每页的行数
            pageList: "[10, 20, 50, 80, 100]",
            paginationPreText: "上一页",
            paginationNextText: "下一页",
            //按钮样式
            buttonsClass: 'btn',
            //分页器class
            iconSize: 'pager',
            //查询条件
            queryParams: queryParams,
            //表头
            columns: [{
                field: 'state',//id
                checkbox: true,//checkbox
                align: 'center',//对其方式
                valign: 'middle'//对其方式
            }, {
                title: "字典ID",
                field: 'id',//id
                align: 'center',//对其方式
                valign: 'middle'//对其方式
            }, {
                title: "字典编码",
                field: 'code',//id
                align: 'center',//对其方式
                valign: 'middle'//对其方式
            }, {
                title: "字典名称",
                field: 'name',//id
                align: 'center',//对其方式
                valign: 'middle'//对其方式
            }, {
                title: "字典值",
                field: 'value',//id
                align: 'center',//对其方式
                valign: 'middle'//对其方式
            }, {
                title: '操作',
                field: 'operate',
                align: 'center',
                events: window.operateEvents,
                formatter: function (value, row, index) {
                    return [
                        '<div class="btn-group">',
                        '<button id="btn_edit" type="button" class="btn btn-default">',
                        '<span class="glyphicon glyphicon-pencil" aria-hidden="true" ></span>修改',
                        '</button> ',
                        '<button id="btn_delete" type="button" class="btn btn-default">',
                        '<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除',
                        '</button> ',
                        '</div>'
                    ].join('');
                }
            }],
            onLoadSuccess: function (res) {//可不写
                //加载成功时
                console.log(res);
            }, onLoadError: function (statusCode) {
                return "加载失败了"
            }, formatLoadingMessage: function () {
                //正在加载
                return "拼命加载中...";
            }, formatNoMatches: function () {
                //没有匹配的结果
                return '无符合条件的记录';
            }
        })
    }

    /*//行数据转化demo
    function genderDel(value, row, index) {
        /!**
         * 替换delete数据为文字
         * @param {string} value 选值
         * @param {object} row 行数据
         * @param {number} index 行索引
         * @return {string} 返回状态或"-"
         *!/
        if (value == null || value == undefined) {
            return "-";
        } else if (value == 1) {
            return "已删除";
        } else if (value == 0) {
            return "正常";
        }
    }*/

    function queryParams(params) {
        var temp = {
            limit: params.limit,
            offset: (params.offset / params.limit) + 1
        };
        return temp
    }
    //刷新
    function refresh(){
        $table.bootstrapTable('refresh');
    }
    //事件部分
    $("#btn-query").on("click", function () {
        /** * 查询 */
        refresh();
    })

    //新增按钮点击事件
    $("#btn_add").on("click", function () {
        $('#addAndUpdateLabel').text("新增用户信息");
        $('#addAndUpdate').modal({
            //点击ESC键,模态窗口即会退出。
            keyboard: true
        });
    });

    //清除弹窗原数据
    $("#addAndUpdate").on("hidden.bs.modal", function () {
        $('#id').val("");
        $('#code').val("");
        $('#name').val("");
        $('#value2').val("");
    });

    //弹框保存按钮点击事件
    $("#btn_add_update_submit").off().on('click', function () {
        var code = $('#code').val(),
            name = $('#name').val(),
            value = $('#value2').val(),
            id =$("#id").val();

        //验证数据
        if (!name) {
            layer.msg('请填写字典名称!', {icon: 2, time: 1500});
            return false;
        }

        if (!code) {
            layer.msg('请填写字典编码!', {icon: 2, time: 1500});
            return false;
        }
        if (!value) {
            layer.msg('请填写字典值!', {icon: 2, time: 1500});
            return false;
        }

        $.ajax({
            url: '${path}/dict/saveEdit',
            method: 'post',
            contentType: "application/x-www-form-urlencoded",
            data: {
                id:id,
                name: name,
                code: code,
                value: value
            },
            //阻止深度序列化，向后台传送数组
            traditional: true,
            success: function (data) {
                layer.msg(data.message, {icon: 1, time: 1500});
                refresh();
            }
        })
    });


    window.operateEvents = {
		"click #btn_edit": function (e, value, row, index) {
			goEdit(row);
		},
		"click #btn_delete": function (e, value, row, index) {
			deleteById(row.id, row.code);
		}
	}

    //删除
    function deleteById(id, code) {
        if (confirm("确定要删除" + code + "吗？")) {
            $.ajax({
                url: '${path}/dict/delete',
                data: {
                    id: id
                },
                dataType: 'json',
                type: 'POST',
                success: function (data) {
                    alert(data.message);
                    refresh();
                }
            });
        }
    }

    //跳转到编辑页面
    function goEdit(row) {
        $("#id").val(row.id);
        $("#name").val(row.name);
        $("#code").val(row.code);
        $("#value2").val(row.value);
        $('#addAndUpdateLabel').text("修改字典信息");
        //显示模态窗口
        $('#addAndUpdate').modal({
            //点击ESC键,模态窗口即会退出。
            keyboard: true
        });
    }


</script>
</html>
