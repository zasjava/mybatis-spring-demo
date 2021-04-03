<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<table class="easyui-datagrid" id="userList" title="用户列表"
       data-options="singleSelect:false,collapsible:true,pagination:true,url:'/user/users',method:'get',pageSize:20,toolbar:toolbar">
    <thead>
    <tr>
        <th data-options="field:'ck',checkbox:true"></th>
        <th data-options="field:'id',width:60">用户ID</th>
        <th data-options="field:'username',width:200">用户名称</th>
        <th data-options="field:'usercode',width:200">用户编号</th>
        <th data-options="field:'salt',width:100">盐值</th>
        <th data-options="field:'locked',width:100,formatter:isLocked">是否锁定</th>
    </tr>
    </thead>
</table>
<div id="userEditWindow" class="easyui-window" title="编辑用户"
     data-options="modal:true,closed:true,iconCls:'icon-save',href:'/user/user-edit'"
     style="width:80%;height:80%;padding:10px;">
</div>
<script>
    function isLocked(val, row) {
        if (val == 0) {
            return '未锁定';
        } else if (val == 1) {
            return '已锁定';
        } else {
            return '未知';
        }
    }

    function getSelectionsIds() {
        var userList = $("#userList");
        var sels = userList.datagrid("getSelections");
        var ids = [];
        for (var i in sels) {
            ids.push(sels[i].id);
        }
        ids = ids.join(",");
        return ids;
    }

    var toolbar = [{
        text: '新增',
        iconCls: 'icon-add',
        handler: function () {
            $(".tree-title:contains('用户新增')").parent().click();
        }
    }, {
        text: '编辑',
        iconCls: 'icon-edit',
        handler: function () {
            var ids = getSelectionsIds();
            if (ids.length == 0) {
                $.messager.alert('提示', '必须选择一个用户才能编辑!');
                return;
            }
            if (ids.indexOf(',') > 0) {
                $.messager.alert('提示', '只能选择一个用户!');
                return;
            }

            $("#userEditWindow").window({
                onLoad: function () {
                    //回显数据
                    var data = $("#userList").datagrid("getSelections")[0];
                    $("#userList").form("load", data);
                }
            }).window("open");
        }
    }, {
        text: '删除',
        iconCls: 'icon-cancel',
        handler: function () {
            var ids = getSelectionsIds();
            $.messager.confirm('确认', '确定删除ID为 ' + ids + ' 的商品吗？', function (r) {
                if (r) {
                    var params = {
                        "ids": ids,
                        "goodStatus": 3
                    };
                    $.post("/good/changeStatus", params, function (data) {
                        if (data.status == 200) {
                            $.messager.alert('提示', '删除商品成功!', undefined, function () {
                                $("#itemList").datagrid("reload");
                            });
                        }
                    });
                }
            });
        }
    }];
</script>