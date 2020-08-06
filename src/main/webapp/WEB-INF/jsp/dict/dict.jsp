<%@ page import="java.util.Date" %>
<%@page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>
<%@page isELIgnored="false"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>字典管理</title>
	<meta http-equiv="Content-Type" content="text/html" charset="UTF-8"/>
	<c:set var="path" value="${pageContext.request.contextPath}"/>
	<link rel="stylesheet" href="${path}/plugins/bootstrap-3.3.7-dist/css/bootstrap.min.css">
	<link rel="stylesheet"href="${path}/plugins/bootstrap-table/bootstrap-table.min.css"/>
	<script src="${path}/plugins/jquery-3.4.1.min.js"></script>
	<script src="${path}/plugins/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	<script src="${path}/plugins/bootstrap-table/bootstrap-table.min.js"></script>
	<script src="${path}/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>

</head>
<body>
	<button id="btn-add" type="button" class="btn btn-default" onclick="goEdit('')">
		<span class="glyphicon glyphicon-plus" aria-hidden="true" ></span>新增
	</button>
	<table id="mytab" ></table>
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
				title:"字典ID",
				field: 'id',//id
				align: 'center',//对其方式
				valign: 'middle'//对其方式
			}, {
				title:"字典编码",
				field: 'code',//id
				align: 'center',//对其方式
				valign: 'middle'//对其方式
			},{
				title:"字典名称",
				field: 'name',//id
				align: 'center',//对其方式
				valign: 'middle'//对其方式
			},{
				title:"字典值",
				field: 'value',//id
				align: 'center',//对其方式
				valign: 'middle'//对其方式
			},{
				title: '操作',
				field: 'operate',
				align: 'center',
				events: window.operateEvents,
				formatter:function(value,row,index){
					return [
						'<div class="btn-group">',
						'<button id="btn_edit" type="button" class="btn btn-default" onclick="goEdit('+row.id+')">',
						'<span class="glyphicon glyphicon-pencil" aria-hidden="true" ></span>修改',
						'</button> ',
						'<button id="btn_delete" type="button" class="btn btn-default" onclick="deleteById('+row.id+",'"+row.code+"'"+')">',
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

	//行数据转化demo
	function genderDel(value, row, index) {
		/**
		 * 替换delete数据为文字
		 * @param {string} value 选值
		 * @param {object} row 行数据
		 * @param {number} index 行索引
		 * @return {string} 返回状态或"-"
		 */
		if (value == null || value == undefined) {
			return "-";
		} else if (value == 1) {
			return "已删除";
		} else if (value == 0) {
			return "正常";
		}
	}
	//操作事件建议卸载内部,防止第一次点击操作不生效
	window.operateEvents = {
		'click .trbtn-edit': function (e, value, row, index) {
			editData(row);
		},
		'click .trbtn-remove': function (e, value, row, index) {
			delData(row.custNo);
		}
	};

	function queryParams(params) {
		var temp = {
			limit:params.limit,
			offset: (params.offset / params.limit) + 1
		};
		console.log("查询条件");
		console.log(temp);
		return temp
	}

	function refresh() {
		/**
		 * 刷新表格数据
		 */

		//$table.bootstrapTable('refresh'.{url:""});//刷新时调用接口防止表格无限销毁重铸时出现英文
	}

	//事件部分
	$("#btn-query").on("click", function () {
		/** * 查询 */
		refresh();
	})


	function editData(row) {//row为表格内一行的数据传值
		console.log("修改页面")
	}

	function delData(strData) {
		/**
		 * 删除 单行or多行
		 * @param {string} strData 单行选中 数据
		 * @param {object} strData 多行中行 数组
		 */
		//多条数据转换
		if (typeof strData == "object") {
			strData = strData.join();
		}

		//确认操作
		if(confirm('确定要删除用户编号为' + strData + '数据?')){
			/**
			 * callback
			 * @param {boolean} result：true>= OK, false>= Cancel
			 */
			if (!result) {
				console.log("Cancel");
				return;
			}
			console.log("OK");
			console.log("删除数据");
			//组织数据-转换
			var sendData = {param:strData};
			console.log(sendData);
			$.ajax({
				url: 'user/deleteTest',
				method: 'POST',
				contentType: "application/x-www-form-urlencoded",
				data: sendData,
				//阻止深度序列化，向后台传送数组
				traditional: true,
				async : false,//这里同步，请按实际需求设置
				//成功
				success: function (msg) {
					console.log()
				},
				//请求错误
				error: function (err) {
					console.log(err)
				}
			})
		}
	}

	$("#btn-del").on("click", function () {
		/**
		 * 多行删除
		 */
		var checkDatas = $table.bootstrapTable('getSelections');//获取选中项
		console.log(checkDatas);
		if (checkDatas.length < 1) {
			alert("请先选择一条或多条数据");
		} else {
			var arr = [];
			for (var i = 0; i < checkDatas.length; i++) {
				arr.push(checkDatas[i].custNo);
			}
			console.log(arr);
			delData(arr);
		}
	});

	//删除
    function deleteById(id,code){
        if (confirm("确定要删除" + code + "吗？")) {
            $.ajax({
                url:'${path}/dict/delete',
                data:{
                    id:id
                },
                dataType:'json',
                type:'POST',
                success:function (data) {
					$table.bootstrapTable('refresh');
                }
            });
        }
    }
    //跳转到编辑页面
	function goEdit(id){
			window.location.href="${path}/dict/goEdit?id="+id;
	}
</script>
</html>
