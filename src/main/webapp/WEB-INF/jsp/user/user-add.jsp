<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div style="padding:10px 10px 10px 10px">
    <form id="userAddForm" class="itemForm" method="post">
        <table cellpadding="5">
            <tr>
                <td>用户名:</td>
                <td>
                    <input class="easyui-textbox" type="text" name="username" data-options="required:true"
                           style="width: 280px;"/>
                </td>
            </tr>

            <tr>
                <td>用户编码:</td>
                <td>
                    <input class="easyui-textbox" type="text" name="usercode" data-options="required:true"
                           style="width: 280px;"/>
                </td>
            </tr>
            <tr>
                <td>密码:</td>
                <td>
                    <input class="easyui-textbox" type="password" id="password" name="password"
                           data-options="required:true"
                           style="width: 280px;"/>
                </td>
            </tr>

            <tr>
                <td>确认密码:</td>
                <td>
                    <input class="easyui-textbox" type="password" id="confirmPassword" name="confirmPassword"
                           data-options="required:true"
                           style="width: 280px;"/>
                </td>
            </tr>
        </table>
    </form>
    <div style="padding:5px">
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">提交</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">重置</a>
    </div>
</div>
<script type="text/javascript">
    //提交表单
    function submitForm() {
        //有效性验证
        if (!$('#userAddForm').form('validate')) {
            $.messager.alert('提示', '表单还未填写完成!');
            return;
        }
        if ($('#confirmPassword').val() != $('#password').val()) {
            $.messager.alert('提示', '密码不一致');
            return;
        }
        $.post("/user/save", $("#userAddForm").serialize(), function (data) {
            if (data.status == 200) {
                $.messager.alert('提示', '新增用户成功!');
            }
        });
    }

    function clearForm() {
        $('#userAddForm').form('reset');
    }
</script>
